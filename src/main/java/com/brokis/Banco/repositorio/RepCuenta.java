package com.brokis.Banco.repositorio;
import com.brokis.Banco.modelo.Cuenta;
import com.brokis.Banco.modelo.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RepCuenta extends JpaRepository <Cuenta,Long> {
    List<Cuenta> findByUsuario(Usuario usuario);
}
