package com.brokis.Banco.servicio.Transaccion;

import com.brokis.Banco.modelo.*;
import com.brokis.Banco.modelo.Transaccion;
import com.brokis.Banco.repositorio.RepCuenta;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ServicioTransaccionImp implements ServicioTransaccion {

    public final RepCuenta repCuenta;

    @Override
    public Transaccion hacerTransferencia(Long transaccionId, Long cuentaOrigen, Long cuentaDestino, int monto) {
        Cuenta CuentaOrigen = repCuenta.findById(cuentaOrigen).orElseThrow(() ->
                new RuntimeException("Cuenta origen no encontrada"));
        Cuenta CuentaDestino = repCuenta.findById(cuentaDestino).orElseThrow(() ->
                new RuntimeException("Cuenta destino no encontrada"));

        if (CuentaOrigen.getSaldo() < monto) {
            throw new RuntimeException("Saldo insuficiente");
        }
        CuentaOrigen.setSaldo(CuentaOrigen.getSaldo() - monto);
        CuentaDestino.setSaldo(CuentaDestino.getSaldo() + monto);
        repCuenta.save(CuentaDestino);
        repCuenta.save(CuentaDestino);

        return null;
    }
}