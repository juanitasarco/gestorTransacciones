package com.brokis.Banco.servicio.Usuario;

import com.brokis.Banco.modelo.Usuario;
import com.brokis.Banco.repositorio.RepUsuario;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ServicioUsuarioImp implements ServicioUsuario {
    private final RepUsuario repUsuario;

    @Override
    public Usuario crearUsuario(Usuario usuario) {
        return repUsuario.save(usuario);
    }
}
