package com.brokis.Banco.servicio;

import com.brokis.Banco.modelo.Cuenta;
import com.brokis.Banco.modelo.Usuario;
import com.brokis.Banco.repositorio.RepCuenta;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CreadorCuentaImp implements CreadorCuenta{
    private final RepCuenta repCuenta;

    @Override
    public Cuenta crearCuenta(Cuenta Cuenta) {
        return null;
    }
}
