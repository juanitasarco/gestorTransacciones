package com.brokis.Banco.controlador;

import com.brokis.Banco.modelo.Cuenta;
import com.brokis.Banco.servicio.CreadorCuenta;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cuenta")
@AllArgsConstructor
public class ControladorCuenta {
    private final CreadorCuenta creadorCuenta;
    @PostMapping("/crear")
    public ResponseEntity crearCuenta(@RequestBody Cuenta Cuenta) {
        return new ResponseEntity(creadorCuenta.crearCuenta(Cuenta), HttpStatus.CREATED);
    }
}
