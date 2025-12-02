-- MySQL dump 10.13  Distrib 8.0.43, for Win64 (x86_64)
--
-- Host: localhost    Database: linguardia
-- ------------------------------------------------------
-- Server version	8.0.43

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

--
-- Table structure for table `ausencias`
--

DROP TABLE IF EXISTS `ausencias`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ausencias` (
  `id` int NOT NULL AUTO_INCREMENT,
  `id_profesor_ausencia` varchar(30) NOT NULL,
  `id_sustituto` varchar(30) NOT NULL,
  `dia_fk` varchar(10) NOT NULL,
  `hora_fk` char(2) NOT NULL,
  `id_tutor_guardia_fk` varchar(30) NOT NULL,
  `id_clase` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_ausente` (`id_profesor_ausencia`),
  KEY `fk_clases_prof` (`id_clase`),
  KEY `fk_guardia_ausencia` (`dia_fk`,`hora_fk`,`id_tutor_guardia_fk`),
  KEY `fk_suplente_prof` (`id_sustituto`),
  CONSTRAINT `fk_ausente` FOREIGN KEY (`id_profesor_ausencia`) REFERENCES `profesores` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_clases_prof` FOREIGN KEY (`id_clase`) REFERENCES `clases` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_guardia_ausencia` FOREIGN KEY (`dia_fk`, `hora_fk`, `id_tutor_guardia_fk`) REFERENCES `guardias` (`dia`, `hora`, `id_tutor`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_suplente_prof` FOREIGN KEY (`id_sustituto`) REFERENCES `profesores` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ausencias`
--

LOCK TABLES `ausencias` WRITE;
/*!40000 ALTER TABLE `ausencias` DISABLE KEYS */;
/*!40000 ALTER TABLE `ausencias` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `clases`
--

DROP TABLE IF EXISTS `clases`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `clases` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nivel` int NOT NULL,
  `tipo` varchar(30) NOT NULL,
  `letra` char(2) NOT NULL,
  `id_tutor` varchar(30) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `nivel_UNIQUE` (`nivel`),
  UNIQUE KEY `tipo_UNIQUE` (`tipo`),
  UNIQUE KEY `letra_UNIQUE` (`letra`),
  UNIQUE KEY `id_tutor_UNIQUE` (`id_tutor`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  CONSTRAINT `fk_clases` FOREIGN KEY (`id_tutor`) REFERENCES `profesores` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clases`
--

LOCK TABLES `clases` WRITE;
/*!40000 ALTER TABLE `clases` DISABLE KEYS */;
/*!40000 ALTER TABLE `clases` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `guardias`
--

DROP TABLE IF EXISTS `guardias`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `guardias` (
  `dia` varchar(10) NOT NULL,
  `hora` char(2) NOT NULL,
  `id_tutor` varchar(30) NOT NULL,
  PRIMARY KEY (`dia`,`hora`,`id_tutor`),
  KEY `fk_guardias_tutor` (`id_tutor`),
  CONSTRAINT `fk_guardias_tutor` FOREIGN KEY (`id_tutor`) REFERENCES `profesores` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `guardias`
--

LOCK TABLES `guardias` WRITE;
/*!40000 ALTER TABLE `guardias` DISABLE KEYS */;
/*!40000 ALTER TABLE `guardias` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `profesores`
--

DROP TABLE IF EXISTS `profesores`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `profesores` (
  `id` varchar(30) NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `apellidos` varchar(100) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_tutor_UNIQUE` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `profesores`
--

LOCK TABLES `profesores` WRITE;
/*!40000 ALTER TABLE `profesores` DISABLE KEYS */;
/*!40000 ALTER TABLE `profesores` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-12-01 14:40:57
