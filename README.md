# androidquickstart 项目目标
**v0.1.0 20150122**

1. 快速构建一个互联网类型的Android App框架
2. 花费最低的成本来构建一个App, 例如无需服务器投入也能进行正常的App发布/更新/推送
3. 集成了许多实用且免费的第三方库来解决我们日常开发会遇到的问题

## 集成的第三方工具(服务)
做选型的时候尽量考虑选用同一家公司(平台)的, 避免需要在多个平台进行分开管理, 造成管理不便.

这些都只是自己的个人推荐而已, 并非所谓的最佳实践, 如有什么不妥, 请自行替换成其他合适的第三方服务.

1. [友盟应用统计分析/在线参数](http://www.umeng.com/analytics)

   umeng-analytics-v5.2.4.jar
2. [友盟消息推送](http://www.umeng.com/push)

   umeng-message-v1.5.0.jar
3. [友盟用户反馈](http://www.umeng.com/component_feedback)

   [com.umeng.fb(v5.0.1)](https://github.com/androidquickstart/androidquickstart/tree/master/com.umeng.fb)
4. [友盟自动更新](http://www.umeng.com/component_update)

   [com.umeng.update(v2.5.0)](https://github.com/androidquickstart/androidquickstart/tree/master/com.umeng.update)

要使用以上服务需要拥有友盟帐号, 在[友盟服务平台](https://www.umeng.com/sso/login?service=http://www.umeng.com/users/login_redirect)上进行相关的设置, 请自行阅读相关SDK的文档来替换一些配置项, 例如UMENG_APPKEY

## 使用手册
1. 请整个项目clone到本地或者[download](https://github.com/androidquickstart/androidquickstart/archive/master.zip)到本地
2. AndroidQuickStart项目为主工程, 其他项目为依赖的lib工程
3. 需要将这些项目都导入到eclipse中
4. 最终运行AndroidQuickStart项目即可

## 测试用的App签名
keystore: AndroidQuickStart/app.keystore

password: 123456

alias: androidquickstart

password: 123456