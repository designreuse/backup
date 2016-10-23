CREATE DATABASE  IF NOT EXISTS `nvidia` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `nvidia`;

-- MySQL dump 10.13  Distrib 5.7.9, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: nvidia
-- ------------------------------------------------------
-- Server version	5.7.11-log

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
-- Table structure for table `alert`
--

DROP TABLE IF EXISTS `alert`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `alert` (
  `alert_Id` int(11) NOT NULL AUTO_INCREMENT,
  `cluster_Id` int(11) DEFAULT NULL,
  `node_Id` int(11) DEFAULT NULL,
  `policy_Id` int(11) DEFAULT NULL,
  `schedule_Id` int(11) DEFAULT NULL,
  `frequency_Id` int(11) DEFAULT NULL,
  `attempts_Id` int(11) DEFAULT NULL,
  PRIMARY KEY (`alert_Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `alert`
--

LOCK TABLES `alert` WRITE;
/*!40000 ALTER TABLE `alert` DISABLE KEYS */;
/*!40000 ALTER TABLE `alert` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `alert_summary`
--

DROP TABLE IF EXISTS `alert_summary`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `alert_summary` (
  `alert_message` varchar(45) DEFAULT NULL,
  `alert_date` timestamp NULL DEFAULT NULL,
  `node_Id` int(11) NOT NULL,
  `cluster_Id` int(11) NOT NULL,
  `alert_summary_Id` int(11) NOT NULL AUTO_INCREMENT,
  `alert_time` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`alert_summary_Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `alert_summary`
--

LOCK TABLES `alert_summary` WRITE;
/*!40000 ALTER TABLE `alert_summary` DISABLE KEYS */;
/*!40000 ALTER TABLE `alert_summary` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `attempts`
--

DROP TABLE IF EXISTS `attempts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `attempts` (
  `attempts_Id` int(11) NOT NULL,
  `attempts` varchar(45) NOT NULL,
  PRIMARY KEY (`attempts_Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `attempts`
--

LOCK TABLES `attempts` WRITE;
/*!40000 ALTER TABLE `attempts` DISABLE KEYS */;
INSERT INTO `attempts` VALUES (1,'1'),(2,'2'),(3,'3'),(4,'4'),(5,'5'),(6,'6'),(7,'7'),(8,'8'),(9,'9'),(10,'10');
/*!40000 ALTER TABLE `attempts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cluster`
--

DROP TABLE IF EXISTS `cluster`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cluster` (
  `cluster_Id` varchar(45) NOT NULL,
  `cluster_ip_address` varchar(45) DEFAULT NULL,
  `nfs_one` varchar(45) DEFAULT NULL,
  `nfs_two` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`cluster_Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cluster`
--

LOCK TABLES `cluster` WRITE;
/*!40000 ALTER TABLE `cluster` DISABLE KEYS */;
INSERT INTO `cluster` VALUES ('cluster1','172.168.4.24','1','2');
/*!40000 ALTER TABLE `cluster` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `customer` (
  `customer_Id` varchar(45) NOT NULL,
  `username` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`customer_Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` VALUES ('customer1','tresbu','tresbu@tresbu.com','Welcome');
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `frequency`
--

DROP TABLE IF EXISTS `frequency`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `frequency` (
  `frequency_Id` int(11) NOT NULL,
  `frequency` varchar(45) NOT NULL,
  PRIMARY KEY (`frequency_Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `frequency`
--

LOCK TABLES `frequency` WRITE;
/*!40000 ALTER TABLE `frequency` DISABLE KEYS */;
INSERT INTO `frequency` VALUES (1,'1'),(2,'2'),(3,'3'),(4,'4'),(5,'5'),(6,'6'),(7,'7'),(8,'8'),(9,'9'),(10,'10');
/*!40000 ALTER TABLE `frequency` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `job`
--

DROP TABLE IF EXISTS `job`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `job` (
  `job_Id` int(11) NOT NULL AUTO_INCREMENT,
  `serialid` varchar(45) DEFAULT NULL,
  `job_name` varchar(45) DEFAULT NULL,
  `last_run_date` timestamp NULL DEFAULT NULL,
  `next_run_date` timestamp NULL DEFAULT NULL,
  `status` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`job_Id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `job`
--

LOCK TABLES `job` WRITE;
/*!40000 ALTER TABLE `job` DISABLE KEYS */;
INSERT INTO `job` VALUES (1,'NODE_1','Torch','2011-10-02 13:18:05','2011-10-02 13:18:05','Stopped');
/*!40000 ALTER TABLE `job` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `login`
--

DROP TABLE IF EXISTS `login`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `login` (
  `user_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `email` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `token` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1 COMMENT='USER INFO TABLE';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `login`
--

LOCK TABLES `login` WRITE;
/*!40000 ALTER TABLE `login` DISABLE KEYS */;
INSERT INTO `login` VALUES (1,'ram@gmail.com','1234','1212sasasas'),(2,'nitin@gmail.com','1111',NULL),(3,'nazeer@gmail.com','1111',NULL),(4,'tresbu@gmail.com','1111',NULL),(5,'test@tresbu.com','1111',NULL),(6,'EMAIL','PASSWORD',NULL);
/*!40000 ALTER TABLE `login` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `node`
--

DROP TABLE IF EXISTS `node`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `node` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cloud_managed` varchar(45) DEFAULT NULL,
  `is_leader` varchar(45) DEFAULT NULL,
  `cluster_Id` varchar(45) DEFAULT NULL,
  `fw_version` varchar(45) DEFAULT NULL,
  `licence_key` varchar(1000) DEFAULT NULL,
  `serialid` varchar(1000) DEFAULT NULL,
  `subnet` varchar(45) DEFAULT NULL,
  `node_name` varchar(1000) NOT NULL,
  `first_boot` varchar(100) DEFAULT NULL,
  `eula_accepted` varchar(45) DEFAULT NULL,
  `bios_version` varchar(45) DEFAULT NULL,
  `serial_number` varchar(1000) DEFAULT NULL,
  `cloud_status` varchar(45) DEFAULT NULL,
  `tags` varchar(45) DEFAULT NULL,
  `gpu_configuration` varchar(45) DEFAULT NULL,
  `ipmi` varchar(45) DEFAULT NULL,
  `node_Id` varchar(45) DEFAULT NULL,
  `mode` varchar(45) DEFAULT NULL,
  `node_key` varchar(1000) DEFAULT NULL,
  `ip_address` varchar(45) DEFAULT NULL,
  `sw_version` varchar(45) DEFAULT NULL,
  `cloud_group` varchar(45) DEFAULT NULL,
  `cluster_group` varchar(45) DEFAULT NULL,
  `model_name` varchar(1000) DEFAULT NULL,
  `created_time` timestamp NULL DEFAULT NULL,
  `last_restarted_on_time` timestamp NULL DEFAULT NULL,
  `memory` varchar(45) DEFAULT NULL,
  `disk_space` varchar(45) DEFAULT NULL,
  `status` varchar(45) DEFAULT NULL,
  `start_time` varchar(45) DEFAULT NULL,
  `node_network_information` varchar(45) DEFAULT NULL,
  `gateway` varchar(45) DEFAULT NULL,
   PRIMARY KEY (`id`),
   UNIQUE KEY `serialid_UNIQUE` (`serialid`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `node`
--

LOCK TABLES `node` WRITE;
/*!40000 ALTER TABLE `node` DISABLE KEYS */;
INSERT INTO `node` VALUES (1,'factory_default','yes','cluster1','54321','customer1-cluster1-node1','node1','172.31.16.0','ec2-52-38-12-79.us-west-2.compute.amazonaws.com','no','no','12345','ec26b4bf-4d38-d70e-7f33-b2e72f6c6254','disconnected','tiger,lion','1','XXXXXX','node1','dhcp','ec2-52-38-12-79.us-west-2.compute.amazonaws.com','172.17.0.1','2.0.0','na','master','db2','2011-10-02 13:18:05','2011-10-02 13:18:05',NULL,NULL,'Connected','2011-10-02 18:48:05.123',NULL,'172.31.16.1');
/*!40000 ALTER TABLE `node` ENABLE KEYS */;
UNLOCK TABLES;
--
-- Table structure for table `policy`
--

DROP TABLE IF EXISTS `policy`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `policy` (
  `policy_Id` int(11) NOT NULL,
  `policy` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`policy_Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `policy`
--

LOCK TABLES `policy` WRITE;
/*!40000 ALTER TABLE `policy` DISABLE KEYS */;
INSERT INTO `policy` VALUES (1,'Full'),(2,'NY-BACk02-SYSTEM'),(3,'Windows-D2D2T');
/*!40000 ALTER TABLE `policy` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `schedule`
--

DROP TABLE IF EXISTS `schedule`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `schedule` (
  `schedule_Id` int(11) NOT NULL,
  `schedule` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`schedule_Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `schedule`
--

LOCK TABLES `schedule` WRITE;
/*!40000 ALTER TABLE `schedule` DISABLE KEYS */;
INSERT INTO `schedule` VALUES (1,'Full'),(2,'Triggered-bkup'),(3,'LINUX-Selections');
/*!40000 ALTER TABLE `schedule` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `status`
--

DROP TABLE IF EXISTS `status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `status` (
  `status_Id` int(11) NOT NULL,
  `status` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`status_Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `status`
--

LOCK TABLES `status` WRITE;
/*!40000 ALTER TABLE `status` DISABLE KEYS */;
INSERT INTO `status` VALUES (1,'Running'),(2,'Stopped'),(3,'Deleted');
/*!40000 ALTER TABLE `status` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-04-18 11:14:38
