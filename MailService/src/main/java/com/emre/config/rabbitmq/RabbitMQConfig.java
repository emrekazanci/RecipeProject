package com.emre.config.rabbitmq;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    @Value("${rabbitmq.mailQueue}")
    String mailQueue;

    @Bean
    Queue mailQueue() {
        return new Queue(mailQueue);
    }

    @Value("${rabbitmq.favCategoryQueue}")
    String favCategoryQueue;

    @Bean
    Queue favCategoryQueue() {
        return new Queue(favCategoryQueue);
    }

}
