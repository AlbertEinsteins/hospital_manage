package com.graduate.hospital_manage.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import com.graduate.hospital_manage.mapper.WardMapper;
import com.graduate.hospital_manage.model.SickBed;
import com.graduate.hospital_manage.model.Ward;
import com.graduate.hospital_manage.service.SickBedService;
import com.graduate.hospital_manage.service.WardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class WardServiceImpl extends ServiceImpl<WardMapper, Ward>
    implements WardService {

    @Autowired
    private SickBedService sickBedService ;

    @Transactional
    @Override
    public void addWardWithSickBed(Ward ward) {
        this.save(ward) ;
        ArrayList<SickBed> list = Lists.newArrayList();
        for(int i = 1; i <= ward.getCapacity(); i++) {
            SickBed sickBed = new SickBed() ;
            String wid = ward.getWid() ;
            sickBed.setWid(wid);
            sickBed.setSid(wid + "00" + i);
            list.add(sickBed) ;
        }
        sickBedService.saveBatch(list) ;
    }

    @Override
    public Optional<Ward> findById(String wid) {
        Ward byId = this.getById(wid);
        return Optional.ofNullable(byId) ;
    }

    @Override
    public IPage<Ward> pageResult(Integer pagenum, Integer pagesize) {
        //TODO
        return null ;
    }

    @Override
    public List<Ward> findByExample(Ward example) {
        return this.list(Wrappers.query(example)) ;
    }
}
