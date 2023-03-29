package com.brokis.Banco.servicio.Cuenta;

import com.brokis.Banco.controlador.dto.CuentaDTO;
import com.brokis.Banco.modelo.Cuenta;
import java.util.Optional;

public interface ServicioCuenta {

    Cuenta crearCuenta(CuentaDTO cuenta);
    Optional<Cuenta> consultarSaldo(Long id);
    Cuenta depositarCuenta(Long id, int monto);
    Cuenta eliminarCuenta(Long id);
}
