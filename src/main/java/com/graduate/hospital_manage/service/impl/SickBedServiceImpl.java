package com.graduate.hospital_manage.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.graduate.hospital_manage.mapper.SickBedMapper;
import com.graduate.hospital_manage.model.SickBed;
import com.graduate.hospital_manage.service.SickBedService;
import org.springframework.stereotype.Service;

@Service
public class SickBedServiceImpl extends ServiceImpl<SickBedMapper, SickBed>
    implements SickBedService {

    @Override
    public void updateStatus(String sid, int status) {
        SickBed update = new SickBed().setSid(sid)
                .setStatus(1) ;

        this.updateById(update) ;
    }
}
