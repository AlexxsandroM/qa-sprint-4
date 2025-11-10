package br.com.fiap.eficientiza_challenge_03.service;

import br.com.fiap.eficientiza_challenge_03.model.HistoricoMoto;
import br.com.fiap.eficientiza_challenge_03.repository.HistoricoMotoRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HistoricoMotoService {

    private final HistoricoMotoRepository historicoMotoRepository;

    public HistoricoMotoService(HistoricoMotoRepository historicoMotoRepository) {
        this.historicoMotoRepository = historicoMotoRepository;
    }

    @Transactional
    public HistoricoMoto salvar(HistoricoMoto historicoMoto) {
        return historicoMotoRepository.save(historicoMoto);
    }

    @Transactional
    public HistoricoMoto atualizar(Long id, HistoricoMoto dados) {
        HistoricoMoto existente = buscarPorId(id);
        existente.setTipoAcao(dados.getTipoAcao());
        existente.setDataAcao(dados.getDataAcao());
        return historicoMotoRepository.save(existente);
    }

    public List<HistoricoMoto> listar() {
        return historicoMotoRepository.findAll();
    }

    public HistoricoMoto buscarPorId(Long id) {
        return historicoMotoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Histórico de Moto não encontrado: " + id));
    }

    @Transactional
    public void excluir(Long id) {
        if (!historicoMotoRepository.existsById(id)) {
            throw new IllegalArgumentException("Histórico de Moto não encontrado: " + id);
        }
        historicoMotoRepository.deleteById(id);
    }
}
