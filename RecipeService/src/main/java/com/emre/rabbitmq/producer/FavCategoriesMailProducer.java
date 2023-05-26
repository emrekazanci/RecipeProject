package com.emre.rabbitmq.producer;

import com.emre.rabbitmq.model.FavCategoriesSendMailModel;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FavCategoriesMailProducer {
    @Value("${rabbitmq.exchange}")
    String exchange;
    @Value("${rabbitmq.favCategoryBindingKey}")
    String favCategoryBindingKey;

    private final RabbitTemplate rabbitTemplate;

    public void sendMailForFavCategory(FavCategoriesSendMailModel model) {
        rabbitTemplate.convertAndSend(exchange,favCategoryBindingKey,model);
    }

}
