package com.brokis.Banco.servicio.Usuario;

import com.brokis.Banco.modelo.Cuenta;
import com.brokis.Banco.modelo.Usuario;
import com.brokis.Banco.repositorio.RepCuenta;
import com.brokis.Banco.repositorio.RepUsuario;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ServicioUsuarioImp implements ServicioUsuario {
    private final RepUsuario repUsuario;
    private final RepCuenta repCuenta;

    @Override
    public Usuario crearUsuario(Usuario usuario) {
        return repUsuario.save(usuario);
    }

    @Override
    public List<Cuenta> consultarCuentas(Long id) {
        return repCuenta.findByUsuario(id);
    }
}
