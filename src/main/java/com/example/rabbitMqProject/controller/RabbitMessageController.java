package com.example.rabbitMqProject.controller;

import com.example.rabbitMqProject.dto.User;
import com.example.rabbitMqProject.producer.RabbitFirstProducer;
import com.example.rabbitMqProject.producer.RabbitJsonProducer;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@Slf4j
public class RabbitMessageController {

    private static final Logger log = LoggerFactory.getLogger(RabbitMessageController.class);

    private RabbitFirstProducer producer;

    @Autowired
    private RabbitJsonProducer jsonProducer;

    public RabbitMessageController(RabbitFirstProducer producer){
        this.producer=producer;
    }

    @GetMapping("/publish")
    public ResponseEntity<String> sendMessage(@RequestParam("message") String message){
        log.info("Message inside controller : {}",message);
        producer.sendMessage(message);
        return ResponseEntity.ok("message sent in Queue "+message);
    }

    @PostMapping("/publish/user")
    public ResponseEntity<String> sendJsonMessage(@RequestBody User user){
        log.info("Message inside Controller : {}",user);
        jsonProducer.sendJsonMessage(user);
        return ResponseEntity.ok("Json Produced");
    }

}
