package com.graduate.hospital_manage.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.graduate.hospital_manage.model.SickBed;
import com.graduate.hospital_manage.response.Result;
import com.graduate.hospital_manage.service.SickBedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sickbed")
public class SickBedController {

    @Autowired
    private SickBedService sickBedService ;

    @GetMapping("/getByWid")
    @PreAuthorize("hasAnyRole('ADMIN', 'DOCTOR', 'FRONT', 'NURSE')")
    public Result getByWid(@RequestParam("wid") String wid) {
        LambdaQueryWrapper<SickBed> wrapper = Wrappers.<SickBed>lambdaQuery()
                .eq(SickBed::getWid, wid)
                .eq(SickBed::getStatus, 0) ;
        return Result.SUCCESS(this.sickBedService.list(wrapper)) ;
    }
}
