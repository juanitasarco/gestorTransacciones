package com.brokis.Banco.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import com.brokis.Banco.modelo.*;
public interface RepUsuario extends JpaRepository <Usuario,Long> {
}
