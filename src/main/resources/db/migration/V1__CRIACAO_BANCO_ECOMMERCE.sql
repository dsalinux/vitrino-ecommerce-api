-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema vitrino_ecommerce
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema vitrino_ecommerce
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `vitrino_ecommerce` ;
USE `vitrino_ecommerce` ;

-- -----------------------------------------------------
-- Table `vitrino_ecommerce`.`usuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `vitrino_ecommerce`.`usuario` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NOT NULL,
  `email` VARCHAR(60) NOT NULL,
  `senha` VARCHAR(100) NOT NULL,
  `salt` VARCHAR(45) NOT NULL,
  `data_registro` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
  `data_desativacao` DATETIME NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `vitrino_ecommerce`.`permissao`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `vitrino_ecommerce`.`permissao` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NOT NULL,
  `descricao` VARCHAR(100) NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `nome_UNIQUE` (`nome` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `vitrino_ecommerce`.`usuario_has_permissao`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `vitrino_ecommerce`.`usuario_has_permissao` (
  `usuario_id` INT NOT NULL,
  `permissao_id` INT NOT NULL,
  PRIMARY KEY (`usuario_id`, `permissao_id`),
  INDEX `fk_usuario_has_permissao_permissao1_idx` (`permissao_id` ASC) VISIBLE,
  INDEX `fk_usuario_has_permissao_usuario_idx` (`usuario_id` ASC) VISIBLE,
  CONSTRAINT `fk_usuario_has_permissao_usuario`
    FOREIGN KEY (`usuario_id`)
    REFERENCES `vitrino_ecommerce`.`usuario` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_usuario_has_permissao_permissao1`
    FOREIGN KEY (`permissao_id`)
    REFERENCES `vitrino_ecommerce`.`permissao` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `vitrino_ecommerce`.`cliente`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `vitrino_ecommerce`.`cliente` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NOT NULL,
  `sobrenome` VARCHAR(45) NULL,
  `email` VARCHAR(60) NOT NULL,
  `senha` VARCHAR(100) NOT NULL,
  `salt` VARCHAR(100) NOT NULL,
  `cpf` VARCHAR(45) NOT NULL,
  `telefone` VARCHAR(45) NOT NULL,
  `whatsapp` VARCHAR(45) NULL,
  `data_nascimento` DATETIME NOT NULL,
  `data_registro` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
  `data_bloqueio` DATETIME NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE,
  UNIQUE INDEX `cpf_UNIQUE` (`cpf` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `vitrino_ecommerce`.`endereco`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `vitrino_ecommerce`.`endereco` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `descricao` VARCHAR(60) NULL,
  `logradouro` VARCHAR(120) NOT NULL,
  `numero` INT NULL,
  `complemento` VARCHAR(60) NULL,
  `bairro` VARCHAR(45) NOT NULL,
  `cidade` VARCHAR(45) NOT NULL,
  `uf` VARCHAR(2) NOT NULL,
  `cep` VARCHAR(8) NOT NULL,
  `cliente_id` INT NOT NULL,
  PRIMARY KEY (`id`, `cliente_id`),
  INDEX `fk_endereco_cliente1_idx` (`cliente_id` ASC) VISIBLE,
  CONSTRAINT `fk_endereco_cliente1`
    FOREIGN KEY (`cliente_id`)
    REFERENCES `vitrino_ecommerce`.`cliente` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `vitrino_ecommerce`.`categoria`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `vitrino_ecommerce`.`categoria` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NOT NULL,
  `categoria_id` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_categoria_categoria1_idx` (`categoria_id` ASC) VISIBLE,
  CONSTRAINT `fk_categoria_categoria1`
    FOREIGN KEY (`categoria_id`)
    REFERENCES `vitrino_ecommerce`.`categoria` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `vitrino_ecommerce`.`marca`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `vitrino_ecommerce`.`marca` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `marca` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `vitrino_ecommerce`.`produto`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `vitrino_ecommerce`.`produto` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NOT NULL,
  `descricao` VARCHAR(45) NULL,
  `detalhes` LONGTEXT NULL,
  `valor` DECIMAL(9,2) NOT NULL,
  `categoria_id` INT NOT NULL,
  `marca_id` INT NOT NULL,
  `quantidade_em_estoque` FLOAT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_produto_categoria_idx` (`categoria_id` ASC) VISIBLE,
  INDEX `fk_produto_marca_idx` (`marca_id` ASC) VISIBLE,
  CONSTRAINT `fk_produto_categoria`
    FOREIGN KEY (`categoria_id`)
    REFERENCES `vitrino_ecommerce`.`categoria` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_produto_marca`
    FOREIGN KEY (`marca_id`)
    REFERENCES `vitrino_ecommerce`.`marca` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `vitrino_ecommerce`.`tag`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `vitrino_ecommerce`.`tag` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `vitrino_ecommerce`.`tag_has_produto`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `vitrino_ecommerce`.`tag_has_produto` (
  `tag_id` INT NOT NULL,
  `produto_id` INT NOT NULL,
  PRIMARY KEY (`tag_id`, `produto_id`),
  INDEX `fk_tag_has_produto_produto1_idx` (`produto_id` ASC) VISIBLE,
  INDEX `fk_tag_has_produto_tag1_idx` (`tag_id` ASC) VISIBLE,
  CONSTRAINT `fk_tag_has_produto_tag1`
    FOREIGN KEY (`tag_id`)
    REFERENCES `vitrino_ecommerce`.`tag` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_tag_has_produto_produto1`
    FOREIGN KEY (`produto_id`)
    REFERENCES `vitrino_ecommerce`.`produto` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `vitrino_ecommerce`.`pedido`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `vitrino_ecommerce`.`pedido` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `cliente_id` INT NOT NULL,
  `data_registro` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
  `endereco_entrega_id` INT NOT NULL,
  `data_cancelamento` DATETIME NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_pedido_cliente_idx` (`cliente_id` ASC) VISIBLE,
  INDEX `fk_pedido_edereco_idx` (`endereco_entrega_id` ASC) VISIBLE,
  CONSTRAINT `fk_pedido_cliente`
    FOREIGN KEY (`cliente_id`)
    REFERENCES `vitrino_ecommerce`.`cliente` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_pedido_edereco`
    FOREIGN KEY (`endereco_entrega_id`)
    REFERENCES `vitrino_ecommerce`.`endereco` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `vitrino_ecommerce`.`item_pedido`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `vitrino_ecommerce`.`item_pedido` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `pedido_id` INT NOT NULL,
  `produto_id` INT NOT NULL,
  `valor` DECIMAL(9,2) NOT NULL,
  `quantidade` FLOAT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_item_pedido_pedido_idx` (`pedido_id` ASC) VISIBLE,
  INDEX `fk_item_pedido_produto_idx` (`produto_id` ASC) VISIBLE,
  CONSTRAINT `fk_item_pedido_pedido`
    FOREIGN KEY (`pedido_id`)
    REFERENCES `vitrino_ecommerce`.`pedido` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_item_pedido_produto`
    FOREIGN KEY (`produto_id`)
    REFERENCES `vitrino_ecommerce`.`produto` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `vitrino_ecommerce`.`forma_pagamento`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `vitrino_ecommerce`.`forma_pagamento` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `vitrino_ecommerce`.`fluxo_caixa`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `vitrino_ecommerce`.`fluxo_caixa` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `descricao` VARCHAR(45) NOT NULL,
  `data_registro` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
  `valor` DECIMAL(9,2) NOT NULL,
  `tipo_transacao` VARCHAR(45) NOT NULL,
  `forma_pagamento_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_fluxo_caixa_forma_pagamento1_idx` (`forma_pagamento_id` ASC) VISIBLE,
  CONSTRAINT `fk_fluxo_caixa_forma_pagamento1`
    FOREIGN KEY (`forma_pagamento_id`)
    REFERENCES `vitrino_ecommerce`.`forma_pagamento` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `vitrino_ecommerce`.`pedido_has_fluxo_caixa`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `vitrino_ecommerce`.`pedido_has_fluxo_caixa` (
  `pedido_id` INT NOT NULL,
  `fluxo_caixa_id` INT NOT NULL,
  PRIMARY KEY (`pedido_id`, `fluxo_caixa_id`),
  INDEX `fk_pedido_has_fluxo_caixa_fluxo_caixa1_idx` (`fluxo_caixa_id` ASC) VISIBLE,
  INDEX `fk_pedido_has_fluxo_caixa_pedido1_idx` (`pedido_id` ASC) VISIBLE,
  CONSTRAINT `fk_pedido_has_fluxo_caixa_pedido1`
    FOREIGN KEY (`pedido_id`)
    REFERENCES `vitrino_ecommerce`.`pedido` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_pedido_has_fluxo_caixa_fluxo_caixa1`
    FOREIGN KEY (`fluxo_caixa_id`)
    REFERENCES `vitrino_ecommerce`.`fluxo_caixa` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
