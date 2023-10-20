package com.atguigu.ssyx.search.receiver;

import com.atguigu.ssyx.mq.constant.MqConst;
import com.atguigu.ssyx.search.service.SkuApiService;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.amqp.RabbitProperties;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author user
 * @date 2023/10/19
 */
@Component
public class SkuReceiver {

    @Autowired
    private SkuApiService skuApiService;

    @RabbitListener(bindings = @QueueBinding(value = @Queue(
            value = MqConst.QUEUE_GOODS_UPPER, durable = "true"),
            exchange = @Exchange(value = MqConst.EXCHANGE_GOODS_DIRECT),
            key = {MqConst.ROUTING_GOODS_UPPER}))
    public void upperSku(Long id, Message message, Channel channel) throws IOException {
        if (id != null) {
            skuApiService.upperSku(id);
        }
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    }

    @RabbitListener(bindings = @QueueBinding(value = @Queue(
            value = MqConst.QUEUE_GOODS_LOWER, durable = "true"),
            exchange = @Exchange(value = MqConst.EXCHANGE_GOODS_DIRECT),
            key = {MqConst.ROUTING_GOODS_LOWER}))
    public void lowerSku(Long id, Message message, Channel channel) throws IOException {
        if (id != null) {
            skuApiService.lowerSku(id);
        }
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    }
}
