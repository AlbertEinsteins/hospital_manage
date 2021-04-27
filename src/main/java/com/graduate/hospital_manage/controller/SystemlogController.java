package com.graduate.hospital_manage.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.graduate.hospital_manage.dto.LogDto;
import com.graduate.hospital_manage.model.Log;
import com.graduate.hospital_manage.model.constant.ELogLevel;
import com.graduate.hospital_manage.response.Result;
import com.graduate.hospital_manage.service.LogService;
import com.graduate.hospital_manage.utils.LogUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/log")
public class SystemlogController {

    @Autowired
    private LogUtils logUtils;

    @GetMapping("/query")
    @PreAuthorize("hasRole('ADMIN')")
    public Result queryLogs(LogDto logDto) {
        IPage<Log> logs;

        Page<Log> page = new Page<>(logDto.getPagenum(), logDto.getPagesize()) ;
        if (!StringUtils.hasLength(logDto.getLevel())) {
            //查询所有
            logs = logUtils.readLogByLevel(null, logDto.getFrom(), page);
        } else {
            logs = logUtils.readLogByLevel(ELogLevel.valueOf(logDto.getLevel()), logDto.getFrom(), page);
        }
        return Result.SUCCESS(logs) ;
    }
}
