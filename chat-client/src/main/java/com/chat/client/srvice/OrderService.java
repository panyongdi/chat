package com.chat.client.srvice;

import cn.hutool.json.JSONObject;
import com.chat.client.Const.Const;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 *
 */
@Service
public class OrderService {
//
//    /**
//     * 死循环操作解决方法
//     * 1、控制重复次数+死信队列
//     */
//    public static int count=0;
//    @RabbitListener(bindings = @QueueBinding(value = @Queue(Const.ORDER_QUEUE),
//            exchange = @Exchange(value = Const.ORDER_EXCHANGE, type = ExchangeTypes.FANOUT)))
//    public void receive(JSONObject jsonObject) {
//        count++;
//        System.out.println("count=====>>>>>>"+count);
//        int i=1/0;
//
//        System.out.println("接收的信息===>>>>"+jsonObject.toString());
//
//    }


//    @RabbitListener(bindings = @QueueBinding(value = @Queue(Const.ORDER_QUEUE),
//            exchange = @Exchange(value = Const.ORDER_EXCHANGE, type = ExchangeTypes.FANOUT)))
//    public void receiveOrder(JSONObject jsonObject, Channel channel,Message message) throws IOException {
//        try {
//            System.out.println("tag=====>>>>>>"+message.getMessageProperties().getDeliveryTag());
//
//            int i=1/0;
//
//            System.out.println("接收的信息===>>>>"+jsonObject.toString());
//            //tag 消息头部
//            //手动签收
//            channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
//        }catch (Exception e){
//            /**
//             * 参数1、消息头部
//             * 参数2、是否引用于多条消息
//             * 参数3、是否重新投递，值为true时，消息会被无限次的放入消费队列重新消费
//             */
//            channel.basicNack(message.getMessageProperties().getDeliveryTag(),false,false);
//        }
//
//
//    }

    /**
     * 配置文件的方式
     *
     * @param jsonObject
     */
    @RabbitListener(queues = Const.ORDER_QUEUE)
    public void receiveMessage(JSONObject jsonObject) {
        System.out.println(jsonObject.toString());
    }
}
