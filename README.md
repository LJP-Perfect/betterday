BetterDay
===========================
### 项目介绍
---
此为后台项目地址，前端项目地址：https://github.com/LJP-Perfect/betterday-web。

BetterDay项目是以H5形式展示的一款实用性记录管理应用，目前BetterDay包含三大功能：习惯打卡、日程安排、团队管理。

该项目是我Web课程的课程设计,时间不是很多,且自己技术有限,项目中肯定存在很多问题和BUG,但是做了之后自己很想把这个项目做好做全,后续也会不断更新。

:smile:有问题交流或者建议可以联系邮箱:`13106058519@163.com`或者QQ:`871560465`



#### 项目演示
-----------
- http://47.101.163.255:8080
- 优化做的不是很好，所以首屏加载会有点慢（大约10秒左右）
- 测试账号：freelee 密码：1234（也可以自行注册一个账号）



### 部署启动
-----------
> 后台
1. 克隆源码到本地，使用IDEA或eclipse打开
2. 数据库新建betterday数据库,导入resources/better.sql文件
3. 修改配置中的数据库配置和`util`包下的`AliyunOSSClientUtil`相关配置
4. 启动项目即可

> 前端
1. 克隆源码到本地
2. 修改config/index.js和src/util/require.js相关Host和Port配置
3. npm install、npm run dev启动即可




