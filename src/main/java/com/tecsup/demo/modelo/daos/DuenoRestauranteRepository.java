package com.tecsup.demo.modelo.daos;

import com.tecsup.demo.modelo.entidades.DuenoRestaurante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DuenoRestauranteRepository extends JpaRepository<DuenoRestaurante, Integer> {
    Optional<DuenoRestaurante> findByCorreoElectronico(String correoElectronico);
    Optional<DuenoRestaurante> findByGoogleId(String googleId);
}
