package com.graduate.hospital_manage.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import com.graduate.hospital_manage.mapper.MenuMapper;
import com.graduate.hospital_manage.model.Menu;
import com.graduate.hospital_manage.model.constant.ERole;
import com.graduate.hospital_manage.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {
  /*  List<Map> tree = Lists.newArrayList() ;

    List<Menu> menus = this.getBaseMapper().selectAllWithRid(pid, rid) ;
        if(menus.size() > 0) {
        menus.forEach(menu -> {
            Map<String, Object> beanMap = BeanUtil.beanToMap(menu) ;
            beanMap.put("children", getMenuItemsByParentId(menu.getMenuId())) ;

            tree.add(beanMap) ;
        });
    }

        return tree;*/

    @Override
    public List<Map> getMenuTreeByPid(Integer pid, Integer rid) {
        List<Menu> all = this.getBaseMapper().selectAllWithRid() ;

        return this.getMenuTreeByPid(all, pid, rid) ;
    }

    private List<Map> getMenuTreeByPid(List<Menu> all, Integer pid, Integer rid) {
        List<Map> subTree = Lists.newArrayList() ;

        all.forEach(item -> {
            if(item.getRid() != null) {
                if(item.getPid().equals(pid) && item.getRid().equals(rid)) {
                    Map<String, Object> beanMap = BeanUtil.beanToMap(item) ;
                    beanMap.put("children", this.getMenuTreeByPid(all, item.getMenuId(), rid)) ;

                    subTree.add(beanMap) ;
                }
            }
        });
        return subTree ;
    }




    protected List<Menu> getByPid(Integer pid) {
        QueryWrapper<Menu> query = Wrappers.query();
        query.eq("pid", pid)
                .orderByAsc("orders") ;
        return this.list(query) ;
    }
}
