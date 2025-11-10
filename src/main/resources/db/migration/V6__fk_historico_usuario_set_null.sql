-- 1) Tornar a coluna ID_USUARIO do histórico nullable
ALTER TABLE tb_mtt_historico_moto_c3_java
    ALTER COLUMN id_usuario SET NULL;

-- 2) Derrubar qualquer FK antiga do histórico → usuário (nomes variam no H2)
ALTER TABLE tb_mtt_historico_moto_c3_java DROP CONSTRAINT IF EXISTS fk_hist_usuario;
ALTER TABLE tb_mtt_historico_moto_c3_java DROP CONSTRAINT IF EXISTS CONSTRAINT_8CD;
ALTER TABLE tb_mtt_historico_moto_c3_java DROP CONSTRAINT IF EXISTS CONSTRAINT_8cd;

-- 3) Recriar com ON DELETE SET NULL
ALTER TABLE tb_mtt_historico_moto_c3_java
    ADD CONSTRAINT fk_hist_usuario
    FOREIGN KEY (id_usuario)
    REFERENCES tb_mtt_usuario_c3_java (id_usuario)
    ON DELETE SET NULL;
