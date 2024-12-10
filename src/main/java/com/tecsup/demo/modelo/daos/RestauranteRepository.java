package com.tecsup.demo.modelo.daos;

import com.tecsup.demo.modelo.entidades.Restaurante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RestauranteRepository extends JpaRepository<Restaurante, Integer> {
    List<Restaurante> findByStatus(String status);
    List<Restaurante> findByTipoCocina(String tipoCocina);
}
