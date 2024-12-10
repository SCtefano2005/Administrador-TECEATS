package com.tecsup.demo.servicios;

import com.tecsup.demo.modelo.entidades.Plato;
import java.util.List;

public interface PlatoService {
    List<Plato> listar();
    void grabar(Plato plato);
    Plato buscar(Integer id);
    void eliminar(Integer id);
}
