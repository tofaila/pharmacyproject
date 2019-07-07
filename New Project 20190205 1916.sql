-- MySQL Administrator dump 1.4
--
-- ------------------------------------------------------
-- Server version	5.0.41-community-nt


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


--
-- Create schema pharmacyproject
--

CREATE DATABASE IF NOT EXISTS pharmacyproject;
USE pharmacyproject;

--
-- Definition of table `sale`
--

DROP TABLE IF EXISTS `sale`;
CREATE TABLE `sale` (
  `id` int(10) unsigned NOT NULL auto_increment,
  `medi_name` varchar(45) NOT NULL,
  `qty` int(10) unsigned NOT NULL,
  `price` double NOT NULL,
  `amount` double NOT NULL,
  `discount` double NOT NULL,
  `netamount` double NOT NULL,
  `date` date NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `sale`
--

/*!40000 ALTER TABLE `sale` DISABLE KEYS */;
INSERT INTO `sale` (`id`,`medi_name`,`qty`,`price`,`amount`,`discount`,`netamount`,`date`) VALUES 
 (1,'Napa',12,5,60,5,55,'2019-02-02'),
 (2,'Calcuim',10,5,50,5,45,'2019-02-05');
/*!40000 ALTER TABLE `sale` ENABLE KEYS */;


--
-- Definition of table `sale_demo`
--

DROP TABLE IF EXISTS `sale_demo`;
CREATE TABLE `sale_demo` (
  `id` int(10) unsigned NOT NULL auto_increment,
  `medicine_name` varchar(40) NOT NULL default 'ZERO',
  `qty` int(10) unsigned NOT NULL,
  `pprice` double NOT NULL,
  `sprice` double NOT NULL,
  `sale_date` date NOT NULL,
  `customer_name` varchar(45) NOT NULL,
  `address` varchar(45) NOT NULL,
  `phone_no` varchar(45) NOT NULL,
  PRIMARY KEY  USING BTREE (`id`,`medicine_name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `sale_demo`
--

/*!40000 ALTER TABLE `sale_demo` DISABLE KEYS */;
INSERT INTO `sale_demo` (`id`,`medicine_name`,`qty`,`pprice`,`sprice`,`sale_date`,`customer_name`,`address`,`phone_no`) VALUES 
 (1,'Napa',5,3,5,'2019-02-03','','',''),
 (2,'Atenolol',0,15,20,'2019-02-04','','',''),
 (3,'Atenolol',0,15,20,'2019-02-04','','','');
/*!40000 ALTER TABLE `sale_demo` ENABLE KEYS */;


--
-- Definition of table `stock`
--

DROP TABLE IF EXISTS `stock`;
CREATE TABLE `stock` (
  `id` int(10) unsigned NOT NULL auto_increment,
  `medi_name` varchar(45) NOT NULL,
  `comp_name` varchar(45) NOT NULL,
  `qty` int(10) unsigned NOT NULL,
  `price` double NOT NULL,
  `expairy_date` varchar(45) NOT NULL,
  `manu_date` varchar(45) NOT NULL,
  `buying_price` double NOT NULL,
  `selling_price` double NOT NULL,
  PRIMARY KEY  (`id`,`medi_name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `stock`
--

/*!40000 ALTER TABLE `stock` DISABLE KEYS */;
INSERT INTO `stock` (`id`,`medi_name`,`comp_name`,`qty`,`price`,`expairy_date`,`manu_date`,`buying_price`,`selling_price`) VALUES 
 (1,'Econate+','incepta',8,40,'01/03/19','01/03/18',35,40);
/*!40000 ALTER TABLE `stock` ENABLE KEYS */;


--
-- Definition of table `stuff`
--

DROP TABLE IF EXISTS `stuff`;
CREATE TABLE `stuff` (
  `id` int(10) unsigned NOT NULL auto_increment,
  `s_name` varchar(45) NOT NULL,
  `address` varchar(45) NOT NULL,
  `age` int(10) unsigned NOT NULL,
  `salary` double NOT NULL,
  `joining_date` varchar(45) NOT NULL,
  PRIMARY KEY  USING BTREE (`id`,`s_name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `stuff`
--

/*!40000 ALTER TABLE `stuff` DISABLE KEYS */;
INSERT INTO `stuff` (`id`,`s_name`,`address`,`age`,`salary`,`joining_date`) VALUES 
 (1,'Ab Salam','Dhaka',20,8000,'01/02/15');
/*!40000 ALTER TABLE `stuff` ENABLE KEYS */;




/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
