package br.com.fiap.eficientiza_challenge_03.repository;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ResumoOcupacaoRepository {

    private final DataSource oracleDataSource;

    public ResumoOcupacaoRepository(@Qualifier("oracleDataSource") DataSource oracleDataSource) { // injete o datasource do Oracle (ou @Qualifier)
        this.oracleDataSource = oracleDataSource;
    }

    /** Executa prc_resumo_ocupacao_minutos e devolve as linhas do DBMS_OUTPUT em ordem. */
    public List<String> executarResumoRaw() {
        List<String> lines = new ArrayList<>();
        try (Connection con = oracleDataSource.getConnection()) {

            // habilita buffer do DBMS_OUTPUT (1 MB)
            try (CallableStatement cs = con.prepareCall("{call dbms_output.enable(?)}")) {
                cs.setInt(1, 1024 * 1024);
                cs.execute();
            }

            // executa a procedure
            try (CallableStatement cs = con.prepareCall("{call prc_resumo_ocupacao_minutos}")) {
                cs.execute();
            }

            // lê todas as linhas do buffer
            try (CallableStatement getLine = con.prepareCall("{call dbms_output.get_line(?,?)}")) {
                getLine.registerOutParameter(1, Types.VARCHAR); // linha
                getLine.registerOutParameter(2, Types.INTEGER); // status: 0=ok, 1=sem mais linhas
                while (true) {
                    getLine.execute();
                    int status = getLine.getInt(2);
                    if (status != 0) break;
                    String line = getLine.getString(1);
                    if (line != null && !line.isBlank()) {
                        lines.add(line);
                    }
                }
            }

            // opcional: desabilita
            try (CallableStatement cs = con.prepareCall("{call dbms_output.disable()}")) {
                cs.execute();
            }

            return lines;
        } catch (SQLException e) {
            throw new RuntimeException("Falha ao executar/ler DBMS_OUTPUT da procedure.", e);
        }
    }
}