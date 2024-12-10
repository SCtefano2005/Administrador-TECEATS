package com.tecsup.demo.controladores;

import com.tecsup.demo.modelo.entidades.Entrega;
import com.tecsup.demo.modelo.entidades.Pedido;
import com.tecsup.demo.modelo.entidades.Repartidor;
import com.tecsup.demo.servicios.EntregaService;
import com.tecsup.demo.servicios.PedidoService;
import com.tecsup.demo.servicios.RepartidorService;
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
@SessionAttributes("entrega")
public class EntregaController {

    @Autowired
    private EntregaService entregaService;

    @Autowired
    private PedidoService pedidoService;

    @Autowired
    private RepartidorService repartidorService;

    @GetMapping("/listarEntregas")
    public String listar(Model model) {
        model.addAttribute("titulo", "Listado de Entregas");
        model.addAttribute("entregas", entregaService.listar());
        return "listarEntregasView";
    }

    @GetMapping("/formEntrega")
    public String crear(Map<String, Object> model) {
        Entrega entrega = new Entrega();
        List<Pedido> pedidos = pedidoService.listar();
        List<Repartidor> repartidores = repartidorService.listar();
        model.put("entrega", entrega);
        model.put("pedidos", pedidos);
        model.put("repartidores", repartidores);
        model.put("titulo", "Formulario de Entrega");
        return "formEntregaView";
    }

    @GetMapping("/formEntrega/{id}")
    public String editar(@PathVariable(value = "id") Integer id, Map<String, Object> model) {
        Entrega entrega = null;
        if (id > 0) {
            entrega = entregaService.buscar(id);
            if (entrega == null) {
                return "redirect:/listarEntregas";
            }
        } else {
            return "redirect:/listarEntregas";
        }
        List<Pedido> pedidos = pedidoService.listar();
        List<Repartidor> repartidores = repartidorService.listar();
        model.put("entrega", entrega);
        model.put("pedidos", pedidos);
        model.put("repartidores", repartidores);
        model.put("titulo", "Editar Entrega");
        return "formEntregaView";
    }

    @PostMapping("/guardarEntrega")
    public String guardar(@Valid Entrega entrega, BindingResult result, Model model, SessionStatus status) {
        if (result.hasErrors()) {
            List<Pedido> pedidos = pedidoService.listar();
            List<Repartidor> repartidores = repartidorService.listar();
            model.addAttribute("pedidos", pedidos);
            model.addAttribute("repartidores", repartidores);
            model.addAttribute("titulo", "Formulario de Entrega");
            return "formEntregaView";
        }
        entregaService.grabar(entrega);
        status.setComplete();
        return "redirect:/listarEntregas";
    }

    @GetMapping("/eliminarEntrega/{id}")
    public String eliminar(@PathVariable(value = "id") Integer id) {
        if (id > 0) {
            entregaService.eliminar(id);
        }
        return "redirect:/listarEntregas";
    }
}
