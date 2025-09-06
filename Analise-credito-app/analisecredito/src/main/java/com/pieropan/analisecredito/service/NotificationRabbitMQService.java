package com.pieropan.analisecredito.service;

import com.pieropan.analisecredito.domain.Proposal;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationRabbitMQService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void toNotify(String exchange, Proposal proposal){
        rabbitTemplate.convertAndSend(exchange, "", proposal);
    }
}
