/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 5.7.9 : Database - management
*********************************************************************
*/


/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`management` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_swedish_ci */;

USE `management`;

/*Table structure for table `affair` */

DROP TABLE IF EXISTS `affair`;

CREATE TABLE `affair` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `end_time` timestamp NOT NULL COMMENT '开始时间',
  `start_time` timestamp NOT NULL COMMENT '截止时间',
  `title` varchar(32) DEFAULT NULL COMMENT '标题',
  `content` varchar(32) NOT NULL COMMENT '内容',
  `is_OK` tinyint(1) NOT NULL DEFAULT '0',
  `type` int(11) NOT NULL,
  `is_deleted` tinyint(1) DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `affair` */

/*Table structure for table `club` */

DROP TABLE IF EXISTS `club`;

CREATE TABLE `club` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(32) COLLATE utf8_swedish_ci NOT NULL COMMENT '社团名称',
  `logo` varchar(64) COLLATE utf8_swedish_ci DEFAULT 'null' COMMENT '社团logo',
  `intro` varchar(1024) COLLATE utf8_swedish_ci DEFAULT 'null' COMMENT '社团简介',
  `username` varchar(32) COLLATE utf8_swedish_ci NOT NULL,
  `password` char(32) COLLATE utf8_swedish_ci NOT NULL,
  `is_deleted` tinyint(1) DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_swedish_ci;

/*Data for the table `club` */

/*Table structure for table `department` */

DROP TABLE IF EXISTS `department`;

CREATE TABLE `department` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(32) COLLATE utf8_swedish_ci NOT NULL COMMENT '部门名称',
  `club_id` bigint(20) unsigned NOT NULL,
  `username` varchar(32) COLLATE utf8_swedish_ci NOT NULL,
  `password` char(32) COLLATE utf8_swedish_ci NOT NULL,
  `is_deleted` tinyint(1) DEFAULT '1',
  PRIMARY KEY (`id`),
  KEY `dept_club_fk` (`club_id`),
  CONSTRAINT `dept_club_fk` FOREIGN KEY (`club_id`) REFERENCES `club` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_swedish_ci;

/*Data for the table `department` */

/*Table structure for table `mem` */

DROP TABLE IF EXISTS `mem`;

CREATE TABLE `mem` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(32) COLLATE utf8_swedish_ci NOT NULL COMMENT '姓名',
  `stuid` varchar(32) COLLATE utf8_swedish_ci NOT NULL COMMENT 'username',
  `password` char(32) COLLATE utf8_swedish_ci NOT NULL,
  `college` varchar(32) COLLATE utf8_swedish_ci NOT NULL COMMENT '学院',
  `major` varchar(32) COLLATE utf8_swedish_ci NOT NULL COMMENT '专业',
  `gender` tinyint(1) DEFAULT '0' COMMENT '性别',
  `phone` varchar(11) COLLATE utf8_swedish_ci DEFAULT NULL COMMENT '电话号码',
  `qq` varchar(10) COLLATE utf8_swedish_ci DEFAULT NULL COMMENT 'QQ号',
  `email` varchar(32) COLLATE utf8_swedish_ci DEFAULT NULL COMMENT '南邮邮箱',
  `dept_id` bigint(20) unsigned NOT NULL,
  `is_deleted` tinyint(1) DEFAULT '1',
  PRIMARY KEY (`id`),
  UNIQUE KEY `stuid` (`stuid`),
  KEY `mem_dept_fk` (`dept_id`),
  CONSTRAINT `mem_dept_fk` FOREIGN KEY (`dept_id`) REFERENCES `department` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_swedish_ci;

/*Data for the table `mem` */

/*Table structure for table `mem_affair` */

DROP TABLE IF EXISTS `mem_affair`;

CREATE TABLE `mem_affair` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `mem_id` bigint(20) unsigned NOT NULL,
  `affair_id` bigint(20) unsigned NOT NULL,
  `is_deleted` tinyint(1) DEFAULT '1',
  PRIMARY KEY (`id`),
  KEY `mem_fk` (`mem_id`),
  KEY `affair_fk` (`affair_id`),
  CONSTRAINT `affair_fk` FOREIGN KEY (`affair_id`) REFERENCES `affair` (`id`),
  CONSTRAINT `mem_fk` FOREIGN KEY (`mem_id`) REFERENCES `mem` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_swedish_ci;

/*Data for the table `mem_affair` */

/*Table structure for table `super_admin` */

DROP TABLE IF EXISTS `super_admin`;

CREATE TABLE `super_admin` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(32) COLLATE utf8_swedish_ci NOT NULL,
  `password` char(32) COLLATE utf8_swedish_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_swedish_ci;

/*Data for the table `super_admin` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
