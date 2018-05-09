/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50173
Source Host           : localhost:3306
Source Database       : story

Target Server Type    : MYSQL
Target Server Version : 50173
File Encoding         : 65001

Date: 2018-05-09 18:32:04
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_logs
-- ----------------------------
DROP TABLE IF EXISTS `t_logs`;
CREATE TABLE `t_logs` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `action` varchar(100) DEFAULT NULL,
  `data` varchar(2000) DEFAULT NULL,
  `author_id` int(10) DEFAULT NULL,
  `ip` varchar(20) DEFAULT NULL,
  `created` int(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_logs
-- ----------------------------
INSERT INTO `t_logs` VALUES ('1', '初始化站点', null, '1', null, '1525759224');
INSERT INTO `t_logs` VALUES ('2', '登录后台', 'admin', '1', '0', '1525759237');
