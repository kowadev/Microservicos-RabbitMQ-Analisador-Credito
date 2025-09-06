package com.pieropan.propostaapp.service;


import com.pieropan.propostaapp.entity.Proposal;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class NotificationRabbitService {
    private final RabbitTemplate rabbitTemplate;

    public NotificationRabbitService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void notify(Proposal entity, String exchange){
        rabbitTemplate.convertAndSend(exchange, "", entity);
    }
}
