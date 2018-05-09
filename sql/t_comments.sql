/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50173
Source Host           : localhost:3306
Source Database       : story

Target Server Type    : MYSQL
Target Server Version : 50173
File Encoding         : 65001

Date: 2018-05-09 18:31:54
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_comments
-- ----------------------------
DROP TABLE IF EXISTS `t_comments`;
CREATE TABLE `t_comments` (
  `coid` int(10) NOT NULL AUTO_INCREMENT,
  `cid` int(10) DEFAULT NULL,
  `created` int(10) DEFAULT NULL,
  `author` varchar(200) DEFAULT NULL,
  `author_id` int(10) DEFAULT NULL,
  `owner_id` int(10) DEFAULT NULL,
  `mail` varchar(200) DEFAULT NULL,
  `url` varchar(200) DEFAULT NULL,
  `ip` varchar(64) DEFAULT NULL,
  `agent` varchar(200) DEFAULT NULL,
  `content` text,
  `type` varchar(16) DEFAULT NULL,
  `status` varchar(16) DEFAULT NULL,
  `parent` int(10) DEFAULT NULL,
  PRIMARY KEY (`coid`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_comments
-- ----------------------------
INSERT INTO `t_comments` VALUES ('1', '1', '1525759419', '老张', '0', '1', '449246146@qq.com', 'http://www.baidu.com', '0', 'hi,我是测试', null, null, null, '0');
INSERT INTO `t_comments` VALUES ('2', '1', '1525759493', 'admin', '1', '1', '449246146@qq.com', 'http://www.baidu.com', '0', '测试不准进', null, null, null, '0');
INSERT INTO `t_comments` VALUES ('3', '2', '1525759627', '老张', '0', '1', '449246146@qq.com', 'http://www.baidu.com', '0', '这是文章么?明明是代码', null, null, null, '0');
INSERT INTO `t_comments` VALUES ('4', '2', '1525759678', 'admin', '1', '1', '449246146@qq.com', 'http://www.baidu.com', '0', '废话,知道还问', null, null, null, '0');
