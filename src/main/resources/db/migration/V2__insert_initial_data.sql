-- v2_insert_data.sql

-- Inserting data into tb_mtt_moto (Motorcycle Table)
INSERT INTO tb_mtt_moto_c3_java (ds_placa, nm_modelo, ds_cor, nr_ano, ds_status, ds_vaga)
VALUES
    ('ABC1234', 'Honda CB500', 'Red', 2020, 'Available', 'V1'),
    ('XYZ5678', 'Yamaha YZF-R3', 'Blue', 2021, 'Occupied', 'V2'),
    ('LMN9876', 'Kawasaki Ninja', 'Green', 2022, 'Available', 'V3'),
    ('DEF4321', 'BMW S1000RR', 'Black', 2021, 'Available', 'V4'),
    ('JKL6543', 'Ducati Panigale', 'White', 2023, 'Occupied', 'V5');

-- Inserting data into tb_mtt_usuario (User Table)
INSERT INTO tb_mtt_usuario_c3_java (nm_usuario, ds_email, ds_senha, tp_usuario)
VALUES
    ('Alexsandro Silva', 'alex@fiap.com.br', '{noop}password123', 'ADMIN'),
    ('ADMIN', 'admin@gmail.com', '{noop}admin', 'ADMIN'),
    ('Carlos Pereira', 'carlos@fiap.com.br', '{noop}mypassword', 'ADMIN'),
    ('Felipe Santos', 'felipe@fiap.com.br', '{noop}securepass', 'ADMIN'),
    ('Joana Lima', 'joana@fiap.com.br', '{noop}pass1234', 'OPERADOR'),
    ('Operador Teste', 'operador@gmail.com', '{noop}operador', 'OPERADOR'),
    ('Eduardo Costa', 'eduardo@fiap.com.br', '{noop}12345', 'OPERADOR');

-- Inserting data into tb_mtt_historico_moto (Motorcycle History Table)
INSERT INTO tb_mtt_historico_moto_c3_java (id_moto, id_usuario, tp_acao, dt_acao)
VALUES
    (1, 1, 'Entrada', '2025-09-01'),
    (2, 2, 'Saída', '2025-09-02'),
    (3, 3, 'Entrada', '2025-09-03'),
    (4, 4, 'Saída', '2025-09-04'),
    (5, 5, 'Entrada', '2025-09-05');

-- Inserting data into tb_mtt_vaga (Parking Spot Table)
INSERT INTO tb_mtt_vaga_c3_java (id_moto, ds_status, ds_vaga)
VALUES
    (1, 'Disponivel', 'V1'),
    (2, 'Ocupada', 'V2'),
    (3, 'Manutenção', 'V3'),
    (4, 'Inativa', 'V4'),
    (5, 'Disponível', 'V5');
