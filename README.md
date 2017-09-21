

1. 了解前端Ajax使用
2. 学会使用Maven
3. 数据库设计
4. 关于Servlet3.0的新技术
5. cookie和Session

# token 实现授权

JSON Web Token（JWT）是一个非常轻巧的规范。这个规范允许我们使用JWT在用户和服务器之间传递安全可靠的信息。

http://www.cnblogs.com/xiekeli/p/5607107.html

[使用Cookie记住密码、自动登录](http://1017401036.iteye.com/blog/2205844)

1. 登录成功并且勾选自动登录
2. 服务器生成token返回给前端
3. 再次登录时，服务器检查token有效期
4. 如果过期则使用refresh_token再次请求数据，如果refresh_token过期了，需要重新登录
eyJ1c2VybmFtZSI6Inl3YWlubGkiLCJkZXZpY2UiOjAsImlzX3N0YWZmIjpmYWxzZSwiaWQiOjU5NjM5MTU1LCJleHAiOjE0OTYxMzM0OTl9
**JWT安全性**
1. jwt有两种方式，一种是秘钥算法
