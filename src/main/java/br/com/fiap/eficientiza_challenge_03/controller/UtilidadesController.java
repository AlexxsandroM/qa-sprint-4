package br.com.fiap.eficientiza_challenge_03.controller;

import br.com.fiap.eficientiza_challenge_03.service.OcupacaoService;
import br.com.fiap.eficientiza_challenge_03.service.ResumoOcupacaoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/utilidades")
public class UtilidadesController {

    private final OcupacaoService ocupacaoService;
    private final ResumoOcupacaoService resumoOcupacaoService;

    public UtilidadesController(OcupacaoService ocupacaoService,
                                ResumoOcupacaoService resumoOcupacaoService) {
        this.ocupacaoService = ocupacaoService;
        this.resumoOcupacaoService = resumoOcupacaoService;
    }

    /**
     * Carrega a página de Utilidades com o resumo (seção 2) e,
     * opcionalmente, a listagem da seção 1 com valores padrão.
     */
    @GetMapping
    public String viewUtilidades(
            @RequestParam(required = false) Integer estacaoId,
            @RequestParam(required = false) String somenteAtivas, // 'S' | 'N' | null
            @RequestParam(required = false, defaultValue = "100") Integer limit,
            Model model
    ) {
        // Seção 2: sempre carrega o resumo
        model.addAttribute("resumo", resumoOcupacaoService.listarResumo());

        // Seção 1: carrega lista (permite abrir já com filtros via querystring)
        List<OcupacaoService.OcupacaoDto> lista = ocupacaoService.listarOcupacoes(estacaoId, somenteAtivas, limit);
        model.addAttribute("lista", lista);
        model.addAttribute("jsonBruto", ocupacaoService.listarOcupacoesJson(estacaoId, somenteAtivas, limit));

        // ecoa os filtros no form
        model.addAttribute("fEstacaoId", estacaoId);
        model.addAttribute("fSomenteAtivas", somenteAtivas);
        model.addAttribute("fLimit", limit);

        return "utils/utils"; // templates/utils.html
    }

    /**
     * Action do formulário da seção 1 (Listar Ocupações).
     * Recarrega a mesma página com os resultados + resumo da seção 2.
     */
    @GetMapping("/ocupacoes")
    public String buscarOcupacoes(
            @RequestParam(required = false) Integer estacaoId,
            @RequestParam(required = false) String somenteAtivas, // 'S' | 'N' | null
            @RequestParam(required = false, defaultValue = "100") Integer limit,
            Model model
    ) {
        // Reaproveita a mesma montagem da view
        return viewUtilidades(estacaoId, somenteAtivas, limit, model);
    }
}