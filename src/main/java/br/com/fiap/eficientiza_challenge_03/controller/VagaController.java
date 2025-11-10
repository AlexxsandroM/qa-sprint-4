package br.com.fiap.eficientiza_challenge_03.controller;

import br.com.fiap.eficientiza_challenge_03.model.Vaga;
import br.com.fiap.eficientiza_challenge_03.service.VagaService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/vagas")
public class VagaController {

    private final VagaService vagaService;

    public VagaController(VagaService vagaService) {
        this.vagaService = vagaService;
    }

    // Listagem
    @GetMapping
    public String listar(Model model) {
        List<Vaga> vagas = vagaService.listar();
        model.addAttribute("vagas", vagas);
        return "vagas/listagem";
    }

    // Formulário - nova vaga
    @GetMapping("/novo")
    public String nova(Model model) {
        model.addAttribute("vaga", new Vaga());
        return "vagas/formulario";
    }

    // Salvar
    @PostMapping("/salvar")
    public String salvar(@ModelAttribute @Valid Vaga vaga,
                         org.springframework.validation.BindingResult binding,
                         Model model) {
        if (binding.hasErrors()) {
            return "vagas/formulario";
        }
        vagaService.salvar(vaga);
        return "redirect:/vagas";
    }

    // Editar
    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        Vaga vaga = vagaService.buscarPorId(id);
        model.addAttribute("vaga", vaga);
        return "vagas/formulario";
    }

    // Atualizar
    @PostMapping("/alterar/{id}")
    public String alterar(@PathVariable Long id,
                          @ModelAttribute @Valid Vaga vaga,
                          org.springframework.validation.BindingResult binding,
                          Model model) {
        if (binding.hasErrors()) {
            return "vagas/formulario";
        }
        vagaService.atualizar(id, vaga);
        return "redirect:/vagas";
    }

    // Excluir
    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable Long id) {
        vagaService.excluir(id);
        return "redirect:/vagas";
    }
}
