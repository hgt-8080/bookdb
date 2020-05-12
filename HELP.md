 springboot整合mybatis,利用mybatis-genetor自动生成文件
 
 实现思路：
 1.添加依赖
    <?xml version="1.0" encoding="UTF-8"?>
    <project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
             xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
        <modelVersion>4.0.0</modelVersion>
        <parent>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-parent</artifactId>
            <version>2.2.7.RELEASE</version>
            <relativePath/> <!-- lookup parent from repository -->
        </parent>
        <groupId>com.bo</groupId>
        <artifactId>bookdb</artifactId>
        <version>0.0.1-SNAPSHOT</version>
        <name>bookdb</name>
        <description>Demo project for Spring Boot</description>
    
        <properties>
            <java.version>1.8</java.version>
        </properties>
    
        <dependencies>
    <!--        <dependency>-->
    <!--            <groupId>org.mybatis.generator</groupId>-->
    <!--            <artifactId>mybatis-generator-core</artifactId>-->
    <!--            <version>1.3.5</version>-->
    <!--        </dependency>-->
    <!--        &lt;!&ndash;MyBatis-Jar包&ndash;&gt;-->
    <!--        <dependency>-->
    <!--            <groupId>org.mybatis</groupId>-->
    <!--            <artifactId>mybatis</artifactId>-->
    <!--            <version>3.4.2</version>-->
    <!--        </dependency>-->
    
    <!--        &lt;!&ndash;MyBatis 整合Spring适配包&ndash;&gt;-->
    <!--        <dependency>-->
    <!--            <groupId>org.mybatis</groupId>-->
    <!--            <artifactId>mybatis-spring</artifactId>-->
    <!--            <version>1.3.1</version>-->
    <!--        </dependency>-->
    <!-- ********************************************** -->
            <dependency>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-starter</artifactId>
            </dependency>
    
            <dependency>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-starter-web</artifactId>
            </dependency>
    
            <!--MyBatis-->
            <dependency>
                <groupId>org.mybatis.spring.boot</groupId>
                <artifactId>mybatis-spring-boot-starter</artifactId>
                <version>1.3.2</version>
            </dependency>
            <!--MySQL-->
            <dependency>
                <groupId>mysql</groupId>
                <artifactId>mysql-connector-java</artifactId>
                <version>8.0.15</version>
            </dependency>
    
            <dependency>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-starter-test</artifactId>
                <scope>test</scope>
            </dependency>
    
    
        </dependencies>
    
        <build>
            <plugins>
                <plugin>
                    <groupId>org.springframework.boot</groupId>
                    <artifactId>spring-boot-maven-plugin</artifactId>
                </plugin>
                <plugin>
                    <groupId>org.mybatis.generator</groupId>
                    <artifactId>mybatis-generator-maven-plugin</artifactId>
                    <version>1.3.7</version>
                    <dependencies>
                        <dependency>
                            <groupId>mysql</groupId>
                            <artifactId>mysql-connector-java</artifactId>
                            <version>8.0.15</version>
                        </dependency>
                        <dependency>
                            <groupId>org.mybatis.generator</groupId>
                            <artifactId>mybatis-generator-core</artifactId>
                            <version>1.3.7</version>
                        </dependency>
                    </dependencies>
                    <executions>
                        <execution>
                            <id>Generate MyBatis Artifacts</id>
                            <phase>package</phase>
                            <goals>
                                <goal>generate</goal>
                            </goals>
                        </execution>
                    </executions>
                    <configuration>
                        <!--允许移动生成的文件 -->
                        <verbose>true</verbose>
                        <!-- 是否覆盖 -->
                        <overwrite>true</overwrite>
                        <!-- 自动生成的配置文件路径。启动插件时，插件会根据这里配置的路径去找到generatorConfig.xml配置文件，
                        根据配置文件里的配置，去自动生成Mapper接口（可以理解为Dao层）、实体类、Mapper.xml文件
                        -->
                        <configurationFile>src/main/resources/mybatis/generatorConfig.xml</configurationFile>
                    </configuration>
                </plugin>
            </plugins>
        </build>
    </project>
    
 2.配置application.properties 
    server.port=8888
    spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
    spring.datasource.url=jdbc:mysql://127.0.0.1:3306/bookdb?serverTimezone=UTC&useSSL=false&allowPublicKeyRetrieval=true
    spring.datasource.username=root
    spring.datasource.password=root
    #映射到dao层(即mapper生成的位置)
    mybatis.type-aliases-package=com.bo.bookdb.dao
    #mapper.xml文件生成位置
    mybatis.mapper-locations=classpath:mybatis/mapper/*.xml

3.配置generatorConfig.xml（基本照搬，注意倒数第三个标签：<table>,里面对应数据库的表名）
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE generatorConfiguration
        PUBLIC "-//mybatis.org//DTD MyBatis Generator Configuration 1.0//EN"
        "http://mybatis.org/dtd/mybatis-generator-config_1_0.dtd">
<generatorConfiguration>
    <!--加载配置文件，为下面读取数据库信息准备-->
    <properties resource="application.properties"/>

    <!--defaultModelType="flat" 大数据字段，不分表 -->
    <context id="Mysql" targetRuntime="MyBatis3Simple" defaultModelType="flat">
        <property name="autoDelimitKeywords" value="true"/>
        <property name="beginningDelimiter" value="`"/>
        <property name="endingDelimiter" value="`"/>
        <property name="javaFileEncoding" value="utf-8"/>
        <plugin type="org.mybatis.generator.plugins.SerializablePlugin"/>

        <plugin type="org.mybatis.generator.plugins.ToStringPlugin"/>

        <!-- 注释 -->
        <commentGenerator>
            <property name="suppressAllComments" value="true"/><!-- 是否取消注释 -->
            <property name="suppressDate" value="true"/> <!-- 是否生成注释代时间戳-->
        </commentGenerator>

        <!--数据库链接地址账号密码，这里是读取application.properties配置文件的。
        如果你的项目配置文件用的是yml格式的，则如下写法是读取不到，需手动填写完整
        -->
        <jdbcConnection driverClass="${spring.datasource.driver-class-name}"
                        connectionURL="${spring.datasource.url}"
                        userId="${spring.datasource.username}"
                        password="${spring.datasource.password}">
        </jdbcConnection>

        <!-- 类型转换 -->
        <javaTypeResolver>
            <!-- 是否使用bigDecimal， false可自动转化以下类型（Long, Integer, Short, etc.） -->
            <property name="forceBigDecimals" value="false"/>
        </javaTypeResolver>

        <!--生成Model类存放位置，即实体类存放路径 -->
        <javaModelGenerator targetPackage="com.bo.bookdb.entity" targetProject="src/main/java">
            <property name="enableSubPackages" value="true"/>
            <property name="trimStrings" value="true"/>
        </javaModelGenerator>

        <!-- 生成mapper.xml文件，mapper.xml存放路径 -->
        <sqlMapGenerator targetPackage="mybatis.mapper" targetProject="src/main/resources">
            <property name="enableSubPackages" value="false"/>
        </sqlMapGenerator>

        <!-- 生成mapper.xml对应得接口，也就是dao层 -->
        <javaClientGenerator targetPackage="com.bo.bookdb.dao" targetProject="src/main/java" type="XMLMAPPER">
            <property name="enableSubPackages" value="false"/>
        </javaClientGenerator>
        <!--以下就是需自动生成mapper.xml文件等表，配置哪些表，就自动生成哪些表那些文件。-->
        <table tableName="book" enableCountByExample="true" enableUpdateByExample="true" enableDeleteByExample="true" enableSelectByExample="true" selectByExampleQueryId="true">
            <generatedKey column="id" sqlStatement="Mysql" identity="true" />
        </table>

        <table tableName="user" enableCountByExample="true" enableUpdateByExample="true" enableDeleteByExample="true" enableSelectByExample="true" selectByExampleQueryId="true">
            <generatedKey column="id" sqlStatement="Mysql" identity="true" />
        </table>


    </context>
</generatorConfiguration>

4.点击Maven-->Plugins-->mybatis-generator-->mybatis-generator:generator
  最后右击Run Maven Build,如此会生成对应的dao,entity,以及mapper.xml
  如果error,可能是mybatis-generator-maven-plugin和mybatis-generator-core版本不一致
  我一开始就是版本没写一致，结果报错
 
5.将dao层里面的mapper类增加@Mapper,以便映射xml文件

6.添加server层
  和dao添加一样的方法，只不过方法名不同
  
7.增加实现类Impl  @Service
    继承对应的mapper接口，内置对应dao的对象。
 
8.编辑测试类      @ResrController
  内置实现类的对象
  编写一个用于测试的方法，@PostMapping设置一个对外接口

9.启动类
    @MapperScan(basePackages = "com.bo.bookdb.dao")
    启动的时候，自动扫描mapper接口

意外情况
如果实现类无法自动创建bean,可考虑以下情况
（1）上面步骤的第六步，继承mapper接口，内置dao对象
（2）Caused by: java.lang.ClassNotFoundException: org.aspectj.weaver.reflect.
    这个可能是路径问题，或者是缺少依赖，当然我写的实例没有用到，你们可能用到，在这说一下
    <dependency>
          <groupId>aopalliance</groupId>
          <artifactId>aopalliance</artifactId>
          <version>1.0</version>
        </dependency>
        <dependency>
          <groupId>org.aspectj</groupId>
          <artifactId>aspectjweaver</artifactId>
          <version>1.8.10</version>
        </dependency>
（3）如果是最后Caused by:...................xml
        这个比较坑，我当时自动创建完mapper.xml之后，里面的相同内容竟然复制了n份
        导致有很多相同id，只留一份就好了其它的全删。

github源码：
https://github.com/hgt-8080/bookdb


        

  
 

    