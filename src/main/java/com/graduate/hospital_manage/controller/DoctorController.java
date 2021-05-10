package com.graduate.hospital_manage.controller;

import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.graduate.hospital_manage.dto.DoctorDto;
import com.graduate.hospital_manage.dto.PatientDto;
import com.graduate.hospital_manage.model.Doctor;
import com.graduate.hospital_manage.model.EnHospitalized;
import com.graduate.hospital_manage.model.constant.ELogLevel;
import com.graduate.hospital_manage.response.Result;
import com.graduate.hospital_manage.service.DoctorService;
import com.graduate.hospital_manage.utils.LogUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.Map;

@RestController
@RequestMapping("/doc")
public class DoctorController {

    @Autowired
    private DoctorService doctorService ;

    @Autowired
    private LogUtils logUtils ;

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

        this.logUtils.writeLog(ELogLevel.INFO, "医生录入",
                String.format("入职医生姓名: %s, 职位: %d", doctor.getName(), doctor.getTid())) ;
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
        this.logUtils.writeLog(ELogLevel.INFO, "医生信息修改",
                JSONUtil.toJsonStr(doctor)) ;

        return Result.SUCCESS() ;
    }

    @GetMapping("/getpatients")
    public Result getPatients(@RequestParam String username,
                              @RequestParam Integer isActive,
                              @RequestParam(defaultValue = "1") Integer pagenum,
                              @RequestParam(defaultValue = "10") Integer pagesize) {
        Page<Map> page = new Page<>(pagenum, pagesize);
        return Result.SUCCESS(doctorService.findByUsername(username, isActive, page));
    }

    @PostMapping("/getInactivepatients")
    public Result getPatients(@RequestBody PatientDto patientDto) {
        Page<Map> page = new Page<>(patientDto.getPagenum(), patientDto.getPagesize());
        return Result.SUCCESS(doctorService.findByUsername(patientDto.getHid(),
                    patientDto.getUsername(), patientDto.getStartTime(), patientDto.getEndTime(), page));
    }
}
