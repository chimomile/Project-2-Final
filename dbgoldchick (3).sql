-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Waktu pembuatan: 17 Bulan Mei 2024 pada 15.07
-- Versi server: 10.4.28-MariaDB
-- Versi PHP: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `dbgoldchick`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `employee`
--

CREATE TABLE `employee` (
  `id` int(11) NOT NULL,
  `nama` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `employee`
--

INSERT INTO `employee` (`id`, `nama`, `username`, `password`) VALUES
(1, '/images/Rice + Spicy Fried Chicken.png', 'Rice + Spicy Fried Chicken', '11111'),
(2, 'Baskara', 'Baskara', 'Bas123'),
(3, 'Lolly', 'Lolly', 'lol123'),
(4, 'Nasa', 'Nasa', 'nas123'),
(5, 'Leoq', 'Leo', 'le123'),
(10, 'Techii', 'Pocyy', 'teci123'),
(12, 'leann', 'lele23', '123'),
(13, 'tewci', 'powci', '123'),
(22, 'fatih', 'fatihh', '123');

-- --------------------------------------------------------

--
-- Struktur dari tabel `menu`
--

CREATE TABLE `menu` (
  `menu_id` int(11) NOT NULL,
  `img_path` varchar(255) DEFAULT NULL,
  `menu_name` varchar(255) DEFAULT NULL,
  `menu_price` int(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `menu`
--

INSERT INTO `menu` (`menu_id`, `img_path`, `menu_name`, `menu_price`) VALUES
(1, '/images/Rice + Spicy Fried Chicken.png', 'Rice + Spicy Fried Chicken', 20000),
(2, '/images/Rice + Spicy Fried Chicken with Green Chili.png', 'Rice + Spicy Fried Chicken with Green Chili', 25000),
(3, '/images/Rice + Spicy Fried Chicken with Matah Chili.png', 'Rice + Spicy Fried Chicken with Matah Chili', 20000),
(4, '/images/Rice + Spicy Fried Chicken with Shrimp Paste Sauce.png', 'Rice + Spicy Fried Chicken with Shrimp Paste Sauce', 20000),
(5, '/images/Mozzarella Cheese.png', 'Mozzarella Cheese', 7000),
(6, '/images/Fried Cabbage.png', 'Fried Cabbage', 8000),
(7, '/images/Rice.png', 'Rice Only', 6000),
(8, '/images/Mineral Water.png', 'Mineral Water', 5000),
(9, '/images/Iced Tea.png', 'Iced Tea', 4000);

-- --------------------------------------------------------

--
-- Struktur dari tabel `transactiondetails`
--

CREATE TABLE `transactiondetails` (
  `id` int(11) NOT NULL,
  `time` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `itemName` varchar(255) DEFAULT NULL,
  `purchaseId` int(11) DEFAULT NULL,
  `adminName` varchar(50) DEFAULT NULL,
  `total` decimal(10,2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `transactiondetails`
--

INSERT INTO `transactiondetails` (`id`, `time`, `itemName`, `purchaseId`, `adminName`, `total`) VALUES
(25, '2024-05-12 09:59:24', 'Mineral Water, Rice + Spicy Fried Chicken with Green Chili', 70456, 'Leo', 35000.00),
(26, '2024-05-12 10:10:17', 'Rice + Spicy Fried Chicken with Matah Sauce, Mineral Water', 52899, 'Leo', 30000.00),
(27, '2024-05-12 12:25:03', 'Rice + Spicy Fried Chicken with Green Chili', 5551, 'Leo', 115000.00),
(28, '2024-05-12 15:58:24', 'Rice + Spicy Fried Chicken, Mozzarella Cheese', 71221, 'Leo', 52500.00),
(29, '2024-05-12 16:17:19', 'Rice + Spicy Fried Chicken, Mozzarella Cheese, Rice + Spicy Fried Chicken with Shrimp Paste Sauce', 11460, 'Leo', 105000.00),
(30, '2024-05-12 16:29:56', 'Rice + Spicy Fried Chicken with Green Chili, Fried Cabbage, Mozzarella Cheese', 21831, 'Leo', 96570.00),
(31, '2024-05-14 12:48:55', 'Mozzarella Cheese', 44022, 'Leo', 5550.00),
(32, '2024-05-17 10:42:24', 'Rice + Spicy Fried Chicken, Mozzarella Cheese, Iced Tea', 46873, 'Leoq', 36630.00),
(33, '2024-05-17 10:56:58', 'Rice + Spicy Fried Chicken			40, Rice + Spicy Fried Chicken with Green Chili			50, Rice + Spicy Fried Chicken with Matah Chili			80, Fried Cabbage			21', 44546, NULL, 212.01),
(34, '2024-05-17 12:48:04', 'Fried Cabbage (x1)			Rp, Rice + Spicy Fried Chicken (x1)			Rp, Rice + Spicy Fried Chicken with Green Chili (x1)			Rp, Mineral Water (x1)			Rp', 29627, NULL, 64380.00),
(35, '2024-05-17 12:52:15', '', 35603, 'Leoq', 126540.00),
(36, '2024-05-17 12:53:43', 'Rice + Spicy Fried Chicken with Green Chili (x2)			Rp', 46809, 'Leoq', 55500.00);

--
-- Indexes for dumped tables
--

--
-- Indeks untuk tabel `employee`
--
ALTER TABLE `employee`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `username` (`username`);

--
-- Indeks untuk tabel `menu`
--
ALTER TABLE `menu`
  ADD PRIMARY KEY (`menu_id`);

--
-- Indeks untuk tabel `transactiondetails`
--
ALTER TABLE `transactiondetails`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT untuk tabel yang dibuang
--

--
-- AUTO_INCREMENT untuk tabel `employee`
--
ALTER TABLE `employee`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=23;

--
-- AUTO_INCREMENT untuk tabel `menu`
--
ALTER TABLE `menu`
  MODIFY `menu_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT untuk tabel `transactiondetails`
--
ALTER TABLE `transactiondetails`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=37;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
