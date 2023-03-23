package com.brokis.Banco.servicio;

import com.brokis.Banco.controlador.DTO.IdDTO;
import com.brokis.Banco.modelo.Cuenta;
import com.brokis.Banco.modelo.Usuario;
import com.brokis.Banco.repositorio.RepCuenta;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class ServicioTransaccionImp implements ServicioTransaccion {

    private final RepCuenta repCuenta;
    private final IdDTO idDTO;


    @Override
    public Optional<IdDTO> consultarSaldo(IdDTO idDTO) {
        return Optional.ofNullable(repCuenta.findById(Long.parseLong(String.valueOf(idDTO.id))).orElseThrow(() ->
                new IllegalStateException("No se encontro ninguna cuenta ")));

    }

    @Override
    public Cuenta realizarDeposito(int id, int monto) {
        return null;
    }

    @Override
    public String hacerTransferencia(int numeroCuentaOrigen, int numeroCuentaDestino, int monto) {
        return null;
    }
    /*
    @Override
    public Cuenta realizarDeposito(int id, int monto) {
        Cuenta cuentaBuscada = repCuenta.findById(cuenta).orElseThrow(() ->
                new RuntimeException("Cuenta no encontrada"));
        usuarioBuscada.setSaldo(usuarioBuscada.getSaldo() + monto);
        return repCuenta.save(usuarioBuscada);
    }

    @Override
    public String hacerTransferencia(int numeroCuentaOrigen, int numeroCuentaDestino, int monto) {
        Cuenta CuentaOrigen = repCuenta.findById(numeroCuentaOrigen).orElseThrow(() ->
                new RuntimeException("Cuenta origen no encontrada"));
        Usuario usuarioDestino = repCuenta.findById(numeroCuentaDestino).orElseThrow(() ->
                new RuntimeException("Cuenta destino no encontrada"));

        if (CuentaOrigen.getSaldo() < monto) {
            throw new RuntimeException("Saldo insuficiente");
        }
        CuentaOrigen.setSaldo(CuentaOrigen.getSaldo() - monto);
        CuentaDestino.setSaldo(usuarioDestino.getSaldo() + monto);
        repCuenta.save(usuarioOrigen);
        repCuenta.save(usuarioDestino);

        return "Transferencia realizada con exito";
    } */
}