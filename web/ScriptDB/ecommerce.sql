-- MySQL dump 10.13  Distrib 8.0.21, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: ecommerce
-- ------------------------------------------------------
-- Server version	5.7.31-log

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
-- Table structure for table `categoria`
--

DROP TABLE IF EXISTS `categoria`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `categoria` (
  `idcategoria` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idcategoria`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categoria`
--

LOCK TABLES `categoria` WRITE;
/*!40000 ALTER TABLE `categoria` DISABLE KEYS */;
INSERT INTO `categoria` VALUES (10,'Mercado'),(12,'Hogar'),(13,'Ferreteria');
/*!40000 ALTER TABLE `categoria` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `imagenes`
--

DROP TABLE IF EXISTS `imagenes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `imagenes` (
  `idimagenes` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) DEFAULT NULL,
  `tipo` varchar(45) DEFAULT NULL,
  `ruta` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idimagenes`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `imagenes`
--

LOCK TABLES `imagenes` WRITE;
/*!40000 ALTER TABLE `imagenes` DISABLE KEYS */;
/*!40000 ALTER TABLE `imagenes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `productos`
--

DROP TABLE IF EXISTS `productos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `productos` (
  `idProductos` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) DEFAULT NULL,
  `descripcion` varchar(45) DEFAULT NULL,
  `estado` varchar(45) DEFAULT NULL,
  `cantidad` int(11) DEFAULT NULL,
  `valor` float DEFAULT '0',
  `fk_sub_categoriaId` int(11) DEFAULT NULL,
  PRIMARY KEY (`idProductos`),
  KEY `fk_productoSub_idx` (`fk_sub_categoriaId`),
  CONSTRAINT `fk_productoSub` FOREIGN KEY (`fk_sub_categoriaId`) REFERENCES `sub_categoria` (`idsub_categoria`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `productos`
--

LOCK TABLES `productos` WRITE;
/*!40000 ALTER TABLE `productos` DISABLE KEYS */;
INSERT INTO `productos` VALUES (1,'a','a','Activo',1,1,4),(2,'a','a','Activo',1,1,4),(3,'b','b','Activo',10,10,1),(4,'b','b','Activo',10,10,1),(5,'LLave','Pequeña','Activo',120,5500,10),(6,'LLave 5.4\"','Pequeña','Activo',450,120000,10),(7,'Cama simple','de 1.20','Activo',85,500000,1),(8,'Cama doble','de 2.00 madera','Activo',10,12000000,1),(9,'Cama doble','de 2.00 madera','Activo',10,12000000,1),(10,'Cama doble','de 2.00 madera','Activo',10,12000000,1),(11,'Cama doble','de 2.00 madera','Activo',10,12000000,1);
/*!40000 ALTER TABLE `productos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `productos_has_imagenes`
--

DROP TABLE IF EXISTS `productos_has_imagenes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `productos_has_imagenes` (
  `productos_idProductos` int(11) NOT NULL,
  `imagenes_idimagenes` int(11) NOT NULL,
  PRIMARY KEY (`productos_idProductos`,`imagenes_idimagenes`),
  KEY `fk_productos_has_imagenes_imagenes1_idx` (`imagenes_idimagenes`),
  KEY `fk_productos_has_imagenes_productos1_idx` (`productos_idProductos`),
  CONSTRAINT `fk_productos_has_imagenes_imagenes1` FOREIGN KEY (`imagenes_idimagenes`) REFERENCES `imagenes` (`idimagenes`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_productos_has_imagenes_productos1` FOREIGN KEY (`productos_idProductos`) REFERENCES `productos` (`idProductos`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `productos_has_imagenes`
--

LOCK TABLES `productos_has_imagenes` WRITE;
/*!40000 ALTER TABLE `productos_has_imagenes` DISABLE KEYS */;
/*!40000 ALTER TABLE `productos_has_imagenes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rol`
--

DROP TABLE IF EXISTS `rol`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `rol` (
  `idrol` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) DEFAULT NULL,
  `descripcion` varchar(45) DEFAULT NULL,
  `ruta` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idrol`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rol`
--

LOCK TABLES `rol` WRITE;
/*!40000 ALTER TABLE `rol` DISABLE KEYS */;
INSERT INTO `rol` VALUES (1,'Administrador','Todos',NULL),(2,'Cliente','Cliente',NULL),(3,'Vendedor','Vendedor',NULL);
/*!40000 ALTER TABLE `rol` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sub_categoria`
--

DROP TABLE IF EXISTS `sub_categoria`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sub_categoria` (
  `idsub_categoria` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) DEFAULT NULL,
  `fk_categoriaId` int(11) DEFAULT NULL,
  PRIMARY KEY (`idsub_categoria`),
  KEY `fk_cat_idx` (`fk_categoriaId`),
  CONSTRAINT `fk_cat` FOREIGN KEY (`fk_categoriaId`) REFERENCES `categoria` (`idcategoria`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sub_categoria`
--

LOCK TABLES `sub_categoria` WRITE;
/*!40000 ALTER TABLE `sub_categoria` DISABLE KEYS */;
INSERT INTO `sub_categoria` VALUES (1,'Camas',12),(2,'Cocina',12),(3,'Baños',12),(4,'Refrigerados',10),(6,'Cocina Integral',12),(7,'Cocina Integral',12),(8,'Cocina Integral',10),(9,'Tornillos',13),(10,'Herramientas',13);
/*!40000 ALTER TABLE `sub_categoria` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuario` (
  `idUsuario` int(11) NOT NULL AUTO_INCREMENT,
  `tipo_documento` varchar(45) DEFAULT NULL,
  `documento` bigint(12) DEFAULT NULL,
  `nombre` varchar(45) DEFAULT NULL,
  `apellido` varchar(45) DEFAULT NULL,
  `correo` varchar(45) DEFAULT NULL,
  `clave` varchar(45) DEFAULT NULL,
  `estado` tinyint(4) DEFAULT '1',
  PRIMARY KEY (`idUsuario`),
  UNIQUE KEY `correo_UNIQUE` (`correo`)
) ENGINE=InnoDB AUTO_INCREMENT=122 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES (77,'Cedula',797979,'jose','sarta','josarta@misena.edu.co','12345',1),(98,'Cedula',1072660320,'Diana','Soriano','dmsoriano02@misena.edu.co','ADSI-25512.141767393525',1),(99,'Cedula',123896,'Manuel Santiago','Aguilar Moncada','aguilar@gmail.com','123',1),(100,'Cedula',1000579618,'Darren Nicolas','Duran Jamaica','dnduran8@misena.edu.co','ADSI-58491.377977091666',1),(101,'Cedula',1000944274,'Juan David','Quesada Rondon','xxjuanquesadaxx@gmail.com','ADSI-22903.620410858082',1),(102,'Cedula',1071165710,'Sergio ','Antonio','seantonio@misena.edu.co','123456',1),(103,'Cedula',109765098,'jose','chacon','jlchacon8@misena.edu.co','ADSI-30714.534341327693',1),(104,'Cedula',1000128208,'yeison','fonseca','yeisonfonseca2002@gmail.com','12345',1),(105,'Cedula',1001115080,'felipe','Nuñez','feliperubiano74@gmail.com','ADSI-37472.76983976586',1),(106,'Cedula',1007699522,'Daniel Ricardo','Gonzalez villabon','darigovi@gmail.com','ADSI-57657.7808073577',1),(107,'T.Identidad',1000354511,'Santiago Andres','Ramirez Ruiz','unknownfko@gmail.com','ADSI-6445.836700752505',1),(108,'T.Identidad',1005207198,'Melany','Obando','michellobando28@gmail.com','123456',1),(109,'Cedula',1002318561,'yeison alberto','guzman amaris','yeisonguzman2001@gmail.com','123',1),(110,'Cedula',1006128772,'jesus antonio','rozo zapata','jarozo27@misena.edu.co','ADSI-63839.41170621444',1),(111,'T.Identidad',1000590861,'Maria Paula','Santana Roa','maria@gmail.com','12345',1),(112,'Cedula',1001048594,'María Alejandra','Rabón Mendez','alejandrarabon2019@gmail.com','4884',1),(113,'Cedula',1000158623,'yeisson andres','vargas latorre','yvargaslatorre@gmail.com','yeisson12345',1),(114,'Cedula',1000158624,'Jhonathan david','vargas latorre','Footways_11@hotmail.com','david12345',1),(115,'Cedula',7634567,'yeison','amaris','yaguzman21@gmail.com','1234',1),(116,'T.Identidad',1000590861,'a','Roa','mpsantana1@misena.edu.co','ADSI-62187.477520686116',1),(117,'Cedula',12345,'Manuel','Aguilar','aguilarmoncada2002@gmail.com','ADSI-32702.44978684299',1),(119,'Cedula',1001048594,'María Alejandra','Rabón Mendez','marabon@misena.edu.co','1224',1),(121,'Cedula',1000590861,'Maria Paula','Santana Roa','mariapauu16@gmail.com','ADSI-32662.403777793505',1);
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario_has_rol`
--

DROP TABLE IF EXISTS `usuario_has_rol`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuario_has_rol` (
  `usuario_idUsuario` int(11) NOT NULL,
  `rol_idrol` int(11) NOT NULL,
  PRIMARY KEY (`usuario_idUsuario`,`rol_idrol`),
  KEY `fk_usuario_has_rol_rol1_idx` (`rol_idrol`),
  KEY `fk_usuario_has_rol_usuario_idx` (`usuario_idUsuario`),
  CONSTRAINT `fk_usuario_has_rol_rol1` FOREIGN KEY (`rol_idrol`) REFERENCES `rol` (`idrol`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_usuario_has_rol_usuario` FOREIGN KEY (`usuario_idUsuario`) REFERENCES `usuario` (`idUsuario`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario_has_rol`
--

LOCK TABLES `usuario_has_rol` WRITE;
/*!40000 ALTER TABLE `usuario_has_rol` DISABLE KEYS */;
INSERT INTO `usuario_has_rol` VALUES (77,1),(98,1),(99,1),(100,1),(101,1),(103,1),(105,1),(109,1),(110,1),(114,1),(99,2),(100,2),(101,2),(102,2),(103,2),(104,2),(105,2),(106,2),(107,2),(108,2),(109,2),(110,2),(111,2),(112,2),(113,2),(114,2),(115,2),(116,2),(117,2),(119,2),(121,2),(98,3),(99,3),(100,3),(101,3),(103,3),(105,3),(109,3),(110,3);
/*!40000 ALTER TABLE `usuario_has_rol` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-02-27 16:42:40
