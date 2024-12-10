package com.tecsup.demo.controladores;

import com.tecsup.demo.modelo.entidades.DuenoRestaurante;
import com.tecsup.demo.servicios.DuenoRestauranteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import java.util.Map;

@Controller
@SessionAttributes("duenoRestaurante")
public class DuenoRestauranteController {

    @Autowired
    private DuenoRestauranteService duenoRestauranteService;

    @GetMapping("/listarDuenoRestaurantes")
    public String listar(Model model) {
        model.addAttribute("titulo", "Listado de Dueños de Restaurante");
        model.addAttribute("duenos", duenoRestauranteService.listar());
        return "listarDuenoRestaurantesView";
    }

    @GetMapping("/formDuenoRestaurante")
    public String crear(Map<String, Object> model) {
        DuenoRestaurante dueno = new DuenoRestaurante();
        model.put("duenoRestaurante", dueno);
        model.put("titulo", "Formulario de Dueño de Restaurante");
        return "formDuenoRestauranteView";
    }

    @GetMapping("/formDuenoRestaurante/{id}")
    public String editar(@PathVariable(value = "id") Integer id, Map<String, Object> model) {
        DuenoRestaurante dueno = null;
        if (id > 0) {
            dueno = duenoRestauranteService.buscar(id);
            if (dueno == null) {
                return "redirect:/listarDuenoRestaurantes";
            }
        } else {
            return "redirect:/listarDuenoRestaurantes";
        }
        model.put("duenoRestaurante", dueno);
        model.put("titulo", "Editar Dueño de Restaurante");
        return "formDuenoRestauranteView";
    }

    @PostMapping("/guardarDuenoRestaurante")
    public String guardar(@Valid DuenoRestaurante duenoRestaurante, BindingResult result, Model model, SessionStatus status) {
        if (result.hasErrors()) {
            model.addAttribute("titulo", "Formulario de Dueño de Restaurante");
            return "formDuenoRestauranteView";
        }
        duenoRestauranteService.grabar(duenoRestaurante);
        status.setComplete();
        return "redirect:/listarDuenoRestaurantes";
    }

    @GetMapping("/eliminarDuenoRestaurante/{id}")
    public String eliminar(@PathVariable(value = "id") Integer id) {
        if (id > 0) {
            duenoRestauranteService.eliminar(id);
        }
        return "redirect:/listarDuenoRestaurantes";
    }
}
