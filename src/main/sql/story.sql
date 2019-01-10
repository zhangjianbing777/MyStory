/*
 Navicat Premium Data Transfer

 Source Server         : mysqldatabase
 Source Server Type    : MySQL
 Source Server Version : 50721
 Source Host           : localhost
 Source Database       : story

 Target Server Type    : MySQL
 Target Server Version : 50721
 File Encoding         : utf-8

 Date: 10/13/2018 13:50:35 PM
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
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `t_comments`
-- ----------------------------
BEGIN;
INSERT INTO `t_comments` VALUES ('1', '1', '1525759419', '李四', '0', '1', '449246146@qq.com', 'http://www.baidu.com', '0', '你好，我是测试', '你好，我是测试', null, 'approved', '0'), ('2', '1', '1525759493', 'admin', '1', '1', '449246146@qq.com', 'http://www.baidu.com', '0', '你好，我是测试', '你好，我是测试', null, 'approved', '0'), ('3', '2', '1525759627', '张三', '0', '1', '449246146@qq.com', 'http://www.baidu.com', '0', '你好，我是测试', '你好，我是测试', null, 'approved', '0'), ('4', '2', '1525759678', 'admin', '1', '1', '449246146@qq.com', 'http://www.baidu.com', '0', '你好，我是测试', '你好，我是测试', null, 'approved', '0'), ('5', '2', '1526114076', '大大兵', '0', '1', '449246146@qq.com', '', '0:0:0:0:0:0:0:1', '你好，我是测试', '你好，我是测试', null, 'approved', '0'), ('6', '2', '1526114385', '大大兵', '0', '1', '449246146@qq.com', 'http://www.baidu.com', '0:0:0:0:0:0:0:1', '你好，我是测试', '中文乱码到底是什么原因呢', null, 'approved', '0'), ('7', '1', '1526179741', '老郑', '0', null, '123456@qq.com', '', '0:0:0:0:0:0:0:1', null, '我就是来测一下评论好不好使～', null, 'approved', '0'), ('8', '1', '1526179849', '热心网友', '0', null, '', '', '0:0:0:0:0:0:0:1', null, 'qqqqq', null, 'approved', '0'), ('9', '1', '1526179963', '热心网友', '0', null, '', '', '0:0:0:0:0:0:0:1', null, 'nihao asdf', null, 'approved', '0'), ('10', '1', '1526179963', '热心网友', '0', null, null, null, '0:0:0:0:0:0:0:1', null, '666666', null, 'approved', '0'), ('11', '1', '1526179963', '热心网友', '0', null, null, null, '0:0:0:0:0:0:0:1', null, '777777', null, 'approved', '0'), ('12', '12', '1526209606', '热心网友', '0', null, '', '', '0:0:0:0:0:0:0:1', null, '你老妹儿哦～', null, 'approved', '0'), ('13', '12', '1526215516', '热心网友', '0', null, '', '', '0:0:0:0:0:0:0:1', null, 'bug君，出来吧', null, 'approved', null), ('14', '12', '1526215861', '热心网友', '0', null, '', '', '0:0:0:0:0:0:0:1', null, '咋回事捏？？？', null, 'approved', null), ('15', '12', '1526216174', '热心网友', '0', null, '', '', '0:0:0:0:0:0:0:1', null, '哟哟哟，切克闹', null, 'approved', null), ('16', '12', '1526216308', '热心网友', '0', null, '', '', '0:0:0:0:0:0:0:1', null, '怎么不行呢？', null, 'approved', null), ('17', '12', '1526216945', '热心网友', '0', null, '', '', '0:0:0:0:0:0:0:1', null, '呀呀呀呀呀', null, 'approved', null), ('18', '12', '1526217118', '热心网友', '0', null, '', '', '0:0:0:0:0:0:0:1', null, '啊啊啊啊啊啊啊啊', null, 'approved', null), ('19', '12', '1526217375', '热心网友', '0', null, '', '', '0:0:0:0:0:0:0:1', null, '这下评论功能好了吧！哈哈', null, 'approved', null), ('20', '12', '1526217536', '热心网友', '0', null, '', '', '0:0:0:0:0:0:0:1', null, '很多字体的测试', null, 'approved', null), ('21', '13', '1526304214', '热心网友', '0', null, '', '', '0:0:0:0:0:0:0:1', null, '哇，北京的夏天真是热啊哦啊哈', null, 'approved', null), ('22', '1', '1533397855', 'XX-XX-内网IP网友', '0', null, '', '', '0:0:0:0:0:0:0:1', 'XX-XX-内网IP', '00000000000', null, 'approved', '0'), ('23', '61', '1535555234', '热心网友', '0', null, '', '', '0:0:0:0:0:0:0:1', null, '111111111111', null, 'not_audit', '0');
COMMIT;

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
  `fmy_type` varchar(16) DEFAULT NULL,
  `tags` varchar(200) DEFAULT NULL,
  `categories` varchar(200) DEFAULT NULL,
  `hits` int(10) DEFAULT NULL,
  `comments_num` int(10) DEFAULT NULL,
  `allow_comment` int(10) DEFAULT NULL,
  `allow_ping` int(10) DEFAULT NULL,
  `allow_feed` int(10) DEFAULT NULL,
  PRIMARY KEY (`cid`)
) ENGINE=InnoDB AUTO_INCREMENT=64 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `t_contents`
-- ----------------------------
BEGIN;
INSERT INTO `t_contents` VALUES ('1', 'MyStory个人网站', 'about', '', '1487853610', '1525759365', '## Hello  World.\r\n\r\n> 第一篇文章，我该写点啥呢？？？\r\n\r\n----------\r\n\r\n\r\n<!--more-->\r\n\r\n```java\r\npublic static void main(String[] args){\r\n    System.out.println(\\\"Hello Tale.\\\");\r\n}\r\n```', '1', 'page', 'publish', 'markdown', null, null, '21', '6', '1', '1', '1'), ('2', '第一篇文章', null, '', '1487861184', '1487872798', '## Hello  World.\r\n\r\n> 第一篇文章，我该写点啥呢？？？\r\n\r\n----------\r\n\r\n\r\n<!--more-->\r\n\r\n```java\r\npublic static void main(String[] args){\r\n    System.out.println(\\\"Hello Tale.\\\");\r\n}\r\n```', '1', 'post', 'publish', 'markdown', null, '默认分类', '17', '4', '1', '1', '1'), ('3', '友情链接', 'links', '', '1505643727', '1505643888', '## å‹æƒ…é“¾æŽ¥\r\n\r\n- :lock: [çŽ‹çˆµçš„æŠ€æœ¯åšå®¢]()\r\n- :lock: [cyang.tech]()\r\n- :lock: [Bakumon\'s Blog]()\r\n\r\n## é“¾æŽ¥é¡»çŸ¥\r\n\r\n> è¯·ç¡®å®šè´µç«™å¯ä»¥ç¨³å®šè¿è¥\r\n> åŽŸåˆ›åšå®¢ä¼˜å…ˆï¼ŒæŠ€æœ¯ç±»åšå®¢ä¼˜å…ˆï¼Œè®¾è®¡ã€è§†è§‰ç±»åšå®¢ä¼˜å…ˆ\r\n> ç»å¸¸è¿‡æ¥è®¿é—®å’Œè¯„è®ºï¼Œçœ¼ç†Ÿçš„\r\n\r\nå¤‡æ³¨ï¼šé»˜è®¤ç”³è¯·å‹æƒ…é“¾æŽ¥å‡ä¸ºå†…é¡µï¼ˆå½“å‰é¡µé¢ï¼‰\r\n\r\n## åŸºæœ¬ä¿¡æ¯\r\n\r\n                ç½‘ç«™åç§°ï¼šTaleåšå®¢\r\n                ç½‘ç«™åœ°å€ï¼šhttps://tale.biezhi.me\r\n\r\nè¯·åœ¨å½“é¡µé€šè¿‡è¯„è®ºæ¥ç”³è¯·å‹é“¾ï¼Œå…¶ä»–åœ°æ–¹ä¸äºˆå›žå¤\r\n\r\næš‚æ—¶å…ˆè¿™æ ·ï¼ŒåŒæ—¶æ¬¢è¿Žäº’æ¢å‹é“¾ï¼Œè¿™ä¸ªé¡µé¢ç•™è¨€å³å¯ã€‚ ^_^\r\n\r\nè¿˜æœ‰ï¼Œæˆ‘ä¼šä¸å®šæ—¶å¯¹æ— æ³•è®¿é—®çš„ç½‘å€è¿›è¡Œæ¸…ç†ï¼Œè¯·ä¿è¯è‡ªå·±çš„é“¾æŽ¥é•¿æœŸæœ‰æ•ˆã€‚', '1', 'page', 'publish', 'markdown', null, null, '2', '0', '1', '1', null), ('4', '第二篇文章', null, null, '1487861185', '1487872798', '## Hello  World.\r\n\r\n> 第一篇文章，我该写点啥呢？？？\r\n\r\n----------\r\n\r\n\r\n<!--more-->\r\n\r\n```java\r\npublic static void main(String[] args){\r\n    System.out.println(\\\"Hello Tale.\\\");\r\n}\r\n```', '1', 'post', 'publish', 'markdown', null, '默认分类', '25', '17', '1', '1', '1'), ('5', '第三篇文章', null, null, '1487861186', '1487872798', '## Hello  World.\r\n\r\n> 第一篇文章，我该写点啥呢？？？\r\n\r\n----------\r\n\r\n\r\n<!--more-->\r\n\r\n```java\r\npublic static void main(String[] args){\r\n    System.out.println(\\\"Hello Tale.\\\");\r\n}\r\n```', '1', 'post', 'publish', 'markdown', null, '默认分类', '16', '5', '1', '1', '1'), ('6', '第四篇文章', null, null, '1487861187', '1487872798', '## Hello  World.\r\n\r\n> 第一篇文章，我该写点啥呢？？？\r\n\r\n----------\r\n\r\n\r\n<!--more-->\r\n\r\n```java\r\npublic static void main(String[] args){\r\n    System.out.println(\\\"Hello Tale.\\\");\r\n}\r\n```', '1', 'post', 'publish', 'markdown', null, '默认分类', '12', '6', '1', '1', '1'), ('7', '第五篇文章', null, null, '1487861188', '1487872798', '## Hello  World.\r\n\r\n> 第一篇文章，我该写点啥呢？？？\r\n\r\n----------\r\n\r\n\r\n<!--more-->\r\n\r\n```java\r\npublic static void main(String[] args){\r\n    System.out.println(\\\"Hello Tale.\\\");\r\n}\r\n```', '1', 'post', 'publish', 'markdown', null, '默认分类', '32', '7', '1', '1', '1'), ('8', '第六篇文章', null, null, '1487861189', '1487872798', '## Hello  World.\r\n\r\n> 第一篇文章，我该写点啥呢？？？\r\n\r\n----------\r\n\r\n\r\n<!--more-->\r\n\r\n```java\r\npublic static void main(String[] args){\r\n    System.out.println(\\\"Hello Tale.\\\");\r\n}\r\n```', '1', 'post', 'publish', 'markdown', null, '默认分类', '465', '8', '1', '1', '1'), ('9', '第七篇文章', null, null, '1487861190', '1487872798', '## Hello  World.\r\n\r\n> 第一篇文章，我该写点啥呢？？？\r\n\r\n----------\r\n\r\n\r\n<!--more-->\r\n\r\n```java\r\npublic static void main(String[] args){\r\n    System.out.println(\\\"Hello Tale.\\\");\r\n}\r\n```', '1', 'post', 'publish', 'markdown', null, '默认分类', '654', '9', '1', '1', '1'), ('10', '第八篇文章', '', null, '1487861191', '1487872798', '## Hello  World.\r\n\r\n> 第一篇文章，我该写点啥呢？？？\r\n\r\n----------\r\n\r\n\r\n<!--more-->\r\n\r\n    public static void main(){\r\n    }\r\n    \r\n    public static void main(String[] args){\r\n        System.out.println(\\\"Hello Tale.\\\");\r\n    }\r\n\r\n    public static void main(String[] args){\r\n        System.out.println(\\\"Hello Tale.\\\");\r\n    }\r\n\r\n```java\r\npublic static void main(String[] args){\r\n    System.out.println(\\\"Hello Tale.\\\");\r\n}\r\n```', '1', 'post', 'publish', 'markdown', '', '默认分类', '910', '10', '1', '1', '1'), ('12', '111', null, null, '1526209494', '1526209494', '周末愉快吗？\r\n------\r\n\r\n今天是周末哦，我很愉快的码了一天的代码哦\r\n\r\n\r\n<!--more-->\r\n\r\n感觉这个博客页面简洁的同学扣个1', '1', 'post', 'publish', null, 'test', '默认分类', '10', '9', '1', '1', '1'), ('13', '北京的夏天', null, null, '1526303228', '1526303228', '## 北京的夏天和河北的夏天有什么不同？ ##\r\n\r\n 1. 这个问题比较难回答。\r\n 2. 其实也很简单。\r\n 3. 要是真的问有什么不同的话，其实都一样，猴特么热，就对了。', '1', 'post', 'publish', null, '随笔', '默认分类', '16', '1', '1', '1', '1'), ('14', '第九篇文章', null, null, '1528724096', '1528724096', '23人玩儿玩儿', '1', 'post', 'publish', null, '啦啦', '技术类', '2', '0', '1', '1', '1'), ('15', '10篇文章', null, null, '1528724124', '1528724124', '23423热234234', '1', 'post', 'publish', null, '嘿嘿', '技术类', '1', '0', '1', '1', '1'), ('16', '11文章', null, null, '1528724136', '1528724136', '撒的发顺丰', '1', 'post', 'publish', null, '', '默认分类', '0', '0', '1', '1', '1'), ('17', '12sad 饭撒的发', null, null, '1528724144', '1528724144', '收到发生的发', '1', 'post', 'publish', null, '', '默认分类', '0', '0', '1', '1', '1'), ('18', '13色淡粉色大放送大', null, null, '1528724153', '1528724153', '收到发生的发', '1', 'post', 'publish', null, '', '默认分类', '0', '0', '1', '1', '1'), ('19', '1423让他为访问', null, null, '1528724162', '1528724162', '送饭撒发顺丰', '1', 'post', 'publish', null, '', '默认分类', '0', '0', '1', '1', '1'), ('20', '15收到发生大幅撒', null, null, '1528724193', '1528724193', '撒大放送大发', '1', 'post', 'publish', null, '', '默认分类', '0', '0', '1', '1', '1'), ('21', '16是大幅撒大放送大发的撒', null, null, '1528724201', '1528724201', '收到发生的发', '1', 'post', 'publish', null, '', '默认分类', '0', '0', '1', '1', '1'), ('22', '17会感觉会更健康', null, null, '1528724210', '1528724210', '回家开花结果看', '1', 'post', 'publish', null, '', '默认分类', '0', '0', '1', '1', '1'), ('23', '18回家看风景h', null, null, '1528724218', '1528724218', ' 刚好经过合法机构', '1', 'post', 'publish', null, '', '默认分类', '0', '0', '1', '1', '1'), ('24', '19刚好放假的发个地方', null, null, '1528724226', '1528724226', '风光好风光好风光好', '1', 'post', 'publish', null, '', '默认分类', '0', '0', '1', '1', '1'), ('25', '20245哇特人体个人过', null, null, '1528724242', '1528724242', '大风好大风过后发光闪闪发光好分割还都是废话发的时光收到风光', '1', 'post', 'publish', null, '', '默认分类', '0', '0', '1', '1', '1'), ('26', '21sadf sdaf', null, null, '1528725052', '1528725052', 'sdf sdfasdfsdafsafsd', '1', 'post', 'publish', null, '', '默认分类', '0', '0', '1', '1', '1'), ('27', '22dcvd', null, null, '1528725059', '1528725059', 'sdfsadf sa', '1', 'post', 'publish', null, '', '默认分类', '0', '0', '1', '1', '1'), ('28', '23', null, null, '1528725066', '1528725066', 'safasdfsadfsad', '1', 'post', 'publish', null, '', '默认分类', '2', '0', '1', '1', '1'), ('29', '24', null, null, '1528725072', '1528725072', 'asdfsadfasdfas', '1', 'post', 'publish', null, '', '默认分类', '0', '0', '1', '1', '1'), ('30', '25', null, null, '1528725078', '1528725078', 'sdfsadfsadfsd', '1', 'post', 'publish', null, '', '默认分类', '0', '0', '1', '1', '1'), ('31', '26', null, null, '1528725083', '1528725083', 'asdfsadfasdfs', '1', 'post', 'publish', null, '', '默认分类', '0', '0', '1', '1', '1'), ('32', '27', null, null, '1528725089', '1528725089', 'asdfasdfsdf', '1', 'post', 'publish', null, '', '默认分类', '0', '0', '1', '1', '1'), ('54', '28', null, null, '1528725864', '1528725864', 'eryeryrtey', '1', 'post', 'publish', null, '', '默认分类', '1', '0', '1', '1', '1'), ('55', '29', null, null, '1528725871', '1528725871', 'rtyrtyrtyrt', '1', 'post', 'publish', null, '', '默认分类', '2', '0', '1', '1', '1'), ('56', '30', null, null, '1528725878', '1528725878', 'rtyrtyrty', '1', 'post', 'publish', null, '', '默认分类', '1', '0', '1', '1', '1'), ('57', '31', null, null, '1528725885', '1528725885', 'rtyrtyrty', '1', 'post', 'publish', null, '', '默认分类', '1', '0', '1', '1', '1'), ('58', '32', null, null, '1528725891', '1528725891', 'rtyrtyrty', '1', 'post', 'publish', null, '', '默认分类', '2', '0', '1', '1', '1'), ('59', '33', null, null, '1528725899', '1528725899', 'rtyrtyrty', '1', 'post', 'publish', null, '', '默认分类', '3', '0', '1', '1', '1'), ('60', '34', null, null, '1528725910', '1528725910', 'rtyrtyrtyrtyrt', '1', 'post', 'publish', null, '', '默认分类', '4', '0', '1', '1', '1'), ('61', 'admin测试～～～', null, null, '1528725915', '1528725915', 'rtyrtyrtyr', '2', 'post', 'publish', null, '', '默认分类', '12', '1', '1', '1', '1'), ('62', '标签测试001', null, null, '1537448406', '1537448406', '111111', '1', 'post', 'publish', null, '标签测试001', '技术类', '1', '0', '1', '1', '1'), ('63', '标签测试002', '', null, '1537448443', '1537448479', '1112221', '1', 'post', 'publish', null, '标签测试001,标签测试002', '技术类', '0', '0', '1', '1', '1');
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
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `t_logs`
-- ----------------------------
BEGIN;
INSERT INTO `t_logs` VALUES ('1', 'åˆå§‹åŒ–ç«™ç‚¹', null, '1', null, '1525759224'), ('2', 'ç™»å½•åŽå°', 'admin', '1', '0', '1525759237'), ('3', '访客访问', '中国-北京-北京', null, '0:0:0:0:0:0:0:1', '1528373332'), ('4', '访客访问', null, null, null, '1528373332'), ('5', '访客访问', null, null, null, '1528373332'), ('6', '访客访问', null, null, null, '1528373332'), ('7', '访客访问', null, null, null, '1528373332'), ('8', '访客访问', null, null, null, '1528373332'), ('9', '访客访问', null, null, null, '1528373332'), ('10', '访客访问', null, null, null, '1528373332'), ('11', '访客访问', null, null, null, '1528373332'), ('12', '访客访问', null, null, null, '1528373332'), ('13', '访客访问', null, null, null, '1528373332'), ('14', '访客访问', null, null, null, '1528373332'), ('15', '访客访问', null, null, null, '1528373332'), ('16', '访客访问', null, null, null, '1528373332'), ('17', '访客访问', null, null, '0:0:0:0:0:0:0:1', '1528471651'), ('18', '访客访问', null, null, '0:0:0:0:0:0:0:1', '1528473552'), ('19', '访客访问', null, null, '0:0:0:0:0:0:0:1', '1528639723'), ('20', '访客访问', null, null, '0:0:0:0:0:0:0:1', '1528724005'), ('21', '访客访问', null, null, '0:0:0:0:0:0:0:1', '1528724989'), ('22', '访客访问', null, null, '0:0:0:0:0:0:0:1', '1528726032'), ('23', '访客访问', null, null, '0:0:0:0:0:0:0:1', '1528726647'), ('24', '访客访问', null, null, '0:0:0:0:0:0:0:1', '1528727182'), ('25', '访客访问', null, null, '0:0:0:0:0:0:0:1', '1528727391'), ('26', '访客访问', null, null, '0:0:0:0:0:0:0:1', '1528727652'), ('27', '访客访问', null, null, '0:0:0:0:0:0:0:1', '1528727779'), ('28', '访客访问', null, null, '0:0:0:0:0:0:0:1', '1528728044'), ('29', '访客访问', null, null, '0:0:0:0:0:0:0:1', '1528730246'), ('30', '访客访问', null, null, '0:0:0:0:0:0:0:1', '1528730302'), ('31', '访客访问', null, null, '0:0:0:0:0:0:0:1', '1528890363'), ('32', '访客访问', null, null, '0:0:0:0:0:0:0:1', '1528895043'), ('33', '访客访问', null, null, '0:0:0:0:0:0:0:1', '1528897126');
COMMIT;

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
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `t_metas`
-- ----------------------------
BEGIN;
INSERT INTO `t_metas` VALUES ('1', '默认分类', null, 'category', null, '0', '0'), ('2', 'baidu', 'https://www.baidu.com', 'link', '', '0', '0'), ('3', '技术类', null, 'category', null, null, null), ('4', '随笔', null, 'tag', null, null, null), ('5', '啦啦', '啦啦', 'tag', null, null, null), ('6', '嘿嘿', '嘿嘿', 'tag', null, null, null), ('7', '标签测试001', '标签测试001', 'tag', null, null, null), ('8', '标签测试002', '标签测试002', 'tag', null, null, null);
COMMIT;

-- ----------------------------
--  Table structure for `t_options`
-- ----------------------------
DROP TABLE IF EXISTS `t_options`;
CREATE TABLE `t_options` (
  `name` varchar(255) NOT NULL,
  `value` text,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `t_options`
-- ----------------------------
BEGIN;
INSERT INTO `t_options` VALUES ('article_image_location', 'nmyswls/article/image/', null);
INSERT INTO `t_options` VALUES ('other_image_location', 'nmyswls/other/image/', null);
INSERT INTO `t_options` VALUES ('qiniu_accesskey', '', null);
INSERT INTO `t_options` VALUES ('qiniu_bucket', '', null);
INSERT INTO `t_options` VALUES ('qiniu_domain', 'image.nmyswls.com/', null);
INSERT INTO `t_options` VALUES ('qiniu_secretkey', '', null);
INSERT INTO `t_options` VALUES ('site_description', '本站是70KG的技术分享博客。涵盖生活故事、专注Java后端技术、SpringBoot、SpringCloud、微服务架构、高可用架构、中间件使用、系统监控等相关的研究与知识分享。', null);
INSERT INTO `t_options` VALUES ('site_keywords', '农码一生,农码一生为了谁,70KG,70KG个人博客,zhangjianbing,张建兵,springboot,springcloud,java,并发编程,jvm,架构,技术分享,技术干货,系统架构,个人网站,生活随笔,开发者,编程,代码,开源,IT网站,IT技术博客', null);
INSERT INTO `t_options` VALUES ('site_record', '京ICP备18029773号', null);
INSERT INTO `t_options` VALUES ('site_theme', 'default', null);
INSERT INTO `t_options` VALUES ('site_title', '农码一生为了谁', null);
INSERT INTO `t_options` VALUES ('site_url', 'https://www.nmyswls.com', null);
INSERT INTO `t_options` VALUES ('social_github', '', null);
INSERT INTO `t_options` VALUES ('social_mayun', 'https://gitee.com/beijinglogic', null);
INSERT INTO `t_options` VALUES ('social_twitter', 'https://gitee.com/beijinglogic', null);
INSERT INTO `t_options` VALUES ('social_weibo', 'https://weibo.com/beijinglogic', null);
INSERT INTO `t_options` VALUES ('social_zhihu', '', null);
COMMIT;

-- ----------------------------
--  Table structure for `t_permission`
-- ----------------------------
DROP TABLE IF EXISTS `t_permission`;
CREATE TABLE `t_permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `url` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `t_permission`
-- ----------------------------
BEGIN;
INSERT INTO `t_permission` VALUES ('1', '/user/create', 'create'), ('2', '/user/delete', 'delete'), ('3', '/user/update', 'update'), ('4', '/user/retrieve', 'retrieve');
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
INSERT INTO `t_relationships` VALUES ('2', '1'), ('13', '4'), ('14', '5'), ('14', '3'), ('15', '6'), ('15', '3'), ('16', '1'), ('17', '1'), ('18', '1'), ('19', '1'), ('20', '1'), ('21', '1'), ('22', '1'), ('23', '1'), ('24', '1'), ('25', '1'), ('26', '1'), ('27', '1'), ('28', '1'), ('29', '1'), ('30', '1'), ('31', '1'), ('32', '1'), ('54', '1'), ('55', '1'), ('56', '1'), ('57', '1'), ('58', '1'), ('59', '1'), ('60', '1'), ('61', '1'), ('62', '7'), ('62', '3'), ('63', '7'), ('63', '8'), ('63', '3');
COMMIT;

-- ----------------------------
--  Table structure for `t_role`
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role` (
  `id` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `t_role`
-- ----------------------------
BEGIN;
INSERT INTO `t_role` VALUES ('1', 'admin', '超级管理员'), ('2', 'test', '测试账户');
COMMIT;

-- ----------------------------
--  Table structure for `t_role_permission`
-- ----------------------------
DROP TABLE IF EXISTS `t_role_permission`;
CREATE TABLE `t_role_permission` (
  `rid` int(11) DEFAULT NULL,
  `pid` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `t_role_permission`
-- ----------------------------
BEGIN;
INSERT INTO `t_role_permission` VALUES ('1', '2'), ('1', '3'), ('2', '1'), ('1', '1');
COMMIT;

-- ----------------------------
--  Table structure for `t_user_role`
-- ----------------------------
DROP TABLE IF EXISTS `t_user_role`;
CREATE TABLE `t_user_role` (
  `user_id` int(11) DEFAULT NULL,
  `rid` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `t_user_role`
-- ----------------------------
BEGIN;
INSERT INTO `t_user_role` VALUES ('1', '1'), ('2', '2');
COMMIT;

-- ----------------------------
--  Table structure for `t_users`
-- ----------------------------
DROP TABLE IF EXISTS `t_users`;
CREATE TABLE `t_users` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `username` varchar(64) NOT NULL,
  `password` varchar(64) NOT NULL,
  `email` varchar(100) DEFAULT NULL,
  `home_url` varchar(255) DEFAULT NULL,
  `screen_name` varchar(100) DEFAULT NULL,
  `created` int(10) DEFAULT NULL,
  `activated` int(10) DEFAULT NULL,
  `logged` int(10) DEFAULT NULL,
  `group_name` varchar(16) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `t_users`
-- ----------------------------
BEGIN;
INSERT INTO `t_users` VALUES ('1', 'admin', '9400feec28101f42f299a0d95794a6a3', '449246146@qq.com', null, '老张', '1525759224', null, '1525759237', null), ('2', 'zhangsan', '123456', '449246146@qq.com', null, '老张', '1525759224', null, '1525759237', null);
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
INSERT INTO `t_visit` VALUES ('1', '133', null);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
