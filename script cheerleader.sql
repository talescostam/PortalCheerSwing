-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Tempo de geração: 07-Dez-2023 às 05:22
-- Versão do servidor: 10.4.22-MariaDB
-- versão do PHP: 8.1.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Banco de dados: `cheerleader`
--

-- --------------------------------------------------------

--
-- Estrutura da tabela `atleta`
--

CREATE TABLE `atleta` (
  `id` int(11) NOT NULL,
  `nome` varchar(55) NOT NULL,
  `idade` int(5) NOT NULL,
  `altura` double DEFAULT NULL,
  `ginasio_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Extraindo dados da tabela `atleta`
--

INSERT INTO `atleta` (`id`, `nome`, `idade`, `altura`, `ginasio_id`) VALUES
(1, 'Tales Costa', 32, 1.7, 1),
(2, 'Ilan Pires', 25, 3.88, 2),
(3, 'Guilherme', 25, 1.81, 2);

-- --------------------------------------------------------

--
-- Estrutura da tabela `campeonato`
--

CREATE TABLE `campeonato` (
  `id` int(11) NOT NULL,
  `nome` varchar(55) NOT NULL,
  `categoria` varchar(55) NOT NULL,
  `divisao` varchar(55) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Extraindo dados da tabela `campeonato`
--

INSERT INTO `campeonato` (`id`, `nome`, `categoria`, `divisao`) VALUES
(1, 'Cheerfest International', 'Internacional', 'All Star'),
(2, 'Campeonato Brasileiro de Cheerleading All Star', 'Nacional', 'All Star'),
(3, 'Campeonato Brasileiro de Cheerleading Universitário', 'Nacional', 'Universitária');

-- --------------------------------------------------------

--
-- Estrutura da tabela `ginasio`
--

CREATE TABLE `ginasio` (
  `id` int(11) NOT NULL,
  `nome` varchar(55) NOT NULL,
  `estado` varchar(55) NOT NULL,
  `municipio` varchar(55) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Extraindo dados da tabela `ginasio`
--

INSERT INTO `ginasio` (`id`, `nome`, `estado`, `municipio`) VALUES
(1, 'Centro de Treinamento de Cheerleading', 'SP', 'Mogi Mirim'),
(2, 'Arkhaios Allstars', 'RJ', 'Rio de Janeiro');

--
-- Índices para tabelas despejadas
--

--
-- Índices para tabela `atleta`
--
ALTER TABLE `atleta`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_ginasio_id` (`ginasio_id`);

--
-- Índices para tabela `campeonato`
--
ALTER TABLE `campeonato`
  ADD PRIMARY KEY (`id`);

--
-- Índices para tabela `ginasio`
--
ALTER TABLE `ginasio`
  ADD PRIMARY KEY (`id`);

--
-- Restrições para despejos de tabelas
--

--
-- Limitadores para a tabela `atleta`
--
ALTER TABLE `atleta`
  ADD CONSTRAINT `fk_ginasio_id` FOREIGN KEY (`ginasio_id`) REFERENCES `ginasio` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
