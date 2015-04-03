--
-- Base de Dados: `sistema_financeiro`
--
--
-- Estrutura da tabela `ramo_atividade`
--
create table if not exists ramo_atividade (
  codigo int not null auto_increment primary key,
  descricao varchar(20) not null
) engine=InnoDB auto_increment=4;
--
-- Estrutura da tabela `pessoa`
--
create table if not exists pessoa (
  codigo int not null auto_increment primary key,
  tipo varchar(20) not null,
  nome varchar(100) not null,
  email varchar(100) not null,
  data_nascimento date,
  codigo_atividade int,
  foreign key (codigo_atividade) references ramo_atividade (codigo)
) engine=InnoDB auto_increment=5;
--
-- Estrutura da tabela `lancamento`
--
create table if not exists lancamento (
  codigo int not null auto_increment primary key,
  tipo varchar(20) not null,
  codigo_pessoa int not null,
  descricao varchar(100) not null,
  valor decimal(10,2) not null,
  data_vencimento date not null,
  pago bit not null,
  data_pagamento date,
  foreign key (codigo_pessoa) references pessoa (codigo)
) engine=InnoDB auto_increment=5;
--
-- Estrutura da tabela `usuario`
--
create table if not exists usuario (
  nome_usuario varchar(15) not null primary key,
  senha varchar(32) NOT NULL
) engine=InnoDB;
--
-- Estrutura da tabela `permissao_usuario`
--
create table if not exists permissao_usuario (
  nome_usuario varchar(15) not null,
  nome_permissao varchar(15) not null,
  primary key (nome_usuario,nome_permissao),
  foreign key (nome_usuario) references usuario (nome_usuario)
) engine=InnoDB;

--
-- Extraindo dados da tabela `ramo_atividade`
--
insert into ramo_atividade (codigo, descricao) values
(1, 'Academia'),
(2, 'Supermercado'),
(3, 'Restaurante');
--
-- Extraindo dados da tabela `pessoa`
--
insert into pessoa (codigo, tipo, nome, email, data_nascimento, codigo_atividade) values
(1, 'FISICA', 'Alessandro Zucoloto', 'zucoloto@gmail.com', '1972-11-05', NULL),
(2, 'JURIDICA', 'Extra', 'extra@gmail.com', NULL, 2),
(3, 'JURIDICA', 'Status Academia', 'status@gmail.com', NULL, 1),
(4, 'JURIDICA', 'Tempero da Bahia', 'tempero.bahia@gmail.com', NULL, 3);
--
-- Extraindo dados da tabela `lancamento`
--
insert into lancamento (codigo, tipo, codigo_pessoa, descricao, valor, data_vencimento, pago, data_pagamento) values
(1, 'RECEITA', 1, 'Salário', '10000.00', '2015-04-01', b'1', '2015-04-01'),
(2, 'DESPESA', 2, 'Compra do mês de ABR/15', '800.00', '2015-04-02', b'1', '2015-04-02'),
(3, 'DESPESA', 3, 'Mês de ABR15', '100.00', '2015-04-05', b'0', NULL),
(4, 'DESPESA', 4, 'Almoço', '50.00', '2015-04-10', b'0', NULL);
--
-- Extraindo dados da tabela `usuario`
--
insert into usuario (nome_usuario, senha) values
('alessandro', '51af78a02435124ebc225e570e533ac9'),
('filha', '457b39dcd6adb2472f2ef06382c64901'),
('filho', 'f8678e212509848640218c2c737796fe'),
('monica', 'ff0d813dd5d2f64dd372c6c4b6aed086');
--
-- Extraindo dados da tabela `permissao_usuario`
--
insert into permissao_usuario (nome_usuario, nome_permissao) values
('alessandro', 'cadastro'),
('alessandro', 'consulta'),
('filho', 'consulta'),
('monica', 'cadastro');