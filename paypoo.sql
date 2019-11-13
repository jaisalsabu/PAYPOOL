-- phpMyAdmin SQL Dump
-- version 4.9.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Nov 13, 2019 at 05:34 AM
-- Server version: 10.3.16-MariaDB
-- PHP Version: 7.3.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `id11161569_login`
--

-- --------------------------------------------------------

--
-- Table structure for table `paypoo`
--

CREATE TABLE `paypoo` (
  `srno` int(10) NOT NULL,
  `Pname` varchar(200) COLLATE utf8_unicode_ci NOT NULL,
  `Pid` varchar(200) COLLATE utf8_unicode_ci NOT NULL,
  `Phone` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `Password` varchar(200) COLLATE utf8_unicode_ci NOT NULL,
  `wallbal` varchar(200) COLLATE utf8_unicode_ci NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `paypoo`
--

INSERT INTO `paypoo` (`srno`, `Pname`, `Pid`, `Phone`, `Password`, `wallbal`) VALUES
(88, 'mariya', 'C9VhVReLQZ', '7293232372', '123', '0'),
(89, 'jaisal', '97PS6lgQYS', '7034308174', '123', '1900'),
(90, 'mary', 'hgF8ul4d6I', '8976453211', '111', '100'),
(91, 'siva', 'PbVX3hKuNh', '7344949955', '111', '0'),
(92, 'kiran', 'EktCii1G2J', '5421315484', '111', '8223'),
(93, 'yono', 'uaWSKvloy7', '8632367984', '123', '1777'),
(94, 'salmon jose', 'WjbLVfYZdl', '+191729323', '123', '0'),
(95, 'roshan', '627EE1eZuE', '4521345484', '111', '1000'),
(96, 'ronnie ', 'p5jHzKujpW', '4534343223', '1223', '0'),
(97, 'jismon', 'Cua7fBTEfR', '2323585874', '11223', '12000'),
(98, 'desmon', 'FOojetI3GB', '2345678901', '123', '0'),
(99, 'abraham', 'ey3pyV9Ye7', '7084521365', '111', '10000'),
(100, 'deswa', 'unF0BsfrvO', '2234353646', '111', '0'),
(101, 'swasthi', 'yj5zy6HSCB', '4536463654', '222', '2500'),
(102, 'gavin', '4rZSnY2P4D', '3434343434', '222', '2000'),
(103, 'christopher', 'ZuIAL6OUpB', '1234567981', '111', '10000'),
(104, 'imran', 'huaR6anSbJ', '1234567896', '111', '18000'),
(105, 'nikita', '4SGikdC1Tt', '1234443332', '222', '2000'),
(106, 'imrani', 'YkQNyVQ2hP', '1234567232', '111', '0'),
(107, 'tanvir', 'ENYdfb1mSH', '8282884646', '111', '0'),
(108, 'anu', 'vnpTnbBfDN', '1234569871', '123', '0'),
(109, 'kruschev', 'TfHblcl8E3', '1225434845', '111', '0'),
(110, 'jaisu', 'L2WF8ysP5C', '7034308174', '111', '0'),
(111, 'roshan', 'WG8ZMRRQmq', '6543452728', '222', '0'),
(112, 'tevver', '9txUKxitSh', '4453466567', '111', '0'),
(113, 'hiiimmu', 'BobBnljB4b', '2131146443', '111', '0'),
(114, 'jonathan', 'Z0HBM6ByMj', '1213454494', '111', '0'),
(115, 'johnappan', '4WA4A3yFZ8', '2134873767', '111', '0'),
(116, 'johnappan', 'x2qnDR1qoV', '2134873767', '111', '0'),
(117, 'jais', 'cCAELHd4vV', '2134446494', '111', '10000'),
(118, 'gishuu', 'TF7C8VRK8m', '3444494491', '111', '17000'),
(119, 'sundar', 'NV4sxwdDUG', '6389342636', '222', '17000'),
(120, 'pichai', 'grJ3JY7F8A', '1246484643', '111', '-7000'),
(121, 'jaya', 'nCIk84M2xo', '7564539834', '222', '1000'),
(122, 'yaasin', 'hmK1EOTvKq', '1115554319', '111', '-800'),
(123, 'jasin', 'NIHGtX4J44', '1235879464', '111', '5000'),
(124, 'johan', 'CRbMObF5zS', '1284643446', '222', '500');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `paypoo`
--
ALTER TABLE `paypoo`
  ADD PRIMARY KEY (`srno`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `paypoo`
--
ALTER TABLE `paypoo`
  MODIFY `srno` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=125;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
