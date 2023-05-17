package com.brokis.Banco.Servicios;
import com.brokis.Banco.controlador.dto.TransaccionDTO;
import com.brokis.Banco.modelo.Cuenta;
import com.brokis.Banco.modelo.Transaccion;
import com.brokis.Banco.modelo.Usuario;
import com.brokis.Banco.repositorio.RepCuenta;
import com.brokis.Banco.repositorio.RepTransaccion;
import com.brokis.Banco.servicio.Transaccion.ServicioTransaccionImp;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
public class ServicioTransaccionImpTest {
    @Mock
    private RepTransaccion repTransaccion;
    @Mock
    private RepCuenta repCuenta;
    @InjectMocks
    private ServicioTransaccionImp servicioTransaccion;
    @Test
    void Given_OriginAccount_nonExistent_When_hacerTransaccion_Then_TrowIllegalArgument() {
        TransaccionDTO transaccionDTO = new TransaccionDTO();
        transaccionDTO.setOrigen(123456789L);
        when(repCuenta.findById(transaccionDTO.getOrigen())).thenReturn(Optional.empty());

        assertThatThrownBy(() -> servicioTransaccion.hacerTransferencia(transaccionDTO))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("Cuenta origen no encontrada");
    }
    @Test
    void Given_DestinyAccount_nonExistent_When_hacerTransaccion_Then_TrowIllegalArgument() {
        TransaccionDTO transaccionDTO = new TransaccionDTO();
        transaccionDTO.setOrigen(123456789L);
        when(repCuenta.findById(transaccionDTO.getOrigen())).thenReturn(Optional.of(new Cuenta()));
        transaccionDTO.setDestino(987654321L);
        when(repCuenta.findById(transaccionDTO.getDestino())).thenReturn(Optional.empty());
        assertThatThrownBy(() -> servicioTransaccion.hacerTransferencia(transaccionDTO))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("Cuenta destino no encontrada");
    }
    @Test
    void Given_AccountsOk_With_insufficient_balance_When_hacerTransaccion_Then_TrowRuntime() {
        TransaccionDTO transaccionDTO = new TransaccionDTO();
        transaccionDTO.setMonto(2000);
        transaccionDTO.setOrigen(123456789L);
        when(repCuenta.findById(transaccionDTO.getOrigen())).thenReturn(Optional.of(
                new Cuenta(123456789L, "Ahorros", 1000,null, new Usuario())));
        transaccionDTO.setDestino(987654321L);
        when(repCuenta.findById(transaccionDTO.getDestino())).thenReturn(Optional.of(
                new Cuenta(987654321L, "Ahorros", 1000,null, new Usuario())));
        assertThatThrownBy(() -> servicioTransaccion.hacerTransferencia(transaccionDTO))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("Saldo insuficiente");
    }
    @Test
    void Given_AccountsOk_With_sufficient_balance_When_hacerTransaccion_Then_return_String() {
        TransaccionDTO transaccionDTO = new TransaccionDTO();
        transaccionDTO.setMonto(500);
        transaccionDTO.setOrigen(123456789L);
        when(repCuenta.findById(transaccionDTO.getOrigen())).thenReturn(Optional.of(
                new Cuenta(123456789L, "Ahorros", 1000, null, new Usuario())));
        transaccionDTO.setDestino(987654321L);
        when(repCuenta.findById(transaccionDTO.getDestino())).thenReturn(Optional.of(
                new Cuenta(987654321L, "Ahorros", 1000, null, new Usuario())));

        Mockito.when(repTransaccion.save(Mockito.any(Transaccion.class))).thenReturn(new Transaccion());
        String result = servicioTransaccion.hacerTransferencia(transaccionDTO);
        Assertions.assertEquals("La Transferencia ha sido exitosa", result);
        Mockito.verify(repTransaccion).save(Mockito.any(Transaccion.class));
    }
}


