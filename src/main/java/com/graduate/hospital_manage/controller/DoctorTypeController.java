package com.graduate.hospital_manage.controller;

import com.graduate.hospital_manage.model.DoctorType;
import com.graduate.hospital_manage.response.Result;
import com.graduate.hospital_manage.service.DoctorTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/doctype")
public class DoctorTypeController {
    @Autowired
    private DoctorTypeService doctorTypeService ;

    @GetMapping("/getAll")
    @PreAuthorize("hasAnyRole('ADMIN', 'DOCTOR', 'FRONT', 'NURSE')")
    public Result getAllTypes() {
        List<DoctorType> list = doctorTypeService.list();
        return Result.SUCCESS(list) ;
    }
}
