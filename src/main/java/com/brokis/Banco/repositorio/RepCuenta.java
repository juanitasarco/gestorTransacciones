package com.brokis.Banco.repositorio;

import com.brokis.Banco.controlador.DTO.IdDTO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepCuenta extends JpaRepository<IdDTO, Long> {
}
