package com.brokis.Banco.servicio.Transaccion;

import com.brokis.Banco.modelo.*;
import com.brokis.Banco.modelo.Transaccion;
import com.brokis.Banco.repositorio.RepCuenta;
import com.brokis.Banco.repositorio.RepTransaccion;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ServicioTransaccionImp implements ServicioTransaccion {

    public final RepCuenta repCuenta;
    public final RepTransaccion repTransaccion;

    @Override
    public String hacerTransferencia(Long ID,Long ORIGIN, Long DESTINATION, int AMOUNT) {

        Cuenta CuentaOrigen = repCuenta.findById(ORIGIN).orElseThrow(() ->
                new RuntimeException("Cuenta origen no encontrada"));
        Cuenta CuentaDestino = repCuenta.findById(DESTINATION).orElseThrow(() ->
                new RuntimeException("Cuenta destino no encontrada"));

        if (CuentaOrigen.getSaldo() < AMOUNT) {
            throw new RuntimeException("Saldo insuficiente");
        }

        System.out.println(DESTINATION);
        CuentaOrigen.setSaldo(CuentaOrigen.getSaldo() - AMOUNT);
        CuentaDestino.setSaldo(CuentaDestino.getSaldo() + AMOUNT);
        repCuenta.save(CuentaDestino);
        repCuenta.save(CuentaOrigen);
        Transaccion nuevaTransaccion = new Transaccion( ID, ORIGIN,DESTINATION,AMOUNT);
        repTransaccion.save(nuevaTransaccion);

        return "La Transferencia ha sido exitosa";
    }


}