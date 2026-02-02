package co.edu.cur.semillero.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ViewController {

    @GetMapping("/login")
    public String login() { return "login"; }

    @GetMapping("/registro")
    public String registro() { return "registro"; }

    @GetMapping("/dashboard")
    public String dashboard() { return "dashboard"; }

    @GetMapping("/admin")
    public String admin() { return "admin"; }

    @GetMapping("/semillero/{id}")
    public String semillero(@PathVariable Long id) { return "semillero"; }
}