package com.brokis.Banco.servicio.Transaccion;

import com.brokis.Banco.controlador.dto.TransaccionDTO;
import com.brokis.Banco.modelo.*;

public interface ServicioTransaccion {

    String hacerTransferencia(TransaccionDTO transaccionDTO);

}
