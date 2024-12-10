package com.tecsup.demo.servicios;

import com.tecsup.demo.modelo.entidades.DetallePedido;
import java.util.List;

public interface DetallePedidoService {
    List<DetallePedido> listar();
    void grabar(DetallePedido detallePedido);
    DetallePedido buscar(Integer id);
    void eliminar(Integer id);
}
