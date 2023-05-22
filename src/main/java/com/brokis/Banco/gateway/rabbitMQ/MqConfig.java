package com.brokis.Banco.gateway.rabbitMQ;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MqConfig {
    @Value("transferencia")
    private String jsonQueue;

    @Value("adminTransferencia")
    private String exchange;

    @Value("transferir")
    private String routingJsonKey;
    @Bean
    public Queue jsonQueue(){
        return new Queue(jsonQueue);
    }
    @Bean
    public FanoutExchange exchange(){
        return new FanoutExchange(exchange);
    }
    @Bean
    public Binding jsonBinding(){
        return BindingBuilder
                .bind(jsonQueue())
                .to(exchange());
    }
    @Bean
    public MessageConverter converter(){
        return new Jackson2JsonMessageConverter();
    }
    @Bean
    public AmqpTemplate amqpTemplate(ConnectionFactory connectionFactory){
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(converter());
        return rabbitTemplate;
    }
}
