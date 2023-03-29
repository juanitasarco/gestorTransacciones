package com.brokis.Banco.servicio.Usuario;

import com.brokis.Banco.controlador.dto.UsuarioDTO;
import com.brokis.Banco.modelo.Cuenta;
import com.brokis.Banco.modelo.Usuario;

import java.util.List;
import java.util.Optional;

public interface ServicioUsuario {
    Usuario crearUsuario(UsuarioDTO usuario);
    List<Cuenta> consultarCuentas(Long id);
}
