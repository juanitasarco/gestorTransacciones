package com.brokis.Banco.controlador;

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
    @PostMapping("/crear")
    public ResponseEntity crearCuenta(@RequestBody Cuenta Cuenta) {
        return new ResponseEntity(servicioCuenta.crearCuenta(Cuenta), HttpStatus.CREATED);
    }
    @GetMapping("/consultar/{id}")
    public ResponseEntity consultarSaldo(@PathVariable Long id) {
        return new ResponseEntity(servicioCuenta.consultarSaldo(id), HttpStatus.OK);
    }
    @PutMapping("/depositar/{idi}/{monto}")
    public ResponseEntity realizarDeposito(@PathVariable Long id, @PathVariable int monto) {
        return new ResponseEntity(servicioCuenta.depositarCuenta(id, monto), HttpStatus.OK);
    }
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity eliminarCuenta(@PathVariable Long id){
        return new ResponseEntity(servicioCuenta.eliminarCuenta(id), HttpStatus.OK);
    }
}
