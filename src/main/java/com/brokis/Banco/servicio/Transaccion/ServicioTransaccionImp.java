package com.brokis.Banco.servicio.Transaccion;

import com.brokis.Banco.controlador.dto.TransaccionDTO;
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
    public String hacerTransferencia(TransaccionDTO transaccionDTO) {

        Cuenta cuentaOrigen = repCuenta.findById(transaccionDTO.getOrigen()).orElseThrow(() ->
                new RuntimeException("Cuenta origen no encontrada"));
        Cuenta cuentaDestino = repCuenta.findById(transaccionDTO.getDestino()).orElseThrow(() ->
                new RuntimeException("Cuenta destino no encontrada"));

        if (cuentaOrigen.getSaldo() < transaccionDTO.getMonto()) {
            throw new RuntimeException("Saldo insuficiente");
        }


        cuentaOrigen.setSaldo(cuentaOrigen.getSaldo() - transaccionDTO.getMonto());
        cuentaDestino.setSaldo(cuentaDestino.getSaldo() + transaccionDTO.getMonto());
        repCuenta.save(cuentaDestino);
        repCuenta.save(cuentaOrigen);
        Transaccion nuevaTransaccion = new Transaccion();
        nuevaTransaccion.setDestino(transaccionDTO.getDestino());
        nuevaTransaccion.setOrigen(transaccionDTO.getOrigen());
        nuevaTransaccion.setMonto(transaccionDTO.getMonto());
        repTransaccion.save(nuevaTransaccion);

        return "La Transferencia ha sido exitosa";
    }


}