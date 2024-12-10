package com.tecsup.demo.modelo.daos;

import com.tecsup.demo.modelo.entidades.DetallePedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetallePedidoRepository extends JpaRepository<DetallePedido, Integer> {
    List<DetallePedido> findByPedidoId(Integer pedidoId);
    List<DetallePedido> findByPlatoId(Integer platoId);
}
