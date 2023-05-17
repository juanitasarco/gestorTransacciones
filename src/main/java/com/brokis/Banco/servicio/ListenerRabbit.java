package com.brokis.Banco.servicio;

import com.brokis.Banco.controlador.dto.TransaccionDTO;
import com.brokis.Banco.servicio.Transaccion.ServicioTransaccion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class ListenerRabbit {
    private final ServicioTransaccion servicioTransaccion;
    private static final Logger LOGGER = LoggerFactory.getLogger(ListenerRabbit.class);
    //TODO: Revisar inyeccion o inversion para mejorar el codigo
    public ListenerRabbit(ServicioTransaccion servicioTransaccion) {
        this.servicioTransaccion = servicioTransaccion;
    }
    @RabbitListener(queues = {"transferencia"})
    public void recibirTransaccion(TransaccionDTO transaccionDTO){
        LOGGER.info(String.format("Received JSON message ->%s",transaccionDTO.toString()));
        servicioTransaccion.hacerTransferencia(transaccionDTO);
    }
}
