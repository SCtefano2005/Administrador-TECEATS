package com.tecsup.demo.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AdministradorController {

    // Otros métodos existentes...

    @GetMapping("/login")
    public String login(@RequestParam(value = "error", required = false) String error,
                        @RequestParam(value = "logout", required = false) String logout,
                        Model model) {
        if (error != null) {
            model.addAttribute("errorMsg", "Correo electrónico o contraseña inválidos.");
        }
        if (logout != null) {
            model.addAttribute("msg", "Has salido exitosamente.");
        }
        return "login";
    }
}
