package com.brokis.Banco.controlador.dto;


import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class TransaccionDTO {

    private Long cuentaOrigen;
    private Long cuentaDestino;
    private int monto;


}
