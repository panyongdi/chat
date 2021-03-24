package com.chat.client.config;

import com.chat.client.Const.Const;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.chat.client.Const.Const.ORDER_EXCHANGE;

/**
 *
 */
@Configuration
public class OrderConfig {
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
