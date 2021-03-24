package com.graduate.hospital_manage.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.graduate.hospital_manage.model.Pay;
import com.graduate.hospital_manage.model.PayItem;
import com.graduate.hospital_manage.model.PayReturn;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface PayService extends IService<Pay> {
    List<Pay> pageRecord(Pay queryVo) ;


    //获取订单
    List<Map> getCostByHid(String hid) ;

    //获取返还的药品
    List<Map> getRtnByHid(String hid) ;


    //获取（除需要返还药品之外）需要支付的费用
    //用于生成订单返还的费用
    List<PayItem> getCostByHidForCreateOrder(String hid) ;

    //判断是否支付
    boolean isPay(String hid) ;

    //获取用于生成订单返还的费用
    List<PayReturn> getRtnByHidForCreateOrder(String hid) ;

    IPage<Map> pageResult(Page<Pay> objectPage, Pay queryVo);

    Optional<Pay> findByHid(String hid) ;
}
