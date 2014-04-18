/*
SQLyog Community v9.50 Beta1
MySQL - 5.1.63-0ubuntu0.11.10.1 : Database - wormdetection
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`wormdetection` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `wormdetection`;

/*Table structure for table `File_Transfer` */

DROP TABLE IF EXISTS `File_Transfer`;

CREATE TABLE `File_Transfer` (
  `Client_ip` varchar(15) DEFAULT NULL,
  `Filename` varchar(100) DEFAULT NULL,
  `Status` varchar(35) DEFAULT NULL,
  `Send_ip` varchar(15) DEFAULT NULL,
  `date` varchar(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

/*Data for the table `File_Transfer` */

insert  into `File_Transfer`(`Client_ip`,`Filename`,`Status`,`Send_ip`,`date`) values ('127.0.0.1','/home/reshma/Desktop/a.jpg','Not Connected','192.168.1.30',NULL),('192.168.1.160','/home/reshma/Desktop/a.jpg','Not Connected','192.168.1.30',NULL),('192.168.1.160','/home/reshma/Downloads/downloaded pics/109177.gif','Not Connected','192.168.1.30',NULL),('192.168.1.160','/home/reshma/Downloads/downloaded pics/109177.gif','Not Connected','192.168.1.30',NULL),('192.168.1.160','/home/reshma/Desktop/a.jpg','Not Connected','192.168.1.30',NULL),('192.168.1.30','/home/reshma/Desktop/a.jpg','Offline','192.168.1.30',NULL),('192.168.1.160','/home/reshma/Desktop/a.jpg','Not Connected','192.168.1.30',NULL),('192.168.1.160','/home/reshma/Desktop/Calendar2012.pdf','Not Connected','192.168.1.30',NULL);

/*Table structure for table `New_Clients` */

DROP TABLE IF EXISTS `New_Clients`;

CREATE TABLE `New_Clients` (
  `New_Clientip` varchar(30) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

/*Data for the table `New_Clients` */

insert  into `New_Clients`(`New_Clientip`) values ('192.168.1.160');

/*Table structure for table `registration` */

DROP TABLE IF EXISTS `registration`;

CREATE TABLE `registration` (
  `user_name` varchar(15) DEFAULT NULL,
  `ip_adress` varchar(20) NOT NULL,
  `status` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`ip_adress`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

/*Data for the table `registration` */

insert  into `registration`(`user_name`,`ip_adress`,`status`) values ('Dieutek 1','192.168.1.30','Not Connected'),('resh','192.168.1.160','Not Connected'),('Dieutek 3','127.0.0.1','Not connected'),('githu','192.168.1.33','Not Connected');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
