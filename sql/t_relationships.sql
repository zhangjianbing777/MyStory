/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50173
Source Host           : localhost:3306
Source Database       : story

Target Server Type    : MYSQL
Target Server Version : 50173
File Encoding         : 65001

Date: 2018-05-09 18:32:22
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_relationships
-- ----------------------------
DROP TABLE IF EXISTS `t_relationships`;
CREATE TABLE `t_relationships` (
  `cid` int(10) DEFAULT NULL,
  `mid` int(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_relationships
-- ----------------------------
INSERT INTO `t_relationships` VALUES ('2', '1');
