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
@Service
@AllArgsConstructor
public class ServicioCuentaImp implements ServicioCuenta {
    private final RepCuenta repCuenta;
    private final RepUsuario repositoryUsuario;
    @Override
    public Cuenta crearCuenta(CuentaDTO cuentaDTO) {
        Usuario usuarioBuscado = repositoryUsuario.findById(cuentaDTO.getDocumentoUsuario())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        if (usuarioBuscado != null) {
            Cuenta cuenta = new Cuenta();
            cuenta.setSaldo(0);
            cuenta.setFechaDeCreacion(new Date());
            cuenta.setUsuario(usuarioBuscado);
            cuenta.setTipo(cuentaDTO.getTipo());
            List<Cuenta> byUsuario = repCuenta.findByUsuario(usuarioBuscado);
            if (byUsuario.size() >= 3) {
                throw new IllegalStateException("El usuario ya tiene 3 cuentas");
            }
            return repCuenta.save(cuenta);
        }
        return null;
    }
    @Override
    public Cuenta consultarSaldo(IdCuentaDTO idCuentaDTO) {
        Cuenta cuentaBuscada = repCuenta.findById(idCuentaDTO.getId()).orElseThrow(() ->
                new RuntimeException("Cuenta no encontrada"));
        if (cuentaBuscada!= null) {
            return cuentaBuscada;
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