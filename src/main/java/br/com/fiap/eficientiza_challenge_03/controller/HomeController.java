package br.com.fiap.eficientiza_challenge_03.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/home")
    public String home() {
        return "home/inicio";  // Garante que o home será buscado em templates/inicio.html
    }

}
