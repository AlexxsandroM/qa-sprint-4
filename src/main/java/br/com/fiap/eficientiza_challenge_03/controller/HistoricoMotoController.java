package br.com.fiap.eficientiza_challenge_03.controller;

import br.com.fiap.eficientiza_challenge_03.model.HistoricoMoto;
import br.com.fiap.eficientiza_challenge_03.service.HistoricoMotoService;
import br.com.fiap.eficientiza_challenge_03.service.MotoService;
import br.com.fiap.eficientiza_challenge_03.service.UsuarioServiceMottu;
import br.com.fiap.eficientiza_challenge_03.service.VagaService;
import jakarta.validation.Valid;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/historicos-moto")
public class HistoricoMotoController {

    private final HistoricoMotoService historicoMotoService;
    private final MotoService motoService;
    private final VagaService vagaService;
    private final UsuarioServiceMottu usuarioServiceMottu;

    public HistoricoMotoController(HistoricoMotoService historicoMotoService,
                                   MotoService motoService,
                                   VagaService vagaService,
                                   UsuarioServiceMottu usuarioServiceMottu) {
        this.historicoMotoService = historicoMotoService;
        this.motoService = motoService;
        this.vagaService = vagaService;
        this.usuarioServiceMottu = usuarioServiceMottu;
    }

    // ------- helpers -------
    private static final List<String> ACOES_PADRAO = List.of("ENTRADA", "SAIDA", "TRANSFERENCIA");
    private static final List<String> STATUS_PADRAO = List.of("DISPONIVEL", "OCUPADA", "MANUTENCAO", "INATIVA");

    private void carregarApoio(Model model) {
        model.addAttribute("motos", motoService.listar());
        model.addAttribute("vagas", vagaService.listar());
        model.addAttribute("usuarios", usuarioServiceMottu.listar());

        // Se você já envia acoes/statuses pelo service, pode remover estas linhas:
        if (!model.containsAttribute("acoes"))   model.addAttribute("acoes", ACOES_PADRAO);
        if (!model.containsAttribute("statuses")) model.addAttribute("statuses", STATUS_PADRAO);
    }

    // ------- listagem -------
    @GetMapping
    public String listar(Model model) {
        model.addAttribute("historicos", historicoMotoService.listar());
        return "historico_moto/listagem";
    }

    // ------- novo -------
    @GetMapping("/novo")
    public String novo(Model model) {
        model.addAttribute("historicoMoto", new HistoricoMoto());
        carregarApoio(model);
        return "historico_moto/formulario";
    }

    // ------- salvar -------
    @PostMapping("/salvar")
    public String salvar(@Valid @ModelAttribute("historicoMoto") HistoricoMoto historicoMoto,
                         BindingResult binding,
                         Model model,
                         RedirectAttributes ra) {
        if (binding.hasErrors()) {
            carregarApoio(model);
            return "historico_moto/formulario";
        }
        historicoMotoService.salvar(historicoMoto);
        ra.addFlashAttribute("ok", "Histórico criado com sucesso.");
        return "redirect:/historicos-moto";
    }

    // ------- editar -------
    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        HistoricoMoto historico = historicoMotoService.buscarPorId(id);
        model.addAttribute("historicoMoto", historico);
        carregarApoio(model);
        return "historico_moto/formulario";
    }

    // ------- atualizar -------
    @PostMapping("/alterar/{id}")
    public String alterar(@PathVariable Long id,
                          @Valid @ModelAttribute("historicoMoto") HistoricoMoto historicoMoto,
                          BindingResult binding,
                          Model model,
                          RedirectAttributes ra) {
        if (binding.hasErrors()) {
            carregarApoio(model);
            return "historico_moto/formulario";
        }
        historicoMotoService.atualizar(id, historicoMoto);
        ra.addFlashAttribute("ok", "Histórico atualizado com sucesso.");
        return "redirect:/historicos-moto";
    }

    // ------- excluir -------
    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable Long id, RedirectAttributes ra) {
        try {
            historicoMotoService.excluir(id);
            ra.addFlashAttribute("ok", "Histórico excluído com sucesso.");
        } catch (DataIntegrityViolationException e) {
            ra.addFlashAttribute("erro", "Não foi possível excluir: registro vinculado a outros dados.");
        }
        return "redirect:/historicos-moto";
    }
}
