package com.example.rabbitMqProject.consumer;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class RabbitFirstConsumer {

    private static final Logger log = LoggerFactory.getLogger(RabbitFirstConsumer.class);
    /*@Value("${rabbitmq.queue.name}")
    private String queue;*/

    @RabbitListener(queues = {"${rabbitmq.queue.name}"})
    public void consume(String Message){
        log.info("Message Received in Consumer class : {} ",Message);
    }
}
