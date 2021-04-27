package com.graduate.hospital_manage.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.graduate.hospital_manage.annotation.aop.Cache;
import com.graduate.hospital_manage.mapper.DictMapper;
import com.graduate.hospital_manage.model.Dict;
import com.graduate.hospital_manage.service.DictService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DictServiceImpl extends ServiceImpl<DictMapper, Dict>
    implements DictService {

    @Value("${dict.code.nation}")
    private String codeNation ;

    @Value("${dict.code.logtype}")
    private String codeEventTypes ;


    @Override
    @Cache("nations")
    public List<Dict> findAllNations() {
        return this.findByCodes(this.codeNation) ;
    }

    @Override
    public List<Dict> findAllEventTypes() {
        return this.findByCodes(this.codeEventTypes) ;
    }

    private List<Dict> findByCodes(String code) {
        QueryWrapper<Dict> cond = Wrappers.query() ;
        cond.eq("code", code);
        return this.list(cond) ;
    }
}
