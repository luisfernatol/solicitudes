-- MySQL dump 10.13  Distrib 8.0.36, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: dbsemillero
-- ------------------------------------------------------
-- Server version	8.0.36

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
-- Table structure for table `alerta`
--

DROP TABLE IF EXISTS `alerta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `alerta` (
  `nm_id_alerta` int NOT NULL AUTO_INCREMENT,
  `ds_asunto` varchar(20) DEFAULT NULL,
  `ds_destinatario` varchar(40) DEFAULT NULL,
  `ds_contenido_alerta` varchar(40) DEFAULT NULL,
  `ds_estado_alerta` varchar(10) DEFAULT NULL,
  `fe_fecha_creacion` date DEFAULT (curdate()),
  PRIMARY KEY (`nm_id_alerta`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `alerta`
--

LOCK TABLES `alerta` WRITE;
/*!40000 ALTER TABLE `alerta` DISABLE KEYS */;
/*!40000 ALTER TABLE `alerta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cargos`
--

DROP TABLE IF EXISTS `cargos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cargos` (
  `nm_id_cargo` int NOT NULL AUTO_INCREMENT,
  `ds_cargo` varchar(50) DEFAULT NULL,
  `ds_descripcion` varchar(100) DEFAULT NULL,
  `fe_fecha_creacion` date DEFAULT (curdate()),
  `ds_activo` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`nm_id_cargo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cargos`
--

LOCK TABLES `cargos` WRITE;
/*!40000 ALTER TABLE `cargos` DISABLE KEYS */;
/*!40000 ALTER TABLE `cargos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `empleado`
--

DROP TABLE IF EXISTS `empleado`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `empleado` (
  `nm_id_empleado` int NOT NULL AUTO_INCREMENT,
  `nm_documento` int DEFAULT NULL,
  `ds_tipo_documento` varchar(30) DEFAULT NULL,
  `ds_nombre` varchar(30) DEFAULT NULL,
  `ds_apellido` varchar(30) DEFAULT NULL,
  `ds_telefono` varchar(14) DEFAULT NULL,
  `ds_direccion` varchar(60) DEFAULT NULL,
  `fe_fecha_ingreso` date DEFAULT NULL,
  `fe_fecha_retiro` date DEFAULT NULL,
  `ds_tipo_contrato` varchar(20) DEFAULT NULL,
  `ds_estado_empleado` varchar(10) DEFAULT NULL,
  `nm_supervisor_inmediato` int DEFAULT NULL,
  `nm_cargo` int DEFAULT NULL,
  PRIMARY KEY (`nm_id_empleado`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `empleado`
--

LOCK TABLES `empleado` WRITE;
/*!40000 ALTER TABLE `empleado` DISABLE KEYS */;
INSERT INTO `empleado` VALUES (1,5252525,'cc','Luz Fernanda','Martinez Gomez','44654','diagonal 110','2015-05-04','2027-05-11','vigente','activo',9,9),(2,4549426,'cc','Dan','MTZ','5649549','cc 876','2015-05-09','2025-05-07','vigente','activo',10,10),(3,99999,'cc','Roberto','Lopez','755661','diagonal 45','2019-05-03','2025-05-03','vigente','activo',9,10),(7,99997,'cc','Samiel','Gomez','4644949','diagonal 47','2019-05-03','2025-05-03','temporal','activo',10,10),(8,54948,'cc','Sol','Gomez','44984','diagonal 47','2024-02-25','2025-05-03','vigente','activo',9,10),(9,4545,'cc','Adriana','Vera','5675764','diagonal 55','2024-02-25','2025-05-03','vigente','activo',10,3),(10,56575,'cc','carlos','Cez','45436','diagonal 55','2019-05-03','2025-05-03','vigente','activo',9,4),(11,43545,'cc','jose','Baez','4366','diagonal 58','2015-05-03','2025-05-03','vigente','activo',10,2),(12,656657,'cc','Martin','Ortiz','3456456','diagonal 58','2015-05-03','2025-05-03','vigente','activo',11,8),(13,77755,'cc','goku','Jr','5646446','46464 mazana','2024-03-19','2024-06-14','temporal','activo',8,4),(14,445555,'cc','Gohan ','Jr','645464','255','2024-03-14','2024-12-06','temporal','activo',11,5),(15,5565465,'cc','Jose','garcia','447545','diagonal 78','2024-03-05','2025-07-17','vigente','activo',11,4),(16,777456,'cc','goku','Son','4541464','namek 4524','2016-02-02','2026-11-18','vigente','activo',10,45),(17,7654645,'cc','Vegetta','Jr','45554654','Diagonal 75','2016-06-07','2026-07-15','vigente','actuvo',8,4);
/*!40000 ALTER TABLE `empleado` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rol_usuario`
--

DROP TABLE IF EXISTS `rol_usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `rol_usuario` (
  `nm_id_rol` int NOT NULL AUTO_INCREMENT,
  `ds_rol` varchar(30) DEFAULT NULL,
  `fe_fecha_creacion` date DEFAULT (curdate()),
  PRIMARY KEY (`nm_id_rol`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rol_usuario`
--

LOCK TABLES `rol_usuario` WRITE;
/*!40000 ALTER TABLE `rol_usuario` DISABLE KEYS */;
INSERT INTO `rol_usuario` VALUES (1,'empleado','2020-05-03'),(2,'administrador','2020-05-03'),(3,'supervisor','2020-05-03'),(4,'maestro_Empleados','2020-05-03');
/*!40000 ALTER TABLE `rol_usuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `solicitud_vaciones`
--

DROP TABLE IF EXISTS `solicitud_vaciones`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `solicitud_vaciones` (
  `nm_id_solicitud` int NOT NULL AUTO_INCREMENT,
  `nm_id_usuario` int DEFAULT NULL,
  `nm_dias_solicita` int DEFAULT NULL,
  `fe_fecha_inicio` date DEFAULT NULL,
  `fe_fecha_fin` date DEFAULT NULL,
  `fe_fecha_retorna` date DEFAULT NULL,
  `ds_estado` varchar(10) DEFAULT NULL,
  `ds_observaciones` varchar(60) DEFAULT NULL,
  `fe_fecha_creacion` date DEFAULT (curdate()),
  PRIMARY KEY (`nm_id_solicitud`),
  KEY `nm_id_usuario` (`nm_id_usuario`),
  CONSTRAINT `solicitud_vaciones_ibfk_1` FOREIGN KEY (`nm_id_usuario`) REFERENCES `usuario` (`nm_id_usuario`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `solicitud_vaciones`
--

LOCK TABLES `solicitud_vaciones` WRITE;
/*!40000 ALTER TABLE `solicitud_vaciones` DISABLE KEYS */;
INSERT INTO `solicitud_vaciones` VALUES (6,2,9,'2024-08-31','2024-09-08','2024-09-09','pendiente','ninguna','2024-02-29'),(7,2,9,'2024-09-01','2024-09-09','2024-09-10','pendiente','ninguna','2024-03-01'),(17,20,9,'2024-09-03','2024-09-11','2024-09-12','pendiente','ninguna','2024-03-01'),(18,5,10,'2024-05-01','2024-05-06','2024-05-06','pendiente','ninguna','2024-03-06'),(19,19,6,'2024-05-01','2024-05-08','2024-05-09','pendiente','ninguna','2024-03-06'),(20,1,10,'2024-05-01','2024-05-14','2024-05-15','pendiente','ninguna','2024-03-06'),(21,2,9,'2024-05-01','2024-05-13','2024-05-14','pendiente','ninguna','2024-03-06'),(22,19,6,'2024-05-01','2024-05-08','2024-05-09','pendiente','ninguna','2024-03-21'),(23,18,6,'2024-05-01','2024-05-08','2024-05-09','pendiente','no','2024-03-22'),(24,16,7,'2024-05-01','2024-05-09','2024-05-10','pendiente','no','2024-03-22');
/*!40000 ALTER TABLE `solicitud_vaciones` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuario` (
  `nm_id_usuario` int NOT NULL AUTO_INCREMENT,
  `nm_id_empleado` int DEFAULT NULL,
  `correo` varchar(100) DEFAULT NULL,
  `fe_fecha_creacion` date DEFAULT (curdate()),
  `ds_activo` varchar(10) DEFAULT NULL,
  `ds_contraseña` varchar(100) DEFAULT NULL,
  `nm_rol` int DEFAULT NULL,
  PRIMARY KEY (`nm_id_usuario`),
  KEY `nm_id_empleado` (`nm_id_empleado`),
  KEY `nm_rol` (`nm_rol`),
  CONSTRAINT `usuario_ibfk_1` FOREIGN KEY (`nm_id_empleado`) REFERENCES `empleado` (`nm_id_empleado`),
  CONSTRAINT `usuario_ibfk_2` FOREIGN KEY (`nm_rol`) REFERENCES `rol_usuario` (`nm_id_rol`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES (1,1,'lol@hotmail.com','2020-05-03','si','123456789',1),(2,2,'ul@hotmail.com','2020-05-03','si','1234',1),(3,7,'wul@hotmail.com','2024-03-07','si','54545',1),(5,8,'u22l@hotmail.com','2024-03-07','si','343434',3),(16,3,'u2a22l@hotmail.com','2024-03-07','si','5643453',2),(17,9,'u2s2l@hotmail.com','2024-03-07','si','435345',3),(18,10,'ss@hotmail.com','2024-03-07','si','444433',3),(19,11,'kg@hotmail.com','2024-03-07','si','234356',3),(20,12,'kwg@hotmail.com','2024-03-07','si','646444',4);
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-03-22 13:43:10
