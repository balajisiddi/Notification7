-- MySQL Administrator dump 1.4
--
-- ------------------------------------------------------
-- Server version	5.5.62


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


--
-- Create schema `sector-seven`
--

CREATE DATABASE IF NOT EXISTS `sector-seven`;
USE `sector-seven`;

--
-- Definition of table `abilities`
--

DROP TABLE IF EXISTS `abilities`;
CREATE TABLE `abilities` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `ability` varchar(45) NOT NULL,
  `subcat_id` bigint(20) unsigned NOT NULL,
  `date_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `status` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_abilities_subcat` (`subcat_id`),
  CONSTRAINT `FK_abilities_subcat` FOREIGN KEY (`subcat_id`) REFERENCES `career_subcategory` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `abilities`
--

/*!40000 ALTER TABLE `abilities` DISABLE KEYS */;
INSERT INTO `abilities` (`id`,`ability`,`subcat_id`,`date_created`,`status`) VALUES 
 (1,'asfsdfsd',9,'2019-10-25 17:57:17',0),
 (2,'sdfffff',9,'2019-10-25 17:57:17',0),
 (3,'sasfdsf',9,'2019-10-25 17:57:17',0),
 (4,'sdf',13,'2019-10-28 13:38:53',0),
 (5,'adsa',16,'2019-11-04 20:48:50',0),
 (6,'dfh',16,'2019-11-04 20:48:50',0),
 (7,'dfd',16,'2019-11-04 20:48:50',0),
 (8,'asd',17,'2019-11-05 12:45:06',0),
 (9,'asdsa',17,'2019-11-05 12:45:06',0),
 (10,'asdsa',17,'2019-11-05 12:45:06',0),
 (11,'asd',17,'2019-11-05 12:45:06',0);
/*!40000 ALTER TABLE `abilities` ENABLE KEYS */;


--
-- Definition of table `academic_year`
--

DROP TABLE IF EXISTS `academic_year`;
CREATE TABLE `academic_year` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `date_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `ending_date` datetime DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `starting_date` datetime DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `academic_year`
--

/*!40000 ALTER TABLE `academic_year` DISABLE KEYS */;
INSERT INTO `academic_year` (`id`,`date_created`,`ending_date`,`name`,`starting_date`,`status`) VALUES 
 (1,'2019-10-10 12:42:24','2020-03-31 00:00:00','2019-20','2019-04-01 00:00:00',0);
/*!40000 ALTER TABLE `academic_year` ENABLE KEYS */;


--
-- Definition of table `activity_log`
--

DROP TABLE IF EXISTS `activity_log`;
CREATE TABLE `activity_log` (
  `id` bigint(10) unsigned NOT NULL AUTO_INCREMENT,
  `message` varchar(45) NOT NULL,
  `user` bigint(10) unsigned NOT NULL,
  `authority` varchar(45) NOT NULL,
  `screen` varchar(45) DEFAULT NULL,
  `action` varchar(45) CHARACTER SET latin1 COLLATE latin1_bin DEFAULT NULL,
  `media_id` bigint(10) unsigned DEFAULT NULL,
  `status` int(10) unsigned NOT NULL,
  `date_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `sub_cat` bigint(10) unsigned DEFAULT NULL,
  `video_type` int(10) unsigned DEFAULT '0',
  `youtube_url` varchar(45) DEFAULT NULL,
  `count` int(10) unsigned DEFAULT NULL,
  `media_type` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_activity_log_career` (`media_id`) USING BTREE,
  KEY `FK_activity_log_subcat` (`sub_cat`),
  CONSTRAINT `FK_activity_log_subcat` FOREIGN KEY (`sub_cat`) REFERENCES `career_subcategory` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `activity_log`
--

/*!40000 ALTER TABLE `activity_log` DISABLE KEYS */;
INSERT INTO `activity_log` (`id`,`message`,`user`,`authority`,`screen`,`action`,`media_id`,`status`,`date_created`,`sub_cat`,`video_type`,`youtube_url`,`count`,`media_type`) VALUES 
 (1,'Your Profile Updated Successfully',16,'ROLE_STUDENT',NULL,0x557064617465,NULL,0,'2019-11-04 17:13:00',NULL,0,NULL,NULL,NULL),
 (2,'Your Profile Image Updated Successfully',16,'ROLE_STUDENT','Profile',0x557064617465,NULL,0,'2019-11-04 17:13:00',NULL,0,NULL,NULL,NULL),
 (3,'You have Seen this video',16,'ROLE_STUDENT',NULL,0x636F6E747269627574696F6E,7,0,'2019-11-08 17:23:13',2,0,NULL,NULL,NULL),
 (4,'You have Seen this video',16,'ROLE_STUDENT',NULL,0x636F6E747269627574696F6E,7,0,'2019-11-08 17:23:13',2,0,NULL,NULL,NULL),
 (5,'You have Seen this video',16,'ROLE_STUDENT',NULL,0x636F6E747269627574696F6E,7,0,'2019-11-08 17:23:14',2,0,NULL,NULL,NULL),
 (8,'You have Seen this video',16,'ROLE_STUDENT','sigma',0x566964656F,28,0,'2019-11-08 17:17:26',1,NULL,NULL,NULL,NULL),
 (9,'You have Seen this video',16,'ROLE_STUDENT','sigma',0x566964656F,28,0,'2019-11-08 17:17:26',1,NULL,NULL,NULL,NULL),
 (10,'You have Seen this video',16,'ROLE_STUDENT','sigma',0x566964656F,28,0,'2019-11-08 17:17:26',1,NULL,NULL,NULL,NULL),
 (11,'You have Seen this video',16,'ROLE_STUDENT','sigma',0x566964656F,28,0,'2019-11-08 17:17:26',1,NULL,NULL,NULL,NULL),
 (12,'You have Seen this video',16,'ROLE_STUDENT','sigma',0x566964656F,28,0,'2019-11-08 17:17:26',1,NULL,NULL,NULL,NULL),
 (13,'You have Seen this video',16,'ROLE_STUDENT','sigma',0x417564696F,28,0,'2019-11-08 17:19:47',1,NULL,NULL,NULL,NULL),
 (14,'You have Seen this video',16,'ROLE_STUDENT','sigma',0x417564696F,28,0,'2019-11-08 17:19:47',1,NULL,NULL,NULL,NULL),
 (15,'You have Seen this video',16,'ROLE_STUDENT','sigma',0x417564696F,28,0,'2019-11-08 17:19:47',1,NULL,NULL,NULL,NULL),
 (16,'You have Seen this video',16,'ROLE_STUDENT','sigma',0x417564696F,28,0,'2019-11-08 17:19:47',1,NULL,NULL,NULL,NULL),
 (17,'You have Seen this video',16,'ROLE_STUDENT','sigma',0x417564696F,28,0,'2019-11-08 17:19:47',1,NULL,NULL,NULL,NULL),
 (18,'You have Seen this video',16,'ROLE_STUDENT','sigma',0x417564696F,28,0,'2019-11-08 17:19:47',1,NULL,NULL,NULL,NULL),
 (19,'You have Seen this video',16,'ROLE_STUDENT','sigma',0x417564696F,28,0,'2019-11-08 17:19:47',1,NULL,NULL,NULL,NULL),
 (20,'You have Seen this video',16,'ROLE_STUDENT','sigma',0x506466,28,0,'2019-11-08 17:19:47',1,NULL,NULL,NULL,NULL),
 (21,'You have Seen this video',16,'ROLE_STUDENT','sigma',0x506466,28,0,'2019-11-08 17:19:47',1,NULL,NULL,NULL,NULL),
 (22,'You have Seen this video',16,'ROLE_STUDENT','sigma',0x506466,28,0,'2019-11-08 17:19:47',1,NULL,NULL,NULL,NULL),
 (23,'You have Seen this video',16,'ROLE_STUDENT','sigma',0x506466,28,0,'2019-11-08 17:19:47',1,NULL,NULL,NULL,NULL),
 (24,'You have Seen this video',16,'ROLE_STUDENT','video/mp4',0x566964656F,12,0,'2019-11-26 16:02:06',1,NULL,NULL,NULL,NULL),
 (25,'You have Seen this video',16,'ROLE_STUDENT','video/mp4',0x566964656F,15,0,'2019-11-26 16:02:06',1,NULL,NULL,NULL,NULL),
 (26,'You have Seen this video',16,'ROLE_STUDENT','video/mp4',0x566964656F,28,0,'2019-11-09 12:38:32',1,NULL,NULL,NULL,NULL),
 (27,'You have Seen this video',16,'ROLE_STUDENT','video/mp4',0x566964656F,28,0,'2019-11-09 12:38:32',NULL,NULL,NULL,NULL,NULL),
 (28,'You have Seen this video',16,'ROLE_STUDENT','video/mp4',0x566964656F,28,0,'2019-11-09 12:38:32',NULL,NULL,NULL,NULL,NULL),
 (38,'Your Profile Image Updated Successfully',16,'ROLE_STUDENT','Profile',0x557064617465,NULL,0,'2019-11-09 10:09:06',NULL,NULL,NULL,1,NULL),
 (39,'Contributed Successfully',16,'ROLE_STUDENT','contribution',0x636F6E747269627574696F6E,NULL,0,'2019-11-09 10:52:44',NULL,NULL,NULL,1,NULL),
 (41,'Your Profile Image Updated Successfully',16,'ROLE_STUDENT','Profile',0x557064617465,NULL,0,'2019-11-09 14:02:01',NULL,NULL,NULL,1,NULL),
 (42,'Your Profile Image Updated Successfully',16,'ROLE_STUDENT','Profile',0x557064617465,NULL,0,'2019-11-09 14:02:48',NULL,NULL,NULL,1,NULL),
 (43,'Your Profile Image Updated Successfully',16,'ROLE_STUDENT','Profile',0x557064617465,NULL,0,'2019-11-09 14:10:17',NULL,NULL,NULL,1,NULL),
 (44,'Your Profile Image Updated Successfully',16,'ROLE_STUDENT','Profile',0x557064617465,NULL,0,'2019-11-09 14:12:27',NULL,NULL,NULL,1,NULL),
 (45,'hello',16,'ROLE_STUDENT','subCategroy',0x566964656F,205,0,'2019-12-16 11:58:06',NULL,1,NULL,8,NULL),
 (46,'hello',16,'ROLE_STUDENT','subCategroy',0x566964656F,206,0,'2019-12-16 12:22:59',NULL,1,NULL,3,NULL),
 (47,'hello',26,'ROLE_STUDENT','subCategroy',0x566964656F,46,0,'2019-12-16 12:46:41',NULL,1,NULL,4,NULL),
 (48,'hiii',26,'ROLE_STUDENT','Recom',0x566964656F,43,0,'2019-12-17 11:21:53',1,1,'asdsd',1,'video/mp4'),
 (49,'UnSubscribed Successfully',16,'ROLE_STUDENT','Subscribed',0x53756273637269626564,NULL,0,'2019-12-20 17:53:05',13,NULL,NULL,1,NULL),
 (50,'Your Profile Image Updated Successfully',16,'ROLE_STUDENT','Profile',0x557064617465,NULL,0,'2019-12-23 10:40:02',NULL,NULL,NULL,1,NULL);
/*!40000 ALTER TABLE `activity_log` ENABLE KEYS */;


--
-- Definition of table `add_event`
--

DROP TABLE IF EXISTS `add_event`;
CREATE TABLE `add_event` (
  `id` bigint(10) unsigned NOT NULL AUTO_INCREMENT,
  `event_name` varchar(45) NOT NULL,
  `event_date` varchar(45) NOT NULL,
  `event_time` varchar(100) NOT NULL,
  `description` varchar(45) NOT NULL,
  `status` int(10) NOT NULL,
  `date_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `hub_zone` bigint(20) unsigned DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_add_event_hub` (`hub_zone`),
  CONSTRAINT `FK_add_event_hub` FOREIGN KEY (`hub_zone`) REFERENCES `co_creation_hub` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `add_event`
--

/*!40000 ALTER TABLE `add_event` DISABLE KEYS */;
INSERT INTO `add_event` (`id`,`event_name`,`event_date`,`event_time`,`description`,`status`,`date_created`,`hub_zone`) VALUES 
 (1,'sads','2019-10-09','10:00 PM','asdsa',0,'2019-09-21 15:04:59',NULL),
 (2,'szds','2019-10-10','10:00 PM','asddsf',0,'2019-09-21 15:04:59',NULL),
 (3,'dfdsf','2019-10-12','sdf','dsf',0,'2019-09-21 15:04:59',NULL),
 (4,'zxc','2019-10-15','zx','zx',0,'2019-09-21 15:04:59',NULL),
 (5,'sdsa','2019-10-17','10:00 AM','zxcsdf',0,'2019-09-21 14:22:56',NULL),
 (6,'ABSS','2019-10-20','11:00 AM','zcxxxxxxxxsafsfd',0,'2019-10-23 12:55:19',1),
 (7,'sdfsdf','2019-10-22','fsdf','dsfsdf',0,'2019-10-23 12:55:19',1),
 (8,'zcxzxc','2019-09-18','10:00 PM','zxc',0,'2019-10-23 12:55:46',1);
/*!40000 ALTER TABLE `add_event` ENABLE KEYS */;


--
-- Definition of table `administrator`
--

DROP TABLE IF EXISTS `administrator`;
CREATE TABLE `administrator` (
  `authority` varchar(31) NOT NULL,
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `date_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `date_of_birth` datetime DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `first_name` varchar(255) NOT NULL,
  `image` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `mobile` bigint(20) NOT NULL,
  `password` varchar(255) NOT NULL,
  `status` int(11) DEFAULT NULL,
  `username` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `administrator`
--

/*!40000 ALTER TABLE `administrator` DISABLE KEYS */;
INSERT INTO `administrator` (`authority`,`id`,`date_created`,`date_of_birth`,`email`,`first_name`,`image`,`last_name`,`mobile`,`password`,`status`,`username`) VALUES 
 ('1',1,'2019-10-10 12:42:49','1993-01-01 00:00:00','admin@sectorseven.com','Ramesh ','','Naidu',9032508683,'$2a$04$KD9xWj0i3TuDyT/5pog3lOvZbNqFEkCDq2d7RnVdWM8snPM7R7WQO',0,'admin');
/*!40000 ALTER TABLE `administrator` ENABLE KEYS */;


--
-- Definition of table `administrator_authority`
--

DROP TABLE IF EXISTS `administrator_authority`;
CREATE TABLE `administrator_authority` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `authority` bigint(20) DEFAULT NULL,
  `user_name` varchar(100) NOT NULL,
  `decrypt_password` varchar(100) NOT NULL,
  `user_type` varchar(45) NOT NULL,
  `user` bigint(10) unsigned DEFAULT NULL,
  `status` int(10) unsigned NOT NULL,
  `date_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `FK_besiq0st4oup49095y1c5cq04` (`authority`),
  CONSTRAINT `FK_besiq0st4oup49095y1c5cq04` FOREIGN KEY (`authority`) REFERENCES `authority` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=50 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `administrator_authority`
--

/*!40000 ALTER TABLE `administrator_authority` DISABLE KEYS */;
INSERT INTO `administrator_authority` (`id`,`authority`,`user_name`,`decrypt_password`,`user_type`,`user`,`status`,`date_created`) VALUES 
 (1,1,'admin','$2a$04$KD9xWj0i3TuDyT/5pog3lOvZbNqFEkCDq2d7RnVdWM8snPM7R7WQO','ROLE_ADMIN',NULL,0,'2019-10-11 13:42:05'),
 (2,2,'ADR56719000001','$2a$10$hUOyur5SwUnQhI9Rh7h96.9UiFdkpMfoycdYZbE7eXeEyydjrL/cO','ROLE_STUDENT',4,0,'2019-10-11 13:42:05'),
 (3,2,'SC19000006','xnwnw/qN','ROLE_STUDENT',NULL,0,'0000-00-00 00:00:00'),
 (4,2,'SC19000007','Y7GANK^Z','ROLE_STUDENT',NULL,0,'0000-00-00 00:00:00'),
 (5,8,'haritha@gmail.com','$2a$10$p0mlwwBSAdL0.liNClL6fetMr0l9ApbOXrchhpkK/6aPjeILvhfYe','ROLE_MENTOR',NULL,0,'0000-00-00 00:00:00'),
 (6,3,'ASD@GMAIL.COM','$2a$10$SmCjZLXxDALFm6teAPGuuu0WU4id2VHejjtHdIFcMx8JsKhGqSpdC','ROLE_PARENT',NULL,0,'0000-00-00 00:00:00'),
 (7,7,'haritha34@gmail.com','$2a$10$nCNkim0/vp5LZTmE3QSfi.LsMYKBNqpEgb5gsf/bBZXfo/dpL3FUG','ROLE_SCHOOL_TEACHER',NULL,0,'2019-10-22 10:03:00'),
 (8,3,'raefew@gmail.com','$2a$10$/NQwJVNLqlC.1cpZW6JlwOC1y806TgxDy0YwIXZXribDMbq9czGc2','ROLE_PARENT',NULL,0,'0000-00-00 00:00:00'),
 (9,2,'SC19000008','aN/UU%d0','ROLE_STUDENT',NULL,0,'0000-00-00 00:00:00'),
 (10,7,'dsfs@gjjasd.com','$2a$10$mEb3OXo6.exfZdlx4IlexOMifHiIucPb5aa/JRGke2HSjmUngsGIu','ROLE_SCHOOL_TEACHER',4,0,'2019-10-15 16:02:52'),
 (11,7,'haritha@gmail.com','$2a$10$pNAMu0VTryUBdU2RurrcYelWuUJOR0d337rsB8TS21j7nm8eVtq0S','ROLE_SCHOOL_TEACHER',2,0,'2019-10-15 16:02:52'),
 (12,3,'sad@gmai.com','$2a$10$u9cenMeK4TCQ./yyZV9Mme6DdjqwZGvN3v7QnBUam0tmaToJG4HUS','ROLE_PARENT',NULL,0,'0000-00-00 00:00:00'),
 (13,3,'ZDSS@GMAIL.COM','$2a$10$NrpHwAd.6ynTi5SmSd2FguHpU6/LIwEYWXMPdcKTauRme3QhZYasS','ROLE_PARENT',9,0,'2019-10-15 15:33:41'),
 (14,2,'SC19000011','$PKgZnTs','ROLE_STUDENT',NULL,0,'0000-00-00 00:00:00'),
 (15,3,'ranu@gmail.com','$2a$10$UZNFfiGxXeJK/0gL5LY6cOgPhFs2wWYDIoD4MbtstU7B4gw21X8K6','ROLE_PARENT',NULL,0,'0000-00-00 00:00:00'),
 (16,2,'SC19000013','Q9&y9l+6','ROLE_STUDENT',NULL,0,'0000-00-00 00:00:00'),
 (17,2,'SC19000014','TYbXzSIt','ROLE_STUDENT',NULL,0,'0000-00-00 00:00:00'),
 (18,3,'sfsdfd@GMAIL.COM','$2a$10$NsnPrMk.EDO1EgSHwcxRjukELe21GdWargEavCx2h5Q66oairZuga','ROLE_PARENT',NULL,0,'0000-00-00 00:00:00'),
 (19,3,'tdsdfs@gmail.com','$2a$10$eP8WoAqt2s2610YH4T.svOxnG5AWA8bQM2H9zDkPuyUj8ogc0qDGq','ROLE_PARENT',NULL,0,'0000-00-00 00:00:00'),
 (20,2,'SC19000017','d7kh@lwf','ROLE_STUDENT',NULL,0,'0000-00-00 00:00:00'),
 (21,3,'sdfsdf@gmail.com','$2a$10$3TUON4A/1hOxmM30RQ2Rhu.zZrCxv8lOxIZYiBAT0f3fiepFXBCWq','ROLE_PARENT',NULL,0,'0000-00-00 00:00:00'),
 (22,2,'SC19000018','$6Xh%asI','ROLE_STUDENT',NULL,0,'0000-00-00 00:00:00'),
 (23,3,'sdfsd@gmail.com','$2a$10$flmS7KfckzIEsJ8S5O/H3.RT4y9f1WPm6yC8OWVbS1NBl7gtNBwOK','ROLE_PARENT',NULL,0,'0000-00-00 00:00:00'),
 (24,2,'SC19000019','g!TNRf5#','ROLE_STUDENT',NULL,0,'0000-00-00 00:00:00'),
 (25,8,'hari@gmail.com','$2a$10$L3GaBQQ/mJErpkt8k1qhOOMX5N4OZ1hlyHc22oyX8QUAuW7wvZkIS','ROLE_MENTOR',2,0,'0000-00-00 00:00:00'),
 (26,2,'SC19000025','$2a$10$aqkexL.t17CF/XmQ/iPVO.fhJxCZGKacnbPS21SylRnAcu4xT7Iqi','ROLE_STUDENT',16,0,'2019-10-11 14:16:30'),
 (27,8,'vlopment000001','$2a$10$ikDotK42vhWMT4j4WvkT7u4baBb6eVFLUt9kbV9MAYwg/59PdX40O','ROLE_MENTOR',5,0,'2019-10-16 12:55:56'),
 (28,8,'dasd0002','$2a$10$g5LwhQ4Ais/wIX441VO5MudZ2oVJjzTQW5RSfgId7WOPS3zr/sKUS','ROLE_MENTOR',6,0,'2019-10-16 13:06:23'),
 (29,3,'sdal0003','$2a$10$MIM.nOCYi5KS5qRYjKAoHOnaxwzCrT5nY.xDMBc2qoilYVBfFf7o.','ROLE_PARENT',21,0,'2019-10-16 13:42:31'),
 (30,7,'attar@gmail.com','$2a$10$tYfgdKCOQ9GG5eGRBMg70e7HTbfMDpuqii77J8K3CVKObecGnynXK','ROLE_SCHOOL_TEACHER',4,0,'2019-10-22 11:16:41'),
 (31,7,'fsdfs0001','$2a$10$HQ11Gn3EpSR.bDR2kkT5Ae63G6o0F/RlnwJJmn1elEPsxa9OKT1sC','ROLE_SCHOOL_TEACHER',5,0,'2019-10-23 10:18:31'),
 (32,2,'SC19000026','$2a$10$zF6G1OPWhesj57L1LkzX7O1UNFbf04c7R60l/nj7mQEbiCuz7Q0rW','ROLE_STUDENT',17,0,'2019-10-23 11:37:06'),
 (33,8,'f0004','$2a$10$HcCdcGtsE6lHMn93n715HuF3jX4us3U/T17y58pk4KbNf7lYgdKAa','ROLE_MENTOR',7,0,'2019-10-23 12:07:58'),
 (34,8,'fds0005','$2a$10$M0gN/C38B0tHCIQb.F38wOExLSBKtWPl7XD5DX8PkAoS4jf1nPpa.','ROLE_MENTOR',8,0,'2019-10-23 12:23:36'),
 (35,8,'fdsf0006','$2a$10$nMaMxihB.GMxytEPjcUrr.DPyEvx/zt3KAN2jrCmaUyv7q/EB7/sy','ROLE_MENTOR',9,0,'2019-10-23 12:40:50'),
 (36,3,'alal0007','$2a$10$ry25yJFGvvfVSNWpqvFz2uLacyVbwoXYM9jE0WoRUMldWavqx6hBe','ROLE_PARENT',22,0,'2019-12-21 11:45:57'),
 (37,2,'SC19000030','$2a$10$ST1DnYwwEZTzxp.aQoSv/u5PIuUIfcL/LXmgjwCVD96nDrxL9zZ6G','ROLE_STUDENT',24,0,'2019-12-21 11:45:57'),
 (38,3,'anan0008','$2a$10$cCHnG0/KKo31CyrEH09z2eyh2/PkOE.667S4RbhVoqstNlX4eJsaq','ROLE_PARENT',23,0,'2019-12-26 17:38:54'),
 (39,3,'anan0009','$2a$10$ZDP.XinOHly0OmHR1Qa3yeUmDUgMsCuYCAzXDT1wGQfHdvMRVI.zK','ROLE_PARENT',24,0,'2019-12-26 17:49:21'),
 (40,3,'anan0010','$2a$10$M.FB2QL6Dn/OiO2LW4IvwOsz7l6jotfjh1U/ycoohys17GvAEYzXS','ROLE_PARENT',25,0,'2019-12-26 17:49:32'),
 (41,3,'anan0011','$2a$10$UiPmV/qQ8j34jqLCWU9mHeXj.YQO2/FhvrjPriqyTr.b9jdXeduIu','ROLE_PARENT',26,0,'2019-12-26 17:50:15'),
 (42,2,'ADR56719000008','$2a$10$Cj0Twhk/0HSnX35Cip4U.uzssFQBUoHbX5Gz/beSW8GFBUDMBfGca','ROLE_STUDENT',28,0,'2019-12-26 17:50:15'),
 (43,3,'anan0012','$2a$10$rHjsTNSat4OLUKD2bfO.c.2JS9tBJ3IuViTWJbzkKYnCRjwEkUhBm','ROLE_PARENT',27,0,'2019-12-26 17:50:15'),
 (44,2,'ADR56719000009','$2a$10$1pDhWwSE6ZZbCf11XHnhPOg5tXPEbrrU3ztomRxEYqdOKbHehJlsK','ROLE_STUDENT',29,0,'2019-12-26 17:50:15'),
 (45,3,'anan0013','$2a$10$rZQ1.oG8k/gMrKgO0IUeJOPH9ogpiJusxfer5Zd3jkRtfxgEqf/oi','ROLE_PARENT',28,0,'2019-12-26 17:50:16'),
 (46,2,'ADR56719000010','$2a$10$f2r1uBJwx8Ie88Fb6lmrE.vmOIlzgBsLBsEzv0xr.9FKL0Cn8YzWa','ROLE_STUDENT',30,0,'2019-12-26 17:50:16'),
 (47,3,'anan0014','$2a$10$n8esXzACP.4qW0pR9UvahO3cqt7tXlSPgro4UdNKKP1yIyUXg7KDK','ROLE_PARENT',29,0,'2019-12-26 17:53:45'),
 (48,2,'ADR56719000011','$2a$10$prIvgr.H.qHzvZ50oOfb0.E1LI4ACQ7dPrQ8QLV/wn7dsh.zH8FxG','ROLE_STUDENT',31,0,'2019-12-26 17:53:45'),
 (49,8,'gineering0015','$2a$10$ldV3kb06ldApRyrNBZATr.W/1szWUanU8uavuMOasIuxsLSp3f9Vm','ROLE_MENTOR',10,0,'2019-12-26 18:00:03');
/*!40000 ALTER TABLE `administrator_authority` ENABLE KEYS */;


--
-- Definition of table `apk`
--

DROP TABLE IF EXISTS `apk`;
CREATE TABLE `apk` (
  `id` bigint(10) unsigned NOT NULL AUTO_INCREMENT,
  `ios_url` varchar(150) NOT NULL,
  `date_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `status` int(10) unsigned NOT NULL,
  `android_url` varchar(150) NOT NULL,
  `version` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `apk`
--

/*!40000 ALTER TABLE `apk` DISABLE KEYS */;
INSERT INTO `apk` (`id`,`ios_url`,`date_created`,`status`,`android_url`,`version`) VALUES 
 (1,'adasfd','2019-11-10 15:45:52',0,'','');
/*!40000 ALTER TABLE `apk` ENABLE KEYS */;


--
-- Definition of table `authority`
--

DROP TABLE IF EXISTS `authority`;
CREATE TABLE `authority` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `authority_code` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `authority`
--

/*!40000 ALTER TABLE `authority` DISABLE KEYS */;
INSERT INTO `authority` (`id`,`authority_code`) VALUES 
 (1,'ROLE_ADMIN'),
 (2,'ROLE_STUDENT'),
 (3,'ROLE_PARENT'),
 (4,'ROLE_SUPER_ADMIN'),
 (5,'ROLE_ORGANIZATION_ADMIN'),
 (6,'ROLE_SCHOOL_ADMIN'),
 (7,'ROLE_SCHOOL_TEACHER'),
 (8,'ROLE_MENTOR');
/*!40000 ALTER TABLE `authority` ENABLE KEYS */;


--
-- Definition of table `career_category`
--

DROP TABLE IF EXISTS `career_category`;
CREATE TABLE `career_category` (
  `id` bigint(10) unsigned NOT NULL AUTO_INCREMENT,
  `category_name` varchar(45) NOT NULL,
  `category_imag_name` varchar(45) DEFAULT NULL,
  `category_imag_path` varchar(45) DEFAULT NULL,
  `date_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `status` int(11) NOT NULL,
  `type` varchar(45) DEFAULT NULL,
  `intrst_imag_name` varchar(150) DEFAULT NULL,
  `intrst_imag_path` varchar(150) DEFAULT NULL,
  `intrst_type` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `career_category`
--

/*!40000 ALTER TABLE `career_category` DISABLE KEYS */;
INSERT INTO `career_category` (`id`,`category_name`,`category_imag_name`,`category_imag_path`,`date_created`,`status`,`type`,`intrst_imag_name`,`intrst_imag_path`,`intrst_type`) VALUES 
 (1,'sdsdf','act1.jpg','/categoryImages/1','2019-07-27 00:31:42',0,NULL,'','',NULL),
 (2,'Blockchain Developer','download.jpg','/categoryImages/2','2019-09-17 11:08:20',0,NULL,'','',NULL);
/*!40000 ALTER TABLE `career_category` ENABLE KEYS */;


--
-- Definition of table `career_subcategory`
--

DROP TABLE IF EXISTS `career_subcategory`;
CREATE TABLE `career_subcategory` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `sub_category_name` varchar(45) DEFAULT NULL,
  `subcategory_img_name` varchar(45) DEFAULT NULL,
  `subcategory_img_path` varchar(45) DEFAULT NULL,
  `date_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `status` int(10) unsigned DEFAULT NULL,
  `career_category` bigint(20) unsigned DEFAULT NULL,
  `trending` int(10) unsigned DEFAULT NULL,
  `amithe_one` varchar(1500) DEFAULT NULL,
  `insight_of_profession` varchar(1500) DEFAULT NULL,
  `side_img_name` varchar(200) DEFAULT NULL,
  `side_img_path` varchar(200) DEFAULT NULL,
  `where_it_will_take` varchar(1500) DEFAULT NULL,
  `landing_img_name` varchar(45) DEFAULT NULL,
  `landing_img_path` varchar(45) DEFAULT NULL,
  `amithe_one_img_name` varchar(45) DEFAULT NULL,
  `amithe_one_img_path` varchar(45) DEFAULT NULL,
  `landing_desc` varchar(2000) DEFAULT NULL,
  `how_to_get_there` varchar(2000) DEFAULT NULL,
  `course_cat1` varchar(2000) DEFAULT NULL,
  `course_cat2` varchar(2000) DEFAULT NULL,
  `course_cat3` varchar(2000) DEFAULT NULL,
  `course_cat4` varchar(2000) DEFAULT NULL,
  `course_cat5` varchar(2000) DEFAULT NULL,
  `entrance_cat1` varchar(2000) DEFAULT NULL,
  `entrance_cat2` varchar(2000) DEFAULT NULL,
  `entrance_cat3` varchar(2000) DEFAULT NULL,
  `india_pay` varchar(2000) DEFAULT NULL,
  `india_scholorship` varchar(2000) DEFAULT NULL,
  `abroad_colleges` varchar(2000) DEFAULT NULL,
  `abroad_pay` varchar(45) DEFAULT NULL,
  `abroad_scholorship` varchar(45) DEFAULT NULL,
  `job_roles` varchar(2000) DEFAULT NULL,
  `landing_quote` varchar(450) DEFAULT NULL,
  `course_cat6` varchar(2000) DEFAULT NULL,
  `entrance_cat4` varchar(2000) DEFAULT NULL,
  `landing_type` varchar(45) DEFAULT NULL,
  `am_type` varchar(45) DEFAULT NULL,
  `type` varchar(45) DEFAULT NULL,
  `jobs_description` varchar(2000) DEFAULT NULL,
  `salary_description` varchar(2000) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_career_subcategory_cat` (`career_category`),
  CONSTRAINT `FK_career_subcategory_cat` FOREIGN KEY (`career_category`) REFERENCES `career_category` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `career_subcategory`
--

/*!40000 ALTER TABLE `career_subcategory` DISABLE KEYS */;
INSERT INTO `career_subcategory` (`id`,`sub_category_name`,`subcategory_img_name`,`subcategory_img_path`,`date_created`,`status`,`career_category`,`trending`,`amithe_one`,`insight_of_profession`,`side_img_name`,`side_img_path`,`where_it_will_take`,`landing_img_name`,`landing_img_path`,`amithe_one_img_name`,`amithe_one_img_path`,`landing_desc`,`how_to_get_there`,`course_cat1`,`course_cat2`,`course_cat3`,`course_cat4`,`course_cat5`,`entrance_cat1`,`entrance_cat2`,`entrance_cat3`,`india_pay`,`india_scholorship`,`abroad_colleges`,`abroad_pay`,`abroad_scholorship`,`job_roles`,`landing_quote`,`course_cat6`,`entrance_cat4`,`landing_type`,`am_type`,`type`,`jobs_description`,`salary_description`) VALUES 
 (1,'category1','act1.jpg','/subCategoryImages/1','2019-07-27 10:36:58',0,1,0,'','','','',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),
 (2,'cat1','act1.jpg','/subCategoryImages/2','2019-08-19 20:34:38',0,1,1,'','','','',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),
 (3,'catero','act1.jpg','/subCategoryImages/3','2019-08-20 23:32:29',0,1,0,'','','','',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),
 (4,'subCate12','download.jpg','/subCategoryImages/4','2019-08-20 23:33:59',0,2,0,'','','','',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),
 (5,'asfsdf','download.jpg','/subCategoryImages/5','2019-08-20 23:39:49',0,2,1,'','','','',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),
 (6,'Zcxzc','Abha.jpg','/subCategoryImages/6','2019-10-01 14:24:36',0,2,0,'zxxxxxxxxxxxxxasdaaaaaaa','zczxcxxxzzzzzzzzzzzzzzzzzzzzzz','2.png','/subCategorySide/6','sadafdafds',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),
 (7,'xzc','Lighthouse.jpg','/subCategoryImages/7','2019-10-01 17:26:08',0,1,0,'xczzzzzzzzzzzzzz','xczzzzzzzzzzzzz','Jellyfish.jpg','/subCategorySide/7','asfffffffffffff',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),
 (8,'subCategory','Jellyfish.jpg','/subCategoryImages/8','2019-10-10 16:14:41',0,1,0,'am i the one','insight of profession','Tulips.jpg','/subCategorySide/8','where it wil take me',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),
 (9,'cxvxv',NULL,NULL,'2019-10-26 15:43:39',0,2,0,'Am I the one',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'sadadfsdf','zxvxzv','sfdsf,sdfds,asfsdfds','fdsfds,sfsedffff,sfdasd','dfsdfds,sdfdsf,sdfds,sdfds','fgfdgfd,sfdsfd,fdsfs,sdfsd','dfdsfsd,sdffffff,hghg','hghgh,hjgjg,jhjh,hghgj','hghgh,hjgjg,jhjh,hghgj','hghgh,hjgjg,jhjh,hghgj','sdfsdfdsfgdf','asffffff,safdsfsdf,sdfsdfsd','dsfdsfsss,sdfssss,sdffffffff,dfdsffff','dffdssdf','sdfsdf,sdfsdf,safsdf','asdasf,hjgjh,hjghj',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),
 (10,'zsfsdfsdgfdf',NULL,NULL,'2019-10-26 22:12:42',0,1,0,'Am I the one',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'Landoning Descript','dfdgdf','cvxxxx,dhhhhhh,dfhhh','cvxxxx,dhhhhhh,dfhhh','cvxxxx,dhhhhhh,dfhhh','cvxxxx,dhhhhhh,dfhhh','cvxxxx,dhhhhhh,dfhhh','cvxxxx,dhhhhhh,dfhhh','cvxxxx,dhhhhhh,dfhhh','cvxxxx,dhhhhhh,dfhhh','cvxxxx,dhhhhhh,dfhhh','cvxxxx,dhhhhhh,dfhhh','cvxxxx,dhhhhhh,dfhhh','cvxxxx,dhhhhhh,dfhhh','cvxxxx,dhhhhhh,dfhhh','cvxxxx,dhhhhhh,dfhhh','\"Why Linux over Windows 3D Animation.mp4\" id=\"video_here\"',NULL,NULL,NULL,NULL,NULL,NULL,NULL),
 (11,'Investment Banker ',NULL,NULL,'2019-10-26 22:43:08',0,2,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'ASDASD','. Investment banker as the name suggests is about investing the resources of any organization to a different organization that would help both the organizations to achieve a growth. ','ADASD,ASDSA,SADSA','SADSF,DSFDS,SDFDS,SDFSD','ADSAD,AFD,DSFSD,SDFDF','ADAS,DSAD,SADSA,ASDSA','ZCAS,ASDAS,ASDAS,ASDAS','ASDF,SDFSD,SDSD,DSFS','ZCSDFDS','WAFES','664565','ASFDSF','FDSF,SFDSD,DSFDS,DSFDS','23534','DSFSDF','ADSAD,ASDAD,ADA,ASDAS','ASDSAD',NULL,NULL,NULL,NULL,NULL,NULL,NULL),
 (12,'ABCDE',NULL,NULL,'2019-10-28 13:29:57',0,2,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'landing desc','how to get there','category1,category2','category1,category2','category1,category2','category1,category2','category1,category2','category1,category2','acategory1,actegory2','acategory1,actegory3','sal:90','scholorship1,scholorship2','college1,college2','sal:79','ascholorship1,asvholorship2','jobrole1,jobrole2','\"landing quote\"',NULL,NULL,NULL,NULL,NULL,NULL,NULL),
 (13,'dsfdsfd','investmentnbanker.jpg','/subCategoryImages/13','2019-10-28 16:17:03',0,2,0,'Am i the on edescription',NULL,NULL,NULL,NULL,'investmentnbanker.jpg','/landingImages/13','investmentnbanker.jpg','/amITheOneImages/13','sdfsdf','dfdsf','dsfds','sdfs','dsffffffffff','dssssss','dssssssssss','sddddddddd','dsssssss','sddddd','sdf','sdffffffff','dssssssssss','sdfds','sdfds','sdaaaaaaaa','\"sdffffffff\"',NULL,NULL,NULL,NULL,NULL,NULL,NULL),
 (14,'Photographer',NULL,NULL,'2019-12-26 19:24:59',0,1,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'entranceExam1,entranceExam2','how to get there','courseCat1,courseCat2','courseCat1,courseCat2','courseCat1,courseCat2','courseCat1,courseCat2','courseCat1,courseCat2','courseCat1,courseCat2','entranceExam1,entranceExam2','entranceExam1,entranceExam2','12000','SCHOLORSHIP1,scholorship1',NULL,'14000','ascholorship1,ascholorship2','jobRoles1,jobRoles2','\"entranceExam1,entranceExam2\"',NULL,NULL,NULL,NULL,NULL,NULL,NULL),
 (15,'dsfds',NULL,NULL,'2019-11-04 20:44:44',0,1,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'entrance1,entrance2','asdsad','cat1,cat2','cat3,cat4','cat5,cat6','cat7,cat8','cat9,cat10','entrance1,entrance2','entrance1,entrance2','entrance1,entranceentrance1,entrance22','1200',NULL,NULL,'1300',NULL,'entrance1,entrance2','\"entrance1,entrance2\"',NULL,NULL,NULL,NULL,NULL,NULL,NULL),
 (16,'dsfsdf','investmentnbanker.jpg','/subCategoryImages/16','2019-11-04 20:48:50',0,1,0,NULL,NULL,NULL,NULL,NULL,'investmentnbanker.jpg','/landingImages/16','investmentnbanker.jpg','/amITheOneImages/16','sdfsdfs','wqwer','adsa,dfh,dfd','adsa,dfh,dfd','adsa,dfh,dfd','adsa,dfh,dfd','adsa,dfh,dfd','adsa,dfh,dfd','asdas','asdasd','122',NULL,NULL,'123',NULL,'adsa,dfh,dfd','dsfsd',NULL,NULL,NULL,NULL,NULL,NULL,NULL),
 (17,'asdasd','investmentnbanker.jpg','/subCategoryImages/17','2019-11-05 12:45:05',0,2,0,NULL,NULL,NULL,NULL,NULL,'investmentnbanker.jpg','/landingImages/17','investmentnbanker.jpg','/amITheOneImages/17','asdasdsa','sadddddd','asdasd','sadas','asdsa','asd','sdsfd','sfffff','xzcdz','sds','1200',NULL,NULL,'1300',NULL,'asdas,asdas,asdasd','\"sada\"',NULL,NULL,NULL,NULL,NULL,NULL,NULL),
 (18,NULL,'investmentnbanker.jpg','/subCategoryImages/18','2019-12-25 11:18:53',0,2,NULL,NULL,NULL,NULL,NULL,NULL,'investmentnbanker.jpg','/landingImages/18',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'image/jpeg',NULL,'image/jpeg',NULL,NULL),
 (19,NULL,'Tulips.jpg','/subCategoryImages/19','2019-12-26 18:29:47',0,2,NULL,NULL,NULL,NULL,NULL,NULL,'Tulips.jpg','/landingImages/19',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'image/jpeg',NULL,'image/jpeg',NULL,NULL),
 (20,NULL,'Tulips.jpg','/subCategoryImages/20','2019-12-26 18:35:18',0,2,NULL,NULL,NULL,NULL,NULL,NULL,'','/landingImages/20',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'application/octet-stream',NULL,'image/jpeg',NULL,NULL),
 (21,NULL,'Tulips.jpg','/subCategoryImages/21','2019-12-26 18:40:53',0,2,NULL,NULL,NULL,NULL,NULL,NULL,'','/landingImages/21',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'application/octet-stream',NULL,'image/jpeg',NULL,NULL),
 (22,NULL,'Desert.jpg','/subCategoryImages/22','2019-12-26 18:56:44',0,2,NULL,NULL,NULL,NULL,NULL,NULL,'Hydrangeas.jpg','/landingImages/22',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'image/jpeg',NULL,'image/jpeg',NULL,NULL),
 (23,NULL,'Desert.jpg','/subCategoryImages/23','2019-12-26 18:59:03',0,2,NULL,NULL,NULL,NULL,NULL,NULL,'Desert.jpg','/landingImages/23',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'image/jpeg',NULL,'image/jpeg',NULL,NULL),
 (24,NULL,'Desert.jpg','/subCategoryImages/24','2019-12-26 19:00:40',0,2,NULL,NULL,NULL,NULL,NULL,NULL,'','/landingImages/24',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'application/octet-stream',NULL,'image/jpeg',NULL,NULL),
 (25,NULL,'Hydrangeas.jpg','/subCategoryImages/25','2019-12-26 19:07:40',0,2,NULL,NULL,NULL,NULL,NULL,NULL,'Desert.jpg','/landingImages/25',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'image/jpeg',NULL,'image/jpeg',NULL,NULL),
 (26,NULL,'Hydrangeas.jpg','/subCategoryImages/26','2019-12-26 19:10:55',0,2,NULL,NULL,NULL,NULL,NULL,NULL,'','/landingImages/26',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'application/octet-stream',NULL,'image/jpeg',NULL,NULL),
 (27,NULL,'Jellyfish.jpg','/subCategoryImages/27','2019-12-26 19:11:25',0,2,NULL,NULL,NULL,NULL,NULL,NULL,'Jellyfish.jpg','/landingImages/27',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'image/jpeg',NULL,'image/jpeg',NULL,NULL),
 (28,NULL,'Jellyfish.jpg','/subCategoryImages/28','2019-12-26 19:12:47',0,2,NULL,NULL,NULL,NULL,NULL,NULL,'','/landingImages/28',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'application/octet-stream',NULL,'image/jpeg',NULL,NULL),
 (29,'Software Developer','Jellyfish.jpg','/subCategoryImages/29','2019-12-26 19:16:34',0,2,0,'Do you like Rocket Ships?        \nDo you dream of travelling around the space?\nDo you love creating flying objects?\n        \n        This unique dream of yours can be achieved by becoming an Aerospace Engineer. This field is comprised of the very bright level of intellectuals who design some unrealistic things like building rocket ships and missiles. To fulfill this, guide your way to the proper way of education and make a good base foundation to turn into a professional. Work for various reputed companies and government in developing something extraordinary that helps the growth of humans and gain a feeling of accomplishment.\n       Start your career now, and become the Kailasavadivoo Sivan of your destiny.',NULL,NULL,NULL,NULL,'Hydrangeas.jpg','/landingImages/29',NULL,NULL,'The design and development of aircraft and spacecraft is called as Aerospace Engineering. Aerospace is a particular branch of engineering that primarily focuses on developing the design for aircraft, spacecraft, missiles, satellites, and other airborne objects. Their job is to develop new technologies for the ones mentioned above and develop new products. Also, an Aerospace Engineer must determine the financial possibility and safety issues with the considering environmental challenges before making an object. Their central role is to make sure that the purposes are meeting the right engineering principles by continually creating prototypes and testing. Aerospace Engineering is divided into two branches, Aeronautical Engineering that deals with object flown in the earth’s atmosphere, and Astronautical Engineering that deals with objects flown out open in the space.','An Aerospace Engineer focuses on aviation technology, defense systems, and space exploration. Their primary focus is manufacturing aircraft for commercial and military purposes. Hence, the base to become an Aerospace Engineer is already a set path, so one must complete his Bachelors in Aerospace Engineering. After graduation, the student will have the opportunity to study the field of his interest and pursue his specialization through the graduate course. As a beginner, an Aerospace Engineers monitor and support the current projects, eventually moving forward with the required experience. The engineers will get the opportunity to construct and design their projects. There are numerous universities and programs that offer students education for this course. During their education journey, it’s essential for students to spend more time in the workshops.','Attain 10+2 or equivalent qualification from a recognized educational Board in Mathematics/Physics/Chemistry.',NULL,'Diploma in Guidance and Counseling','B.Tech. (Aerospace Engineering)\n@@@B.E. (Aerospace Engineering)\n@@@B.Tech. Aeronautical Engineering\n@@@B.E. Aeronautical Engineering\n@@@B.E. (Aircraft Maintenance Engineering)\n@@@B.Sc. (Hons.) (Aerospace Engineering)\n@@@B.Sc. (Aeronautical Science)\n@@@B.Sc. (Aircraft Maintenance Science)','M.Tech Aerospace Engineering\n@@@M.Tech. Aeronautical Engineering\n@@@M.E. Aeronautical Engineering\n@@@M.E. (Aircraft Maintenance Engineering)\n@@@M.E. (Space Engineering & Rocketry)\n@@@M.Tech. (Aircraft Design)\n','All India Engineering/ Architecture Entrance Examination (AIEEE).\n@@@Delhi University Combined Entrance Examination (CEE).\n@@@Hindustan University Entrance Test (HITSEE).\n@@@IIST Admission Test (IIST, Thiruvananthapuram).\n@@@JEE (Joint Entrance Exam) conducted by the IIT’s.\n@@@SLIET Entrance Test (SET).\n@@@SRM Engineering Entrance Examination (SRM EEE) Admissions.\n@@@The Indian Institute of Aeronautics (IIA) Entrance Exam\n','Graduate Aptitude Test in Engineering (GATE).\n@@@Punjab Engineering college PhD Entrance exam.\n@@@Sathyabama University ME Entrance Exam\n','Punjab Engineering college PhD Entrance exam',NULL,NULL,NULL,NULL,NULL,NULL,'Aeronautics was neither an industry nor a science. It was a miracle.”-Igor Sikorsky','Diploma in Aerospace Engineering\n@@@Diploma in Aeronautical Engineering','AIMA Management Aptitude Test@@@Common Admission Test@@@Common Management Admission Test@@@ICFAI Business School Aptitude Test@@@Joint Entrance Management Aptitude Test@@@Joint Management Entrance Test\n','image/jpeg',NULL,'image/jpeg','The demand for Aerospace Engineers is growing at 2 % worldwide. India needs more aerospace engineers now than ever. Even though India has the lowest number in this profession, the countries entry into commercial satellites and the demand in defence related organizations is becoming more, the country needs around 6000 engineers in this category. The worldwide demand is quite strong, especially in the United Kingdom and the USA as the manufacturing sector in Aerospace is doing really well. The days have come where people are thinking of colonizing Mars and companies like SpaceX are going strong, this category has a stronger appeal than most of the other engineering branches.','The demand for Aerospace Engineers is growing at 2 % worldwide. India needs more aerospace engineers now than ever. Even though India has the lowest number in this profession, the countries entry into commercial satellites and the demand in defence related organizations is becoming more, the country needs around 6000 engineers in this category. The worldwide demand is quite strong, especially in the United Kingdom and the USA as the manufacturing sector in Aerospace is doing really well. The days have come where people are thinking of colonizing Mars and companies like SpaceX are going strong, this category has a stronger appeal than most of the other engineering branches.'),
 (30,'Software Developer','Jellyfish.jpg','/subCategoryImages/30','2019-12-26 19:17:18',0,2,0,'Do you like Rocket Ships?        \nDo you dream of travelling around the space?\nDo you love creating flying objects?\n        \n        This unique dream of yours can be achieved by becoming an Aerospace Engineer. This field is comprised of the very bright level of intellectuals who design some unrealistic things like building rocket ships and missiles. To fulfill this, guide your way to the proper way of education and make a good base foundation to turn into a professional. Work for various reputed companies and government in developing something extraordinary that helps the growth of humans and gain a feeling of accomplishment.\n       Start your career now, and become the Kailasavadivoo Sivan of your destiny.',NULL,NULL,NULL,NULL,'Jellyfish.jpg','/landingImages/30',NULL,NULL,'The design and development of aircraft and spacecraft is called as Aerospace Engineering. Aerospace is a particular branch of engineering that primarily focuses on developing the design for aircraft, spacecraft, missiles, satellites, and other airborne objects. Their job is to develop new technologies for the ones mentioned above and develop new products. Also, an Aerospace Engineer must determine the financial possibility and safety issues with the considering environmental challenges before making an object. Their central role is to make sure that the purposes are meeting the right engineering principles by continually creating prototypes and testing. Aerospace Engineering is divided into two branches, Aeronautical Engineering that deals with object flown in the earth’s atmosphere, and Astronautical Engineering that deals with objects flown out open in the space.','An Aerospace Engineer focuses on aviation technology, defense systems, and space exploration. Their primary focus is manufacturing aircraft for commercial and military purposes. Hence, the base to become an Aerospace Engineer is already a set path, so one must complete his Bachelors in Aerospace Engineering. After graduation, the student will have the opportunity to study the field of his interest and pursue his specialization through the graduate course. As a beginner, an Aerospace Engineers monitor and support the current projects, eventually moving forward with the required experience. The engineers will get the opportunity to construct and design their projects. There are numerous universities and programs that offer students education for this course. During their education journey, it’s essential for students to spend more time in the workshops.','Attain 10+2 or equivalent qualification from a recognized educational Board in Mathematics/Physics/Chemistry.',NULL,'Diploma in Guidance and Counseling','B.Tech. (Aerospace Engineering)\n@@@B.E. (Aerospace Engineering)\n@@@B.Tech. Aeronautical Engineering\n@@@B.E. Aeronautical Engineering\n@@@B.E. (Aircraft Maintenance Engineering)\n@@@B.Sc. (Hons.) (Aerospace Engineering)\n@@@B.Sc. (Aeronautical Science)\n@@@B.Sc. (Aircraft Maintenance Science)','M.Tech Aerospace Engineering\n@@@M.Tech. Aeronautical Engineering\n@@@M.E. Aeronautical Engineering\n@@@M.E. (Aircraft Maintenance Engineering)\n@@@M.E. (Space Engineering & Rocketry)\n@@@M.Tech. (Aircraft Design)\n','All India Engineering/ Architecture Entrance Examination (AIEEE).\n@@@Delhi University Combined Entrance Examination (CEE).\n@@@Hindustan University Entrance Test (HITSEE).\n@@@IIST Admission Test (IIST, Thiruvananthapuram).\n@@@JEE (Joint Entrance Exam) conducted by the IIT’s.\n@@@SLIET Entrance Test (SET).\n@@@SRM Engineering Entrance Examination (SRM EEE) Admissions.\n@@@The Indian Institute of Aeronautics (IIA) Entrance Exam\n','Graduate Aptitude Test in Engineering (GATE).\n@@@Punjab Engineering college PhD Entrance exam.\n@@@Sathyabama University ME Entrance Exam\n','Punjab Engineering college PhD Entrance exam',NULL,NULL,NULL,NULL,NULL,NULL,'Aeronautics was neither an industry nor a science. It was a miracle.”-Igor Sikorsky','Diploma in Aerospace Engineering\n@@@Diploma in Aeronautical Engineering','AIMA Management Aptitude Test@@@Common Admission Test@@@Common Management Admission Test@@@ICFAI Business School Aptitude Test@@@Joint Entrance Management Aptitude Test@@@Joint Management Entrance Test\n','image/jpeg',NULL,'image/jpeg','The demand for Aerospace Engineers is growing at 2 % worldwide. India needs more aerospace engineers now than ever. Even though India has the lowest number in this profession, the countries entry into commercial satellites and the demand in defence related organizations is becoming more, the country needs around 6000 engineers in this category. The worldwide demand is quite strong, especially in the United Kingdom and the USA as the manufacturing sector in Aerospace is doing really well. The days have come where people are thinking of colonizing Mars and companies like SpaceX are going strong, this category has a stronger appeal than most of the other engineering branches.','The demand for Aerospace Engineers is growing at 2 % worldwide. India needs more aerospace engineers now than ever. Even though India has the lowest number in this profession, the countries entry into commercial satellites and the demand in defence related organizations is becoming more, the country needs around 6000 engineers in this category. The worldwide demand is quite strong, especially in the United Kingdom and the USA as the manufacturing sector in Aerospace is doing really well. The days have come where people are thinking of colonizing Mars and companies like SpaceX are going strong, this category has a stronger appeal than most of the other engineering branches.'),
 (31,'Doctor','Hydrangeas.jpg','/subCategoryImages/31','2019-12-26 19:53:01',0,2,0,'Do you like Rocket Ships?        \nDo you dream of travelling around the space?\nDo you love creating flying objects?\n        \n        This unique dream of yours can be achieved by becoming an Aerospace Engineer. This field is comprised of the very bright level of intellectuals who design some unrealistic things like building rocket ships and missiles. To fulfill this, guide your way to the proper way of education and make a good base foundation to turn into a professional. Work for various reputed companies and government in developing something extraordinary that helps the growth of humans and gain a feeling of accomplishment.\n       Start your career now, and become the Kailasavadivoo Sivan of your destiny.',NULL,NULL,NULL,NULL,'Hydrangeas.jpg','/landingImages/31',NULL,NULL,'The design and development of aircraft and spacecraft is called as Aerospace Engineering. Aerospace is a particular branch of engineering that primarily focuses on developing the design for aircraft, spacecraft, missiles, satellites, and other airborne objects. Their job is to develop new technologies for the ones mentioned above and develop new products. Also, an Aerospace Engineer must determine the financial possibility and safety issues with the considering environmental challenges before making an object. Their central role is to make sure that the purposes are meeting the right engineering principles by continually creating prototypes and testing. Aerospace Engineering is divided into two branches, Aeronautical Engineering that deals with object flown in the earth’s atmosphere, and Astronautical Engineering that deals with objects flown out open in the space.','An Aerospace Engineer focuses on aviation technology, defense systems, and space exploration. Their primary focus is manufacturing aircraft for commercial and military purposes. Hence, the base to become an Aerospace Engineer is already a set path, so one must complete his Bachelors in Aerospace Engineering. After graduation, the student will have the opportunity to study the field of his interest and pursue his specialization through the graduate course. As a beginner, an Aerospace Engineers monitor and support the current projects, eventually moving forward with the required experience. The engineers will get the opportunity to construct and design their projects. There are numerous universities and programs that offer students education for this course. During their education journey, it’s essential for students to spend more time in the workshops.','Attain 10+2 or equivalent qualification from a recognized educational Board in Mathematics/Physics/Chemistry.',NULL,'Diploma in Guidance and Counseling','B.Tech. (Aerospace Engineering)\n@@@B.E. (Aerospace Engineering)\n@@@B.Tech. Aeronautical Engineering\n@@@B.E. Aeronautical Engineering\n@@@B.E. (Aircraft Maintenance Engineering)\n@@@B.Sc. (Hons.) (Aerospace Engineering)\n@@@B.Sc. (Aeronautical Science)\n@@@B.Sc. (Aircraft Maintenance Science)','M.Tech Aerospace Engineering\n@@@M.Tech. Aeronautical Engineering\n@@@M.E. Aeronautical Engineering\n@@@M.E. (Aircraft Maintenance Engineering)\n@@@M.E. (Space Engineering & Rocketry)\n@@@M.Tech. (Aircraft Design)\n','All India Engineering/ Architecture Entrance Examination (AIEEE).\n@@@Delhi University Combined Entrance Examination (CEE).\n@@@Hindustan University Entrance Test (HITSEE).\n@@@IIST Admission Test (IIST, Thiruvananthapuram).\n@@@JEE (Joint Entrance Exam) conducted by the IIT’s.\n@@@SLIET Entrance Test (SET).\n@@@SRM Engineering Entrance Examination (SRM EEE) Admissions.\n@@@The Indian Institute of Aeronautics (IIA) Entrance Exam\n','Graduate Aptitude Test in Engineering (GATE).\n@@@Punjab Engineering college PhD Entrance exam.\n@@@Sathyabama University ME Entrance Exam\n','Punjab Engineering college PhD Entrance exam',NULL,NULL,NULL,NULL,NULL,NULL,'Aeronautics was neither an industry nor a science. It was a miracle.”-Igor Sikorsky','Diploma in Aerospace Engineering\n@@@Diploma in Aeronautical Engineering','AIMA Management Aptitude Test@@@Common Admission Test@@@Common Management Admission Test@@@ICFAI Business School Aptitude Test@@@Joint Entrance Management Aptitude Test@@@Joint Management Entrance Test\n','image/jpeg',NULL,'image/jpeg','The demand for Aerospace Engineers is growing at 2 % worldwide. India needs more aerospace engineers now than ever. Even though India has the lowest number in this profession, the countries entry into commercial satellites and the demand in defence related organizations is becoming more, the country needs around 6000 engineers in this category. The worldwide demand is quite strong, especially in the United Kingdom and the USA as the manufacturing sector in Aerospace is doing really well. The days have come where people are thinking of colonizing Mars and companies like SpaceX are going strong, this category has a stronger appeal than most of the other engineering branches.','The demand for Aerospace Engineers is growing at 2 % worldwide. India needs more aerospace engineers now than ever. Even though India has the lowest number in this profession, the countries entry into commercial satellites and the demand in defence related organizations is becoming more, the country needs around 6000 engineers in this category. The worldwide demand is quite strong, especially in the United Kingdom and the USA as the manufacturing sector in Aerospace is doing really well. The days have come where people are thinking of colonizing Mars and companies like SpaceX are going strong, this category has a stronger appeal than most of the other engineering branches.');
/*!40000 ALTER TABLE `career_subcategory` ENABLE KEYS */;


--
-- Definition of table `co_creation_hub`
--

DROP TABLE IF EXISTS `co_creation_hub`;
CREATE TABLE `co_creation_hub` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `hub_name` varchar(45) NOT NULL,
  `address1` varchar(45) NOT NULL,
  `address2` varchar(45) NOT NULL,
  `city` varchar(45) NOT NULL,
  `state` varchar(45) NOT NULL,
  `pincode` varchar(45) NOT NULL,
  `date_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `status` int(11) NOT NULL,
  `longitude` varchar(45) NOT NULL,
  `latitude` varchar(45) NOT NULL,
  `hub_zone` varchar(1500) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `co_creation_hub`
--

/*!40000 ALTER TABLE `co_creation_hub` DISABLE KEYS */;
INSERT INTO `co_creation_hub` (`id`,`hub_name`,`address1`,`address2`,`city`,`state`,`pincode`,`date_created`,`status`,`longitude`,`latitude`,`hub_zone`) VALUES 
 (1,'asdsd','asdsad','asdas','asdsad','asdsd','sds','2019-09-24 12:15:06',0,'','','https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3806.3558748888127!2d78.43083441418842!3d17.442672905800944!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x3bcb91723f07d545%3A0xbeacc2e8cdc751f7!2sSECTORSEVEN%20E4E%20TECHNOLOGIES%20PVT%20LTD!5e0!3m2!1sen!2sin!4v1569044839088!5m2!1sen!2sin'),
 (2,'ABC','madhapur','sdfsdfds','Hyderabad','Telangana','500089','2019-10-10 10:57:13',0,'23','45',NULL);
/*!40000 ALTER TABLE `co_creation_hub` ENABLE KEYS */;


--
-- Definition of table `common_images`
--

DROP TABLE IF EXISTS `common_images`;
CREATE TABLE `common_images` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `img_name` varchar(45) DEFAULT NULL,
  `img_path` varchar(45) DEFAULT NULL,
  `screen` varchar(45) NOT NULL,
  `status` int(10) unsigned NOT NULL,
  `date_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `img_type` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `common_images`
--

/*!40000 ALTER TABLE `common_images` DISABLE KEYS */;
INSERT INTO `common_images` (`id`,`img_name`,`img_path`,`screen`,`status`,`date_created`,`img_type`) VALUES 
 (3,NULL,NULL,'asds',0,'2019-12-21 17:53:05',NULL),
 (4,NULL,NULL,'asds',0,'2019-12-21 17:53:28',NULL),
 (6,'user.png','/commonImages/user/6','user',0,'2019-12-23 09:56:48','image/png'),
 (7,'am-i-the-one-bg.png','/commonImages/amItheOne/7','amItheOne',0,'2019-12-26 13:59:55','image/png');
/*!40000 ALTER TABLE `common_images` ENABLE KEYS */;


--
-- Definition of table `contribution`
--

DROP TABLE IF EXISTS `contribution`;
CREATE TABLE `contribution` (
  `id` bigint(10) unsigned NOT NULL AUTO_INCREMENT,
  `message` varchar(45) NOT NULL,
  `date_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `status` int(10) NOT NULL,
  `authority` bigint(10) NOT NULL,
  `user` bigint(10) unsigned NOT NULL,
  `acceptance` int(10) NOT NULL,
  `subject` bigint(10) unsigned NOT NULL,
  `sub_subject` bigint(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_contribution_authority` (`authority`),
  KEY `FK_contribution_career_subcat` (`subject`),
  KEY `FK_subsuject_idx` (`sub_subject`),
  CONSTRAINT `FK_contribution_authority` FOREIGN KEY (`authority`) REFERENCES `authority` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_contribution_career_subcat` FOREIGN KEY (`subject`) REFERENCES `career_category` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_contribution_subcat` FOREIGN KEY (`sub_subject`) REFERENCES `contribution` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `contribution`
--

/*!40000 ALTER TABLE `contribution` DISABLE KEYS */;
INSERT INTO `contribution` (`id`,`message`,`date_created`,`status`,`authority`,`user`,`acceptance`,`subject`,`sub_subject`) VALUES 
 (1,'sdfsdf','2019-12-25 17:41:11',0,2,16,2,2,1),
 (2,'asdasd','2019-12-25 17:41:11',0,2,16,0,2,1);
/*!40000 ALTER TABLE `contribution` ENABLE KEYS */;


--
-- Definition of table `contribution_docs`
--

DROP TABLE IF EXISTS `contribution_docs`;
CREATE TABLE `contribution_docs` (
  `id` bigint(10) unsigned NOT NULL AUTO_INCREMENT,
  `cont_doc_name` varchar(45) DEFAULT NULL,
  `cont_doc_path` varchar(45) DEFAULT NULL,
  `date_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `status` int(10) unsigned NOT NULL,
  `authority` bigint(10) NOT NULL,
  `user` bigint(10) DEFAULT NULL,
  `type` varchar(45) DEFAULT NULL,
  `contribution` bigint(10) unsigned DEFAULT NULL,
  `description` varchar(45) DEFAULT NULL,
  `title` varchar(45) DEFAULT NULL,
  `video_type` int(10) unsigned DEFAULT NULL,
  `acceptance` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_contribution_docs_authority` (`authority`),
  KEY `FK_contribution_docs_contribution` (`contribution`),
  CONSTRAINT `FK_contribution_docs_authority` FOREIGN KEY (`authority`) REFERENCES `authority` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_contribution_docs_contribution` FOREIGN KEY (`contribution`) REFERENCES `contribution` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `contribution_docs`
--

/*!40000 ALTER TABLE `contribution_docs` DISABLE KEYS */;
INSERT INTO `contribution_docs` (`id`,`cont_doc_name`,`cont_doc_path`,`date_created`,`status`,`authority`,`user`,`type`,`contribution`,`description`,`title`,`video_type`,`acceptance`) VALUES 
 (1,'gytfyrc.PNG','/contributionDocs/1','2019-12-25 17:11:06',0,2,16,'image/png',1,'cadfdsf','sdgsdsdfds',0,0),
 (2,'sample.pdf','/contributionDocs/2','2019-12-25 17:11:06',0,2,16,'application/pdf',1,'sfgdrs','xzvdsfsdg',0,0),
 (3,'gytfyrc.PNG','/contributionDocs/3','2019-12-25 17:11:06',0,2,1,'image/png',1,NULL,NULL,0,0),
 (4,'17.mp4','/contributionDocs/4','2019-12-25 17:11:06',0,2,1,'video/mp4',1,NULL,NULL,0,0);
/*!40000 ALTER TABLE `contribution_docs` ENABLE KEYS */;


--
-- Definition of table `demand_colors`
--

DROP TABLE IF EXISTS `demand_colors`;
CREATE TABLE `demand_colors` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `status` int(10) unsigned NOT NULL,
  `date_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `demand_colors`
--

/*!40000 ALTER TABLE `demand_colors` DISABLE KEYS */;
INSERT INTO `demand_colors` (`id`,`name`,`status`,`date_created`) VALUES 
 (1,'#FB404B',0,'2019-12-26 19:41:21'),
 (2,'#1DC7EA',0,'2019-12-26 19:41:26'),
 (3,'#64b2cd',0,'2019-11-25 15:32:41');
/*!40000 ALTER TABLE `demand_colors` ENABLE KEYS */;


--
-- Definition of table `demand_labels`
--

DROP TABLE IF EXISTS `demand_labels`;
CREATE TABLE `demand_labels` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `label` varchar(45) NOT NULL,
  `status` int(10) unsigned NOT NULL,
  `date_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `demand_labels`
--

/*!40000 ALTER TABLE `demand_labels` DISABLE KEYS */;
INSERT INTO `demand_labels` (`id`,`label`,`status`,`date_created`) VALUES 
 (1,'IND',1,'2019-11-25 15:35:02'),
 (2,'USA',1,'2019-11-25 15:35:02'),
 (3,'UK',1,'2019-11-25 15:35:02'),
 (4,'SIN',1,'2019-12-26 19:46:22'),
 (5,'CAN',1,'2019-11-25 15:35:02');
/*!40000 ALTER TABLE `demand_labels` ENABLE KEYS */;


--
-- Definition of table `demand_supply`
--

DROP TABLE IF EXISTS `demand_supply`;
CREATE TABLE `demand_supply` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `country` int(10) unsigned NOT NULL DEFAULT '0',
  `year` bigint(20) unsigned NOT NULL,
  `salary` double NOT NULL,
  `manpower` double NOT NULL,
  `status` int(10) unsigned NOT NULL,
  `date_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `background_color` varchar(45) NOT NULL,
  `subcat_id` bigint(10) unsigned DEFAULT NULL,
  `color` bigint(10) unsigned NOT NULL,
  `label` bigint(10) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_demand_supply_subcat` (`subcat_id`),
  KEY `FK_demand_supply_color` (`color`),
  KEY `FK_demand_supply_year` (`year`),
  KEY `FK_demand_supply_label` (`label`),
  CONSTRAINT `FK_demand_supply_color` FOREIGN KEY (`color`) REFERENCES `demand_colors` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_demand_supply_label` FOREIGN KEY (`label`) REFERENCES `demand_labels` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_demand_supply_subcat` FOREIGN KEY (`subcat_id`) REFERENCES `career_subcategory` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_demand_supply_year` FOREIGN KEY (`year`) REFERENCES `demand_years` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `demand_supply`
--

/*!40000 ALTER TABLE `demand_supply` DISABLE KEYS */;
INSERT INTO `demand_supply` (`id`,`country`,`year`,`salary`,`manpower`,`status`,`date_created`,`background_color`,`subcat_id`,`color`,`label`) VALUES 
 (1,0,1,3,180,0,'2019-12-26 19:48:34','#FB404B',30,1,1),
 (2,0,1,36,135,0,'2019-12-26 19:48:34','#FB404B',30,1,2),
 (3,0,1,17,25,0,'2019-12-26 19:48:34','#FB404B',30,1,5),
 (4,0,1,24,130,0,'2019-12-26 19:48:34','#FB404B',30,1,3),
 (5,0,1,25,8,0,'2019-12-26 19:48:34','#FB404B',30,1,4),
 (6,0,2,4.3,260,0,'2019-12-26 19:48:34','#1DC7EA',30,2,1),
 (7,0,2,52,164,0,'2019-12-26 19:48:34','#1DC7EA',30,2,2),
 (8,0,2,45,28,0,'2019-12-26 19:48:34','#1DC7EA',30,2,5),
 (9,0,2,29,146,0,'2019-12-26 19:48:34','#1DC7EA',30,2,3),
 (10,0,2,30,9,0,'2019-12-26 19:48:34','#1DC7EA',30,2,4);
/*!40000 ALTER TABLE `demand_supply` ENABLE KEYS */;


--
-- Definition of table `demand_years`
--

DROP TABLE IF EXISTS `demand_years`;
CREATE TABLE `demand_years` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `year` varchar(45) NOT NULL,
  `status` int(10) unsigned NOT NULL,
  `date_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `demand_years`
--

/*!40000 ALTER TABLE `demand_years` DISABLE KEYS */;
INSERT INTO `demand_years` (`id`,`year`,`status`,`date_created`) VALUES 
 (1,'2020',0,'2019-12-04 11:22:38'),
 (2,'2030',0,'2019-12-26 19:47:26');
/*!40000 ALTER TABLE `demand_years` ENABLE KEYS */;


--
-- Definition of table `exam_pattern`
--

DROP TABLE IF EXISTS `exam_pattern`;
CREATE TABLE `exam_pattern` (
  `id` bigint(10) unsigned NOT NULL AUTO_INCREMENT,
  `pattern` varchar(150) NOT NULL,
  `date_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `status` int(10) unsigned NOT NULL,
  `subcat_id` bigint(10) unsigned NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `exam_pattern`
--

/*!40000 ALTER TABLE `exam_pattern` DISABLE KEYS */;
INSERT INTO `exam_pattern` (`id`,`pattern`,`date_created`,`status`,`subcat_id`) VALUES 
 (1,'zxcccccccccccccccc','2019-09-26 17:03:08',0,1),
 (2,'sccccsaddddddccccwd','2019-09-26 17:03:08',0,1),
 (3,'sdasdddddd','2019-09-26 17:03:08',0,1),
 (4,'sadsasa','2019-09-26 17:03:08',0,1),
 (5,'asdsad','2019-09-26 17:03:08',0,1),
 (6,'zxcccccccccc','2019-09-27 11:25:34',0,6),
 (7,'ZCCCCCCCCCaD','2019-09-27 11:25:34',0,6),
 (8,'ASDDDDDDDD','2019-09-27 11:25:34',0,6),
 (9,'ASD','2019-09-27 11:25:34',0,6),
 (10,'asfsaff','2019-10-01 17:26:08',0,7),
 (11,'safffffff','2019-10-01 17:26:09',0,7),
 (12,'pattern1','2019-10-10 16:14:41',0,8),
 (13,'patttern2','2019-10-10 16:14:41',0,8),
 (14,'dgdf','2019-10-25 17:57:17',0,9),
 (15,'kjhk','2019-10-25 17:57:17',0,9),
 (16,'hjj','2019-10-25 17:57:17',0,9),
 (17,'fdsfsd','2019-10-28 13:38:53',0,13),
 (18,'adsa','2019-11-04 20:48:50',0,16),
 (19,'dfh','2019-11-04 20:48:50',0,16),
 (20,'dfd','2019-11-04 20:48:50',0,16),
 (21,'asd','2019-11-05 12:45:06',0,17),
 (22,'asd','2019-11-05 12:45:06',0,17),
 (23,'asd','2019-11-05 12:45:06',0,17);
/*!40000 ALTER TABLE `exam_pattern` ENABLE KEYS */;


--
-- Definition of table `id_sequence`
--

DROP TABLE IF EXISTS `id_sequence`;
CREATE TABLE `id_sequence` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `format` varchar(255) DEFAULT NULL,
  `module` int(11) DEFAULT NULL,
  `school_code` varchar(255) DEFAULT NULL,
  `sequence_no` bigint(20) DEFAULT NULL,
  `academic_year` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_sv223p2fgniv3wjdd51rs36bx` (`academic_year`),
  CONSTRAINT `FK_sv223p2fgniv3wjdd51rs36bx` FOREIGN KEY (`academic_year`) REFERENCES `academic_year` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `id_sequence`
--

/*!40000 ALTER TABLE `id_sequence` DISABLE KEYS */;
INSERT INTO `id_sequence` (`id`,`format`,`module`,`school_code`,`sequence_no`,`academic_year`) VALUES 
 (1,NULL,0,'SC',30,1),
 (2,NULL,0,'ADR567',11,1),
 (3,NULL,0,NULL,1,NULL),
 (4,NULL,0,NULL,1,NULL),
 (5,NULL,3,NULL,15,NULL),
 (6,NULL,2,NULL,1,NULL);
/*!40000 ALTER TABLE `id_sequence` ENABLE KEYS */;


--
-- Definition of table `institute_subcat`
--

DROP TABLE IF EXISTS `institute_subcat`;
CREATE TABLE `institute_subcat` (
  `id` bigint(10) NOT NULL AUTO_INCREMENT,
  `subcat_id` bigint(10) unsigned DEFAULT NULL,
  `institution_id` bigint(10) unsigned DEFAULT NULL,
  `date_created` timestamp NULL DEFAULT NULL,
  `status` int(10) unsigned DEFAULT NULL,
  `country` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_for_subcatId_idx` (`subcat_id`),
  KEY `FK_for_institutionId_idx` (`institution_id`),
  CONSTRAINT `FK_for_institutionId` FOREIGN KEY (`institution_id`) REFERENCES `institutions` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_for_subcatId` FOREIGN KEY (`subcat_id`) REFERENCES `career_subcategory` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `institute_subcat`
--

/*!40000 ALTER TABLE `institute_subcat` DISABLE KEYS */;
INSERT INTO `institute_subcat` (`id`,`subcat_id`,`institution_id`,`date_created`,`status`,`country`) VALUES 
 (1,1,8,NULL,0,0),
 (2,1,8,NULL,0,0),
 (3,1,9,NULL,0,0),
 (4,1,9,NULL,0,0),
 (5,1,9,NULL,0,0),
 (6,1,9,NULL,0,0),
 (7,1,10,NULL,0,0),
 (8,1,11,NULL,0,0),
 (9,1,11,NULL,0,0),
 (10,1,12,NULL,0,0),
 (11,1,12,NULL,0,0),
 (12,1,13,NULL,0,0),
 (13,1,13,NULL,0,0),
 (14,1,14,NULL,0,0),
 (15,1,13,NULL,0,0),
 (16,1,15,NULL,0,0),
 (17,1,16,NULL,0,0),
 (18,1,17,NULL,0,0),
 (19,1,12,NULL,0,0),
 (20,1,12,NULL,0,0),
 (21,1,13,NULL,0,0),
 (22,1,13,NULL,0,0),
 (23,1,14,NULL,0,0),
 (24,1,13,NULL,0,0),
 (25,1,15,NULL,0,0),
 (26,1,16,NULL,0,0),
 (27,1,17,NULL,0,0),
 (28,30,12,NULL,0,1),
 (29,30,12,NULL,0,1),
 (30,30,13,NULL,0,1),
 (31,30,13,NULL,0,1),
 (32,30,14,NULL,0,1),
 (33,30,13,NULL,0,1),
 (34,30,15,NULL,0,1),
 (35,30,16,NULL,0,1),
 (36,30,17,NULL,0,1),
 (37,31,18,NULL,0,0),
 (38,31,19,NULL,0,0),
 (39,31,20,NULL,0,0),
 (40,31,21,NULL,0,0),
 (41,31,22,NULL,0,0),
 (42,31,23,NULL,0,0),
 (43,31,23,NULL,0,0),
 (44,31,23,NULL,0,0),
 (45,31,23,NULL,0,0);
/*!40000 ALTER TABLE `institute_subcat` ENABLE KEYS */;


--
-- Definition of table `institution_courses`
--

DROP TABLE IF EXISTS `institution_courses`;
CREATE TABLE `institution_courses` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `course_url` varchar(100) DEFAULT NULL,
  `course_name` varchar(100) DEFAULT NULL,
  `institution` bigint(10) unsigned NOT NULL,
  `date_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `status` int(10) unsigned NOT NULL,
  `subcat_id` bigint(10) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_course_institute_idx` (`institution`),
  KEY `FK_institution_courses_subcat` (`subcat_id`),
  CONSTRAINT `fk_course_in_institution` FOREIGN KEY (`institution`) REFERENCES `institutions` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_institution_courses_subcat` FOREIGN KEY (`subcat_id`) REFERENCES `career_subcategory` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `institution_courses`
--

/*!40000 ALTER TABLE `institution_courses` DISABLE KEYS */;
/*!40000 ALTER TABLE `institution_courses` ENABLE KEYS */;


--
-- Definition of table `institutions`
--

DROP TABLE IF EXISTS `institutions`;
CREATE TABLE `institutions` (
  `id` bigint(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(500) NOT NULL,
  `img_name` varchar(45) DEFAULT NULL,
  `img_path` varchar(45) DEFAULT NULL,
  `date_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `status` int(10) unsigned NOT NULL,
  `subcat_id` bigint(10) unsigned zerofill NOT NULL,
  `institute_url` varchar(200) DEFAULT NULL,
  `country` int(10) unsigned NOT NULL,
  `address` varchar(105) NOT NULL DEFAULT '',
  `type` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_institutions_sucat` (`subcat_id`),
  CONSTRAINT `FK_institutions_sucat` FOREIGN KEY (`subcat_id`) REFERENCES `career_subcategory` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `institutions`
--

/*!40000 ALTER TABLE `institutions` DISABLE KEYS */;
INSERT INTO `institutions` (`id`,`name`,`img_name`,`img_path`,`date_created`,`status`,`subcat_id`,`institute_url`,`country`,`address`,`type`) VALUES 
 (2,'dfdsfds',NULL,NULL,'2019-10-28 17:16:48',0,0000000013,NULL,0,'',NULL),
 (3,'SAFSDFF','3.png','/subCategorySide/3','2019-10-28 17:16:48',0,0000000013,NULL,0,'',NULL),
 (4,'ABCDE','investmentnbanker.jpg','/subCategorySide/4','2019-10-28 17:16:48',0,0000000013,'abcdurl.com',0,'',NULL),
 (5,'AVCFRT','investmentnbanker.jpg','/subCategorySide/5','2019-10-29 12:19:14',0,0000000013,'institure.com',1,'',NULL),
 (6,'GDFG','investmentnbanker.jpg','/subCategorySide/6','2019-10-29 12:19:14',0,0000000013,'',1,'',NULL),
 (7,'asdsad','investmentnbanker.jpg','/subCategorySide/7','2019-10-29 12:29:34',0,0000000013,'',0,'',NULL),
 (8,'School of Business, University of Petroleum and Energy Studies, Dehradun',NULL,NULL,'2019-12-26 18:17:55',0,0000000001,'https://www.upes.ac.in/',0,'',NULL),
 (9,'KJ Somaiya Institute of Management Studies and Research, Mumbai',NULL,NULL,'2019-12-26 18:17:55',0,0000000001,'https://simsr.somaiya.edu/en',0,'',NULL),
 (10,'Maharaja Sayajirao University of Baroda, Vadodara',NULL,NULL,'2019-12-26 18:17:55',0,0000000001,'https://www.msubaroda.ac.in/',0,'',NULL),
 (11,'Bharati Vidyapeeth Deemed University Institute of Management and Research, New Delhi',NULL,NULL,'2019-12-26 18:17:55',0,0000000001,'http://www.bvimr.com/',0,'',NULL),
 (12,'School of Business, University of Petroleum and Energy Studies, Dehradun',NULL,NULL,'2019-12-26 18:22:24',0,0000000001,'https://www.upaes.ac.in/',0,'',NULL),
 (13,'KJ Somaiya Institute of Management Studies and Research, Mumbai',NULL,NULL,'2019-12-26 18:22:24',0,0000000001,'https://simsr.somaaiya.edu/en',0,'',NULL),
 (14,'KJ Somaiya Institute of Management Studies and Research, Mumbai',NULL,NULL,'2019-12-26 18:22:24',0,0000000001,'https://simsr.saomaiya.edu/en',0,'',NULL),
 (15,'Maharaja Sayajirao University of Baroda, Vadodara',NULL,NULL,'2019-12-26 18:22:24',0,0000000001,'https://www.msaubaroda.ac.in/',0,'',NULL),
 (16,'Bharati Vidyapeeth Deemed University Institute of Management and Research, New Delhi',NULL,NULL,'2019-12-26 18:22:24',0,0000000001,'http://www.bviamr.com/',0,'',NULL),
 (17,'Bharati Vidyapeeth Deemed University Institute of Management and Research, New Delhi',NULL,NULL,'2019-12-26 18:22:24',0,0000000001,'http://www.bvaimr.com/',0,'',NULL),
 (18,'School of Business, University of Petroleum and Energy Studies, Dehradun',NULL,NULL,'2019-12-26 19:56:50',0,0000000031,'mycolo.com',0,'',NULL),
 (19,'School of Business, University of Petroleum and Energy Studies, Dehradun',NULL,NULL,'2019-12-26 19:56:50',0,0000000031,'am.com',0,'',NULL),
 (20,'KJ Somaiya Institute of Management Studies and Research, Mumbai',NULL,NULL,'2019-12-26 19:56:50',0,0000000031,'sigma.com',0,'',NULL),
 (21,'KJ Somaiya Institute of Management Studies and Research, Mumbai',NULL,NULL,'2019-12-26 19:56:51',0,0000000031,'john.com',0,'',NULL),
 (22,'KJ Somaiya Institute of Management Studies and Research, Mumbai',NULL,NULL,'2019-12-26 19:56:51',0,0000000031,'kalya.com',0,'',NULL),
 (23,'KJ Somaiya Institute of Management Studies and Research, Mumbai',NULL,NULL,'2019-12-26 19:56:51',0,0000000031,'srinag.com',0,'',NULL);
/*!40000 ALTER TABLE `institutions` ENABLE KEYS */;


--
-- Definition of table `interests`
--

DROP TABLE IF EXISTS `interests`;
CREATE TABLE `interests` (
  `id` bigint(10) unsigned NOT NULL AUTO_INCREMENT,
  `interest` varchar(45) NOT NULL,
  `status` int(10) unsigned NOT NULL,
  `date_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `intrst_imag_name` varchar(45) DEFAULT NULL,
  `intrst_imag_path` varchar(45) DEFAULT NULL,
  `type` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `interests`
--

/*!40000 ALTER TABLE `interests` DISABLE KEYS */;
INSERT INTO `interests` (`id`,`interest`,`status`,`date_created`,`intrst_imag_name`,`intrst_imag_path`,`type`) VALUES 
 (1,'sdsdf',0,'2019-11-06 12:40:57','','',NULL),
 (2,'Blockchain Developer',0,'2019-11-06 12:40:57','','',NULL),
 (3,'walking',0,'2019-09-16 17:29:29','','',NULL),
 (4,'plays',0,'2019-10-15 10:41:40','Jellyfish.jpg','/interestsImages/4',NULL),
 (5,'dsfsddf',0,'2019-10-15 10:41:40',NULL,NULL,NULL);
/*!40000 ALTER TABLE `interests` ENABLE KEYS */;


--
-- Definition of table `mentor`
--

DROP TABLE IF EXISTS `mentor`;
CREATE TABLE `mentor` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `address` varchar(255) DEFAULT NULL,
  `address1` varchar(255) DEFAULT NULL,
  `authority` varchar(255) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `date_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `date_of_birth` varchar(150) DEFAULT NULL,
  `decrypt_password` varchar(255) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `expertise` varchar(255) DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `gender` int(11) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `mentor_talks` int(11) DEFAULT NULL,
  `mobile` bigint(20) NOT NULL,
  `occupation` varchar(255) DEFAULT NULL,
  `password` varchar(255) NOT NULL,
  `pin_code` varchar(255) DEFAULT NULL,
  `reason` varchar(255) DEFAULT NULL,
  `salutation` int(11) DEFAULT NULL,
  `solution_to_students` int(11) unsigned DEFAULT NULL,
  `state` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `user_name` varchar(255) NOT NULL,
  `education` varchar(45) DEFAULT NULL,
  `description` varchar(500) DEFAULT NULL,
  `twitter_url` varchar(45) DEFAULT NULL,
  `linked_url` varchar(45) DEFAULT NULL,
  `instagram_url` varchar(45) DEFAULT NULL,
  `img_name` varchar(45) DEFAULT NULL,
  `img_path` varchar(45) DEFAULT NULL,
  `diff_mentor_sect_seven` varchar(45) DEFAULT NULL,
  `contribution_time` varchar(45) DEFAULT NULL,
  `child_activities` int(11) DEFAULT NULL,
  `type` varchar(45) DEFAULT NULL,
  `occupation_category` bigint(20) unsigned DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_mentor_authority` (`authority`),
  KEY `FK_mentor_careercat` (`occupation_category`),
  CONSTRAINT `FK_mentor_careercat` FOREIGN KEY (`occupation_category`) REFERENCES `career_category` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `mentor`
--

/*!40000 ALTER TABLE `mentor` DISABLE KEYS */;
INSERT INTO `mentor` (`id`,`address`,`address1`,`authority`,`city`,`date_created`,`date_of_birth`,`decrypt_password`,`email`,`expertise`,`first_name`,`gender`,`last_name`,`mentor_talks`,`mobile`,`occupation`,`password`,`pin_code`,`reason`,`salutation`,`solution_to_students`,`state`,`status`,`user_name`,`education`,`description`,`twitter_url`,`linked_url`,`instagram_url`,`img_name`,`img_path`,`diff_mentor_sect_seven`,`contribution_time`,`child_activities`,`type`,`occupation_category`) VALUES 
 (1,'ayyappasociety','madhapur','ROLE_MENTOR','hyderabad','2019-10-23 11:48:03','2016-02-02 00:00:00','agLNUbZD','harith785@gmail.com','UI','ramu',1,'h',0,9876543210,'walking','$2a$10$p0mlwwBSAdL0.liNClL6fetMr0l9ApbOXrchhpkK/6aPjeILvhfYe','500089',NULL,2,0,'Telangana',0,'haritha@gmail.com','','Dr Parakala Prabhakar is currently handling affairs of RightFOLIO, a Knowledge Service Enterprise as its Managing Director.',NULL,NULL,NULL,'','',NULL,NULL,NULL,NULL,NULL),
 (2,'madhapur','sdfsdfds','ROLE_MENTOR','hyderabad','2019-10-23 11:48:03',NULL,'I5gJMmjv','BCDE@gmail.com','','BCDE',1,'BCDE',0,9090909089,'playing','$2a$10$L3GaBQQ/mJErpkt8k1qhOOMX5N4OZ1hlyHc22oyX8QUAuW7wvZkIS','500089',NULL,2,0,'Telangana',0,'hari@gmail.com',NULL,'zcxxxxxxxxxxxdsgfdg','','','',NULL,NULL,NULL,NULL,NULL,NULL,NULL),
 (3,'madhapur','hyderabad','ROLE_MENTOR','hyderabad','2019-10-23 11:48:03','2020-04-10 00:00:00','&!qjJ^Y-','haritha2065@gmail.com','Developer','harita',1,'palle',NULL,8908907890,'UIDeveloper','$2a$10$sf5HQu.WIz63qDbz/AkDqe0McSFNzxBaN7nBmRGjyiKjGnFKX.zX6','500090',NULL,2,NULL,'Telangana',0,'000001',NULL,'description','twitter.com','linkedin.com','instagram.com',NULL,NULL,NULL,NULL,NULL,NULL,NULL),
 (4,'madhapur','srnagar','ROLE_MENTOR','hyderabad','2019-10-23 11:48:03','2018-07-05 00:00:00','+pbLnEeO','raju@gmail.com','Java','raju',1,'ram',0,7893433432,'Software Developer','$2a$10$6sxmRel5iZEiPYQThQ7sh.1sBTJyv53Z.T0qTBSIQJ7wxOo/HA/Km','500009',NULL,2,0,'Telangana',0,'va000001',NULL,'description','twitter.com','linkedin.com','instagram.com',NULL,NULL,NULL,NULL,NULL,NULL,NULL),
 (5,'zsds','dsf','ROLE_MENTOR','hyderabad','2019-10-23 11:48:03','2019-08-10 00:00:00','H48cp2Uc','rajesh@gmail.com','Devlopment','rajesh',1,'p',0,9089787888,'Designer','$2a$10$ikDotK42vhWMT4j4WvkT7u4baBb6eVFLUt9kbV9MAYwg/59PdX40O','500089',NULL,3,0,'Telangana',0,'vlopment000001',NULL,'zxcdfdsfds','twitter.com','linked.com','instagram.com',NULL,NULL,NULL,NULL,NULL,NULL,NULL),
 (6,'adssd','asfds','ROLE_MENTOR','dsfsdf','2019-10-23 11:48:03','2019-07-10 00:00:00','IznJm83e','zxc@gmail.com','asdasd','Z',0,'asfsdd',NULL,8909994354,'asdsa','$2a$10$g5LwhQ4Ais/wIX441VO5MudZ2oVJjzTQW5RSfgId7WOPS3zr/sKUS','500903',NULL,2,0,'dgfdsfg',0,'dasd0002',NULL,'asdasdasd','Czxc.com','sfdsf.com','zsdsad.comas',NULL,NULL,NULL,NULL,NULL,NULL,NULL),
 (7,'Madhapur, Srnagar','Madhapur','ROLE_MENTOR','Hyderabad','2019-10-23 12:07:58',NULL,'BiFsoj4T','vasu@gmail.com','sdf','asdsd',NULL,'asdasd',0,8887773456,'sdfsd','$2a$10$HcCdcGtsE6lHMn93n715HuF3jX4us3U/T17y58pk4KbNf7lYgdKAa','500038',NULL,1,0,'AP',0,'f0004','Btech','sdfffffffff','sdfsdf','sdfsd','sdfsd',NULL,NULL,NULL,NULL,NULL,NULL,NULL),
 (8,'Madhapur, Srnagar','Madhapur','ROLE_MENTOR','Hyderabad','2019-10-23 12:23:36','2019-10-09','@D774P$Z','vasuSdsdf@gmail.com','dsfds','ranii',0,'m',1,7987987987,'sdfsdf','$2a$10$M0gN/C38B0tHCIQb.F38wOExLSBKtWPl7XD5DX8PkAoS4jf1nPpa.','500038',NULL,0,0,'AP',0,'fds0005','sdf','sfsd','sdf','sdf','dsf',NULL,NULL,NULL,NULL,NULL,NULL,NULL),
 (9,'Madhapur','Madhapur','ROLE_MENTOR','Hyderabad','2019-11-02 09:52:24','2019-10-02','q8eFVa1Q','vasudfds@gmail.com','sdfdsf','asdsd',1,'asdasd',1,9899899956,'walking','$2a$10$nMaMxihB.GMxytEPjcUrr.DPyEvx/zt3KAN2jrCmaUyv7q/EB7/sy','500038','xcxzc',1,0,'AP',0,'fdsf0006','dsfdsf','sdfsdf','assaf','sfdsf','sdfsdf','investmentnbanker.jpg','/mentorImages/9','sdfsdfds','phone',0,NULL,NULL),
 (10,'addline1','addline2','ROLE_MENTOR','Hyderabad','2019-12-26 18:00:03','22-02-2002','6-O0vwT1','balaji.siddi@gmail.com','Engineering','Kaveri',0,'Kavuri',0,9874545471,'Engineering','$2a$10$ldV3kb06ldApRyrNBZATr.W/1szWUanU8uavuMOasIuxsLSp3f9Vm','523155','This is my reason is...',2,0,'Telangana',0,'gineering0015','B.Tech','This is description','twitter.com','linkedlin.com','instagram.com',NULL,NULL,'ChangeEverything','2 to 4 Hours Daily',0,NULL,2);
/*!40000 ALTER TABLE `mentor` ENABLE KEYS */;


--
-- Definition of table `mentor_followers`
--

DROP TABLE IF EXISTS `mentor_followers`;
CREATE TABLE `mentor_followers` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `mentor` bigint(10) DEFAULT NULL,
  `student` bigint(20) DEFAULT NULL,
  `status` int(10) NOT NULL,
  `date_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `acceptance` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_mentor_REF` (`mentor`),
  KEY `FK_STUDENT_REF` (`student`),
  CONSTRAINT `FK_mentor_REF` FOREIGN KEY (`mentor`) REFERENCES `mentor` (`id`),
  CONSTRAINT `FK_STUDENT_REF` FOREIGN KEY (`student`) REFERENCES `student` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `mentor_followers`
--

/*!40000 ALTER TABLE `mentor_followers` DISABLE KEYS */;
INSERT INTO `mentor_followers` (`id`,`mentor`,`student`,`status`,`date_created`,`acceptance`) VALUES 
 (1,1,4,1,'2019-09-16 12:27:14',0),
 (2,1,4,1,'2019-09-16 12:35:10',0),
 (4,1,4,1,'2019-09-23 10:56:28',0),
 (5,1,4,1,'2019-09-23 10:57:58',0),
 (6,1,4,1,'2019-09-27 14:47:29',0),
 (12,3,16,0,'2019-12-20 14:13:21',1);
/*!40000 ALTER TABLE `mentor_followers` ENABLE KEYS */;


--
-- Definition of table `notification`
--

DROP TABLE IF EXISTS `notification`;
CREATE TABLE `notification` (
  `id` bigint(10) unsigned NOT NULL AUTO_INCREMENT,
  `message` varchar(45) NOT NULL,
  `media_id` bigint(10) unsigned DEFAULT NULL,
  `screen` varchar(45) NOT NULL,
  `date_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `status` int(10) unsigned NOT NULL,
  `video_type` int(10) unsigned DEFAULT NULL,
  `action` varchar(45) NOT NULL,
  `sub_cat` bigint(10) unsigned DEFAULT NULL,
  `student` bigint(10) unsigned DEFAULT NULL,
  `mentor` bigint(10) unsigned DEFAULT NULL,
  `acceptance` int(10) unsigned DEFAULT NULL,
  `youtube_url` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_notification_subcat` (`sub_cat`),
  CONSTRAINT `FK_notification_subcat` FOREIGN KEY (`sub_cat`) REFERENCES `career_subcategory` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=95 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `notification`
--

/*!40000 ALTER TABLE `notification` DISABLE KEYS */;
INSERT INTO `notification` (`id`,`message`,`media_id`,`screen`,`date_created`,`status`,`video_type`,`action`,`sub_cat`,`student`,`mentor`,`acceptance`,`youtube_url`) VALUES 
 (1,'New SubCategory Added',NULL,'SubCategory','2019-11-04 20:48:50',0,NULL,'SubCategory Added',16,NULL,NULL,NULL,''),
 (4,'New Video Added to website',25,'subCategory','2019-11-04 21:42:10',0,1,'subCategory',NULL,NULL,NULL,NULL,''),
 (5,'New Video Added to website',26,'subCategory','2019-11-04 21:44:08',0,1,'subCategory',NULL,NULL,NULL,NULL,''),
 (6,'New Video Added to website',27,'subCategory','2019-11-04 21:44:58',0,1,'subCategory',NULL,NULL,NULL,NULL,''),
 (7,'New Video Added to website',28,'subCategory','2019-11-04 21:47:44',0,1,'subCategory',NULL,NULL,NULL,NULL,''),
 (8,'New Video Added to website',29,'subCategory','2019-11-04 21:49:56',0,1,'subCategory',NULL,NULL,NULL,NULL,''),
 (9,'New Video Added to website',30,'subCategory','2019-11-04 21:50:51',0,1,'subCategory',NULL,NULL,NULL,NULL,''),
 (10,'New PDF Added to website',32,'subCategory','2019-11-04 22:05:49',0,0,'subCategory',NULL,NULL,NULL,NULL,''),
 (11,'New PDF Added to website',33,'subCategory','2019-11-04 22:07:01',0,0,'subCategory',NULL,NULL,NULL,NULL,''),
 (12,'New Video Added to website',34,'subCategory','2019-11-04 22:07:51',0,0,'subCategory',NULL,NULL,NULL,NULL,''),
 (13,'New Audio Added to website',35,'subCategory','2019-11-04 22:11:41',0,0,'subCategory',NULL,NULL,NULL,NULL,''),
 (14,'New Video Added to website',36,'subCategory','2019-11-04 22:13:06',0,0,'subCategory',NULL,NULL,NULL,NULL,''),
 (15,'New PDF Added to website',37,'subCategory','2019-11-04 22:14:10',0,0,'subCategory',NULL,NULL,NULL,NULL,''),
 (16,'New Video Added to website',38,'subCategory','2019-11-04 22:14:46',0,1,'subCategory',NULL,NULL,NULL,NULL,''),
 (17,'New Video Added in website',28,'sigma','2019-11-04 22:27:52',0,NULL,'sigma',NULL,NULL,NULL,NULL,''),
 (18,'New Video Added in website',29,'sigma','2019-11-04 22:32:54',0,NULL,'sigma',NULL,NULL,NULL,NULL,''),
 (19,'New PDF Added in website',30,'sigma','2019-11-04 22:33:51',0,NULL,'sigma',NULL,NULL,NULL,NULL,''),
 (20,'New Audio Added in website',31,'sigma','2019-11-04 22:34:45',0,NULL,'sigma',NULL,NULL,NULL,NULL,''),
 (21,'New SubCategory Added',NULL,'SubCategory','2019-11-05 12:45:05',0,NULL,'SubCategory Added',17,NULL,NULL,NULL,''),
 (22,'New Video Added to website',39,'subCategory','2019-11-05 12:46:36',0,0,'subCategory',NULL,NULL,NULL,NULL,''),
 (23,'New Audio Added to website',40,'subCategory','2019-11-05 12:47:04',0,0,'subCategory',NULL,NULL,NULL,NULL,''),
 (24,'New PDF Added to website',41,'subCategory','2019-11-05 12:47:29',0,0,'subCategory',NULL,NULL,NULL,NULL,''),
 (29,'New Video Added to website',42,'subCategory','2019-11-09 11:16:19',0,1,'Video',NULL,NULL,NULL,NULL,''),
 (30,'New Video Added to website',43,'subCategory','2019-11-09 11:18:14',0,1,'Video',NULL,NULL,NULL,NULL,''),
 (31,'New Video Added to website',44,'subCategory','2019-11-09 11:19:37',0,1,'Video',NULL,NULL,NULL,NULL,''),
 (32,'New Video Has been Added',45,'subCategory','2019-12-04 12:03:37',0,1,'Video',NULL,NULL,NULL,NULL,NULL),
 (33,'New Video Has been Added',46,'subCategory','2019-12-04 12:05:06',0,1,'Video',NULL,NULL,NULL,NULL,NULL),
 (34,'New Video Has been Added',47,'subCategory','2019-12-04 12:10:14',0,1,'Video',NULL,NULL,NULL,NULL,NULL),
 (35,'Successfully Accepted ',NULL,'acceptRej','2019-12-20 14:13:21',0,NULL,'Accepted',NULL,16,3,1,NULL),
 (36,'New Trending Career Added',NULL,'subCategory','2019-12-26 19:17:18',0,NULL,'careerMain',30,NULL,NULL,NULL,NULL),
 (37,'New Video Has been Added',NULL,'sigma','2019-12-26 19:21:04',0,NULL,'Video',NULL,NULL,NULL,NULL,'32fLCPcsffs'),
 (38,'New Video Has been Added',NULL,'sigma','2019-12-26 19:21:04',0,NULL,'Video',NULL,NULL,NULL,NULL,'MbMh6KRLz9U'),
 (39,'New Video Has been Added',NULL,'sigma','2019-12-26 19:21:04',0,NULL,'Video',NULL,NULL,NULL,NULL,'MQtVmxQbvXo'),
 (40,'New Video Has been Added',NULL,'sigma','2019-12-26 19:21:04',0,NULL,'Video',NULL,NULL,NULL,NULL,'Z1fGHLKA2zk'),
 (41,'New Video Has been Added',NULL,'sigma','2019-12-26 19:21:04',0,NULL,'Video',NULL,NULL,NULL,NULL,'if75PRKQHWQ'),
 (42,'New Video Has been Added',NULL,'sigma','2019-12-26 19:21:04',0,NULL,'Video',NULL,NULL,NULL,NULL,'qAKODfJZV9M'),
 (43,'New Video Has been Added',NULL,'sigma','2019-12-26 19:21:04',0,NULL,'Video',NULL,NULL,NULL,NULL,'WdvC9xUfuyc'),
 (44,'New Video Has been Added',NULL,'sigma','2019-12-26 19:21:04',0,NULL,'Video',NULL,NULL,NULL,NULL,'ZX6dz61s-po'),
 (45,'New Video Has been Added',NULL,'sigma','2019-12-26 19:21:04',0,NULL,'Video',NULL,NULL,NULL,NULL,'CfzeEz826VM'),
 (46,'New Video Has been Added',NULL,'sigma','2019-12-26 19:21:04',0,NULL,'Video',NULL,NULL,NULL,NULL,'_s42gOg2WSU'),
 (47,'New Video Has been Added',NULL,'sigma','2019-12-26 19:21:04',0,NULL,'Video',NULL,NULL,NULL,NULL,'3dZRmgLq8bU'),
 (48,'New Video Has been Added',NULL,'sigma','2019-12-26 19:21:04',0,NULL,'Video',NULL,NULL,NULL,NULL,'kLFItf290Fc'),
 (49,'New Video Has been Added',NULL,'sigma','2019-12-26 19:21:04',0,NULL,'Video',NULL,NULL,NULL,NULL,'fdTX1nUapTY'),
 (50,'New Video Has been Added',NULL,'sigma','2019-12-26 19:21:04',0,NULL,'Video',NULL,NULL,NULL,NULL,'QCQyB0FBJjY'),
 (51,'New Video Has been Added',NULL,'sigma','2019-12-26 19:21:04',0,NULL,'Video',NULL,NULL,NULL,NULL,'tPYlvMERJmY'),
 (52,'New Video Has been Added',NULL,'sigma','2019-12-26 19:21:04',0,NULL,'Video',NULL,NULL,NULL,NULL,'14ffsxVCRxs'),
 (53,'New Video Has been Added',NULL,'sigma','2019-12-26 19:21:04',0,NULL,'Video',NULL,NULL,NULL,NULL,'8yvK31KNvAc'),
 (54,'New Video Has been Added',NULL,'sigma','2019-12-26 19:21:04',0,NULL,'Video',NULL,NULL,NULL,NULL,'7wYva9dhelE'),
 (55,'New Video Has been Added',NULL,'sigma','2019-12-26 19:21:04',0,NULL,'Video',NULL,NULL,NULL,NULL,'R8aDgJlHB3Q'),
 (56,'New Video Has been Added',NULL,'subCategory','2019-12-26 19:22:49',0,NULL,'Video',30,NULL,NULL,NULL,'32fLCPcsffs'),
 (57,'New Video Has been Added',NULL,'subCategory','2019-12-26 19:22:49',0,NULL,'Video',30,NULL,NULL,NULL,'MbMh6KRLz9U'),
 (58,'New Video Has been Added',NULL,'subCategory','2019-12-26 19:22:49',0,NULL,'Video',30,NULL,NULL,NULL,'MQtVmxQbvXo'),
 (59,'New Video Has been Added',NULL,'subCategory','2019-12-26 19:22:49',0,NULL,'Video',30,NULL,NULL,NULL,'Z1fGHLKA2zk'),
 (60,'New Video Has been Added',NULL,'subCategory','2019-12-26 19:22:49',0,NULL,'Video',30,NULL,NULL,NULL,'if75PRKQHWQ'),
 (61,'New Video Has been Added',NULL,'subCategory','2019-12-26 19:22:49',0,NULL,'Video',30,NULL,NULL,NULL,'qAKODfJZV9M'),
 (62,'New Video Has been Added',NULL,'subCategory','2019-12-26 19:22:49',0,NULL,'Video',30,NULL,NULL,NULL,'WdvC9xUfuyc'),
 (63,'New Video Has been Added',NULL,'subCategory','2019-12-26 19:22:49',0,NULL,'Video',30,NULL,NULL,NULL,'ZX6dz61s-po'),
 (64,'New Video Has been Added',NULL,'subCategory','2019-12-26 19:22:49',0,NULL,'Video',30,NULL,NULL,NULL,'CfzeEz826VM'),
 (65,'New Video Has been Added',NULL,'subCategory','2019-12-26 19:22:49',0,NULL,'Video',30,NULL,NULL,NULL,'_s42gOg2WSU'),
 (66,'New Video Has been Added',NULL,'subCategory','2019-12-26 19:22:49',0,NULL,'Video',30,NULL,NULL,NULL,'3dZRmgLq8bU'),
 (67,'New Video Has been Added',NULL,'subCategory','2019-12-26 19:22:49',0,NULL,'Video',30,NULL,NULL,NULL,'kLFItf290Fc'),
 (68,'New Video Has been Added',NULL,'subCategory','2019-12-26 19:22:49',0,NULL,'Video',30,NULL,NULL,NULL,'fdTX1nUapTY'),
 (69,'New Video Has been Added',NULL,'subCategory','2019-12-26 19:22:49',0,NULL,'Video',30,NULL,NULL,NULL,'QCQyB0FBJjY'),
 (70,'New Video Has been Added',NULL,'subCategory','2019-12-26 19:22:49',0,NULL,'Video',30,NULL,NULL,NULL,'tPYlvMERJmY'),
 (71,'New Video Has been Added',NULL,'subCategory','2019-12-26 19:22:49',0,NULL,'Video',30,NULL,NULL,NULL,'14ffsxVCRxs'),
 (72,'New Video Has been Added',NULL,'subCategory','2019-12-26 19:22:49',0,NULL,'Video',30,NULL,NULL,NULL,'8yvK31KNvAc'),
 (73,'New Video Has been Added',NULL,'subCategory','2019-12-26 19:22:49',0,NULL,'Video',30,NULL,NULL,NULL,'7wYva9dhelE'),
 (74,'New Video Has been Added',NULL,'subCategory','2019-12-26 19:22:49',0,NULL,'Video',30,NULL,NULL,NULL,'R8aDgJlHB3Q'),
 (75,'New Trending Career Added',NULL,'subCategory','2019-12-26 19:53:02',0,NULL,'careerMain',31,NULL,NULL,NULL,NULL),
 (76,'New Video Has been Added',NULL,'subCategory','2019-12-26 20:03:16',0,NULL,'Video',31,NULL,NULL,NULL,'32fLCPcsffs'),
 (77,'New Video Has been Added',NULL,'subCategory','2019-12-26 20:03:16',0,NULL,'Video',31,NULL,NULL,NULL,'MbMh6KRLz9U'),
 (78,'New Video Has been Added',NULL,'subCategory','2019-12-26 20:03:16',0,NULL,'Video',31,NULL,NULL,NULL,'MQtVmxQbvXo'),
 (79,'New Video Has been Added',NULL,'subCategory','2019-12-26 20:03:16',0,NULL,'Video',31,NULL,NULL,NULL,'Z1fGHLKA2zk'),
 (80,'New Video Has been Added',NULL,'subCategory','2019-12-26 20:03:16',0,NULL,'Video',31,NULL,NULL,NULL,'if75PRKQHWQ'),
 (81,'New Video Has been Added',NULL,'subCategory','2019-12-26 20:03:16',0,NULL,'Video',31,NULL,NULL,NULL,'qAKODfJZV9M'),
 (82,'New Video Has been Added',NULL,'subCategory','2019-12-26 20:03:16',0,NULL,'Video',31,NULL,NULL,NULL,'WdvC9xUfuyc'),
 (83,'New Video Has been Added',NULL,'subCategory','2019-12-26 20:03:16',0,NULL,'Video',31,NULL,NULL,NULL,'ZX6dz61s-po'),
 (84,'New Video Has been Added',NULL,'subCategory','2019-12-26 20:03:16',0,NULL,'Video',31,NULL,NULL,NULL,'CfzeEz826VM'),
 (85,'New Video Has been Added',NULL,'subCategory','2019-12-26 20:03:16',0,NULL,'Video',31,NULL,NULL,NULL,'_s42gOg2WSU'),
 (86,'New Video Has been Added',NULL,'subCategory','2019-12-26 20:03:16',0,NULL,'Video',31,NULL,NULL,NULL,'3dZRmgLq8bU'),
 (87,'New Video Has been Added',NULL,'subCategory','2019-12-26 20:03:16',0,NULL,'Video',31,NULL,NULL,NULL,'kLFItf290Fc'),
 (88,'New Video Has been Added',NULL,'subCategory','2019-12-26 20:03:16',0,NULL,'Video',31,NULL,NULL,NULL,'fdTX1nUapTY'),
 (89,'New Video Has been Added',NULL,'subCategory','2019-12-26 20:03:16',0,NULL,'Video',31,NULL,NULL,NULL,'QCQyB0FBJjY'),
 (90,'New Video Has been Added',NULL,'subCategory','2019-12-26 20:03:16',0,NULL,'Video',31,NULL,NULL,NULL,'tPYlvMERJmY'),
 (91,'New Video Has been Added',NULL,'subCategory','2019-12-26 20:03:16',0,NULL,'Video',31,NULL,NULL,NULL,'14ffsxVCRxs'),
 (92,'New Video Has been Added',NULL,'subCategory','2019-12-26 20:03:16',0,NULL,'Video',31,NULL,NULL,NULL,'8yvK31KNvAc'),
 (93,'New Video Has been Added',NULL,'subCategory','2019-12-26 20:03:16',0,NULL,'Video',31,NULL,NULL,NULL,'7wYva9dhelE'),
 (94,'New Video Has been Added',NULL,'subCategory','2019-12-26 20:03:16',0,NULL,'Video',31,NULL,NULL,NULL,'R8aDgJlHB3Q');
/*!40000 ALTER TABLE `notification` ENABLE KEYS */;


--
-- Definition of table `otp_token`
--

DROP TABLE IF EXISTS `otp_token`;
CREATE TABLE `otp_token` (
  `id` bigint(10) unsigned NOT NULL AUTO_INCREMENT,
  `user` bigint(10) unsigned NOT NULL,
  `authority` bigint(10) NOT NULL,
  `date_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `status` int(10) unsigned NOT NULL,
  `otp` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_otp_token_authority` (`authority`),
  CONSTRAINT `FK_otp_token_authority` FOREIGN KEY (`authority`) REFERENCES `authority` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `otp_token`
--

/*!40000 ALTER TABLE `otp_token` DISABLE KEYS */;
INSERT INTO `otp_token` (`id`,`user`,`authority`,`date_created`,`status`,`otp`) VALUES 
 (1,16,2,'2019-10-18 14:23:37',1,'6f741b'),
 (2,16,2,'2019-10-18 14:27:36',1,'f82268'),
 (3,16,2,'2019-10-18 14:53:16',1,'0d17cb'),
 (4,16,2,'2019-10-18 15:49:59',1,'01ce3f'),
 (5,16,2,'2019-10-18 15:49:59',1,'38e4b5'),
 (6,16,2,'2019-10-18 15:49:59',1,'39fdff'),
 (7,16,2,'2019-10-18 15:47:02',1,'44dd7e'),
 (8,16,2,'2019-10-18 15:50:08',1,'fb4d1d'),
 (9,16,2,'2019-10-18 15:50:27',1,'09287c'),
 (10,16,2,'2019-10-18 16:06:37',1,'e8f8f5'),
 (11,16,2,'2019-10-18 16:07:54',1,'4c4ac3'),
 (12,16,2,'2019-10-21 16:52:36',1,'2828e4'),
 (13,16,2,'2019-10-21 16:53:24',1,'c7bf64'),
 (14,16,2,'2019-10-21 17:12:58',1,'494ed6'),
 (15,16,2,'2019-10-22 15:33:55',1,'f31459');
/*!40000 ALTER TABLE `otp_token` ENABLE KEYS */;


--
-- Definition of table `parents`
--

DROP TABLE IF EXISTS `parents`;
CREATE TABLE `parents` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `address` varchar(255) DEFAULT NULL,
  `address1` varchar(255) DEFAULT NULL,
  `authority` varchar(255) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `date_created` datetime DEFAULT NULL,
  `decrypt_password` varchar(255) NOT NULL,
  `father_email` varchar(255) DEFAULT NULL,
  `father_mobile` bigint(20) DEFAULT NULL,
  `father_name` varchar(255) DEFAULT NULL,
  `father_occupation` varchar(255) DEFAULT NULL,
  `mother_email` varchar(255) DEFAULT NULL,
  `mother_mobile` bigint(20) DEFAULT NULL,
  `mother_name` varchar(255) DEFAULT NULL,
  `mother_occupation` varchar(255) DEFAULT NULL,
  `password` varchar(255) NOT NULL,
  `pincode` varchar(255) DEFAULT NULL,
  `state` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `user_name` varchar(255) NOT NULL,
  `parent_img_name` varchar(45) DEFAULT NULL,
  `parent_img_path` varchar(45) DEFAULT NULL,
  `description` varchar(45) DEFAULT NULL,
  `type` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `parents`
--

/*!40000 ALTER TABLE `parents` DISABLE KEYS */;
INSERT INTO `parents` (`id`,`address`,`address1`,`authority`,`city`,`date_created`,`decrypt_password`,`father_email`,`father_mobile`,`father_name`,`father_occupation`,`mother_email`,`mother_mobile`,`mother_name`,`mother_occupation`,`password`,`pincode`,`state`,`status`,`user_name`,`parent_img_name`,`parent_img_path`,`description`,`type`) VALUES 
 (1,'Hyd','Hyd','ROLE_PARENT','Hyd',NULL,'Geo1YYVY','ramesh@gmail.com',23223234,'XY',NULL,'',9032508683,'abc',NULL,'$2a$10$6oIDjL8waijO699hjNa.WOaFFfWFVgyYY9qaPbYs7e7Kxc29tgBNa','5300039','TS',0,'ramesh@gmail.com',NULL,NULL,NULL,NULL),
 (2,'ayyappasocity','mainroad','ROLE_PARENT','hyderan]bad',NULL,'y/7O@C=j','BCDE@gmail.com',9090909089,'BCDE','BCDE','hhhh@gmail.com',9090909090,'BCDE','BCDE','$2a$10$awClkc4KsNdjZvWmcyG4IusfFs4Fqk9c6pynT/0Nkv4j0RUrTfXKa','500038','Telangana',0,'haritha2065@gmail.com',NULL,NULL,NULL,NULL),
 (3,'madhapur','','ROLE_PARENT','hyderabad',NULL,'j3e/p^Xu','haritha@gmail.com',8989898989,'Vasu',NULL,'haritha1@gmail.com',9090909090,'Raji',NULL,'$2a$10$81xAqPDOlgcIJ30RiRS77ePwqX1p7QD4pQFGiXNcKn58qwJlcI04W','500089','Telangana',0,'haritha@gmail.com',NULL,NULL,NULL,NULL),
 (4,'madhapur','madhapur','ROLE_PARENT','hyderabad',NULL,'f6m$p1jV','vasu@gmail.com',9089767899,'Vasu','Farmer','raji@gmail.com',8986567888,'Raji','House wife','$2a$10$5GtSgHOLmuAWNrgSm0mq3eCcsmnGFgSdAg/He7P00lgmCnpS7UJxO','500089','Telangana',0,'vasu@gmail.com',NULL,NULL,NULL,NULL),
 (5,'asd','dfds','ROLE_PARENT','dsfds',NULL,'q27-eaR+','vasu12@gmail.com',9090789090,'sfds',NULL,'vani@gmail.com',7897899090,'sdfds',NULL,'$2a$10$BTS7M/UTbe2EoYLsbNSnieUGjddySCXdTCgwpTATfaVRbQ6aBAnGy','dsfs','dsfs',0,'vasu12@gmail.com',NULL,NULL,NULL,NULL),
 (6,'SDFS','DSFDS','ROLE_PARENT','DSFDS',NULL,'D5UUFycH','ASD@GMAIL.COM',9091226789,'ASDSDS',NULL,'ASD@GMAIL.COM',8908907890,'ADSAD',NULL,'$2a$10$SmCjZLXxDALFm6teAPGuuu0WU4id2VHejjtHdIFcMx8JsKhGqSpdC','500078','WFDSQ',0,'ASD@GMAIL.COM',NULL,NULL,NULL,NULL),
 (7,'dsfsdf','sdfsd','ROLE_PARENT','hyderabad',NULL,'x1IGhZaV','raefew@gmail.com',9090900899,'sdfsdfds',NULL,'sdfsd@hasds.com',9887333332,'assdfdsf',NULL,'$2a$10$/NQwJVNLqlC.1cpZW6JlwOC1y806TgxDy0YwIXZXribDMbq9czGc2','500038','AP',0,'raefew@gmail.com',NULL,NULL,NULL,NULL),
 (8,'adasdasd','asdasd','ROLE_PARENT','adad',NULL,'yjiPyI4L','sad@gmai.com',9899234234,'sdsad',NULL,'adwew@gmail.com',8892344567,'ADASDASD',NULL,'$2a$10$u9cenMeK4TCQ./yyZV9Mme6DdjqwZGvN3v7QnBUam0tmaToJG4HUS','500098','asdasd',0,'sad@gmai.com',NULL,NULL,NULL,NULL),
 (9,'ASDASD','ASDASD','ROLE_PARENT','ASDASD',NULL,'gPonA!yt','ZDSS@GMAIL.COM',9893423333,'ASDASD',NULL,'sdfsd@afsd.com',8990034324,'ASDADS',NULL,'$2a$10$NrpHwAd.6ynTi5SmSd2FguHpU6/LIwEYWXMPdcKTauRme3QhZYasS','780098','ASDASD',0,'ZDSS@GMAIL.COM',NULL,NULL,NULL,NULL),
 (10,'asdasd','dasdasd','ROLE_PARENT','asdasd',NULL,'UvSQOi-T','ranu@gmail.com',9090978900,'asdasd',NULL,'asdas@gmail.com',8908907890,'sadasd',NULL,'$2a$10$UZNFfiGxXeJK/0gL5LY6cOgPhFs2wWYDIoD4MbtstU7B4gw21X8K6','500038','sdasdsa',0,'ranu@gmail.com',NULL,NULL,NULL,NULL),
 (11,'zxczxc','zcxzxc','ROLE_PARENT','zxczxc',NULL,'k5-_jXI=','sfsdfd@GMAIL.COM',8908907890,'zxczxc',NULL,'zxczxc@gmail.com',9090978900,'zxczxc',NULL,'$2a$10$NsnPrMk.EDO1EgSHwcxRjukELe21GdWargEavCx2h5Q66oairZuga','500078','WFDSQ',0,'sfsdfd@GMAIL.COM',NULL,NULL,NULL,NULL),
 (12,'dfdsfdg','dsdfsdf','ROLE_PARENT','sdfsdf',NULL,'I#L/h_C/','tdsdfs@gmail.com',8762345674,'sdfsdf',NULL,'sdfsd@sfsdf.com',7897899090,'dfsdf',NULL,'$2a$10$eP8WoAqt2s2610YH4T.svOxnG5AWA8bQM2H9zDkPuyUj8ogc0qDGq','500099','Telangana',0,'tdsdfs@gmail.com',NULL,NULL,NULL,NULL),
 (13,'dfdsfdsf','sdfsdf','ROLE_PARENT','dfds',NULL,'9//Ytye2','sdfsdf@gmail.com',890890999,'sfsdf',NULL,'sfsdf@adsd.com',8989898989,'sdfsdf',NULL,'$2a$10$3TUON4A/1hOxmM30RQ2Rhu.zZrCxv8lOxIZYiBAT0f3fiepFXBCWq','500038','AP',0,'sdfsdf@gmail.com',NULL,NULL,NULL,NULL),
 (14,'safsdfsdf','sdfsdfsd','ROLE_PARENT','sdfsdf',NULL,'1+prJ@o#','sdfsd@gmail.com',9900546546,'fsdfdsf',NULL,'sdfsdf@afdf.com',9879734544,'sdgfsdg',NULL,'$2a$10$flmS7KfckzIEsJ8S5O/H3.RT4y9f1WPm6yC8OWVbS1NBl7gtNBwOK','500089','Telangana',0,'sdfsd@gmail.com',NULL,NULL,NULL,NULL),
 (15,'zxczxc','zxczxc','ROLE_PARENT','zxczxcxzc',NULL,'4Qo^I63p','ranu@gmail.com',8908907890,'asdasd','Farmer','asdas@gmail.com',8908990008,'sadasd','HouseWife','$2a$10$fee4TB662z17tkzhjaJBC.fNgarhLA8CtWuCy4EK39hXnhR9VcpB.','500090','AP',0,'hasda@gmail.com',NULL,NULL,NULL,NULL),
 (16,'dfsdf','dsf','ROLE_PARENT','sdfffffffffffff',NULL,'p8#I^tcJ','tyu@gmail.com',7890890890,'fsfsdfsf',NULL,'qwe@gmail.com',8907890765,'sdfdsfdsf',NULL,'$2a$10$B6DI4zXn.CDfELrAGz2.iONviJXlnsbpRpFMssD0.xPxiy9mYYRy.','546789','fssssssssdf',0,'tyu@gmail.com',NULL,NULL,NULL,NULL),
 (17,'sdsdfdsfsdf','kalyannagar','ROLE_PARENT','hyderabad',NULL,'7D_OBrW^','ssdd@gmail.com',8762909889,'asdd',NULL,'cds@gmail.com',8900765234,'sdfsfd',NULL,'$2a$10$KrPw3R9p1iymfRFPnWKODOV1h952US72u.I.uZqqQnmPmzGsyXp6q','500099','Telangana',0,'ssdd@gmail.com',NULL,NULL,NULL,NULL),
 (18,'asdasd','das','ROLE_PARENT','hyderabad',NULL,'xILSuqdE','harithae@gmail.com',7890789909,'hari',NULL,'htyu@gmail.com',9089890078,'harii',NULL,'$2a$10$Sh3/qlywxg5KHzcz4lRyM.TUqRpfVHG1rUHiUuOnsFaApxt3oFOiC','500098','AP',0,'harithae@gmail.com',NULL,NULL,NULL,NULL),
 (19,'madhapur','sdfsdfds','ROLE_PARENT','hyderabad',NULL,'En%#yMyZ','hariwe@gmail.com',90909000678,'sadsad',NULL,'sdfsdf@gmail.com',8090808000,'asdasd',NULL,'$2a$10$XqNqdNt.df1VO6l/WNfkQuiB7M5CIP.eVRvFMuMzzF3P9dsqIcfza','500089','Telangana',0,'hariwe@gmail.com',NULL,NULL,NULL,NULL),
 (20,'zxcx','xvc','ROLE_PARENT','hyderabad',NULL,'=4iMqdGP','swar@gmail.com',7890789067,'swar','Employee','swarna@gmail.com',9087889990,'swarnalatha','house wife','$2a$10$s43tHO5gQsvUHeZKA.2D8usiZPHGsRqAIfbtvZFSsw52CZiKlV5a2','500099','Telangana',0,'swar@gmail.com',NULL,NULL,NULL,NULL),
 (21,'srnagar','kalyannagar','ROLE_PARENT','hyderabad',NULL,'*+@#^Z&T','asds@gmail.com',65768768777,'asdsd','sadasd','dfdsf@gmail.com',8762345678,'nalini ram','asdsad','$2a$10$MIM.nOCYi5KS5qRYjKAoHOnaxwzCrT5nY.xDMBc2qoilYVBfFf7o.','500099','Telangana',0,'sdal0003','investmentnbanker.jpg','/parentImages/21',NULL,NULL),
 (22,'madhapur','srnagar','ROLE_PARENT','hyderabad',NULL,'4xIMHB8!','balajif@gmail.com',9808900900,'balajiFather',NULL,'balajim@gmail.com',8986789999,'balajiMother',NULL,'$2a$10$ry25yJFGvvfVSNWpqvFz2uLacyVbwoXYM9jE0WoRUMldWavqx6hBe','500089','Telangana',0,'alal0007',NULL,NULL,NULL,NULL),
 (23,'Hyderabad','Hyderabad','ROLE_PARENT','Hyderabad',NULL,'UtZuk/XY','mani@gmail.com',8525852451,'Manikanta','Teacher','manimnai@gmail.com',9874147454,'Mani','Doctor','$2a$10$cCHnG0/KKo31CyrEH09z2eyh2/PkOE.667S4RbhVoqstNlX4eJsaq','500000','Hyderabad',0,'anan0008',NULL,NULL,NULL,NULL),
 (24,'Hyderabad','Hyderabad','ROLE_PARENT','Hyderabad',NULL,'0P!dD+*x','mani@gmail.com',8525852451,'Manikanta','Teacher','manimnai@gmail.com',9874147454,'Mani','Doctor','$2a$10$ZDP.XinOHly0OmHR1Qa3yeUmDUgMsCuYCAzXDT1wGQfHdvMRVI.zK','500000','Hyderabad',0,'anan0009',NULL,NULL,NULL,NULL),
 (25,'Hyderabad','Hyderabad','ROLE_PARENT','Hyderabad',NULL,'TCr&Tu@t','mani@gmail.com',8525852451,'Manikanta','Teacher','manimnai@gmail.com',9874147454,'Mani','Doctor','$2a$10$M.FB2QL6Dn/OiO2LW4IvwOsz7l6jotfjh1U/ycoohys17GvAEYzXS','500000','Hyderabad',0,'anan0010',NULL,NULL,NULL,NULL),
 (26,'Hyderabad','Hyderabad','ROLE_PARENT','Hyderabad',NULL,'J2uTcN/R','mani@gmail.com',8525852451,'Manikanta','Teacher','manimnai@gmail.com',9874147454,'Mani','Doctor','$2a$10$UiPmV/qQ8j34jqLCWU9mHeXj.YQO2/FhvrjPriqyTr.b9jdXeduIu','500000','Hyderabad',0,'anan0011',NULL,NULL,NULL,NULL),
 (27,'Hyderabad','Hyderabad','ROLE_PARENT','Hyderabad',NULL,'*7qp+106','mani@gmail.com',8525852451,'Manikantaone','Tour Operator','manimnai@gmail.com',9874147454,'Mani','Tour Guide','$2a$10$rHjsTNSat4OLUKD2bfO.c.2JS9tBJ3IuViTWJbzkKYnCRjwEkUhBm','500000','Hyderabad',0,'anan0012',NULL,NULL,NULL,NULL),
 (28,'Hyderabad','Hyderabad','ROLE_PARENT','Hyderabad',NULL,'dE#M8_fT','mani@gmail.com',8525852451,'Manikanta','Police','manimnai@gmail.com',9874147454,'Mani','Commitioner','$2a$10$rZQ1.oG8k/gMrKgO0IUeJOPH9ogpiJusxfer5Zd3jkRtfxgEqf/oi','500000','Hyderabad',0,'anan0013',NULL,NULL,NULL,NULL),
 (29,'Hyderabad','Hyderabad','ROLE_PARENT','Hyderabad',NULL,'R#j-/Ab6','mani@gmail.com',8525852451,'Manikanta','Teacher','manimnai@gmail.com',9874147454,'Mani','Doctor','$2a$10$n8esXzACP.4qW0pR9UvahO3cqt7tXlSPgro4UdNKKP1yIyUXg7KDK','500000','Hyderabad',0,'anan0014',NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `parents` ENABLE KEYS */;


--
-- Definition of table `recommended`
--

DROP TABLE IF EXISTS `recommended`;
CREATE TABLE `recommended` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `user` bigint(20) unsigned NOT NULL,
  `authority` varchar(45) NOT NULL,
  `count` int(10) unsigned NOT NULL,
  `date_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `status` int(11) unsigned NOT NULL,
  `career_subcat` bigint(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_recommended_subcat` (`career_subcat`),
  CONSTRAINT `FK_recommended_subcat` FOREIGN KEY (`career_subcat`) REFERENCES `career_subcategory` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `recommended`
--

/*!40000 ALTER TABLE `recommended` DISABLE KEYS */;
INSERT INTO `recommended` (`id`,`user`,`authority`,`count`,`date_created`,`status`,`career_subcat`) VALUES 
 (1,17,'ROLE_STUDENT',2,'2019-12-16 14:34:21',0,4),
 (3,17,'ROLE_STUDENT',1,'2019-11-08 14:44:18',0,7),
 (4,17,'ROLE_STUDENT',1,'2019-12-16 14:34:21',0,9),
 (5,17,'ROLE_STUDENT',1,'2019-12-16 14:34:21',0,10),
 (6,17,'ROLE_STUDENT',1,'2019-11-08 14:44:19',0,12),
 (7,14,'ROLE_STUDENT',1,'2019-12-16 14:34:56',0,13),
 (18,16,'ROLE_STUDENT',3,'2019-11-26 16:29:15',0,1),
 (19,30,'ROLE_STUDENT',2,'2019-12-01 18:17:40',0,2),
 (20,16,'ROLE_STUDENT',1,'2019-12-16 14:34:21',0,4),
 (21,16,'ROLE_STUDENT',1,'2019-12-16 12:21:44',0,NULL),
 (22,16,'ROLE_STUDENT',1,'2019-12-16 12:24:37',0,NULL),
 (23,26,'ROLE_STUDENT',3,'2019-12-17 11:09:52',0,1);
/*!40000 ALTER TABLE `recommended` ENABLE KEYS */;


--
-- Definition of table `responsibilities`
--

DROP TABLE IF EXISTS `responsibilities`;
CREATE TABLE `responsibilities` (
  `id` bigint(10) unsigned NOT NULL AUTO_INCREMENT,
  `roles` varchar(2000) NOT NULL,
  `subcat_id` bigint(10) unsigned DEFAULT NULL,
  `date_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `status` int(10) unsigned DEFAULT NULL,
  `skills` varchar(2000) DEFAULT NULL,
  `abilities` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_roles_response_subcat` (`subcat_id`),
  CONSTRAINT `FK_roles_response_subcat` FOREIGN KEY (`subcat_id`) REFERENCES `career_subcategory` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=71 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `responsibilities`
--

/*!40000 ALTER TABLE `responsibilities` DISABLE KEYS */;
INSERT INTO `responsibilities` (`id`,`roles`,`subcat_id`,`date_created`,`status`,`skills`,`abilities`) VALUES 
 (7,'xzcccccccc',1,'2019-09-26 14:24:11',0,NULL,NULL),
 (8,'zxcccccc',1,'2019-09-26 14:24:11',0,NULL,NULL),
 (9,'xzcccccccccc',1,'2019-09-26 14:24:11',0,NULL,NULL),
 (10,'xzcccccccccc',1,'2019-09-26 14:24:11',0,NULL,NULL),
 (11,'zxcccccccccc',1,'2019-09-26 14:24:11',0,NULL,NULL),
 (12,'XZZZZZZZZZZ',1,'2019-09-26 16:08:29',0,NULL,NULL),
 (13,'adsasd',1,'2019-09-26 16:08:29',0,NULL,NULL),
 (14,'asdad',1,'2019-09-26 16:08:29',0,NULL,NULL),
 (15,'asdsad',1,'2019-09-26 16:08:29',0,NULL,NULL),
 (16,'xzcccccccccccccccc',1,'2019-09-26 17:03:08',0,NULL,NULL),
 (17,'xzcczxcccccccc',1,'2019-09-26 17:03:08',0,NULL,NULL),
 (18,'zxcccccccc',1,'2019-09-26 17:03:08',0,NULL,NULL),
 (19,'xzccccccccccc',1,'2019-09-26 17:03:08',0,NULL,NULL),
 (20,'zxczzzzzzzz',6,'2019-09-27 11:25:34',0,NULL,NULL),
 (21,'xzccccccccccc',6,'2019-09-27 11:25:34',0,NULL,NULL),
 (22,'zxcxzzzzzzzzzz',6,'2019-09-27 11:25:34',0,NULL,NULL),
 (23,'zcxzxxxxxxx',1,'2019-09-27 11:25:34',0,NULL,NULL),
 (24,'zxczv',7,'2019-10-01 17:26:08',0,NULL,NULL),
 (25,'asfda',7,'2019-10-01 17:26:08',0,NULL,NULL),
 (26,'asf',7,'2019-10-01 17:26:08',0,NULL,NULL),
 (27,'role1',8,'2019-10-10 16:14:41',0,NULL,NULL),
 (28,'role2',8,'2019-10-10 16:14:41',0,NULL,NULL),
 (29,'role3',8,'2019-10-10 16:14:41',0,NULL,NULL),
 (30,'zxvxz',9,'2019-10-25 17:57:17',0,NULL,NULL),
 (31,'safsadf',9,'2019-10-25 17:57:17',0,NULL,NULL),
 (32,'safsaf',9,'2019-10-25 17:57:17',0,NULL,NULL),
 (33,'zxvxz',9,'2019-10-25 17:57:17',0,NULL,NULL),
 (34,'safsadf',9,'2019-10-25 17:57:17',0,NULL,NULL),
 (35,'safsaf',9,'2019-10-25 17:57:17',0,NULL,NULL),
 (36,'sdf',13,'2019-10-28 13:38:53',0,NULL,NULL),
 (37,'sdf',13,'2019-10-28 13:38:53',0,NULL,NULL),
 (38,'sdfsd',16,'2019-11-04 20:48:50',0,NULL,NULL),
 (39,'ytr',16,'2019-11-04 20:48:50',0,NULL,NULL),
 (40,'drh',16,'2019-11-04 20:48:50',0,NULL,NULL),
 (41,'sdfsd',16,'2019-11-04 20:48:50',0,NULL,NULL),
 (42,'ytr',16,'2019-11-04 20:48:50',0,NULL,NULL),
 (43,'drh',16,'2019-11-04 20:48:50',0,NULL,NULL),
 (44,'asd',17,'2019-11-05 12:45:05',0,NULL,NULL),
 (45,'asd',17,'2019-11-05 12:45:05',0,NULL,NULL),
 (46,'asd',17,'2019-11-05 12:45:05',0,NULL,NULL),
 (47,'asd',17,'2019-11-05 12:45:05',0,NULL,NULL),
 (48,'asd',17,'2019-11-05 12:45:06',0,NULL,NULL),
 (49,'asd',17,'2019-11-05 12:45:06',0,NULL,NULL),
 (50,'asd',17,'2019-11-05 12:45:06',0,NULL,NULL),
 (51,'asd',17,'2019-11-05 12:45:06',0,NULL,NULL),
 (56,'Inspect malfunctioning or damaged products to identify sources of problems and possible solutions.',29,'2019-12-26 19:16:34',0,NULL,NULL),
 (57,'Develop standards and guidelines for hardware.',29,'2019-12-26 19:16:34',0,NULL,NULL),
 (58,'Perform modelling and prediction using statistical tools and computational analyses, and creativity.',29,'2019-12-26 19:16:34',0,NULL,NULL),
 (59,'Assesses proposals for projects to determine if they are technically and financially feasible',29,'2019-12-26 19:16:34',0,NULL,NULL),
 (60,'Develops acceptance criteria for design methods, quality standards, sustainment after delivery, and completion dates.',29,'2019-12-26 19:16:34',0,NULL,NULL),
 (61,'Inspect malfunctioning or damaged products to identify sources of problems and possible solutions.',30,'2019-12-26 19:17:18',0,NULL,NULL),
 (62,'Develop standards and guidelines for hardware.',30,'2019-12-26 19:17:18',0,NULL,NULL),
 (63,'Perform modelling and prediction using statistical tools and computational analyses, and creativity.',30,'2019-12-26 19:17:18',0,NULL,NULL),
 (64,'Assesses proposals for projects to determine if they are technically and financially feasible',30,'2019-12-26 19:17:18',0,NULL,NULL),
 (65,'Develops acceptance criteria for design methods, quality standards, sustainment after delivery, and completion dates.',30,'2019-12-26 19:17:18',0,NULL,NULL),
 (66,'Inspect malfunctioning or damaged products to identify sources of problems and possible solutions.',31,'2019-12-26 19:53:01',0,NULL,NULL),
 (67,'Develop standards and guidelines for hardware.',31,'2019-12-26 19:53:01',0,NULL,NULL),
 (68,'Perform modelling and prediction using statistical tools and computational analyses, and creativity.',31,'2019-12-26 19:53:01',0,NULL,NULL),
 (69,'Assesses proposals for projects to determine if they are technically and financially feasible',31,'2019-12-26 19:53:01',0,NULL,NULL),
 (70,'Develops acceptance criteria for design methods, quality standards, sustainment after delivery, and completion dates.',31,'2019-12-26 19:53:02',0,NULL,NULL);
/*!40000 ALTER TABLE `responsibilities` ENABLE KEYS */;


--
-- Definition of table `scholorships`
--

DROP TABLE IF EXISTS `scholorships`;
CREATE TABLE `scholorships` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `scholorship_name` varchar(105) NOT NULL,
  `scholorship_list` varchar(105) NOT NULL,
  `date_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `status` int(10) unsigned NOT NULL,
  `subcat_id` bigint(10) unsigned NOT NULL,
  `country` int(10) unsigned NOT NULL,
  `url` varchar(300) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_scholorships_subcat` (`subcat_id`),
  CONSTRAINT `FK_scholorships_subcat` FOREIGN KEY (`subcat_id`) REFERENCES `career_subcategory` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `scholorships`
--

/*!40000 ALTER TABLE `scholorships` DISABLE KEYS */;
INSERT INTO `scholorships` (`id`,`scholorship_name`,`scholorship_list`,`date_created`,`status`,`subcat_id`,`country`,`url`) VALUES 
 (2,'scholorship','scholorship1,scholorship2,scholorship3','2019-11-01 15:16:19',0,13,0,'url.com');
/*!40000 ALTER TABLE `scholorships` ENABLE KEYS */;


--
-- Definition of table `school`
--

DROP TABLE IF EXISTS `school`;
CREATE TABLE `school` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `address` varchar(255) DEFAULT NULL,
  `address1` varchar(255) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `contact_no` bigint(20) NOT NULL,
  `date_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `email` varchar(255) DEFAULT NULL,
  `pincode` varchar(255) DEFAULT NULL,
  `school_code` varchar(255) DEFAULT NULL,
  `school_name` varchar(255) DEFAULT NULL,
  `state` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `school`
--

/*!40000 ALTER TABLE `school` DISABLE KEYS */;
INSERT INTO `school` (`id`,`address`,`address1`,`city`,`contact_no`,`date_created`,`email`,`pincode`,`school_code`,`school_name`,`state`,`status`) VALUES 
 (1,'sdf','Hyderabad','Hyderabad',9032508683,'2019-10-10 12:41:57','ramesh@gmail.com','530029','SC','School 1','Telanagana',0),
 (2,'Ayyappasociety1','Mainroad','Hyderabad',890789089,'2019-12-26 15:43:06','haritha2065@gmail.com','500456','ADR567','Nalanda','Telangana',0),
 (3,'srnagar','narayanaguda','hyderabad',8790890890,'2019-12-26 17:37:34','haritha34@gmail.com','90890','NDPC','Narmada','Telangana',0),
 (4,'ayyappascociety','Madhapur','Hyderabad',8908908900,'2019-10-10 12:41:57','haritha@gmail.com','518155','9090','Swetha','AP',0),
 (5,'asfffff','asfffff','asfasf',8908908909,'2019-10-10 12:41:57','cdsf@gmail.com','390893','asfas','zxcccccccc','safasf',0);
/*!40000 ALTER TABLE `school` ENABLE KEYS */;


--
-- Definition of table `school_administrator_authority`
--

DROP TABLE IF EXISTS `school_administrator_authority`;
CREATE TABLE `school_administrator_authority` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `date_created` datetime DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `authority` bigint(20) DEFAULT NULL,
  `school_user` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_j48e279we1i9s4qlgubyvf2s5` (`authority`),
  KEY `FK_8vbyja28fhluanlddkhy9786h` (`school_user`),
  CONSTRAINT `FK_8vbyja28fhluanlddkhy9786h` FOREIGN KEY (`school_user`) REFERENCES `school_users` (`id`),
  CONSTRAINT `FK_j48e279we1i9s4qlgubyvf2s5` FOREIGN KEY (`authority`) REFERENCES `authority` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `school_administrator_authority`
--

/*!40000 ALTER TABLE `school_administrator_authority` DISABLE KEYS */;
INSERT INTO `school_administrator_authority` (`id`,`date_created`,`status`,`authority`,`school_user`) VALUES 
 (1,NULL,0,NULL,1),
 (2,NULL,0,NULL,2),
 (3,NULL,0,6,3),
 (4,NULL,0,7,4),
 (5,NULL,0,7,5),
 (6,NULL,0,6,6),
 (7,NULL,0,7,7),
 (8,NULL,0,7,8);
/*!40000 ALTER TABLE `school_administrator_authority` ENABLE KEYS */;


--
-- Definition of table `school_teacher`
--

DROP TABLE IF EXISTS `school_teacher`;
CREATE TABLE `school_teacher` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `address` varchar(255) DEFAULT NULL,
  `address1` varchar(255) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `mobile` bigint(20) NOT NULL,
  `date_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `date_of_birth` varchar(150) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `expertise` varchar(255) DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `gender` int(11) DEFAULT NULL,
  `hobbies` varchar(255) DEFAULT NULL,
  `interests` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `pincode` varchar(255) DEFAULT NULL,
  `state` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `teachers_talk_contribution` bit(1) DEFAULT NULL,
  `school` bigint(20) DEFAULT NULL,
  `teacher_img_name` varchar(45) DEFAULT NULL,
  `teacher_img_path` varchar(45) DEFAULT NULL,
  `description` varchar(45) DEFAULT NULL,
  `type` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_lehb1dj15duidvsod59mcmm3p` (`school`),
  CONSTRAINT `FK_lehb1dj15duidvsod59mcmm3p` FOREIGN KEY (`school`) REFERENCES `school` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `school_teacher`
--

/*!40000 ALTER TABLE `school_teacher` DISABLE KEYS */;
INSERT INTO `school_teacher` (`id`,`address`,`address1`,`city`,`mobile`,`date_created`,`date_of_birth`,`email`,`expertise`,`first_name`,`gender`,`hobbies`,`interests`,`last_name`,`pincode`,`state`,`status`,`teachers_talk_contribution`,`school`,`teacher_img_name`,`teacher_img_path`,`description`,`type`) VALUES 
 (1,'dfdf','sdfsdf','sdfsdf',8990988789,'2019-10-23 09:21:37','0024-03-11 00:00:00','dsfs@gjjasd.com','sdf','zxcxzc',1,'zxczx','zxcxzcxzc','zdsfsd','500009','sdfsdf',0,0x00,1,NULL,NULL,NULL,NULL),
 (2,'madhapur','sdfsdfds','hyderabad',8989898989,'2019-10-23 09:21:37','0014-02-07 00:00:00','haritha@gmail.com','sdfsdfdsfds','ramu',0,'sdfsdf','sdfsdfds','h','500089','Telangana',0,0x00,1,NULL,NULL,NULL,NULL),
 (3,'Madhapur','HitechCity','hyderabad',9089078909,'2019-10-23 09:21:37','0013-06-06 00:00:00','jillan@gmail.com','Maths','abdula',0,'Listening Music,Watching TV','learning new things','Jillan','500098','Telangana',0,0x00,3,NULL,NULL,NULL,NULL),
 (4,'Madhapur','HitechCity','Hyderabad',8074416723,'2019-10-23 09:21:37','0013-04-10 00:00:00','haritha2065@gmail.com','Maths','haritha',0,'Watching TV,Listening Music','Learning new things','pall','500988','Telangana',0,0x00,3,NULL,NULL,NULL,NULL),
 (5,'Madhapur','HitechCity','hyderabad',9089078909,'2019-10-23 10:18:31','2019-05-08','jillan@gmail.com','dsfsdfs','abdula',0,'dsfdsf,fdgdfg,fdgfd','sfsf,dgdgfd,dfdfg','Jillan','500098','Telangana',0,0x00,3,'investmentnbanker.jpg','/teacherImages/5',NULL,NULL);
/*!40000 ALTER TABLE `school_teacher` ENABLE KEYS */;


--
-- Definition of table `school_users`
--

DROP TABLE IF EXISTS `school_users`;
CREATE TABLE `school_users` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `date_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `decrypt_password` varchar(255) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `password` varchar(255) NOT NULL,
  `phone` bigint(20) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `user_name` varchar(255) NOT NULL,
  `school` bigint(20) DEFAULT NULL,
  `authority` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_4bibndmpe7pkw3k8xjficnhf7` (`school`),
  CONSTRAINT `FK_4bibndmpe7pkw3k8xjficnhf7` FOREIGN KEY (`school`) REFERENCES `school` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `school_users`
--

/*!40000 ALTER TABLE `school_users` DISABLE KEYS */;
INSERT INTO `school_users` (`id`,`date_created`,`decrypt_password`,`email`,`first_name`,`last_name`,`password`,`phone`,`status`,`user_name`,`school`,`authority`) VALUES 
 (1,'2019-10-23 09:38:15','WEVc4EnM','ramesh@gmail.com','School 1',NULL,'$2a$10$MpmZaLzfNKyhEk8YpHg1iOcOtagILO2yR5JXuTGJsM24QjtIl6l16',9032508683,0,'ramesh@gmail.com',1,0),
 (2,'2019-10-23 09:38:15','y7NuZaIx','haritha2065@gmail.com','Nalandha',NULL,'$2a$10$paM01fw7tn87jUT0Ta3YuOFujJ.Wbf406JXAHn8NGe1GCtZMM5RZK',890789089,0,'haritha2065@gmail.com',2,0),
 (3,'2019-10-23 09:38:15','HU9kDIqd','haritha34@gmail.com','Narmadha',NULL,'$2a$10$nCNkim0/vp5LZTmE3QSfi.LsMYKBNqpEgb5gsf/bBZXfo/dpL3FUG',8790890890,0,'haritha34@gmail.com',3,7),
 (4,'2019-10-23 09:38:15','FwruHUUT','dsfs@gjjasd.com','zxcxzc','zdsfsd','$2a$10$mEb3OXo6.exfZdlx4IlexOMifHiIucPb5aa/JRGke2HSjmUngsGIu',8990988789,0,'dsfs@gjjasd.com',1,7),
 (5,'2019-10-23 09:38:15','pBQzCgdu','haritha@gmail.com','ramu','h','$2a$10$pNAMu0VTryUBdU2RurrcYelWuUJOR0d337rsB8TS21j7nm8eVtq0S',8989898989,0,'haritha@gmail.com',1,7),
 (6,'2019-10-23 09:38:15','RwhmmwgK','cdsf@gmail.com','zxcccccccc',NULL,'$2a$10$rEn7.pwn/s9fXi4dnohliuPBzt4LeBb7J/fyOC8HB0YEsJoHkBnzy',8908908909,0,'cdsf@gmail.com',5,5),
 (7,'2019-10-23 09:38:15','zfrb4Ox2','haritha2065@gmail.com','haritha','pall','$2a$10$tYfgdKCOQ9GG5eGRBMg70e7HTbfMDpuqii77J8K3CVKObecGnynXK',8074416723,0,'attar@gmail.com',3,6),
 (8,'2019-10-23 10:18:31','3sOvlRmS','jillan@gmail.com','abdula','Jillan','$2a$10$HQ11Gn3EpSR.bDR2kkT5Ae63G6o0F/RlnwJJmn1elEPsxa9OKT1sC',9089078909,0,'fsdfs0001',3,6);
/*!40000 ALTER TABLE `school_users` ENABLE KEYS */;


--
-- Definition of table `server_broke`
--

DROP TABLE IF EXISTS `server_broke`;
CREATE TABLE `server_broke` (
  `id` bigint(20) NOT NULL,
  `server_broke` int(10) DEFAULT NULL,
  `date_created` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `status` int(10) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `server_broke`
--

/*!40000 ALTER TABLE `server_broke` DISABLE KEYS */;
INSERT INTO `server_broke` (`id`,`server_broke`,`date_created`,`status`) VALUES 
 (1,0,'2019-11-01 15:12:58',0);
/*!40000 ALTER TABLE `server_broke` ENABLE KEYS */;


--
-- Definition of table `seven_sigma`
--

DROP TABLE IF EXISTS `seven_sigma`;
CREATE TABLE `seven_sigma` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `sigma_name` varchar(45) NOT NULL,
  `date_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `status` int(10) unsigned NOT NULL,
  `sigma_icon_name` varchar(45) DEFAULT NULL,
  `sigma_icon_path` varchar(45) DEFAULT NULL,
  `sigma_desc` varchar(200) NOT NULL,
  `icon_color_code` varchar(45) DEFAULT NULL,
  `type` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `seven_sigma`
--

/*!40000 ALTER TABLE `seven_sigma` DISABLE KEYS */;
INSERT INTO `seven_sigma` (`id`,`sigma_name`,`date_created`,`status`,`sigma_icon_name`,`sigma_icon_path`,`sigma_desc`,`icon_color_code`,`type`) VALUES 
 (1,'Culture Based Education','2019-10-05 13:02:51',0,'act1.jpg','/categoryImages/1','As culture shapes minds and the future of the younger generations','','image/jpeg'),
 (2,'Positive Parenting','2019-10-05 13:02:51',0,'demo.png','/categoryImages/2','Parenting is Fun and Tough. Positive parenting is intense and demanding. We dwell into numerous principles like Attachment, Respect, Empathy and Discipline.','','image/jpeg'),
 (3,'fdgfdg','2019-10-05 13:02:51',0,'act1.jpg','/categoryImages/3','sdffffffffffff','','image/jpeg'),
 (4,'1st sigma','2019-10-05 13:02:51',0,'4.png','/categoryImages/4','sigma description','#B22222','image/jpeg'),
 (5,'3rd sigma','2019-10-05 13:02:51',0,NULL,NULL,'xdcvvvvvvvvvvvvv',NULL,'image/jpeg'),
 (6,'7th sigma','2019-10-23 12:48:30',0,'Tulips.jpg','/sigmaImages/6','description','000000','image/jpeg'),
 (7,'sigma5','2019-11-27 11:58:22',0,'1.png','/sigmaImages/7','zcddddddsaas','asdas','image/png'),
 (8,'sigma6','2019-11-27 12:03:53',0,'2.png','/sigmaImages/8','dsadasd','asdas','image/png'),
 (9,'sigma0','2019-11-27 14:28:36',0,'3_Horticulturist.jpg','/sigmaImages/9','Dsadasd','asdasd','image/jpeg');
/*!40000 ALTER TABLE `seven_sigma` ENABLE KEYS */;


--
-- Definition of table `seven_sigma_details`
--

DROP TABLE IF EXISTS `seven_sigma_details`;
CREATE TABLE `seven_sigma_details` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `seven_sigma` bigint(20) unsigned NOT NULL,
  `date_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `status` int(10) unsigned NOT NULL,
  `author_name` varchar(45) DEFAULT NULL,
  `date_published` varchar(45) NOT NULL,
  `description` varchar(45) DEFAULT NULL,
  `type` varchar(45) DEFAULT NULL,
  `name` varchar(1000) DEFAULT NULL,
  `sigma_document_name` varchar(1000) DEFAULT NULL,
  `sigma_document_path` varchar(1000) DEFAULT NULL,
  `title` varchar(500) DEFAULT NULL,
  `video_type` int(10) unsigned DEFAULT '0',
  `youtube_url` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_seven_sigma_details_seven_sigma` (`seven_sigma`),
  CONSTRAINT `FK_seven_sigma_details_seven_sigma` FOREIGN KEY (`seven_sigma`) REFERENCES `seven_sigma` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `seven_sigma_details`
--

/*!40000 ALTER TABLE `seven_sigma_details` DISABLE KEYS */;
INSERT INTO `seven_sigma_details` (`id`,`seven_sigma`,`date_created`,`status`,`author_name`,`date_published`,`description`,`type`,`name`,`sigma_document_name`,`sigma_document_path`,`title`,`video_type`,`youtube_url`) VALUES 
 (3,1,'2019-11-04 13:19:46',0,'sfdsffffffffs','sfdsfffffff','','application/pdf','','','',NULL,0,NULL),
 (4,1,'2019-11-04 13:19:46',0,'dfdsfd','sdfsdfsd',NULL,'application/pdf',NULL,NULL,NULL,NULL,0,NULL),
 (5,2,'2019-11-04 13:19:46',0,'SAFDS','SDFDS',NULL,'application/audio',NULL,'act1.jpg','/subSigmaDocuments/5',NULL,0,NULL),
 (6,1,'2019-11-04 13:19:46',0,'dsfds','dsfds',NULL,'application/audio','picdoc.pdf','picdoc.pdf','/subSigmaDocuments/6',NULL,0,NULL),
 (7,1,'2019-11-04 13:19:46',0,'pdf author','10-09-18',NULL,'application/pdf','20190611175530.pdf','20190611175530.pdf','/subSigmaDocuments/7',NULL,0,NULL),
 (8,1,'2019-11-04 13:19:46',0,'VIDEO AUTHOR','10-09-1889',NULL,'application/ebook','','','/subSigmaDocuments/8',NULL,0,NULL),
 (9,1,'2019-11-04 13:19:46',0,'audio author','10-09-1887',NULL,'application/ebook','','','/subSigmaDocuments/9',NULL,0,NULL),
 (10,2,'2019-11-04 13:19:46',0,'asdas','sadasd',NULL,'application/pdf',NULL,NULL,NULL,NULL,0,NULL),
 (11,2,'2019-11-04 13:19:46',0,'asdas','sadasd',NULL,'video/mp4','What is dependency injection in spring [ Getting started ] - Spring framework tutorial for beginners.mp4','','/subSigmaDocuments/11',NULL,0,NULL),
 (12,1,'2019-11-04 13:19:46',0,'asd','dsfds',NULL,'video/mp4','What is dependency injection in spring [ Getting started ] - Spring framework tutorial for beginners.mp4','','/subSigmaDocuments/12',NULL,0,NULL),
 (13,1,'2019-11-04 13:19:46',0,'zzczx','xcxzcx',NULL,'video/mp4','What is dependency injection in spring [ Getting started ] - Spring framework tutorial for beginners.mp4','','/subSigmaDocuments/13',NULL,0,NULL),
 (14,1,'2019-11-04 13:19:46',0,'Zcxzc','sfsd',NULL,'application/ebook',NULL,NULL,NULL,NULL,0,NULL),
 (15,2,'2019-11-04 13:19:46',0,'zxvds','sdfsd',NULL,'video/mp4','What is dependency injection in spring [ Getting started ] - Spring framework tutorial for beginners.mp4','What is dependency injection in spring [ Getting started ] - Spring framework tutorial for beginners.mp4','/subSigmaDocuments/15',NULL,0,NULL),
 (16,1,'2019-11-04 13:19:46',0,'asdas','asdas',NULL,'video/mp4','Java Programming.mp4','Java Programming.mp4','/subSigmaDocuments/16',NULL,0,NULL),
 (17,1,'2019-11-04 13:19:46',0,'asfsdfds','sfdsf',NULL,'video/mp4','Java Programming (1).mp4','Java Programming (1).mp4','/subSigmaDocuments/17',NULL,0,NULL),
 (18,1,'2019-11-04 13:19:46',0,'safdsfzsfds','dsfds',NULL,'application/pdf','BIBLIOGRAPY.pdf','BIBLIOGRAPY.pdf','/subSigmaDocuments/18',NULL,0,NULL),
 (19,1,'2019-11-04 13:19:46',0,'sdfsdf','10-09-1998',NULL,'audio/mp3','SampleAudio_0.4mb.mp3','SampleAudio_0.4mb.mp3','/subSigmaDocuments/19',NULL,0,NULL),
 (20,1,'2019-11-04 13:19:46',0,'safd','dsf',NULL,'application/epub+zip','valentin-hauy.epub','valentin-hauy.epub','/subSigmaDocuments/20',NULL,0,NULL),
 (21,2,'2019-11-04 13:19:46',0,'zxcxz','2019-10-08','zxczxc','image/jpeg','Koala.jpg','Koala.jpg','/subSigmaDocuments/21',NULL,0,NULL),
 (22,6,'2019-11-04 13:19:46',0,'dsfds','2019-11-05','Sdsd','video/mp4','','','/subSigmaDocuments/22','title',1,'https://www.youtube.com/watch?v=On9mzziI2pU'),
 (23,6,'2019-11-04 13:19:46',0,'sdfsdaf','2019-11-20','sdfsdf','application/octet-stream','','','/subSigmaDocuments/23','sdfsda',1,'dsaffffff'),
 (24,1,'2019-11-04 13:19:46',0,'zc','2019-11-19','zxcz','video/mp4','','','/subSigmaDocuments/24','sdf',1,'https://www.youtube.com/watch?v=On9mzziI2pU'),
 (25,1,'2019-11-04 13:19:46',0,'xxxxxxxx','2019-11-21','xc','application/octet-stream','','','/subSigmaDocuments/25','xccccccccc',1,'https://www.youtube.com/watch?v=On9mzziI2pU'),
 (26,1,'2019-11-04 13:19:47',0,'dcvx','2019-10-29','dffffff','application/octet-stream','','','/subSigmaDocuments/26','title',1,'https://youtu.be/4PWVFBiFVVU'),
 (27,1,'2019-11-04 14:49:41',0,'werwer','2019-11-13','adsf','application/octet-stream','','','/subSigmaDocuments/27','ewr',1,'https://youtu.be/Y72CeUEn_Vc'),
 (28,1,'2019-11-04 22:27:52',0,'fs','2019-11-03','dvfd','video/mp4','17.mp4','17.mp4','/subSigmaDocuments/28','fsgs',0,''),
 (29,1,'2019-11-04 22:32:54',0,'zdfdsf','2019-11-04','sdfsf','video/mp4','17.mp4','17.mp4','/subSigmaDocuments/29','sdfsd',0,''),
 (30,1,'2019-11-04 22:33:51',0,'asdas','2019-11-04','asdasd','application/pdf','save.pdf','save.pdf','/subSigmaDocuments/30','dsfsdf',0,''),
 (31,1,'2019-11-04 22:34:45',0,'sdfsdf','2019-11-04','sfsfsdf','audio/mp3','audio.mp3','audio.mp3','/subSigmaDocuments/31','sdfsdf',0,''),
 (32,1,'2019-12-26 19:21:04',0,'SectorSeven','26-21-2019',NULL,'video/mp4',NULL,NULL,NULL,'What is Investment Banking (What do they actually do?)',0,'32fLCPcsffs'),
 (33,1,'2019-12-26 19:21:04',0,'SectorSeven','26-21-2019',NULL,'video/mp4',NULL,NULL,NULL,'Investment Banking: What do they do?',0,'MbMh6KRLz9U'),
 (34,1,'2019-12-26 19:21:04',0,'SectorSeven','26-21-2019',NULL,'video/mp4',NULL,NULL,NULL,'Day in the Life of an Investment Banker I Manisha Girotra I Success Stories I ChetChat',0,'MQtVmxQbvXo'),
 (35,1,'2019-12-26 19:21:04',0,'SectorSeven','26-21-2019',NULL,'video/mp4',NULL,NULL,NULL,'Everything you need to know about CFA Program!',0,'Z1fGHLKA2zk'),
 (36,1,'2019-12-26 19:21:04',0,'SectorSeven','26-21-2019',NULL,'video/mp4',NULL,NULL,NULL,'How to become an Investment banker',0,'if75PRKQHWQ'),
 (37,1,'2019-12-26 19:21:04',0,'SectorSeven','26-21-2019',NULL,'video/mp4',NULL,NULL,NULL,'How to Become Investment Banker in INDIA |Complete Procedure| Highest Paying Jobs [HINDI/URDU]',0,'qAKODfJZV9M'),
 (38,1,'2019-12-26 19:21:04',0,'SectorSeven','26-21-2019',NULL,'video/mp4',NULL,NULL,NULL,'What is Investment Banking? What do Investment Bankers Do?',0,'WdvC9xUfuyc'),
 (39,1,'2019-12-26 19:21:04',0,'SectorSeven','26-21-2019',NULL,'video/mp4',NULL,NULL,NULL,'Investment Banking Salary (The ACTUAL First Year Analyst SALARY Explained!)',0,'ZX6dz61s-po'),
 (40,1,'2019-12-26 19:21:04',0,'SectorSeven','26-21-2019',NULL,'video/mp4',NULL,NULL,NULL,'What is Investment Banking | Departments to Job Roles and Responsibilities',0,'CfzeEz826VM'),
 (41,1,'2019-12-26 19:21:04',0,'SectorSeven','26-21-2019',NULL,'video/mp4',NULL,NULL,NULL,'Suits - Investment bankers',0,'_s42gOg2WSU'),
 (42,1,'2019-12-26 19:21:04',0,'SectorSeven','26-21-2019',NULL,'video/mp4',NULL,NULL,NULL,'How to Become an Investment Banker? CareerBuilder Videos from funza Academy',0,'3dZRmgLq8bU'),
 (43,1,'2019-12-26 19:21:04',0,'SectorSeven','26-21-2019',NULL,'video/mp4',NULL,NULL,NULL,'How to get into investment banking',0,'kLFItf290Fc'),
 (44,1,'2019-12-26 19:21:04',0,'SectorSeven','26-21-2019',NULL,'video/mp4',NULL,NULL,NULL,'All You Should Know About Investment banking Courses - Skills Required, Job Prospects',0,'fdTX1nUapTY'),
 (45,1,'2019-12-26 19:21:04',0,'SectorSeven','26-21-2019',NULL,'video/mp4',NULL,NULL,NULL,'What is Investment Banking?',0,'QCQyB0FBJjY'),
 (46,1,'2019-12-26 19:21:04',0,'SectorSeven','26-21-2019',NULL,'video/mp4',NULL,NULL,NULL,'Why pursue a career in investment banking?',0,'tPYlvMERJmY'),
 (47,1,'2019-12-26 19:21:04',0,'SectorSeven','26-21-2019',NULL,'video/mp4',NULL,NULL,NULL,'What is Investment Banking in HINDI || Investment Banker ?| Highest Paying Jobs in India | World',0,'14ffsxVCRxs'),
 (48,1,'2019-12-26 19:21:04',0,'SectorSeven','26-21-2019',NULL,'video/mp4',NULL,NULL,NULL,'Investment Banking Career Tips - What does an Investment Banking Analyst Do?',0,'8yvK31KNvAc'),
 (49,1,'2019-12-26 19:21:04',0,'SectorSeven','26-21-2019',NULL,'video/mp4',NULL,NULL,NULL,'Investment Banker reveals secrets of his profession',0,'7wYva9dhelE'),
 (50,1,'2019-12-26 19:21:04',0,'SectorSeven','26-21-2019',NULL,'video/mp4',NULL,NULL,NULL,'Investment Banking: Required Skills for Success',0,'R8aDgJlHB3Q');
/*!40000 ALTER TABLE `seven_sigma_details` ENABLE KEYS */;


--
-- Definition of table `skills`
--

DROP TABLE IF EXISTS `skills`;
CREATE TABLE `skills` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `skill` varchar(2000) NOT NULL,
  `subcat_id` bigint(10) unsigned NOT NULL,
  `date_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `status` int(10) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_skills_subcat` (`subcat_id`),
  CONSTRAINT `FK_skills_subcat` FOREIGN KEY (`subcat_id`) REFERENCES `career_subcategory` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `skills`
--

/*!40000 ALTER TABLE `skills` DISABLE KEYS */;
INSERT INTO `skills` (`id`,`skill`,`subcat_id`,`date_created`,`status`) VALUES 
 (1,'asasfsa',9,'2019-10-25 17:57:17',0),
 (2,'adadaf',9,'2019-10-25 17:57:17',0),
 (3,'afafsa',9,'2019-10-25 17:57:17',0),
 (4,'dsf',13,'2019-10-28 13:38:53',0),
 (5,'adsa',16,'2019-11-04 20:48:50',0),
 (6,'dfh',16,'2019-11-04 20:48:50',0),
 (7,'dfd',16,'2019-11-04 20:48:50',0),
 (8,'asdsa',17,'2019-11-05 12:45:05',0),
 (9,'asd',17,'2019-11-05 12:45:06',0),
 (10,'asd',17,'2019-11-05 12:45:06',0),
 (11,'Critical Thinker- Using Logic and Reasoning to find strengths and weaknesses.',30,'2019-12-26 19:17:18',0),
 (12,'Being Judgment and Decision making while considering the costs and benefits of an action.\n',30,'2019-12-26 19:17:18',0),
 (13,'Monitoring the activities closely.',30,'2019-12-26 19:17:18',0),
 (14,'Actively listening and learning.\n',30,'2019-12-26 19:17:18',0),
 (15,'Being optimistic and striving for a better change.',30,'2019-12-26 19:17:18',0),
 (16,'Critical Thinker- Using Logic and Reasoning to find strengths and weaknesses.',31,'2019-12-26 19:53:02',0),
 (17,'Being Judgment and Decision making while considering the costs and benefits of an action.\n',31,'2019-12-26 19:53:02',0),
 (18,'Monitoring the activities closely.',31,'2019-12-26 19:53:02',0),
 (19,'Actively listening and learning.\n',31,'2019-12-26 19:53:02',0),
 (20,'Being optimistic and striving for a better change.',31,'2019-12-26 19:53:02',0);
/*!40000 ALTER TABLE `skills` ENABLE KEYS */;


--
-- Definition of table `student`
--

DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `authority` varchar(255) DEFAULT NULL,
  `date_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `date_of_birth` varchar(255) DEFAULT NULL,
  `decrypt_password` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `gender` int(11) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `mobile` bigint(20) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `section` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `class` varchar(255) DEFAULT NULL,
  `user_name` varchar(255) DEFAULT NULL,
  `academic_year` bigint(20) DEFAULT NULL,
  `parent` bigint(20) DEFAULT NULL,
  `school` bigint(20) DEFAULT NULL,
  `hobbies` varchar(1000) DEFAULT NULL,
  `school_teacher` bigint(20) DEFAULT NULL,
  `description` varchar(1000) DEFAULT NULL,
  `student_stream` int(10) DEFAULT NULL,
  `student_img_name` varchar(45) DEFAULT NULL,
  `student_img_path` varchar(45) DEFAULT NULL,
  `type` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_qvswv7u4u4wxb0uqksvq0qrhc` (`academic_year`),
  KEY `FK_lhsdoli0moyu0hcl09paddtr3` (`parent`),
  KEY `FK_8fkfaabgxj2qoc6njcrpf8vf8` (`school`),
  KEY `FK_student_school_teacher555` (`school_teacher`),
  CONSTRAINT `FK_8fkfaabgxj2qoc6njcrpf8vf8` FOREIGN KEY (`school`) REFERENCES `school` (`id`),
  CONSTRAINT `FK_lhsdoli0moyu0hcl09paddtr3` FOREIGN KEY (`parent`) REFERENCES `parents` (`id`),
  CONSTRAINT `FK_qvswv7u4u4wxb0uqksvq0qrhc` FOREIGN KEY (`academic_year`) REFERENCES `academic_year` (`id`),
  CONSTRAINT `FK_student_school_teacher555` FOREIGN KEY (`school_teacher`) REFERENCES `school_teacher` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `student`
--

/*!40000 ALTER TABLE `student` DISABLE KEYS */;
INSERT INTO `student` (`id`,`authority`,`date_created`,`date_of_birth`,`decrypt_password`,`email`,`first_name`,`gender`,`last_name`,`mobile`,`password`,`section`,`status`,`class`,`user_name`,`academic_year`,`parent`,`school`,`hobbies`,`school_teacher`,`description`,`student_stream`,`student_img_name`,`student_img_path`,`type`) VALUES 
 (4,'ROLE_STUDENT','2019-10-07 15:23:50','1996-06-09 00:00:00','1&k^M%01','anno@gmail.com','xc',1,'xzcxzczx',9874587458,'$2a$10$jRHP15ey/vjL9YzMVUMZ..WPqh7zlV2CEvpPBpwrmdKKYZbfwa7SW',NULL,0,'2nd class','00519000001',NULL,3,1,'Indie music, skiing and hiking. I love the great outdoors.',1,'zxxxxxxxxxxxxx',0,NULL,NULL,NULL),
 (5,'ROLE_STUDENT','2019-08-17 10:14:30','1778-08-10 00:00:00','9v#UXpS7',NULL,'nalini',1,'p',0,'$2a$10$AEFI0Mq.vpUIITU6yOjW5O2UDSdNDUd6uKk.6SCbGJ5jhKX6MiOJK',NULL,0,'8 th class','SC19000004',NULL,3,1,NULL,NULL,'',0,'','',NULL),
 (6,'ROLE_STUDENT','2019-08-17 10:14:30','1996-06-09 00:00:00','%WhOM5oz',NULL,'zds',1,'sfdds',0,'$2a$10$XpalLhwbjc8FWllRHKSvouhNu/zfScEHfRMl59GgXhn5F8h5lSbDW',NULL,0,'sadsa','SC19000005',NULL,5,1,NULL,NULL,'',0,'','',NULL),
 (7,'ROLE_STUDENT','2019-08-17 10:14:30','0778-08-10 00:00:00','xnwnw/qN',NULL,'fdsfdssfsdf',0,'dfdsf',0,'$2a$10$kdTUTbp/MZ1jaJFCBZTX2O4.I0Ay9zDHgqvagSg3MnYSm2NYPPt22',NULL,0,'ASAD','SC19000006',NULL,6,1,NULL,NULL,'',0,'','',NULL),
 (8,'ROLE_STUDENT','2019-08-17 10:14:30','2019-09-10 00:00:00','Y7GANK^Z',NULL,'ramu',1,'h',0,'$2a$10$TIpJNAS3IuP9oqYHjSwuPuSHN4GjR5fRqSDG6fCkZSx0AShjCkl1C',NULL,0,'6th class','SC19000007',NULL,3,1,NULL,NULL,'',0,'','',NULL),
 (9,'ROLE_STUDENT','2019-08-17 10:14:30','1998-09-10 00:00:00','aN/UU%d0',NULL,'dsfsdf',1,'sdfsdf',0,'$2a$10$lwh.AA.BwlbYwm2NY9MZ4OM/qmw2exO19k9wi.fBipNqPYtXrUoyK',NULL,0,'6th class','SC19000008',NULL,7,1,NULL,NULL,'',0,'','',NULL),
 (10,'ROLE_STUDENT','2019-08-17 10:14:30','0007-03-12 00:00:00','$PKgZnTs',NULL,'ramu',1,'h',0,'$2a$10$RD8knCHljeaNjZDGS50XWOpVpk5EvR1.GQTxjuWl7zaJg52ldkNM2',NULL,0,'madhapur','SC19000011',NULL,7,1,NULL,1,'sdsadsd',0,'','',NULL),
 (11,'ROLE_STUDENT','2019-08-17 10:14:30','0024-03-11 00:00:00','Q9&y9l+6',NULL,'ramu',1,'h',0,'$2a$10$J1LE.X8GPWlcM8BQINtos.vNdL4OwPnQyGKPYZLnG8FNefj7wFok6',NULL,0,'madhapur','SC19000013',NULL,5,1,NULL,1,'',0,'','',NULL),
 (12,'ROLE_STUDENT','2019-08-17 10:14:30','0024-03-11 00:00:00','TYbXzSIt',NULL,'zds',1,'sfdds',0,'$2a$10$WjIjxK9xY/7R2ACYRk/sduNzz.xJDTZPsdybWqfHAGpcLroR1cnP2',NULL,0,'asd','SC19000014',NULL,7,1,NULL,2,'asfdsf',0,'','',NULL),
 (13,'ROLE_STUDENT','2019-09-20 15:40:37','0024-03-11 00:00:00','d7kh@lwf','haritha@gmail.com','ramu',0,'h',0,'$2a$10$Nl1fce1FYoJZaB/3h5mWWuFBPPQGOI1V5QzIhfbvufnTReQXjOdLu',NULL,0,'madhapur','SC19000017',NULL,12,1,'cxzcxzc,xzczxc',2,'ZXczc',0,'','',NULL),
 (14,'ROLE_STUDENT','2019-09-20 15:40:42','0016-03-11 00:00:00','$6Xh%asI','haritha@gmail.com','ramu',1,'h',0,'$2a$10$hXL3tDXIm2Q5K2r90W3eoOmtpk.a.RVc.rRm9hwQ7BuFUJ3D8ErCy',NULL,0,'madhapur','SC19000018',NULL,13,1,'sdasdasd,dsfdsfd',2,'sdasdasd',0,'','',NULL),
 (15,'ROLE_STUDENT','2019-09-20 15:43:42','0015-07-11 00:00:00','g!TNRf5#','sdfsdf@gmail.com','nalini',1,'ram',0,'$2a$10$J33CjJ.xAd8a7yDchM/FtuENuq56HoP8VaOjoR6rfTdRTnLfgNS1C',NULL,0,'srnagar','SC19000019',NULL,14,1,'sdfsdfd,sdfsdf,dgfdgfd,dfddfsg',2,'sdfsdf',1,'','',NULL),
 (16,'ROLE_STUDENT','2019-10-11 14:16:29','2019-09-10 00:00:00','123456','haritha2065@gmail.com','hari',0,'BCDE',9090909089,'$2a$10$IheBd3xb4n1aw4a5xaXuSu/ng0OvTxll5jI.OhEbV0pi1hVy1MWtC',NULL,0,'zxc','SC19000025',1,10,1,'xzccccccccccc,zxczxcsad',1,'hiiii',0,'investmentnbanker.jpg','/studentImages/16','image/jpeg'),
 (17,'ROLE_STUDENT','2019-10-23 11:37:06','2019-07-16','HvhPLO+#','sdfsdf','sffs',0,'dsfsdf',0,'$2a$10$zF6G1OPWhesj57L1LkzX7O1UNFbf04c7R60l/nj7mQEbiCuz7Q0rW','sdfsdf',0,'dsfsdf','SC19000026',1,4,1,'dsfdsfsdf',1,'sdfsd',0,NULL,NULL,NULL),
 (18,'ROLE_STUDENT','2019-12-12 17:29:05',NULL,'Ob*UkVMh',NULL,NULL,NULL,NULL,0,'$2a$10$JsZCRUVGE.O9BtNCiPWDSeeoh8LeVCsz3MhwUn34j6hiVM1MwCq/a',NULL,0,NULL,'SC19000027',NULL,NULL,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL),
 (19,'ROLE_STUDENT','2019-12-12 17:29:05',NULL,'BT5XW1p!',NULL,'sdf',NULL,NULL,0,'$2a$10$6jXDcLVjnDd1fiT9hTf9FuSGn1NUNc/gGNEJA3gYRYgNQ7HvzRuL2',NULL,0,NULL,'SC19000028',NULL,NULL,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL),
 (20,'ROLE_STUDENT','2019-12-12 17:29:05',NULL,'15m97d=B',NULL,NULL,NULL,'sdf',0,'$2a$10$UW/QqjASbWyN0RjTa95LH..TjWVf6UpXguGJz/uLsDXsLvcMYdJiy',NULL,0,NULL,'SC19000029',NULL,NULL,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL),
 (21,'ROLE_STUDENT','2019-12-12 17:37:51',NULL,'xbWbUfcB',NULL,NULL,NULL,NULL,0,'$2a$10$9eeX8kn2Vz9PFRurTyOvd..0bI9Ih0Dy62FGbjVHM7.oj/tzZrtdm',NULL,0,NULL,'ADR56719000002',NULL,NULL,2,NULL,NULL,NULL,NULL,NULL,NULL,NULL),
 (22,'ROLE_STUDENT','2019-12-12 17:37:51',NULL,'IPs5dZhx',NULL,'sdf',NULL,NULL,0,'$2a$10$/gydTDzkBkDrBUrkB0CfluUDFJE9cRovcU6xNn8b2Fo5akSYTSvoW',NULL,0,NULL,'ADR56719000003',NULL,NULL,2,NULL,NULL,NULL,NULL,NULL,NULL,NULL),
 (23,'ROLE_STUDENT','2019-12-12 17:37:51',NULL,'v5tJwY9L',NULL,NULL,NULL,'sdf',0,'$2a$10$eTnHousFE2RztghvvZopY.m4DYJNJj2VsM0pIcHaJvG1NC/X.ktXe',NULL,0,NULL,'ADR56719000004',NULL,NULL,2,NULL,NULL,NULL,NULL,NULL,NULL,NULL),
 (24,'ROLE_STUDENT','2019-12-21 11:45:57','2019-12-10','mxUMfMsx','hari@gmail.com','balaji',0,'',0,'$2a$10$ST1DnYwwEZTzxp.aQoSv/u5PIuUIfcL/LXmgjwCVD96nDrxL9zZ6G','A section',0,'4 th','SC19000030',1,22,1,'playing,watching',2,'',1,NULL,NULL,NULL),
 (25,'ROLE_STUDENT','2019-08-17 10:14:30','2020-12-05 00:00:00','Ln$Vul=3','haritha345@gmail.com','ramu',0,'h',9097890877,'$2a$10$Cw/yuqr9GRf5SDQyPsuVJu72b46lS5VkMPJqmHUFiDv5VChwOgT3y',NULL,0,'10','SC19000001',NULL,1,1,'Indie music.',NULL,'',0,'','',''),
 (26,'ROLE_STUDENT','2019-08-17 10:14:30','1996-06-09 00:00:00','Oh=^Jtfz',NULL,'haritha',0,'palle',0,'$2a$10$d.ayMYPmo13AJbgfSSg.DeI9AvJwuedVAEaWZK7lx60RTVuPHsVZq',NULL,0,'4th class','SC19000002',NULL,2,1,'Indie music, skiing and hiking. ',NULL,'',0,'','',NULL),
 (27,'ROLE_STUDENT','2019-08-17 10:14:30','1996-06-09 00:00:00','1&k^M%01',NULL,'Ramu',0,'Ravi',0,'$2a$10$jRHP15ey/vjL9YzMVUMZ..WPqh7zlV2CEvpPBpwrmdKKYZbfwa7SW',NULL,0,'4th class','SC19000003',NULL,3,1,'Indie music, skiing and hiking. I love the great outdoors.',NULL,'',0,'','',NULL),
 (28,'ROLE_STUDENT','2019-12-26 17:50:15','20-02-2000','TZU+=k2J','mahesh@gmail.com','Mahesh',0,'Macharla',9874521458,'$2a$10$Cj0Twhk/0HSnX35Cip4U.uzssFQBUoHbX5Gz/beSW8GFBUDMBfGca','A',0,'XI','ADR56719000008',1,26,2,NULL,2,'This is my profile',0,NULL,NULL,NULL),
 (29,'ROLE_STUDENT','2019-12-26 17:50:15','20-02-2001','x_JHB6@o','keerthi@gmail.com','Keerthi',1,'Keerthi',9874587451,'$2a$10$1pDhWwSE6ZZbCf11XHnhPOg5tXPEbrrU3ztomRxEYqdOKbHehJlsK','A+',0,'IV','ADR56719000009',1,27,2,NULL,2,'Keerthi is inti',1,NULL,NULL,NULL),
 (30,'ROLE_STUDENT','2019-12-26 17:50:16','20-02-2002','HEAmyE4-','pratap@gmail.com','Kalyan',0,'Karra',9874521458,'$2a$10$f2r1uBJwx8Ie88Fb6lmrE.vmOIlzgBsLBsEzv0xr.9FKL0Cn8YzWa','A++',0,'X','ADR56719000010',1,28,2,NULL,2,'This is my profile',0,NULL,NULL,NULL),
 (31,'ROLE_STUDENT','2019-12-26 17:53:45','20-02-2000','G2td/V79','mahesh@gmail.com','LeelaMohan',0,'Lakshmi',9874521458,'$2a$10$prIvgr.H.qHzvZ50oOfb0.E1LI4ACQ7dPrQ8QLV/wn7dsh.zH8FxG','A',0,'XI','ADR56719000011',1,29,2,NULL,2,'This is my profile',0,NULL,NULL,NULL);
/*!40000 ALTER TABLE `student` ENABLE KEYS */;


--
-- Definition of table `student_queries`
--

DROP TABLE IF EXISTS `student_queries`;
CREATE TABLE `student_queries` (
  `id` bigint(10) unsigned NOT NULL AUTO_INCREMENT,
  `question` varchar(200) NOT NULL DEFAULT '',
  `attachment_name` varchar(45) DEFAULT NULL,
  `attachment_path` varchar(45) DEFAULT NULL,
  `mentor` bigint(10) DEFAULT NULL,
  `status` int(10) unsigned DEFAULT NULL,
  `student` bigint(20) NOT NULL DEFAULT '0',
  `description` varchar(500) NOT NULL DEFAULT '',
  `date_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `type` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_student_queries_student` (`student`),
  KEY `FK_student_queries_mentor` (`mentor`),
  CONSTRAINT `FK_student_queries_mentor` FOREIGN KEY (`mentor`) REFERENCES `mentor` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_student_queries_student` FOREIGN KEY (`student`) REFERENCES `student` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `student_queries`
--

/*!40000 ALTER TABLE `student_queries` DISABLE KEYS */;
/*!40000 ALTER TABLE `student_queries` ENABLE KEYS */;


--
-- Definition of table `sub_category_details`
--

DROP TABLE IF EXISTS `sub_category_details`;
CREATE TABLE `sub_category_details` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `video_name` varchar(1000) DEFAULT NULL,
  `date_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `status` int(11) NOT NULL,
  `career_subcat` bigint(20) unsigned NOT NULL,
  `author_name` varchar(50) DEFAULT NULL,
  `date_published` varchar(45) NOT NULL,
  `sub_category_document_path` varchar(200) DEFAULT NULL,
  `sub_category_document_name` varchar(250) DEFAULT NULL,
  `video_path` varchar(1000) DEFAULT NULL,
  `type` varchar(45) DEFAULT NULL,
  `landing_img_name` varchar(45) DEFAULT NULL,
  `landing_img_path` varchar(45) DEFAULT NULL,
  `side_img_name` varchar(45) DEFAULT NULL,
  `side_img_path` varchar(45) DEFAULT NULL,
  `interests` varchar(45) DEFAULT NULL,
  `description` varchar(45) DEFAULT NULL,
  `title` varchar(500) DEFAULT NULL,
  `video_type` int(10) unsigned DEFAULT '0',
  `youtube_url` varchar(500) DEFAULT NULL,
  `tags` varchar(45) DEFAULT NULL,
  `thumbnail_url` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_sub_category_details_subcat` (`career_subcat`),
  CONSTRAINT `FK_sub_category_details_subcat` FOREIGN KEY (`career_subcat`) REFERENCES `career_subcategory` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=87 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `sub_category_details`
--

/*!40000 ALTER TABLE `sub_category_details` DISABLE KEYS */;
INSERT INTO `sub_category_details` (`id`,`video_name`,`date_created`,`status`,`career_subcat`,`author_name`,`date_published`,`sub_category_document_path`,`sub_category_document_name`,`video_path`,`type`,`landing_img_name`,`landing_img_path`,`side_img_name`,`side_img_path`,`interests`,`description`,`title`,`video_type`,`youtube_url`,`tags`,`thumbnail_url`) VALUES 
 (12,NULL,'2019-11-04 13:20:58',0,1,'asdsad','asdsad','/subCategoryDocuments/12','Java Programming.mp4',NULL,'video/mp4',NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,NULL,NULL,NULL),
 (13,NULL,'2019-11-04 13:20:58',0,1,'asdas','ascas','/subCategoryDocuments/13','Java Programming.mp4',NULL,'video/mp4',NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,NULL,NULL,NULL),
 (14,NULL,'2019-11-04 13:20:58',0,1,'asdad','asdasd','/subCategoryDocuments/14','17.mp4',NULL,'video/mp4','layer.png','/subCategoryLanding/14','actor.jpg','/subCategorySide/14',NULL,NULL,NULL,0,NULL,NULL,NULL),
 (15,NULL,'2019-11-26 16:00:30',0,2,'xzc','10-09-1090',NULL,NULL,NULL,'video/mp4',NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,NULL,NULL,NULL),
 (16,NULL,'2019-11-26 16:00:30',0,2,'asdddddddddddddddddddd','10-09-1990','/subCategoryDocuments/16','What is dependency injection in spring [ Getting started ] - Spring framework tutorial for beginners (1).mp4',NULL,'video/mp4',NULL,NULL,'2.png','/subCategorySide/16',NULL,NULL,NULL,0,NULL,NULL,NULL),
 (17,NULL,'2019-11-04 13:20:58',0,1,'adssssssssss','10-09-1990','/subCategoryDocuments/17','What is dependency injection in spring [ Getting started ] - Spring framework tutorial for beginners (1).mp4',NULL,'video/mp4',NULL,NULL,'2.png','/subCategorySide/17',NULL,NULL,NULL,0,NULL,NULL,NULL),
 (18,NULL,'2019-11-04 13:20:58',0,1,'asdsad','10-09-1990','/subCategoryDocuments/18','What is dependency injection in spring [ Getting started ] - Spring framework tutorial for beginners (1).mp4',NULL,'video/mp4',NULL,NULL,'2.png','/subCategorySide/18',NULL,NULL,NULL,0,NULL,NULL,NULL),
 (19,NULL,'2019-11-04 13:20:58',0,1,'zxcxvc','10-09-1998','/subCategoryDocuments/19','What is dependency injection in spring [ Getting started ] - Spring framework tutorial for beginners.mp4',NULL,'video/mp4',NULL,NULL,'2.png','/subCategorySide/19',NULL,NULL,NULL,0,NULL,NULL,NULL),
 (20,NULL,'2019-11-04 13:20:58',0,1,'ZXxzc','10-09-1997','/subCategoryDocuments/20','17.mp4',NULL,NULL,NULL,NULL,'4.png','/subCategorySide/20','watchingtv',NULL,NULL,0,NULL,NULL,NULL),
 (21,NULL,'2019-11-04 13:20:58',0,13,'asdsa','sadas','/subCategoryDocuments/21','',NULL,'video/mp4',NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,'https://www.youtube.com/watch?v=gfyQkdunrLs&t=224s',NULL,NULL),
 (22,NULL,'2019-11-04 21:16:46',0,16,'zdsad','10-09-1998','/subCategoryDocuments/22','',NULL,'application/octet-stream',NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,'https://www.youtube.com/watch?v=gfyQkdunrLs&t=224s',NULL,NULL),
 (23,NULL,'2019-11-04 21:22:09',0,16,'author1','10-09-1998','/subCategoryDocuments/23','',NULL,'application/octet-stream',NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,'fyQkdunrLs&t=224s',NULL,NULL),
 (24,NULL,'2019-11-04 21:24:49',0,16,'author1','10-09-1998','/subCategoryDocuments/24','17.mp4',NULL,'video/mp4',NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,'',NULL,NULL),
 (25,NULL,'2019-11-04 21:42:10',0,16,'author','10-09-1889','/subCategoryDocuments/25','17.mp4',NULL,'video/mp4',NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,'',NULL,NULL),
 (26,NULL,'2019-11-04 21:44:08',0,16,'author2','10-09-1990','/subCategoryDocuments/26','video.mp4',NULL,'video/mp4',NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,'',NULL,NULL),
 (27,NULL,'2019-11-04 21:44:58',0,16,'author3','10-09-1778','/subCategoryDocuments/27','save.pdf',NULL,'application/pdf',NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,'',NULL,NULL),
 (28,NULL,'2019-11-06 12:38:26',0,16,'author3','10-09-1889','/subCategoryDocuments/28','save.pdf',NULL,'application/pdf',NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,'','sdsdf',NULL),
 (29,NULL,'2019-11-06 12:38:26',0,16,'author1','10-09-2019','/subCategoryDocuments/29','17.mp4',NULL,'video/mp4',NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,'','sdsdf',NULL),
 (30,NULL,'2019-11-06 12:38:26',0,16,'author1','10-08-2019','/subCategoryDocuments/30','save.pdf',NULL,'application/pdf',NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,'','sdsdf',NULL),
 (31,NULL,'2019-11-06 12:38:26',0,16,'sdasd','wadqw','/subCategoryDocuments/31','save.pdf',NULL,'application/pdf',NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,'','sdsdf',NULL),
 (32,NULL,'2019-11-06 12:38:26',0,16,'sdasd','wadqw','/subCategoryDocuments/32','save.pdf',NULL,'application/pdf',NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,'','sdsdf',NULL),
 (33,NULL,'2019-11-06 12:38:26',0,16,'asda','asd','/subCategoryDocuments/33','save.pdf',NULL,'application/pdf',NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,'','sdsdf',NULL),
 (34,NULL,'2019-11-06 12:38:26',0,16,'asdasd','asdsad','/subCategoryDocuments/34','17.mp4',NULL,'video/mp4',NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,'','sdsdf',NULL),
 (35,NULL,'2019-11-06 12:38:26',0,16,'author2','10-09-2018','/subCategoryDocuments/35','audio.mp3',NULL,'audio/mp3',NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,'','Blockchain Developer',NULL),
 (36,NULL,'2019-11-06 12:38:26',0,16,'awewewqe','10-09-2018','/subCategoryDocuments/36','17.mp4',NULL,'video/mp4',NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,'','Blockchain Developer',NULL),
 (37,NULL,'2019-11-06 12:38:26',0,16,'aSas','10-09-2017','/subCategoryDocuments/37','save.pdf',NULL,'application/pdf',NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,'','Blockchain Developer',NULL),
 (38,NULL,'2019-11-06 12:38:26',0,16,'asdsad','10-09-2017','/subCategoryDocuments/38','',NULL,'application/octet-stream',NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,'gfyQkdunrLs&t=224s','Blockchain Developer',NULL),
 (39,NULL,'2019-11-06 12:38:26',0,17,'asda','asdas','/subCategoryDocuments/39','Java Programming.mp4',NULL,'video/mp4',NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,'','Blockchain Developer',NULL),
 (40,NULL,'2019-11-06 12:38:26',0,17,'zxczc','zsfdad','/subCategoryDocuments/40','audio.mp3',NULL,'audio/mp3',NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,'','Blockchain Developer',NULL),
 (41,NULL,'2019-11-06 12:38:26',0,17,'sdf','dfsdf','/subCategoryDocuments/41','save.pdf',NULL,'application/pdf',NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,'','Blockchain Developer',NULL),
 (42,NULL,'2019-11-09 11:16:19',0,4,' WebDriver Basics Tutorial ','10-09-1990','/subCategoryDocuments/42','',NULL,'application/octet-stream',NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,'f91oemYNu0E',NULL,NULL),
 (43,NULL,'2019-11-09 11:18:14',0,4,'Programming in Visual Basic ','12-09-1008','/subCategoryDocuments/43','',NULL,'application/octet-stream',NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,'cwDqjmSmtMQ',NULL,NULL),
 (44,NULL,'2019-11-09 11:19:37',0,4,'dfdsfsdf','19-09-1889','/subCategoryDocuments/44','',NULL,'application/octet-stream',NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,'Y4pzMHe_gp0',NULL,NULL),
 (45,NULL,'2019-12-04 12:03:37',0,17,'zdssf','sf','/subCategoryDocuments/45','',NULL,'video/mp4',NULL,NULL,NULL,NULL,NULL,'asfsaf','asfasf',1,'https://www.youtube.com/watch?v=gfyQkdunrLs&t=224s',NULL,NULL),
 (46,NULL,'2019-12-04 12:05:06',0,17,'asd','asd','/subCategoryDocuments/46','',NULL,'video/mp4',NULL,NULL,NULL,NULL,NULL,'asdsa','asdas',1,'https://www.youtube.com/watch?v=gfyQkdunrLs&t=224s',NULL,NULL),
 (47,NULL,'2019-12-04 12:10:12',0,16,'asd','safsaf','/subCategoryDocuments/47','',NULL,'video/mp4',NULL,NULL,NULL,NULL,NULL,'sdfdsg','afdsf',1,'',NULL,NULL),
 (48,NULL,'2019-12-04 12:13:27',0,16,'sa','sdfsd',NULL,NULL,NULL,'video/mp4',NULL,NULL,NULL,NULL,NULL,'sdfsd','sdf',1,'ps://',NULL,NULL),
 (49,NULL,'2019-12-26 19:22:49',0,30,'SectorSeven','26-22-2019',NULL,NULL,NULL,'video/mp4',NULL,NULL,NULL,NULL,NULL,NULL,'What is Investment Banking (What do they actually do?)',1,'32fLCPcsffs','Blockchain Developer','http://img.youtube.com/vi/32fLCPcsffs/0.jpg'),
 (50,NULL,'2019-12-26 19:22:49',0,30,'SectorSeven','26-22-2019',NULL,NULL,NULL,'video/mp4',NULL,NULL,NULL,NULL,NULL,NULL,'Investment Banking: What do they do?',1,'MbMh6KRLz9U','Blockchain Developer','http://img.youtube.com/vi/MbMh6KRLz9U/0.jpg'),
 (51,NULL,'2019-12-26 19:22:49',0,30,'SectorSeven','26-22-2019',NULL,NULL,NULL,'video/mp4',NULL,NULL,NULL,NULL,NULL,NULL,'Day in the Life of an Investment Banker I Manisha Girotra I Success Stories I ChetChat',1,'MQtVmxQbvXo','Blockchain Developer','http://img.youtube.com/vi/MQtVmxQbvXo/0.jpg'),
 (52,NULL,'2019-12-26 19:22:49',0,30,'SectorSeven','26-22-2019',NULL,NULL,NULL,'video/mp4',NULL,NULL,NULL,NULL,NULL,NULL,'Everything you need to know about CFA Program!',1,'Z1fGHLKA2zk','Blockchain Developer','http://img.youtube.com/vi/Z1fGHLKA2zk/0.jpg'),
 (53,NULL,'2019-12-26 19:22:49',0,30,'SectorSeven','26-22-2019',NULL,NULL,NULL,'video/mp4',NULL,NULL,NULL,NULL,NULL,NULL,'How to become an Investment banker',1,'if75PRKQHWQ','Blockchain Developer','http://img.youtube.com/vi/if75PRKQHWQ/0.jpg'),
 (54,NULL,'2019-12-26 19:22:49',0,30,'SectorSeven','26-22-2019',NULL,NULL,NULL,'video/mp4',NULL,NULL,NULL,NULL,NULL,NULL,'How to Become Investment Banker in INDIA |Complete Procedure| Highest Paying Jobs [HINDI/URDU]',1,'qAKODfJZV9M','Blockchain Developer','http://img.youtube.com/vi/qAKODfJZV9M/0.jpg'),
 (55,NULL,'2019-12-26 19:22:49',0,30,'SectorSeven','26-22-2019',NULL,NULL,NULL,'video/mp4',NULL,NULL,NULL,NULL,NULL,NULL,'What is Investment Banking? What do Investment Bankers Do?',1,'WdvC9xUfuyc','Blockchain Developer','http://img.youtube.com/vi/WdvC9xUfuyc/0.jpg'),
 (56,NULL,'2019-12-26 19:22:49',0,30,'SectorSeven','26-22-2019',NULL,NULL,NULL,'video/mp4',NULL,NULL,NULL,NULL,NULL,NULL,'Investment Banking Salary (The ACTUAL First Year Analyst SALARY Explained!)',1,'ZX6dz61s-po','Blockchain Developer','http://img.youtube.com/vi/ZX6dz61s-po/0.jpg'),
 (57,NULL,'2019-12-26 19:22:49',0,30,'SectorSeven','26-22-2019',NULL,NULL,NULL,'video/mp4',NULL,NULL,NULL,NULL,NULL,NULL,'What is Investment Banking | Departments to Job Roles and Responsibilities',1,'CfzeEz826VM','Blockchain Developer','http://img.youtube.com/vi/CfzeEz826VM/0.jpg'),
 (58,NULL,'2019-12-26 19:22:49',0,30,'SectorSeven','26-22-2019',NULL,NULL,NULL,'video/mp4',NULL,NULL,NULL,NULL,NULL,NULL,'Suits - Investment bankers',1,'_s42gOg2WSU','Blockchain Developer','http://img.youtube.com/vi/_s42gOg2WSU/0.jpg'),
 (59,NULL,'2019-12-26 19:22:49',0,30,'SectorSeven','26-22-2019',NULL,NULL,NULL,'video/mp4',NULL,NULL,NULL,NULL,NULL,NULL,'How to Become an Investment Banker? CareerBuilder Videos from funza Academy',1,'3dZRmgLq8bU','Blockchain Developer','http://img.youtube.com/vi/3dZRmgLq8bU/0.jpg'),
 (60,NULL,'2019-12-26 19:22:49',0,30,'SectorSeven','26-22-2019',NULL,NULL,NULL,'video/mp4',NULL,NULL,NULL,NULL,NULL,NULL,'How to get into investment banking',1,'kLFItf290Fc','Blockchain Developer','http://img.youtube.com/vi/kLFItf290Fc/0.jpg'),
 (61,NULL,'2019-12-26 19:22:49',0,30,'SectorSeven','26-22-2019',NULL,NULL,NULL,'video/mp4',NULL,NULL,NULL,NULL,NULL,NULL,'All You Should Know About Investment banking Courses - Skills Required, Job Prospects',1,'fdTX1nUapTY','Blockchain Developer','http://img.youtube.com/vi/fdTX1nUapTY/0.jpg'),
 (62,NULL,'2019-12-26 19:22:49',0,30,'SectorSeven','26-22-2019',NULL,NULL,NULL,'video/mp4',NULL,NULL,NULL,NULL,NULL,NULL,'What is Investment Banking?',1,'QCQyB0FBJjY','Blockchain Developer','http://img.youtube.com/vi/QCQyB0FBJjY/0.jpg'),
 (63,NULL,'2019-12-26 19:22:49',0,30,'SectorSeven','26-22-2019',NULL,NULL,NULL,'video/mp4',NULL,NULL,NULL,NULL,NULL,NULL,'Why pursue a career in investment banking?',1,'tPYlvMERJmY','Blockchain Developer','http://img.youtube.com/vi/tPYlvMERJmY/0.jpg'),
 (64,NULL,'2019-12-26 19:22:49',0,30,'SectorSeven','26-22-2019',NULL,NULL,NULL,'video/mp4',NULL,NULL,NULL,NULL,NULL,NULL,'What is Investment Banking in HINDI || Investment Banker ?| Highest Paying Jobs in India | World',1,'14ffsxVCRxs','Blockchain Developer','http://img.youtube.com/vi/14ffsxVCRxs/0.jpg'),
 (65,NULL,'2019-12-26 19:22:49',0,30,'SectorSeven','26-22-2019',NULL,NULL,NULL,'video/mp4',NULL,NULL,NULL,NULL,NULL,NULL,'Investment Banking Career Tips - What does an Investment Banking Analyst Do?',1,'8yvK31KNvAc','Blockchain Developer','http://img.youtube.com/vi/8yvK31KNvAc/0.jpg'),
 (66,NULL,'2019-12-26 19:22:49',0,30,'SectorSeven','26-22-2019',NULL,NULL,NULL,'video/mp4',NULL,NULL,NULL,NULL,NULL,NULL,'Investment Banker reveals secrets of his profession',1,'7wYva9dhelE','Blockchain Developer','http://img.youtube.com/vi/7wYva9dhelE/0.jpg'),
 (67,NULL,'2019-12-26 19:22:49',0,30,'SectorSeven','26-22-2019',NULL,NULL,NULL,'video/mp4',NULL,NULL,NULL,NULL,NULL,NULL,'Investment Banking: Required Skills for Success',1,'R8aDgJlHB3Q','Blockchain Developer','http://img.youtube.com/vi/R8aDgJlHB3Q/0.jpg'),
 (68,NULL,'2019-12-26 20:03:16',0,31,'SectorSeven','26-03-2019',NULL,NULL,NULL,'video/mp4',NULL,NULL,NULL,NULL,NULL,NULL,'What is Investment Banking (What do they actually do?)',1,'32fLCPcsffs','Blockchain Developer','http://img.youtube.com/vi/32fLCPcsffs/0.jpg'),
 (69,NULL,'2019-12-26 20:03:16',0,31,'SectorSeven','26-03-2019',NULL,NULL,NULL,'video/mp4',NULL,NULL,NULL,NULL,NULL,NULL,'Investment Banking: What do they do?',1,'MbMh6KRLz9U','Blockchain Developer','http://img.youtube.com/vi/MbMh6KRLz9U/0.jpg'),
 (70,NULL,'2019-12-26 20:03:16',0,31,'SectorSeven','26-03-2019',NULL,NULL,NULL,'video/mp4',NULL,NULL,NULL,NULL,NULL,NULL,'Day in the Life of an Investment Banker I Manisha Girotra I Success Stories I ChetChat',1,'MQtVmxQbvXo','Blockchain Developer','http://img.youtube.com/vi/MQtVmxQbvXo/0.jpg'),
 (71,NULL,'2019-12-26 20:03:16',0,31,'SectorSeven','26-03-2019',NULL,NULL,NULL,'video/mp4',NULL,NULL,NULL,NULL,NULL,NULL,'Everything you need to know about CFA Program!',1,'Z1fGHLKA2zk','Blockchain Developer','http://img.youtube.com/vi/Z1fGHLKA2zk/0.jpg'),
 (72,NULL,'2019-12-26 20:03:16',0,31,'SectorSeven','26-03-2019',NULL,NULL,NULL,'video/mp4',NULL,NULL,NULL,NULL,NULL,NULL,'How to become an Investment banker',1,'if75PRKQHWQ','Blockchain Developer','http://img.youtube.com/vi/if75PRKQHWQ/0.jpg'),
 (73,NULL,'2019-12-26 20:03:16',0,31,'SectorSeven','26-03-2019',NULL,NULL,NULL,'video/mp4',NULL,NULL,NULL,NULL,NULL,NULL,'How to Become Investment Banker in INDIA |Complete Procedure| Highest Paying Jobs [HINDI/URDU]',1,'qAKODfJZV9M','Blockchain Developer','http://img.youtube.com/vi/qAKODfJZV9M/0.jpg'),
 (74,NULL,'2019-12-26 20:03:16',0,31,'SectorSeven','26-03-2019',NULL,NULL,NULL,'video/mp4',NULL,NULL,NULL,NULL,NULL,NULL,'What is Investment Banking? What do Investment Bankers Do?',1,'WdvC9xUfuyc','Blockchain Developer','http://img.youtube.com/vi/WdvC9xUfuyc/0.jpg'),
 (75,NULL,'2019-12-26 20:03:16',0,31,'SectorSeven','26-03-2019',NULL,NULL,NULL,'video/mp4',NULL,NULL,NULL,NULL,NULL,NULL,'Investment Banking Salary (The ACTUAL First Year Analyst SALARY Explained!)',1,'ZX6dz61s-po','Blockchain Developer','http://img.youtube.com/vi/ZX6dz61s-po/0.jpg'),
 (76,NULL,'2019-12-26 20:03:16',0,31,'SectorSeven','26-03-2019',NULL,NULL,NULL,'video/mp4',NULL,NULL,NULL,NULL,NULL,NULL,'What is Investment Banking | Departments to Job Roles and Responsibilities',1,'CfzeEz826VM','Blockchain Developer','http://img.youtube.com/vi/CfzeEz826VM/0.jpg'),
 (77,NULL,'2019-12-26 20:03:16',0,31,'SectorSeven','26-03-2019',NULL,NULL,NULL,'video/mp4',NULL,NULL,NULL,NULL,NULL,NULL,'Suits - Investment bankers',1,'_s42gOg2WSU','Blockchain Developer','http://img.youtube.com/vi/_s42gOg2WSU/0.jpg'),
 (78,NULL,'2019-12-26 20:03:16',0,31,'SectorSeven','26-03-2019',NULL,NULL,NULL,'video/mp4',NULL,NULL,NULL,NULL,NULL,NULL,'How to Become an Investment Banker? CareerBuilder Videos from funza Academy',1,'3dZRmgLq8bU','Blockchain Developer','http://img.youtube.com/vi/3dZRmgLq8bU/0.jpg'),
 (79,NULL,'2019-12-26 20:03:16',0,31,'SectorSeven','26-03-2019',NULL,NULL,NULL,'video/mp4',NULL,NULL,NULL,NULL,NULL,NULL,'How to get into investment banking',1,'kLFItf290Fc','Blockchain Developer','http://img.youtube.com/vi/kLFItf290Fc/0.jpg'),
 (80,NULL,'2019-12-26 20:03:16',0,31,'SectorSeven','26-03-2019',NULL,NULL,NULL,'video/mp4',NULL,NULL,NULL,NULL,NULL,NULL,'All You Should Know About Investment banking Courses - Skills Required, Job Prospects',1,'fdTX1nUapTY','Blockchain Developer','http://img.youtube.com/vi/fdTX1nUapTY/0.jpg'),
 (81,NULL,'2019-12-26 20:03:16',0,31,'SectorSeven','26-03-2019',NULL,NULL,NULL,'video/mp4',NULL,NULL,NULL,NULL,NULL,NULL,'What is Investment Banking?',1,'QCQyB0FBJjY','Blockchain Developer','http://img.youtube.com/vi/QCQyB0FBJjY/0.jpg'),
 (82,NULL,'2019-12-26 20:03:16',0,31,'SectorSeven','26-03-2019',NULL,NULL,NULL,'video/mp4',NULL,NULL,NULL,NULL,NULL,NULL,'Why pursue a career in investment banking?',1,'tPYlvMERJmY','Blockchain Developer','http://img.youtube.com/vi/tPYlvMERJmY/0.jpg'),
 (83,NULL,'2019-12-26 20:03:16',0,31,'SectorSeven','26-03-2019',NULL,NULL,NULL,'video/mp4',NULL,NULL,NULL,NULL,NULL,NULL,'What is Investment Banking in HINDI || Investment Banker ?| Highest Paying Jobs in India | World',1,'14ffsxVCRxs','Blockchain Developer','http://img.youtube.com/vi/14ffsxVCRxs/0.jpg'),
 (84,NULL,'2019-12-26 20:03:16',0,31,'SectorSeven','26-03-2019',NULL,NULL,NULL,'video/mp4',NULL,NULL,NULL,NULL,NULL,NULL,'Investment Banking Career Tips - What does an Investment Banking Analyst Do?',1,'8yvK31KNvAc','Blockchain Developer','http://img.youtube.com/vi/8yvK31KNvAc/0.jpg'),
 (85,NULL,'2019-12-26 20:03:16',0,31,'SectorSeven','26-03-2019',NULL,NULL,NULL,'video/mp4',NULL,NULL,NULL,NULL,NULL,NULL,'Investment Banker reveals secrets of his profession',1,'7wYva9dhelE','Blockchain Developer','http://img.youtube.com/vi/7wYva9dhelE/0.jpg'),
 (86,NULL,'2019-12-26 20:03:16',0,31,'SectorSeven','26-03-2019',NULL,NULL,NULL,'video/mp4',NULL,NULL,NULL,NULL,NULL,NULL,'Investment Banking: Required Skills for Success',1,'R8aDgJlHB3Q','Blockchain Developer','http://img.youtube.com/vi/R8aDgJlHB3Q/0.jpg');
/*!40000 ALTER TABLE `sub_category_details` ENABLE KEYS */;


--
-- Definition of table `subscribed_careers`
--

DROP TABLE IF EXISTS `subscribed_careers`;
CREATE TABLE `subscribed_careers` (
  `id` bigint(10) unsigned NOT NULL AUTO_INCREMENT,
  `user` bigint(10) unsigned DEFAULT NULL,
  `authority` bigint(10) NOT NULL,
  `date_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `status` int(10) unsigned NOT NULL,
  `subscribe` int(10) unsigned NOT NULL,
  `career_subcat` bigint(10) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_subscribed_careers_authority` (`authority`),
  KEY `FK_subscribed_careers_subcat` (`career_subcat`),
  CONSTRAINT `FK_subscribed_careers_authority` FOREIGN KEY (`authority`) REFERENCES `authority` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_subscribed_careers_subcat` FOREIGN KEY (`career_subcat`) REFERENCES `career_subcategory` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `subscribed_careers`
--

/*!40000 ALTER TABLE `subscribed_careers` DISABLE KEYS */;
INSERT INTO `subscribed_careers` (`id`,`user`,`authority`,`date_created`,`status`,`subscribe`,`career_subcat`) VALUES 
 (1,17,2,'2019-10-31 22:12:28',0,0,13),
 (10,16,2,'2019-12-20 17:53:05',0,0,13);
/*!40000 ALTER TABLE `subscribed_careers` ENABLE KEYS */;


--
-- Definition of table `success_persons`
--

DROP TABLE IF EXISTS `success_persons`;
CREATE TABLE `success_persons` (
  `id` bigint(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `description` varchar(2000) NOT NULL,
  `status` int(10) unsigned NOT NULL,
  `date_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `subcat_id` bigint(10) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_success_persons_subcat` (`subcat_id`),
  CONSTRAINT `FK_success_persons_subcat` FOREIGN KEY (`subcat_id`) REFERENCES `career_subcategory` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=81 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `success_persons`
--

/*!40000 ALTER TABLE `success_persons` DISABLE KEYS */;
INSERT INTO `success_persons` (`id`,`name`,`description`,`status`,`date_created`,`subcat_id`) VALUES 
 (1,'Ravish Kumar\n','Ravish Kumar was an Indian Journalist.\nHe is a TV anchor, writer,  and media personality.\nHe is also a Managing Editor of NDTV India.\n',0,'2019-12-26 19:30:24',14),
 (2,'Nidhi Razdan','Nidhi Razdan is an Indian Journalist.\n‘NDTV 24x7 news show Left, Right & Centre’ primary anchor.\nShe has reported broadly on a wide range of key political, economic, and social stories from the Indian subcontinent.\n',0,'2019-12-26 19:30:24',14),
 (3,'Anoop Singh','Anoop Singh was an Indian Wrestling coach.\nHe is a three-time national champion and international wrestler\n',0,'2019-12-26 19:30:24',14),
 (4,'Harbans Singh','Harbans Singh is India’s athletics coach.He also has selected for the lifetime achievement category.',0,'2019-12-26 19:30:24',14),
 (5,'M.S. Swaminathan','He is the founder of the MS Swaminathan Research Foundation.He has worked worldwide in collaboration with colleagues and students on a wide range of problems in basic and applied plant breeding, agricultural research, and development and the conservation of natural resources.',0,'2019-12-26 19:30:24',14),
 (6,'Leason Heberling Adams','Leason Heberling Adams is an American geophysicist and researcher. His principal achievement was his research on the characteristics of materials exposed to very high pressures, which he used to derive information on the nature of the Earth\'s interior. He earned the William Bowie Medal of the American Geophysical Union on the yer 1950 for his performance.',0,'2019-12-26 19:30:24',14),
 (7,'George Edward Backus','George Edward Backups is an American geophysicist, well known for his work with J. Freeman Gilbert on inverse methods for geophysical data. He is also highly credited with advancing the dynamo theory on the origin of the Earth\'s magnetic field.',0,'2019-12-26 19:30:24',14),
 (8,'Shankar Doraiswamy','Shankar Doraiswamy is an Indian oceanographer. He is the Senior Principal Scientist position at the National Institute of Oceanography in Goa. He was awarded on the year 2011 the Shanti Swarup Bhatnagar Prize for Science and Technology, the highest science award in India, in the Earth sciences category.',0,'2019-12-26 19:30:24',14),
 (9,'Tad S. Murty',' Tad S. Murty is an Indian-Canadian oceanographer and expert about tsunamis. He is the former president of Tsunami Society. He is an adjunct professor in the departments of Civil Engineering and Earth Sciences at the University of Ottawa. Murty is a co-editor of the journal Natural Hazards with Tom Beer of CSIRO and Vladimir Schenk of the Czech Republic.',0,'2019-12-26 19:30:24',14),
 (10,'Raghu Rai','Raghu Rai is an Indian photographer. He is also a  photojournalist.',0,'2019-12-26 19:30:24',14),
 (11,'Sooni Taraporevala','Sooni Taraporevala is an Indian photographer.She is also a  screenwriter and filmmaker.',0,'2019-12-26 19:30:24',14),
 (12,'Ellen Church','Ellen church was the first female air hostess. She started her career in the 1930s. In fact, she was a pilot too. Unfortunately, she is back then, airlines were not interested in hiring women. Or any job aboard a plane.',0,'2019-12-26 19:30:24',14),
 (13,'Naina Lal Kidwai','Naina Lal Kidwai is an Indian Chartered Accountant.She is an  Indian banker and business executive.She is also a Group General Manager and the Country Head of HSBC Indian and also a former President of the Federation of Indian Chambers of Commerce and Industry (FICCI).',0,'2019-12-26 19:30:24',14),
 (14,'T. N. Manoharan','T. N. Manoharan is an Indian Chartered Accountant. T. N. Manoharan is a Canara Bank Chairman and also a former president of Institute of Chartered Accountants of India (ICAI)',0,'2019-12-26 19:30:24',14),
 (15,'Meredith Ann Whitney','Meredith Ann Whitney is an American businesswoman recognised as “The Oracle of Wall Street” by Bloomberg. She is best known for successfully forecasting the difficulties of Citigroup and other major banks during the financial crisis of 2007–2008. She joined Oppenheimer Holdings in 1993 as a Director, and in 1995 she joined the company\'s Specialty Finance Group.',0,'2019-12-26 19:30:24',14),
 (16,'Abby Joseph Cohen',' Abby Joseph Cohen is an American economist and financial analyst on Wall Street. Since February 2017, she continues to serve as an advisory director at Goldman Sachs, after retiring from the leadership of its Global Markets Institute. She started her career as an economist in the year 1973 at the Federal Reserve Board in Washington, D.C., up to 1977. She worked as an economist and quantitative research director for T. Rowe Price Associates. From 1983 to 1988, she was the vice president in charge of investment strategy at Drexel Burnham Lambert.',0,'2019-12-26 19:30:24',14),
 (17,'Dieter Rams','He is a German industrial designer. In the year 1955, he was appointed as an architect and an interior designer at Braun and in the year 1961, he became the chief design officer at Braun. Dieter Rams have served as head of consumer products for German consumer products company Braun.',0,'2019-12-26 19:30:24',14),
 (18,'Naoto Fukasawa','Naoto Fukasawa is a Japanese industrial designer. He is a product design graduate from Tama Art University on the year 1980.',0,'2019-12-26 19:30:24',14),
 (19,'Amil Kumar Das','Amil Kumar Das was an Indian astronomer. He was the director of the Kodaikanal observatory.',0,'2019-12-26 19:30:24',14),
 (20,'Aryabhata','Aryabhata was an Indian astronomer. He is also an Indian mathematician.',0,'2019-12-26 19:30:24',14),
 (21,'Ravish Kumar\n','Ravish Kumar was an Indian Journalist.\nHe is a TV anchor, writer,  and media personality.\nHe is also a Managing Editor of NDTV India.\n',0,'2019-12-26 19:33:50',14),
 (22,'Nidhi Razdan','Nidhi Razdan is an Indian Journalist.\n‘NDTV 24x7 news show Left, Right & Centre’ primary anchor.\nShe has reported broadly on a wide range of key political, economic, and social stories from the Indian subcontinent.\n',0,'2019-12-26 19:33:50',14),
 (23,'Anoop Singh','Anoop Singh was an Indian Wrestling coach.\nHe is a three-time national champion and international wrestler\n',0,'2019-12-26 19:33:50',14),
 (24,'Harbans Singh','Harbans Singh is India’s athletics coach.He also has selected for the lifetime achievement category.',0,'2019-12-26 19:33:50',14),
 (25,'M.S. Swaminathan','He is the founder of the MS Swaminathan Research Foundation.He has worked worldwide in collaboration with colleagues and students on a wide range of problems in basic and applied plant breeding, agricultural research, and development and the conservation of natural resources.',0,'2019-12-26 19:33:50',14),
 (26,'Leason Heberling Adams','Leason Heberling Adams is an American geophysicist and researcher. His principal achievement was his research on the characteristics of materials exposed to very high pressures, which he used to derive information on the nature of the Earth\'s interior. He earned the William Bowie Medal of the American Geophysical Union on the yer 1950 for his performance.',0,'2019-12-26 19:33:50',14),
 (27,'George Edward Backus','George Edward Backups is an American geophysicist, well known for his work with J. Freeman Gilbert on inverse methods for geophysical data. He is also highly credited with advancing the dynamo theory on the origin of the Earth\'s magnetic field.',0,'2019-12-26 19:33:50',14),
 (28,'Shankar Doraiswamy','Shankar Doraiswamy is an Indian oceanographer. He is the Senior Principal Scientist position at the National Institute of Oceanography in Goa. He was awarded on the year 2011 the Shanti Swarup Bhatnagar Prize for Science and Technology, the highest science award in India, in the Earth sciences category.',0,'2019-12-26 19:33:50',14),
 (29,'Tad S. Murty',' Tad S. Murty is an Indian-Canadian oceanographer and expert about tsunamis. He is the former president of Tsunami Society. He is an adjunct professor in the departments of Civil Engineering and Earth Sciences at the University of Ottawa. Murty is a co-editor of the journal Natural Hazards with Tom Beer of CSIRO and Vladimir Schenk of the Czech Republic.',0,'2019-12-26 19:33:50',14),
 (30,'Raghu Rai','Raghu Rai is an Indian photographer. He is also a  photojournalist.',0,'2019-12-26 19:33:50',14),
 (31,'Sooni Taraporevala','Sooni Taraporevala is an Indian photographer.She is also a  screenwriter and filmmaker.',0,'2019-12-26 19:33:50',14),
 (32,'Ellen Church','Ellen church was the first female air hostess. She started her career in the 1930s. In fact, she was a pilot too. Unfortunately, she is back then, airlines were not interested in hiring women. Or any job aboard a plane.',0,'2019-12-26 19:33:50',14),
 (33,'Naina Lal Kidwai','Naina Lal Kidwai is an Indian Chartered Accountant.She is an  Indian banker and business executive.She is also a Group General Manager and the Country Head of HSBC Indian and also a former President of the Federation of Indian Chambers of Commerce and Industry (FICCI).',0,'2019-12-26 19:33:50',14),
 (34,'T. N. Manoharan','T. N. Manoharan is an Indian Chartered Accountant. T. N. Manoharan is a Canara Bank Chairman and also a former president of Institute of Chartered Accountants of India (ICAI)',0,'2019-12-26 19:33:50',14),
 (35,'Meredith Ann Whitney','Meredith Ann Whitney is an American businesswoman recognised as “The Oracle of Wall Street” by Bloomberg. She is best known for successfully forecasting the difficulties of Citigroup and other major banks during the financial crisis of 2007–2008. She joined Oppenheimer Holdings in 1993 as a Director, and in 1995 she joined the company\'s Specialty Finance Group.',0,'2019-12-26 19:33:50',14),
 (36,'Abby Joseph Cohen',' Abby Joseph Cohen is an American economist and financial analyst on Wall Street. Since February 2017, she continues to serve as an advisory director at Goldman Sachs, after retiring from the leadership of its Global Markets Institute. She started her career as an economist in the year 1973 at the Federal Reserve Board in Washington, D.C., up to 1977. She worked as an economist and quantitative research director for T. Rowe Price Associates. From 1983 to 1988, she was the vice president in charge of investment strategy at Drexel Burnham Lambert.',0,'2019-12-26 19:33:51',14),
 (37,'Dieter Rams','He is a German industrial designer. In the year 1955, he was appointed as an architect and an interior designer at Braun and in the year 1961, he became the chief design officer at Braun. Dieter Rams have served as head of consumer products for German consumer products company Braun.',0,'2019-12-26 19:33:51',14),
 (38,'Naoto Fukasawa','Naoto Fukasawa is a Japanese industrial designer. He is a product design graduate from Tama Art University on the year 1980.',0,'2019-12-26 19:33:51',14),
 (39,'Amil Kumar Das','Amil Kumar Das was an Indian astronomer. He was the director of the Kodaikanal observatory.',0,'2019-12-26 19:33:51',14),
 (40,'Aryabhata','Aryabhata was an Indian astronomer. He is also an Indian mathematician.',0,'2019-12-26 19:33:51',14),
 (41,'Ravish Kumar\n','Ravish Kumar was an Indian Journalist.\nHe is a TV anchor, writer,  and media personality.\nHe is also a Managing Editor of NDTV India.\n',0,'2019-12-26 19:38:16',14),
 (42,'Nidhi Razdan','Nidhi Razdan is an Indian Journalist.\n‘NDTV 24x7 news show Left, Right & Centre’ primary anchor.\nShe has reported broadly on a wide range of key political, economic, and social stories from the Indian subcontinent.\n',0,'2019-12-26 19:38:16',14),
 (43,'Anoop Singh','Anoop Singh was an Indian Wrestling coach.\nHe is a three-time national champion and international wrestler\n',0,'2019-12-26 19:38:16',14),
 (44,'Harbans Singh','Harbans Singh is India’s athletics coach.He also has selected for the lifetime achievement category.',0,'2019-12-26 19:38:16',14),
 (45,'M.S. Swaminathan','He is the founder of the MS Swaminathan Research Foundation.He has worked worldwide in collaboration with colleagues and students on a wide range of problems in basic and applied plant breeding, agricultural research, and development and the conservation of natural resources.',0,'2019-12-26 19:38:16',14),
 (46,'Leason Heberling Adams','Leason Heberling Adams is an American geophysicist and researcher. His principal achievement was his research on the characteristics of materials exposed to very high pressures, which he used to derive information on the nature of the Earth\'s interior. He earned the William Bowie Medal of the American Geophysical Union on the yer 1950 for his performance.',0,'2019-12-26 19:38:16',14),
 (47,'George Edward Backus','George Edward Backups is an American geophysicist, well known for his work with J. Freeman Gilbert on inverse methods for geophysical data. He is also highly credited with advancing the dynamo theory on the origin of the Earth\'s magnetic field.',0,'2019-12-26 19:38:17',14),
 (48,'Shankar Doraiswamy','Shankar Doraiswamy is an Indian oceanographer. He is the Senior Principal Scientist position at the National Institute of Oceanography in Goa. He was awarded on the year 2011 the Shanti Swarup Bhatnagar Prize for Science and Technology, the highest science award in India, in the Earth sciences category.',0,'2019-12-26 19:38:17',14),
 (49,'Tad S. Murty',' Tad S. Murty is an Indian-Canadian oceanographer and expert about tsunamis. He is the former president of Tsunami Society. He is an adjunct professor in the departments of Civil Engineering and Earth Sciences at the University of Ottawa. Murty is a co-editor of the journal Natural Hazards with Tom Beer of CSIRO and Vladimir Schenk of the Czech Republic.',0,'2019-12-26 19:38:17',14),
 (50,'Raghu Rai','Raghu Rai is an Indian photographer. He is also a  photojournalist.',0,'2019-12-26 19:38:17',14),
 (51,'Sooni Taraporevala','Sooni Taraporevala is an Indian photographer.She is also a  screenwriter and filmmaker.',0,'2019-12-26 19:38:17',14),
 (52,'Ellen Church','Ellen church was the first female air hostess. She started her career in the 1930s. In fact, she was a pilot too. Unfortunately, she is back then, airlines were not interested in hiring women. Or any job aboard a plane.',0,'2019-12-26 19:38:17',14),
 (53,'Naina Lal Kidwai','Naina Lal Kidwai is an Indian Chartered Accountant.She is an  Indian banker and business executive.She is also a Group General Manager and the Country Head of HSBC Indian and also a former President of the Federation of Indian Chambers of Commerce and Industry (FICCI).',0,'2019-12-26 19:38:17',14),
 (54,'T. N. Manoharan','T. N. Manoharan is an Indian Chartered Accountant. T. N. Manoharan is a Canara Bank Chairman and also a former president of Institute of Chartered Accountants of India (ICAI)',0,'2019-12-26 19:38:17',14),
 (55,'Meredith Ann Whitney','Meredith Ann Whitney is an American businesswoman recognised as “The Oracle of Wall Street” by Bloomberg. She is best known for successfully forecasting the difficulties of Citigroup and other major banks during the financial crisis of 2007–2008. She joined Oppenheimer Holdings in 1993 as a Director, and in 1995 she joined the company\'s Specialty Finance Group.',0,'2019-12-26 19:38:17',14),
 (56,'Abby Joseph Cohen',' Abby Joseph Cohen is an American economist and financial analyst on Wall Street. Since February 2017, she continues to serve as an advisory director at Goldman Sachs, after retiring from the leadership of its Global Markets Institute. She started her career as an economist in the year 1973 at the Federal Reserve Board in Washington, D.C., up to 1977. She worked as an economist and quantitative research director for T. Rowe Price Associates. From 1983 to 1988, she was the vice president in charge of investment strategy at Drexel Burnham Lambert.',0,'2019-12-26 19:38:17',14),
 (57,'Dieter Rams','He is a German industrial designer. In the year 1955, he was appointed as an architect and an interior designer at Braun and in the year 1961, he became the chief design officer at Braun. Dieter Rams have served as head of consumer products for German consumer products company Braun.',0,'2019-12-26 19:38:17',14),
 (58,'Naoto Fukasawa','Naoto Fukasawa is a Japanese industrial designer. He is a product design graduate from Tama Art University on the year 1980.',0,'2019-12-26 19:38:17',14),
 (59,'Amil Kumar Das','Amil Kumar Das was an Indian astronomer. He was the director of the Kodaikanal observatory.',0,'2019-12-26 19:38:17',14),
 (60,'Aryabhata','Aryabhata was an Indian astronomer. He is also an Indian mathematician.',0,'2019-12-26 19:38:17',14),
 (61,'Ravish Kumar\n','Ravish Kumar was an Indian Journalist.\nHe is a TV anchor, writer,  and media personality.\nHe is also a Managing Editor of NDTV India.\n',0,'2019-12-26 20:03:26',14),
 (62,'Nidhi Razdan','Nidhi Razdan is an Indian Journalist.\n‘NDTV 24x7 news show Left, Right & Centre’ primary anchor.\nShe has reported broadly on a wide range of key political, economic, and social stories from the Indian subcontinent.\n',0,'2019-12-26 20:03:26',14),
 (63,'Anoop Singh','Anoop Singh was an Indian Wrestling coach.\nHe is a three-time national champion and international wrestler\n',0,'2019-12-26 20:03:26',14),
 (64,'Harbans Singh','Harbans Singh is India’s athletics coach.He also has selected for the lifetime achievement category.',0,'2019-12-26 20:03:26',14),
 (65,'M.S. Swaminathan','He is the founder of the MS Swaminathan Research Foundation.He has worked worldwide in collaboration with colleagues and students on a wide range of problems in basic and applied plant breeding, agricultural research, and development and the conservation of natural resources.',0,'2019-12-26 20:03:26',14),
 (66,'Leason Heberling Adams','Leason Heberling Adams is an American geophysicist and researcher. His principal achievement was his research on the characteristics of materials exposed to very high pressures, which he used to derive information on the nature of the Earth\'s interior. He earned the William Bowie Medal of the American Geophysical Union on the yer 1950 for his performance.',0,'2019-12-26 20:03:27',14),
 (67,'George Edward Backus','George Edward Backups is an American geophysicist, well known for his work with J. Freeman Gilbert on inverse methods for geophysical data. He is also highly credited with advancing the dynamo theory on the origin of the Earth\'s magnetic field.',0,'2019-12-26 20:03:27',14),
 (68,'Shankar Doraiswamy','Shankar Doraiswamy is an Indian oceanographer. He is the Senior Principal Scientist position at the National Institute of Oceanography in Goa. He was awarded on the year 2011 the Shanti Swarup Bhatnagar Prize for Science and Technology, the highest science award in India, in the Earth sciences category.',0,'2019-12-26 20:03:27',14),
 (69,'Tad S. Murty',' Tad S. Murty is an Indian-Canadian oceanographer and expert about tsunamis. He is the former president of Tsunami Society. He is an adjunct professor in the departments of Civil Engineering and Earth Sciences at the University of Ottawa. Murty is a co-editor of the journal Natural Hazards with Tom Beer of CSIRO and Vladimir Schenk of the Czech Republic.',0,'2019-12-26 20:03:27',14),
 (70,'Raghu Rai','Raghu Rai is an Indian photographer. He is also a  photojournalist.',0,'2019-12-26 20:03:27',14),
 (71,'Sooni Taraporevala','Sooni Taraporevala is an Indian photographer.She is also a  screenwriter and filmmaker.',0,'2019-12-26 20:03:27',14),
 (72,'Ellen Church','Ellen church was the first female air hostess. She started her career in the 1930s. In fact, she was a pilot too. Unfortunately, she is back then, airlines were not interested in hiring women. Or any job aboard a plane.',0,'2019-12-26 20:03:27',14),
 (73,'Naina Lal Kidwai','Naina Lal Kidwai is an Indian Chartered Accountant.She is an  Indian banker and business executive.She is also a Group General Manager and the Country Head of HSBC Indian and also a former President of the Federation of Indian Chambers of Commerce and Industry (FICCI).',0,'2019-12-26 20:03:27',14),
 (74,'T. N. Manoharan','T. N. Manoharan is an Indian Chartered Accountant. T. N. Manoharan is a Canara Bank Chairman and also a former president of Institute of Chartered Accountants of India (ICAI)',0,'2019-12-26 20:03:27',14),
 (75,'Meredith Ann Whitney','Meredith Ann Whitney is an American businesswoman recognised as “The Oracle of Wall Street” by Bloomberg. She is best known for successfully forecasting the difficulties of Citigroup and other major banks during the financial crisis of 2007–2008. She joined Oppenheimer Holdings in 1993 as a Director, and in 1995 she joined the company\'s Specialty Finance Group.',0,'2019-12-26 20:03:27',14),
 (76,'Abby Joseph Cohen',' Abby Joseph Cohen is an American economist and financial analyst on Wall Street. Since February 2017, she continues to serve as an advisory director at Goldman Sachs, after retiring from the leadership of its Global Markets Institute. She started her career as an economist in the year 1973 at the Federal Reserve Board in Washington, D.C., up to 1977. She worked as an economist and quantitative research director for T. Rowe Price Associates. From 1983 to 1988, she was the vice president in charge of investment strategy at Drexel Burnham Lambert.',0,'2019-12-26 20:03:27',14),
 (77,'Dieter Rams','He is a German industrial designer. In the year 1955, he was appointed as an architect and an interior designer at Braun and in the year 1961, he became the chief design officer at Braun. Dieter Rams have served as head of consumer products for German consumer products company Braun.',0,'2019-12-26 20:03:27',14),
 (78,'Naoto Fukasawa','Naoto Fukasawa is a Japanese industrial designer. He is a product design graduate from Tama Art University on the year 1980.',0,'2019-12-26 20:03:27',14),
 (79,'Amil Kumar Das','Amil Kumar Das was an Indian astronomer. He was the director of the Kodaikanal observatory.',0,'2019-12-26 20:03:27',14),
 (80,'Aryabhata','Aryabhata was an Indian astronomer. He is also an Indian mathematician.',0,'2019-12-26 20:03:27',14);
/*!40000 ALTER TABLE `success_persons` ENABLE KEYS */;


--
-- Definition of table `time_spent`
--

DROP TABLE IF EXISTS `time_spent`;
CREATE TABLE `time_spent` (
  `id` bigint(10) unsigned NOT NULL AUTO_INCREMENT,
  `login_time` datetime NOT NULL,
  `logout_time` datetime DEFAULT NULL,
  `student` bigint(10) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_time_spent_student` (`student`),
  CONSTRAINT `FK_time_spent_student` FOREIGN KEY (`student`) REFERENCES `student` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=193 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `time_spent`
--

/*!40000 ALTER TABLE `time_spent` DISABLE KEYS */;
INSERT INTO `time_spent` (`id`,`login_time`,`logout_time`,`student`) VALUES 
 (1,'2019-08-31 17:18:32',NULL,7),
 (2,'2019-09-08 13:26:07',NULL,4),
 (3,'2019-09-08 13:26:09',NULL,4),
 (4,'2019-09-08 13:34:39',NULL,4),
 (5,'2019-09-08 14:36:03',NULL,4),
 (6,'2019-09-08 16:30:06',NULL,4),
 (7,'2019-09-09 11:49:56',NULL,4),
 (8,'2019-09-12 12:46:20',NULL,4),
 (9,'2019-09-12 14:42:09',NULL,4),
 (10,'2019-09-12 15:33:07',NULL,4),
 (11,'2019-09-12 15:36:05',NULL,4),
 (12,'2019-09-12 16:36:47',NULL,4),
 (13,'2019-09-12 16:53:35',NULL,4),
 (14,'2019-09-12 17:13:05',NULL,4),
 (15,'2019-09-13 10:25:12',NULL,4),
 (16,'2019-09-13 11:08:57',NULL,4),
 (17,'2019-09-13 11:38:26',NULL,4),
 (18,'2019-09-13 11:52:29',NULL,4),
 (19,'2019-09-13 11:54:54',NULL,4),
 (20,'2019-09-13 14:58:24',NULL,4),
 (21,'2019-09-13 15:02:11',NULL,4),
 (22,'2019-09-13 15:04:19',NULL,4),
 (23,'2019-09-13 16:55:03',NULL,4),
 (24,'2019-09-13 17:49:45',NULL,4),
 (25,'2019-09-14 09:34:18',NULL,4),
 (26,'2019-09-14 10:41:48',NULL,4),
 (27,'2019-09-14 10:45:19',NULL,4),
 (28,'2019-09-14 10:52:16',NULL,4),
 (29,'2019-09-14 11:05:13',NULL,4),
 (30,'2019-09-15 18:57:24',NULL,4),
 (31,'2019-09-15 21:30:54',NULL,4),
 (32,'2019-09-15 21:41:40',NULL,4),
 (33,'2019-09-15 21:50:54',NULL,4),
 (34,'2019-09-15 22:03:19',NULL,4),
 (35,'2019-09-15 22:16:36',NULL,4),
 (36,'2019-09-15 22:54:36',NULL,4),
 (37,'2019-09-15 23:15:12',NULL,4),
 (38,'2019-09-15 23:17:26',NULL,4),
 (39,'2019-09-15 23:19:51',NULL,4),
 (40,'2019-09-15 23:22:41',NULL,4),
 (41,'2019-09-16 11:15:32',NULL,4),
 (42,'2019-09-16 11:22:34',NULL,4),
 (43,'2019-09-16 12:26:23',NULL,4),
 (44,'2019-09-16 12:33:05',NULL,4),
 (45,'2019-09-16 14:55:21',NULL,4),
 (46,'2019-09-16 16:50:58',NULL,4),
 (47,'2019-09-16 17:13:54',NULL,4),
 (48,'2019-09-16 17:33:06',NULL,4),
 (49,'2019-09-16 17:43:22',NULL,4),
 (50,'2019-09-16 17:57:43',NULL,4),
 (51,'2019-09-17 09:42:20',NULL,4),
 (52,'2019-09-17 10:13:47',NULL,4),
 (53,'2019-09-17 10:18:06',NULL,4),
 (54,'2019-09-17 10:26:19',NULL,4),
 (55,'2019-09-17 10:39:39',NULL,4),
 (56,'2019-09-17 21:47:22',NULL,4),
 (57,'2019-09-17 22:08:00',NULL,4),
 (58,'2019-09-18 14:18:52',NULL,4),
 (59,'2019-09-20 17:28:55',NULL,4),
 (60,'2019-09-20 17:36:32',NULL,4),
 (61,'2019-09-20 17:39:12',NULL,4),
 (62,'2019-09-20 17:39:45',NULL,4),
 (63,'2019-09-20 17:44:29',NULL,4),
 (64,'2019-09-21 14:15:51',NULL,4),
 (65,'2019-09-21 14:20:55',NULL,4),
 (66,'2019-09-21 14:22:18',NULL,4),
 (67,'2019-09-21 14:23:20',NULL,4),
 (68,'2019-09-21 14:35:27',NULL,4),
 (69,'2019-09-21 14:37:02',NULL,4),
 (70,'2019-09-21 14:38:21',NULL,4),
 (71,'2019-09-21 14:57:20',NULL,4),
 (72,'2019-09-21 14:59:36',NULL,4),
 (73,'2019-09-21 15:03:43',NULL,4),
 (74,'2019-09-21 15:06:48',NULL,4),
 (75,'2019-09-21 16:42:44',NULL,4),
 (76,'2019-09-22 11:47:41',NULL,4),
 (77,'2019-09-22 12:33:58',NULL,4),
 (78,'2019-09-22 12:45:54',NULL,4),
 (79,'2019-09-22 12:58:40',NULL,4),
 (80,'2019-09-22 13:02:24',NULL,4),
 (81,'2019-09-22 13:08:40',NULL,4),
 (82,'2019-09-22 13:59:25',NULL,4),
 (83,'2019-09-22 16:38:11',NULL,4),
 (84,'2019-09-23 09:32:01',NULL,4),
 (85,'2019-09-23 10:48:58',NULL,4),
 (86,'2019-09-23 11:35:54',NULL,4),
 (87,'2019-09-23 14:14:24',NULL,4),
 (88,'2019-09-23 15:25:47',NULL,4),
 (89,'2019-09-23 16:00:03',NULL,4),
 (90,'2019-09-23 16:42:46',NULL,4),
 (91,'2019-09-23 17:07:08',NULL,4),
 (92,'2019-09-23 17:26:00',NULL,4),
 (93,'2019-09-23 17:41:21',NULL,4),
 (94,'2019-09-23 17:54:55',NULL,4),
 (95,'2019-09-24 10:05:30',NULL,4),
 (96,'2019-09-24 10:59:49',NULL,4),
 (97,'2019-09-24 11:08:22',NULL,4),
 (98,'2019-09-24 11:51:17',NULL,4),
 (99,'2019-09-24 12:08:54',NULL,4),
 (100,'2019-09-24 12:22:06',NULL,4),
 (101,'2019-09-24 14:11:04',NULL,4),
 (102,'2019-09-24 14:37:03',NULL,4),
 (103,'2019-09-24 14:41:05',NULL,4),
 (104,'2019-09-24 15:45:39',NULL,4),
 (105,'2019-09-24 16:45:04',NULL,4),
 (106,'2019-09-24 17:11:59',NULL,4),
 (107,'2019-09-24 17:14:56',NULL,4),
 (108,'2019-09-24 17:27:20',NULL,4),
 (109,'2019-09-24 17:52:55',NULL,4),
 (110,'2019-09-24 17:54:31',NULL,4),
 (111,'2019-09-24 19:06:36',NULL,4),
 (112,'2019-09-24 19:07:01',NULL,4),
 (113,'2019-09-24 19:45:19',NULL,4),
 (114,'2019-09-24 20:13:07',NULL,4),
 (115,'2019-09-24 20:41:34',NULL,4),
 (116,'2019-09-24 20:48:11',NULL,4),
 (117,'2019-09-24 20:53:48',NULL,4),
 (118,'2019-09-24 21:18:06',NULL,4),
 (119,'2019-09-24 21:22:24',NULL,4),
 (120,'2019-09-24 22:02:19',NULL,4),
 (121,'2019-09-24 23:05:50',NULL,4),
 (122,'2019-09-24 23:09:58',NULL,4),
 (123,'2019-09-24 23:31:36',NULL,4),
 (124,'2019-09-24 23:57:05',NULL,4),
 (125,'2019-09-25 00:40:41',NULL,4),
 (126,'2019-09-25 00:46:32',NULL,4),
 (127,'2019-09-25 09:28:16',NULL,4),
 (128,'2019-09-25 10:35:50',NULL,4),
 (129,'2019-09-25 11:30:26',NULL,4),
 (130,'2019-09-27 09:50:23',NULL,4),
 (131,'2019-09-27 10:16:30',NULL,4),
 (132,'2019-09-27 11:21:32',NULL,4),
 (133,'2019-09-27 11:34:14',NULL,4),
 (134,'2019-09-27 11:45:19',NULL,4),
 (135,'2019-09-27 12:11:26',NULL,4),
 (136,'2019-09-27 14:47:19',NULL,4),
 (137,'2019-09-27 15:35:28',NULL,4),
 (138,'2019-09-27 15:56:13',NULL,4),
 (139,'2019-09-27 16:51:29',NULL,4),
 (140,'2019-09-27 17:54:34',NULL,4),
 (141,'2019-09-27 17:58:22',NULL,4),
 (142,'2019-09-28 12:51:04',NULL,4),
 (143,'2019-09-28 14:29:02',NULL,4),
 (144,'2019-09-28 14:32:54',NULL,4),
 (145,'2019-09-28 15:13:29',NULL,4),
 (146,'2019-09-28 17:15:09',NULL,4),
 (147,'2019-09-28 17:19:23',NULL,4),
 (148,'2019-09-30 10:43:59',NULL,4),
 (149,'2019-09-30 11:34:58',NULL,4),
 (150,'2019-09-30 13:00:18',NULL,4),
 (151,'2019-09-30 14:31:21',NULL,4),
 (152,'2019-09-30 14:41:03',NULL,4),
 (153,'2019-09-30 14:41:05',NULL,4),
 (154,'2019-09-30 14:43:12',NULL,4),
 (155,'2019-09-30 15:31:24',NULL,4),
 (156,'2019-10-01 10:33:36',NULL,4),
 (157,'2019-10-01 11:17:06',NULL,4),
 (158,'2019-10-01 13:10:53',NULL,4),
 (159,'2019-10-01 13:18:29',NULL,4),
 (160,'2019-10-01 13:54:01',NULL,4),
 (161,'2019-10-01 14:05:31',NULL,4),
 (162,'2019-10-01 15:24:20',NULL,4),
 (163,'2019-10-01 16:50:19',NULL,4),
 (164,'2019-10-05 10:12:36',NULL,4),
 (165,'2019-10-05 11:01:52',NULL,4),
 (166,'2019-10-05 12:22:12',NULL,4),
 (167,'2019-10-05 12:53:37',NULL,4),
 (168,'2019-10-05 14:18:43',NULL,4),
 (169,'2019-10-05 15:50:39',NULL,4),
 (170,'2019-10-11 14:18:38',NULL,16),
 (171,'2019-10-22 15:36:05',NULL,16),
 (172,'2019-10-30 11:10:06',NULL,16),
 (173,'2019-10-30 11:14:20',NULL,16),
 (174,'2019-10-30 11:27:58',NULL,16),
 (175,'2019-10-30 11:50:00',NULL,16),
 (176,'2019-10-30 12:40:13',NULL,16),
 (177,'2019-10-30 13:06:50',NULL,16),
 (178,'2019-10-30 13:53:27',NULL,16),
 (179,'2019-10-30 23:53:30',NULL,16),
 (180,'2019-10-31 00:05:34',NULL,16),
 (181,'2019-10-31 11:31:06',NULL,16),
 (182,'2019-10-31 12:00:34',NULL,16),
 (183,'2019-10-31 13:20:18',NULL,16),
 (184,'2019-10-31 16:12:55',NULL,16),
 (185,'2019-10-31 16:40:50',NULL,16),
 (186,'2019-10-31 16:42:19',NULL,16),
 (187,'2019-10-31 16:44:29',NULL,16),
 (188,'2019-10-31 16:49:47',NULL,16),
 (189,'2019-10-31 16:51:28',NULL,16),
 (190,'2019-10-31 17:04:12',NULL,16),
 (191,'2019-10-31 23:41:21',NULL,16),
 (192,'2019-11-01 16:31:52',NULL,16);
/*!40000 ALTER TABLE `time_spent` ENABLE KEYS */;


--
-- Definition of table `user_interests`
--

DROP TABLE IF EXISTS `user_interests`;
CREATE TABLE `user_interests` (
  `id` bigint(10) unsigned NOT NULL AUTO_INCREMENT,
  `user` bigint(10) NOT NULL,
  `interests` bigint(10) unsigned NOT NULL,
  `authority` bigint(10) NOT NULL,
  `date_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `status` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_student_interests_student` (`user`) USING BTREE,
  KEY `FK_user_interests_authority` (`authority`),
  KEY `FK_user_interests_category` (`interests`),
  CONSTRAINT `FK_user_interests_authority` FOREIGN KEY (`authority`) REFERENCES `authority` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_user_interests_category` FOREIGN KEY (`interests`) REFERENCES `career_category` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user_interests`
--

/*!40000 ALTER TABLE `user_interests` DISABLE KEYS */;
INSERT INTO `user_interests` (`id`,`user`,`interests`,`authority`,`date_created`,`status`) VALUES 
 (25,17,2,2,'2019-12-19 10:03:00',0);
/*!40000 ALTER TABLE `user_interests` ENABLE KEYS */;




/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
