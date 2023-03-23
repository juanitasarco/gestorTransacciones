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
    private int documento;
    @Column(nullable = false)
    private String nombre;
    @Column(nullable = false)
    private String apellido;
    @Column
    @DateTimeFormat
    private String fechaDeCreacion ;



}
