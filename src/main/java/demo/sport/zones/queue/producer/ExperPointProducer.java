package demo.sport.zones.queue.producer;

import cn.hutool.core.collection.CollectionUtil;
import com.alibaba.fastjson.JSONObject;
import demo.sport.domain.queue.ExperPointMessage;
import demo.sport.domain.queue.PlayExperMessage;
import demo.sport.domain.queue.TeamExperMessage;
import demo.sport.zones.convert.IZonesZoneConvert;
import demo.sport.zones.entity.PlaceExperFlowBO;
import demo.sport.zones.entity.PlayExperInfoBO;
import demo.sport.zones.entity.RankGameInfoBO;
import demo.sport.zones.entity.TeamExperInfoBO;
import demo.sport.zones.service.IPFileInfoService;
import demo.sport.zones.service.ITeamInfoService;
import demo.sport.zones.service.IThemeInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;


@Component
@RefreshScope
public class ExperPointProducer
{
    private static final Logger LOGGER = LoggerFactory.getLogger(ExperPointProducer.class);

    @Resource
    private KafkaTemplate<String, String> kafkaTemplate;


    @Value("${queue.kafka.exper-topic: hrms-sport-zones-exper}")
    private String KAFKA_TOPIC_EXPER_POINT;

    @Resource
    private IZonesZoneConvert zonesZoneConvert;

    @Resource
    private IPFileInfoService ipFileInfoService;
    @Resource
    private ITeamInfoService teamInfoService;

    @Resource
    private IThemeInfoService themeInfoService;

    public TeamExperMessage createExperMessage(String themeNo, String accntNo)
    {
        TeamExperInfoBO teamExperInfoBO = ipFileInfoService.calcuTeamExper(themeNo, accntNo);
        if (!ObjectUtils.isEmpty(teamExperInfoBO)) {
            TeamExperMessage teamExperMessage = new TeamExperMessage();
            teamExperMessage.setExperPoint(teamExperInfoBO.getExperPoint().divide(BigDecimal.valueOf(teamExperInfoBO.getTeamSize()), 2, RoundingMode.UP).multiply(BigDecimal.valueOf(100)).setScale(0));
            teamExperMessage.setTeamNo(teamExperInfoBO.getTeamNo());
            teamExperMessage.setChangeTime(LocalDateTime.now());
            return teamExperMessage;
        }
        return null;
    }


    public void writeQueueMessage(PlaceExperFlowBO placeExperFlowBO)
    {
        if (ObjectUtils.isEmpty(placeExperFlowBO)) {
            LOGGER.info("没有经验点数数据需要写入队列，直接返回");
        }
        try {
            ExperPointMessage experPointMessage = new ExperPointMessage();
            String strAccntNo = placeExperFlowBO.getAccountNo();
            String strPlayCode = placeExperFlowBO.getPlayCode();
            String strThemeNo = placeExperFlowBO.getThemeNo();

            List<RankGameInfoBO> rankGameInfoList = themeInfoService.queryGameList(strPlayCode);
            if (CollectionUtil.isEmpty(rankGameInfoList)) {
                LOGGER.info("表[t_review_rank_game]没有配置[playCode={}]的排行信息本玩法不参与排行，丢弃的玩法信息[{}]", strPlayCode, placeExperFlowBO);
                return;
            }
            RankGameInfoBO bo = rankGameInfoList.get(0);
            String strRankCode = Optional.ofNullable(bo).map(RankGameInfoBO::getRankCode).orElse(null);

            //查询玩法的经验点数
            PlayExperInfoBO playExperInfoBO = ipFileInfoService.queryPlayExper(strThemeNo, strAccntNo, strPlayCode);
            if (Objects.isNull(playExperInfoBO)) {
                LOGGER.info("无法在[t_place_profile_exper]查询到用户[account={}]，[playCode={}]的经验值信息，本次不再推送排行信息[{}]", strAccntNo, strPlayCode, placeExperFlowBO);
                return;
            }
            PlayExperMessage playExperMessage = new PlayExperMessage();
            BigDecimal nExperPoint = playExperInfoBO.getExperPoint();
            playExperMessage.setExperPoint(nExperPoint.multiply(BigDecimal.valueOf(100)).setScale(0));
            playExperMessage.setAccntNo(strAccntNo);
            playExperMessage.setRankCode(strRankCode);
            playExperMessage.setChangeTime(LocalDateTime.now());
            experPointMessage.setPlayExperMessage(playExperMessage);
            //查询团队经验点数
            TeamExperMessage teamExperMessage = createExperMessage(strThemeNo, strAccntNo);
            if (Objects.nonNull(teamExperMessage)) {
                experPointMessage.setTeamExperMessage(teamExperMessage);
            }
            //通过指定KEY，让相同用户的消息在分区在一个分区中，保证消息的顺序性
            ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(KAFKA_TOPIC_EXPER_POINT, strAccntNo, JSONObject.toJSONString(experPointMessage));
            future.addCallback(new ListenableFutureCallback<SendResult<String, String>>()
            {
                @Override
                public void onFailure(Throwable e)
                {
                    cbackErrorHandler(placeExperFlowBO, e);
                }

                @Override
                public void onSuccess(SendResult<String, String> result)
                {
                    cbackSucceHandler(result, placeExperFlowBO);
                }
            });
        } catch (Exception e) {
            cbackErrorHandler(placeExperFlowBO, e);
        }
    }

    /**
     * 写入队列成功时的回调函数
     *
     * @param result
     * @param placeExperFlowBO
     */
    private void cbackSucceHandler(SendResult<String, String> result, PlaceExperFlowBO placeExperFlowBO)
    {
        LOGGER.info("写入经验点数数据成功：TOPIC_NAME={}, EXPER_FLOW={},WRITE_RESULT={}", KAFKA_TOPIC_EXPER_POINT, JSONObject.toJSONString(placeExperFlowBO), result);
    }

    /**
     * 写入队列异常时回调函数
     *
     * @param placeExperFlowBO
     * @param e
     */
    private void cbackErrorHandler(PlaceExperFlowBO placeExperFlowBO, Throwable e)
    {
        //TODO 此处需要进行补偿处理
        LOGGER.error("写入经验点数数据异常：TOPIC_NAME={}, EXPER_FLOW={},WRITE_RESULT={}", KAFKA_TOPIC_EXPER_POINT, JSONObject.toJSONString(placeExperFlowBO), e);
    }
}
