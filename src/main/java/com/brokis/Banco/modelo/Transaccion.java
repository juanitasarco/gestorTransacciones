package com.brokis.Banco.modelo;

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
    private Long transaccionId;
    @Column
    private Long cuentaOrigen;
    @Column
    private Long cuentaDestino;
    @Column
    private int monto;


}
