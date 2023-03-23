package com.brokis.Banco.servicio;

import com.brokis.Banco.controlador.DTO.IdDTO;
import com.brokis.Banco.modelo.Cuenta;

import java.util.Optional;

public interface ServicioTransaccion {

    Optional<IdDTO> consultarSaldo(IdDTO idDTO); //El optional es para que no se rompa si no encuentra ninguna cuenta
    Cuenta realizarDeposito(int id, int monto);

    String hacerTransferencia(int numeroCuentaOrigen, int numeroCuentaDestino, int monto);
}
