/*
 Navicat Premium Data Transfer

 Source Server         : CentOS-shanlin
 Source Server Type    : MariaDB
 Source Server Version : 50547
 Source Host           : 192.168.9.3
 Source Database       : sldb

 Target Server Type    : MariaDB
 Target Server Version : 50547
 File Encoding         : utf-8

 Date: 06/13/2016 15:17:37 PM
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `t_user`
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `device_type` int(3) DEFAULT NULL COMMENT '设备类型,android 1 ios 2 ',
  `serial_number` varchar(52) DEFAULT NULL COMMENT '唯一序列号',
  `sw_version` varchar(10) DEFAULT NULL COMMENT '软件版本',
  `app_token` varchar(32) DEFAULT NULL,
  `hw_version` varchar(32) DEFAULT NULL COMMENT '硬件版本',
  `device_mode` varchar(32) DEFAULT NULL COMMENT '设备类型 如 iPhone5s',
  `token` varchar(32) DEFAULT NULL COMMENT '验证token',
  `create_token_time` timestamp NULL DEFAULT NULL COMMENT '创建token的时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

SET FOREIGN_KEY_CHECKS = 1;
