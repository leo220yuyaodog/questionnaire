package com.starvey;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.starvey.mapper")
public class QuestionnaireApplication {

    public static void main(String[] args) {
        SpringApplication.run(QuestionnaireApplication.class, args);
    }

}
