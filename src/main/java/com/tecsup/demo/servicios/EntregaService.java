package com.tecsup.demo.servicios;

import com.tecsup.demo.modelo.entidades.Entrega;
import java.util.List;

public interface EntregaService {
    List<Entrega> listar();
    void grabar(Entrega entrega);
    Entrega buscar(Integer id);
    void eliminar(Integer id);
}
