package com.graduate.hospital_manage.controller;

import com.graduate.hospital_manage.model.DoctorPosition;
import com.graduate.hospital_manage.response.Result;
import com.graduate.hospital_manage.service.DoctorPositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/docpos")
public class DoctorPositionController {
    @Autowired
    private DoctorPositionService doctorPositionService ;

    @GetMapping("/getAll")
    @PreAuthorize("hasAnyRole('ADMIN', 'DOCTOR', 'FRONT', 'NURSE')")
    public Result getAllTypes() {
        List<DoctorPosition> list = doctorPositionService.list();
        return Result.SUCCESS(list) ;
    }
}
