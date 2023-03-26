package com.brokis.Banco.servicio.Usuario;

import com.brokis.Banco.modelo.Cuenta;
import com.brokis.Banco.modelo.Usuario;

import java.util.List;
import java.util.Optional;

public interface ServicioUsuario {
    Usuario crearUsuario(Usuario usuario);
    List<Cuenta> consultarCuentas(Long id);
}
