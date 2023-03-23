package com.brokis.Banco.repositorio;

import com.brokis.Banco.modelo.Transaccion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepTransaccion extends JpaRepository <Transaccion,Long> {
}
