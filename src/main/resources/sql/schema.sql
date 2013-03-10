/*
Navicat MySQL Data Transfer

Target Server Type    : MYSQL
Target Server Version : 50067
File Encoding         : 65001

Date: 2013-03-08 18:04:41
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `oauth2_token`
-- ----------------------------
DROP TABLE IF EXISTS `oauth2_token`;
CREATE TABLE `oauth2_token` (
  `id` int(11) unsigned NOT NULL auto_increment,
  `alias_id` int(11) unsigned NOT NULL,
  `access_token` varchar(128) NOT NULL default '',
  `refresh_token` varchar(128) NOT NULL default '',
  `time` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  PRIMARY KEY  (`id`),
  UNIQUE KEY `idx_alias_id` (`alias_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1748 DEFAULT CHARSET=utf8 COMMENT='oauth2_token';

-- ----------------------------
-- Records of oauth2_token
-- ----------------------------

-- ----------------------------
-- Table structure for `status`
-- ----------------------------
DROP TABLE IF EXISTS `status`;
CREATE TABLE `status` (
  `id` int(11) unsigned NOT NULL auto_increment,
  `user_id` int(11) unsigned NOT NULL,
  `origin_id` varchar(20) NOT NULL default '0',
  `create_time` timestamp NOT NULL default '0000-00-00 00:00:00',
  `site` varchar(2) NOT NULL,
  `category` smallint(4) NOT NULL,
  `content` varchar(300) NOT NULL,
  `title` varchar(150) NOT NULL default '',
  `time` bigint(20) NOT NULL,
  PRIMARY KEY  (`id`),
  UNIQUE KEY `idx_origin` (`origin_id`,`site`,`category`),
  KEY `idx_create_time` (`create_time`),
  KEY `idx_uid` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='status';

-- ----------------------------
-- Records of status
-- ----------------------------

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) unsigned NOT NULL auto_increment,
  `uid` varchar(16) NOT NULL default '',
  `name` varchar(63) NOT NULL default '',
  `email` varchar(63) NOT NULL default '',
  `session_id` varchar(16) default NULL,
  `time` bigint(20) NOT NULL,
  PRIMARY KEY  (`id`),
  UNIQUE KEY `idx_uid` (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='user';

-- ----------------------------
-- Records of user
-- ----------------------------

-- ----------------------------
-- Table structure for `user_alias`
-- ----------------------------
DROP TABLE IF EXISTS `user_alias`;
CREATE TABLE `user_alias` (
  `id` int(11) unsigned NOT NULL auto_increment,
  `type` varchar(10) NOT NULL default '',
  `user_id` int(11) unsigned NOT NULL default '0',
  `alias` varchar(128) NOT NULL default '',
  `time` bigint(20) NOT NULL,
  PRIMARY KEY  (`id`),
  UNIQUE KEY `idx_alias_type` (`alias`,`type`),
  KEY `idx_uid` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='alias';

-- ----------------------------
-- Records of user_alias
-- ----------------------------

-- ----------------------------
-- Table structure for `user_bak`
-- ----------------------------
DROP TABLE IF EXISTS `user_bak`;
CREATE TABLE `user_bak` (
  `id` int(11) NOT NULL auto_increment,
  `username` varchar(100) default NULL,
  `nickname` varchar(100) default NULL,
  `email` varchar(100) default NULL,
  `description` varchar(255) default NULL,
  `privacy` tinyint(4) default NULL,
  `remind` tinyint(4) default NULL,
  `sina_weibo_uid` varchar(20) default NULL,
  `sina_weibo_token` varchar(50) default NULL,
  `qq_weibo_uid` varchar(20) default NULL,
  `qq_weibo_token` varchar(50) default NULL,
  `register_date` bigint(20) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=40 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_bak
-- ----------------------------
INSERT INTO `user_bak` VALUES ('37', '___1867649773', null, null, null, null, null, '1867649773', '2.008BT5CCLG4l2Bfd201502ba0gVeBg', null, null, '1361779195025');
INSERT INTO `user_bak` VALUES ('38', 'nomospace', null, null, null, null, null, null, null, '801312468', '2.008BT5CCLG4l2Bf2a8d0da70QmU23D', '1361779202731');
INSERT INTO `user_bak` VALUES ('39', '1760951922', null, null, null, null, null, '1760951922', '2.003CmKvBLG4l2Ba372f14dbb_dvxTD', null, null, '1362646047578');
