package br.com.fiap.eficientiza_challenge_03.service;

import br.com.fiap.eficientiza_challenge_03.model.Moto;
import br.com.fiap.eficientiza_challenge_03.repository.MotoRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MotoService {

    private final MotoRepository motoRepository;

    public MotoService(MotoRepository motoRepository) {
        this.motoRepository = motoRepository;
    }

    @Transactional
    public Moto salvar(Moto moto) {
        return motoRepository.save(moto);
    }

    @Transactional
    public Moto atualizar(Long id, Moto dados) {
        Moto existente = buscarPorId(id);
        existente.setPlaca(dados.getPlaca());
        existente.setModelo(dados.getModelo());
        existente.setCor(dados.getCor());
        existente.setAno(dados.getAno());
        existente.setStatus(dados.getStatus());
        existente.setVaga(dados.getVaga());
        return motoRepository.save(existente);
    }

    public List<Moto> listar() {
        return motoRepository.findAll();
    }

    public Moto buscarPorId(Long id) {
        return motoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Moto não encontrada: " + id));
    }

    @Transactional
    public void excluir(Long id) {
        if (!motoRepository.existsById(id)) {
            throw new IllegalArgumentException("Moto não encontrada: " + id);
        }
        motoRepository.deleteById(id);
    }
}
