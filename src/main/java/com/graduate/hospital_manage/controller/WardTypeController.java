package com.graduate.hospital_manage.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.graduate.hospital_manage.model.WardType;
import com.graduate.hospital_manage.response.Result;
import com.graduate.hospital_manage.service.WardTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/wardtype")
public class WardTypeController {

    @Autowired
    private WardTypeService wardTypeService ;

    @GetMapping("/getAll")
    @PreAuthorize("hasAnyRole('ADMIN', 'DOCTOR', 'FRONT', 'NURSE')")
    public Result getAllTypes() {
        List<WardType> types = wardTypeService.list() ;
        return Result.SUCCESS(types) ;
    }

    @GetMapping("/page")
    @PreAuthorize("hasAnyRole('ADMIN', 'DOCTOR', 'FRONT', 'NURSE')")
    public Result getPageByNumAndSize(@RequestParam(value = "pagenum", defaultValue = "1") Integer pagenum,
                                      @RequestParam(value = "pagesize", defaultValue = "10") Integer pagesize) {
        IPage<WardType> page = this.wardTypeService.pageResult(pagenum, pagesize);
        return Result.SUCCESS(page) ;
    }

    @PutMapping("/changePrice")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public Result changePrice(@RequestParam(value = "id") Integer id,
                              @RequestParam(value = "price") Integer price) {
        this.wardTypeService.updatePrice(id, price) ;
        return Result.SUCCESS() ;
    }
}
