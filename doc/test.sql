/*
SQLyog v10.2 
MySQL - 5.6.15 : Database - socket_test
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
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
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

/*Data for the table `t_device` */

insert  into `t_device`(`c_id`,`c_device_id`,`c_version`,`c_create_time`,`c_type`,`c_switch_1`,`c_switch_2`,`c_online`,`c_last_heart`) values (11,'dlkz000111111111','YT-DZY01 0.01\0\0\0','2018-03-13 16:44:22',768,0,0,NULL,NULL);

/*Table structure for table `t_version` */

DROP TABLE IF EXISTS `t_version`;

CREATE TABLE `t_version` (
  `c_id` int(11) NOT NULL AUTO_INCREMENT,
  `c_version` varchar(32) DEFAULT NULL,
  `c_size` int(11) DEFAULT NULL,
  `c_count` int(11) DEFAULT NULL,
  PRIMARY KEY (`c_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `t_version` */

insert  into `t_version`(`c_id`,`c_version`,`c_size`,`c_count`) values (1,'xxx',12,4);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
