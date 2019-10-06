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
INSERT INTO `car` VALUES (5,'ABC-1234','Toyota','Supra',2020,'Red',2503,NULL,'Used, Second Owner.',9500000,1000000,4),(11,'XY-5567','Nissan','GTR',2015,'Black',23568,NULL,'Used, Third Owner.',5500000,1500000,10),(21,'PQR-1133','Mclaren','720s',2018,'Orange',500,NULL,'Brand New, All Options',10500000,4000000,20);
/*!40000 ALTER TABLE `car` ENABLE KEYS */;
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
INSERT INTO `favourite` VALUES (12,1,10),(19,1,6),(22,1,20),(24,23,20);
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
INSERT INTO `hibernate_sequence` VALUES (25);
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
  PRIMARY KEY (`inv_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `inventory`
--

LOCK TABLES `inventory` WRITE;
/*!40000 ALTER TABLE `inventory` DISABLE KEYS */;
INSERT INTO `inventory` VALUES (4,'2019-10-05 19:15:30',NULL,'car',1),(6,'2019-10-05 19:38:47',NULL,'part',25),(8,'2019-10-05 19:44:41',NULL,'part',5),(10,'2019-10-05 19:51:38',NULL,'car',1),(20,'2019-10-05 22:00:47',NULL,'car',1);
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
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `login`
--

LOCK TABLES `login` WRITE;
/*!40000 ALTER TABLE `login` DISABLE KEYS */;
INSERT INTO `login` VALUES ('hiranthaathapaththu@gmail.com','123456','admin',1),('chathurya@gmail.com','123456','cust',23);
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
  `amount` int(11) DEFAULT NULL,
  PRIMARY KEY (`order_id`,`inv_id`),
  CONSTRAINT `fk_order_item_list_order_` FOREIGN KEY (`order_id`) REFERENCES `order_` (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_item_list`
--

LOCK TABLES `order_item_list` WRITE;
/*!40000 ALTER TABLE `order_item_list` DISABLE KEYS */;
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
INSERT INTO `part` VALUES (7,'Tyre','Michelin',NULL,'Michelin 70 Series',17500,6),(9,'V8 Engine','Ford',NULL,'V8, 550 HP, Brand New',1850000,8);
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
  `card_owner` varchar(50) DEFAULT NULL,
  `card_csv` int(11) DEFAULT NULL,
  `card_exp_date` datetime DEFAULT NULL,
  PRIMARY KEY (`pay_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payment`
--

LOCK TABLES `payment` WRITE;
/*!40000 ALTER TABLE `payment` DISABLE KEYS */;
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
INSERT INTO `user` VALUES (1,'Mr','Hirantha','Athapaththu','941090230V','hiranthaathapaththu@gmail.com','0769065565','Ithanawaththa,Kurunegala'),(23,'Ms','Chathurya','Athapaththu','123456789V','chathurya@gmail.com','0712345671','Malkaduwava,Kurunegala');
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

-- Dump completed on 2019-10-06 12:14:19
