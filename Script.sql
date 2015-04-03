--
-- Base de Dados: `sistema_financeiro`
--
--
-- Estrutura da tabela `ramo_atividade`
--
create table if not exists ramo_atividade (
  codigo int not null auto_increment primary key,
  descricao varchar(20) not null
) ENGINE=InnoDB;
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
) ENGINE=InnoDB;
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
) ENGINE=InnoDB;
--
-- Estrutura da tabela `usuario`
--
create table if not exists usuario (
  nome_usuario varchar(15) not null primary key,
  senha varchar(32) NOT NULL
) ENGINE=InnoDB;
--
-- Estrutura da tabela `permissao_usuario`
--
create table if not exists permissao_usuario (
  nome_usuario varchar(15) not null,
  nome_permissao varchar(15) not null,
  primary key (nome_usuario,nome_permissao),
  foreign key (nome_usuario) references usuario (nome_usuario)
) ENGINE=InnoDB;

--
-- Extraindo dados da tabela `ramo_atividade`
--
insert into ramo_atividade (codigo, descricao) values
(1, 'Militar'),
(4, 'Professor(a)'),
(5, 'Motorista'),
(6, 'Supermercado');
--
-- Extraindo dados da tabela `pessoa`
--
insert into pessoa (codigo, tipo, nome, email, data_nascimento, codigo_atividade) values
(3, 'FISICA', 'Alessandro Zucoloto', 'zucoloto@gmail.com', '1972-11-05', NULL),
(4, 'JURIDICA', 'Extra', 'extra@gmail.com', NULL, 6),
(5, 'FISICA', 'Mônica Rodrigues de Almeida Zucoloto', 'monica@gmail.com', '1976-02-24', NULL),
(7, 'JURIDICA', 'Big Box', 'bigbox@gmail.com', NULL, 6),
(8, 'FISICA', 'Alessandro Zucoloto Filho', 'zucoloto.filho@gmail.com', '1995-07-26', NULL);
--
-- Extraindo dados da tabela `lancamento`
--
insert into lancamento (codigo, tipo, codigo_pessoa, descricao, valor, data_vencimento, pago, data_pagamento) values
(3, 'RECEITA', 3, 'Salário', '4000.00', '2015-02-01', b'1', '2015-02-02'),
(5, 'DESPESA', 4, 'Compra carne', '50.00', '2015-02-05', b'1', '2015-02-10'),
(7, 'DESPESA', 7, 'Verdura', '70.00', '2015-02-09', b'0', NULL),
(8, 'RECEITA', 5, 'Salário', '3000.00', '2015-02-15', b'0', NULL);
--
-- Extraindo dados da tabela `usuario`
--
insert into usuario (nome_usuario, senha) values
('joao', 'dccd96c256bc7dd39bae41a405f25e43'),
('maria', '263bce650e68ab4e23f28263760b9fa5'),
('sebastiao', '2b492cc70ad15ad496389e4d2ef4e8d2');
--
-- Extraindo dados da tabela `permissao_usuario`
--
insert into permissao_usuario (nome_usuario, nome_permissao) values
('joao', 'cadastro'),
('maria', 'consulta'),
('sebastiao', 'cadastro'),
('sebastiao', 'consulta');