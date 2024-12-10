package com.tecsup.demo.modelo.daos;

import com.tecsup.demo.modelo.entidades.Repartidor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepartidorRepository extends JpaRepository<Repartidor, Integer> {
    List<Repartidor> findByVehiculo(String vehiculo);
    List<Repartidor> findByRestauranteId(Integer restauranteId);
}
