-- v1_create_tables.sql

-- Table for Moto
CREATE TABLE tb_mtt_moto_c3_java (
                             id_moto BIGINT AUTO_INCREMENT PRIMARY KEY,
                             ds_placa VARCHAR(10) NOT NULL UNIQUE,
                             nm_modelo VARCHAR(100) NOT NULL,
                             ds_cor VARCHAR(30),
                             nr_ano INT,
                             ds_status VARCHAR(50),
                             ds_vaga VARCHAR(10) UNIQUE
);

-- Table for Usuario
CREATE TABLE tb_mtt_usuario_c3_java (
                                id_usuario BIGINT AUTO_INCREMENT PRIMARY KEY,
                                nm_usuario VARCHAR(100) NOT NULL,
                                ds_email VARCHAR(100) NOT NULL UNIQUE,
                                ds_senha VARCHAR(100) NOT NULL,
                                tp_usuario VARCHAR(50) NOT NULL
);

-- Table for HistoricoMoto
CREATE TABLE tb_mtt_historico_moto_c3_java (
                                       id_historico BIGINT AUTO_INCREMENT PRIMARY KEY,
                                       id_moto BIGINT NOT NULL,
                                       id_usuario BIGINT NOT NULL,
                                       tp_acao VARCHAR(20) NOT NULL,
                                       dt_acao DATE NOT NULL,
                                       FOREIGN KEY (id_moto) REFERENCES tb_mtt_moto_c3_java(id_moto),
                                       FOREIGN KEY (id_usuario) REFERENCES tb_mtt_usuario_c3_java(id_usuario)
);

-- Table for Vaga
CREATE TABLE tb_mtt_vaga_c3_java (
                             id_vaga BIGINT AUTO_INCREMENT PRIMARY KEY,
                             id_moto BIGINT NOT NULL,
                             ds_status VARCHAR(30) NOT NULL,
                             ds_vaga VARCHAR(30) NOT NULL,
                             FOREIGN KEY (id_moto) REFERENCES tb_mtt_moto_c3_java(id_moto)
);
