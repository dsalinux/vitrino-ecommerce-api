INSERT INTO cliente (nome, sobrenome, email, senha, salt, cpf, telefone, whatsapp, data_nascimento, data_registro)
VALUES 
('João', 'Silva', 'joao@gmail.com', '1234', 'salt1', '11111111111', '34999999991', '34999999991', '1990-01-01', NOW()),
('Maria', 'Souza', 'maria@gmail.com', '1234', 'salt2', '22222222222', '34999999992', '34999999992', '1985-02-02', NOW()),
('Pedro', 'Lima', 'pedro@gmail.com', '1234', 'salt3', '33333333333', '34999999993', '34999999993', '1992-03-03', NOW()),
('Ana', 'Ferreira', 'ana@gmail.com', '1234', 'salt4', '44444444444', '34999999994', '34999999994', '1988-04-04', NOW()),
('Lucas', 'Mendes', 'lucas@gmail.com', '1234', 'salt5', '55555555555', '34999999995', '34999999995', '1995-05-05', NOW()),
('Juliana', 'Costa', 'juliana@gmail.com', '1234', 'salt6', '66666666666', '34999999996', '34999999996', '1991-06-06', NOW()),
('Carlos', 'Almeida', 'carlos@gmail.com', '1234', 'salt7', '77777777777', '34999999997', '34999999997', '1987-07-07', NOW()),
('Mariana', 'Oliveira', 'mariana@gmail.com', '1234', 'salt8', '88888888888', '34999999998', '34999999998', '1993-08-08', NOW()),
('Rafael', 'Rodrigues', 'rafael@gmail.com', '1234', 'salt9', '99999999999', '34999999999', '34999999999', '1994-09-09', NOW()),
('Fernanda', 'Gomes', 'fernanda@gmail.com', '1234', 'salt10', '00000000000', '34999999990', '34999999990', '1986-10-10', NOW());

INSERT INTO endereco (descricao, logradouro, numero, complemento, bairro, cidade, uf, cep, cliente_id)
VALUES 
('Casa', 'Rua A', '123', 'Apto 1', 'Centro', 'Uberaba', 'MG', '38000000', 1),
('Trabalho', 'Rua B', '456', 'Sala 2', 'Abadia', 'Uberaba', 'MG', '38000001', 2),
('Casa', 'Rua C', '789', '', 'Boa Vista', 'Uberaba', 'MG', '38000002', 3),
('Casa', 'Rua D', '321', 'Casa 1', 'Fabrício', 'Uberaba', 'MG', '38000003', 4),
('Trabalho', 'Rua E', '654', 'Sala 4', 'Olinda', 'Uberaba', 'MG', '38000004', 5),
('Casa', 'Rua F', '987', '', 'Gameleira', 'Uberaba', 'MG', '38000005', 6),
('Casa', 'Rua G', '111', '', 'Centro', 'Uberaba', 'MG', '38000006', 7),
('Casa', 'Rua H', '222', '', 'Boa Vista', 'Uberaba', 'MG', '38000007', 8),
('Casa', 'Rua I', '333', '', 'Abadia', 'Uberaba', 'MG', '38000008', 9),
('Trabalho', 'Rua J', '444', '', 'Olinda', 'Uberaba', 'MG', '38000009', 10);

INSERT INTO categoria (nome) VALUES 
('Eletrônicos'), ('Roupas'), ('Livros');

INSERT INTO marca (marca) VALUES 
('Samsung'), ('Nike'), ('Dell'), ('Altabooks');

INSERT INTO produto (nome, descricao, detalhes, valor, categoria_id, marca_id, quantidade_em_estoque)
VALUES 
('Smartphone Galaxy', 'Celular Samsung', 'Android 12, 128GB', 1500.00, 1, 1, 100),
('Notebook Dell', 'Notebook para trabalho', 'Intel i5, 8GB RAM, SSD 256GB', 3500.00, 1, 3, 50),
('Tênis Nike Air', 'Calçado esportivo', 'Tamanhos variados', 399.90, 2, 2, 200),
('Livro Java', 'Livro técnico', 'Programação com Java', 99.00, 3, 4, 500),
('Monitor 24"', 'Full HD', 'HDMI, VGA', 699.99, 1, 3, 30),
('Fone Bluetooth', 'Sem fio', '5h bateria', 199.00, 1, 1, 75),
('Camiseta Tech', 'Estampa moderna', 'Algodão', 49.90, 2, 2, 120),
('Livro Angular', 'Desenvolvimento web', 'Angular completo', 89.00, 3, 4, 40),
('Tablet Galaxy', 'Android', '10.1", 64GB', 1200.00, 1, 1, 60),
('Notebook Gamer', 'Alto desempenho', 'RTX 3060, 16GB RAM', 7999.00, 1, 3, 10);


INSERT INTO pedido (cliente_id, data_registro, endereco_entrega_id)
VALUES 
(1, NOW(), 1), (2, NOW(), 2), (3, NOW(), 3), (4, NOW(), 4), (5, NOW(), 5),
(6, NOW(), 6), (7, NOW(), 7), (8, NOW(), 8), (9, NOW(), 9), (10, NOW(), 10);

INSERT INTO item_pedido (pedido_id, produto_id, valor, quantidade)
VALUES 
(1, 1, 1500.00, 1),
(1, 6, 199.00, 2),
(2, 2, 3500.00, 1),
(3, 3, 399.90, 1),
(4, 4, 99.00, 2),
(5, 5, 699.99, 1),
(6, 7, 49.90, 3),
(7, 8, 89.00, 2),
(8, 9, 1200.00, 1),
(9, 10, 7999.00, 1);


INSERT INTO forma_pagamento (nome)
VALUES ('Dinheiro'), ('Cartão Crédito'), ('Cartão Débito'), ('Pix');

INSERT INTO fluxo_caixa (descricao, data_registro, valor, tipo_transacao, forma_pagamento_id)
VALUES 
('Venda Pedido 1', NOW(), 1898.00, 'Entrada', 1),
('Compra Produtos Informática', NOW(), -3500.00, 'Saída', 2),
('Venda Pedido 2', NOW(), 399.90, 'Entrada', 3),
('Venda Pedido 3', NOW(), 198.00, 'Entrada', 1),
('Pagamento Boleto', NOW(), -699.99, 'Saída', 2),
('Venda Pedido 4', NOW(), 149.70, 'Entrada', 1),
('Venda Pedido 5', NOW(), 178.00, 'Entrada', 4),
('Venda Pedido 6', NOW(), 1200.00, 'Entrada', 3),
('Venda Pedido 7', NOW(), 7999.00, 'Entrada', 2),
('Compra Produtos', NOW(), -9200.00, 'Saída', 1); -- saída exemplo

INSERT INTO pedido_has_fluxo_caixa (pedido_id, fluxo_caixa_id)
VALUES 
(1, 1), (2, 2), (3, 3), (4, 4), (5, 5),
(6, 6), (7, 7), (8, 8), (9, 9), (10, 10);
