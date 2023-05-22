package com.brokis.Banco.servicio;

import com.brokis.Banco.gateway.dto.TransaccionDTO;

public interface ServicioTransaccion {

    String hacerTransferencia(TransaccionDTO transaccionDTO);

}
