package com.brokis.Banco.controlador;

import com.brokis.Banco.controlador.dto.TransaccionDTO;
import com.brokis.Banco.modelo.Transaccion;
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
    @PutMapping  ("/transferencia")
    public ResponseEntity transferir(@RequestBody TransaccionDTO transaccionDTO) {

        String resultadoTransferencia = servicioTransaccion.hacerTransferencia(transaccionDTO);


        if ("La Transferencia ha sido exitosa".equals(resultadoTransferencia)) {
            return ResponseEntity.ok(resultadoTransferencia);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("La transferencia no se pudo realizar");
        }

    }
}
