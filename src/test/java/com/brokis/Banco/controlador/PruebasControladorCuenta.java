package com.brokis.Banco.controlador;

import com.brokis.Banco.AbstractTest;
import com.brokis.Banco.controlador.dto.CuentaDTO;
import com.brokis.Banco.controlador.dto.UsuarioDTO;
import com.brokis.Banco.modelo.Cuenta;
import com.brokis.Banco.modelo.Usuario;
import com.brokis.Banco.repositorio.RepUsuario;
import com.brokis.Banco.servicio.Usuario.ServicioUsuarioImp;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import com.brokis.Banco.servicio.Usuario.ServicioUsuario;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import java.net.http.HttpResponse;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class PruebasControladorCuenta extends AbstractTest {

    @Autowired
    private TestRestTemplate restTemplate;
    private static final String PATH_CUENTA_CREACION = "/cuenta/creacion";
    @Mock
    private RepUsuario repUsuario;

    @InjectMocks
    private ServicioUsuarioImp servicioUsuario;


    @Test
    public void Given_cuentaDTO_When_invoke_crearCuenta_Then_return_newCuenta() {

        CuentaDTO dto = new CuentaDTO("Ahorros",0);
        Usuario usuario = new Usuario(0L,"Juan","Parrado",null);
        UsuarioDTO usuarioDTO = new UsuarioDTO(0L,"Juan","Parrado");


        repUsuario.save(usuario);
        //servicioUsuario.crearUsuario(usuarioDTO);
        Mockito.when(repUsuario.save(usuario)).thenReturn(usuario);


        ResponseEntity<Cuenta> responseEntity = restTemplate.postForEntity(PATH_CUENTA_CREACION,dto, Cuenta.class);


        HttpStatusCode status = responseEntity.getStatusCode();

        assertEquals(HttpStatusCode.valueOf(201),status);


    }

    @Test
    public void Given_cuentaDTO_When_invoke_crearCuenta_Then_return_usuarioNoEncontrado(){
        CuentaDTO dto = new CuentaDTO("Ahorros",0);

        ResponseEntity<Cuenta> responseEntity = restTemplate.postForEntity(PATH_CUENTA_CREACION,dto, Cuenta.class);

        HttpStatusCode status = responseEntity.getStatusCode();

        assertEquals(HttpStatusCode.valueOf(500),status);
    }

}
