package com.brokis.Banco.controlador.rabbitMQ;
import com.brokis.Banco.controlador.dto.IdCuentaDTO;
import com.brokis.Banco.controlador.dto.TransaccionDTO;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PublisherDeposito {

    @Value("adminDeposito")
    private String exchange;

    @Value("depositar")
    private String routingJsonKey;

    private static final Logger LOGGER = LoggerFactory.getLogger(PublisherDeposito.class);

    private RabbitTemplate rabbitTemplate;

    public PublisherDeposito(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendJsonMessage(IdCuentaDTO idCuentaDTO){
        LOGGER.info(String.format("Json message sent -> %s",idCuentaDTO.toString()));
        rabbitTemplate.convertAndSend(exchange,routingJsonKey,idCuentaDTO);
    }
}
