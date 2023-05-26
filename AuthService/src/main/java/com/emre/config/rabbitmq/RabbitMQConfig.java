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
    @Value("${rabbitmq.exchange-auth}")
    String exchange;
    @Value("${rabbitmq.mailQueue}")
    String mailQueue;
    @Value("${rabbitmq.mailBindingKey}")
    String mailBindingKey;

    @Bean
    Queue mailQueue() {
        return new Queue(mailQueue);
    }
    @Bean
    DirectExchange directAuthExchange() {
        return new DirectExchange(exchange);
    }
    @Bean
    public Binding bindingRegisterMail(final Queue mailQueue,final DirectExchange directAuthExchange) {
        return BindingBuilder.bind(mailQueue).to(directAuthExchange).with(mailBindingKey);
    }

}
