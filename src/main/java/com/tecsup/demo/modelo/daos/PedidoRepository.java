package com.tecsup.demo.modelo.daos;

import com.tecsup.demo.modelo.entidades.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Integer> {
    List<Pedido> findByEstado(String estado);
    List<Pedido> findByUsuarioId(Integer usuarioId);
    List<Pedido> findByRestauranteId(Integer restauranteId);
}
