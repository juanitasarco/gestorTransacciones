package com.brokis.Banco.servicio.Cuenta;
import com.brokis.Banco.controlador.dto.CuentaDTO;
import com.brokis.Banco.controlador.dto.IdCuentaDTO;
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
        if (usuario.isPresent()) {
            Cuenta cuenta = new Cuenta();
            cuenta.setSaldo(0);
            cuenta.setFechaDeCreacion(new Date());
            cuenta.setUsuario(usuario.get());
            cuenta.setTipo(cuentaDTO.getTipo());
            List<Cuenta> byUsuario = repCuenta.findByUsuario(usuario.get());
            if (byUsuario.size() >= 3) {
                throw new IllegalStateException("El usuario ya tiene 3 cuentas");
            }
            return repCuenta.save(cuenta);
        } else {
            throw new IllegalArgumentException("Usuario innexistente");
        }
    }
    @Override
    public Cuenta consultarSaldo(IdCuentaDTO idCuentaDTO) {
        Optional<Cuenta> cuenta = repCuenta.findById(idCuentaDTO.getId());
        if (cuenta.isPresent()) {
            return cuenta.get();
        } else {
            throw new IllegalArgumentException("Cuenta innexistente");
        }
    }
    @Override
    public Cuenta depositarCuenta(IdCuentaDTO idCuentaDTO) {
        Cuenta cuentaBuscada = repCuenta.findById(idCuentaDTO.getId()).orElseThrow(() ->
                new RuntimeException("Cuenta no encontrada"));
        cuentaBuscada.setSaldo(cuentaBuscada.getSaldo() + idCuentaDTO.getMonto());
        return repCuenta.save(cuentaBuscada);
    }
    @Override
    public Cuenta eliminarCuenta(IdCuentaDTO idCuentaDTO) {
        Cuenta cuentaBuscada = repCuenta.findById(idCuentaDTO.getId()).orElseThrow(() ->
                new RuntimeException("Cuenta no encontrada"));
        repCuenta.delete(cuentaBuscada);
        return null;
    }
}