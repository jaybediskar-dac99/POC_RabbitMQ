package com.example.rabbitMqProject.consumer;

import com.example.rabbitMqProject.dto.User;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service

public class RabbitJsonConsumer {

    private static final Logger log = LoggerFactory.getLogger(RabbitJsonConsumer.class);

    @RabbitListener(queues = {"${rabbitmq.jsonQueue.name}"})
    public void consume(User Message){
        log.info("Message Received in Consumer class : {} ",Message);
    }
}
