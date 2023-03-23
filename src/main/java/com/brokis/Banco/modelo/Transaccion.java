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
    private int transaccionId;
    @Column
    private int cuentaOrigen;
    @Column
    private int cuentaDestino;
    @Column
    private int monto;


}
