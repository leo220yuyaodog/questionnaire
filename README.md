## 问卷调查网站

- 技术栈：
  - 前端：Vue + Element-ui
  - 后端：Springboot + Mybatis-Plus + MySQL

- feature：
  - Swagger3.0 API文档 
  - 基础的用户状态管理和安全管理
  - 响应式布局
  - Material Design风格

## 运行

### step1: 环境

- Java 8,11 
- Maven
- node.js 16+
- yarn 或 npm
- mysql
### step2: 启动后端

#### 创建数据库

1. mysql手动创建数据库 `questionnaire`
2. 导入sql文件.  位置`src/main/resources/starvey.sql`

#### 修改application.yml

修改为自己的数据库用户和密码

```yml
spring:
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://localhost:3306/questionnaire?allowPublicKeyRetrieval=true&useUnicode=true&useSSL=false&characterEncoding=utf8&serverTimezone=Asia/Shanghai
    username: root  # 改为你的
    password: 123456  # 修改为你的
    type: com.alibaba.druid.pool.DruidDataSource
```

#### 启动 QuestionnaireApplication.java
位于 `src/main/java/com/starvey/QuestionnaireApplication.java`

### step3: 启动前端
yarn 换成 npm 也可以
1. 进入web目录

```shell
cd web
```

2. 安装依赖

```shell
yarn install 
```

3启动前端 

```shell
yarn start
```

打开浏览器，访问 `localhost:80`
