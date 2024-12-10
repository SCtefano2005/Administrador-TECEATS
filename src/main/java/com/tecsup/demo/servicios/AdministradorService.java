package com.tecsup.demo.servicios;

import com.tecsup.demo.modelo.entidades.Administrador;
import java.util.List;

public interface AdministradorService {
    List<Administrador> listar();
    void grabar(Administrador administrador);
    Administrador buscar(Integer id);
    void eliminar(Integer id);
}
