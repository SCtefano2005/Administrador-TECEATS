package com.tecsup.demo.servicios;

import com.tecsup.demo.modelo.entidades.Usuario;
import java.util.List;

public interface UsuarioService {
    List<Usuario> listar();
    void grabar(Usuario usuario);
    Usuario buscar(Integer id);
    void eliminar(Integer id);
}
