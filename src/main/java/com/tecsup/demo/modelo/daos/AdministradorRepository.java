package com.tecsup.demo.modelo.daos;

import com.tecsup.demo.modelo.entidades.Administrador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdministradorRepository extends JpaRepository<Administrador, Integer> {
    Optional<Administrador> findByCorreoElectronico(String correoElectronico);
    Optional<Administrador> findByNombre(String nombre);
}
