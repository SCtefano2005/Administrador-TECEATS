package com.tecsup.demo.controladores;

import com.tecsup.demo.modelo.entidades.Repartidor;
import com.tecsup.demo.modelo.entidades.Restaurante;
import com.tecsup.demo.servicios.RepartidorService;
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
@SessionAttributes("repartidor")
public class RepartidorController {

    @Autowired
    private RepartidorService repartidorService;

    @Autowired
    private RestauranteService restauranteService;

    @GetMapping("/listarRepartidores")
    public String listar(Model model) {
        model.addAttribute("titulo", "Listado de Repartidores");
        model.addAttribute("repartidores", repartidorService.listar());
        return "listarRepartidoresView";
    }

    @GetMapping("/formRepartidor")
    public String crear(Map<String, Object> model) {
        Repartidor repartidor = new Repartidor();
        List<Restaurante> restaurantes = restauranteService.listar();
        model.put("repartidor", repartidor);
        model.put("restaurantes", restaurantes);
        model.put("titulo", "Formulario de Repartidor");
        return "formRepartidorView";
    }

    @GetMapping("/formRepartidor/{id}")
    public String editar(@PathVariable(value = "id") Integer id, Map<String, Object> model) {
        Repartidor repartidor = null;
        if (id > 0) {
            repartidor = repartidorService.buscar(id);
            if (repartidor == null) {
                return "redirect:/listarRepartidores";
            }
        } else {
            return "redirect:/listarRepartidores";
        }
        List<Restaurante> restaurantes = restauranteService.listar();
        model.put("repartidor", repartidor);
        model.put("restaurantes", restaurantes);
        model.put("titulo", "Editar Repartidor");
        return "formRepartidorView";
    }

    @PostMapping("/guardarRepartidor")
    public String guardar(@Valid Repartidor repartidor, BindingResult result, Model model, SessionStatus status) {
        if (result.hasErrors()) {
            List<Restaurante> restaurantes = restauranteService.listar();
            model.addAttribute("restaurantes", restaurantes);
            model.addAttribute("titulo", "Formulario de Repartidor");
            return "formRepartidorView";
        }
        repartidorService.grabar(repartidor);
        status.setComplete();
        return "redirect:/listarRepartidores";
    }

    @GetMapping("/eliminarRepartidor/{id}")
    public String eliminar(@PathVariable(value = "id") Integer id) {
        if (id > 0) {
            repartidorService.eliminar(id);
        }
        return "redirect:/listarRepartidores";
    }
}
