package com.graduate.hospital_manage;

import com.google.common.collect.Maps;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@EnableTransactionManagement
@MapperScan("com.graduate.hospital_manage.mapper")
@RestController
@SpringBootApplication
public class HospitalManageApplication {

    public static void main(String[] args) {
        SpringApplication.run(HospitalManageApplication.class, args);
    }

    @GetMapping("/x")
    public Object test() {
        HashMap<Object, Object> map = Maps.newHashMap();
        map.put("code", 1) ;
        map.put("msg", "It is so different") ;
        map.put("ceshi", 3) ;


        return map ;
    }
}
