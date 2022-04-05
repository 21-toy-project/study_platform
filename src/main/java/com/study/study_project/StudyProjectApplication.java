package com.study.study_project;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = {"com.study.study_project.mapper"})
public class StudyProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(StudyProjectApplication.class, args);
    }

}
