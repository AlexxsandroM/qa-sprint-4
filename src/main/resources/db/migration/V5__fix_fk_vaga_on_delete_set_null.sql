-- 1) Garantir que a coluna aceita NULL (H2)
ALTER TABLE tb_mtt_vaga_c3_java ALTER COLUMN id_moto SET NULL;

-- 2) Derrubar QUALQUER FK antiga VAGA→MOTO (nomes variam no H2)
ALTER TABLE tb_mtt_vaga_c3_java DROP CONSTRAINT IF EXISTS fk_vaga_moto;
ALTER TABLE tb_mtt_vaga_c3_java DROP CONSTRAINT IF EXISTS CONSTRAINT_97;
ALTER TABLE tb_mtt_vaga_c3_java DROP CONSTRAINT IF EXISTS CONSTRAINT_8D;
ALTER TABLE tb_mtt_vaga_c3_java DROP CONSTRAINT IF EXISTS CONSTRAINT_8c; -- (case varia)
ALTER TABLE tb_mtt_vaga_c3_java DROP CONSTRAINT IF EXISTS CONSTRAINT_8C;

-- 3) Recriar com regra certa
ALTER TABLE tb_mtt_vaga_c3_java
  ADD CONSTRAINT fk_vaga_moto
  FOREIGN KEY (id_moto)
  REFERENCES tb_mtt_moto_c3_java (id_moto)
  ON DELETE SET NULL;
