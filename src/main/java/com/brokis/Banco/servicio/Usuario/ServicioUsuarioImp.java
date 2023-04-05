package com.brokis.Banco.servicio.Usuario;

import com.brokis.Banco.controlador.dto.UsuarioDTO;
import com.brokis.Banco.modelo.Cuenta;
import com.brokis.Banco.modelo.Usuario;
import com.brokis.Banco.repositorio.RepCuenta;
import com.brokis.Banco.repositorio.RepUsuario;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class ServicioUsuarioImp implements ServicioUsuario {
    private final RepUsuario repUsuario;
    private final RepCuenta repCuenta;
    @Override
    public Usuario crearUsuario(UsuarioDTO usuarioDTO) {
        Usuario usuario = new Usuario();
        usuario.setDocumento(usuarioDTO.getDocument());
        usuario.setApellido(usuarioDTO.getLastName());
        usuario.setNombre(usuarioDTO.getName());
        usuario.setFechaDeCreacion(new Date());
        return repUsuario.save(usuario);
    }
    @Override
    public List<Cuenta> consultarCuentas(UsuarioDTO usuarioDTO) {
        Long idUsuario = usuarioDTO.getDocument();
        List<Cuenta> listaCuentas = new ArrayList<>();
        for (Cuenta cuenta : repCuenta.findAll()) {
            if (cuenta.getUsuario().getDocumento().equals(idUsuario) ) {
                listaCuentas.add(cuenta);
            }
        }
        return listaCuentas;
    }
}
