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
    private Long id;
    @JsonFormat(pattern = " ")
    @Column(name = "TYPE")
    private String tipo;
    @Column (name = "MONEY")
    private int saldo;
    @Temporal(TemporalType.DATE)
    @Column(name = "DATE_CREATED")
    private Date fechaDeCreacion;
    @ManyToOne()
    @JoinColumn(name = "USER")
    Usuario usuario;

    @PrePersist
    public void prePersist() {
        fechaDeCreacion = new Date();
    }
}
