-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Sep 09, 2021 at 01:19 PM
-- Server version: 10.4.19-MariaDB
-- PHP Version: 7.3.28

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `db_mobile_shop`
--

-- --------------------------------------------------------

--
-- Table structure for table `ct_phieudat`
--

DROP TABLE IF EXISTS `ct_phieudat`;
CREATE TABLE `ct_phieudat` (
  `MACTPD` int(10) UNSIGNED NOT NULL,
  `MAPD` int(10) UNSIGNED NOT NULL,
  `MASP` int(10) UNSIGNED NOT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `ct_phieudat`
--

INSERT INTO `ct_phieudat` (`MACTPD`, `MAPD`, `MASP`, `created_at`, `updated_at`) VALUES
(28, 22, 30, '2021-08-27 08:37:43', '2021-08-27 08:37:43'),
(29, 22, 31, '2021-08-27 08:37:43', '2021-08-27 08:37:43'),
(30, 22, 18, '2021-08-27 08:37:43', '2021-08-27 08:37:43'),
(31, 22, 19, '2021-08-27 08:37:43', '2021-08-27 08:37:43'),
(32, 22, 20, '2021-08-27 08:37:43', '2021-08-27 08:37:43'),
(33, 23, 21, '2021-08-29 04:28:09', '2021-08-29 04:28:09'),
(34, 24, 41, '2021-09-01 05:35:14', '2021-09-01 05:35:14'),
(35, 24, 22, '2021-09-01 05:35:14', '2021-09-01 05:35:14'),
(36, 25, 27, '2021-09-07 06:42:27', '2021-09-07 06:42:27');

-- --------------------------------------------------------

--
-- Table structure for table `ct_phieunhap`
--

DROP TABLE IF EXISTS `ct_phieunhap`;
CREATE TABLE `ct_phieunhap` (
  `MACTPN` int(10) UNSIGNED NOT NULL,
  `MAPN` int(10) UNSIGNED NOT NULL,
  `MADONGSP` int(10) UNSIGNED NOT NULL,
  `SOLUONG` int(11) NOT NULL,
  `GIA` double NOT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `ct_phieunhap`
--

INSERT INTO `ct_phieunhap` (`MACTPN`, `MAPN`, `MADONGSP`, `SOLUONG`, `GIA`, `created_at`, `updated_at`) VALUES
(39, 50, 18, 2, 0, '2021-08-19 16:48:43', '2021-08-19 16:48:43'),
(40, 50, 19, 3, 0, '2021-08-19 16:53:00', '2021-08-19 16:53:00'),
(41, 50, 20, 1, 0, '2021-08-19 16:58:05', '2021-08-19 16:58:05'),
(42, 50, 21, 2, 0, '2021-08-19 17:01:42', '2021-08-19 17:01:42'),
(43, 50, 22, 3, 0, '2021-08-19 17:06:54', '2021-08-19 17:06:54'),
(44, 50, 23, 3, 0, '2021-08-19 17:10:42', '2021-08-19 17:10:42'),
(45, 51, 22, 1, 0, '2021-08-19 17:15:42', '2021-08-19 17:15:42'),
(46, 52, 25, 3, 0, '2021-08-19 17:25:40', '2021-08-19 17:25:40'),
(47, 52, 26, 2, 0, '2021-08-19 17:29:17', '2021-08-19 17:29:17'),
(48, 53, 32, 5, 0, '2021-08-20 00:38:19', '2021-08-20 00:38:19'),
(49, 53, 38, 2, 0, '2021-08-20 00:50:51', '2021-08-20 00:50:51'),
(50, 53, 37, 1, 0, '2021-08-20 01:25:30', '2021-08-20 01:25:30'),
(54, 55, 18, 2, 0, '2021-08-20 22:21:27', '2021-08-20 22:21:27'),
(55, 56, 18, 4, 0, '2021-08-20 23:47:02', '2021-08-20 23:47:02'),
(56, 56, 18, 1, 0, '2021-08-20 23:49:12', '2021-08-20 23:49:12'),
(57, 58, 37, 3, 0, '2021-09-01 10:47:00', '2021-09-01 10:47:00'),
(58, 59, 31, 2, 0, '2021-09-06 06:27:07', '2021-09-06 06:27:07'),
(59, 60, 21, 1, 0, '2021-09-06 11:54:27', '2021-09-06 11:54:27'),
(60, 60, 23, 1, 0, '2021-09-07 11:54:27', '2021-09-06 11:54:27'),
(61, 60, 26, 1, 0, '2021-09-07 11:54:27', '2021-09-06 11:54:27');

-- --------------------------------------------------------

--
-- Table structure for table `danhgia`
--

DROP TABLE IF EXISTS `danhgia`;
CREATE TABLE `danhgia` (
  `MADG` int(10) UNSIGNED NOT NULL,
  `NOIDUNG` text NOT NULL,
  `HINHANH` varchar(300) NOT NULL,
  `MUCDO` int(1) NOT NULL,
  `MAPD` int(10) UNSIGNED NOT NULL,
  `MADONGSP` int(10) UNSIGNED NOT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `danhgia`
--

INSERT INTO `danhgia` (`MADG`, `NOIDUNG`, `HINHANH`, `MUCDO`, `MAPD`, `MADONGSP`, `created_at`, `updated_at`) VALUES
(4, 'Sản phẩm quá là ok luôn. \nChất lượng tuyệt vời! \nNên mua nha anh chị em', 'samsung-galaxy-a51-5g-uw-1.jpg', 5, 22, 18, '2021-08-29 11:03:59', '2021-08-29 11:03:59'),
(6, 'Sản phẩm khá ok đấy', 're-view-001.jpg', 5, 22, 25, '2021-08-29 16:24:50', '2021-08-29 16:24:50');

-- --------------------------------------------------------

--
-- Table structure for table `dongsp`
--

DROP TABLE IF EXISTS `dongsp`;
CREATE TABLE `dongsp` (
  `MADONGSP` int(10) UNSIGNED NOT NULL,
  `TENDONGSP` varchar(300) NOT NULL,
  `GIA` double NOT NULL,
  `TRANGTHAI` int(11) NOT NULL,
  `HINHANH` varchar(100) DEFAULT NULL,
  `MOTA` text DEFAULT NULL,
  `LOAI` varchar(100) DEFAULT NULL,
  `MAHANG` int(10) UNSIGNED NOT NULL,
  `GIAMGIA` varchar(100) DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `dongsp`
--

INSERT INTO `dongsp` (`MADONGSP`, `TENDONGSP`, `GIA`, `TRANGTHAI`, `HINHANH`, `MOTA`, `LOAI`, `MAHANG`, `GIAMGIA`, `created_at`, `updated_at`) VALUES
(18, 'iPhone 6 Plus', 150, 1, 'apple-iphone-6-plus-1.jpg', 'Released 2014, September 19\n172g, 7.1mm thickness\niOS 8, up to iOS 12.4.6\n16GB/64GB/128GB storage, no card slot', '99%', 2, '0.0', '2021-08-19 15:53:19', '2021-08-19 15:53:19'),
(19, 'iPhone 7 Plus', 250, 1, 'apple-iphone-7-plus-01.jpg', 'Released 2016, September 16\n188g, 7.3mm thickness\niOS 10.0.1, up to iOS 14.7\n32GB/128GB/256GB storage, no card slot', '99%', 2, '0.0', '2021-08-19 15:55:27', '2021-08-19 15:55:27'),
(20, 'iPhone 7', 200, 1, 'apple-iphone-7-2.jpg', 'Released 2016, September 16\n138g, 7.1mm thickness\niOS 10.0.1, up to iOS 14.7\n32GB/128GB/256GB storage, no card slot', '99%', 2, '0.0', '2021-08-19 15:56:23', '2021-08-19 15:56:23'),
(21, 'iPhone 8', 300, 1, 'apple-iphone-8-new-1.jpg', 'Released 2017, September 22\n148g, 7.3mm thickness\niOS 11, up to iOS 14.7\n64GB/128GB/256GB storage, no card slot', '99%', 2, '0.0', '2021-08-19 15:57:44', '2021-08-19 15:57:44'),
(22, 'iPhone 8 Plus', 350, 1, 'apple-iphone-8-plus-1.jpg', 'Released 2017, September 22\n202g, 7.5mm thickness\niOS 11, up to iOS 14.7\n64GB/128GB/256GB storage, no card slot', '99%', 2, '0.0', '2021-08-19 15:58:47', '2021-08-19 15:58:47'),
(23, 'iPhone X', 400, 1, 'apple-iphone-x-new-1.jpg', 'Released 2017, November 03\n174g, 7.7mm thickness\niOS 11.1.1, up to iOS 14.7\n64GB/256GB storage, no card slot', '99%', 2, '0.0', '2021-08-19 16:00:10', '2021-08-19 16:00:10'),
(24, 'iPhone XS', 420, 1, 'apple-iphone-xs-max-5.jpg', 'Released 2018, September 21\n177g, 7.7mm thickness\niOS 12, up to iOS 14.7\n64GB/256GB/512GB storage, no card slot', '99%', 2, '0.0', '2021-08-19 16:01:35', '2021-08-19 16:01:35'),
(25, 'iPhone XS Max', 450, 1, 'apple-iphone-xs-max-3.jpg', 'Released 2018, September 21\n208g, 7.7mm thickness\niOS 12, up to iOS 14.7\n64GB/256GB/512GB storage, no card slot', '99%', 2, '0.0', '2021-08-19 16:03:33', '2021-08-19 16:03:33'),
(26, 'iPhone 11', 550, 1, 'apple-iphone-11-1.jpg', 'Released 2019, September 20\n194g, 8.3mm thickness\niOS 13, up to iOS 14.7\n64GB/128GB/256GB storage, no card slot', 'New', 2, '0.0', '2021-08-19 16:05:41', '2021-08-19 16:05:41'),
(27, 'iPhone 11 Pro', 580, 1, 'apple-iphone-pro-10.jpg', 'Released 2019, September 20\n188g, 8.1mm thickness\niOS 13, up to iOS 14.7\n64GB/256GB/512GB storage, no card slot', 'New', 2, '0.0', '2021-08-19 16:07:00', '2021-08-19 16:07:00'),
(28, 'iPhone 11 Pro Max', 620, 1, 'apple-iphone-pro-10.jpg', 'Released 2019, September 20\n226g, 8.1mm thickness\niOS 13, up to iOS 14.7\n64GB/256GB/512GB storage, no card slot', 'New', 2, '0.0', '2021-08-19 16:08:13', '2021-08-19 16:08:13'),
(29, 'iPhone 12', 850, 1, 'apple-iphone-12-3.jpg', 'Released 2020, October 23\n164g, 7.4mm thickness\niOS 14.1, up to iOS 14.7\n64GB/128GB/256GB storage, no card slot', 'New', 2, '0.0', '2021-08-19 16:10:04', '2021-08-19 16:10:04'),
(30, 'iPhone 12 Pro', 1050, 1, 'apple-iphone-12-pro-max-3.jpg', 'Released 2020, October 23\n189g, 7.4mm thickness\niOS 14.1, up to iOS 14.7\n128GB/256GB/512GB storage, no card slot', 'New', 2, '0.0', '2021-08-19 16:11:21', '2021-08-19 16:11:21'),
(31, 'iPhone 12 Pro Max', 1150, 1, 'apple-iphone-12-pro-max-1.jpg', 'Released 2020, November 13\n228g, 7.4mm thickness\niOS 14.1, up to iOS 14.7\n128GB/256GB/512GB storage, no card slot', 'New', 2, '0.0', '2021-08-19 16:13:59', '2021-08-19 16:13:59'),
(32, 'Samsung Galaxy J7 Prime', 150, 1, 'samsung-galaxy-j7-prime-1.jpg', 'Released 2016, November 30\n167g, 8mm thickness\nAndroid 6.0.1, up to Android 9.0, One UI\n16GB/32GB/64GB storage, microSDXC', '2nd', 13, '0.0', '2021-08-19 16:16:45', '2021-08-19 16:16:45'),
(33, 'Samsung Galaxy Note20', 800, 1, 'samsung-galaxy-note20-2.jpg', 'Released 2020, August 21\n192g, 8.3mm thickness\nAndroid 10, up to Android 11, One UI 3.0\n256GB storage, no card slot', 'New', 13, '0.0', '2021-08-19 16:19:09', '2021-08-19 16:19:09'),
(34, 'Samsung Galaxy A51 5G', 400, 1, 'samsung-galaxy-a51-5g-uw-1.jpg', 'Released 2020, August 14\n188.8g, 8.6mm thickness\nAndroid 10, One UI 2\n128GB storage, microSDXC', 'New', 13, '0.0', '2021-08-19 16:20:22', '2021-08-19 16:20:22'),
(35, 'Samsung Galaxy Z Flip 5G', 900, 1, 'samsung-galaxy-z-flip-5g-mystic-bronze-1.jpg', 'Released 2020, August 07\n183g, 7.2mm thickness\nAndroid 10, up to Android 11, One UI 3.0\n256GB storage, no card slot', 'New', 13, '0.0', '2021-08-19 16:21:37', '2021-08-19 16:21:37'),
(36, 'Samsung Galaxy Z Fold3 5G', 1799, 2, 'galaxy-z-fold-3-1.jpg', 'Exp. release 2021, August 27\n271g, 6.4mm thickness\nAndroid 11, One UI 3.5\n256GB/512GB storage, no card slot', 'New', 13, '0.0', '2021-08-19 16:24:27', '2021-08-19 16:24:27'),
(37, 'Samsung Galaxy A03s', 150, 1, 'samsung-galaxy-a03s-01.jpg', '2021, August\n196g, 9.1mm thickness\nAndroid 11, One UI 3.0\n32GB/64GB storage, microSDXC', 'New', 13, '0.0', '2021-08-19 16:27:52', '2021-08-19 16:27:52'),
(38, 'Samsung Galaxy F52 5G', 300, 1, 'samsung-galaxy-f52-5g-1.jpg', 'Released 2021, June 01\n199g, 8.7mm thickness\nAndroid 11, One UI 3.1\n128GB storage, microSDXC', 'New', 13, '0.0', '2021-08-19 16:29:31', '2021-08-19 16:29:31'),
(39, 'Samsung Galaxy S21 Ultra 5G', 835, 1, 'samsung-galaxy-s21-ultra-5g-2.jpg', 'Released 2021, January 29\n227g (Sub6), 229g (mmWave), 8.9mm thickness\nAndroid 11, One UI 3.1\n128GB/256GB/512GB storage, no card slot', 'New', 13, '0.0', '2021-08-19 16:31:10', '2021-08-19 16:31:10');

-- --------------------------------------------------------

--
-- Table structure for table `hang`
--

DROP TABLE IF EXISTS `hang`;
CREATE TABLE `hang` (
  `MAHANG` int(10) UNSIGNED NOT NULL,
  `TENHANG` varchar(100) NOT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `hang`
--

INSERT INTO `hang` (`MAHANG`, `TENHANG`, `created_at`, `updated_at`) VALUES
(2, 'iPhone', NULL, NULL),
(3, 'Oppo', NULL, NULL),
(6, 'Huawei', '2021-08-14 22:49:28', '2021-08-14 22:49:28'),
(13, 'Samsung', '2021-08-19 16:14:53', '2021-08-19 16:14:53'),
(15, 'Vsmart', '2021-09-01 10:09:36', '2021-09-01 10:09:36');

-- --------------------------------------------------------

--
-- Table structure for table `hoadon`
--

DROP TABLE IF EXISTS `hoadon`;
CREATE TABLE `hoadon` (
  `MAHD` int(10) UNSIGNED NOT NULL,
  `MAPD` int(10) UNSIGNED NOT NULL,
  `NGAYDAT` date NOT NULL,
  `THANHTIEN` double NOT NULL,
  `MASOTHUE` varchar(100) DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `khachhang`
--

DROP TABLE IF EXISTS `khachhang`;
CREATE TABLE `khachhang` (
  `MAKH` int(10) UNSIGNED NOT NULL,
  `HO` varchar(100) NOT NULL,
  `TEN` varchar(200) NOT NULL,
  `GIOITINH` varchar(100) NOT NULL,
  `DIACHI` varchar(200) NOT NULL,
  `SDT` varchar(100) NOT NULL,
  `EMAIL` varchar(200) NOT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  `removal_flag` tinyint(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `khachhang`
--

INSERT INTO `khachhang` (`MAKH`, `HO`, `TEN`, `GIOITINH`, `DIACHI`, `SDT`, `EMAIL`, `created_at`, `updated_at`, `removal_flag`) VALUES
(1, 'Tan', 'Nguyen', 'Nam', 'Ben Tre', '0389211236', 'tannd190499@gmail.com', '2021-07-13 22:16:48', '2021-08-13 22:16:48', NULL),
(2, 'Tân', 'Nguyễn Duy', 'Nam', 'Bến Tre', '0389211236', 'tan123@gmail.com', '2021-08-14 02:04:50', '2021-08-14 02:04:50', NULL),
(3, 'Thảo', 'Nguyễn', 'Nữ', 'Thủ Đức', '0376116779', 'thaontp@gmail.com', '2021-08-19 20:17:55', '2021-08-19 20:17:55', NULL),
(4, 'Minh', 'Đặng', 'Nam', 'Nam Định', '0123456789', 'minh123@gmail.com', '2021-09-01 09:58:04', '2021-09-01 09:58:04', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `nhanvien`
--

DROP TABLE IF EXISTS `nhanvien`;
CREATE TABLE `nhanvien` (
  `MANV` int(10) UNSIGNED NOT NULL,
  `HO` varchar(100) NOT NULL,
  `TEN` varchar(200) NOT NULL,
  `GIOITINH` varchar(100) NOT NULL,
  `DIACHI` varchar(200) NOT NULL,
  `SDT` varchar(100) NOT NULL,
  `EMAIL` varchar(200) NOT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  `removal_flag` tinyint(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `nhanvien`
--

INSERT INTO `nhanvien` (`MANV`, `HO`, `TEN`, `GIOITINH`, `DIACHI`, `SDT`, `EMAIL`, `created_at`, `updated_at`, `removal_flag`) VALUES
(8, 'Tan', 'Nguyen', 'Nam', 'Bến Tre', '0389211236', 'tannd1904@gmail.com', '2021-08-18 22:27:01', '2021-08-18 22:27:01', NULL),
(9, 'Thao', 'Nguyen', 'Nam', 'Ben Tre', '0389211236', 'thao_admin@gmail.com', '2021-09-01 10:03:06', '2021-09-01 10:03:06', NULL),
(10, 'Nguyên', 'Nguyễn', 'Nữ', 'Đắk Lắk', '0321983213', 'nguyen123@gmail.com', '2021-09-01 10:25:02', '2021-09-01 10:25:02', NULL),
(11, 'Nguyên', 'Nguyễn', 'Nữ', 'Đắk Lắk', '0312321832', 'nguyen12345@gmail.com', '2021-09-01 10:28:46', '2021-09-01 10:28:46', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `nhomquyen`
--

DROP TABLE IF EXISTS `nhomquyen`;
CREATE TABLE `nhomquyen` (
  `MANQ` int(10) UNSIGNED NOT NULL,
  `TENQUYEN` varchar(100) NOT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `nhomquyen`
--

INSERT INTO `nhomquyen` (`MANQ`, `TENQUYEN`, `created_at`, `updated_at`) VALUES
(1, 'ROLE_ADMIN', NULL, NULL),
(2, 'ROLE_USER', NULL, NULL),
(3, 'ROLE_EMPLOYEE', NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `phieudat`
--

DROP TABLE IF EXISTS `phieudat`;
CREATE TABLE `phieudat` (
  `MAPD` int(10) UNSIGNED NOT NULL,
  `NGAYDAT` date NOT NULL,
  `GIA` double DEFAULT NULL,
  `HO` varchar(300) NOT NULL,
  `TEN` varchar(100) DEFAULT NULL,
  `EMAIL` varchar(300) DEFAULT NULL,
  `DIACHI` varchar(300) NOT NULL,
  `SDT` varchar(100) NOT NULL,
  `TRANGTHAI` int(11) NOT NULL,
  `MANV` int(10) UNSIGNED DEFAULT NULL,
  `MAKH` int(10) UNSIGNED DEFAULT NULL,
  `GHICHU` text DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `phieudat`
--

INSERT INTO `phieudat` (`MAPD`, `NGAYDAT`, `GIA`, `HO`, `TEN`, `EMAIL`, `DIACHI`, `SDT`, `TRANGTHAI`, `MANV`, `MAKH`, `GHICHU`, `created_at`, `updated_at`) VALUES
(1, '2021-08-26', 0, 'Tân', 'Nguyênx', 'tannd1904@gmail.com', 'Thủ Đức', '0389211236', 1, 8, 3, 'Giao hàng nhanh', '2021-08-25 23:57:35', '2021-08-25 23:57:35'),
(21, '2021-08-27', 0, 'Thảo', 'Nguyễn', 'thaontp@gmail.com', 'Bến Tre', '0376116779', 3, 8, 3, '', '2021-08-27 02:09:34', '2021-08-27 02:09:34'),
(22, '2021-08-27', 1485, 'Thảo', 'Nguyễn', 'thaontp@gmail.com', 'Bến Tre', '0376116779', 2, 8, 3, '', '2021-08-27 08:37:43', '2021-08-27 08:37:43'),
(23, '2021-08-29', 275, 'Thảo', 'Nguyễn', 'thaontp@gmail.com', 'Bến Tre', '0376116779', 2, 8, 3, 'Test 2', '2021-08-29 04:28:09', '2021-08-29 04:28:09'),
(24, '2021-09-01', 385.00000000000006, 'Thảo', 'Nguyễn', 'thaontp@gmail.com', 'Thủ Đức', '0376116779', 0, 8, 3, '', '2021-09-01 05:35:13', '2021-09-01 05:35:13'),
(25, '2021-09-07', 385.00000000000006, 'Thảo', 'Nguyễn', 'thaontp@gmail.com', 'Thủ Đức', '0376116779', 2, 8, 3, '', '2021-09-07 06:42:27', '2021-09-07 06:42:27');

-- --------------------------------------------------------

--
-- Table structure for table `phieunhap`
--

DROP TABLE IF EXISTS `phieunhap`;
CREATE TABLE `phieunhap` (
  `MAPN` int(10) UNSIGNED NOT NULL,
  `NGAYLAP` date NOT NULL,
  `MANV` int(10) UNSIGNED NOT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `phieunhap`
--

INSERT INTO `phieunhap` (`MAPN`, `NGAYLAP`, `MANV`, `created_at`, `updated_at`) VALUES
(50, '2021-08-19', 8, '2021-08-19 16:31:52', '2021-08-19 16:31:52'),
(51, '2021-08-20', 8, '2021-08-19 17:11:56', '2021-08-19 17:11:56'),
(52, '2021-08-20', 8, '2021-08-19 17:22:59', '2021-08-19 17:22:59'),
(53, '2021-08-20', 8, '2021-08-20 00:33:13', '2021-08-20 00:33:13'),
(55, '2021-08-21', 8, '2021-08-20 22:20:59', '2021-08-20 22:20:59'),
(56, '2021-08-21', 8, '2021-08-20 23:46:11', '2021-08-20 23:46:11'),
(57, '2021-09-01', 8, '2021-09-01 10:45:13', '2021-09-01 10:45:13'),
(58, '2021-09-01', 8, '2021-09-01 10:46:13', '2021-09-01 10:46:13'),
(59, '2021-09-06', 8, '2021-09-06 06:24:39', '2021-09-06 06:24:39'),
(60, '2021-09-06', 8, '2021-09-06 06:34:25', '2021-09-06 06:34:25');

-- --------------------------------------------------------

--
-- Table structure for table `phieutra`
--

DROP TABLE IF EXISTS `phieutra`;
CREATE TABLE `phieutra` (
  `MAPT` int(10) UNSIGNED NOT NULL,
  `NGAYTRA` date NOT NULL,
  `MANV` int(10) UNSIGNED NOT NULL,
  `MAPD` int(10) UNSIGNED NOT NULL,
  `MASP` int(10) UNSIGNED NOT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `sanpham`
--

DROP TABLE IF EXISTS `sanpham`;
CREATE TABLE `sanpham` (
  `MASP` int(10) UNSIGNED NOT NULL,
  `SERIAL` varchar(100) NOT NULL,
  `GIA` double NOT NULL,
  `COLOR` varchar(100) DEFAULT NULL,
  `CPU` varchar(100) DEFAULT NULL,
  `RAM` varchar(100) DEFAULT NULL,
  `SCREEN` varchar(100) DEFAULT NULL,
  `MEMMORY` varchar(100) DEFAULT NULL,
  `CAMERA` varchar(100) DEFAULT NULL,
  `PIN` varchar(100) DEFAULT NULL,
  `OS` varchar(100) DEFAULT NULL,
  `GHICHU` text DEFAULT NULL,
  `MACTPN` int(10) UNSIGNED NOT NULL,
  `TRANGTHAI` tinyint(1) DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `sanpham`
--

INSERT INTO `sanpham` (`MASP`, `SERIAL`, `GIA`, `COLOR`, `CPU`, `RAM`, `SCREEN`, `MEMMORY`, `CAMERA`, `PIN`, `OS`, `GHICHU`, `MACTPN`, `TRANGTHAI`, `created_at`, `updated_at`) VALUES
(18, 'FOI3129JLAJO', 0, 'Black', 'Apple A10', '3GB', 'Retina IPS LCD', '32 GB', '12 MP - 7 MP', 'Li-Ion 2900 mAh', 'iOS', '', 39, 1, '2021-08-19 16:53:00', '2021-08-19 16:53:00'),
(19, 'F2LV24K7HG04', 0, 'Black', 'Apple A10', '3GB', 'Retina IPS LCD', '32 GB', '12 MP - 7 MP', 'Li-Ion 2900 mAh', 'iOS', '', 39, 1, '2021-08-19 16:53:00', '2021-08-19 16:53:00'),
(20, 'F012I9J1OKLL', 0, 'Black', 'Apple A10', '3GB', 'Retina IPS LCD', '32 GB', '12 MP - 7 MP', 'Li-Ion 2900 mAh', 'iOS', '', 39, 1, '2021-08-19 16:53:00', '2021-08-19 16:53:00'),
(21, 'F31ODKASLQ21', 0, 'Black', 'Apple A10', '2GB', 'Retina IPS LCD 4.7\"', '32 GB', '12 MP - 7 MP', 'Li-Ion 1960', 'iOS', '', 40, 1, '2021-08-19 16:58:05', '2021-08-19 16:58:05'),
(22, 'F123LKKZXAMN', 0, 'White', 'Apple A11', '2GB', 'Retina LCD IPS 4.7\"', '64 GB', '12 MP - 7 MP', 'Li-Ion 1821 mAh', 'iOS', '', 41, 1, '2021-08-19 17:01:43', '2021-08-19 17:01:43'),
(23, 'FPO1I230KKML', 0, 'White', 'Apple A11', '2GB', 'Retina LCD IPS 4.7\"', '64 GB', '12 MP - 7 MP', 'Li-Ion 1821 mAh', 'iOS', '', 41, 0, '2021-08-19 17:01:43', '2021-08-19 17:01:43'),
(24, 'FI0994230MLL', 0, 'White', 'Apple 11', '3GB', 'Retina LCD IPS 5.5\"', '64 GB', '12 MP - 7 MP', 'Li-Ion 2691 mAh', 'iOS', '', 42, 0, '2021-08-19 17:06:54', '2021-08-19 17:06:54'),
(25, 'FNZPI231IOKX', 0, 'White', 'Apple 11', '3GB', 'Retina LCD IPS 5.5\"', '64 GB', '12 MP - 7 MP', 'Li-Ion 2691 mAh', 'iOS', '', 42, 0, '2021-08-19 17:06:54', '2021-08-19 17:06:54'),
(26, 'FMLK2LKXKP', 0, 'White', 'Apple 11', '3GB', 'Retina LCD IPS 5.5\"', '64 GB', '12 MP - 7 MP', 'Li-Ion 2691 mAh', 'iOS', '', 42, 0, '2021-08-19 17:06:54', '2021-08-19 17:06:54'),
(27, 'FMZKCO1203JL', 0, 'White', 'Apple A11', '4GB', 'Super Retina OLED 5.8\" ', '64 GB', '12 MP - MP', 'Li-Ion 2716 mAh', 'iOS', '', 43, 1, '2021-08-19 17:10:42', '2021-08-19 17:10:42'),
(28, 'FKPZO102OI0Z', 0, 'White', 'Apple A11', '4GB', 'Super Retina OLED 5.8\" ', '64 GB', '12 MP - MP', 'Li-Ion 2716 mAh', 'iOS', '', 43, 0, '2021-08-19 17:10:42', '2021-08-19 17:10:42'),
(29, 'FOIZ2109IZOOI', 0, 'White', 'Apple A11', '4GB', 'Super Retina OLED 5.8\" ', '64 GB', '12 MP - MP', 'Li-Ion 2716 mAh', 'iOS', '', 43, 0, '2021-08-19 17:10:42', '2021-08-19 17:10:42'),
(30, 'F312DAADWWN', 0, 'White', 'Apple A13', '4GB', 'Liquid Retina IPS LCD 6.1\"', '64 GB', '12 MP - 12 MP', 'Li-Ion 3110 mAh', 'iOS', '', 46, 1, '2021-08-19 17:29:17', '2021-08-19 17:29:17'),
(31, 'FQWCAJH435VC', 0, 'White', 'Apple A13', '4GB', 'Liquid Retina IPS LCD 6.1\"', '64 GB', '12 MP - 12 MP', 'Li-Ion 3110 mAh', 'iOS', '', 46, 1, '2021-08-19 17:29:17', '2021-08-19 17:29:17'),
(32, '389172310283091', 0, 'Silver', 'Snapdragon 750G 5G', '8GB', 'TFT, 120Hz 6.6\"', '128 GB', '64 MP - 16 MP', 'Li-Po 4500 mAh', 'Android', '', 48, 0, '2021-08-20 00:50:51', '2021-08-20 00:50:51'),
(33, '673982173982132', 0, 'Silver', 'Snapdragon 750G 5G', '8GB', 'TFT, 120Hz 6.6\"', '128 GB', '64 MP - 16 MP', 'Li-Po 4500 mAh', 'Android', '', 48, 0, '2021-08-20 00:50:51', '2021-08-20 00:50:51'),
(34, '873981290381043', 0, 'Blue', 'MediaTek MT6765 Helio P35', '4GB', 'PLS LCD 6.5\"', '32 GB', '13 MP - 5 MP', 'Li-Po 5000 mAh', 'Android', '', 49, 0, '2021-08-20 01:25:30', '2021-08-20 01:25:30'),
(41, 'FASDASDASDA', 0, 'Black', 'Gen 7th', '2GB', 'Full HD', 'SD 16GB', 'Dual Cam', '2000 mAh', 'Android', '', 54, 1, '2021-08-20 22:21:39', '2021-08-20 22:21:39'),
(42, 'FASDSA8ASDA', 0, 'Black', 'Gen 7th', '2GB', 'Full HD', 'SD 16GB', 'Dual Cam', '2000 mAh', 'Android', '', 54, 1, '2021-08-20 22:21:39', '2021-08-20 22:21:39'),
(43, 'PODASKDAPOKCX', 0, 'Black', 'Gen 7th', '2GB', 'Full HD', 'SD 16GB', 'Dual Cam', '2000 mAh', 'Android', '', 55, 0, '2021-08-20 23:47:19', '2021-08-20 23:47:19'),
(44, 'DSAFSADASCXZC', 0, 'Black', 'Gen 7th', '2GB', 'Full HD', 'SD 16GB', 'Dual Cam', '2000 mAh', 'Android', '', 55, 0, '2021-08-20 23:47:19', '2021-08-20 23:47:19'),
(45, 'FASDAXZCAWQE', 0, 'Black', 'Gen 7th', '2GB', 'Full HD', 'SD 16GB', 'Dual Cam', '2000 mAh', 'Android', '', 55, 1, '2021-08-20 23:47:19', '2021-08-20 23:47:19'),
(46, 'POADSIDPOQWMCL', 0, 'Black', 'Gen 7th', '2GB', 'Full HD', 'SD 16GB', 'Dual Cam', '2000 mAh', 'Android', '', 55, 0, '2021-08-20 23:47:19', '2021-08-20 23:47:19'),
(47, 'FAKLSKXZCOIOC', 0, 'Red', 'Gen 7th', '2GB', '2K', 'HD 8GB', 'Dual Cam', '8000', 'iOS', '', 56, 0, '2021-08-20 23:49:20', '2021-08-20 23:49:20'),
(48, '2EWQEQDASDASD', 0, 'Black', 'Gen X', '3GB', 'Full HD', 'SD 16GB', 'Dual Cam', '2000 mAh', 'iOS', '', 57, 0, '2021-09-01 10:47:38', '2021-09-01 10:47:38'),
(49, 'EQWDADASDASD', 0, 'Black', 'Gen X', '3GB', 'Full HD', 'SD 16GB', 'Dual Cam', '2000 mAh', 'iOS', '', 57, 0, '2021-09-01 10:47:38', '2021-09-01 10:47:38'),
(50, 'FASDASDASFDA', 0, 'Black', 'Gen X', '3GB', 'Full HD', 'SD 16GB', 'Dual Cam', '2000 mAh', 'iOS', '', 57, 0, '2021-09-01 10:47:38', '2021-09-01 10:47:38'),
(51, 'FPOKZMXCLKZ', 0, 'Gold', 'Apple A14 Bionic', '6GB', 'Super Retina XDR OLED 6.7\"', '256 GB', '12 MP - 12 MP', 'Li-Ion 3687 mAh', 'iOS', '', 58, 0, '2021-09-06 06:27:24', '2021-09-06 06:27:24'),
(52, 'FASXZCBNKYUI', 0, 'Gold', 'Apple A14 Bionic', '6GB', 'Super Retina XDR OLED 6.7\"', '256 GB', '12 MP - 12 MP', 'Li-Ion 3687 mAh', 'iOS', '', 58, 0, '2021-09-06 06:27:24', '2021-09-06 06:27:24'),
(53, 'FACZXCASQADASDQW', 0, 'Black', 'Gen X', '3GB', 'Full HD', 'SD 16GB', 'Dual Cam', '2000 mAh', 'Android', '', 59, 0, '2021-09-06 11:54:33', '2021-09-06 11:54:33'),
(54, 'FCZXLCKZXKCZ', 0, 'Black', 'Apple A11', '4GB', 'Super Retina OLED 5.8\" ', '128 GB', '12 MP - 7MP', 'Li-Ion 3110 mAh', 'iOS', NULL, 60, 0, '2021-09-07 06:27:24', '2021-09-07 06:27:24'),
(56, 'FCZXLCKZXK312', 0, 'White', 'Apple A13 Bionic', '4GB', 'Liquid Retina IPS LCD 6.1\" ', '128 GB', '12 MP - 12MP', 'Li-Ion 3110 mAh', 'iOS', NULL, 61, 0, '2021-09-07 06:27:24', '2021-09-07 06:27:24');

-- --------------------------------------------------------

--
-- Table structure for table `taikhoan`
--

DROP TABLE IF EXISTS `taikhoan`;
CREATE TABLE `taikhoan` (
  `EMAIL` varchar(200) NOT NULL,
  `PASSWORD` varchar(300) NOT NULL,
  `MANQ` int(10) UNSIGNED NOT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `taikhoan`
--

INSERT INTO `taikhoan` (`EMAIL`, `PASSWORD`, `MANQ`, `created_at`, `updated_at`) VALUES
('minh123@gmail.com', '$2a$10$QtOT9DXUEkktoTSOzuG5K.QXApJDMsprB/kJAgIRGNNHYYOLxT0Om', 2, '2021-09-01 09:58:04', '2021-09-01 09:58:04'),
('nguyen12345@gmail.com', '$2a$10$y2plKP5QZlVTH7ZLjHVcE.edKYnyFFTdHPD4NCgq/thuvaR3ZT1n6', 1, '2021-09-01 10:28:46', '2021-09-01 10:28:46'),
('nguyen123@gmail.com', '$2a$10$GmYLV8VBXFBJMncJY6jC4.oi3pa8HiXZAiARTsHoBcFwd.4kv1ViK', 1, '2021-09-01 10:25:02', '2021-09-01 10:25:02'),
('tan123@gmail.com', '$2a$10$ZM9LciQr7hE202.b.h3/ReL8vx8nDV/ca17SNVKIVMxW6YhBTVW2a', 2, '2021-08-14 02:04:50', '2021-08-14 02:04:50'),
('tannd190499@gmail.com', '$2a$10$MSJeGEHRufDc7XE7sfjA0e7gj6giI6joIg7v.qQxCwyziGolPOYn2', 2, '2021-08-13 22:16:48', '2021-08-13 22:16:48'),
('tannd1904@gmail.com', '$2a$10$8Q4E6mX5.J55bmsYzyDL3utOPFhvgSIZ5mzM9FdZCZJqAKFtnIzPO', 1, '2021-08-18 22:27:01', '2021-08-18 22:27:01'),
('tanndd190499@gmail.com', '$2a$10$c2DSEOYFVpE3CN5m8pI3Q.u02C1fQuCA3nk7gE4uqrrakA/uVtXdG', 1, '2021-08-13 22:34:41', '2021-08-13 22:34:41'),
('tanndd904@gmail.com', '$2a$10$E95Eu5OjSpCM0j0VMdwxaeB/tQZ.CiVwTBcoOzX9IDc8usOebbEb2', 1, '2021-08-18 04:16:22', '2021-08-18 04:16:22'),
('thaontp@gmail.com', '$2a$10$5BJyhxwDpZF0SzPzw064repWeb08PNq4PZrOhTbS0kFvJWNiEt8h2', 2, '2021-08-19 20:17:55', '2021-08-19 20:17:55'),
('thao_admin@gmail.com', '$2a$10$MzwCek5dWcqoWQpWXKrSD.DTDxcX4R8aCVw51SqMkMQtY7jQ936P.', 1, '2021-09-01 10:03:06', '2021-09-01 10:03:06');

-- --------------------------------------------------------

--
-- Table structure for table `yeuthich`
--

DROP TABLE IF EXISTS `yeuthich`;
CREATE TABLE `yeuthich` (
  `MAGH` int(10) UNSIGNED NOT NULL,
  `MAKH` int(10) UNSIGNED NOT NULL,
  `MADONGSP` int(10) UNSIGNED DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  `removal_flag` tinyint(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `yeuthich`
--

INSERT INTO `yeuthich` (`MAGH`, `MAKH`, `MADONGSP`, `created_at`, `updated_at`, `removal_flag`) VALUES
(1, 3, 38, '2021-08-22 04:50:47', '2021-08-22 04:50:47', 1),
(6, 3, 39, '2021-08-23 22:55:54', '2021-08-23 22:55:54', 1),
(7, 3, 32, '2021-08-24 00:39:16', '2021-08-24 00:39:16', 1),
(8, 3, 26, '2021-08-24 00:39:23', '2021-08-24 00:39:23', 1),
(9, 3, 19, '2021-09-01 05:15:08', '2021-09-01 05:15:08', 0),
(10, 3, 22, '2021-09-01 05:25:10', '2021-09-01 05:25:10', 0);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `ct_phieudat`
--
ALTER TABLE `ct_phieudat`
  ADD PRIMARY KEY (`MACTPD`),
  ADD UNIQUE KEY `ct_phieudat_un` (`MASP`),
  ADD UNIQUE KEY `NewTable_MASP_IDX` (`MASP`) USING BTREE,
  ADD KEY `ct_phieudat_FK_1` (`MAPD`);

--
-- Indexes for table `ct_phieunhap`
--
ALTER TABLE `ct_phieunhap`
  ADD PRIMARY KEY (`MACTPN`),
  ADD KEY `ct_phieunhap_FK` (`MAPN`),
  ADD KEY `ct_phieunhap_FK_1` (`MADONGSP`);

--
-- Indexes for table `danhgia`
--
ALTER TABLE `danhgia`
  ADD PRIMARY KEY (`MADG`),
  ADD KEY `danhgia_FK_1` (`MADONGSP`),
  ADD KEY `danhgia_FK` (`MAPD`);

--
-- Indexes for table `dongsp`
--
ALTER TABLE `dongsp`
  ADD PRIMARY KEY (`MADONGSP`),
  ADD KEY `dongsp_FK` (`MAHANG`);

--
-- Indexes for table `hang`
--
ALTER TABLE `hang`
  ADD PRIMARY KEY (`MAHANG`);

--
-- Indexes for table `hoadon`
--
ALTER TABLE `hoadon`
  ADD PRIMARY KEY (`MAHD`),
  ADD UNIQUE KEY `hoadon_MAPD_IDX` (`MAPD`) USING BTREE;

--
-- Indexes for table `khachhang`
--
ALTER TABLE `khachhang`
  ADD PRIMARY KEY (`MAKH`);

--
-- Indexes for table `nhanvien`
--
ALTER TABLE `nhanvien`
  ADD PRIMARY KEY (`MANV`);

--
-- Indexes for table `nhomquyen`
--
ALTER TABLE `nhomquyen`
  ADD PRIMARY KEY (`MANQ`);

--
-- Indexes for table `phieudat`
--
ALTER TABLE `phieudat`
  ADD PRIMARY KEY (`MAPD`),
  ADD KEY `phieudat_FK` (`MANV`),
  ADD KEY `phieudat_FK_1` (`MAKH`);

--
-- Indexes for table `phieunhap`
--
ALTER TABLE `phieunhap`
  ADD PRIMARY KEY (`MAPN`),
  ADD KEY `PHIEUNHAP_FK` (`MANV`);

--
-- Indexes for table `phieutra`
--
ALTER TABLE `phieutra`
  ADD PRIMARY KEY (`MAPT`),
  ADD KEY `phieutra_FK` (`MANV`),
  ADD KEY `phieutra_FK_1` (`MAPD`),
  ADD KEY `phieutra_FK_2` (`MASP`);

--
-- Indexes for table `sanpham`
--
ALTER TABLE `sanpham`
  ADD PRIMARY KEY (`MASP`),
  ADD UNIQUE KEY `sanpham_SERIAL_IDX` (`SERIAL`) USING BTREE,
  ADD KEY `sanpham_FK` (`MACTPN`);

--
-- Indexes for table `taikhoan`
--
ALTER TABLE `taikhoan`
  ADD PRIMARY KEY (`EMAIL`),
  ADD KEY `taikhoan_FK` (`MANQ`);

--
-- Indexes for table `yeuthich`
--
ALTER TABLE `yeuthich`
  ADD PRIMARY KEY (`MAGH`),
  ADD UNIQUE KEY `yeuthich_MADONGSP_IDX` (`MADONGSP`) USING BTREE,
  ADD KEY `yeuthich_FK_1` (`MAKH`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `ct_phieudat`
--
ALTER TABLE `ct_phieudat`
  MODIFY `MACTPD` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=37;

--
-- AUTO_INCREMENT for table `ct_phieunhap`
--
ALTER TABLE `ct_phieunhap`
  MODIFY `MACTPN` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=62;

--
-- AUTO_INCREMENT for table `danhgia`
--
ALTER TABLE `danhgia`
  MODIFY `MADG` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `dongsp`
--
ALTER TABLE `dongsp`
  MODIFY `MADONGSP` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=40;

--
-- AUTO_INCREMENT for table `hang`
--
ALTER TABLE `hang`
  MODIFY `MAHANG` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT for table `hoadon`
--
ALTER TABLE `hoadon`
  MODIFY `MAHD` int(10) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `khachhang`
--
ALTER TABLE `khachhang`
  MODIFY `MAKH` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `nhanvien`
--
ALTER TABLE `nhanvien`
  MODIFY `MANV` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT for table `nhomquyen`
--
ALTER TABLE `nhomquyen`
  MODIFY `MANQ` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `phieudat`
--
ALTER TABLE `phieudat`
  MODIFY `MAPD` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=26;

--
-- AUTO_INCREMENT for table `phieunhap`
--
ALTER TABLE `phieunhap`
  MODIFY `MAPN` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=61;

--
-- AUTO_INCREMENT for table `phieutra`
--
ALTER TABLE `phieutra`
  MODIFY `MAPT` int(10) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `sanpham`
--
ALTER TABLE `sanpham`
  MODIFY `MASP` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=57;

--
-- AUTO_INCREMENT for table `yeuthich`
--
ALTER TABLE `yeuthich`
  MODIFY `MAGH` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `ct_phieudat`
--
ALTER TABLE `ct_phieudat`
  ADD CONSTRAINT `ct_phieudat_FK` FOREIGN KEY (`MASP`) REFERENCES `sanpham` (`MASP`) ON UPDATE CASCADE,
  ADD CONSTRAINT `ct_phieudat_FK_1` FOREIGN KEY (`MAPD`) REFERENCES `phieudat` (`MAPD`) ON UPDATE CASCADE;

--
-- Constraints for table `ct_phieunhap`
--
ALTER TABLE `ct_phieunhap`
  ADD CONSTRAINT `ct_phieunhap_FK` FOREIGN KEY (`MAPN`) REFERENCES `phieunhap` (`MAPN`) ON UPDATE CASCADE,
  ADD CONSTRAINT `ct_phieunhap_FK_1` FOREIGN KEY (`MADONGSP`) REFERENCES `dongsp` (`MADONGSP`) ON UPDATE CASCADE;

--
-- Constraints for table `danhgia`
--
ALTER TABLE `danhgia`
  ADD CONSTRAINT `danhgia_FK` FOREIGN KEY (`MAPD`) REFERENCES `phieudat` (`MAPD`) ON UPDATE CASCADE,
  ADD CONSTRAINT `danhgia_FK_1` FOREIGN KEY (`MADONGSP`) REFERENCES `dongsp` (`MADONGSP`) ON UPDATE CASCADE;

--
-- Constraints for table `dongsp`
--
ALTER TABLE `dongsp`
  ADD CONSTRAINT `dongsp_FK` FOREIGN KEY (`MAHANG`) REFERENCES `hang` (`MAHANG`) ON UPDATE CASCADE;

--
-- Constraints for table `hoadon`
--
ALTER TABLE `hoadon`
  ADD CONSTRAINT `hoadon_FK` FOREIGN KEY (`MAPD`) REFERENCES `phieudat` (`MAPD`) ON UPDATE CASCADE;

--
-- Constraints for table `phieudat`
--
ALTER TABLE `phieudat`
  ADD CONSTRAINT `phieudat_FK` FOREIGN KEY (`MANV`) REFERENCES `nhanvien` (`MANV`) ON UPDATE CASCADE,
  ADD CONSTRAINT `phieudat_FK_1` FOREIGN KEY (`MAKH`) REFERENCES `khachhang` (`MAKH`) ON UPDATE CASCADE;

--
-- Constraints for table `phieunhap`
--
ALTER TABLE `phieunhap`
  ADD CONSTRAINT `PHIEUNHAP_FK` FOREIGN KEY (`MANV`) REFERENCES `nhanvien` (`MANV`) ON UPDATE CASCADE;

--
-- Constraints for table `phieutra`
--
ALTER TABLE `phieutra`
  ADD CONSTRAINT `phieutra_FK` FOREIGN KEY (`MANV`) REFERENCES `nhanvien` (`MANV`) ON UPDATE CASCADE,
  ADD CONSTRAINT `phieutra_FK_1` FOREIGN KEY (`MAPD`) REFERENCES `phieudat` (`MAPD`) ON UPDATE CASCADE,
  ADD CONSTRAINT `phieutra_FK_2` FOREIGN KEY (`MASP`) REFERENCES `sanpham` (`MASP`) ON UPDATE CASCADE;

--
-- Constraints for table `sanpham`
--
ALTER TABLE `sanpham`
  ADD CONSTRAINT `sanpham_FK` FOREIGN KEY (`MACTPN`) REFERENCES `ct_phieunhap` (`MACTPN`);

--
-- Constraints for table `taikhoan`
--
ALTER TABLE `taikhoan`
  ADD CONSTRAINT `taikhoan_FK` FOREIGN KEY (`MANQ`) REFERENCES `nhomquyen` (`MANQ`) ON UPDATE CASCADE;

--
-- Constraints for table `yeuthich`
--
ALTER TABLE `yeuthich`
  ADD CONSTRAINT `yeuthich_FK` FOREIGN KEY (`MADONGSP`) REFERENCES `dongsp` (`MADONGSP`) ON UPDATE CASCADE,
  ADD CONSTRAINT `yeuthich_FK_1` FOREIGN KEY (`MAKH`) REFERENCES `khachhang` (`MAKH`) ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
