package com.graduate.hospital_manage.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.graduate.hospital_manage.model.Ward;
import com.graduate.hospital_manage.response.Result;
import com.graduate.hospital_manage.service.WardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/ward")
public class WardController {

    @Autowired
    private WardService wardService;

    @PostMapping("/add")
    @PreAuthorize("hasRole('ADMIN') or hasRole('DOCTOR')")
    public Result addWard(@RequestBody Ward ward) {
        Optional<Ward> existWard = wardService.findById(ward.getWid());
        if (existWard.isPresent()) {
            return Result.FAILURE("房间号已存在");
        }

        this.wardService.addWardWithSickBed(ward);
        return Result.SUCCESS();
    }

    @GetMapping("/page")
    @PreAuthorize("hasAnyRole('ADMIN', 'DOCTOR', 'NURSE', 'FRONT')")
    public Result pageByNumAndSize(@RequestParam(defaultValue = "1") Integer num,
                                   @RequestParam(defaultValue = "10") Integer size) {
        IPage<Ward> data = this.wardService.pageResult(num, size);
        return Result.SUCCESS(data) ;
    }

    @GetMapping("/getByExample")
    @PreAuthorize("hasAnyRole('ADMIN', 'DOCTOR', 'NURSE', 'FRONT')")
    public Result getByExample(@RequestBody Ward ward) {
        return Result.SUCCESS(this.wardService.findByExample(ward)) ;
    }

    @GetMapping("/getAll")
    @PreAuthorize("hasAnyRole('ADMIN', 'DOCTOR', 'NURSE', 'FRONT')")
    public Result getAll() {
        return Result.SUCCESS(this.wardService.list()) ;
    }

}
