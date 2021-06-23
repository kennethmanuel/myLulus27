-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Waktu pembuatan: 05 Bulan Mei 2021 pada 22.34
-- Versi server: 8.0.23
-- Versi PHP: 7.4.11

SET FOREIGN_KEY_CHECKS=0;
SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `kelulusan_ubaya`
--
CREATE DATABASE IF NOT EXISTS `kelulusan_ubaya` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci;
USE `kelulusan_ubaya`;

-- --------------------------------------------------------

--
-- Struktur dari tabel `mahasiswa`
--

CREATE TABLE `mahasiswa` (
  `nrp` char(9) NOT NULL,
  `pin` char(8) NOT NULL,
  `nama` varchar(50) NOT NULL,
  `angkatan` smallint UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Struktur dari tabel `mahasiswa_ambil_mk`
--

CREATE TABLE `mahasiswa_ambil_mk` (
  `nrp` char(9) NOT NULL,
  `kode_mk` char(8) NOT NULL,
  `semester` enum('gasal','genap') DEFAULT NULL,
  `tahun_ambil` smallint UNSIGNED DEFAULT NULL,
  `nisbi` enum('A','AB','B','BC','C','D','E') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Struktur dari tabel `mk`
--

CREATE TABLE `mk` (
  `kode` char(8) NOT NULL,
  `nama` varchar(50) NOT NULL,
  `sks` tinyint UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data untuk tabel `mk`
--

INSERT INTO `mk` (`kode`, `nama`, `sks`) VALUES
('1604B011', 'Algorithm and Programming', 6),
('1604B021', 'Object Oriented Programming', 6),
('1604B031', 'Data Structure', 3),
('1604B041', 'Numerical Method', 2),
('1604B052', 'Native Mobile Programming', 3),
('1604B061', 'Kepemimpinan dan Etika Profesi', 2),
('1604B071', 'Research Methodology', 2),
('1608B081', 'Tugas Akhir', 5);

--
-- Indexes for dumped tables
--

--
-- Indeks untuk tabel `mahasiswa`
--
ALTER TABLE `mahasiswa`
  ADD PRIMARY KEY (`nrp`);

--
-- Indeks untuk tabel `mahasiswa_ambil_mk`
--
ALTER TABLE `mahasiswa_ambil_mk`
  ADD PRIMARY KEY (`nrp`,`kode_mk`),
  ADD KEY `fk_mahasiswa_has_mk_mk1_idx` (`kode_mk`),
  ADD KEY `fk_mahasiswa_has_mk_mahasiswa_idx` (`nrp`);

--
-- Indeks untuk tabel `mk`
--
ALTER TABLE `mk`
  ADD PRIMARY KEY (`kode`);

--
-- Ketidakleluasaan untuk tabel pelimpahan (Dumped Tables)
--

--
-- Ketidakleluasaan untuk tabel `mahasiswa_ambil_mk`
--
ALTER TABLE `mahasiswa_ambil_mk`
  ADD CONSTRAINT `fk_mahasiswa_has_mk_mahasiswa` FOREIGN KEY (`nrp`) REFERENCES `mahasiswa` (`nrp`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_mahasiswa_has_mk_mk1` FOREIGN KEY (`kode_mk`) REFERENCES `mk` (`kode`) ON DELETE CASCADE ON UPDATE CASCADE;
SET FOREIGN_KEY_CHECKS=1;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
