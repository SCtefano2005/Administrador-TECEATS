package com.tecsup.demo.controladores;

import com.tecsup.demo.modelo.entidades.DetallePedido;
import com.tecsup.demo.modelo.entidades.Pedido;
import com.tecsup.demo.modelo.entidades.Plato;
import com.tecsup.demo.servicios.DetallePedidoService;
import com.tecsup.demo.servicios.PedidoService;
import com.tecsup.demo.servicios.PlatoService;
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
@SessionAttributes("detallePedido")
public class DetallePedidoController {

    @Autowired
    private DetallePedidoService detallePedidoService;

    @Autowired
    private PedidoService pedidoService;

    @Autowired
    private PlatoService platoService;

    @GetMapping("/listarDetallePedidos")
    public String listar(Model model) {
        model.addAttribute("titulo", "Listado de Detalles de Pedido");
        model.addAttribute("detallePedidos", detallePedidoService.listar());
        return "listarDetallePedidosView";
    }

    @GetMapping("/formDetallePedido")
    public String crear(Map<String, Object> model) {
        DetallePedido detallePedido = new DetallePedido();
        List<Pedido> pedidos = pedidoService.listar();
        List<Plato> platos = platoService.listar();
        model.put("detallePedido", detallePedido);
        model.put("pedidos", pedidos);
        model.put("platos", platos);
        model.put("titulo", "Formulario de Detalle de Pedido");
        return "formDetallePedidoView";
    }

    @GetMapping("/formDetallePedido/{id}")
    public String editar(@PathVariable(value = "id") Integer id, Map<String, Object> model) {
        DetallePedido detallePedido = null;
        if (id > 0) {
            detallePedido = detallePedidoService.buscar(id);
            if (detallePedido == null) {
                return "redirect:/listarDetallePedidos";
            }
        } else {
            return "redirect:/listarDetallePedidos";
        }
        List<Pedido> pedidos = pedidoService.listar();
        List<Plato> platos = platoService.listar();
        model.put("detallePedido", detallePedido);
        model.put("pedidos", pedidos);
        model.put("platos", platos);
        model.put("titulo", "Editar Detalle de Pedido");
        return "formDetallePedidoView";
    }

    @PostMapping("/guardarDetallePedido")
    public String guardar(@Valid DetallePedido detallePedido, BindingResult result, Model model, SessionStatus status) {
        if (result.hasErrors()) {
            List<Pedido> pedidos = pedidoService.listar();
            List<Plato> platos = platoService.listar();
            model.addAttribute("pedidos", pedidos);
            model.addAttribute("platos", platos);
            model.addAttribute("titulo", "Formulario de Detalle de Pedido");
            return "formDetallePedidoView";
        }
        detallePedidoService.grabar(detallePedido);
        status.setComplete();
        return "redirect:/listarDetallePedidos";
    }

    @GetMapping("/eliminarDetallePedido/{id}")
    public String eliminar(@PathVariable(value = "id") Integer id) {
        if (id > 0) {
            detallePedidoService.eliminar(id);
        }
        return "redirect:/listarDetallePedidos";
    }
}
