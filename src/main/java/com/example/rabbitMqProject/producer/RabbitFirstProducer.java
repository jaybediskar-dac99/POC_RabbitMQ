package com.example.rabbitMqProject.producer;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


//Spring will register it as Bean in IOC Container
@Service
@Slf4j
public class RabbitFirstProducer {

    private static final Logger log = LoggerFactory.getLogger(RabbitFirstProducer.class);
    @Value("${rabbitmq.queue.name}")
    private String queue;


    @Value("${rabbitmq.exchange.name}")
    private String exchange;

    @Value("${rabbitmq.routingKey.name}")
    private String routingKey;

    //Use RabbitTemplate to send message
    //RabbitTemplate is autoConfigured in spring Container just use it by injecting

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /*public RabbitFirstProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }*/


    public void sendMessage(String message){
        log.info("Message Produced : {}",message);
        rabbitTemplate.convertAndSend(exchange,routingKey,message);
    }
}
