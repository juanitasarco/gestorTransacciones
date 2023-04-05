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
    public String hacerTransferencia(Long id,Long origen, Long destino, int monto) {

        Cuenta cuentaOrigen = repCuenta.findById(origen).orElseThrow(() ->
                new RuntimeException("Cuenta origen no encontrada"));
        Cuenta cuentaDestino = repCuenta.findById(destino).orElseThrow(() ->
                new RuntimeException("Cuenta destino no encontrada"));

        if (cuentaOrigen.getSaldo() < monto) {
            throw new RuntimeException("Saldo insuficiente");
        }

        System.out.println(destino);
        cuentaOrigen.setSaldo(cuentaOrigen.getSaldo() - monto);
        cuentaDestino.setSaldo(cuentaDestino.getSaldo() + monto);
        repCuenta.save(cuentaDestino);
        repCuenta.save(cuentaOrigen);
        Transaccion nuevaTransaccion = new Transaccion( id, origen,destino,monto);
        repTransaccion.save(nuevaTransaccion);

        return "La Transferencia ha sido exitosa";
    }


}