package com.graduate.hospital_manage.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.graduate.hospital_manage.mapper.PayMapper;
import com.graduate.hospital_manage.model.MedicineDistribution;
import com.graduate.hospital_manage.model.Pay;
import com.graduate.hospital_manage.model.PayItem;
import com.graduate.hospital_manage.model.PayReturn;
import com.graduate.hospital_manage.model.constant.PayStatus;
import com.graduate.hospital_manage.service.MedicineDistributeService;
import com.graduate.hospital_manage.service.PayItemService;
import com.graduate.hospital_manage.service.PayReturnService;
import com.graduate.hospital_manage.service.PayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class PayServiceImpl extends ServiceImpl<PayMapper, Pay>
    implements PayService {

    @Autowired
    private MedicineDistributeService mdService ;

    @Autowired
    private PayItemService payItemService ;

    @Autowired
    private PayReturnService payReturnService ;

    @Override
    public List<Map> getCostByHid(String hid) {
        List<Map> result = Lists.newArrayList() ;

        this.findByHid(hid).ifPresent(pay -> {
            //获取所有订单
            LambdaQueryWrapper<PayItem> payItemWrapper = Wrappers.<PayItem>lambdaQuery();
            payItemWrapper.eq(PayItem::getPid, pay.getPid()) ;

            List<PayItem> list = this.payItemService.list(payItemWrapper) ;

            this.transferRtnValue(hid, result, pay, list);
        });

        return result ;
    }

    @Override
    public List<Map> getRtnByHid(String hid) {
        List<Map> result = Lists.newArrayList() ;

        this.findByHid(hid).ifPresent(pay -> {
            //获取所有订单
            LambdaQueryWrapper<PayReturn> payRtnWrapper = Wrappers.<PayReturn>lambdaQuery();
            payRtnWrapper.eq(PayReturn::getPid, pay.getPid()) ;

            List<PayReturn> list = this.payReturnService.list(payRtnWrapper) ;

            transferRtnValue(hid, result, pay, list);
        });

        return result ;
    }

    private void transferRtnValue(String hid, List<Map> result, Pay pay, List<?> list) {
        list.forEach(item -> {
            Map itemMap  = Maps.newHashMap() ;
            itemMap.put("hid", hid) ;
            itemMap.put("name", pay.getName()) ;

            itemMap.putAll(BeanUtil.beanToMap(item)) ;
            result.add(itemMap) ;
        }) ;
    }


    @Override
    public List<Pay> pageRecord(Pay queryVo) {
        return this.list(Wrappers.query(queryVo)) ;
    }


    //床费,医药费
    @Override
    public List<PayItem> getCostByHidForCreateOrder(String hid) {
        List<PayItem> result = Lists.newArrayList() ;

        result.addAll(getWardCostByHid(hid)) ;
        result.addAll(getMedicineCostByHid(hid)) ;
        return result ;
    }

    @Override
    public boolean isPay(String hid) {
        Pay isExist = this.getOne(Wrappers.<Pay>lambdaQuery()
                .eq(Pay::getHid, hid)
                .eq(Pay::getStatus, PayStatus.PAY.status())) ;

        return isExist != null ;
    }

    private List<PayItem> getWardCostByHid(String hid) {
        return this.getBaseMapper().selectWardCostByHid(hid) ;
    }

    private List<PayItem> getMedicineCostByHid(String hid) {
        return this.getBaseMapper().selectMedicineCost(hid) ;
    }


    public List<PayReturn> getRtnByHidForCreateOrder(String hid) {
        return this.getBaseMapper().selectRtnMoney(hid) ;
    }

    @Override
    public IPage<Map> pageResult(Page<Pay> objectPage, Pay queryVo) {
        return this.getBaseMapper().selectPagePayRecord(objectPage, queryVo);
    }

    @Override
    public Optional<Pay> findByHid(String hid) {
        LambdaQueryWrapper<Pay> wrapper = Wrappers.<Pay>lambdaQuery();
        wrapper.eq(Pay::getHid, hid) ;
        return Optional.ofNullable(this.getOne(wrapper)) ;
    }
}
