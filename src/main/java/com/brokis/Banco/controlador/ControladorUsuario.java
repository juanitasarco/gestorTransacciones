package com.brokis.Banco.controlador;

import com.brokis.Banco.controlador.dto.UsuarioDTO;
import com.brokis.Banco.servicio.Usuario.ServicioUsuario;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")
@AllArgsConstructor
public class ControladorUsuario {
    private final ServicioUsuario servicioUsuario;

    @PostMapping("/crear")
    public ResponseEntity crearCuenta(@RequestBody UsuarioDTO usuario) {
        return new ResponseEntity(servicioUsuario.crearUsuario(usuario), HttpStatus.CREATED);
    }
    @GetMapping("/consulta/{id}")
    public ResponseEntity consultarCuentas(@PathVariable Long id) {
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setDocument(id);
        return new ResponseEntity(servicioUsuario.consultarCuentas(usuarioDTO), HttpStatus.OK);
    }
}
