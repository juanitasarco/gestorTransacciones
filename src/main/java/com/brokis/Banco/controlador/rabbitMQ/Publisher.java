package com.brokis.Banco.controlador.rabbitMQ;
import com.brokis.Banco.controlador.dto.TransaccionDTO;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class Publisher {
    @Value("adminTransferencia")
    private String exchange;
    @Value("transferir")
    private String routingJsonKey;
    private static final Logger LOGGER = LoggerFactory.getLogger(Publisher.class);
    private RabbitTemplate rabbitTemplate;
    public Publisher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }
    public void sendJsonMessage(TransaccionDTO transaccionDTO){
        LOGGER.info(String.format("Json message sent -> %s",transaccionDTO.toString()));
        rabbitTemplate.convertAndSend(exchange,routingJsonKey,transaccionDTO);
    }
}
