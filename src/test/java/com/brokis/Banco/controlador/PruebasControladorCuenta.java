package com.brokis.Banco.controlador;

import com.brokis.Banco.AbstractTest;
import com.brokis.Banco.modelo.Cuenta;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class PruebasControladorCuenta extends AbstractTest {

    @Autowired
    private TestRestTemplate restTemplate;
    private static final String pathCuentaCreacion = "/cuenta/creacion";
    private static final String pathCuentaConsulta = "/cuenta/consulta/2";
    private static final String pathCuentaDesposito = "/cuenta/deposito/2/1000";

    private static final  String pathCuentaEliminar = "/cuenta/eliminacion/1";


    @Test
    @Sql(statements = "INSERT INTO USER (DOCUMENT,NAME,LAST_NAME,DATE_CREATED)VALUES (17,'JUAN','PARRADO','2023-03-23')")
    public void Given_cuentaDTO_When_invoke_crearCuenta_Then_return_newCuenta() {

        CuentaDTO dto = new CuentaDTO("Ahorros", 17);

        ResponseEntity<Cuenta> responseEntity = restTemplate.postForEntity(pathCuentaCreacion, dto, Cuenta.class);

        HttpStatusCode status = responseEntity.getStatusCode();

        assertEquals(HttpStatusCode.valueOf(201), status);
    }

    @Test
    public void Given_cuentaDTO_withNoUser_When_invoke_crearCuenta_Then_return_usuarioNoEncontrado() {
        CuentaDTO dto = new CuentaDTO("Ahorros", 0);

        ResponseEntity<Cuenta> responseEntity = restTemplate.postForEntity(pathCuentaCreacion, dto, Cuenta.class);

        HttpStatusCode status = responseEntity.getStatusCode();

        assertEquals(HttpStatusCode.valueOf(500), status);
    }

    @Test
    @Sql(statements = "INSERT INTO USER (DOCUMENT,NAME,LAST_NAME,DATE_CREATED)VALUES (16,'JUAN','PARRADO','2023-03-23')")
    @Sql(statements = "INSERT INTO ACCOUNT (TYPE,MONEY,DATE_CREATED,USER) VALUES ('AHORROS','1000','2023-03-23',16)")
    public void Given_IdCuentaDTO_When_invoke_consultarSaldo_Then_return_SaldoCuenta() {

        ResponseEntity<Cuenta> responseEntity = restTemplate.exchange(pathCuentaConsulta,HttpMethod.GET,null,Cuenta.class);

        HttpStatusCode status = responseEntity.getStatusCode();

        assertEquals(HttpStatusCode.valueOf(200),status);
    }
    @Test
    @Sql(statements = "INSERT INTO USER (DOCUMENT,NAME,LAST_NAME,DATE_CREATED)VALUES (15,'JUAN','PARRADO','2023-03-23')")
    @Sql(statements = "INSERT INTO ACCOUNT (TYPE,MONEY,DATE_CREATED,USER) VALUES ('AHORROS','1000','2023-03-23',15)")
    public void Given_IdCuentaDTO_when_invoke_realizarDeposito_Then_return_depositarEnCuenta(){
        ResponseEntity<Cuenta> responseEntity= restTemplate.exchange(pathCuentaDesposito,HttpMethod.PUT,null,Cuenta.class);

        HttpStatusCode status = responseEntity.getStatusCode();

        assertEquals(HttpStatusCode.valueOf(200),status);

    }

    @Test
    @Sql(statements = "INSERT INTO USER (DOCUMENT,NAME,LAST_NAME,DATE_CREATED)VALUES (14,'JUAN','PARRADO','2023-03-23')")
    @Sql(statements = "INSERT INTO ACCOUNT (TYPE,MONEY,DATE_CREATED,USER) VALUES ('AHORROS','1000','2023-03-23',14)")
    public void Given_IdCuentaDTO_When_invoke_eliminarCuenta_Then_return_deleteCuenta(){
        ResponseEntity<Cuenta> responseEntity = restTemplate.exchange(pathCuentaEliminar,HttpMethod.DELETE,null,Cuenta.class);

        HttpStatusCode status = responseEntity.getStatusCode();

        assertEquals(HttpStatusCode.valueOf(200),status);

    }



}
