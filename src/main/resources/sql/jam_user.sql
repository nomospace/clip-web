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
  `sina_weibo_uid` int(11) default NULL,
  `sina_weibo_token` varchar(30) default NULL,
  `qq_weibo_uid` varchar(255) default NULL,
  `qq_weibo_token` varchar(30) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of jam_user
-- ----------------------------
INSERT INTO `jam_user` VALUES ('1', 'xxx', null, null, null, null, null, null, null, null, null);
INSERT INTO `jam_user` VALUES ('2', 'xxx', null, null, null, null, null, null, null, null, null);
