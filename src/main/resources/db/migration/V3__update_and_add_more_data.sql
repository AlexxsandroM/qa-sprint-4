-- Inserting additional data into tb_mtt_moto (Motorcycle Table)
INSERT INTO tb_mtt_moto_c3_java (ds_placa, nm_modelo, ds_cor, nr_ano, ds_status, ds_vaga)
VALUES
    ('PQR3210', 'Suzuki GSX-R1000', 'Yellow', 2024, 'Available', 'V6'),
    ('VWX5432', 'Honda CRF250L', 'Red', 2023, 'Occupied', 'V7'),
    ('STU8765', 'KTM 390 Duke', 'Orange', 2022, 'Available', 'V8');

-- Inserting additional data into tb_mtt_usuario (User Table)
INSERT INTO tb_mtt_usuario_c3_java (nm_usuario, ds_email, ds_senha, tp_usuario)
VALUES
    ('Lucas Oliveira', 'lucas@fiap.com.br', 'lucas123', 'OPERADOR'),
    ('Renata Alves', 'renata@fiap.com.br', 'renata2025', 'ADMIN');

-- Updating existing records in tb_mtt_moto (Motorcycle Table)
UPDATE tb_mtt_moto_c3_java
SET ds_status = 'Occupied', ds_vaga = 'V2'
WHERE ds_placa = 'XYZ5678';  -- Changing the status and parking spot of the Yamaha YZF-R3

UPDATE tb_mtt_moto_c3_java
SET ds_status = 'Available', ds_vaga = 'V4'
WHERE ds_placa = 'DEF4321';  -- Changing the status and parking spot of the BMW S1000RR

-- Updating existing records in tb_mtt_usuario (User Table)
UPDATE tb_mtt_usuario_c3_java
SET tp_usuario = 'Admin'
WHERE ds_email = 'felipe@fiap.com.br';  -- Changing Felipe Santos to Admin

-- Updating existing records in tb_mtt_historico_moto (Motorcycle History Table)
UPDATE tb_mtt_historico_moto_c3_java
SET tp_acao = 'Saída', dt_acao = '2025-09-06'
WHERE id_moto = 1 AND id_usuario = 1;  -- Updating the action for the Honda CB500 by Alexsandro Silva

UPDATE tb_mtt_historico_moto_c3_java
SET tp_acao = 'Entrada', dt_acao = '2025-09-06'
WHERE id_moto = 2 AND id_usuario = 2;  -- Updating the action for the Yamaha YZF-R3 by Carlos Pereira

-- Inserting more data into tb_mtt_historico_moto (Motorcycle History Table)
INSERT INTO tb_mtt_historico_moto_c3_java (id_moto, id_usuario, tp_acao, dt_acao)
VALUES
    (6, 6, 'Entrada', '2025-09-07'),  -- New Suzuki GSX-R1000 by Lucas Oliveira
    (7, 7, 'Saída', '2025-09-08');    -- New Honda CRF250L by Renata Alves

-- Inserting more data into tb_mtt_vaga (Parking Spot Table)
INSERT INTO tb_mtt_vaga_c3_java (id_moto, ds_status, ds_vaga)
VALUES
    (6, 'Available', 'V6'),  -- Suzuki GSX-R1000
    (7, 'Occupied', 'V7');   -- Honda CRF250L

-- Updating parking spot status
UPDATE tb_mtt_vaga_c3_java
SET ds_status = 'Available'
WHERE id_vaga = 2;  -- Setting V2 (Yamaha YZF-R3) to Available

UPDATE tb_mtt_vaga_c3_java
SET ds_status = 'Occupied'
WHERE id_vaga = 4;  -- Setting V4 (BMW S1000RR) to Occupied
