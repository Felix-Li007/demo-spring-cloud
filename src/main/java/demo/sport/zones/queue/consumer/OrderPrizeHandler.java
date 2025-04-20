package demo.sport.zones.queue.consumer;

import org.apache.kafka.clients.consumer.Consumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.listener.ConsumerAwareListenerErrorHandler;
import org.springframework.kafka.listener.ListenerExecutionFailedException;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Component
class OrderPrizeHandler implements ConsumerAwareListenerErrorHandler
{
    private static final Logger LOGGER = LoggerFactory.getLogger(OrderPrizeHandler.class);

    @Override
    public Object handleError(Message<?> message, ListenerExecutionFailedException exception, Consumer<?, ?> consumer)
    {
        Object payload = message.getPayload();
        LOGGER.error("处理队列的消息异常：", exception);
//        List<TradePayResultMessage> boList = JSONObject.parseArray(payload.toString(), TradePayResultMessage.class);
//        boList.forEach(this::handleError);
//        consumer.commitAsync();
        return message;
    }
}