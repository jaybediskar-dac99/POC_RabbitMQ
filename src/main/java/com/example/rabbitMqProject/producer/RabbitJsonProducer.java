package com.example.rabbitMqProject.producer;

import com.example.rabbitMqProject.dto.User;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RabbitJsonProducer {


    private static final Logger log = LoggerFactory.getLogger(RabbitJsonProducer.class);
    @Value("${rabbitmq.jsonExchange.name}")
    private String jsonExchange;

    @Value("${rabbitmq.jsonRoutingKey.name}")
    private String jsonRoutingKey;

    @Autowired
    private RabbitTemplate rabbitTemplate;


    public void sendJsonMessage(User user){
        log.info("Json Object Produced : {} ",user);
        MessageProperties properties = new MessageProperties();
        properties.setDeliveryMode(MessageDeliveryMode.PERSISTENT);
        rabbitTemplate.convertAndSend(jsonExchange,jsonRoutingKey,user);
    }
}
