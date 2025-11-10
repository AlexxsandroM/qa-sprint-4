package br.com.fiap.eficientiza_challenge_03.repository;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.*;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import java.sql.Clob;
import java.sql.Types;
import java.util.Map;

@Repository
public class OcupacaoSpRepository {

    private final JdbcTemplate oracleJdbc;

    public OcupacaoSpRepository(@Qualifier("oracleJdbcTemplate") JdbcTemplate oracleJdbc) {
        this.oracleJdbc = oracleJdbc;
    }

    public String listarOcupacoesJson(Integer estacaoId, String somenteAtivas, Integer limit) {
        SimpleJdbcCall call = new SimpleJdbcCall(oracleJdbc)
                .withProcedureName("prc_listar_ocupacoes_json")
                .declareParameters(
                        new SqlParameter("p_estacao_id", Types.NUMERIC),
                        new SqlParameter("p_somente_ativas", Types.CHAR),
                        new SqlParameter("p_limit", Types.INTEGER),
                        new SqlOutParameter("p_json_out", Types.CLOB)
                );

        Map<String, Object> out = call.execute(estacaoId, somenteAtivas, limit);
        Clob clob = (Clob) out.get("p_json_out");

        try {
            return clob != null ? clob.getSubString(1, (int) clob.length()) : "[]";
        } catch (Exception e) {
            throw new RuntimeException("Erro lendo CLOB da procedure", e);
        }
    }
}