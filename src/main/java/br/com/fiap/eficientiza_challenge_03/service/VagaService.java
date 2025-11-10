package br.com.fiap.eficientiza_challenge_03.service;

import br.com.fiap.eficientiza_challenge_03.model.Vaga;
import br.com.fiap.eficientiza_challenge_03.repository.VagaRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VagaService {

    private final VagaRepository vagaRepository;

    public VagaService(VagaRepository vagaRepository) {
        this.vagaRepository = vagaRepository;
    }

    @Transactional
    public Vaga salvar(Vaga vaga) {
        return vagaRepository.save(vaga);
    }

    @Transactional
    public Vaga atualizar(Long id, Vaga dados) {
        Vaga existente = buscarPorId(id);
        existente.setStatus(dados.getStatus());
        existente.setDescricaoVaga(dados.getDescricaoVaga());
        return vagaRepository.save(existente);
    }

    public List<Vaga> listar() {
        return vagaRepository.findAll();
    }

    public Vaga buscarPorId(Long id) {
        return vagaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Vaga não encontrada: " + id));
    }

    @Transactional
    public void excluir(Long id) {
        if (!vagaRepository.existsById(id)) {
            throw new IllegalArgumentException("Vaga não encontrada: " + id);
        }
        vagaRepository.deleteById(id);
    }
}
