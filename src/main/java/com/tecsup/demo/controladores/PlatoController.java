package com.tecsup.demo.controladores;

import com.tecsup.demo.modelo.entidades.Plato;
import com.tecsup.demo.modelo.entidades.Restaurante;
import com.tecsup.demo.servicios.PlatoService;
import com.tecsup.demo.servicios.RestauranteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import java.util.List;
import java.util.Map;

@Controller
@SessionAttributes("plato")
public class PlatoController {

    @Autowired
    private PlatoService platoService;

    @Autowired
    private RestauranteService restauranteService;

    @GetMapping("/listarPlatos")
    public String listar(Model model) {
        model.addAttribute("titulo", "Listado de Platos");
        model.addAttribute("platos", platoService.listar());
        return "listarPlatosView";
    }

    @GetMapping("/formPlato")
    public String crear(Map<String, Object> model) {
        Plato plato = new Plato();
        List<Restaurante> restaurantes = restauranteService.listar();
        model.put("plato", plato);
        model.put("restaurantes", restaurantes);
        model.put("titulo", "Formulario de Plato");
        return "formPlatoView";
    }

    @GetMapping("/formPlato/{id}")
    public String editar(@PathVariable(value = "id") Integer id, Map<String, Object> model) {
        Plato plato = null;
        if (id > 0) {
            plato = platoService.buscar(id);
            if (plato == null) {
                return "redirect:/listarPlatos";
            }
        } else {
            return "redirect:/listarPlatos";
        }
        List<Restaurante> restaurantes = restauranteService.listar();
        model.put("plato", plato);
        model.put("restaurantes", restaurantes);
        model.put("titulo", "Editar Plato");
        return "formPlatoView";
    }

    @PostMapping("/guardarPlato")
    public String guardar(@Valid Plato plato, BindingResult result, Model model, SessionStatus status) {
        if (result.hasErrors()) {
            List<Restaurante> restaurantes = restauranteService.listar();
            model.addAttribute("restaurantes", restaurantes);
            model.addAttribute("titulo", "Formulario de Plato");
            return "formPlatoView";
        }
        platoService.grabar(plato);
        status.setComplete();
        return "redirect:/listarPlatos";
    }

    @GetMapping("/eliminarPlato/{id}")
    public String eliminar(@PathVariable(value = "id") Integer id) {
        if (id > 0) {
            platoService.eliminar(id);
        }
        return "redirect:/listarPlatos";
    }
}
