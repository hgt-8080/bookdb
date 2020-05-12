package com.bo.bookdb;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.bo.bookdb.dao")
/**
 * 添加标签@MapperScan(basePackages = "com.bo.bookdb.dao")
 * 在启动得时候，会自动去扫描com.bo.bookdb.dao路径下mapper接口，即dao层。
 */
public class BookdbApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookdbApplication.class, args);
    }

}
