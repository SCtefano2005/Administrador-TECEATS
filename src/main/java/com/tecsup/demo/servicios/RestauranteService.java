package com.tecsup.demo.servicios;

import com.tecsup.demo.modelo.entidades.Restaurante;
import java.util.List;

public interface RestauranteService {
    List<Restaurante> listar();
    void grabar(Restaurante restaurante);
    Restaurante buscar(Integer id);
    void eliminar(Integer id);
}
