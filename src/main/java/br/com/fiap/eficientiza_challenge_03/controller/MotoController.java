package br.com.fiap.eficientiza_challenge_03.controller;

import br.com.fiap.eficientiza_challenge_03.model.Moto;
import br.com.fiap.eficientiza_challenge_03.service.MotoService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/motos")
public class MotoController {

    private final MotoService motoService;

    public MotoController(MotoService motoService) {
        this.motoService = motoService;
    }

    // Listagem
    @GetMapping
    public String listar(Model model) {
        List<Moto> motos = motoService.listar();
        model.addAttribute("motos", motos);
        return "motos/listagem";
    }

    // Formulário - novo
    @GetMapping("/novo")
    public String novo(Model model) {
        model.addAttribute("moto", new Moto());
        model.addAttribute("statuses", List.of("DISPONIVEL","OCUPADA","MANUTENCAO","INATIVA"));
        return "motos/formulario";
    }

    // Salvar
    @PostMapping("/salvar")
    public String salvar(@ModelAttribute @Valid Moto moto,
                         org.springframework.validation.BindingResult binding,
                         Model model) {
        model.addAttribute("statuses", List.of("DISPONIVEL","OCUPADA","MANUTENCAO","INATIVA"));
        if (binding.hasErrors()) {
            return "motos/formulario";
        }
        motoService.salvar(moto);
        return "redirect:/motos";
    }

    // Editar
    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        Moto moto = motoService.buscarPorId(id);
        model.addAttribute("moto", moto);
        model.addAttribute("statuses", List.of("DISPONIVEL","OCUPADA","MANUTENCAO","INATIVA"));
        return "motos/formulario";
    }

    // Atualizar
    @PostMapping("/alterar/{id}")
    public String alterar(@PathVariable Long id,
                          @ModelAttribute @Valid Moto moto,
                          org.springframework.validation.BindingResult binding,
                          Model model) {
        if (binding.hasErrors()) {
            return "motos/formulario";
        }
        motoService.atualizar(id, moto);
        return "redirect:/motos";
    }

    // Excluir
    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable Long id) {
        motoService.excluir(id);
        return "redirect:/motos";
    }
}
