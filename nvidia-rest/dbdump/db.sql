-- MySQL dump 10.13  Distrib 5.6.28, for debian-linux-gnu (x86_64)
--
-- Host: localhost    Database: nvidia_new
-- ------------------------------------------------------
-- Server version	5.6.28-0ubuntu0.14.04.1

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
-- Table structure for table `NV_APPLIANCE_MASTER`
--

DROP TABLE IF EXISTS `NV_APPLIANCE_MASTER`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `NV_APPLIANCE_MASTER` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `CREATED_DATE` datetime NOT NULL,
  `NAME` varchar(50) NOT NULL,
  `SERVICE_KEY` varchar(255) NOT NULL,
  `UPDATED_DATE` datetime DEFAULT NULL,
  `CUSTOMER_ID` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_2rvt21u4niamjrahq064al3k8` (`CUSTOMER_ID`),
  CONSTRAINT `FK_2rvt21u4niamjrahq064al3k8` FOREIGN KEY (`CUSTOMER_ID`) REFERENCES `NV_CUSTOMER_MASTER` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `NV_APPLIANCE_MASTER`
--

LOCK TABLES `NV_APPLIANCE_MASTER` WRITE;
/*!40000 ALTER TABLE `NV_APPLIANCE_MASTER` DISABLE KEYS */;
/*!40000 ALTER TABLE `NV_APPLIANCE_MASTER` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `NV_AUTEHNTICATIONS_MASTER`
--

DROP TABLE IF EXISTS `NV_AUTEHNTICATIONS_MASTER`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `NV_AUTEHNTICATIONS_MASTER` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `AUTH_TOKEN` varchar(255) DEFAULT NULL,
  `CREATED_DATE` datetime NOT NULL,
  `EMAIL` varchar(255) DEFAULT NULL,
  `EXT_TOKEN` varchar(255) DEFAULT NULL,
  `NAME` varchar(255) DEFAULT NULL,
  `ROLE_NAME` varchar(255) DEFAULT NULL,
  `UPDATED_DATE` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=152 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `NV_AUTEHNTICATIONS_MASTER`
--

LOCK TABLES `NV_AUTEHNTICATIONS_MASTER` WRITE;
/*!40000 ALTER TABLE `NV_AUTEHNTICATIONS_MASTER` DISABLE KEYS */;
INSERT INTO `NV_AUTEHNTICATIONS_MASTER` VALUES (4,'d0bf8a6bd142d2a5f888cffd1f34e676','2016-05-09 06:48:03','admin@nvidia.com',NULL,'Nvidia Admin','Super Admin',NULL),(10,'727e954aac12908be89db364d25ef88','2016-05-10 00:10:06','admin@nvidia.com',NULL,'Nvidia Admin','Super Admin',NULL),(14,'9f715e52f422ec035aff8698e990e7','2016-05-10 08:20:14','admin@nvidia.com',NULL,'Nvidia Admin','Super Admin',NULL),(20,'4072ced84558a9e9dbfe5eaf48816b4b','2016-05-10 10:43:19','sitaram.shastri@tresbu.com',NULL,'SSS','Customer Admin',NULL),(32,'10c356aa43ca3e8a39683776e2787aa','2016-05-10 23:11:18','admin@nvidia.com',NULL,'Nvidia Admin','Super Admin',NULL),(36,'16c74457e33cea1824ac8be4968c46f','2016-05-10 23:25:53','admin@nvidia.com',NULL,'Nvidia Admin','Super Admin',NULL),(39,'59307962d7e773d94ddbacd6298606b','2016-05-10 23:44:37','admin@nvidia.com',NULL,'Nvidia Admin','Super Admin',NULL),(42,'2059e26b39dcd494a087b8f7c7bde6b8','2016-05-10 23:52:46','nazeer.basha@tresbu.com',NULL,'nazeer','Customer Admin',NULL),(45,'57a69c589c3284322bd8546818714e0','2016-05-11 00:13:36','nitinsai.n@tresbu.com',NULL,'Nitin','Customer Admin',NULL),(53,'8f22f9a244b4c189981f5ecdd83333b3','2016-05-11 00:31:39','sitaram.shastri@tresbu.com',NULL,'SSS','Customer Admin',NULL),(59,'c67732c2b55444956818afeaeba88b0','2016-05-11 00:44:36','nazeer.basha@tresbu.com',NULL,'nazeer','Customer Admin',NULL),(62,'8a7c3534648c47a82dad8a15aa8adde3','2016-05-11 00:47:10','koppalavenky@yopmail.com',NULL,'koppala','Customer Admin',NULL),(64,'7939caf72d197a57bedc7f96d52626d1','2016-05-11 02:29:32','admin@nvidia.com',NULL,'Nvidia Admin','Super Admin',NULL),(66,'872557603491b55f1c2d7607b75ecdb','2016-05-11 03:47:16','tenant@yopmail.com',NULL,'Tenant12','Customer Admin',NULL),(69,'5e97d72e0c0782ea835f8f5ad32c08a','2016-05-11 10:21:49','admin@nvidia.com',NULL,'Nvidia Admin','Super Admin',NULL),(70,'37c450ad3a44f7724997658f3cb61742','2016-05-11 10:23:01','admin@nvidia.com',NULL,'Nvidia Admin','Super Admin',NULL),(71,'596bd24444207a53c11f2245b5935516','2016-05-11 10:23:25','admin@nvidia.com',NULL,'Nvidia Admin','Super Admin',NULL),(72,'8298b9340c684ff3eb7870d9a8381','2016-05-11 10:25:42','admin@nvidia.com',NULL,'Nvidia Admin','Super Admin',NULL),(73,'f4dc6b5b3f98653c3b6e4bb96d5fdb47','2016-05-11 10:26:10','admin@nvidia.com',NULL,'Nvidia Admin','Super Admin',NULL),(74,'c642bcd4825f90851ba6eadaf8a780','2016-05-11 10:26:40','admin@nvidia.com',NULL,'Nvidia Admin','Super Admin',NULL),(75,'5067c112c3df6220a74a3f337946a574','2016-05-11 22:30:17','nazeer.basha@tresbu.com',NULL,'nazeer','Customer Admin',NULL),(76,'88b348cabeb2df82d14b9c6f9e4f4089','2016-05-11 22:55:59','admin@nvidia.com',NULL,'Nvidia Admin','Super Admin',NULL),(80,'63439a165cf9539a59434286d4c93e66','2016-05-12 04:33:02','admin@nvidia.com',NULL,'Nvidia Admin','Super Admin',NULL),(81,'7f969a97381548cf43f294bf182ca','2016-05-12 06:01:12','nazeer.basha@tresbu.com',NULL,'nazeer','Customer Admin',NULL),(82,'6c3c46e42ed08fee74a245462e76f08c','2016-05-12 06:01:26','nazeer.basha@tresbu.com',NULL,'nazeer','Customer Admin',NULL),(83,'1157977393f4596c623076ffd4d9d22','2016-05-12 06:01:41','nazeer.basha@tresbu.com',NULL,'nazeer','Customer Admin',NULL),(84,'1db7fe3f5ebbaf1db3b77875878063','2016-05-12 06:04:23','nazeer.basha@tresbu.com',NULL,'nazeer','Customer Admin',NULL),(86,'7b985567802031352059c3323d0c148','2016-05-12 06:06:12','nazeer.basha@tresbu.com',NULL,'nazeer','Customer Admin',NULL),(87,'5cba69f166dbc687108ca0165071e76b','2016-05-12 06:15:49','koppalavenky@yopmail.com',NULL,'koppala','Customer Admin',NULL),(89,'8b501f5ff0456def3aca9bb9711c12e','2016-05-12 11:27:21','sitaram.shastri@yopmail.com',NULL,'S Shastri','Customer User',NULL),(92,'2b77759a60f283517db81fd772918119','2016-05-12 22:01:18','koppalavenky@yopmail.com',NULL,'koppala','Customer Admin',NULL),(93,'74be27aed178aa8eeaad3b6776f1df59','2016-05-12 22:32:07','nazeer.basha@tresbu.com',NULL,'nazeer','Customer Admin',NULL),(94,'ed72b47c5e0254b2d3e3ce279abb776','2016-05-12 22:32:38','nazeer.basha@tresbu.com',NULL,'nazeer','Customer Admin',NULL),(96,'bcb2d1f3eb9a9615bc7da2859a25ef','2016-05-13 00:16:02','sitaram.shastri@tresbu.com',NULL,'SSS','Customer Admin',NULL),(100,'16577a7053f88f535a9954a63954689','2016-05-13 00:53:06','ajay1@yopmail.com',NULL,'Ajay','Customer User',NULL),(103,'e0325b40d084fcadfd8624fa69a35c3c','2016-05-13 03:16:15','admin@nvidia.com',NULL,'Nvidia Admin','Super Admin',NULL),(104,'64ce4a731851165843814325dcbe1','2016-05-13 03:16:24','admin@nvidia.com',NULL,'Nvidia Admin','Super Admin',NULL),(105,'8fdd25e97ad1ea065440bd853df12','2016-05-13 03:16:28','admin@nvidia.com',NULL,'Nvidia Admin','Super Admin',NULL),(106,'b7d4511d29195112c68e52a721909dc','2016-05-13 03:16:29','admin@nvidia.com',NULL,'Nvidia Admin','Super Admin',NULL),(107,'0f04a4f663275450ca567a93e95c1','2016-05-13 03:16:29','admin@nvidia.com',NULL,'Nvidia Admin','Super Admin',NULL),(108,'49d62f21a83ab2f88f5518b4a80868a','2016-05-13 03:16:30','admin@nvidia.com',NULL,'Nvidia Admin','Super Admin',NULL),(109,'3563dfc9b34fba824be1cecf1bd71cb','2016-05-13 03:16:30','admin@nvidia.com',NULL,'Nvidia Admin','Super Admin',NULL),(110,'7b5ec2847a563e134f9e8af4a942556','2016-05-13 03:16:30','admin@nvidia.com',NULL,'Nvidia Admin','Super Admin',NULL),(111,'5213d17b9f35b7f67f6231388e2a424b','2016-05-13 03:16:31','admin@nvidia.com',NULL,'Nvidia Admin','Super Admin',NULL),(112,'ab7859d2f040fd45bae0d420531cf1a7','2016-05-13 03:16:54','admin@nvidia.com',NULL,'Nvidia Admin','Super Admin',NULL),(114,'92c99884968bfe9dcdb54137b246b53','2016-05-13 03:23:04','admin@nvidia.com',NULL,'Nvidia Admin','Super Admin',NULL),(116,'598f9942c6b633de782c1c278ae69c8','2016-05-13 04:54:45','nazeer.basha@tresbu.com',NULL,'nazeer','Customer Admin',NULL),(120,'7fbc1da8e516fbbbcf59df68b1e1cf1','2016-05-13 07:38:36','tenant7@yopmail.com',NULL,'tenant7','Customer Admin',NULL),(123,'5588c77ee19cd21d246bd0c8173fe6df','2016-05-13 09:22:23','admin@nvidia.com',NULL,'Nvidia Admin','Super Admin',NULL),(135,'114af8715aa19a5f3c94b4f5764c','2016-05-13 23:50:04','nazeer.basha@tresbu.com',NULL,'nazeer','Customer Admin',NULL),(137,'aa88e042f8151e94ddcf3444a983688b','2016-05-14 01:02:53','admin@nvidia.com',NULL,'Nvidia Admin','Super Admin',NULL),(139,'9dd07937818fd0ee28e6ec7638af2fb7','2016-05-14 01:39:51','koppalavenky@yopmail.com',NULL,'koppala','Customer Admin',NULL),(140,'f28893b2366e8cd31d3e649edf85542f','2016-05-14 03:12:17','nazeer.basha@tresbu.com',NULL,'nazeer','Customer Admin',NULL),(145,'597f7612b17ca25b6c9830a44d6242a','2016-05-14 04:13:06','admin@nvidia.com',NULL,'Nvidia Admin','Super Admin',NULL),(146,'9b6bb19ebba39ee69b0c56f51a92ba2','2016-05-14 04:14:47','admin@nvidia.com',NULL,'Nvidia Admin','Super Admin',NULL),(147,'2a3732cdf790f22452ab6d590ecd338','2016-05-14 04:47:12','parasaram.bombale@tresbu.com',NULL,'Ram','Customer Admin',NULL),(149,'874a81e1a7c2e1824f7a1c0a0f68ee9','2016-05-14 04:58:15','parasaram.bombale@tresbu.com',NULL,'Ram','Customer Admin',NULL),(151,'dd42f69e7d2af0c8aad5fae918b15e8d','2016-05-14 05:06:33','tenant7@yopmail.com',NULL,'tenant7','Customer Admin',NULL);
/*!40000 ALTER TABLE `NV_AUTEHNTICATIONS_MASTER` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `NV_CLUSTER_MASTER`
--

DROP TABLE IF EXISTS `NV_CLUSTER_MASTER`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `NV_CLUSTER_MASTER` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `CREATED_DATE` datetime NOT NULL,
  `IP_ADDRESS` varchar(255) DEFAULT NULL,
  `NAME` varchar(50) NOT NULL,
  `NFS_DETAILS` varchar(255) DEFAULT NULL,
  `UPDATED_DATE` datetime DEFAULT NULL,
  `CUSTOMER_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_huam64ukjvh1k2a9g5jgw0t7m` (`CUSTOMER_ID`),
  CONSTRAINT `FK_huam64ukjvh1k2a9g5jgw0t7m` FOREIGN KEY (`CUSTOMER_ID`) REFERENCES `NV_CUSTOMER_MASTER` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `NV_CLUSTER_MASTER`
--

LOCK TABLES `NV_CLUSTER_MASTER` WRITE;
/*!40000 ALTER TABLE `NV_CLUSTER_MASTER` DISABLE KEYS */;
INSERT INTO `NV_CLUSTER_MASTER` VALUES (1,'2016-05-10 08:20:41',NULL,'cluster1',NULL,NULL,3),(2,'2016-05-10 23:45:52',NULL,'cluster2',NULL,NULL,1),(3,'2016-05-10 23:46:59',NULL,'cluster3',NULL,NULL,1),(4,'2016-05-10 23:51:34',NULL,'cluster4',NULL,'2016-05-10 23:51:48',1);
/*!40000 ALTER TABLE `NV_CLUSTER_MASTER` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `NV_CUSTOMER_MASTER`
--

DROP TABLE IF EXISTS `NV_CUSTOMER_MASTER`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `NV_CUSTOMER_MASTER` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `CREATED_DATE` datetime NOT NULL,
  `EMAIL` varchar(255) NOT NULL,
  `NAME` varchar(50) NOT NULL,
  `PHONE` varchar(255) DEFAULT NULL,
  `UPDATED_DATE` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `NV_CUSTOMER_MASTER`
--

LOCK TABLES `NV_CUSTOMER_MASTER` WRITE;
/*!40000 ALTER TABLE `NV_CUSTOMER_MASTER` DISABLE KEYS */;
INSERT INTO `NV_CUSTOMER_MASTER` VALUES (1,'2016-05-09 05:57:17','admin@nvidia.com','Nvidia Admin','4082134567',NULL),(3,'2016-05-09 06:44:39','sitaram.shastri@tresbu.com','SSS',NULL,NULL),(4,'2016-05-10 06:46:11','tenant1@yopmail.com','Tenant 1',NULL,NULL),(5,'2016-05-10 10:38:38','tenant2@yopmail.com','Tenant2',NULL,NULL),(6,'2016-05-10 22:16:47','koppalavenky@yopmail.com','koppala',NULL,NULL),(7,'2016-05-10 22:44:00','nazeer.basha@tresbu.com','nazeer',NULL,NULL),(8,'2016-05-10 22:46:37','nitinsai.n@tresbu.com','Nitin',NULL,NULL),(9,'2016-05-10 23:16:12','premsai.g@tresbu.com','prem',NULL,NULL),(10,'2016-05-10 23:27:05','prem@yopmail.com','prem',NULL,NULL),(11,'2016-05-11 03:45:25','tenant@yopmail.com','Tenant12','',NULL),(12,'2016-05-11 22:59:32','tenant3@yopmail.com','Tenant3',NULL,NULL),(13,'2016-05-12 23:44:43','tenant5@yopmail.com','Tenant5',NULL,NULL),(14,'2016-05-13 00:46:06','vishwanath.hd@tresbu.com','vishwa',NULL,NULL),(15,'2016-05-13 02:07:58','ciscosus@yopmail.com','Cisco Susyems',NULL,NULL),(16,'2016-05-13 05:47:03','tenant7@yopmail.com','tenant7',NULL,NULL),(17,'2016-05-13 09:22:58','tenant20@yopmail.com','Tenant20',NULL,NULL),(18,'2016-05-13 12:00:23','tenant21@yopmail.com','tenant21',NULL,NULL),(19,'2016-05-13 12:07:23','tenant22@yopmail.com','Tenant22',NULL,NULL),(20,'2016-05-13 12:45:26','tenant23@yopmail.com','tenant23',NULL,NULL),(21,'2016-05-13 13:05:46','tenant8@yopmail.com','tenant8',NULL,NULL),(22,'2016-05-13 13:35:05','fakeadmin@yopmail.com','fakeOrg',NULL,NULL),(23,'2016-05-13 13:37:30','andy@yopmail.com','Andy Corp.',NULL,NULL),(24,'2016-05-14 03:34:28','parasaram.bombale@tresbu.com','Ram',NULL,NULL);
/*!40000 ALTER TABLE `NV_CUSTOMER_MASTER` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `NV_ENTITLEMENT_MASTER`
--

DROP TABLE IF EXISTS `NV_ENTITLEMENT_MASTER`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `NV_ENTITLEMENT_MASTER` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `CREATED_DATE` datetime NOT NULL,
  `CUSTOMER_NAME` varchar(50) NOT NULL,
  `ENTITLE_KEY` varchar(255) NOT NULL,
  `HW_SERIAL_NUMBER` varchar(255) NOT NULL,
  `UPDATED_DATE` datetime DEFAULT NULL,
  `CUSTOMER_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_ktlyju0dgb7ryv28n4dt1oiv9` (`CUSTOMER_ID`),
  CONSTRAINT `FK_ktlyju0dgb7ryv28n4dt1oiv9` FOREIGN KEY (`CUSTOMER_ID`) REFERENCES `NV_CUSTOMER_MASTER` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `NV_ENTITLEMENT_MASTER`
--

LOCK TABLES `NV_ENTITLEMENT_MASTER` WRITE;
/*!40000 ALTER TABLE `NV_ENTITLEMENT_MASTER` DISABLE KEYS */;
INSERT INTO `NV_ENTITLEMENT_MASTER` VALUES (2,'2016-05-09 06:41:53','SSS','12345','54321','2016-05-09 06:44:39',3),(3,'2016-05-09 06:43:32','SSS','23456','65432',NULL,NULL),(4,'2016-05-10 06:45:27','Tenant1','1','1','2016-05-10 06:46:11',4),(5,'2016-05-10 10:14:39','Tenant2','2','2','2016-05-10 10:38:38',5),(6,'2016-05-10 22:16:11','koppala','123','321','2016-05-10 22:16:47',6),(7,'2016-05-10 22:43:14','nazeer','11111','22222','2016-05-10 22:44:00',7),(8,'2016-05-10 22:45:21','Nitin','qwerty-qwerty-qwerty-qwerty','543210','2016-05-10 22:46:37',8),(9,'2016-05-10 23:13:36','premsai','1234567','123456','2016-05-10 23:16:12',9),(10,'2016-05-10 23:26:38','prem','12345678','123456','2016-05-10 23:27:05',10),(11,'2016-05-11 03:44:31','Tenant12','1244','54321','2016-05-11 03:45:25',11),(12,'2016-05-11 22:58:41','Tenant3','3','3','2016-05-11 22:59:32',12),(13,'2016-05-12 23:44:03','Tenant5','5','5','2016-05-12 23:44:43',13),(14,'2016-05-13 00:43:44','Vishwa','98804','54321','2016-05-13 00:46:06',14),(15,'2016-05-13 02:07:19','Cisco Susyems','23131231','4324234234','2016-05-13 02:07:58',15),(16,'2016-05-13 03:23:32','XYZ Company','36742','24763',NULL,NULL),(17,'2016-05-13 03:35:57','Ram','1234','1234',NULL,NULL),(18,'2016-05-13 05:42:54','tenant7','7','7','2016-05-13 05:47:03',16),(19,'2016-05-13 09:19:19','Tenant20','20','10','2016-05-13 09:22:58',17),(20,'2016-05-13 11:59:27','tenant21','21','21','2016-05-13 12:00:23',18),(21,'2016-05-13 12:06:17','Tenant22','22','22','2016-05-13 12:07:23',19),(22,'2016-05-13 12:44:24','tenant23','23','23','2016-05-13 12:45:26',20),(23,'2016-05-13 13:02:24','tenant8','8','8','2016-05-13 13:05:46',21),(24,'2016-05-13 13:33:51','fakeOrg','5676','8787','2016-05-13 13:35:05',22),(25,'2016-05-13 13:34:09','Andy Corp.','1234345345sdgfdsfg','345345345','2016-05-13 13:37:30',23),(26,'2016-05-14 03:32:21','Ram','900896','12345','2016-05-14 03:34:28',24);
/*!40000 ALTER TABLE `NV_ENTITLEMENT_MASTER` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `NV_NODE_MASTER`
--

DROP TABLE IF EXISTS `NV_NODE_MASTER`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `NV_NODE_MASTER` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `BIOS_VERSION` varchar(50) NOT NULL,
  `CLOUD_GROUP` varchar(255) NOT NULL,
  `CLOUD_MANAGED` varchar(100) NOT NULL,
  `CLOUD_STATUS` varchar(50) NOT NULL,
  `CLUSTER_GROUP` varchar(255) NOT NULL,
  `CREATED_DATE` datetime NOT NULL,
  `DISK_SPACE` varchar(255) DEFAULT NULL,
  `EULA_ACCEPTED` varchar(255) NOT NULL,
  `FIRST_BOOT` varchar(255) NOT NULL,
  `FW_VERSION` varchar(50) NOT NULL,
  `GATEWAY` varchar(255) DEFAULT NULL,
  `GPU_CONFIGURATION` varchar(255) NOT NULL,
  `IP_ADDRESS` varchar(255) NOT NULL,
  `IPMI` varchar(50) NOT NULL,
  `IS_LEADER` varchar(50) NOT NULL,
  `LICENCE_KEY` longtext NOT NULL,
  `MEMORY` varchar(255) DEFAULT NULL,
  `MODE` varchar(50) NOT NULL,
  `MODEL_NAME` varchar(255) NOT NULL,
  `NAME` longtext NOT NULL,
  `NODE_ID` varchar(100) NOT NULL,
  `NODE_KEY` longtext NOT NULL,
  `SERIAL_ID` longtext NOT NULL,
  `SERIAL_NUMBER` longtext NOT NULL,
  `SUB_NET` varchar(255) NOT NULL,
  `SW_VERSION` varchar(255) NOT NULL,
  `TAGS` varchar(50) NOT NULL,
  `UPDATED_DATE` datetime DEFAULT NULL,
  `CLUSTER_ID` int(11) DEFAULT NULL,
  `CLOUD_URL` varchar(50) DEFAULT NULL,
  `REPO_URL` varchar(50) DEFAULT NULL,
  `TOTAL_CPU_CORES` varchar(50) DEFAULT NULL,
  `USER_ADDED` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_7y4tq5rmxklqaalchcmtobww2` (`CLUSTER_ID`),
  CONSTRAINT `FK_7y4tq5rmxklqaalchcmtobww2` FOREIGN KEY (`CLUSTER_ID`) REFERENCES `NV_CLUSTER_MASTER` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `NV_NODE_MASTER`
--

LOCK TABLES `NV_NODE_MASTER` WRITE;
/*!40000 ALTER TABLE `NV_NODE_MASTER` DISABLE KEYS */;
INSERT INTO `NV_NODE_MASTER` VALUES (1,'54321','na','factory_default','connected','master','2016-05-10 08:20:41',NULL,'no','no','54321',NULL,'0','52.24.185.151','XXXXXX','yes','SSS-cluster1-node1',NULL,'dhcp','dbs2','node1','nvidia-DEV1_DGX1','nvidia-DEV1_DGX1','node1','54321','22.33.44.000','2.0.0','tiger,lion',NULL,1,NULL,NULL,NULL,NULL),(2,'54321','na','factory_default','disconnected','master','2016-05-10 23:45:52',NULL,'no','no','54321',NULL,'1','172.17.0.1','XXXXXX','yes','Nvidia Admin-cluster4-node2',NULL,'dhcp','db2','node2','node1','ec2-52-38-12-79.us-west-2.compute.amazonaws.com','node2','ec26b4bf-4d38-d70e-7f33-b2e72f6c6254','22.33.44.000','2.0.0','tiger,lion','2016-05-10 23:51:48',4,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `NV_NODE_MASTER` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `NV_ROLE_MASTER`
--

DROP TABLE IF EXISTS `NV_ROLE_MASTER`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `NV_ROLE_MASTER` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `CREATED_DATE` datetime NOT NULL,
  `DESCRIPTION` varchar(255) NOT NULL,
  `NAME` varchar(50) NOT NULL,
  `UPDATED_DATE` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `NV_ROLE_MASTER`
--

LOCK TABLES `NV_ROLE_MASTER` WRITE;
/*!40000 ALTER TABLE `NV_ROLE_MASTER` DISABLE KEYS */;
INSERT INTO `NV_ROLE_MASTER` VALUES (1,'2016-05-09 05:57:17','Super Admin','Super Admin',NULL),(2,'2016-05-09 05:57:17','Customer Admin','Customer Admin',NULL),(3,'2016-05-09 05:57:17','Customer User','Customer User',NULL);
/*!40000 ALTER TABLE `NV_ROLE_MASTER` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `NV_USER_MASTER`
--

DROP TABLE IF EXISTS `NV_USER_MASTER`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `NV_USER_MASTER` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `CREATED_DATE` datetime NOT NULL,
  `EMAIL` varchar(255) NOT NULL,
  `NAME` varchar(50) NOT NULL,
  `PASSWORD` varchar(255) DEFAULT NULL,
  `PHONE` varchar(255) DEFAULT NULL,
  `STATUS` varchar(255) NOT NULL,
  `UPDATED_DATE` datetime DEFAULT NULL,
  `CUSTOMER_ID` int(11) DEFAULT NULL,
  `ROLE_ID` int(11) DEFAULT NULL,
  `GID` varchar(255) DEFAULT NULL,
  `LAST_LOGIN` datetime DEFAULT NULL,
  `Q_AUTH_TOKEN` varchar(255) DEFAULT NULL,
  `UID` varchar(255) DEFAULT NULL,
  `USERNAME` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_suyode29vd6t6ql60f6usouy9` (`CUSTOMER_ID`),
  KEY `FK_1lrgfw8a7qne9tntl8yeb3cp9` (`ROLE_ID`),
  CONSTRAINT `FK_1lrgfw8a7qne9tntl8yeb3cp9` FOREIGN KEY (`ROLE_ID`) REFERENCES `NV_ROLE_MASTER` (`id`),
  CONSTRAINT `FK_suyode29vd6t6ql60f6usouy9` FOREIGN KEY (`CUSTOMER_ID`) REFERENCES `NV_CUSTOMER_MASTER` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `NV_USER_MASTER`
--

LOCK TABLES `NV_USER_MASTER` WRITE;
/*!40000 ALTER TABLE `NV_USER_MASTER` DISABLE KEYS */;
INSERT INTO `NV_USER_MASTER` VALUES (1,'2016-05-09 05:57:17','admin@nvidia.com','Nvidia Admin','9m0EAXwvYz7gTe/1m4ixMg==','4082134567','ACTIVE','2016-05-14 04:14:47',1,1,NULL,'2016-05-14 04:14:47',NULL,NULL,NULL),(3,'2016-05-09 06:44:39','sitaram.shastri@tresbu.com','SSS','9m0EAXwvYz7gTe/1m4ixMg==',NULL,'ACTIVE','2016-05-14 04:29:15',3,2,NULL,'2016-05-13 00:16:02',NULL,NULL,'Ram'),(4,'2016-05-10 06:46:11','tenant1@yopmail.com','Tenant 1','+L2ZGFRWmqwfDQ2XHsWKTg==',NULL,'ACTIVE','2016-05-10 06:47:16',4,2,NULL,NULL,NULL,NULL,NULL),(5,'2016-05-10 10:38:38','tenant2@yopmail.com','Tenant2','+L2ZGFRWmqwfDQ2XHsWKTg==',NULL,'ACTIVE','2016-05-10 10:40:13',5,2,NULL,NULL,NULL,NULL,NULL),(6,'2016-05-10 22:16:47','koppalavenky@yopmail.com','koppala','CDjzTvHqvi1Eq6iEZjvEnQ==',NULL,'ACTIVE','2016-05-14 01:39:51',6,2,NULL,'2016-05-14 01:39:51',NULL,NULL,NULL),(7,'2016-05-10 22:44:00','nazeer.basha@tresbu.com','nazeer','OhLgLZVyg0ZHTMb9L6S5OQ==',NULL,'ACTIVE','2016-05-14 03:12:17',7,2,NULL,'2016-05-14 03:12:17',NULL,NULL,NULL),(8,'2016-05-10 22:46:37','nitinsai.n@tresbu.com','Nitin','MqRRipZvcCsmrOWNsYXNhA==',NULL,'ACTIVE','2016-05-10 22:50:44',8,2,NULL,NULL,NULL,NULL,NULL),(9,'2016-05-10 23:16:12','premsai.g@tresbu.com','prem',NULL,NULL,'REGISTERED',NULL,9,2,NULL,NULL,NULL,NULL,NULL),(10,'2016-05-10 23:27:05','prem@yopmail.com','prem','9m0EAXwvYz7gTe/1m4ixMg==',NULL,'ACTIVE','2016-05-10 23:27:50',10,2,NULL,NULL,NULL,NULL,NULL),(11,'2016-05-11 03:45:25','tenant@yopmail.com','Tenant12','61Ew1QkBF7Bn32IJ4QgJEg==','','ACTIVE','2016-05-11 03:46:27',11,2,NULL,NULL,NULL,NULL,NULL),(12,'2016-05-11 22:59:32','tenant3@yopmail.com','Tenant3','+L2ZGFRWmqwfDQ2XHsWKTg==',NULL,'ACTIVE','2016-05-12 00:14:36',12,2,NULL,'2016-05-12 00:14:36',NULL,NULL,NULL),(13,'2016-05-11 23:05:55','t3u2@yopmail.com','t3u2',NULL,'12345','REGISTERED',NULL,12,3,NULL,NULL,NULL,NULL,NULL),(14,'2016-05-12 04:34:08','sitaram.shastri@yopmail.com','S Shastri','+L2ZGFRWmqwfDQ2XHsWKTg==',NULL,'ACTIVE','2016-05-12 11:27:38',1,3,NULL,'2016-05-12 11:27:38',NULL,NULL,'sshastri'),(15,'2016-05-12 12:42:54','nvadmin@yopmail.com','NV Admin','+L2ZGFRWmqwfDQ2XHsWKTg==','12345','ACTIVE','2016-05-12 12:44:28',1,3,NULL,NULL,NULL,NULL,'nvadmin'),(16,'2016-05-12 23:44:43','tenant5@yopmail.com','Tenant5','+L2ZGFRWmqwfDQ2XHsWKTg==',NULL,'ACTIVE','2016-05-12 23:52:59',13,2,NULL,NULL,NULL,NULL,'tenant5'),(17,'2016-05-13 00:46:06','vishwanath.hd@tresbu.com','vishwa','Xo05jQ6SrpomrOWNsYXNhA==',NULL,'ACTIVE','2016-05-13 00:47:49',14,2,NULL,'2016-05-13 00:47:49',NULL,NULL,'vishwa'),(18,'2016-05-13 00:49:50','ajay1@yopmail.com','Ajay','qSJtu6C87plEq6iEZjvEnQ==','1234567890','ACTIVE','2016-05-13 00:53:06',14,3,NULL,'2016-05-13 00:53:06',NULL,NULL,'ajay1'),(19,'2016-05-13 02:07:58','ciscosus@yopmail.com','Cisco Susyems','VmjojGooaUdEq6iEZjvEnQ==',NULL,'ACTIVE','2016-05-13 06:00:29',15,2,NULL,NULL,NULL,NULL,'cisco123'),(20,'2016-05-13 05:47:03','tenant7@yopmail.com','tenant7','+L2ZGFRWmqwfDQ2XHsWKTg==',NULL,'ACTIVE','2016-05-14 05:06:33',16,2,NULL,'2016-05-14 05:06:33','ipeyVxY3TPxcUCEzKtAjt4yWEzQsnqEmkOSGwFPA',NULL,'tenant7'),(21,'2016-05-13 09:22:58','tenant20@yopmail.com','Tenant20','+L2ZGFRWmqwfDQ2XHsWKTg==',NULL,'ACTIVE','2016-05-13 09:36:22',17,2,NULL,NULL,NULL,NULL,'tenant20'),(22,'2016-05-13 12:00:23','tenant21@yopmail.com','tenant21','+L2ZGFRWmqwfDQ2XHsWKTg==',NULL,'ACTIVE','2016-05-13 12:01:22',18,2,NULL,NULL,NULL,NULL,'tenant21'),(23,'2016-05-13 12:07:23','tenant22@yopmail.com','Tenant22','+L2ZGFRWmqwfDQ2XHsWKTg==',NULL,'ACTIVE','2016-05-13 12:33:16',19,2,NULL,NULL,NULL,NULL,'tenant22'),(24,'2016-05-13 12:45:26','tenant23@yopmail.com','tenant23','+L2ZGFRWmqwfDQ2XHsWKTg==',NULL,'ACTIVE','2016-05-13 12:53:47',20,2,NULL,NULL,NULL,NULL,'tenant23'),(25,'2016-05-13 13:05:46','tenant8@yopmail.com','tenant8','+L2ZGFRWmqwfDQ2XHsWKTg==',NULL,'ACTIVE','2016-05-13 13:13:06',21,2,NULL,NULL,'Ckr4VnQvx9L5wHfNAICcw7ivOm7YBRUmeQ2aTyP7',NULL,'tenant8'),(26,'2016-05-13 13:35:05','fakeadmin@yopmail.com','fakeOrg','HYZwyaRRGvVQETtjZd6dSQ==',NULL,'ACTIVE','2016-05-13 13:43:37',22,2,NULL,'2016-05-13 13:43:37',NULL,NULL,'fakeadmin'),(27,'2016-05-13 13:37:30','andy@yopmail.com','Andy Corp.','7bw48MnIyMhEq6iEZjvEnQ==',NULL,'ACTIVE','2016-05-13 13:38:34',23,2,NULL,NULL,NULL,NULL,'andy'),(28,'2016-05-14 03:34:28','parasaram.bombale@tresbu.com','Ram','A/HUG1liJUt4KXjuleCDYg==',NULL,'ACTIVE','2016-05-14 04:58:33',24,2,NULL,'2016-05-14 04:58:33',NULL,NULL,'parasaram.bombale@tresbu.com');
/*!40000 ALTER TABLE `NV_USER_MASTER` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-05-14  5:16:41
