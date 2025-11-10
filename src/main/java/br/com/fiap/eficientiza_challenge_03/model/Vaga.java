package br.com.fiap.eficientiza_challenge_03.model;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_mtt_vaga_c3_java")
public class Vaga {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_vaga")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_moto", nullable = false)
    private Moto moto;

    @Column(name = "ds_status", nullable = false, length = 30)
    private String status;

    @Column(name = "ds_vaga", nullable = false, length = 30)
    private String descricaoVaga;

    public Vaga() {}

    public Vaga(Moto moto, String status, String descricaoVaga) {
        this.moto = moto;
        this.status = status;
        this.descricaoVaga = descricaoVaga;
    }

    // Getters e Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Moto getMoto() {
        return moto;
    }

    public void setMoto(Moto moto) {
        this.moto = moto;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescricaoVaga() {
        return descricaoVaga;
    }

    public void setDescricaoVaga(String descricaoVaga) {
        this.descricaoVaga = descricaoVaga;
    }
}
