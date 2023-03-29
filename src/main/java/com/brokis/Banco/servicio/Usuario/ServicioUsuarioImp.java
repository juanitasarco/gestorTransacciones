package com.brokis.Banco.servicio.Usuario;

import com.brokis.Banco.controlador.dto.UsuarioDTO;
import com.brokis.Banco.modelo.Cuenta;
import com.brokis.Banco.modelo.Usuario;
import com.brokis.Banco.repositorio.RepCuenta;
import com.brokis.Banco.repositorio.RepUsuario;
import java.util.Date;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ServicioUsuarioImp implements ServicioUsuario {
    private final RepUsuario repUsuario;
    private final RepCuenta repCuenta;

    @Override
    public Usuario crearUsuario(UsuarioDTO usuarioDTO) {
        Usuario usuario = new Usuario();
        usuario.setDOCUMENT(usuarioDTO.getDocument());
        usuario.setLAST_NAME(usuarioDTO.getLastName());
        usuario.setNAME(usuarioDTO.getName());
        usuario.setDATE_CREATED(new Date());

        return repUsuario.save(usuario);
    }

    @Override
    public List<Cuenta> consultarCuentas(Long id) {
        //TODO cambiar esto
        return null;
    }
}
