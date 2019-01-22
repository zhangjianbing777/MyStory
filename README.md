# MyStory

#### 项目介绍

 **个人博客开源项目** 

 **首页地址为：https://www.nmyswls.com** 

 **后台管理：https://www.nmyswls.com/admin/login** 

#### 软件架构

软件架构说明：
- 核心框架：`SpringBoot2.0`
- 安全框架：`Apache Shiro 1.3.2`
- 缓存框架：`Redis 4.0`
- 任务调度：`quartz 2.3`
- 持久层框架：`MyBatis 3`
- 数据库连接池：`Alibaba Druid 1.0.2`
- 日志管理：`SLF4J 1.7、Log4j`
- 前端样式：`Tale`
- 上传框架：`DropZoneJs`

#### 安装教程

1. 需要jdk1.8+
2. maven环境，熟悉springboot
3. down代码到本地
4. 数据库文件放在sql中,直接导入到数据库中即可,数据库名字为story
5. 编译没错后,直接运行Application文件即可,访问地址为:localhost即可(默认端口为80)
6. 访问后台“/admin”或者“/admin/login”,用户名:admin 密码:123456
7. 七牛相关配置：
   - 进入后台设置页面，添加ACCESSKEY和SECRETKEY
   - DOMAIN：为七牛的默认外链域名，注意后面应该有斜杠
   - BUCKET：是七牛存储的空间名字
   - 文章图片地址：因为七牛没有文件夹系统，但可以分层，这个地址就是除了domain，后面自己定义的名字，也该有斜杠
   - 其它图片地址：暂时可以不填 
8. 如果部署到Linux或者安装过程中碰到问题，请加技术交流(4060038)

`博客安装终极教程：[https://gitee.com/beijinglogic/MyStory.wiki.git](http://)`

#### 说明
1. 最近深受百度统计的困扰特来说明，一定要把header中的百度统计代码替换成自己的
2. 数据库要用自己安装的，因为我测试库是加密了的([点这里][1])，并且固定IP才可以访问
3. redis也要用自己的，因为我这边设置了登录密码

#### 前台页面
 **首页** 

![输入图片说明](https://images.gitee.com/uploads/images/2018/0930/111904_8609d6e2_1705382.jpeg "menu.saveimg.savepath20180930111841.jpg")

**标签列表**

![标签列表](https://images.gitee.com/uploads/images/2018/0930/113723_413f18c0_1705382.jpeg "menu.saveimg.savepath20180930113705.jpg")

**归档页面**

![输入图片说明](https://images.gitee.com/uploads/images/2018/0930/113805_76139847_1705382.jpeg "menu.saveimg.savepath20180930113742.jpg")

#### 后台页面
**后台登录**

![输入图片说明](https://images.gitee.com/uploads/images/2018/0930/112038_5a91cf9f_1705382.jpeg "menu.saveimg.savepath20180930112013.jpg")

**后台页面展示**

![输入图片说明](https://images.gitee.com/uploads/images/2018/0930/112131_0c900467_1705382.jpeg "menu.saveimg.savepath20180930112106.jpg")

![输入图片说明](https://images.gitee.com/uploads/images/2018/0930/112300_016c60b6_1705382.jpeg "menu.saveimg.savepath20180930112250.jpg")

 **文章发布页面新增自动上传七牛云存储，支持多图、拖拽、删除，操作更便捷** 
![输入图片说明](https://images.gitee.com/uploads/images/2019/0110/145735_b03101b7_1705382.jpeg "menu.saveimg.savepath20190110145648.jpg")

#### 运行环境
- WEB服务器： `Tomcat`
- 数据库服务器： `Mysql5.7`
- 操作系统： `Windows、Linux`
#### 开发环境
 **建议开发者使用以下环境，这样避免版本带来的问题** 
- IDE: `IntelliJ IDEA`
- DB: `Mysql5.7 Redis(Window版本,Linux版本)`
- JDK: `JAVA 8`
- WEB容器: `Tomcat8+ （采用springboot框架开发时,并没有用到额外的tomcat 用的框架自带的）`

> QQ群：4060038

> 邮箱：zhangjianbing777@163.com

#### 使用说明

**分享技术，品味人生。欢迎fork，喜欢的话，给个star呗!**

  [1]: https://www.nmyswls.com/article/32/1
