package com.brokis.Banco.gateway;

import com.brokis.Banco.AbstractTest;
import com.brokis.Banco.gateway.dto.TransaccionDTO;
import com.brokis.Banco.gateway.rabbitMQ.ListenerRabbit;
import com.brokis.Banco.servicio.ServicioTransaccion;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.Mock;
import org.mockito.Mockito;

@ExtendWith(MockitoExtension.class)
public class RabbitmqLsitenerTest extends AbstractTest {
    @Mock
    private ServicioTransaccion servicioTransaccion;

    private ListenerRabbit listenerRabbit;

    @Test
    public void testRecibirTransaccion() {
        // Arrange
        TransaccionDTO transaccionDTO = new TransaccionDTO();
        Mockito.when(servicioTransaccion.hacerTransferencia(transaccionDTO)).thenReturn("La Transferencia ha sido exitosa");

        // Act
        listenerRabbit = new ListenerRabbit(servicioTransaccion);
        listenerRabbit.recibirTransaccion(transaccionDTO);

        // Assert
        Mockito.verify(servicioTransaccion).hacerTransferencia(transaccionDTO);
    }

}

