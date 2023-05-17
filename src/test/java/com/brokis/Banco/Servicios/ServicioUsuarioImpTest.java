package com.brokis.Banco.Servicios;
import static org.mockito.BDDMockito.given;

import com.brokis.Banco.modelo.Cuenta;
import com.brokis.Banco.modelo.Usuario;
import com.brokis.Banco.repositorio.RepCuenta;
import com.brokis.Banco.repositorio.RepUsuario;
import com.brokis.Banco.servicio.Usuario.ServicioUsuarioImp;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@ExtendWith(MockitoExtension.class)
public class ServicioUsuarioImpTest {
    @Mock
    private RepUsuario repUsuario;
    @Mock
    private RepCuenta repCuenta;
    @InjectMocks
    private ServicioUsuarioImp servicioUsuario;
    @DisplayName("Test para crear un usuario")
    @Test
    void Given_userOk_When_crearUsuario_Then_return_usuario() {
        UsuarioDTO usuarioDTO = new UsuarioDTO(123456789L, "Juan", "Parrado");
        Usuario usuario = new Usuario(usuarioDTO.getDocument(), usuarioDTO.getName(), usuarioDTO.getLastName(), null);
        given(repUsuario.save(Mockito.any(Usuario.class))).willReturn(usuario);

        Usuario usuarioGuardado = servicioUsuario.crearUsuario(usuarioDTO);
        Assertions.assertEquals(123456789L, usuarioGuardado.getDocumento());
        Mockito.verify(repUsuario).save(Mockito.any(Usuario.class));
    }
    @DisplayName("Test para consultar cuentas de un usuario")
    @Test
    void Given_userOk_When_consultarCuentas_Then_return_listaCuentas() {
        UsuarioDTO usuarioDTO = new UsuarioDTO(12345L, "Juan", "Parrado");
        Usuario usuarioSolicitado = new Usuario(usuarioDTO.getDocument(), usuarioDTO.getName(), usuarioDTO.getLastName(), new Date());
        List<Cuenta> listaCuentas = new ArrayList<>();
        listaCuentas.add(new Cuenta(1L, "Ahorro", 100, new Date(), usuarioSolicitado));
        listaCuentas.add(new Cuenta(2L, "Corriente", 100, new Date(), usuarioSolicitado));
        listaCuentas.add(new Cuenta(3L, "Ahorro", 100, new Date(), usuarioSolicitado));
        Mockito.when(repCuenta.findAll()).thenReturn(listaCuentas);
        List<Cuenta> listaCuentasSolicitadas = servicioUsuario.consultarCuentas(usuarioDTO);
        Assertions.assertEquals(listaCuentas, listaCuentasSolicitadas);
    }
}


