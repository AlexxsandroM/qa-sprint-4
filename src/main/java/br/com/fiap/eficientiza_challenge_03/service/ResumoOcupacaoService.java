package br.com.fiap.eficientiza_challenge_03.service;

import br.com.fiap.eficientiza_challenge_03.repository.ResumoOcupacaoRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class ResumoOcupacaoService {

    private final ResumoOcupacaoRepository repo;

    public ResumoOcupacaoService(ResumoOcupacaoRepository repo) {
        this.repo = repo;
    }

    public List<LinhaResumo> listarResumo() {
        List<String> raw = repo.executarResumoRaw();
        List<LinhaResumo> out = new ArrayList<>();

        for (String line : raw) {
            String trimmed = line.trim();

            // ignora header e separadores
            if (trimmed.isBlank() || trimmed.toUpperCase().startsWith("CAT1_ESTACAO")) {
                continue;
            }

            // se depois você imprimir SUBTOTAL/TOTAL na procedure, dá pra detectar aqui:
            if (trimmed.toUpperCase().startsWith("SUBTOTAL")) {
                // exemplo esperado: "SUBTOTAL ESTACAO 1 | 12345.00"
                out.add(LinhaResumo.subtotal(trimmed));
                continue;
            }
            if (trimmed.toUpperCase().startsWith("TOTAL")) {
                out.add(LinhaResumo.total(trimmed));
                continue;
            }

            // linhas de detalhe: "estacao | vaga | minutos"
            String[] parts = trimmed.split("\\|");
            if (parts.length >= 3) {
                try {
                    Integer estacao = Integer.valueOf(parts[0].trim());
                    Integer vaga    = Integer.valueOf(parts[1].trim());
                    BigDecimal min  = new BigDecimal(parts[2].trim());
                    out.add(LinhaResumo.detalhe(estacao, vaga, min));
                } catch (Exception ignored) {
                    // linha inesperada — apenas pula
                }
            }
        }
        return out;
    }

    /** DTO com um "tipo" para permitir renderizar detalhe/subtotal/total diferente no Thymeleaf. */
    public static class LinhaResumo {
        public enum Tipo { DETALHE, SUBTOTAL, TOTAL }

        public final Tipo tipo;
        public final Integer estacao;
        public final Integer vaga;
        public final BigDecimal minutos;
        public final String raw; // para casos especiais (exibir como veio)

        private LinhaResumo(Tipo tipo, Integer estacao, Integer vaga, BigDecimal minutos, String raw) {
            this.tipo = tipo; this.estacao = estacao; this.vaga = vaga; this.minutos = minutos; this.raw = raw;
        }

        public static LinhaResumo detalhe(Integer estacao, Integer vaga, BigDecimal minutos) {
            return new LinhaResumo(Tipo.DETALHE, estacao, vaga, minutos, null);
        }
        public static LinhaResumo subtotal(String raw) {
            return new LinhaResumo(Tipo.SUBTOTAL, null, null, null, raw);
        }
        public static LinhaResumo total(String raw) {
            return new LinhaResumo(Tipo.TOTAL, null, null, null, raw);
        }
    }
}