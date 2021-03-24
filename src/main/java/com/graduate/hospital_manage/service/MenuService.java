package com.graduate.hospital_manage.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.graduate.hospital_manage.model.Menu;

import java.util.List;
import java.util.Map;

public interface MenuService extends IService<Menu> {

    List<Map> getMenuTreeByPid(Integer pid, Integer rid) ;
}
