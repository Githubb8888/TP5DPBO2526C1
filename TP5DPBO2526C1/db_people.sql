-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Хост: localhost
-- Время создания: Окт 16 2026 г., 10:54
-- Версия сервера: 10.4.28-MariaDB
-- Версия PHP: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- База данных: `db_people`
--

-- --------------------------------------------------------

--
-- Структура таблицы `person`
--

CREATE TABLE `person` (
  `id` varchar(20) NOT NULL,
  `nama` varchar(100) NOT NULL,
  `tahun_lahir` int(11) NOT NULL,
  `kategori` varchar(50) NOT NULL,
  `kota` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Дамп данных таблицы `person`
--

INSERT INTO `person` (`id`, `nama`, `tahun_lahir`, `kategori`, `kota`) VALUES
('12345', 'Husein', 2007, 'Mahasiswa', 'Bandung'),
('P001', 'Budi Santoso', 2003, 'Mahasiswa', 'Bandung'),
('P002', 'Siti Aminah', 1985, 'Dosen', 'Jakarta'),
('P003', 'Rian Pratama', 1995, 'Staf', 'Surabaya'),
('P004', 'Dewi Lestari', 2001, 'Alumni', 'Yogyakarta');

--
-- Индексы сохранённых таблиц
--

--
-- Индексы таблицы `person`
--
ALTER TABLE `person`
  ADD PRIMARY KEY (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
