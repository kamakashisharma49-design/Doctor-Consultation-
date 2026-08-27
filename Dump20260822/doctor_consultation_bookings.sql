CREATE DATABASE  IF NOT EXISTS `doctor_consultation` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `doctor_consultation`;
-- MySQL dump 10.13  Distrib 8.0.46, for macos15 (arm64)
--
-- Host: localhost    Database: doctor_consultation
-- ------------------------------------------------------
-- Server version	9.7.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
SET @MYSQLDUMP_TEMP_LOG_BIN = @@SESSION.SQL_LOG_BIN;
SET @@SESSION.SQL_LOG_BIN= 0;

--
-- GTID state at the beginning of the backup 
--

SET @@GLOBAL.GTID_PURGED=/*!80000 '+'*/ 'd7e0654a-88b7-11f1-87ab-240093e0a8ab:1-127';

--
-- Table structure for table `bookings`
--

DROP TABLE IF EXISTS `bookings`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bookings` (
  `booking_id` int NOT NULL AUTO_INCREMENT,
  `doctor_email` varchar(150) NOT NULL,
  `patient_email` varchar(150) NOT NULL,
  `patient_name` varchar(150) NOT NULL,
  `appointment_date` date NOT NULL,
  `appointment_time` time NOT NULL,
  `status` varchar(30) DEFAULT 'Pending',
  `gender` varchar(20) DEFAULT NULL,
  `age` int DEFAULT NULL,
  `blood_group` varchar(30) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`booking_id`),
  UNIQUE KEY `unique_doctor_slot` (`doctor_email`,`appointment_date`,`appointment_time`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bookings`
--

LOCK TABLES `bookings` WRITE;
/*!40000 ALTER TABLE `bookings` DISABLE KEYS */;
INSERT INTO `bookings` VALUES (2,'Cardiologist','123@gmail.com','Abhi','2026-08-10','09:00:00','Booked','gender',NULL,'blood_group','phone'),(5,'Cardiologist','123@gmail.com','Abhi','2026-08-11','09:30:00','Booked','gender',NULL,'blood_group','phone'),(6,'Cardiologist','123@gmail.com','Abhi','2026-08-11','11:00:00','Booked','gender',NULL,'blood_group','phone'),(7,'Cardiologist','23@gmail.com','kamakshi','2026-08-18','17:30:00','Booked','Female',21,'A-','7814998042'),(11,'kamakshi49@gmail.com','123@gmail.com','abhi','2026-08-11','12:30:00','Declined','Male',26,'A+','0987654321'),(13,'12@gmail.com','123@gmail.com','abhi','2026-08-25','10:30:00','Accepted','Male',26,'A+','0987654321'),(16,'kamakshi49@gmail.com','yatinsood704@gmail.com','Yatin Sood','2026-08-03','09:30:00','Pending','Male',25,'A-','09876543'),(17,'armaans1231@gmail.com','kashikakd@gmail.com','kashika','2026-08-20','09:30:00','Accepted','Female',20,'A-','0987654321'),(18,'armaans1231@gmail.com','kashikakd@gmail.com','kashika','2026-08-21','10:30:00','Accepted','Female',20,'A-','0987654321'),(19,'armaans1231@gmail.com','judgeapram@gmail.com','Apram Singh','2026-08-24','09:00:00','Accepted','Male',20,'A+','781220987'),(20,'armaans1231@gmail.com','kashikakd@gmail.com','kashika','2026-08-24','09:30:00','Accepted','Female',20,'A-','0987654321');
/*!40000 ALTER TABLE `bookings` ENABLE KEYS */;
UNLOCK TABLES;
SET @@SESSION.SQL_LOG_BIN = @MYSQLDUMP_TEMP_LOG_BIN;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-08-22 17:28:53
