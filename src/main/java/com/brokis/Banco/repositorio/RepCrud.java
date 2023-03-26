package com.brokis.Banco.repositorio;
import com.brokis.Banco.modelo.Cuenta;
import org.springframework.data.repository.CrudRepository;

public interface RepCrud extends CrudRepository<Cuenta,Long> {
    long countByUsuario(Long DOCUMENT);
}
