package com.chat.mq.config;

import com.chat.mq.Const;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.chat.mq.Const.ORDER_EXCHANGE;

/**
 * mq配置类型
 */
@Configuration
public class MqConfig {

    @Bean
    public FanoutExchange orderExchange() {
        return new FanoutExchange(ORDER_EXCHANGE);
    }

    @Bean
    public Queue orderQueue() {
        return new Queue(Const.ORDER_QUEUE);
    }

    @Bean
    public Binding orderBindding() {
        return BindingBuilder.bind(orderQueue()).to(orderExchange());
    }
}
