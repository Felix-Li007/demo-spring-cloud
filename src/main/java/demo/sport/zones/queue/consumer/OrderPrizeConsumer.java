package demo.sport.zones.queue.consumer;

import com.alibaba.fastjson.JSONObject;
import com.hrms.order.domain.message.BetCalculationMessage;
import demo.sport.zones.common.config.ThreadPoolConfig;
import demo.sport.zones.entity.PlaceExperFlowBO;
import demo.sport.zones.queue.producer.ExperPointProducer;
import demo.sport.zones.service.IGatheInfoService;
import demo.sport.zones.service.IZoneInfoService;
import org.apache.kafka.clients.consumer.Consumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Component
public class OrderPrizeConsumer
{
    private static final Logger LOGGER = LoggerFactory.getLogger(OrderPrizeConsumer.class);

    @Resource
    private IZoneInfoService zoneInfoService;

    @Resource
    private ExperPointProducer experPointProducer;

    @Resource
    private ThreadPoolConfig threadPoolConfig;

    @Resource
    private IGatheInfoService gatheInfoService;

    /**
     * 读取队列中的消息，处理经验值的计算
     *
     * @param
     * @param ack
     * @param consumer
     */
    @KafkaListener(topics = "${queue.kafka.order-topic}", errorHandler = "orderPrizeHandler")
    public void readQueueMessage(List<String> experPointMessage, Acknowledgment ack, Consumer<?, ?> consumer)
    {
        if (ObjectUtils.isEmpty(experPointMessage)) {
            LOGGER.error("接收到经验点数消息为空，直接丢弃");
            consumer.commitAsync();
            ack.acknowledge();
            return;
        }

        LOGGER.info("收到经验点数消息：EXPER_MESSAGE={}", JSONObject.toJSONString(experPointMessage));
        List<BetCalculationMessage> betCalculationMessages = experPointMessage.stream().map(e -> JSONObject.parseObject(e, BetCalculationMessage.class)).collect(Collectors.toList());
        CompletableFuture[] futureList = betCalculationMessages.stream().map(betCalcuMessage -> {
            return CompletableFuture.runAsync(() -> {
                String strAccntNo = betCalcuMessage.getAccountNo();
                String strPlayCode = betCalcuMessage.getPlayId();
                String strZoneNo = betCalcuMessage.getBusinessScene();
                String strExperNo = betCalcuMessage.getCalcSid();
                String strGameCode = betCalcuMessage.getLotteryId();
                LocalDateTime ldtResultTime = betCalcuMessage.getPoolResultTime();
                PlaceExperFlowBO placeExperFlowBO = new PlaceExperFlowBO();
                placeExperFlowBO.setAccountNo(strAccntNo);
                placeExperFlowBO.setPlayCode(strPlayCode);
                placeExperFlowBO.setZoneNo(strZoneNo);
                placeExperFlowBO.setFetchTime(ldtResultTime);
                placeExperFlowBO.setExperNo(strExperNo);
                placeExperFlowBO.setGameCode(strGameCode);

                gatheInfoService.gathePlayTask(strAccntNo, strPlayCode);

                try {
                    placeExperFlowBO = zoneInfoService.calcuExperPoint(placeExperFlowBO);
                    if (!ObjectUtils.isEmpty(placeExperFlowBO)) {
                        //处理成功，写入队列通知榜单服务进行处理
                        experPointProducer.writeQueueMessage(placeExperFlowBO);
                    }
                } catch (Exception e) {
                    LOGGER.error("处理用户经验点数计算异常：ACCNT_NO={}, PLAY_CODE={}, ZONE_NO={}, FETCH_TIME={}", strAccntNo, strPlayCode, strZoneNo, JSONObject.toJSONString(ldtResultTime), e);
                }
            }, threadPoolConfig.ThreadPoolExecutor());
        }).toArray(CompletableFuture[]::new);
        if (!ObjectUtils.isEmpty(futureList)) {
            futureList = Arrays.stream(futureList).filter(Objects::nonNull).toArray(CompletableFuture[]::new);
            CompletableFuture.allOf(futureList).join();
        }
        consumer.commitAsync();
        ack.acknowledge();
        LOGGER.error("完成经验点数消息处理");
    }
}
