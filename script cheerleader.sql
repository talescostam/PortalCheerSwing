--
-- Banco de dados: `cheerleader`
--
CREATE DATABASE cheerleader;

-- --------------------------------------------------------

--
-- Estrutura da tabela `atleta`
--

CREATE TABLE `atleta` (
  `id` int(11) NOT NULL,
  `nome` varchar(55) NOT NULL,
  `idade` int(5) NOT NULL,
  `altura` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Extraindo dados da tabela `atleta`
--

INSERT INTO `atleta` (`id`, `nome`, `idade`, `altura`) VALUES
(0, 'Guilherme Menezes', 26, 1.8),
(1, 'Tales Costa', 32, 1.7),
(2, 'Ilan Pires', 25, 3.88);

--
-- Índices para tabela `atleta`
--
ALTER TABLE `atleta`
  ADD PRIMARY KEY (`id`);
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

--
-- Índices para tabela `campeonato`
--
ALTER TABLE `campeonato`
  ADD PRIMARY KEY (`id`);

-- --------------------------------------------------------

--
-- Estrutura da tabela `ginasio`
--

CREATE TABLE `ginasio` (
  `id` int(11) NOT NULL,
  `nome` varchar(55) NOT NULL,
  `estado` varchar(2) NOT NULL,
  `municipio` varchar(55) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Extraindo dados da tabela `ginasio`
--

INSERT INTO `ginasio` (`id`, `nome`, `estado`, `municipio`) VALUES
(1, 'Centro de Treinamento de Cheerleading', 'SP', 'Mogi Mirim'),
(2, 'Arkhaios Allstars', 'RJ', 'Rio de Janeiro');

--
-- Índices para tabela `ginasio`
--
ALTER TABLE `ginasio`
  ADD PRIMARY KEY (`id`);