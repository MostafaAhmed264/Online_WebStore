-- MySQL dump 10.13  Distrib 8.0.29, for Win64 (x86_64)
--
-- Host: localhost    Database: store
-- ------------------------------------------------------
-- Server version	8.0.29

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
-- Table structure for table `client`
--

DROP TABLE IF EXISTS `client`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `client` (
  `Email` varchar(200) NOT NULL,
  `pasw` varchar(200) NOT NULL,
  `Fname` varchar(100) DEFAULT NULL,
  `Lname` varchar(100) DEFAULT NULL,
  `Phone` varchar(300) DEFAULT NULL,
  `Address` varchar(300) DEFAULT NULL,
  `Balance` double DEFAULT NULL,
  PRIMARY KEY (`Email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `client`
--

LOCK TABLES `client` WRITE;
/*!40000 ALTER TABLE `client` DISABLE KEYS */;
INSERT INTO `client` VALUES ('a','a','aa','aaa','192121','abbsia',4020),('Adel@gmail.com','123456','adel','sayed','11548625','ainshams',2925),('fadiola@gmail.com','fadiola','fady','ibrahim','1280919609','ElThawra',1880),('fadyibrahim@gmail.com','fadyola1','fady','abo sena','1280919609','soura street',5000),('fares@gmail.com','fares123','fares','wafy','1148165486','nasrcity',1000),('feso@gmail.com','12345','Fares','wafy','011481234','nasrocity',2000),('mostafa7@gmail.com','mostafa74','Mostafa','Hassan','1100834520','abbsia',5270),('mostafa704@gmail.com','13423','xczzxczc','xcsad','1234','fasdf4',7000),('sadad@gmail.com','1234','dsada','weqewq','32323','dsadas',8000),('sadas','123213','asdasd','asdasd','213123','sdaf',234),('tasd','123','asd','asd','124124','asdf',123),('tsga','asdf','asd','asd','1234','asdasd',213),('wafy55@gmail.com','wafy55','mostafa','wafy','0100232732','2bc',9000),('yhjhh','1234','dsdddd','feasdg','512355','dfsadsf',5165),('zyad12@gmail.com','zyad2000','zyad','fahmy','109008708','awl abbas',6000);
/*!40000 ALTER TABLE `client` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `has`
--

DROP TABLE IF EXISTS `has`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `has` (
  `orderid` int NOT NULL,
  `itemname` varchar(30) NOT NULL,
  `quantity` int DEFAULT NULL,
  PRIMARY KEY (`orderid`,`itemname`),
  KEY `itemname` (`itemname`),
  CONSTRAINT `has_ibfk_1` FOREIGN KEY (`orderid`) REFERENCES `o_order` (`orderid`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `has_ibfk_2` FOREIGN KEY (`itemname`) REFERENCES `item` (`ItemName`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `has`
--

LOCK TABLES `has` WRITE;
/*!40000 ALTER TABLE `has` DISABLE KEYS */;
INSERT INTO `has` VALUES (0,'headphones',1),(1,'headphones',1),(2,'headphones',1),(2,'headspeakers',2),(3,'headphones',1),(3,'headspeakers',3),(3,'powerbank',2),(4,'headphones',1),(5,'headphones',1),(6,'headspeakers',1),(8,'headspeakers',2),(9,'headspeakers',1),(10,'headspeakers',1),(11,'headspeakers',1),(12,'headspeakers',2),(13,'headspeakers',1),(14,'headspeakers',2),(15,'headspeakers',3),(16,'headphones',3),(16,'headspeakers',3),(17,'headphones',1),(18,'headphones',4),(18,'powerbank',1),(19,'headphones',1),(20,'headphones',1),(21,'headphones',2),(22,'headphones',2),(23,'lancer',1),(24,'Iphone13ProMax',1),(25,'LenovoA6',1),(26,'LenovoA6',1),(27,'HuwaeiNova5t',1),(28,'Opporeno6',1),(29,'headphones',1),(30,'Opporeno6',1),(31,'headphones',1),(32,'lancer',1),(33,'headphones',2),(34,'lancer',1),(35,'lancer',1);
/*!40000 ALTER TABLE `has` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `item`
--

DROP TABLE IF EXISTS `item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `item` (
  `ItemName` varchar(30) NOT NULL,
  `ItemId` int DEFAULT NULL,
  `Price` int DEFAULT NULL,
  `Category` varchar(100) DEFAULT NULL,
  `Quantity` int DEFAULT NULL,
  PRIMARY KEY (`ItemName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `item`
--

LOCK TABLES `item` WRITE;
/*!40000 ALTER TABLE `item` DISABLE KEYS */;
INSERT INTO `item` VALUES ('gamingChair',15,3000,'Furniture',7),('headphones',1,70,'Accessories',0),('headspeakers',2,50,'Accessories',0),('HuwaeiNova5t',8,2500,'Phone',9),('Iphone12',18,4000,'Phone',12),('Iphone13ProMax',9,3000,'Phone',11),('lancer',3,2000,'cars',1),('LenovoA6',10,1800,'Phone',12),('LG',12,3500,'TV',10),('Oneplus7t',17,4000,'Phone',8),('Opporeno6',4,2000,'Phone',8),('powerbank',5,100,'Accessories',12),('RoundTable',16,1500,'Furniture',10),('SamsungGalaxynote20',20,3400,'Phone',10),('SamsungGalaxys21',21,3200,'Phone',12),('SamsungGalaxys22',19,3800,'Phone',13),('samsungs10',6,4000,'Phone',10),('samsungTv',11,4000,'TV',8),('short',7,250,'Clothes',5),('Tornado',14,2200,'TV',13),('Toshiba',13,2500,'TV',12);
/*!40000 ALTER TABLE `item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `o_order`
--

DROP TABLE IF EXISTS `o_order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `o_order` (
  `orderid` int NOT NULL,
  `Email` varchar(30) NOT NULL,
  `reqdate` date DEFAULT NULL,
  PRIMARY KEY (`orderid`),
  KEY `Email` (`Email`),
  CONSTRAINT `o_order_ibfk_1` FOREIGN KEY (`Email`) REFERENCES `client` (`Email`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `o_order`
--

LOCK TABLES `o_order` WRITE;
/*!40000 ALTER TABLE `o_order` DISABLE KEYS */;
INSERT INTO `o_order` VALUES (0,'mostafa7@gmail.com','2022-06-29'),(1,'mostafa7@gmail.com','2022-06-29'),(2,'mostafa7@gmail.com','2022-06-29'),(3,'mostafa7@gmail.com','2022-06-29'),(4,'mostafa7@gmail.com','2022-06-29'),(5,'Adel@gmail.com','2022-06-29'),(6,'Adel@gmail.com','2022-06-29'),(7,'mostafa7@gmail.com','2022-06-29'),(8,'Adel@gmail.com','2022-06-29'),(9,'mostafa7@gmail.com','2022-06-29'),(10,'mostafa7@gmail.com','2022-06-29'),(11,'mostafa7@gmail.com','2022-06-29'),(12,'Adel@gmail.com','2022-06-29'),(13,'Adel@gmail.com','2022-06-29'),(14,'mostafa7@gmail.com','2022-06-29'),(15,'Adel@gmail.com','2022-06-29'),(16,'Adel@gmail.com','2022-06-29'),(17,'mostafa7@gmail.com','2022-06-29'),(18,'fadiola@gmail.com','2022-06-29'),(19,'mostafa7@gmail.com','2022-06-29'),(20,'mostafa7@gmail.com','2022-06-29'),(21,'mostafa7@gmail.com','2022-06-29'),(22,'a','2022-06-29'),(23,'fadiola@gmail.com','2022-06-29'),(24,'a','2022-06-29'),(25,'a','2022-06-30'),(26,'a','2022-06-30'),(27,'a','2022-06-30'),(28,'a','2022-06-30'),(29,'a','2022-06-30'),(30,'mostafa7@gmail.com','2022-06-30'),(31,'a','2022-07-01'),(32,'a','2022-07-01'),(33,'fadiola@gmail.com','2022-07-01'),(34,'fadiola@gmail.com','2022-07-01'),(35,'fadiola@gmail.com','2022-07-01');
/*!40000 ALTER TABLE `o_order` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-07-01 23:33:13
