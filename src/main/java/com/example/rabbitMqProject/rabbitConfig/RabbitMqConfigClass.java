package com.example.rabbitMqProject.rabbitConfig;


import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfigClass {

    @Value("${rabbitmq.queue.name}")
    private String queue;


    @Value("${rabbitmq.exchange.name}")
    private String exchange;

    @Value("${rabbitmq.routingKey.name}")
    private String routingKey;



    @Value("${rabbitmq.jsonQueue.name}")
    private String jsonQueue;


    @Value("${rabbitmq.jsonExchange.name}")
    private String jsonExchange;

    @Value("${rabbitmq.jsonRoutingKey.name}")
    private String jsonRoutingKey;




    //Spring Bean for rabbitMq Queue............
    @Bean
    public Queue queue(){
        return new Queue(queue);
    }

    @Bean
    public TopicExchange exchange(){
        return new TopicExchange(exchange);
    }


    //Binding our Queue with Exchange using routingKey
    @Bean
    public Binding binding(){
        return BindingBuilder.bind(queue())
                .to(exchange()).with(routingKey);
    }

    //ConnectionFactory
    //rabbitTemplate
    //rabbitAdmin
    //All 3 infra related beans springBoot AutoConfig we just need to inject them to use.........


    //...............................
    //Creating Queue for Json Messages like our instance of Classes
    @Bean
    public Queue jsonQueue(){
        return new Queue(jsonQueue);
    }

    @Bean
    public TopicExchange jsonExchange(){
        return new TopicExchange(jsonExchange);
    }

    @Bean
    public Binding jsonBinding(){
        return BindingBuilder.bind(jsonQueue())
                .to(jsonExchange()).with(jsonRoutingKey);
    }

    @Bean
    public MessageConverter jsonConvertor(){
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate amqpTemplate(ConnectionFactory connectionFactory){
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jsonConvertor());
        return rabbitTemplate;
    }
}
