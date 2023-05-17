package com.brokis.Banco.controlador;

import com.brokis.Banco.controlador.dto.IdCuentaDTO;
import com.brokis.Banco.controlador.dto.TransaccionDTO;
import com.brokis.Banco.controlador.rabbitMQ.Publisher;
import com.brokis.Banco.controlador.rabbitMQ.PublisherDeposito;
import com.brokis.Banco.servicio.Transaccion.ServicioTransaccion;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/servicio")
@AllArgsConstructor
public class ControladorTransaccion {
    private final ServicioTransaccion servicioTransaccion;
    private final Publisher Publisher;
    private final PublisherDeposito publisherDeposito;
    @PutMapping  ("/transferencia")
    public ResponseEntity transferir(@RequestBody TransaccionDTO transaccionDTO) {
        String resultadoTransferencia = servicioTransaccion.hacerTransferencia(transaccionDTO);
        if ("La Transferencia ha sido exitosa".equals(resultadoTransferencia)) {
            return ResponseEntity.ok(resultadoTransferencia);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("La transferencia no se pudo realizar");
        }
    }
    //TODO: cambiar el toString
    @PostMapping("/publish")
    public ResponseEntity<String> envioTransferencia(@RequestBody TransaccionDTO transaccionDTO){
        Publisher.sendJsonMessage(transaccionDTO);
        return ResponseEntity.ok("Transaccion enviada = "+transaccionDTO.toString());
    }
    @PostMapping("/deposito")
    public ResponseEntity<String> envioDeposito(@RequestBody IdCuentaDTO idCuentaDTO){
        publisherDeposito.sendJsonMessage(idCuentaDTO);
        return ResponseEntity.ok("Deposito enviado = "+idCuentaDTO.toString());
    }
}
