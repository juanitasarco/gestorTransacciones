package com.brokis.Banco.servicio.Cuenta;

import com.brokis.Banco.modelo.*;
import com.brokis.Banco.repositorio.RepCrud;
import com.brokis.Banco.repositorio.RepCuenta;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class ServicioCuentaImp implements ServicioCuenta {
    private final RepCuenta repCuenta;
    private final RepCrud repCrud;

    @Override
    public Cuenta crearCuenta(Cuenta cuenta) {
        long id = cuenta.getID();
        long count = repCrud.countByUsuario(id);
        if (count > 3) {
            throw new IllegalStateException("El usuario ya tiene 3 cuentas");
        }else {
            return repCuenta.save(cuenta);
        }
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
        cuentaBuscada.setSaldo(cuentaBuscada.getSaldo() + monto);
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
