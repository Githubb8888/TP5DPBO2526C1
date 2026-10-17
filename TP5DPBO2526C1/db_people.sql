

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";




CREATE TABLE `person` (
  `id` varchar(20) NOT NULL,
  `nama` varchar(100) NOT NULL,
  `tahun_lahir` int(11) NOT NULL,
  `kategori` varchar(50) NOT NULL,
  `kota` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


INSERT INTO `person` (`id`, `nama`, `tahun_lahir`, `kategori`, `kota`) VALUES
('12345', 'Husein', 2007, 'Mahasiswa', 'Bandung'),
('P001', 'Budi Santoso', 2003, 'Mahasiswa', 'Bandung'),
('P002', 'Siti Aminah', 1985, 'Dosen', 'Jakarta'),
('P003', 'Rian Pratama', 1995, 'Staf', 'Surabaya'),
('P004', 'Dewi Lestari', 2001, 'Alumni', 'Yogyakarta');


ALTER TABLE `person`
  ADD PRIMARY KEY (`id`);
COMMIT;
