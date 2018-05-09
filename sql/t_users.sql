/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50173
Source Host           : localhost:3306
Source Database       : story

Target Server Type    : MYSQL
Target Server Version : 50173
File Encoding         : 65001

Date: 2018-05-09 18:32:27
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_users
-- ----------------------------
DROP TABLE IF EXISTS `t_users`;
CREATE TABLE `t_users` (
  `uid` int(10) NOT NULL AUTO_INCREMENT,
  `username` varchar(64) NOT NULL,
  `password` varchar(64) NOT NULL,
  `email` varchar(100) DEFAULT NULL,
  `home_url` varchar(255) DEFAULT NULL,
  `screen_name` varchar(100) DEFAULT NULL,
  `created` int(10) DEFAULT NULL,
  `activated` int(10) DEFAULT NULL,
  `logged` int(10) DEFAULT NULL,
  `group_name` varchar(16) DEFAULT NULL,
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_users
-- ----------------------------
INSERT INTO `t_users` VALUES ('1', 'admin', 'eafbecf0972a4e9bd59d0ece6371490e', '449246146@qq.com', null, 'admin', '1525759224', null, '1525759237', null);
