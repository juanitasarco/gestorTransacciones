package com.brokis.Banco.servicio.Transaccion;

import com.brokis.Banco.modelo.*;

public interface ServicioTransaccion {

    String hacerTransferencia( Long ID,Long ORIGIN, Long DESTINATION, int AMOUNT );

}
