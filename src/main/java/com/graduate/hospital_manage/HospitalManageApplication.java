package com.graduate.hospital_manage;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.RestController;



@EnableTransactionManagement
@MapperScan("com.graduate.hospital_manage.mapper")
@RestController
@SpringBootApplication
@Slf4j
public class HospitalManageApplication {

    public static void main(String[] args) {
        SpringApplication.run(HospitalManageApplication.class, args);
    }

}
