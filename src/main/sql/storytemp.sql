/*
 Navicat Premium Data Transfer

 Source Server         : mysqldatabase
 Source Server Type    : MySQL
 Source Server Version : 50721
 Source Host           : localhost
 Source Database       : storytemp

 Target Server Type    : MySQL
 Target Server Version : 50721
 File Encoding         : utf-8

 Date: 08/23/2018 22:50:48 PM
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `t_attach`
-- ----------------------------
DROP TABLE IF EXISTS `t_attach`;
CREATE TABLE `t_attach` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `fname` varchar(100) DEFAULT NULL,
  `ftype` varchar(50) DEFAULT NULL,
  `fkey` varchar(100) DEFAULT NULL,
  `author_id` int(10) DEFAULT NULL,
  `created` int(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `t_comments`
-- ----------------------------
DROP TABLE IF EXISTS `t_comments`;
CREATE TABLE `t_comments` (
  `coid` int(10) NOT NULL AUTO_INCREMENT,
  `cid` int(10) DEFAULT NULL COMMENT '文章的id',
  `created` int(10) DEFAULT NULL COMMENT '创建时间unix',
  `author` varchar(200) DEFAULT NULL COMMENT '评论作者',
  `author_id` int(10) DEFAULT NULL COMMENT '评论所属用户的id',
  `owner_id` int(10) DEFAULT NULL COMMENT '评论所属内容作者id',
  `mail` varchar(200) DEFAULT NULL COMMENT '评论者的邮箱',
  `url` varchar(200) DEFAULT NULL COMMENT '评论者的网址',
  `ip` varchar(64) DEFAULT NULL COMMENT '评论者的ip地址',
  `agent` varchar(200) DEFAULT NULL COMMENT '评论者的客户端用的什么',
  `content` text COMMENT '评论的内容',
  `type` varchar(16) DEFAULT NULL COMMENT '评论的类型',
  `status` varchar(16) DEFAULT NULL COMMENT '评论的审核状态',
  `parent` int(10) DEFAULT NULL COMMENT '父级评论',
  PRIMARY KEY (`coid`)
) ENGINE=InnoDB AUTO_INCREMENT=47 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `t_contents`
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
  `fmt_type` varchar(16) DEFAULT NULL,
  `tags` varchar(200) DEFAULT NULL,
  `categories` varchar(200) DEFAULT NULL,
  `hits` int(10) DEFAULT NULL,
  `comments_num` int(10) DEFAULT NULL,
  `allow_comment` int(10) DEFAULT NULL,
  `allow_ping` int(10) DEFAULT NULL,
  `allow_feed` int(10) DEFAULT NULL,
  PRIMARY KEY (`cid`)
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `t_contents`
-- ----------------------------
BEGIN;
INSERT INTO `t_contents` VALUES ('50', '欢迎光临', null, null, '1535035551', '1535035551', '> 欢迎欢迎热烈欢迎，yeah～', '1', 'post', 'publish', null, 'welcome', '默认分类', '0', '0', '1', '1', '1');
COMMIT;

-- ----------------------------
--  Table structure for `t_logs`
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
) ENGINE=InnoDB AUTO_INCREMENT=938 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `t_metas`
-- ----------------------------
DROP TABLE IF EXISTS `t_metas`;
CREATE TABLE `t_metas` (
  `mid` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `slug` varchar(255) DEFAULT NULL,
  `type` varchar(32) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `sort` int(10) DEFAULT NULL,
  `parent` int(10) DEFAULT NULL,
  PRIMARY KEY (`mid`)
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `t_metas`
-- ----------------------------
BEGIN;
INSERT INTO `t_metas` VALUES ('40', 'welcome', 'welcome', 'tag', null, null, null), ('41', '默认分类', '默认分类', 'category', null, null, null), ('42', '农码一生为了谁', 'https://www.nmyswls.com', 'link', '', '0', null);
COMMIT;

-- ----------------------------
--  Table structure for `t_options`
-- ----------------------------
DROP TABLE IF EXISTS `t_options`;
CREATE TABLE `t_options` (
  `name` varchar(255) NOT NULL COMMENT '配置名称',
  `value` text COMMENT '值',
  `description` varchar(255) DEFAULT NULL COMMENT '配置描述',
  PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `t_options`
-- ----------------------------
BEGIN;
INSERT INTO `t_options` VALUES ('allow_install', '', '是否允许重新安装博客'), ('site_description', '本站是70KG的技术分享博客。涵盖生活故事、专注Java后端技术、SpringBoot、SpringCloud、微服务架构、高可用架构、中间件使用、系统监控等相关的研究与知识分享。', null), ('site_keywords', '农码一生,农码一生为了谁,70KG,70KG个人博客,zhangjianbing,张建兵,springboot,springcloud,java,并发编程,jvm,架构,技术分享,技术干货,系统架构,个人网站,生活随笔,开发者,编程,代码,开源,IT网站,IT技术博客', null), ('site_record', '京ICP备18029773号', null), ('site_theme', 'default', null), ('site_title', '农码一生为了谁', null), ('site_url', 'https://www.nmyswls.com', null), ('social_github', '', null), ('social_mayun', 'https://gitee.com/beijinglogic', null), ('social_twitter', 'https://gitee.com/beijinglogic', null), ('social_weibo', 'https://weibo.com/beijinglogic', null), ('social_zhihu', '', null);
COMMIT;

-- ----------------------------
--  Table structure for `t_relationships`
-- ----------------------------
DROP TABLE IF EXISTS `t_relationships`;
CREATE TABLE `t_relationships` (
  `cid` int(10) DEFAULT NULL,
  `mid` int(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `t_relationships`
-- ----------------------------
BEGIN;
INSERT INTO `t_relationships` VALUES ('50', '40'), ('50', '41');
COMMIT;

-- ----------------------------
--  Table structure for `t_users`
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
--  Records of `t_users`
-- ----------------------------
BEGIN;
INSERT INTO `t_users` VALUES ('1', 'admin', '9400feec28101f42f299a0d95794a6a3', 'zhangjianbing777@gmail.com', null, '老张', null, null, null, null);
COMMIT;

-- ----------------------------
--  Table structure for `t_visit`
-- ----------------------------
DROP TABLE IF EXISTS `t_visit`;
CREATE TABLE `t_visit` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `count` int(11) DEFAULT NULL,
  `modify_time` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `t_visit`
-- ----------------------------
BEGIN;
INSERT INTO `t_visit` VALUES ('1', '2', null);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
