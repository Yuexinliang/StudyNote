# spring整合mybatis
## 1.创建项目：
我创建的是maven项目，主要需要的就是导入以下依赖：
```xml
<dependencies>
        <!--要保证spring的依赖版本一致，不然会出现版本冲突-->
        <!--lombok注解-->
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <version>1.18.22</version>
        </dependency>

        <!-- mybatis核心包-->
        <dependency>
            <groupId>org.mybatis</groupId>
            <artifactId>mybatis</artifactId>
            <version>3.5.6</version>
        </dependency>

        <!-- mysql驱动包 -->
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <version>8.0.23</version>
        </dependency>

        <!-- 日志包，方便看sql语句 -->
        <dependency>
            <groupId>org.slf4j</groupId>
            <artifactId>slf4j-log4j12</artifactId>
            <version>1.6.1</version>
        </dependency>
    
        <!--进行junit单元测试 -->
        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
            <version>4.11</version>
            <scope>test</scope>
        </dependency>

        <!--依赖于commons-logging日志管理 -->
        <dependency>
            <groupId>commons-logging</groupId>
            <artifactId>commons-logging</artifactId>
            <version>1.2</version>
        </dependency>

        <!--提供了框架的基本组成部分,包括IOC 和 DI-->
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-core</artifactId>
            <version>4.1.6.RELEASE</version>
        </dependency>

        <!-- 提供了BeanFactory-->
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-beans</artifactId>
            <version>4.1.6.RELEASE</version>
        </dependency>

        <!--上下文配置对象，提供一个框架式的对象访问方式-->
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-context</artifactId>
            <version>4.1.6.RELEASE</version>
        </dependency>

        <!--提供了强大的表达式语言-->
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-expression</artifactId>
            <version>4.1.6.RELEASE</version>
        </dependency>
    
        <!-- ====注解式声明切面  -->
        <dependency>
            <groupId>org.aspectj</groupId>
            <artifactId>aspectjweaver</artifactId>
            <version>1.9.1</version>
        </dependency>
    
        <!--整合Spring + Mybatis -->
        <dependency>
            <groupId>org.mybatis</groupId>
            <artifactId>mybatis-spring</artifactId>
            <version>1.3.0</version>
        </dependency>

        <!-- 导入dbcp的jar包，用来在spring-mybatis.xml中配置数据库 -->
        <dependency>
            <groupId>commons-dbcp</groupId>
            <artifactId>commons-dbcp</artifactId>
            <version>1.4</version>
        </dependency>
        
        <!--TX事务管理-->
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-tx</artifactId>
            <version>4.1.6.RELEASE</version>
        </dependency>

        <!--对Spring 对JDBC 数据访问进行封装的所有类-->
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-jdbc</artifactId>
            <version>4.1.6.RELEASE</version>
        </dependency>
    </dependencies>

    <!--逆向工程的依赖配置-->
    <build>
        <finalName>你的项目名</finalName>
        <!-- 构建过程中用到的插件 -->
        <plugins>
            <!-- 具体插件，逆向工程的操作是以构建过程中插件形式出现的 -->
            <plugin>
                <groupId>org.mybatis.generator</groupId>
                <artifactId>mybatis-generator-maven-plugin</artifactId>
                <version>1.3.5</version>
                <!-- 插件的依赖 -->
                <dependencies>
                    <!-- 逆向工程的核心依赖 -->
                    <dependency>
                        <groupId>org.mybatis.generator</groupId>
                        <artifactId>mybatis-generator-core</artifactId>
                        <version>1.3.5</version>
                    </dependency>
                    
                    <dependency>
                        <groupId>mysql</groupId>
                        <artifactId>mysql-connector-java</artifactId>
                        <version>8.0.23</version>
                    </dependency>
                </dependencies>
            </plugin>
        </plugins>
    </build>
```
## 将配置文件放在resources下
### jdbc.properties
```properties
#注意要将driver改为driverClass，username改为user防止spring中的重复属性冲突。
driverClass=com.mysql.cj.jdbc.Driver
url=jdbc:mysql://127.0.0.1:3306/studz10b?useSSL=false&useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai&allowPublicKeyRetrieval=true
user=root
password=root
```
### log4j.properties
```properties
### set log levels ###
log4j.rootLogger = debug ,  stdout ,  D

### ?????? ###
log4j.appender.stdout = org.apache.log4j.ConsoleAppender
log4j.appender.stdout.Target = System.out
log4j.appender.stdout.layout = org.apache.log4j.PatternLayout
log4j.appender.stdout.layout.ConversionPattern =  %d{ABSOLUTE} %5p %c{1}:%L - %m%n

### ??????? ###
log4j.appender.A = org.apache.log4j.DailyRollingFileAppender
log4j.appender.A.File = logs/log.log
log4j.appender.A.Append = true
## ??DEBUG???????
log4j.appender.A.Threshold = DEBUG
log4j.appender.A.layout = org.apache.log4j.PatternLayout
log4j.appender.A.layout.ConversionPattern = %-d{yyyy-MM-dd HH:mm:ss}  [%t:%r] - [%p]  %m%n

### ??????????? ###
log4j.appender.D = org.apache.log4j.DailyRollingFileAppender
## ???????
log4j.appender.D.File = logs/error.log
log4j.appender.D.Append = true
## ???ERROR???????!!!
log4j.appender.D.Threshold = ERROR
log4j.appender.D.layout = org.apache.log4j.PatternLayout
log4j.appender.D.layout.ConversionPattern = %-d{yyyy-MM-dd HH:mm:ss}  [%t:%r] - [%p]  %m%n
```
### generatorConfig.xml
```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE generatorConfiguration
        PUBLIC "-//mybatis.org//DTD MyBatis Generator Configuration 1.0//EN"
        "http://mybatis.org/dtd/mybatis-generator-config_1_0.dtd">

<generatorConfiguration>
    <!-- mybatis-generator:generate -->
    <context id="atguiguTables" targetRuntime="MyBatis3">
        <commentGenerator>
            <!-- 是否去除自动生成的注释 true:是;false:否 -->
            <property name="suppressAllComments" value="true"/>
        </commentGenerator>
        <!--数据库连接的信息：驱动类、连接地址、用户名、密码 -->
        <jdbcConnection
                driverClass="com.mysql.cj.jdbc.Driver"
                connectionURL="jdbc:mysql://127.0.0.1:3306/studz10bb?useSSL=false&amp;useUnicode=true&amp;characterEncoding=UTF-8&amp;serverTimezone=Asia/Shanghai&amp;allowPublicKeyRetrieval=true"
                userId="root"
                password="root">
        </jdbcConnection>
        <!-- 默认 false，把 JDBC DECIMAL 和 NUMERIC 类型解析为 Integer，为 true 时把
        JDBC DECIMAL
        和 NUMERIC 类型解析为 java.math.BigDecimal -->
        <javaTypeResolver>
            <property name="forceBigDecimals" value="false"/>
        </javaTypeResolver>
        <!-- targetProject:生成 Entity 类的路径 -->
        <javaModelGenerator targetProject=".\src\main\java"
                            targetPackage="com.jr.entity">
            <!-- enableSubPackages:是否让 schema 作为包的后缀 -->
            <property name="enableSubPackages" value="false"/>
            <!-- 从数据库返回的值被清理前后的空格 -->
            <property name="trimStrings" value="true"/>
        </javaModelGenerator>
        <!-- targetProject:XxxMapper.xml 映射文件生成的路径 -->
        <sqlMapGenerator targetProject=".\src\main\resources"
                         targetPackage="com.jr.mapper">
            <!-- enableSubPackages:是否让 schema 作为包的后缀 -->
            <property name="enableSubPackages" value="false"/>
        </sqlMapGenerator>
        <!-- targetPackage：Mapper 接口生成的位置 -->
        <javaClientGenerator type="XMLMAPPER"
                             targetProject=".\src\main\java"
                             targetPackage="com.jr.mapper">
            <!-- enableSubPackages:是否让 schema 作为包的后缀 -->
            <property name="enableSubPackages" value="false"/>
        </javaClientGenerator>
        <!-- 数据库表名字和我们的 entity 类对应的映射指定 -->
        <table tableName="orders" domainObjectName="Orders"/>
    </context>
</generatorConfiguration>
```
## 配置mybatis核心配置文件
mybatis.xml中的大部分配置都转移到spring中进行配置了，可以只进行一个别名配置
```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE configuration
        PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-config.dtd">
<configuration>
    <!--配置类型别名，将该包下的类被调用全限定包名时设置别名为包名首字母小写，但大写也可用-->
    <typeAliases>
        <package name="com.jr.entity"/>
        <package name="com.jr.pojo"/>
    </typeAliases>
</configuration>
```
## 配置spring核心配置文件

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:aop="http://www.springframework.org/schema/aop"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:tx="http://www.springframework.org/schema/tx"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
       http://www.springframework.org/schema/beans/spring-beans.xsd
       http://www.springframework.org/schema/aop
	   http://www.springframework.org/schema/aop/spring-aop-4.3.xsd
       http://www.springframework.org/schema/context
	   http://www.springframework.org/schema/context/spring-context-4.3.xsd
       http://www.springframework.org/schema/tx
       http://www.springframework.org/schema/tx/spring-tx-4.3.xsd">
       
       <!--加载外部属性文件，获得连接数据库四要素-->
       <context:property-placeholder location="classpath:jdbc.properties"
       <!--配置数据源-->
       <bean id="dataSource" class="org.apache.commons.dbcp.BasicDataSource"
            <property name="driverClassName" value="${driverClass}"/>
            <property name="url" value="${url}"/>
            <property name="username" value="${user}"/>
            <property name="password" value="${password}"/>
       </bean>
       <!-- 管理sqlsessionFactory对象,
        SqlSessionFactoryBean中默认配置了xml文件的位置，大概是与mapper的目录一致，不一致会找不到方法。 -->
        <bean id="factory" class="org.mybatis.spring.SqlSessionFactoryBean">
            <property name="dataSource" ref="dataSource"/>
            <property name="configLocation" value="classpath:mybatis.xml"/>
        </bean>
        <!--配置mapper映射文件扫描器，扫描的是接口并配置代理对象 -->
        <bean class="org.mybatis.spring.mapper.MapperScannerConfigurer">
            <property name="basePackage" value="com.jr.mapper"/>
            <property name="sqlSessionFactoryBeanName" value="factory"/>
        </bean>
        <!--配置注解扫描路径,扫描com.jr下所有包的注解,也包括扫描切面类的注解 -->
        <context:component-scan base-package="com.jr"/>
        <!--配置aop自动创建代理类-->
        <aop:aspectj-autoproxy/>
        
        <!--方法一：xml版配置事务传播策略与设置xml方式，两种方法选择其一即可-->

        <!--配置事务管理对象-->
        <bean id="txManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
        <property name="dataSource" ref="dataSource"/>
        </bean>
        
        <!--配置事务传播策略
        规范：传播策略都是配置在service层
        name属性值是 service实现类中的方法前缀；
        propagation 属性值 设置事务的传播策略；REQUIRED 有事务就加入；没有就创建（增，删，改）
                                          SUPPORTS 有事务就加入，没有也不创建（查询）
        事务的隔离级别，Spring框架采用默认方式，自动识别-->
        <tx:advice id="txAdvice" transaction-manager="txManager">
            <tx:attributes>
                <tx:method name="add*" propagation="REQUIRED" />
                <tx:method name="remove*" propagation="REQUIRED"/>
                <tx:method name="up*" propagation="REQUIRED"/>
                <tx:method name="get*" propagation="SUPPORTS"/>
            </tx:attributes>
        </tx:advice>
        <!--配置声明式事务的xml方式： -->
        <aop:config>
            <aop:pointcut id="servicePointcut" expression="execution(* com.jr.service.impl.DeptServiceImpl.*(..))"/>
            <aop:advisor advice-ref="txAdvice" pointcut-ref="servicePointcut"/>
        </aop:config>
        

</beans>
```
配置使用注解的声明式事务
```xml
        <!--方法二：注解版配置事务传播策略与设置xml方式，开启事务的注解驱动后还需要在想要实现事务管理的service实现类中标注注解-->

        <!--配置事务管理对象-->
        <bean id="txManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
            <property name="dataSource" ref="dataSource"/>
        </bean>
        
        <!--开启事务的注解驱动-->
        <tx:annotation-driven transaction-manager="txManager"/>
```
## 创建实体类
要点就是使用lombok进行方法的注解，以及进行spring获取bean的注解`@Component`
```
@Component //spring获取bean的注解
@NoArgsConstructor
@AllArgsConstructor
@Data
```
## 创建mapper接口
## 创建mapper.xml映射文件
放置在resources中，保证路径与mapper接口一致，例如接口的路径为com.jr.mapper，xml文件也要放在com/jr/mapper下
## 创建service的接口
## 创建service的实现类
service实现类中要标注spring中获取bean的注解`@Service`
如果是采用注解式的声明式事务实现事务管理的话标注注解`@Transactional (propagation= Propagation.REQUIRED)`
```java
package com.jr.service.impl;

import com.jr.entity.Dept;
import com.jr.mapper.DeptMapper;
import com.jr.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
/*添加事务传播策略的注解
* 设置当前业务类下， 所有业务方法,都遵守REQUIRED 传播策略；
REQUIRED ：有事务就加入，没事务就开启*/
@Transactional (propagation= Propagation.REQUIRED)
public class DeptServiceImpl implements DeptService {
    @Autowired/*byType类型注入*/
    private DeptMapper deptMapper;
    @Override
    public boolean upDept(Dept dept) {
        int i = 0 ;
        i = deptMapper.insertDept(dept);
        dept.setDname(null);
        i += deptMapper.updateDept(dept);
        if(i == 2){
            return true;
        }else {
            return false;
        }
    }
}
```
## 创建切面类
在util包下创建切面类，包含非业务的方法
spring获取bean的注解`@Component`
切面注解`@Aspect`，标注这个才能识别切点配置
然后在对应方法上标注执行时机的注解@Before，@After。使用表达式设置切点。
```java
package com.jr.util;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;


@Component
@Aspect
public class Advice {
    @Before ("execution(* com.jr.service.impl.DeptServiceImpl.*Dept(..)) || execution(* com.jr.service.impl.EmpServiceImpl.*Emp(..))")
    public void check(){
        System.out.println("权限检查..");
    }
    @After ("execution(* com.jr.service.impl.*ServiceImpl.*(..))")
    public void log(){
        System.out.println("日志记录");
    }

}
```
## 创建测试类
```java
import com.jr.entity.Dept;
import com.jr.entity.Emp;
import com.jr.pojo.EmpDept;
import com.jr.service.DeptService;
import com.jr.service.EmpService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import java.util.List;
public class TestSM {
    @Test
    public void testSM() {
        /*进行spring核心文件的获取*/
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
        /*获取spring中注册管理的对象*/
        DeptService deptService = applicationContext.getBean("deptServiceImpl", DeptService.class);
        List<Dept> all = deptService.getAll();
        for (Dept dept : all) {
            System.out.println(dept);
        }
        /*事务管理方法的测试*/
        Dept dept = applicationContext.getBean("dept",Dept.class);
        dept.setDeptno(450);
        dept.setDname("测试");
        dept.setLoc("sssss");
        boolean b = deptService.upDept(dept);
        System.out.println(b);
    }
}
```

事务的传播策略
>REQUIRED（默认）：如果当前没有事务，就创建一个新的事务。如果已经存在一个事务，就加入到这个事务中。这是最常用的传播策略。<br><br>
SUPPORTS：如果当前没有事务，就以非事务方式执行方法；如果已经存在一个事务，就加入到这个事务中。支持当前事务，但不会创建新事务。<br><br>
MANDATORY：方法必须在一个已存在的事务中执行，否则会抛出异常。如果没有事务存在，则会抛出 IllegalTransactionStateException 异常。<br><br>
REQUIRES_NEW：创建一个新的事务，如果已经存在一个事务，就挂起当前事务，并在方法执行完成后，恢复挂起的事务。这意味着方法总是会在自己的事务中执行。<br><br>
NOT_SUPPORTED：以非事务方式执行方法，如果当前存在事务，则挂起事务，执行完成后再恢复。这个策略适用于不需要事务支持的方法。<br><br>
NEVER：方法必须在没有活动事务的情况下执行，否则会抛出异常。如果已经存在一个事务，则会抛出 IllegalTransactionStateException 异常。<br><br>
NESTED：如果已经存在一个事务，就在一个嵌套的事务中执行方法。嵌套事务可以独立于外部事务进行提交或回滚。如果外部事务回滚，嵌套事务可以选择回滚或保存点。这个传播策略只在特定的事务管理器和数据库支持时才可用。<br><br>
NESTED（JDBC支持）：与上面的嵌套传播策略类似，但仅在使用 JDBC 事务管理时使用。这个传播策略是 Spring 3.0 引入的。
