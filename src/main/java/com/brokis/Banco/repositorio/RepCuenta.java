package com.brokis.Banco.repositorio;
import com.brokis.Banco.modelo.Cuenta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepCuenta extends JpaRepository <Cuenta,Long> {
}
