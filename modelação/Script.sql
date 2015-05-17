/* Base de Dados: 'sistema_financeiro' */
CREATE DATABASE IF NOT EXISTS sistema_financeiro;
USE sistema_financeiro;

/* Cria o usuário e senha */
CREATE USER 'user_financeiro'@'localhost' IDENTIFIED BY '008516';

/* Libera as permissões */
GRANT ALL PRIVILEGES ON sistema_financeiro.* TO 'user_financeiro'@'localhost' WITH GRANT OPTION;

/* Estrutura da tabela 'ramo_atividade' */
CREATE TABLE IF NOT EXISTS ramo_atividade (
  codigo INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  descricao VARCHAR(20) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=4;

/* Estrutura da tabela 'pessoa' */
CREATE TABLE IF NOT EXISTS pessoa (
  codigo INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  tipo VARCHAR(20) NOT NULL,
  nome VARCHAR(100) NOT NULL,
  email VARCHAR(100) NOT NULL,
  data_nascimento DATE,
  codigo_atividade INT,
  CONSTRAINT fk_pessoa_atividade FOREIGN KEY (codigo_atividade) REFERENCES ramo_atividade (codigo)
) ENGINE=InnoDB AUTO_INCREMENT=5;

/* Estrutura da tabela 'lancamento' */
CREATE TABLE IF NOT EXISTS lancamento (
  codigo INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  tipo VARCHAR(20) NOT NULL,
  codigo_pessoa INT NOT NULL,
  descricao VARCHAR(100) NOT NULL,
  valor DECIMAL(10,2) NOT NULL,
  data_vencimento DATE NOT NULL,
  pago bit NOT NULL,
  data_pagamento DATE,
  CONSTRAINT fk_lancamento_pessoa FOREIGN KEY (codigo_pessoa) REFERENCES pessoa (codigo)
) ENGINE=InnoDB AUTO_INCREMENT=5;

/* Estrutura da tabela 'usuario' */
CREATE TABLE IF NOT EXISTS usuario (
  nome_usuario VARCHAR(15) NOT NULL PRIMARY KEY,
  senha VARCHAR(32) NOT NULL
) ENGINE=InnoDB;

/* Estrutura da tabela 'permissao_usuario' */
CREATE TABLE IF NOT EXISTS permissao_usuario (
  nome_usuario VARCHAR(15) NOT NULL,
  nome_permissao VARCHAR(15) NOT NULL,
  PRIMARY KEY (nome_usuario,nome_permissao),
  CONSTRAINT fk_permissao_usuario FOREIGN KEY (nome_usuario) REFERENCES usuario (nome_usuario)
) ENGINE=InnoDB;

/* Extraindo dados da tabela 'ramo_atividade' */
INSERT INTO ramo_atividade (codigo, descricao) VALUES
(1, 'Academia'),
(2, 'Supermercado'),
(3, 'Restaurante');

/* Extraindo dados da tabela 'pessoa' */
INSERT INTO pessoa (codigo, tipo, nome, email, data_nascimento, codigo_atividade) VALUES
(1, 'FISICA', 'Alessandro Zucoloto', 'zucoloto@gmail.com', '1972-11-05', NULL),
(2, 'JURIDICA', 'Extra', 'extra@gmail.com', NULL, 2),
(3, 'JURIDICA', 'Status Academia', 'status@gmail.com', NULL, 1),
(4, 'JURIDICA', 'Tempero da Bahia', 'tempero.bahia@gmail.com', NULL, 3);

/* Extraindo dados da tabela 'lancamento' */
INSERT INTO lancamento (codigo, tipo, codigo_pessoa, descricao, valor, data_vencimento, pago, data_pagamento) VALUES
(1, 'RECEITA', 1, 'Salário', '10000.00', '2015-04-01', b'1', '2015-04-01'),
(2, 'DESPESA', 2, 'Compra do mês de ABR/15', '800.00', '2015-04-02', b'1', '2015-04-02'),
(3, 'DESPESA', 3, 'Mês de ABR15', '100.00', '2015-04-05', b'0', NULL),
(4, 'DESPESA', 4, 'Almoço', '50.00', '2015-04-10', b'0', NULL);

/* Extraindo dados da tabela 'usuario' */
INSERT INTO usuario (nome_usuario, senha) VALUES
('alessandro', '51af78a02435124ebc225e570e533ac9'),
('filha', '457b39dcd6adb2472f2ef06382c64901'),
('filho', 'f8678e212509848640218c2c737796fe'),
('monica', 'ff0d813dd5d2f64dd372c6c4b6aed086');

/* Extraindo dados da tabela 'permissao_usuario' */
INSERT INTO permissao_usuario (nome_usuario, nome_permissao) VALUES
('alessandro', 'cadastro'),
('alessandro', 'consulta'),
('filho', 'consulta'),
('monica', 'cadastro');