package com.tecsup.demo.modelo.daos;

import com.tecsup.demo.modelo.entidades.Plato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlatoRepository extends JpaRepository<Plato, Integer> {
    List<Plato> findByNombreContainingIgnoreCase(String nombre);
    List<Plato> findByRestauranteId(Integer restauranteId);
}
