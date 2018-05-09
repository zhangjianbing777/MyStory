/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50173
Source Host           : localhost:3306
Source Database       : story

Target Server Type    : MYSQL
Target Server Version : 50173
File Encoding         : 65001

Date: 2018-05-09 18:31:59
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_contents
-- ----------------------------
DROP TABLE IF EXISTS `t_contents`;
CREATE TABLE `t_contents` (
  `cid` int(10) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) DEFAULT NULL,
  `slug` varchar(255) DEFAULT NULL,
  `thumb_img` varchar(255) DEFAULT NULL,
  `created` int(10) DEFAULT NULL,
  `modified` int(10) DEFAULT NULL,
  `content` text,
  `author_id` int(10) DEFAULT NULL,
  `type` varchar(16) DEFAULT NULL,
  `status` varchar(16) DEFAULT NULL,
  `fmy_type` varchar(16) DEFAULT NULL,
  `tags` varchar(200) DEFAULT NULL,
  `categories` varchar(200) DEFAULT NULL,
  `hits` int(10) DEFAULT NULL,
  `comments_num` int(10) DEFAULT NULL,
  `allow_comment` int(10) DEFAULT NULL,
  `allow_ping` int(10) DEFAULT NULL,
  `allow_feed` int(10) DEFAULT NULL,
  PRIMARY KEY (`cid`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_contents
-- ----------------------------
INSERT INTO `t_contents` VALUES ('1', '关于', 'about', '', '1487853610', '1525759365', '### Hello World\r\n\r\n这是我的关于页面\r\n\r\n### 当然还有其他\r\n\r\n具体你来写点什么吧,\r\n\r\n没什么可写的啊.', '1', 'page', 'publish', 'markdown', null, null, '4', '2', '1', '1', '1');
INSERT INTO `t_contents` VALUES ('2', '第一篇文章', null, '', '1487861184', '1487872798', '## Hello  World.\r\n\r\n> 第一篇文章总得写点儿什么?...\r\n\r\n----------\r\n\r\n\r\n<!--more-->\r\n\r\n```java\r\npublic static void main(String[] args){\r\n    System.out.println(\\\"Hello Tale.\\\");\r\n}\r\n```', '1', 'post', 'publish', 'markdown', null, '默认分类', '14', '2', '1', '1', '1');
INSERT INTO `t_contents` VALUES ('3', '友情链接', 'links', '', '1505643727', '1505643888', '## 友情链接\r\n\r\n- :lock: [王爵的技术博客]()\r\n- :lock: [cyang.tech]()\r\n- :lock: [Bakumon\'s Blog]()\r\n\r\n## 链接须知\r\n\r\n> 请确定贵站可以稳定运营\r\n> 原创博客优先，技术类博客优先，设计、视觉类博客优先\r\n> 经常过来访问和评论，眼熟的\r\n\r\n备注：默认申请友情链接均为内页（当前页面）\r\n\r\n## 基本信息\r\n\r\n                网站名称：Tale博客\r\n                网站地址：https://tale.biezhi.me\r\n\r\n请在当页通过评论来申请友链，其他地方不予回复\r\n\r\n暂时先这样，同时欢迎互换友链，这个页面留言即可。 ^_^\r\n\r\n还有，我会不定时对无法访问的网址进行清理，请保证自己的链接长期有效。', '1', 'page', 'publish', 'markdown', null, null, '2', '0', '1', '1', null);
