package com.brokis.Banco.modelo;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity(name = "ACCOUNT")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cuenta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;
    @JsonFormat(pattern = " ")
    @Column(name = "TYPE")
    private String Tipo;
    @Column (name = "MONEY")
    private int Saldo=0;
    @Temporal(TemporalType.DATE)
    @Column (name = "DATE_CREATED")
    private Date Fecha_De_Creacion;

    @ManyToOne
    @Column (name = "USER")
    private Usuario usuario;


}
