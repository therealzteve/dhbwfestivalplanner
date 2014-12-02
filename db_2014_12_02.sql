CREATE DATABASE  IF NOT EXISTS `dhbwfestivalplanner` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `dhbwfestivalplanner`;
-- MySQL dump 10.13  Distrib 5.6.17, for Win32 (x86)
--
-- Host: localhost    Database: dhbwfestivalplanner
-- ------------------------------------------------------
-- Server version	5.6.21

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
-- Table structure for table `event`
--

DROP TABLE IF EXISTS `event`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `event` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(45) NOT NULL,
  `Veranstalter_ID` int(11) NOT NULL,
  `title` varchar(100) DEFAULT NULL,
  `address` varchar(100) DEFAULT NULL,
  `plz` int(11) DEFAULT '-1',
  `city` varchar(100) DEFAULT NULL,
  `date` datetime NOT NULL,
  `time` datetime DEFAULT NULL,
  `design` int(11) NOT NULL DEFAULT '1',
  `description` longtext,
  PRIMARY KEY (`ID`),
  KEY `fk_Event_Veranstalter_idx` (`Veranstalter_ID`),
  CONSTRAINT `fk_Event_Veranstalter` FOREIGN KEY (`Veranstalter_ID`) REFERENCES `veranstalter` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `event`
--

LOCK TABLES `event` WRITE;
/*!40000 ALTER TABLE `event` DISABLE KEYS */;
INSERT INTO `event` VALUES (1,'TestEvent',1,NULL,NULL,-1,'','0000-00-00 00:00:00',NULL,1,NULL),(2,'MegaEvent',1,NULL,NULL,-1,'','0000-00-00 00:00:00',NULL,1,NULL),(3,'Testort',6,'Test3','rerwgerw',123456,'ergerwg','2014-01-17 00:12:00','2014-12-01 15:00:42',1,NULL),(4,'Testort',6,'Meine neue Party','Teststraße 1',76139,'Karlsruhe','2014-01-02 00:12:00','2014-12-02 16:00:12',0,'');
/*!40000 ALTER TABLE `event` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `guests`
--

DROP TABLE IF EXISTS `guests`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `guests` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `Event_ID` int(11) NOT NULL,
  `email` varchar(100) DEFAULT NULL,
  `confirmed` tinyint(1) NOT NULL DEFAULT '0',
  `receivesEmail` tinyint(1) NOT NULL DEFAULT '0',
  `receivedInvitation` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`ID`),
  KEY `fk_Guests_Event1_idx` (`Event_ID`),
  CONSTRAINT `fk_Guests_Event1` FOREIGN KEY (`Event_ID`) REFERENCES `event` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `guests`
--

LOCK TABLES `guests` WRITE;
/*!40000 ALTER TABLE `guests` DISABLE KEYS */;
INSERT INTO `guests` VALUES (1,'Testgast 1',3,'testEmail@test.de',0,0,0),(2,'Testgast 2',3,'testEmail2@test.de',0,1,0);
/*!40000 ALTER TABLE `guests` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `messages`
--

DROP TABLE IF EXISTS `messages`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `messages` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Content` varchar(45) DEFAULT NULL,
  `Veranstalter_ID` int(11) NOT NULL,
  `Vorgaenger_ID` int(11) NOT NULL,
  `Messagescol` varchar(45) DEFAULT NULL,
  `Guests_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `fk_Messages_Veranstalter1_idx` (`Veranstalter_ID`),
  KEY `fk_Messages_Messages1_idx` (`Vorgaenger_ID`),
  KEY `fk_Messages_Guests1_idx` (`Guests_ID`),
  CONSTRAINT `fk_Messages_Guests1` FOREIGN KEY (`Guests_ID`) REFERENCES `guests` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Messages_Messages1` FOREIGN KEY (`Vorgaenger_ID`) REFERENCES `messages` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Messages_Veranstalter1` FOREIGN KEY (`Veranstalter_ID`) REFERENCES `veranstalter` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `messages`
--

LOCK TABLES `messages` WRITE;
/*!40000 ALTER TABLE `messages` DISABLE KEYS */;
/*!40000 ALTER TABLE `messages` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `veranstalter`
--

DROP TABLE IF EXISTS `veranstalter`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `veranstalter` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(70) NOT NULL,
  `username` varchar(70) NOT NULL,
  `password` varchar(70) NOT NULL,
  `email` varchar(70) NOT NULL,
  `birth` datetime NOT NULL,
  `isEnabled` tinyint(1) NOT NULL,
  `code` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `veranstalter`
--

LOCK TABLES `veranstalter` WRITE;
/*!40000 ALTER TABLE `veranstalter` DISABLE KEYS */;
INSERT INTO `veranstalter` VALUES (1,'Testveranstalter','test','Test1234!','','0000-00-00 00:00:00',0,NULL),(2,'Temporary Test Name','test2','$2a$10$5CpO1hXLUL4NSdQyXnDoVu1rWVBv8gFOC2aOpUYtQ06Kb86iHAhby','test@test.de','0000-00-00 00:00:00',0,NULL),(3,'Temporary Test Name','test2','$2a$10$vTLxR2/jkdWCybBcfx67wO2HIIlLQkCOkgmW4SZ2lNyPqtAOxwAH6','test@test.de','0000-00-00 00:00:00',0,NULL),(4,'Temporary Test Name','test2','$2a$10$AK2XJ4ue7miEEw9kKycWfuF5p.jMePVOUgHmjUCizg9AqvdsYwIK6','test@test.de','0000-00-00 00:00:00',0,NULL),(5,'Temporary Test Name','test2','$2a$10$rvhc.APltTZf5wrR4YhlWuy5FsTDR0RwYFrJeT/Qr4bLn.WkZAWWe','test@test.de','0000-00-00 00:00:00',0,NULL),(6,'Temporary Test Name','Test3','$2a$10$Z9TC35ZFwNG/XV48B.zi.ug2tkc/UF7d8cZc2HDRz4sGJV3w9ITHO','test@test.de','0000-00-00 00:00:00',1,NULL),(7,'Temporary Test Name','test4','$2a$10$161nQgDTg7PIcjavdckCh.eFVPpfDC8N.U.fl3ObKXmQdsoMBpSYK','hans@wurst.de','0000-00-00 00:00:00',0,NULL),(8,'Temporary Test Name','RegisterTest','$2a$10$Fiy1tN51iLK5xZkxdWaIBeHoPYPJPkWHi.dZHulSZ8J0kHSl5Y6eW','test@test2.de','2014-11-24 15:45:08',1,''),(9,'','test1234','$2a$10$5osz7pcxWvTFWa1QBZiDaOA5soXBweJ3JJHPhF2J0G1jdTSrDYrdy','Meintest@Test.de','2014-11-26 11:44:34',0,'6y7wvafobvpinj7l4qn2x2dxrfbvg5ah9uo3lgws'),(10,'','test','$2a$10$SHK0eJiXHPXhByi8stx0H.Yhb.RzIGjQf6cKFns0c4/Bm/BNYIrJ.','Test12345t','2014-11-27 13:38:01',0,'frru9n7enuu4wcmd64l0xssqyml6f9gp1wnkcil9');
/*!40000 ALTER TABLE `veranstalter` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2014-12-02 16:04:52
