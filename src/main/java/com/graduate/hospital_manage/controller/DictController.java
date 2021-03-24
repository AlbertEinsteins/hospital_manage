package com.graduate.hospital_manage.controller;

import com.graduate.hospital_manage.response.Result;
import com.graduate.hospital_manage.service.DictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dict")
public class DictController {

    @Autowired
    private DictService dictService ;

    @GetMapping("/nation_all")
    @PreAuthorize("hasRole('DOCTOR') or hasRole('ADMIN')")
    public Result getAllNations() {
        return Result.SUCCESS(dictService.findAllNations()) ;
    }

    @GetMapping("/event_all")
    @PreAuthorize("hasRole('ADMIN')")
    public Result getAllEventTypes() {
        return Result.SUCCESS(dictService.findAllEventTypes()) ;
    }
}
