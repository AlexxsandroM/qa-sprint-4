package br.com.fiap.eficientiza_challenge_03.service;


import br.com.fiap.eficientiza_challenge_03.repository.OcupacaoSpRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Locale;

@Service
public class OcupacaoService {

    private final OcupacaoSpRepository spRepository;
    private final ObjectMapper mapper;

    public OcupacaoService(OcupacaoSpRepository spRepository, ObjectMapper mapper) {
        this.spRepository = spRepository;
        this.mapper = mapper; // o Spring Boot já provê um ObjectMapper
    }

    /**
     * Chama a procedure e retorna o JSON bruto (string) conforme a SP entrega.
     * @param estacaoId       filtro opcional
     * @param somenteAtivas   'S' | 'N' | null  (null = todas)
     * @param limit           quantidade máxima (usa 100 se null/<=0)
     */
    public String listarOcupacoesJson(Integer estacaoId, String somenteAtivas, Integer limit) {
        String flag = normalizarFlagAtivas(somenteAtivas);
        Integer lim = (limit == null || limit <= 0) ? 100 : limit;
        return spRepository.listarOcupacoesJson(estacaoId, flag, lim);
    }

    /**
     * Chama a procedure e converte o JSON em uma lista tipada de DTOs.
     */
    public List<OcupacaoDto> listarOcupacoes(Integer estacaoId, String somenteAtivas, Integer limit) {
        String json = listarOcupacoesJson(estacaoId, somenteAtivas, limit);
        if (json == null || json.isBlank() || "[]".equals(json.trim())) {
            return Collections.emptyList();
        }
        try {
            return mapper.readValue(json, new TypeReference<List<OcupacaoDto>>() {});
        } catch (Exception e) {
            throw new RuntimeException("Falha ao converter o JSON da procedure em lista tipada.", e);
        }
    }

    private String normalizarFlagAtivas(String valor) {
        if (valor == null || valor.isBlank()) return null;
        String v = valor.trim().toUpperCase(Locale.ROOT);
        return (v.equals("S") || v.equals("N")) ? v : null;
    }

    // DTO aderente ao JSON retornado pela sua SP
    public record OcupacaoDto(
            Integer id_ocupacao,
            String dt_entrada,
            String dt_saida,
            Integer id_vaga,
            String ds_vaga,
            Integer id_estacao,
            String nm_estacao,
            Integer id_moto,
            String ds_placa,
            String nm_modelo,
            Integer id_usuario,
            String nm_usuario
    ) {}
}
