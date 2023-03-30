package com.brokis.Banco.servicio.Cuenta;

import com.brokis.Banco.controlador.dto.CuentaDTO;
import com.brokis.Banco.controlador.dto.IdCuentaDTO;
import com.brokis.Banco.modelo.Cuenta;
public interface ServicioCuenta {
    Cuenta crearCuenta(CuentaDTO cuenta);
    Cuenta consultarSaldo(IdCuentaDTO idCuentaDTO);
    Cuenta depositarCuenta(IdCuentaDTO idCuentaDTO);
    Cuenta eliminarCuenta(IdCuentaDTO idCuentaDTO);
}
