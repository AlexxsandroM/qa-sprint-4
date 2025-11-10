-- HISTÓRICO: refaz a FK com ON DELETE CASCADE
ALTER TABLE tb_mtt_historico_moto_c3_java
    DROP CONSTRAINT IF EXISTS fk_hist_moto;
ALTER TABLE tb_mtt_historico_moto_c3_java
    DROP CONSTRAINT IF EXISTS CONSTRAINT_8C;  -- nome auto do H2 (pode variar)

ALTER TABLE tb_mtt_historico_moto_c3_java
    ADD CONSTRAINT fk_hist_moto
    FOREIGN KEY (id_moto)
    REFERENCES tb_mtt_moto_c3_java (id_moto)
    ON DELETE CASCADE;

-- VAGA: permite soltar a moto (id_moto NULL) e aplica ON DELETE SET NULL
-- H2 (>=2.x) aceita "SET NULL"; em outras bases:
--   Postgres: ALTER TABLE ... ALTER COLUMN id_moto DROP NOT NULL;
--   H2:       ALTER TABLE ... ALTER COLUMN id_moto SET NULL;
ALTER TABLE tb_mtt_vaga_c3_java ALTER COLUMN id_moto SET NULL;

ALTER TABLE tb_mtt_vaga_c3_java
    DROP CONSTRAINT IF EXISTS fk_vaga_moto;
ALTER TABLE tb_mtt_vaga_c3_java
    DROP CONSTRAINT IF EXISTS CONSTRAINT_8D;  -- se existir nome auto

ALTER TABLE tb_mtt_vaga_c3_java
    ADD CONSTRAINT fk_vaga_moto
    FOREIGN KEY (id_moto)
    REFERENCES tb_mtt_moto_c3_java (id_moto)
    ON DELETE SET NULL;
