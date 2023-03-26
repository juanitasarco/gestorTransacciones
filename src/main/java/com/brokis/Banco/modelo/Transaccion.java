package com.brokis.Banco.modelo;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

@Entity(name = "TRANSACTION")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Transaccion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;
    @Column (name = "ORIGEN")
    private Long  ORIGEN;
    @JsonFormat(pattern = " ")
    @Column (name = "DESTINATION")
    private Long DESTINATION;
    @Column (name = "AMOUNT")
    private int AMOUNT;


}
