/*
Navicat MySQL Data Transfer

Source Server Version : 50067

Target Server Type    : MYSQL
Target Server Version : 50067
File Encoding         : 65001

Date: 2013-03-12 17:52:09
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
  `time` bigint(20) NOT NULL,
  PRIMARY KEY  (`id`),
  UNIQUE KEY `idx_alias_id` (`alias_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1749 DEFAULT CHARSET=utf8 COMMENT='oauth2_token';

-- ----------------------------
-- Records of oauth2_token
-- ----------------------------
INSERT INTO `oauth2_token` VALUES ('1748', '1867649773', '2.008BT5CCLG4l2Be382f3107bu6gqOB', '2.008BT5CCLG4l2Be382f3107bu6gqOB', '1363071668358');

-- ----------------------------
-- Table structure for `status`
-- ----------------------------
DROP TABLE IF EXISTS `status`;
CREATE TABLE `status` (
  `id` int(11) unsigned NOT NULL auto_increment,
  `user_id` int(11) unsigned NOT NULL,
  `origin_id` varchar(20) NOT NULL default '0',
  `create_time` bigint(20) NOT NULL,
  `site` varchar(2) NOT NULL,
  `category` smallint(4) NOT NULL,
  `content` varchar(300) NOT NULL,
  `title` varchar(150) default '',
  `time` bigint(20) NOT NULL,
  PRIMARY KEY  (`id`),
  UNIQUE KEY `idx_origin` (`origin_id`,`site`,`category`),
  KEY `idx_create_time` (`create_time`),
  KEY `idx_uid` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8 COMMENT='status';

-- ----------------------------
-- Records of status
-- ----------------------------
INSERT INTO `status` VALUES ('21', '1867649773', '3555061861087033', '1363076260000', '1', '1', 'asd', null, '1363080664474');
INSERT INTO `status` VALUES ('22', '1867649773', '3555061127314452', '1363076083000', '1', '1', 'xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx', null, '1363080664474');
INSERT INTO `status` VALUES ('23', '1867649773', '3555051106944840', '1363073697000', '1', '1', '#thepast.me# xxxxxxxxxxxxxxxxxxxxxxxxxxxxxx', null, '1363080664474');
INSERT INTO `status` VALUES ('24', '1867649773', '3555050947787829', '1363073656000', '1', '1', 'zxxxxxx', null, '1363080664474');
INSERT INTO `status` VALUES ('25', '1867649773', '3555050838464874', '1363073632000', '1', '1', 'sadasdsad', null, '1363080664490');
INSERT INTO `status` VALUES ('26', '1867649773', '3550003471699157', '1361870244000', '1', '1', 'wqqwe', null, '1363080664490');
INSERT INTO `status` VALUES ('27', '1867649773', '3547069966970203', '1361170844000', '1', '1', '我刚更新了春节版微博HD V3.2.0，带着这份喜气红红火火过大年！完美适配iPad mini，优化高清图览！你也快来下载体验吧：http://t.cn/zWy447r', null, '1363080664490');
INSERT INTO `status` VALUES ('28', '1867649773', '3542061372550083', '1359976701000', '1', '1', 'wwwwwwwwwwwwwwww', null, '1363080664490');
INSERT INTO `status` VALUES ('29', '1867649773', '3542061326607958', '1359976692000', '1', '1', 'qwewqe', null, '1363080664490');
INSERT INTO `status` VALUES ('30', '1867649773', '3542061217532102', '1359976663000', '1', '1', 'eqwewqe', null, '1363080664490');
INSERT INTO `status` VALUES ('31', '1867649773', '3542060664029506', '1359976531000', '1', '1', 'yu', null, '1363080664505');
INSERT INTO `status` VALUES ('32', '1867649773', '3542060588516874', '1359976515000', '1', '1', '#thepast.me# “[音乐盒]网易摄影独家专访5位优质摄影师，为你讲述文艺系情侣写真的拍摄秘诀！猛戳链接哟”tyttyty', null, '1363080664521');
INSERT INTO `status` VALUES ('33', '1867649773', '3542060546563991', '1359976505000', '1', '1', '#thepast.me# 洒洒的', null, '1363080664521');
INSERT INTO `status` VALUES ('34', '1867649773', '3540628971676223', '1359635188000', '1', '1', '弱水三千，只取一瓢。纷乱的世界中，这里是清晰的自己。分享挫人三2222的摄影名片 | 网易摄影 http://t.cn/zYUUyJFs', null, '1363080664537');
INSERT INTO `status` VALUES ('35', '1867649773', '3540628308832524', '1359635033000', '1', '1', '弱水三千，只取一瓢。纷乱的世界中，这里是清晰的自己。分享挫人三2222的摄影名片 | 网易摄影 http://t.cn/zYUUyJF', null, '1363080664537');
INSERT INTO `status` VALUES ('36', '1867649773', '3540627985802166', '1359634956000', '1', '1', '留住毕业这一刻 我在网易相册看到一个很有意思的相片！#网易相册 留住这一刻# 参加就送15张相片免费冲印和LOMO卡7折优惠券！http://t.cn/zWxNiJI', null, '1363080664537');
INSERT INTO `status` VALUES ('37', '1867649773', '3540627755327984', '1359634900000', '1', '1', '“[音乐盒]网易摄影独家专访5位优质摄影师，为你讲述文艺系情侣写真的拍摄秘诀！猛戳链接哟”', null, '1363080664537');
INSERT INTO `status` VALUES ('38', '1867649773', '3540626836579451', '1359634683000', '1', '1', '弱水三千，只取一瓢。纷乱的世界中，这里是清晰的自己。分享挫人三2222的摄影名片 | 网易摄影 http://t.cn/zYUUyJF sdf', null, '1363080664552');
INSERT INTO `status` VALUES ('39', '1867649773', '3540626756870708', '1359634663000', '1', '1', '弱水三千，只取一瓢。纷乱的世界中，这里是清晰的自己。分享挫人三2222的摄影名片 | 网易摄影 http://t.cn/zYUUyJF', null, '1363080664552');
INSERT INTO `status` VALUES ('40', '1867649773', '3540626581004062', '1359634618000', '1', '1', '留住毕业这一刻 我在网易相册看到一个很有意思的相片！#网易相册 留住这一刻# 参加就送15张相片免费冲印和LOMO卡7折优惠券！http://t.cn/zWxNiJI', null, '1363080664568');
INSERT INTO `status` VALUES ('41', '1867649773', '3555083280797415', '1363081366000', '1', '1', 'xxxx', null, '1363081371286');

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) unsigned NOT NULL auto_increment,
  `uid` varchar(16) NOT NULL default '',
  `name` varchar(63) NOT NULL default '',
  `email` varchar(63) default '',
  `session_id` varchar(63) default NULL,
  `time` bigint(20) NOT NULL,
  PRIMARY KEY  (`id`),
  UNIQUE KEY `idx_uid` (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='user';

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', '1867649773', 'qatest2', null, 'F2A4E0CBB256B880305D04F75D6D9BF8', '1363071667671');
INSERT INTO `user` VALUES ('2', '1760951922', '1760951922', null, '61AF403DBE07F93C1BA182781FE7FB40', '1363081613452');

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
  KEY `idx_uid` (`user_id`),
  KEY `idx_alias_type` USING BTREE (`alias`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='alias';

-- ----------------------------
-- Records of user_alias
-- ----------------------------
INSERT INTO `user_alias` VALUES ('1', 'sina', '1867649773', '', '1363071667671');

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
