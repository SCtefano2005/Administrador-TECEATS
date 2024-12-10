
package com.tecsup.demo.controladores;

import com.tecsup.demo.modelo.entidades.Restaurante;
import com.tecsup.demo.modelo.entidades.DuenoRestaurante;
import com.tecsup.demo.servicios.RestauranteService;
import com.tecsup.demo.servicios.DuenoRestauranteService;
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
@SessionAttributes("restaurante")
public class RestauranteController {

    @Autowired
    private RestauranteService restauranteService;

    @Autowired
    private DuenoRestauranteService duenoService;

    /**
     * Método para listar todos los restaurantes.
     */
    @GetMapping("/listarRestaurantes")
    public String listar(Model model) {
        model.addAttribute("titulo", "Listado de Restaurantes");
        model.addAttribute("restaurantes", restauranteService.listar());
        return "listarRestaurantesView";
    }

    /**
     * Método para mostrar el formulario de creación de un nuevo restaurante.
     */
    @GetMapping("/formRestaurante")
    public String crear(Model model) {
        Restaurante restaurante = new Restaurante();
        List<DuenoRestaurante> duenos = duenoService.listar();
        model.addAttribute("restaurante", restaurante);
        model.addAttribute("duenos", duenos);
        model.addAttribute("titulo", "Formulario de Restaurante");
        return "formRestauranteView";
    }

    /**
     * Método para mostrar el formulario de edición de un restaurante existente.
     */
    @GetMapping("/formRestaurante/{id}")
    public String editar(@PathVariable(value = "id") Integer id, Model model) {
        Restaurante restaurante = null;
        if (id != null && id > 0) {
            restaurante = restauranteService.buscar(id);
            if (restaurante == null) {
                return "redirect:/listarRestaurantes";
            }
        } else {
            return "redirect:/listarRestaurantes";
        }
        List<DuenoRestaurante> duenos = duenoService.listar();
        model.addAttribute("restaurante", restaurante);
        model.addAttribute("duenos", duenos);
        model.addAttribute("titulo", "Editar Restaurante");
        return "formRestauranteView";
    }

    /**
     * Método para guardar o actualizar un restaurante.
     */
    @PostMapping("/guardarRestaurante")
    public String guardar(@Valid @ModelAttribute("restaurante") Restaurante restaurante, BindingResult result, Model model, SessionStatus status) {
        if (result.hasErrors()) {
            List<DuenoRestaurante> duenos = duenoService.listar();
            model.addAttribute("duenos", duenos);
            model.addAttribute("titulo", "Formulario de Restaurante");
            return "formRestauranteView";
        }
        restauranteService.grabar(restaurante);
        status.setComplete();
        return "redirect:/listarRestaurantes";
    }

    /**
     * Método para eliminar un restaurante por su ID.
     */
    @GetMapping("/eliminarRestaurante/{id}")
    public String eliminar(@PathVariable(value = "id") Integer id) {
        if (id != null && id > 0) {
            restauranteService.eliminar(id);
        }
        return "redirect:/listarRestaurantes";
    }
}
