package com.emre.rabbitmq.producer;

import com.emre.rabbitmq.model.RegisterMailModel;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MailProducer {
    @Value("${rabbitmq.exchange-auth}")
    private String directAuthExchange;
    @Value("${rabbitmq.mailBindingKey}")
    private String mailBindingKey;

    private final RabbitTemplate rabbitTemplate;

    public void sendActivationCode(RegisterMailModel registerMailModel) {
        rabbitTemplate.convertAndSend(directAuthExchange,mailBindingKey,registerMailModel);
    }
}
