package com.brokis.Banco.controlador;

import com.brokis.Banco.controlador.dto.UsuarioDTO;
import com.brokis.Banco.modelo.Cuenta;
import com.brokis.Banco.modelo.Usuario;
import com.brokis.Banco.servicio.Cuenta.ServicioCuenta;
import com.brokis.Banco.servicio.Usuario.ServicioUsuario;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuario")
@AllArgsConstructor
public class ControladorUsuario {
    private final ServicioUsuario servicioUsuario;
    @PostMapping("/crear")
    public ResponseEntity crearCuenta(@RequestBody UsuarioDTO usuario) {
        return new ResponseEntity(servicioUsuario.crearUsuario(usuario), HttpStatus.CREATED);
    }
    @GetMapping("/consultarCuentas/{id}")
    public List<Cuenta> consultarCuentas(@PathVariable Long id) {
        return servicioUsuario.consultarCuentas(id);
    }
}
