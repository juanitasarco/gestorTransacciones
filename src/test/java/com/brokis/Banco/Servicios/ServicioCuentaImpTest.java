package com.brokis.Banco.Servicios;

import com.brokis.Banco.controlador.dto.CuentaDTO;
import com.brokis.Banco.controlador.dto.IdCuentaDTO;
import com.brokis.Banco.controlador.dto.UsuarioDTO;
import com.brokis.Banco.modelo.Cuenta;
import com.brokis.Banco.modelo.Usuario;
import com.brokis.Banco.repositorio.RepCuenta;
import com.brokis.Banco.repositorio.RepUsuario;
import com.brokis.Banco.servicio.Cuenta.ServicioCuenta;
import com.brokis.Banco.servicio.Cuenta.ServicioCuentaImp;
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
import java.util.List;
import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
public class ServicioCuentaImpTest {
    @Mock
    private RepCuenta repCuenta;
    @Mock
    private RepUsuario repUsuario;
    @InjectMocks
    private ServicioCuentaImp servicioCuenta;
    @Test
    void Given_user_nonExistent_When_crearCuenta_Then_TrowIllegalArgument() {
        CuentaDTO cuentaDTO = new CuentaDTO("Ahorros", 123456789L);
        Assertions.assertThrows(NullPointerException.class, () -> {
            servicioCuenta.crearCuenta(cuentaDTO);
        });
    }
    @DisplayName("Test para crear una cuenta con un usuario que existe")
    @Test
    void Given_user_Existent_When_crearCuenta_Then_Return_Saved_Cuenta() {
        CuentaDTO cuentaDTO = new CuentaDTO("Ahorros", 123456789L);
        Usuario usuario = new Usuario(cuentaDTO.getDocumentoUsuario(),null,null,null);
        Cuenta cuenta = new Cuenta(null,cuentaDTO.getTipo(),0,null,usuario);
        List<Cuenta> byUsuario = new ArrayList<>();
        byUsuario.add(cuenta);


        Mockito.<Optional<Usuario>>when(repUsuario.findById(cuenta.getUsuario().getDocumento())).thenReturn(Optional.of(usuario));
        Mockito.when(repCuenta.save(cuenta)).thenReturn(cuenta);
        Assertions.assertEquals(cuentaDTO.getTipo(), cuenta.getTipo());
        Mockito.verify(repUsuario).findById(cuentaDTO.getDocumentoUsuario());
        Mockito.verify(repCuenta).findByUsuario(cuenta.getUsuario());
        Mockito.verify(repCuenta).save(cuenta);

        /*
        Mockito.when(repUsuario.findById(cuentaDTO.getDocumentoUsuario())).thenReturn(Optional.of(new Usuario()));
        Mockito.when(repCuenta.findByUsuario(new Usuario())).thenReturn(null);
        Mockito.when(repCuenta.save(new Cuenta())).thenReturn(new Cuenta());
        Assertions.assertEquals(cuentaDTO.getTipo(), cuenta.getTipo());

        Mockito.verify(repUsuario).findById(cuentaDTO.getDocumentoUsuario());
        Mockito.verify(repCuenta).findByUsuario(new Usuario());
        Mockito.verify(repCuenta).save(new Cuenta());

         */



    }
    @DisplayName("Test para consultar cuentas de un usuario")
    @Test
    void Given_userOk_When_consultarSaldo_Then_return_cuenta() {

    }












}


