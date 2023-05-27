package com.emre.config.rabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    @Value("${rabbitmq.exchange}")
    String exchange;
    @Value("${rabbitmq.favCategoryQueue}")
    String favCategoryQueue;
    @Value("${rabbitmq.favCategoryBindingKey}")
    String favCategoryBindingKey;

    @Bean
    Queue favCategoryQueue() {
        return new Queue(favCategoryQueue);
    }

    @Bean
    DirectExchange directExchange() {
        return new DirectExchange(exchange);
    }

    @Bean
    public Binding binding(final Queue favCategoryQueue, final DirectExchange directExchange) {
        return BindingBuilder.bind(favCategoryQueue).to(directExchange).with(favCategoryBindingKey);
    }
}
