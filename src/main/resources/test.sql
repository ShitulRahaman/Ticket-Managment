-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 28, 2021 at 04:40 PM
-- Server version: 10.4.18-MariaDB
-- PHP Version: 8.0.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `test`
--

-- --------------------------------------------------------

--
-- Table structure for table `arrangement`
--

CREATE TABLE `arrangement` (
  `id` bigint(20) NOT NULL,
  `active` bit(1) NOT NULL,
  `created_stamp` datetime DEFAULT NULL,
  `created_tx_stamp` datetime DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `end_date_time` datetime DEFAULT NULL,
  `fare` bigint(20) DEFAULT NULL,
  `last_updated_stamp` datetime DEFAULT NULL,
  `last_updated_tx_stamp` datetime DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `reporting_date_time` datetime DEFAULT NULL,
  `start_date_time` datetime DEFAULT NULL,
  `total_seats` int(11) DEFAULT NULL,
  `ticket_type_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `arrangement`
--

INSERT INTO `arrangement` (`id`, `active`, `created_stamp`, `created_tx_stamp`, `description`, `end_date_time`, `fare`, `last_updated_stamp`, `last_updated_tx_stamp`, `name`, `reporting_date_time`, `start_date_time`, `total_seats`, `ticket_type_id`) VALUES
(1, b'1', '2021-04-27 18:14:24', '2021-04-27 18:14:24', 'Bus Journey', '2021-05-01 18:10:00', NULL, '2021-04-27 18:14:24', '2021-04-27 18:14:24', 'DeshTravelTicket', '2021-04-30 19:00:00', '2021-04-30 19:15:00', 1, 1),
(2, b'1', '2021-04-28 20:15:35', '2021-04-28 20:15:35', 'Train Journey', '2021-05-02 08:13:00', NULL, '2021-04-28 20:15:35', '2021-04-28 20:15:35', 'BanglaDeshRailway', '2021-05-01 20:00:00', '2021-05-01 20:15:00', 4, 3),
(4, b'1', '2021-04-28 20:23:15', '2021-04-28 20:23:15', 'Air Journey', '2021-05-02 20:21:00', NULL, '2021-04-28 20:23:15', '2021-04-28 20:23:15', 'Biman', '2021-04-30 20:21:00', '2021-04-30 20:21:00', 4, 2);

-- --------------------------------------------------------

--
-- Table structure for table `arrangement_locations`
--

CREATE TABLE `arrangement_locations` (
  `arrangement_id` bigint(20) NOT NULL,
  `locations_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `arrangement_locations`
--

INSERT INTO `arrangement_locations` (`arrangement_id`, `locations_id`) VALUES
(1, 1),
(2, 5),
(2, 6),
(4, 3),
(4, 4);

-- --------------------------------------------------------

--
-- Table structure for table `arrangement_seats`
--

CREATE TABLE `arrangement_seats` (
  `arrangement_id` bigint(20) NOT NULL,
  `seats_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `arrangement_seats`
--

INSERT INTO `arrangement_seats` (`arrangement_id`, `seats_id`) VALUES
(1, 1),
(2, 2),
(2, 3),
(2, 4),
(2, 5),
(4, 10),
(4, 11),
(4, 12),
(4, 13);

-- --------------------------------------------------------

--
-- Table structure for table `authority`
--

CREATE TABLE `authority` (
  `id` bigint(20) NOT NULL,
  `name` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `authority`
--

INSERT INTO `authority` (`id`, `name`) VALUES
(1, 'ROLE_ADMIN'),
(2, 'ROLE_USER');

-- --------------------------------------------------------

--
-- Table structure for table `buyer`
--

CREATE TABLE `buyer` (
  `id` bigint(20) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `buyer`
--

INSERT INTO `buyer` (`id`, `email`, `name`, `phone`) VALUES
(1, 'shitul.abcoder@gmail.com', 'Shitul', '01831731066'),
(2, 'shitul.abcoder@gmail.com', 'Shitul', '01831731066'),
(5, 'shitul.abcoder@gmail.com', 'Shitul', '01831731066');

-- --------------------------------------------------------

--
-- Table structure for table `location`
--

CREATE TABLE `location` (
  `id` bigint(20) NOT NULL,
  `active` bit(1) NOT NULL,
  `area` varchar(255) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `country` varchar(255) DEFAULT NULL,
  `created_stamp` datetime DEFAULT NULL,
  `created_tx_stamp` datetime DEFAULT NULL,
  `last_updated_stamp` datetime DEFAULT NULL,
  `last_updated_tx_stamp` datetime DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `street` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `location`
--

INSERT INTO `location` (`id`, `active`, `area`, `city`, `country`, `created_stamp`, `created_tx_stamp`, `last_updated_stamp`, `last_updated_tx_stamp`, `name`, `street`) VALUES
(1, b'1', 'Symoly', 'Dhaka', 'Bangladesh', '2021-04-26 22:05:44', '2021-04-26 22:05:44', '2021-04-26 22:05:44', '2021-04-26 22:05:44', 'Kollanpur', 'Dhaka'),
(2, b'1', 'Arambug Panir Tank', 'Dhaka', 'Bangladesh', '2021-04-28 20:02:10', '2021-04-28 20:02:10', '2021-04-28 20:02:10', '2021-04-28 20:02:10', 'ArambugCounter', 'Arambug'),
(3, b'1', 'Uttora', 'Dhaka', 'Bangladesh', '2021-04-28 20:09:43', '2021-04-28 20:09:43', '2021-04-28 20:09:43', '2021-04-28 20:09:43', 'Dhaka Airport', 'AirportRoad'),
(4, b'1', 'Nohata', 'Rajshahi', 'Bangladesh', '2021-04-28 20:10:11', '2021-04-28 20:10:11', '2021-04-28 20:10:11', '2021-04-28 20:10:11', 'Rajshahi Airport', 'Nohata Road'),
(5, b'1', 'Siol', 'Rajshahi', 'Bangladesh', '2021-04-28 20:10:44', '2021-04-28 20:10:44', '2021-04-28 20:10:44', '2021-04-28 20:10:44', 'Rajshahi Station', 'Rajshahi '),
(6, b'1', 'KomlaPur', 'Dhaka', 'Bangladesh', '2021-04-28 20:11:24', '2021-04-28 20:11:24', '2021-04-28 20:11:24', '2021-04-28 20:11:24', 'Dhaka Station', 'Komlapur'),
(7, b'1', 'Mirpur 2', 'Dhaka', 'Bangladesh', '2021-04-28 20:11:51', '2021-04-28 20:11:51', '2021-04-28 20:11:51', '2021-04-28 20:11:51', 'ShereBanglaStadium', 'Mirpur'),
(8, b'1', 'Bonani', 'Dhaka', 'Bangladesh', '2021-04-28 20:12:22', '2021-04-28 20:12:22', '2021-04-28 20:12:22', '2021-04-28 20:12:22', 'Army Stadium ', 'Bonani');

-- --------------------------------------------------------

--
-- Table structure for table `seat`
--

CREATE TABLE `seat` (
  `id` bigint(20) NOT NULL,
  `active` bit(1) NOT NULL,
  `created_stamp` datetime DEFAULT NULL,
  `created_tx_stamp` datetime DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `fare` bigint(20) DEFAULT NULL,
  `last_updated_stamp` datetime DEFAULT NULL,
  `last_updated_tx_stamp` datetime DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `seat`
--

INSERT INTO `seat` (`id`, `active`, `created_stamp`, `created_tx_stamp`, `description`, `fare`, `last_updated_stamp`, `last_updated_tx_stamp`, `name`, `status`) VALUES
(1, b'1', '2021-04-27 18:14:24', '2021-04-27 18:14:24', 'window', 500, '2021-04-28 01:56:19', '2021-04-28 01:56:19', 'A1', 3),
(2, b'1', '2021-04-28 20:15:35', '2021-04-28 20:15:35', 'LW', 500, '2021-04-28 20:15:35', '2021-04-28 20:15:35', 'A1', 0),
(3, b'1', '2021-04-28 20:15:35', '2021-04-28 20:15:35', 'LM', 500, '2021-04-28 20:15:35', '2021-04-28 20:15:35', 'A2', 0),
(4, b'1', '2021-04-28 20:15:35', '2021-04-28 20:15:35', 'RM', 500, '2021-04-28 20:15:35', '2021-04-28 20:15:35', 'A3', 0),
(5, b'1', '2021-04-28 20:15:35', '2021-04-28 20:15:35', 'RW', 500, '2021-04-28 20:32:18', '2021-04-28 20:32:18', 'A4', 3),
(10, b'1', '2021-04-28 20:23:15', '2021-04-28 20:23:15', 'L', 2500, '2021-04-28 20:23:15', '2021-04-28 20:23:15', 'A1', 0),
(11, b'1', '2021-04-28 20:23:15', '2021-04-28 20:23:15', 'LW', 2500, '2021-04-28 20:23:15', '2021-04-28 20:23:15', 'A2', 0),
(12, b'1', '2021-04-28 20:23:15', '2021-04-28 20:23:15', 'R', 2500, '2021-04-28 20:23:15', '2021-04-28 20:23:15', 'A3', 0),
(13, b'1', '2021-04-28 20:23:15', '2021-04-28 20:23:15', 'R', 2500, '2021-04-28 20:23:15', '2021-04-28 20:23:15', 'A4', 0);

-- --------------------------------------------------------

--
-- Table structure for table `ticket`
--

CREATE TABLE `ticket` (
  `id` bigint(20) NOT NULL,
  `comment` varchar(255) DEFAULT NULL,
  `created_stamp` datetime DEFAULT NULL,
  `created_tx_stamp` datetime DEFAULT NULL,
  `last_updated_stamp` datetime DEFAULT NULL,
  `last_updated_tx_stamp` datetime DEFAULT NULL,
  `serial` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `total_fare` bigint(20) DEFAULT NULL,
  `arrangement_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `buyer_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `ticket`
--

INSERT INTO `ticket` (`id`, `comment`, `created_stamp`, `created_tx_stamp`, `last_updated_stamp`, `last_updated_tx_stamp`, `serial`, `status`, `total_fare`, `arrangement_id`, `user_id`, `buyer_id`) VALUES
(1, 'Buy', '2021-04-28 00:51:03', '2021-04-28 00:51:03', '2021-04-28 20:23:28', '2021-04-28 20:23:28', 'fghtyuhj', 4, 500, 1, NULL, 2),
(2, 'Buy', '2021-04-28 20:32:18', '2021-04-28 20:32:18', '2021-04-28 20:32:18', '2021-04-28 20:32:18', 'V00OP6STNLDS8Y80ZP', 3, 500, 2, NULL, 5);

-- --------------------------------------------------------

--
-- Table structure for table `ticket_seats`
--

CREATE TABLE `ticket_seats` (
  `ticket_id` bigint(20) NOT NULL,
  `seats_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `ticket_seats`
--

INSERT INTO `ticket_seats` (`ticket_id`, `seats_id`) VALUES
(2, 5);

-- --------------------------------------------------------

--
-- Table structure for table `ticket_type`
--

CREATE TABLE `ticket_type` (
  `id` bigint(20) NOT NULL,
  `active` bit(1) NOT NULL,
  `created_stamp` datetime DEFAULT NULL,
  `created_tx_stamp` datetime DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `last_updated_stamp` datetime DEFAULT NULL,
  `last_updated_tx_stamp` datetime DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `program` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `ticket_type`
--

INSERT INTO `ticket_type` (`id`, `active`, `created_stamp`, `created_tx_stamp`, `description`, `last_updated_stamp`, `last_updated_tx_stamp`, `name`, `program`) VALUES
(1, b'1', '2021-04-26 21:46:31', '2021-04-26 21:46:31', 'Bus Journey', '2021-04-26 21:46:31', '2021-04-26 21:46:31', 'BusTicket', 0),
(2, b'1', '2021-04-28 20:02:37', '2021-04-28 20:02:37', 'Air Journey', '2021-04-28 20:02:37', '2021-04-28 20:02:37', 'AirTicket', 0),
(3, b'1', '2021-04-28 20:02:49', '2021-04-28 20:02:49', 'Train Journey', '2021-04-28 20:02:49', '2021-04-28 20:02:49', 'Train', 0),
(4, b'1', '2021-04-28 20:03:07', '2021-04-28 20:03:07', 'Consert', '2021-04-28 20:03:07', '2021-04-28 20:03:07', 'Consert', 1),
(5, b'1', '2021-04-28 20:03:41', '2021-04-28 20:03:41', 'Bangladesh Cricket', '2021-04-28 20:03:41', '2021-04-28 20:03:41', 'CricketTicket', 1);

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `id` bigint(20) NOT NULL,
  `active` bit(1) NOT NULL,
  `created_stamp` datetime DEFAULT NULL,
  `created_tx_stamp` datetime DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `last_updated_stamp` datetime DEFAULT NULL,
  `last_updated_tx_stamp` datetime DEFAULT NULL,
  `mobile` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `user_name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `active`, `created_stamp`, `created_tx_stamp`, `email`, `last_updated_stamp`, `last_updated_tx_stamp`, `mobile`, `password`, `user_name`) VALUES
(1, b'1', '2021-04-25 15:24:32', '2021-04-25 15:24:32', 'shitul.abcoder@gmail.com', '2021-04-25 15:24:32', '2021-04-25 15:24:32', '01831731066', '$2a$10$TnTKEOz9NpS/l8nBSMNb9.sqq3AZHdCNaMrGzltS3R/UFhmCw4Ibe', 'Admin'),
(2, b'1', '2021-04-27 21:05:26', '2021-04-27 21:05:26', 'shitul.abcoder@gmail.com', '2021-04-27 21:05:26', '2021-04-27 21:05:26', '01831731066', '$2a$10$4/YCFwYeYPPc64waOu7kYeCkuNQSPGJ/I.Texl2eHeUavptmqDi0C', 'Shitul');

-- --------------------------------------------------------

--
-- Table structure for table `user_authority`
--

CREATE TABLE `user_authority` (
  `id` bigint(20) NOT NULL,
  `authority_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user_authority`
--

INSERT INTO `user_authority` (`id`, `authority_id`, `user_id`) VALUES
(1, 1, 1),
(2, 1, 2);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `arrangement`
--
ALTER TABLE `arrangement`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKbpjug1hcf5xdnyxu0oikvk0u2` (`ticket_type_id`);

--
-- Indexes for table `arrangement_locations`
--
ALTER TABLE `arrangement_locations`
  ADD PRIMARY KEY (`arrangement_id`,`locations_id`),
  ADD UNIQUE KEY `UK_akymuvt3ge1648ie4buaj2y98` (`locations_id`);

--
-- Indexes for table `arrangement_seats`
--
ALTER TABLE `arrangement_seats`
  ADD PRIMARY KEY (`arrangement_id`,`seats_id`),
  ADD UNIQUE KEY `UK_n3ia81vjghligaenpokxhrjka` (`seats_id`);

--
-- Indexes for table `authority`
--
ALTER TABLE `authority`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `buyer`
--
ALTER TABLE `buyer`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `location`
--
ALTER TABLE `location`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `seat`
--
ALTER TABLE `seat`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `ticket`
--
ALTER TABLE `ticket`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKewu3f0oxfq2geo0mwivnivb63` (`arrangement_id`),
  ADD KEY `FKdvt57mcco3ogsosi97odw563o` (`user_id`),
  ADD KEY `FKeux43qi4hkl3k42pn4iplcdm8` (`buyer_id`);

--
-- Indexes for table `ticket_seats`
--
ALTER TABLE `ticket_seats`
  ADD PRIMARY KEY (`ticket_id`,`seats_id`),
  ADD UNIQUE KEY `UK_7a0j0unfryrjuqx00jc9fy8p7` (`seats_id`);

--
-- Indexes for table `ticket_type`
--
ALTER TABLE `ticket_type`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `user_authority`
--
ALTER TABLE `user_authority`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKgvxjs381k6f48d5d2yi11uh89` (`authority_id`),
  ADD KEY `FKpqlsjpkybgos9w2svcri7j8xy` (`user_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `arrangement`
--
ALTER TABLE `arrangement`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `authority`
--
ALTER TABLE `authority`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `buyer`
--
ALTER TABLE `buyer`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `location`
--
ALTER TABLE `location`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `seat`
--
ALTER TABLE `seat`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT for table `ticket`
--
ALTER TABLE `ticket`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `ticket_type`
--
ALTER TABLE `ticket_type`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `user_authority`
--
ALTER TABLE `user_authority`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `arrangement`
--
ALTER TABLE `arrangement`
  ADD CONSTRAINT `FKbpjug1hcf5xdnyxu0oikvk0u2` FOREIGN KEY (`ticket_type_id`) REFERENCES `ticket_type` (`id`);

--
-- Constraints for table `arrangement_locations`
--
ALTER TABLE `arrangement_locations`
  ADD CONSTRAINT `FK6gfrgvtl5u3fwjpxgcnxcapnr` FOREIGN KEY (`locations_id`) REFERENCES `location` (`id`),
  ADD CONSTRAINT `FKgrsxrhym4cjki20gybl5atjhg` FOREIGN KEY (`arrangement_id`) REFERENCES `arrangement` (`id`);

--
-- Constraints for table `arrangement_seats`
--
ALTER TABLE `arrangement_seats`
  ADD CONSTRAINT `FKb9t2k1i3erd7vqbox7numf4n4` FOREIGN KEY (`seats_id`) REFERENCES `seat` (`id`),
  ADD CONSTRAINT `FKhwcnidk29a6m3kxkj7m45obm8` FOREIGN KEY (`arrangement_id`) REFERENCES `arrangement` (`id`);

--
-- Constraints for table `ticket`
--
ALTER TABLE `ticket`
  ADD CONSTRAINT `FKdvt57mcco3ogsosi97odw563o` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  ADD CONSTRAINT `FKeux43qi4hkl3k42pn4iplcdm8` FOREIGN KEY (`buyer_id`) REFERENCES `buyer` (`id`),
  ADD CONSTRAINT `FKewu3f0oxfq2geo0mwivnivb63` FOREIGN KEY (`arrangement_id`) REFERENCES `arrangement` (`id`);

--
-- Constraints for table `ticket_seats`
--
ALTER TABLE `ticket_seats`
  ADD CONSTRAINT `FK44gchftco6god800pnyfl3f06` FOREIGN KEY (`ticket_id`) REFERENCES `ticket` (`id`),
  ADD CONSTRAINT `FKbk06gvc4iih25uvt94suvg0av` FOREIGN KEY (`seats_id`) REFERENCES `seat` (`id`);

--
-- Constraints for table `user_authority`
--
ALTER TABLE `user_authority`
  ADD CONSTRAINT `FKgvxjs381k6f48d5d2yi11uh89` FOREIGN KEY (`authority_id`) REFERENCES `authority` (`id`),
  ADD CONSTRAINT `FKpqlsjpkybgos9w2svcri7j8xy` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
