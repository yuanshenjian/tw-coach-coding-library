# OTR培训题库

## 1. 简介
本工程包含了2017OTR应届生培训的练习题，由`First Project`和`Springboot Web开发`两大块组成。

`First Project`目标是让学生从零开始创建一个Gradle项目，了解掌握Gradle工程的结构和原理，掌握如何管理项目的依赖以及如何使用Gradle做自动化构建和测试。其中了几道题算术统计题来考察学生对Java基础的使用，其中使用到了Java 8 Stream API。

`Springboot Web开发`以一个简化版的电商网站为背景，以用户下订单的User Journey来学习Spring boot的用法，其中包含了ER图分析、`Spring Security`、`Spring MVC`、`Spring Data JPA`、`Spring IOC`、`Spring AOP`等核心知识点。另外集成了Flyway，旨在让学生掌握数据库Migration管理以及SQL的写法。

整个习题库中习题的完成建议采用TDD的方式。


## 2. 详情

### 2.1 First Project

First Project包含以下内容：

1. 从零开始，创建一个Java Gradle工程（创建过程）。
2. 认识Gradle工程结构，掌握Gralde构建工具基础用法。
	- 掌握build.gradle文件中的各个部分的作用。
	- 应用Springboot插件和JUnit 5插件。
	- 引入JUnit 5相关的依赖。
	- 使用Gradle进行编译、测试、构建。
3. Java 基础练习
	- 统计偶数的和。
	- 统计字符并排序。
	- 生成随机序列。

关于Java基础练习，详情请参阅`com.thoughtworks.star.firstproject`包中的类，测试位于`com.thoughtworks.star.firstproject.CalculatorTest`中。


### 2.2 Spring Boot Web开发

#### User Journey
Spring Boot Web以一个简化版的电子商城为背景，利用Spring相关知识点来完成User Journey，User Journey如下图所示：

![User journey](https://github.com/sjyuan-cc/tw-coach-coding-library/raw/master/user-journey.png)


#### 知识点

完成上述User Journey所涉及的相关知识点包含：

1. 三层架构，Repository, Service, UI（postman）。
	- Repository: `com.thoughtworks.star.repository`包。
	- Service: com.thoughtworks.star.service`包。
2. Spring Security
	- `com.thoughtworks.star.configuration.security`包中的相关类。 
2. Spring MVC。
	- `GreetingController`
	- `AuthenticationController`
	- `UserController`
	- `ItemController`
	- `ShoppingCartController`	
3. Spring Data JPA
	- `ItemRepository`
	- `UserRepository`
4. Spring AOP
	- `ControllerExceptionAdvice`，全局异常处理切面
5. Spring IOC
	- 各个Bean中的依赖注入。
6. ER（`com.thoughtworks.star.entity`包中的Entity）
	- One to one
	- One to Many
	- Many to one
	- Many to Many
7. Flyway
	- `recource/db/migration`目录下的SQL脚本。
8. 测试
	- `src/test/java/`目录下所有`*Test`类。

#### 习题分布


关于User Journey的API，请参阅`src/test/java/`目录下`com.thoughtworks.star.api`包中的测试说明。


## 构建

### 运行测试

```
$ ./gradlew test
```

### 运行服务

```
$ ./gradlew bootRun
```

需要启动MySQL服务，服务信息如下：

- username: root  
- password: dev  
- port: 3306  
- database: future_star  






