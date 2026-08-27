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
-- Table structure for table `doctor`
--

DROP TABLE IF EXISTS `doctor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `doctor` (
  `email` varchar(50) NOT NULL,
  `fullname` varchar(50) NOT NULL,
  `pass` varchar(50) NOT NULL,
  `speciality` varchar(50) NOT NULL,
  `experience` varchar(50) NOT NULL,
  `address` varchar(50) NOT NULL,
  `consultationFee` varchar(500) NOT NULL,
  `phoneNumber` varchar(50) DEFAULT NULL,
  `gender` varchar(50) DEFAULT NULL,
  `profile_picture` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `doctor`
--

LOCK TABLES `doctor` WRITE;
/*!40000 ALTER TABLE `doctor` DISABLE KEYS */;
INSERT INTO `doctor` VALUES ('armaans1231@gmail.com','Armaan Singh','098765','Cardiologist','9','331 A Block , Ranjit Avenue','500',NULL,NULL,'uploads/doctor_88f1bdca-8692-4592-ab21-6cfbcac38682.jpeg'),('dilpreet@gmail.com','Dilpreet Kaur','123456','General Physician','12','Gurdaspur','200',NULL,NULL,'uploads/doctor_5da84096-26b9-4bb0-b158-2b0c0382b973.png'),('harman@gmail.com','Harman Singh Gill','123456','Pediatrician','11','Kabir Park','500',NULL,NULL,'uploads/doctor_e8179784-3b1d-4d57-85f9-bb1c537dc901.jpeg'),('kamakashisharma49@gmail.com','Kamakshi Sharma','123456','Dermatologist','10','46,Beri Gate','300',NULL,NULL,'uploads/doctor_a073b043-6340-43b7-aada-6a6173331fbf.jpeg'),('kayna@gmail.com','Kayna','123456','Orthopedic','8','Batala Road','700',NULL,NULL,'uploads/doctor_24508491-2e87-457a-8b6f-d45d6f9502a8.png'),('krish@gmail.com','Krish Sharma','123456','Neurologist','11','Batala Road','600',NULL,NULL,'uploads/doctor_13404a26-59e0-46f7-8fec-bff19c071980.png');
/*!40000 ALTER TABLE `doctor` ENABLE KEYS */;
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
