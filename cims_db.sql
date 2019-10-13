CREATE DATABASE  IF NOT EXISTS `cims` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `cims`;
-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: cims
-- ------------------------------------------------------
-- Server version	5.7.19-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `car`
--

DROP TABLE IF EXISTS `car`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `car` (
  `car_id` int(11) NOT NULL,
  `plate_no` varchar(10) DEFAULT NULL,
  `brand` varchar(50) DEFAULT NULL,
  `model` varchar(50) DEFAULT NULL,
  `prod_yr` int(11) DEFAULT NULL,
  `color` varchar(20) DEFAULT NULL,
  `mileage` int(11) DEFAULT NULL,
  `photo` varchar(100) DEFAULT NULL,
  `description` varchar(1000) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `down_payment` double DEFAULT NULL,
  `inv_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`car_id`),
  KEY `fk_car_inventory` (`inv_id`),
  CONSTRAINT `fk_car_inventory` FOREIGN KEY (`inv_id`) REFERENCES `inventory` (`inv_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `car`
--

LOCK TABLES `car` WRITE;
/*!40000 ALTER TABLE `car` DISABLE KEYS */;
INSERT INTO `car` VALUES (5,'ARR-2346','Toyota','Prius',2018,'White',25,'inv_4_1570934562787_1_XbuW8WuRrAY5pC4t-9DZAQ.jpeg','Used, Second Owner.',5000000,1000000,4),(11,'XY-5567','Nissan','GTR',2015,'Black',23568,NULL,'Used, Third Owner.',5500000,1500000,10),(21,'PQR-1133','Mclaren','720s',2018,'Orange',500,NULL,'Brand New, All Options',10500000,4000000,20),(37,'QW-4356','Toyota','Corolla',2002,'White',89000,NULL,'Used, Second Owner.',1500000,500000,36),(57,'ARR-2345','Toyota','Prius',2018,'White',25,'inv_56_1570909366014toyota-prius-2019.jpg','Used, Second Owner.',5000000,1000000,56);
/*!40000 ALTER TABLE `car` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cart`
--

DROP TABLE IF EXISTS `cart`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cart` (
  `user_id` int(11) NOT NULL,
  `inv_id` int(11) NOT NULL,
  `quantity` int(11) DEFAULT NULL,
  PRIMARY KEY (`user_id`,`inv_id`),
  KEY `fk_cart_inventory` (`inv_id`),
  CONSTRAINT `fk_cart_inventory` FOREIGN KEY (`inv_id`) REFERENCES `inventory` (`inv_id`),
  CONSTRAINT `fk_cart_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cart`
--

LOCK TABLES `cart` WRITE;
/*!40000 ALTER TABLE `cart` DISABLE KEYS */;
INSERT INTO `cart` VALUES (1,8,2),(23,4,1),(23,6,7),(23,8,3),(23,56,2);
/*!40000 ALTER TABLE `cart` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `favourite`
--

DROP TABLE IF EXISTS `favourite`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `favourite` (
  `fav_id` int(11) NOT NULL,
  `user_id` int(11) DEFAULT NULL,
  `inv_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`fav_id`),
  KEY `fk_favourite_user` (`user_id`),
  KEY `fk_favourite_inventory` (`inv_id`),
  CONSTRAINT `fk_favourite_inventory` FOREIGN KEY (`inv_id`) REFERENCES `inventory` (`inv_id`),
  CONSTRAINT `fk_favourite_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `favourite`
--

LOCK TABLES `favourite` WRITE;
/*!40000 ALTER TABLE `favourite` DISABLE KEYS */;
INSERT INTO `favourite` VALUES (12,1,10),(22,1,20),(24,23,20),(27,1,6),(35,1,4),(38,23,36);
/*!40000 ALTER TABLE `favourite` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequence`
--

LOCK TABLES `hibernate_sequence` WRITE;
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT INTO `hibernate_sequence` VALUES (62);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `inventory`
--

DROP TABLE IF EXISTS `inventory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `inventory` (
  `inv_id` int(11) NOT NULL,
  `added_on` datetime DEFAULT NULL,
  `exp_on` datetime DEFAULT NULL,
  `item_type` varchar(10) DEFAULT NULL,
  `stock` int(11) DEFAULT NULL,
  `state` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`inv_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `inventory`
--

LOCK TABLES `inventory` WRITE;
/*!40000 ALTER TABLE `inventory` DISABLE KEYS */;
INSERT INTO `inventory` VALUES (4,'2019-10-05 19:15:30',NULL,'car',1,'available'),(6,'2019-10-05 19:38:47',NULL,'part',7,'available'),(8,'2019-10-05 19:44:41',NULL,'part',47,'available'),(10,'2019-10-05 19:51:38',NULL,'car',1,'available'),(20,'2019-10-05 22:00:47',NULL,'car',0,'available'),(36,'2019-10-06 21:25:19',NULL,'car',1,'available'),(56,'2019-10-13 01:11:57',NULL,'car',1,'available'),(58,'2019-10-13 01:19:38',NULL,'part',19,'available');
/*!40000 ALTER TABLE `inventory` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `login`
--

DROP TABLE IF EXISTS `login`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `login` (
  `username` varchar(255) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `role` varchar(255) DEFAULT NULL,
  `state` varchar(10) DEFAULT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`username`),
  KEY `fk_login_userid_idx` (`user_id`),
  CONSTRAINT `fk_login_userid` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `login`
--

LOCK TABLES `login` WRITE;
/*!40000 ALTER TABLE `login` DISABLE KEYS */;
INSERT INTO `login` VALUES ('chanaka@gmail.com','123456','cust','active',61),('chathurya@gmail.com','123456','cust','active',23),('geeth@gmail.com','123456','admin','active',25),('hiranthaathapaththu@gmail.com','123456','admin','active',1);
/*!40000 ALTER TABLE `login` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_`
--

DROP TABLE IF EXISTS `order_`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `order_` (
  `order_id` int(11) NOT NULL,
  `order_date` datetime DEFAULT NULL,
  `buyer_id` int(11) DEFAULT NULL,
  `pay_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`order_id`),
  KEY `fk_order_user` (`buyer_id`),
  KEY `fk_order_payment` (`pay_id`),
  CONSTRAINT `fk_order_payment` FOREIGN KEY (`pay_id`) REFERENCES `payment` (`pay_id`),
  CONSTRAINT `fk_order_user` FOREIGN KEY (`buyer_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_`
--

LOCK TABLES `order_` WRITE;
/*!40000 ALTER TABLE `order_` DISABLE KEYS */;
INSERT INTO `order_` VALUES (41,'2019-10-07 22:26:34',1,40),(43,'2019-10-08 22:33:57',1,42),(45,'2019-10-09 22:51:56',1,44),(47,'2019-10-09 22:53:24',23,46),(49,'2019-10-09 23:21:18',25,48);
/*!40000 ALTER TABLE `order_` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_item_list`
--

DROP TABLE IF EXISTS `order_item_list`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `order_item_list` (
  `order_id` int(11) NOT NULL,
  `inv_id` int(11) NOT NULL,
  `quantity` int(11) DEFAULT NULL,
  `price_per_unit` int(11) DEFAULT NULL,
  PRIMARY KEY (`order_id`,`inv_id`),
  CONSTRAINT `fk_order_item_list_order_` FOREIGN KEY (`order_id`) REFERENCES `order_` (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_item_list`
--

LOCK TABLES `order_item_list` WRITE;
/*!40000 ALTER TABLE `order_item_list` DISABLE KEYS */;
INSERT INTO `order_item_list` VALUES (41,6,3,60000),(41,8,4,45000),(41,20,1,230000),(43,6,6,60000),(43,8,4,45000),(43,20,1,230000),(45,6,1,60000),(45,8,1,45000),(45,20,1,230000),(47,6,1,60000),(47,8,1,45000),(47,20,1,230000),(49,6,1,60000),(49,8,1,45000),(49,20,1,230000);
/*!40000 ALTER TABLE `order_item_list` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `part`
--

DROP TABLE IF EXISTS `part`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `part` (
  `part_id` int(11) NOT NULL,
  `part_name` varchar(50) DEFAULT NULL,
  `brand` varchar(20) DEFAULT NULL,
  `photo` varchar(100) DEFAULT NULL,
  `description` varchar(1000) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `inv_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`part_id`),
  KEY `fk_part_inventory` (`inv_id`),
  CONSTRAINT `fk_part_inventory` FOREIGN KEY (`inv_id`) REFERENCES `inventory` (`inv_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `part`
--

LOCK TABLES `part` WRITE;
/*!40000 ALTER TABLE `part` DISABLE KEYS */;
INSERT INTO `part` VALUES (7,'new part name','Suzuki',NULL,'new desc',99,6),(9,'V8 Engine','Ford',NULL,'V8, 550 HP, Brand New',1850000,8),(59,'Engine Oil','Nulon','inv_58_1570909784877_nulon.jpg','1 liter',5000,58);
/*!40000 ALTER TABLE `part` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `payment`
--

DROP TABLE IF EXISTS `payment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `payment` (
  `pay_id` int(11) NOT NULL,
  `card_type` varchar(20) DEFAULT NULL,
  `card_no` varchar(20) DEFAULT NULL,
  `card_holder` varchar(50) DEFAULT NULL,
  `card_csv` int(11) DEFAULT NULL,
  `card_exp_date` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`pay_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payment`
--

LOCK TABLES `payment` WRITE;
/*!40000 ALTER TABLE `payment` DISABLE KEYS */;
INSERT INTO `payment` VALUES (40,'Visa','1234 5678 9101 1121','Hirantha Athapaththu',123,'01/23'),(42,'Visa','1234 5678 9101 1121','Hirantha Athapaththu',123,'01/23'),(44,'Visa','1234 5678 9101 1121','Hirantha Athapaththu',123,'01/23'),(46,'Visa','1234 5678 9101 1121','Hirantha Athapaththu',123,'01/23'),(48,'Visa','1234 5678 9101 1121','Hirantha Athapaththu',123,'01/23');
/*!40000 ALTER TABLE `payment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `user_id` int(11) NOT NULL,
  `title` varchar(5) NOT NULL,
  `fname` varchar(45) NOT NULL,
  `lname` varchar(45) NOT NULL,
  `nic` varchar(20) NOT NULL,
  `email` varchar(45) NOT NULL,
  `phone` varchar(10) DEFAULT NULL,
  `address` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `nic` (`nic`),
  UNIQUE KEY `email` (`email`),
  UNIQUE KEY `UK_cf69eql64ont5pb889q2x2wt6` (`nic`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'Mr','Hirantha','Athapaththu','941090230V','hiranthaathapaththu@gmail.com','0769065565','Ithanawaththa,Kurunegala'),(23,'Ms','Chathurya','Athapaththu','123456789V','chathurya@gmail.com','0712345671','Malkaduwava,Kurunegala'),(25,'Mr','Geeth','Sameera','941093333V','geeth@gmail.com','0789685457','Kuliyapitiya,Kurunegala'),(61,'Mr','Chanaka','Athapaththu','444444444V','chanaka@gmail.com','0778899454','Piduruwella,Kurunegala');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'cims'
--

--
-- Dumping routines for database 'cims'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-10-13  8:54:59
