package com.brokis.Banco.modelo;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "USER")
public class Usuario {

    @Id
    @Column(name = "DOCUMENT")
    private int DOCUMENT;
    @Column(name = "NAME")
    private String NAME;
    @Column(name = "LAST_NAME")
    private String LAST_NAME;
    @Temporal(TemporalType.DATE)
    @Column(name = "DATE_CREATED")
    private Date DATE_CREATED;

    @PrePersist
    public void prePersist() {
        DATE_CREATED = new Date();
    }

}
