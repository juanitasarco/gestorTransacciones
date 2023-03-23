package com.brokis.Banco.servicio.Cuenta;

import com.brokis.Banco.modelo.*;
import com.brokis.Banco.repositorio.RepCuenta;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class ServicioCuentaImp implements ServicioCuenta {
    private final RepCuenta repCuenta;

    @Override
    public Cuenta crearCuenta(Cuenta Cuenta) {

        return repCuenta.save(Cuenta);
    }

    @Override
    public Optional<Cuenta> consultarSaldo(Long id) {
        return Optional.ofNullable(repCuenta.findById(id).orElseThrow(() ->
                new IllegalStateException("No se encontro ninguna cuenta con ese id")));
    }

    @Override
    public Cuenta depositarCuenta(Long id, int monto) {
        Cuenta cuentaBuscada = repCuenta.findById(id).orElseThrow(() ->
                new RuntimeException("Cuenta no encontrada"));
        cuentaBuscada.setMONEY(cuentaBuscada.getMONEY() + monto);
        return repCuenta.save(cuentaBuscada);
    }

    @Override
    public Cuenta eliminarCuenta(Long id) {
        Cuenta cuentaBuscada = repCuenta.findById(id).orElseThrow(() ->
                new RuntimeException("Cuenta no encontrada"));
        repCuenta.delete(cuentaBuscada);
        return null;
    }
}
