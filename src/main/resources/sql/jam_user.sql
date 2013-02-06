/*
Navicat MySQL Data Transfer

Source Server Version : 50067
Source Database       : clip

Target Server Type    : MYSQL
Target Server Version : 50067
File Encoding         : 65001

Date: 2013-02-06 18:02:31
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `jam_user`
-- ----------------------------
DROP TABLE IF EXISTS `jam_user`;
CREATE TABLE `jam_user` (
  `id` int(11) NOT NULL auto_increment,
  `username` varchar(100) NOT NULL,
  `nickname` varchar(100) default NULL,
  `email` varchar(100) default NULL,
  `description` varchar(255) default NULL,
  `privacy` tinyint(4) default NULL,
  `remind` tinyint(4) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of jam_user
-- ----------------------------
