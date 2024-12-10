package com.tecsup.demo.controladores;

import com.tecsup.demo.modelo.entidades.Pedido;
import com.tecsup.demo.modelo.entidades.Usuario;
import com.tecsup.demo.modelo.entidades.Restaurante;
import com.tecsup.demo.servicios.PedidoService;
import com.tecsup.demo.servicios.UsuarioService;
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
@SessionAttributes("pedido")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private RestauranteService restauranteService;

    @GetMapping("/listarPedidos")
    public String listar(Model model) {
        model.addAttribute("titulo", "Listado de Pedidos");
        model.addAttribute("pedidos", pedidoService.listar());
        return "listarPedidosView";
    }

    @GetMapping("/formPedido")
    public String crear(Map<String, Object> model) {
        Pedido pedido = new Pedido();
        List<Usuario> usuarios = usuarioService.listar();
        List<Restaurante> restaurantes = restauranteService.listar();
        model.put("pedido", pedido);
        model.put("usuarios", usuarios);
        model.put("restaurantes", restaurantes);
        model.put("titulo", "Formulario de Pedido");
        return "formPedidoView";
    }

    @GetMapping("/formPedido/{id}")
    public String editar(@PathVariable(value = "id") Integer id, Map<String, Object> model) {
        Pedido pedido = null;
        if (id > 0) {
            pedido = pedidoService.buscar(id);
            if (pedido == null) {
                return "redirect:/listarPedidos";
            }
        } else {
            return "redirect:/listarPedidos";
        }
        List<Usuario> usuarios = usuarioService.listar();
        List<Restaurante> restaurantes = restauranteService.listar();
        model.put("pedido", pedido);
        model.put("usuarios", usuarios);
        model.put("restaurantes", restaurantes);
        model.put("titulo", "Editar Pedido");
        return "formPedidoView";
    }

    @PostMapping("/guardarPedido")
    public String guardar(@Valid Pedido pedido, BindingResult result, Model model, SessionStatus status) {
        if (result.hasErrors()) {
            List<Usuario> usuarios = usuarioService.listar();
            List<Restaurante> restaurantes = restauranteService.listar();
            model.addAttribute("usuarios", usuarios);
            model.addAttribute("restaurantes", restaurantes);
            model.addAttribute("titulo", "Formulario de Pedido");
            return "formPedidoView";
        }
        pedidoService.grabar(pedido);
        status.setComplete();
        return "redirect:/listarPedidos";
    }

    @GetMapping("/eliminarPedido/{id}")
    public String eliminar(@PathVariable(value = "id") Integer id) {
        if (id > 0) {
            pedidoService.eliminar(id);
        }
        return "redirect:/listarPedidos";
    }
}
