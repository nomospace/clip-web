-- ----------------------------
-- Table structure for `jam_user`
-- ----------------------------
DROP TABLE IF EXISTS `jam_user`;
CREATE TABLE `jam_user` (
  `id` int(11) NOT NULL auto_increment,
  `username` varchar(100) default NULL,
  `nickname` varchar(100) default NULL,
  `email` varchar(100) default NULL,
  `description` varchar(255) default NULL,
  `privacy` tinyint(4) default NULL,
  `remind` tinyint(4) default NULL,
  `sina_weibo_uid` int(11) default NULL,
  `sina_weibo_token` varchar(50) default NULL,
  `qq_weibo_uid` varchar(255) default NULL,
  `qq_weibo_token` varchar(50) default NULL,
  `register_date` bigint(20) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;
