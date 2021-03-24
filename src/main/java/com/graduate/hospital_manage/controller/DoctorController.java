package com.graduate.hospital_manage.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.graduate.hospital_manage.dto.DoctorDto;
import com.graduate.hospital_manage.model.Doctor;
import com.graduate.hospital_manage.response.Result;
import com.graduate.hospital_manage.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/doc")
public class DoctorController {

    @Autowired
    private DoctorService doctorService ;

    @GetMapping("/getAll")
    @PreAuthorize("hasAnyRole('ADMIN', 'DOCTOR', 'FRONT', 'NURSE')")
    public Result getAllDoctors() {
        LambdaQueryWrapper<Doctor> wrapper = Wrappers.<Doctor>lambdaQuery();
        wrapper.eq(Doctor::getIsRetire, false) ;

        return Result.SUCCESS(this.doctorService.list(wrapper)) ;
    }

    @GetMapping("/getByExample")
    @PreAuthorize("hasAnyRole('ADMIN', 'DOCTOR', 'FRONT', 'NURSE')")
    public Result getByExample(@RequestBody Doctor doctor) {
        return Result.SUCCESS(this.doctorService.findByExample(doctor));
    }


    @PostMapping("/save")
    @PreAuthorize("hasRole('ADMIN')")
    public Result save(@RequestBody Doctor doctor) {
        return Result.SUCCESS(this.doctorService.save(doctor)) ;
    }

    @PostMapping("/page")
    @PreAuthorize("hasAnyRole('ADMIN', 'DOCTOR', 'FRONT', 'NURSE')")
    public Result getPageByExample(@RequestBody DoctorDto doctorDto) {
        return Result.SUCCESS(this.doctorService.getPageByExample(
                doctorDto.getData(), new Page<>(doctorDto.getPagenum(), doctorDto.getPagesize()) )) ;
    }


    @PutMapping("/updatebyid")
    @PreAuthorize("hasRole('ADMIN')")
    public Result updateById(@Valid @RequestBody Doctor doctor) {
        this.doctorService.updateById(doctor) ;
        return Result.SUCCESS() ;
    }

}