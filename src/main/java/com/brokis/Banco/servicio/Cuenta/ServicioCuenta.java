package com.brokis.Banco.servicio.Cuenta;
import com.brokis.Banco.modelo.*;

import java.util.Optional;

public interface ServicioCuenta {

    Cuenta crearCuenta(Cuenta Cuenta);
    Optional<Cuenta> consultarSaldo(Long id);
    Cuenta depositarCuenta(Long id, int monto);
    Cuenta eliminarCuenta(Long id);
}
