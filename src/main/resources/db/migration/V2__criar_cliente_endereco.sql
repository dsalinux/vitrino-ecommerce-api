-- tabela cliente
CREATE TABLE cliente (
    id               INT AUTO_INCREMENT PRIMARY KEY,
    nome             VARCHAR(45)      NOT NULL,
    sobrenome        VARCHAR(45),
    email            VARCHAR(60)      NOT NULL UNIQUE,
    senha            VARCHAR(100),
    salt             VARCHAR(100),
    cpf              VARCHAR(45),
    telefone         VARCHAR(45),
    whatsapp         VARCHAR(45),
    data_nascimento  DATETIME,
    data_registro    TIMESTAMP        DEFAULT CURRENT_TIMESTAMP,
    data_bloqueio    DATETIME
);

-- tabela endereco
CREATE TABLE endereco (
    id          INT AUTO_INCREMENT PRIMARY KEY,
    descricao   VARCHAR(60),
    logradouro  VARCHAR(120),
    numero      INT,
    complemento VARCHAR(60),
    bairro      VARCHAR(45),
    cidade      VARCHAR(45),
    uf          VARCHAR(45),
    cep         VARCHAR(8),
    cliente_id  INT,
    CONSTRAINT fk_endereco_cliente
      FOREIGN KEY (cliente_id)
      REFERENCES cliente(id)
      ON DELETE CASCADE
);
