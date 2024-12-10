package com.tecsup.demo.modelo.daos;

import com.tecsup.demo.modelo.entidades.Entrega;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EntregaRepository extends JpaRepository<Entrega, Integer> {
    List<Entrega> findByRepartidorId(Integer repartidorId);
    List<Entrega> findByPedidoId(Integer pedidoId);
}
