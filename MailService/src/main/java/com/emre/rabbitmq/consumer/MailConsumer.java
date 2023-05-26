package com.emre.rabbitmq.consumer;

import com.emre.rabbitmq.model.FavCategoriesSendMailModel;
import com.emre.rabbitmq.model.RegisterMailModel;
import com.emre.service.MailService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MailConsumer {
    private final MailService mailService;

    @RabbitListener(queues = ("${rabbitmq.mailQueue}"))
    public void sendActivationCode(RegisterMailModel model) {
        mailService.createMail(model);
    }

    @RabbitListener(queues = ("${rabbitmq.favCategoryQueue}"))
    public void sendFavCategory(FavCategoriesSendMailModel model) {
        mailService.createFavCategoryMail(model);
    }
}
