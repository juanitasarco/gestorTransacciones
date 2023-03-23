package com.brokis.Banco.modelo;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "USER")
public class Usuario {

    @Id
    private Long documento;
    @Column(name = "NAME")
    private String nombre;
    @Column(name = "LAST_NAME")
    private String apellido;
    @Temporal(TemporalType.DATE)
    @Column(name = "DATE_CREATED")
    private String fechaDeCreacion ;



}
