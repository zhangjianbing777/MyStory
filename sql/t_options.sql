/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50173
Source Host           : localhost:3306
Source Database       : story

Target Server Type    : MYSQL
Target Server Version : 50173
File Encoding         : 65001

Date: 2018-05-09 18:32:18
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_options
-- ----------------------------
DROP TABLE IF EXISTS `t_options`;
CREATE TABLE `t_options` (
  `name` varchar(255) NOT NULL,
  `value` text,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_options
-- ----------------------------
INSERT INTO `t_options` VALUES ('allow_install', '0', '是否允许重新安装博客');
INSERT INTO `t_options` VALUES ('site_description', '博客系统,Blade框架,Tale', null);
INSERT INTO `t_options` VALUES ('site_keywords', '博客系统,Blade框架,Tale', null);
INSERT INTO `t_options` VALUES ('site_theme', 'default', null);
INSERT INTO `t_options` VALUES ('site_title', '农码一生', null);
INSERT INTO `t_options` VALUES ('site_url', 'http://localhost:9000', null);
INSERT INTO `t_options` VALUES ('social_github', null, null);
INSERT INTO `t_options` VALUES ('social_twitter', null, null);
INSERT INTO `t_options` VALUES ('social_weibo', null, null);
INSERT INTO `t_options` VALUES ('social_zhihu', null, null);
