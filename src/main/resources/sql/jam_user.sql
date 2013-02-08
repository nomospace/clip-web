-- 服务器版本: 5.5.27
-- PHP 版本: 5.3.15

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- 数据库: `clip_web`
--

-- --------------------------------------------------------

--
-- 表的结构 `jam_user`
--

CREATE TABLE IF NOT EXISTS `jam_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(100) NOT NULL,
  `nickname` varchar(100) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `privacy` tinyint(4) DEFAULT NULL,
  `remind` tinyint(4) DEFAULT NULL,
  `sina_weibo_uid` int(11) DEFAULT NULL,
  `sina_weibo_token` varchar(50) DEFAULT NULL,
  `qq_weibo_uid` varchar(255) DEFAULT NULL,
  `qq_weibo_token` varchar(50) DEFAULT NULL,
  `register_date` date NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 AUTO_INCREMENT=3 ;

--
-- 转存表中的数据 `jam_user`
--

INSERT INTO `jam_user` (`id`, `username`, `nickname`, `email`, `description`, `privacy`, `remind`, `sina_weibo_uid`, `sina_weibo_token`, `qq_weibo_uid`, `qq_weibo_token`, `register_date`) VALUES
(1, 'xxx', NULL, NULL, NULL, NULL, NULL, NULL, '2.003CmKvBLG4l2Ba372f14dbb_dvxTD', NULL, NULL, '0000-00-00'),
(2, 'xxx', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '0000-00-00');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
