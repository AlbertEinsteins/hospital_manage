package com.graduate.hospital_manage.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.graduate.hospital_manage.mapper.WardTypeMapper;
import com.graduate.hospital_manage.model.WardType;
import com.graduate.hospital_manage.service.WardTypeService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class WardTypeServiceImpl extends ServiceImpl<WardTypeMapper, WardType>
    implements WardTypeService {
    @Override
    public IPage<WardType> pageResult(Integer pagenum, Integer pagesize) {
        return this.page(new Page<>(pagenum, pagesize)) ;
    }


    public void updatePrice(Integer id, Integer price) {
        WardType cond = new WardType().setId(id).setPrice(BigDecimal.valueOf(price));
        this.updateById(cond) ;
    }

}
