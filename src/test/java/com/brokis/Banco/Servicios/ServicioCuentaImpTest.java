package com.brokis.Banco.Servicios;
import com.brokis.Banco.controlador.dto.CuentaDTO;
import com.brokis.Banco.controlador.dto.IdCuentaDTO;
import com.brokis.Banco.modelo.Cuenta;
import com.brokis.Banco.modelo.Usuario;
import com.brokis.Banco.repositorio.RepCuenta;
import com.brokis.Banco.repositorio.RepUsuario;
import com.brokis.Banco.servicio.Cuenta.ServicioCuentaImp;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.when;
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
        when(repUsuario.findById(cuentaDTO.getDocumentoUsuario())).thenReturn(Optional.empty());
        assertThatThrownBy(() -> servicioCuenta.crearCuenta(cuentaDTO))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("Usuario no encontrado");

    }
    @Test
    void Given_user_Existent_When_crearCuenta_Then_Return_Saved_Cuenta() {
        CuentaDTO cuentaDTO = new CuentaDTO("Ahorros", 12345L);
        when(repUsuario.findById(cuentaDTO.getDocumentoUsuario())).thenReturn(Optional.of(
                new Usuario(cuentaDTO.getDocumentoUsuario(),"Juan","Parrado",null)));
        Usuario usuarioBuscado = repUsuario.findById(cuentaDTO.getDocumentoUsuario()).get();
        Cuenta cuentaNueva = new Cuenta(1L,cuentaDTO.getTipo(),0,null,usuarioBuscado);
        List<Cuenta> byUsuario = new ArrayList<>();
        byUsuario.add(new Cuenta(2L,"Ahorros",0,null,usuarioBuscado));
        byUsuario.add(cuentaNueva);
        when(repCuenta.findByUsuario(usuarioBuscado)).thenReturn(byUsuario);
        Cuenta result = servicioCuenta.crearCuenta(cuentaDTO);
        Assertions.assertNull(result);
    }
    @Test
    void Given_user_With_More_than_3Accounts_When_crearCuenta_Then_TrowRuntime() {
        CuentaDTO cuentaDTO = new CuentaDTO("Ahorros", 123456789L);
        when(repUsuario.findById(cuentaDTO.getDocumentoUsuario())).thenReturn(Optional.of(
                new Usuario(cuentaDTO.getDocumentoUsuario(),"Juan","Parrado",null)));
        Cuenta cuentaNueva = new Cuenta(1L,cuentaDTO.getTipo(),0,null,new Usuario(cuentaDTO.getDocumentoUsuario(),"Juan","Parrado",null));
        Usuario usuario = new Usuario(cuentaDTO.getDocumentoUsuario(),"Juan","Parrado",null);
        List<Cuenta> byUsuario = new ArrayList<>();
        byUsuario.add(new Cuenta(1L,"Ahorros",0,null,usuario));
        byUsuario.add(new Cuenta(2L,"Corriente",0,null,usuario));
        byUsuario.add(new Cuenta(3L,"Ahorros",0,null,usuario));
        byUsuario.add(new Cuenta(4L,"Corriente",0,null,usuario));
        byUsuario.add(cuentaNueva);
        when(repCuenta.findByUsuario(usuario)).thenReturn(byUsuario);
        assertThatThrownBy(() -> servicioCuenta.crearCuenta(cuentaDTO))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("El usuario ya tiene 3 cuentas");
    }
    @Test
    void Given_nonExistent_Account_When_consultarSaldo_Then_TrowIllegalArgument() {
        IdCuentaDTO idCuentaDTO = new IdCuentaDTO(1L,0);
        when(repCuenta.findById(idCuentaDTO.getId())).thenReturn(Optional.empty());
        assertThatThrownBy(() -> servicioCuenta.consultarSaldo(idCuentaDTO))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("Cuenta no encontrada");
    }
    @Test
    void Given_AccountOk_When_consultarSaldo_Then_return_null() {
        IdCuentaDTO idCuentaDTO = new IdCuentaDTO(1L,0);
        when(repCuenta.findById(idCuentaDTO.getId())).thenReturn(Optional.of(new Cuenta(1L,"Ahorros",0,null,null)));
        Cuenta result = servicioCuenta.consultarSaldo(idCuentaDTO);
        Assertions.assertEquals(result.getId(),idCuentaDTO.getId());
    }
    @Test
    void Given_nonExistent_Account_When_depositarCuenta_Then_TrowIllegalArgument() {
        IdCuentaDTO idCuentaDTO = new IdCuentaDTO(1L,100);
        when(repCuenta.findById(idCuentaDTO.getId())).thenReturn(Optional.empty());
        assertThatThrownBy(() -> servicioCuenta.depositarCuenta(idCuentaDTO))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("Cuenta no encontrada");
    }
    @Test
    void Given_AccountOk_When_depositarCuenta_Then_return_Account() {
        IdCuentaDTO idCuentaDTO = new IdCuentaDTO(1L,100);
        when(repCuenta.findById(idCuentaDTO.getId())).thenReturn(Optional.of(new Cuenta(1L,"Ahorros",100,null,null)));
        Cuenta cuentaBuscada = repCuenta.findById(idCuentaDTO.getId()).get();
        cuentaBuscada.setSaldo(cuentaBuscada.getSaldo()+idCuentaDTO.getMonto());
        int result = cuentaBuscada.getSaldo();
        Assertions.assertEquals(200,result);
    }
    @Test
    void Given_nonExistent_Account_When_eliminarCuenta_Then_TrowIllegalArgument() {
        IdCuentaDTO idCuentaDTO = new IdCuentaDTO(1L,0);
        when(repCuenta.findById(idCuentaDTO.getId())).thenReturn(Optional.empty());
        assertThatThrownBy(() -> servicioCuenta.eliminarCuenta(idCuentaDTO))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("Cuenta no encontrada");
        }
    @Test
    void Given_AccountOk_When_eliminarCuenta_Then_return_null() {
        IdCuentaDTO idCuentaDTO = new IdCuentaDTO(1L,0);
        when(repCuenta.findById(idCuentaDTO.getId())).thenReturn(Optional.of(new Cuenta(1L,"Ahorros",0,null,null)));
        Cuenta result = servicioCuenta.eliminarCuenta(idCuentaDTO);
        Assertions.assertNull(result);
    }
    }















