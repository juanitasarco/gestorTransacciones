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
import jakarta.xml.bind.SchemaOutputResolver;
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
        Assertions.assertThrows(RuntimeException.class, () -> {
            servicioCuenta.crearCuenta(cuentaDTO);
        });
    }
    @DisplayName("Test para crear una cuenta con un usuario que existe")
    @Test
    void Given_user_Existent_When_crearCuenta_Then_Return_Saved_Cuenta() {
        CuentaDTO cuentaDTO = new CuentaDTO("Ahorros", 123456789L);
        Usuario usuario = new Usuario(cuentaDTO.getDocumentoUsuario(),"Juan","Parrado",null);
        Cuenta cuenta = new Cuenta(1L,cuentaDTO.getTipo(),0,null,usuario);
        List<Cuenta> byUsuario = new ArrayList<>();
        byUsuario.add(cuenta);

        given(repUsuario.findById(cuentaDTO.getDocumentoUsuario())).willReturn(Optional.of(usuario));
        given(repCuenta.findByUsuario(usuario)).willReturn(byUsuario);
        Cuenta cuentaGuardada = servicioCuenta.crearCuenta(cuentaDTO);
        cuentaGuardada= cuenta;
        Assertions.assertEquals(cuentaDTO.getTipo(), cuentaGuardada.getTipo());
        Assertions.assertEquals(cuentaDTO.getDocumentoUsuario(), cuenta.getUsuario().getDocumento());
        Mockito.verify(repCuenta).save(Mockito.any(Cuenta.class));

    }
    @DisplayName("Test para consultar cuentas de un usuario")
    @Test
    void Given_user_With_More_than_3Accounts_When_crearCuenta_Then_TrowRuntime() {
        CuentaDTO cuentaDTO = new CuentaDTO("Ahorros", 123456789L);
        Usuario usuario = new Usuario(cuentaDTO.getDocumentoUsuario(),"Juan","Parrado",null);
        Cuenta cuenta = new Cuenta(5L,cuentaDTO.getTipo(),0,null,usuario);
        List<Cuenta> byUsuario = new ArrayList<>();
        byUsuario.add(new Cuenta(1L,"Ahorros",0,null,usuario));
        byUsuario.add(new Cuenta(2L,"Corriente",0,null,usuario));
        byUsuario.add(new Cuenta(3L,"Ahorros",0,null,usuario));
        byUsuario.add(new Cuenta(4L,"Corriente",0,null,usuario));
        byUsuario.add(cuenta);
        Assertions.assertThrows(RuntimeException.class, () -> {
            servicioCuenta.crearCuenta(cuentaDTO);
        });
    }













}


