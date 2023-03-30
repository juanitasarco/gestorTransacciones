package com.brokis.Banco.modelo;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "USER")
public class Usuario {

    @Id
    @Column(name = "DOCUMENT")
    private Long documento;
    @Column(name = "NAME")
    private String nombre;
    @Column(name = "LAST_NAME")
    private String apellido;
    @Temporal(TemporalType.DATE)
    @Column(name = "DATE_CREATED")
    private Date fechaDeCreacion;

    @PrePersist
    public void prePersist() {
        fechaDeCreacion = new Date();
    }

}
