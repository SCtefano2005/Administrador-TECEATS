package com.tecsup.demo.servicios;

import com.tecsup.demo.modelo.entidades.DuenoRestaurante;
import java.util.List;

public interface DuenoRestauranteService {
    List<DuenoRestaurante> listar();
    void grabar(DuenoRestaurante duenoRestaurante);
    DuenoRestaurante buscar(Integer id);
    void eliminar(Integer id);
}
