package com.graduate.hospital_manage.controller;

import cn.hutool.crypto.digest.BCrypt;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.graduate.hospital_manage.dto.UserDto;
import com.graduate.hospital_manage.model.User;
import com.graduate.hospital_manage.response.Result;
import com.graduate.hospital_manage.service.RoleService;
import com.graduate.hospital_manage.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService ;

    @GetMapping("/roles")
    @PreAuthorize("hasAnyRole('ADMIN', 'DOCTOR', 'FRONT', 'NURSE')")
    public Result getRoles() {
        return Result.SUCCESS(this.roleService.list()) ;
    }


    @PostMapping("/get_pageuser")
    @PreAuthorize("hasAnyRole('ADMIN', 'DOCTOR', 'FRONT', 'NURSE')")
    public Result getPageResult(@RequestBody UserDto queryVo) {
        IPage<Map> pageRs = this.userService.pageResult(
                new Page<>(queryVo.getPagenum(), queryVo.getPagesize()),
                queryVo.getData()) ;
        return Result.SUCCESS(pageRs) ;
    }

    @PutMapping("/updatebyid")
    @PreAuthorize("hasRole('ADMIN')")
    public Result updateById(@RequestBody User user) {
        if (!StringUtils.hasLength(user.getUid())) {
            return Result.FAILURE("id为空") ;
        }

        this.userService.updateById(user) ;
        return Result.SUCCESS() ;
    }

    @DeleteMapping("/delbyid")
    @PreAuthorize("hasRole('ADMIN')")
    public Result delById(@RequestBody User delUser) {
        if (!StringUtils.hasLength(delUser.getUid())) {
            return Result.FAILURE("id为空") ;
        }

        this.userService.removeById(delUser.getUid()) ;
        return Result.SUCCESS() ;
    }

    @Value("${init.passwd}")
    private String initPwd ;

    @PutMapping("/retpwd")
    @PreAuthorize("hasRole('ADMIN')")
    public Result resetPassword(@RequestBody User resetUser) {
        if (!StringUtils.hasLength(resetUser.getUid())) {
            return Result.FAILURE("id为空") ;
        }

        resetUser.setPassword(
                BCrypt.hashpw(initPwd)
        ) ;
        this.userService.updateById(resetUser) ;
        return Result.SUCCESS() ;
    }

    @PutMapping("/revisePwd")
    @PreAuthorize("hasAnyRole('ADMIN', 'DOCTOR', 'FRONT', 'NURSE')")
    public Result revisePwd(@RequestBody User reviseUser) {
        if(!StringUtils.hasLength(reviseUser.getUsername())
            || !StringUtils.hasLength(reviseUser.getPassword())) {
            return Result.FAILURE("修改失败") ;
        }

        reviseUser.setPassword(
                BCrypt.hashpw(reviseUser.getPassword())
        ) ;
        LambdaUpdateWrapper<User> wrapper = Wrappers.<User>lambdaUpdate();

        wrapper.eq(User::getUsername, reviseUser.getUsername()) ;
        this.userService.update(reviseUser, wrapper) ;
        return Result.SUCCESS() ;
    }
}
