package com.brokis.Banco.modelo;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "TRANSACTION")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Transaccion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column (name = "ORIGEN")
    private Long origen;
    @JsonFormat(pattern = " ")
    @Column (name = "DESTINATION")
    private Long destino;
    @Column (name = "AMOUNT")
    private int monto;


}
