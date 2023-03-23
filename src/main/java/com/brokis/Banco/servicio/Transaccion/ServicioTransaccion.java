package com.brokis.Banco.servicio.Transaccion;

import com.brokis.Banco.modelo.*;

public interface ServicioTransaccion {
    Transaccion hacerTransferencia(Long transaccionId, Long cuentaOrigen, Long cuentaDestino, int monto );
}
