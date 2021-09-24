-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Sep 13, 2021 at 04:33 PM
-- Server version: 10.4.19-MariaDB
-- PHP Version: 8.0.7

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `waste`
--

-- --------------------------------------------------------

--
-- Table structure for table `infectious_waste`
--

CREATE TABLE `infectious_waste` (
  `id` int(10) NOT NULL,
  `type_of_waste` text NOT NULL,
  `waste_object` text NOT NULL,
  `type_of_container` text NOT NULL,
  `total_waste_per_day` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `infectious_waste`
--

INSERT INTO `infectious_waste` (`id`, `type_of_waste`, `waste_object`, `type_of_container`, `total_waste_per_day`) VALUES
(1, 'Sharp', 'Needle', '3', 10),
(2, 'Chemical', 'Expired_cream', '2', 5),
(3, 'Sharp', 'Razors', '1', 6),
(5, 'Pharmaceutical', 'Expired_drug', '1', 4),
(7, 'Pathological', 'Liquid', '4', 1),
(8, 'Genotoxic', 'Blood', '2', 2),
(9, 'Genotoxic', 'Body_parts', '8', 7);

-- --------------------------------------------------------

--
-- Table structure for table `login`
--

CREATE TABLE `login` (
  `id` int(11) NOT NULL,
  `email` text NOT NULL,
  `password` text NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `login`
--

INSERT INTO `login` (`id`, `email`, `password`) VALUES
(1, 'test1@gmail.com', '12345');

-- --------------------------------------------------------

--
-- Table structure for table `non_infectious_waste`
--

CREATE TABLE `non_infectious_waste` (
  `id` int(10) NOT NULL,
  `type_of_waste` text NOT NULL,
  `waste_object` text NOT NULL,
  `type_of_container` text NOT NULL,
  `total_waste_per_day` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `non_infectious_waste`
--

INSERT INTO `non_infectious_waste` (`id`, `type_of_waste`, `waste_object`, `type_of_container`, `total_waste_per_day`) VALUES
(2, 'Sharp', 'Needle', '1', 2),
(3, 'Chemical', 'Expired_cream', '2', 3);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `infectious_waste`
--
ALTER TABLE `infectious_waste`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `login`
--
ALTER TABLE `login`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `non_infectious_waste`
--
ALTER TABLE `non_infectious_waste`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `infectious_waste`
--
ALTER TABLE `infectious_waste`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `login`
--
ALTER TABLE `login`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `non_infectious_waste`
--
ALTER TABLE `non_infectious_waste`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
