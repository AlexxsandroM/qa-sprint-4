package br.com.fiap.eficientiza_challenge_03.controller;
import br.com.fiap.eficientiza_challenge_03.service.OcupacaoService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class OcupacaoController {

    private final OcupacaoService ocupacaoService;

    public OcupacaoController(OcupacaoService ocupacaoService) {
        this.ocupacaoService = ocupacaoService;
    }

    /**
     * Lista ocupações em página Thymeleaf com filtros opcionais.
     * Ex.: GET /ocupacoes?somenteAtivas=S&limit=20
     */
    @GetMapping
    public String listarView(
            @RequestParam(required = false) Integer estacaoId,
            @RequestParam(required = false) String somenteAtivas, // 'S' | 'N' | null
            @RequestParam(required = false, defaultValue = "100") Integer limit,
            Model model
    ) {
        List<OcupacaoService.OcupacaoDto> lista = ocupacaoService.listarOcupacoes(estacaoId, somenteAtivas, limit);

        model.addAttribute("lista", lista);
        // ecoa filtros para o formulário
        model.addAttribute("fEstacaoId", estacaoId);
        model.addAttribute("fSomenteAtivas", somenteAtivas);
        model.addAttribute("fLimit", limit);

        // se quiser também expor o JSON bruto (para debug/download)
        String jsonBruto = ocupacaoService.listarOcupacoesJson(estacaoId, somenteAtivas, limit);
        model.addAttribute("jsonBruto", jsonBruto);

        return "utils/utils"; // templates/ocupacoes/listar.html
    }

}
