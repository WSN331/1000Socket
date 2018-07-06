/*
SQLyog v10.2 
MySQL - 5.5.56-MariaDB : Database - socket_test
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`socket_test` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `socket_test`;

/*Table structure for table `t_device` */

DROP TABLE IF EXISTS `t_device`;

CREATE TABLE `t_device` (
  `c_id` int(11) NOT NULL AUTO_INCREMENT,
  `c_device_id` varchar(32) DEFAULT NULL,
  `c_version` varchar(32) DEFAULT NULL,
  `c_create_time` datetime DEFAULT NULL,
  `c_type` int(11) DEFAULT NULL,
  `c_switch_1` int(11) DEFAULT '0',
  `c_switch_2` int(11) DEFAULT '0',
  `c_online` int(1) DEFAULT NULL,
  `c_last_heart` datetime DEFAULT NULL,
  PRIMARY KEY (`c_id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;

/*Data for the table `t_device` */

insert  into `t_device`(`c_id`,`c_device_id`,`c_version`,`c_create_time`,`c_type`,`c_switch_1`,`c_switch_2`,`c_online`,`c_last_heart`) values (11,'dlkz000111111111','YT-DZY01 0.01\0\0\0','2020-09-13 16:44:22',768,0,0,0,NULL),(12,'dlkz1002','DLKZ01 0.03','2018-06-15 10:40:58',1024,0,0,0,'2018-06-15 11:54:50'),(13,'dlkz1001','DLKZ01 0.03','2018-06-15 10:51:15',1024,0,0,0,'2018-07-03 16:54:57'),(14,'dlkz0003','DLKZ01 0.03','2018-06-15 11:01:31',1024,0,0,0,'2018-06-15 11:16:32'),(15,'dlkz0004','DLKZ01 0.03','2018-06-15 11:18:51',1024,0,0,0,'2018-06-15 11:19:52'),(16,'dlkz0005','DLKZ01 0.02','2018-06-15 11:22:43',1024,0,0,0,NULL),(17,'dlkz0006','DLKZ01 0.02','2018-06-15 11:26:05',1024,0,0,0,NULL),(18,'dlkz0007','DLKZ01 0.02','2018-06-15 11:30:04',1024,0,0,0,NULL),(19,'dlkz0008','DLKZ01 0.02','2018-06-15 11:32:28',1024,0,0,0,NULL),(20,'dlkz0009','DLKZ01 0.02','2018-06-15 11:34:35',1024,0,0,0,NULL),(21,'dlkz0010','DLKZ01 0.02','2018-06-15 11:36:24',1024,0,0,0,'2018-06-15 11:37:34'),(22,'dlkz0011','DLKZ01 0.02','2018-06-15 12:01:37',1024,0,0,0,'2018-06-15 13:55:16'),(23,'dlkz0012','DLKZ01 0.02','2018-06-15 14:12:54',1024,0,0,0,'2018-06-29 17:44:27');

/*Table structure for table `t_version` */

DROP TABLE IF EXISTS `t_version`;

CREATE TABLE `t_version` (
  `c_id` int(11) NOT NULL AUTO_INCREMENT,
  `c_version` varchar(32) DEFAULT NULL,
  `c_size` int(11) DEFAULT NULL,
  `c_count` int(11) DEFAULT NULL,
  `c_location` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`c_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `t_version` */

insert  into `t_version`(`c_id`,`c_version`,`c_size`,`c_count`,`c_location`) values (1,'DLKZ01 0.03',12,4,'/home/update/DLKZ01_0_03.bin');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
