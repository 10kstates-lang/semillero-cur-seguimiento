package co.edu.cur.semillero.controller;

import co.edu.cur.semillero.model.Usuario;
import co.edu.cur.semillero.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {

    @Autowired
    private UsuarioService usuarioService;

    // Página de Inicio
    @GetMapping("/")
    public String home() {
        return "index"; 
    }

    // Formulario de Login
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    // Formulario de Registro
    @GetMapping("/registro")
    public String registroForm(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "registro";
    }

    // Procesar Registro
    @PostMapping("/registro")
    public String registrarUsuario(@ModelAttribute Usuario usuario) {
        usuarioService.registrarUsuario(usuario);
        return "redirect:/login?registrado";
    }

    // Panel Principal (Dashboard) - Protegido
    @GetMapping("/dashboard")
    public String dashboard() {
        return "dashboard"; // Necesitaremos crear este HTML después
    }
}