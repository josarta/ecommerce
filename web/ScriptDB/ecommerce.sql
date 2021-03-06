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
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categoria`
--

LOCK TABLES `categoria` WRITE;
/*!40000 ALTER TABLE `categoria` DISABLE KEYS */;
INSERT INTO `categoria` VALUES (14,'Mercado'),(15,'Tecnología '),(16,'Moda y accesorios'),(19,'Hogar'),(21,'Salud y Belleza'),(27,'Deportes'),(30,'Juguetes'),(31,'Ferreteria'),(32,'Tecnologia');
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
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `imagenes`
--

LOCK TABLES `imagenes` WRITE;
/*!40000 ALTER TABLE `imagenes` DISABLE KEYS */;
INSERT INTO `imagenes` VALUES (38,'tv.jpg','Producto','20210306174924584.jpg'),(39,'tv.jpg','Producto','20210306174954840.jpg'),(40,'img1.jpg','Producto','20210306175334501.jpg'),(41,'1711327-800-auto.jpg','Producto','20210306175344073.jpg'),(42,'1711327-800-auto.jpg','Producto','20210306175352020.jpg'),(43,'4947642-800-auto.jpg','Producto','20210306175602094.jpg');
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
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `productos`
--

LOCK TABLES `productos` WRITE;
/*!40000 ALTER TABLE `productos` DISABLE KEYS */;
INSERT INTO `productos` VALUES (12,'Televisor','LG','Activo',1,50000,57),(13,'SAN REMO-PLU: 31579','Porta Jabon Dosificador 2.35L','Activo',5000,8990,53),(14,'Televisor','LG','Activo',1,50000,57),(15,'SAMSUNG','GALAXY TAB A7','Activo',20,789900,16),(16,'SAMSUNG','GALAXY TAB A7','Activo',20,789900,16),(17,'SAN REMO-PLU: 31579','Porta Jabon Dosificador 2.35L','Activo',5000,8990,53),(18,'SAMSUNG','GALAXY TAB A7','Activo',20,789900,16),(19,'DAMECOS-PLU: 244504','MOLDE PARA AREPA DAMECOS MPA','Activo',6900,8500,53),(20,'DAMECOS-PLU: 244504','MOLDE PARA AREPA DAMECOS MPA','Activo',6900,8500,53);
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
INSERT INTO `productos_has_imagenes` VALUES (12,38),(12,39),(15,40),(13,41),(13,42),(19,43);
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
) ENGINE=InnoDB AUTO_INCREMENT=69 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sub_categoria`
--

LOCK TABLES `sub_categoria` WRITE;
/*!40000 ALTER TABLE `sub_categoria` DISABLE KEYS */;
INSERT INTO `sub_categoria` VALUES (11,'Vinos y Licores',14),(12,'Cervezas',14),(13,'Cigarrillos',14),(14,'Aseo personal',14),(15,'Aseo del hogar',14),(16,'Tablet',15),(17,'Lacteos',14),(18,'Tablet',15),(19,'Carne, Pollo, y Pescado',14),(20,'Televisores',15),(21,'Televisores',15),(22,'Despensa',14),(23,'Celulares',15),(24,'Computadores',15),(25,'Farmacia',14),(26,'Maquillaje',21),(27,'Higiene Íntima',21),(28,'SmartWatch',15),(29,'Snacks',14),(30,'Higiene Oral',21),(31,'Cuidado Capilar',21),(32,'Cuidado Corporal',21),(33,'Cuidado Facial',21),(34,'Maquillaje',16),(35,'Protección Solar',21),(36,'colchones',19),(37,'Droguería',21),(38,'colchones',19),(39,'Enlatados',14),(40,'Balones',27),(41,'JUGETES',30),(42,'Muebles',19),(43,'Cuidado Masculino',21),(44,'Vestuario',16),(45,'Uniformes',27),(46,'Ropa de Hogar',19),(47,'Aretes',16),(48,'Carros',30),(49,'Suministros y Equipos Médicos',21),(50,'Collares',16),(51,'Tapabocas',21),(52,'Herramientas',30),(53,'Organizadores',19),(54,'Uniformes',27),(55,'Uniformes',27),(56,'Utensilios de Cocina',19),(57,'Televisores',32),(58,'Uniformes',27),(59,'Herramientas',31),(60,'Uniformes',27),(61,'Carros',30),(62,'Vajillas y Cristaleria',19),(63,'Herramientas',30),(64,'Muñecas',30),(65,'Muñecas',30),(66,'Balones',27),(67,'Paracaidismo',27),(68,'Skateboard',27);
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
) ENGINE=InnoDB AUTO_INCREMENT=152 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES (126,'Cedula',1071165710,'Sergio ','Antonio','seantonio@misena.edu.co','1234',1),(127,'Cedula',1001283399,'Johan','Avendaño','freakjda@gmail.com','12345',1),(129,'Cedula',1082314133,'Sergio ','Coba ','Sergiocoba73@gmail.com ','123',1),(130,'Cedula',1000590861,'Maria Paula','Santana Roa','mpsantana1@misena.edu.co','1234',1),(135,'T.Identidad',1005207198,'Melany ','Obando','Michellobando28@gmail.com ','123456',1),(137,'Cedula',1001048594,'Maria Aleejandra','Rabon Mendez','alejandrarabon2019@gmail.com','1234',1),(140,'Cedula',1000944274,'Juan','Quesada','xxjuanquesadaxx@gmail.com','1111',1),(141,'T.Identidad',1001115080,'Felipe Esteban','Nunez Rubiano','feliperubiano74@gmail.com','12345',1),(143,'T.Identidad',1000354511,'Santiago Andres','Ramirez Ruiz','unknownfko@gmail.com','ADSI-6737.107561070555',1),(146,'Cedula',1000128208,'yeison','fonseca','yeisonfonseca2002@gmail.com','1000128208',1),(148,'Cedula',1072660320,'Diana','Soriano','dmsoriano02@misena.edu.co','12345',1),(150,'Cedula',12345677,'Manuel Santiago','Aguilar Moncada','santux44@gmail.com','123',1),(151,'---',1000579618,'Darren Nicolas','Duran Jamaica','dnduran8@misena.edu.co','ahri',1);
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
INSERT INTO `usuario_has_rol` VALUES (126,1),(127,1),(129,1),(130,1),(135,1),(137,1),(140,1),(141,1),(143,1),(146,1),(148,1),(150,1),(126,2),(127,2),(129,2),(130,2),(137,2),(140,2),(141,2),(143,2),(146,2),(148,2),(150,2),(151,2),(126,3),(127,3),(129,3),(130,3),(137,3),(141,3),(148,3),(150,3);
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

-- Dump completed on 2021-03-06 18:00:14
