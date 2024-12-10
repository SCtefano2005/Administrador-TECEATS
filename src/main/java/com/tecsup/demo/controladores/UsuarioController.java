package com.tecsup.demo.controladores;

import com.tecsup.demo.modelo.entidades.Usuario;
import com.tecsup.demo.servicios.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import java.util.Map;

@Controller
@SessionAttributes("usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/listarUsuarios")
    public String listar(Model model) {
        model.addAttribute("titulo", "Listado de Usuarios");
        model.addAttribute("usuarios", usuarioService.listar());
        return "listarUsuariosView";
    }

    @GetMapping("/formUsuario")
    public String crear(Map<String, Object> model) {
        Usuario usuario = new Usuario();
        model.put("usuario", usuario);
        model.put("titulo", "Formulario de Usuario");
        return "formUsuarioView";
    }

    @GetMapping("/formUsuario/{id}")
    public String editar(@PathVariable(value = "id") Integer id, Map<String, Object> model) {
        Usuario usuario = null;
        if (id > 0) {
            usuario = usuarioService.buscar(id);
            if (usuario == null) {
                return "redirect:/listarUsuarios";
            }
        } else {
            return "redirect:/listarUsuarios";
        }
        model.put("usuario", usuario);
        model.put("titulo", "Editar Usuario");
        return "formUsuarioView";
    }

    @PostMapping("/guardarUsuario")
    public String guardar(@Valid Usuario usuario, BindingResult result, Model model, SessionStatus status) {
        if (result.hasErrors()) {
            model.addAttribute("titulo", "Formulario de Usuario");
            return "formUsuarioView";
        }
        usuarioService.grabar(usuario);
        status.setComplete();
        return "redirect:/listarUsuarios";
    }

    @GetMapping("/eliminarUsuario/{id}")
    public String eliminar(@PathVariable(value = "id") Integer id) {
        if (id > 0) {
            usuarioService.eliminar(id);
        }
        return "redirect:/listarUsuarios";
    }
}
