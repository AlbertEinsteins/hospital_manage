package com.graduate.hospital_manage.controller;

import com.graduate.hospital_manage.response.Result;
import com.graduate.hospital_manage.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private MenuService menuService ;

    @GetMapping("/getMenu")
    public Result getMenu(@RequestParam("rid") Integer rid) {
        return Result.SUCCESS(menuService.getMenuTreeByPid(0, rid)) ;
    }
}
