package com.tecsup.demo.servicios;

import com.tecsup.demo.modelo.entidades.Pedido;
import java.util.List;

public interface PedidoService {
    List<Pedido> listar();
    void grabar(Pedido pedido);
    Pedido buscar(Integer id);
    void eliminar(Integer id);
}
