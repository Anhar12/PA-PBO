-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 27, 2023 at 02:44 AM
-- Server version: 10.4.24-MariaDB
-- PHP Version: 8.1.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `dbtopup`
--

-- --------------------------------------------------------

--
-- Table structure for table `akun`
--

CREATE TABLE `akun` (
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `akun`
--

INSERT INTO `akun` (`username`, `password`) VALUES
('admin', 'admin');

-- --------------------------------------------------------

--
-- Table structure for table `game`
--

CREATE TABLE `game` (
  `nama` varchar(50) NOT NULL,
  `mataUang` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `game`
--

INSERT INTO `game` (`nama`, `mataUang`) VALUES
('Fifa Mobile', 'POINTS'),
('Free Fire MAX', 'Diamond'),
('PUBG Mobile', 'UC');

-- --------------------------------------------------------

--
-- Table structure for table `pesanan`
--

CREATE TABLE `pesanan` (
  `id` int(10) NOT NULL,
  `jumlah` int(10) NOT NULL,
  `harga` int(11) NOT NULL,
  `id_akun` varchar(50) NOT NULL,
  `nickname` varchar(50) NOT NULL,
  `game` varchar(50) NOT NULL,
  `waktu` varchar(50) DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `pesanan`
--

INSERT INTO `pesanan` (`id`, `jumlah`, `harga`, `id_akun`, `nickname`, `game`, `waktu`) VALUES
(50, 550, 300000, '00119922', 'SuperOdado', 'Fifa Mobile', 'Thu, Apr 27 2023 07:58:36'),
(51, 300, 75000, '10203040', 'BocilChrono', 'Free Fire MAX', 'Thu, Apr 27 2023 08:03:02');

-- --------------------------------------------------------

--
-- Table structure for table `varian`
--

CREATE TABLE `varian` (
  `game` varchar(50) NOT NULL,
  `harga` int(10) NOT NULL,
  `id` int(10) NOT NULL,
  `jumlah` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `varian`
--

INSERT INTO `varian` (`game`, `harga`, `id`, `jumlah`) VALUES
('Fifa Mobile', 25000, 2, 30),
('Free Fire MAX', 30000, 3, 35),
('Fifa Mobile', 60000, 6, 125),
('Free Fire MAX', 35000, 7, 50),
('PUBG Mobile', 35000, 9, 60),
('PUBG Mobile', 15000, 10, 30),
('PUBG Mobile', 55000, 11, 90),
('Fifa Mobile', 30000, 12, 60),
('PUBG Mobile', 30000, 17, 45),
('Fifa Mobile', 300000, 20, 550),
('Free Fire MAX', 75000, 21, 300);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `akun`
--
ALTER TABLE `akun`
  ADD PRIMARY KEY (`username`);

--
-- Indexes for table `game`
--
ALTER TABLE `game`
  ADD PRIMARY KEY (`nama`);

--
-- Indexes for table `pesanan`
--
ALTER TABLE `pesanan`
  ADD PRIMARY KEY (`id`),
  ADD KEY `pesan` (`game`);

--
-- Indexes for table `varian`
--
ALTER TABLE `varian`
  ADD PRIMARY KEY (`id`),
  ADD KEY `TopUp` (`game`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `pesanan`
--
ALTER TABLE `pesanan`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=52;

--
-- AUTO_INCREMENT for table `varian`
--
ALTER TABLE `varian`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `pesanan`
--
ALTER TABLE `pesanan`
  ADD CONSTRAINT `pesan` FOREIGN KEY (`game`) REFERENCES `game` (`nama`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `varian`
--
ALTER TABLE `varian`
  ADD CONSTRAINT `TopUp` FOREIGN KEY (`game`) REFERENCES `game` (`nama`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
