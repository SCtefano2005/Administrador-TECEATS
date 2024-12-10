package com.tecsup.demo.servicios;

import com.tecsup.demo.modelo.entidades.Repartidor;
import java.util.List;

public interface RepartidorService {
    List<Repartidor> listar();
    void grabar(Repartidor repartidor);
    Repartidor buscar(Integer id);
    void eliminar(Integer id);
}
