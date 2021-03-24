package com.graduate.hospital_manage.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.graduate.hospital_manage.mapper.UserMapper;
import com.graduate.hospital_manage.model.User;
import com.graduate.hospital_manage.service.UserService;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService {
    @Override
    public IPage<Map> pageResult(Page<User> userPage, User data) {
        return this.getBaseMapper().selectPageResult(userPage, data) ;
    }

    @Override
    public Optional<User> findByUsername(String username) {
        LambdaQueryWrapper<User> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(User::getUsername, username) ;
        return Optional.ofNullable(this.getOne(wrapper)) ;
    }
}
