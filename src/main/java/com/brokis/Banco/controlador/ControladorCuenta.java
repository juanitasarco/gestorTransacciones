package com.brokis.Banco.controlador;

import com.brokis.Banco.controlador.dto.CuentaDTO;
import com.brokis.Banco.controlador.dto.IdCuentaDTO;
import com.brokis.Banco.modelo.*;
import com.brokis.Banco.servicio.Cuenta.ServicioCuenta;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cuenta")
@AllArgsConstructor
public class ControladorCuenta {
    private final ServicioCuenta servicioCuenta;
    @PostMapping("/creacion")
    public ResponseEntity crearCuenta(@RequestBody CuentaDTO cuentaDTO) {
        return new ResponseEntity(servicioCuenta.crearCuenta(cuentaDTO), HttpStatus.CREATED);
    }
    @GetMapping("/consulta/{id}")
    public ResponseEntity consultarSaldo(@PathVariable Long id) {
        IdCuentaDTO idCuentaDTO = new IdCuentaDTO();
        idCuentaDTO.setId(id);
        return new ResponseEntity(servicioCuenta.consultarSaldo(idCuentaDTO), HttpStatus.OK);
    }
    @PutMapping("/deposito/{id}/{monto}")
    public ResponseEntity realizarDeposito(@PathVariable Long id, @PathVariable int monto) {
        IdCuentaDTO idCuentaDTO = new IdCuentaDTO();
        idCuentaDTO.setId(id);
        idCuentaDTO.setMonto(monto);
        return new ResponseEntity(servicioCuenta.depositarCuenta(idCuentaDTO), HttpStatus.OK);
    }
    @DeleteMapping("/eliminacion/{id}")
    public ResponseEntity eliminarCuenta(@PathVariable Long id){
        IdCuentaDTO idCuentaDTO = new IdCuentaDTO();
        idCuentaDTO.setId(id);
        return new ResponseEntity(servicioCuenta.eliminarCuenta(idCuentaDTO), HttpStatus.OK);
    }
}
