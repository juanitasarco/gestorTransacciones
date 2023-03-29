package com.brokis.Banco.servicio.Cuenta;

import com.brokis.Banco.controlador.dto.CuentaDTO;
import com.brokis.Banco.modelo.*;
import com.brokis.Banco.repositorio.RepCuenta;
import com.brokis.Banco.repositorio.RepUsuario;
import java.util.Date;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class ServicioCuentaImp implements ServicioCuenta {
    private final RepCuenta repCuenta;
    private final RepUsuario repositoryUsuario;

    @Override
    public Cuenta crearCuenta(CuentaDTO cuentaDTO) {
      Optional<Usuario> usuario = repositoryUsuario.findById(cuentaDTO.getDocumentoUsuario());
      if(usuario.isPresent()){
        Cuenta cuenta = new Cuenta();
        cuenta.setSaldo(0);
        cuenta.setFecha_De_Creacion(new Date());
        cuenta.setUsuario(usuario.get());
        cuenta.setTipo(cuentaDTO.getTipo());
        List<Cuenta> byUsuario = repCuenta.findByUsuario(usuario.get());
        if(byUsuario.size() >= 3){
          throw new IllegalStateException("El usuario ya tiene 3 cuentas");
        }

        return repCuenta.save(cuenta);
      }else{
        throw new IllegalArgumentException("Usuario innexistente");
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
