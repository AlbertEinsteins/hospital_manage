package com.graduate.hospital_manage.controller;

import cn.hutool.crypto.digest.BCrypt;
import com.graduate.hospital_manage.dto.request.LoginRequest;
import com.graduate.hospital_manage.model.User;
import com.graduate.hospital_manage.response.JwtResponse;
import com.graduate.hospital_manage.response.Result;
import com.graduate.hospital_manage.security.jwt.JwtUtils;
import com.graduate.hospital_manage.security.services.UserDetailImpl;
import com.graduate.hospital_manage.service.RoleService;
import com.graduate.hospital_manage.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private RoleService roleService ;

    @Autowired
    private UserService userService ;

    @Autowired
    private AuthenticationManager authenticationManager ;


    @Autowired
    private JwtUtils jwtUtils ;

    @PostMapping("/signup")
    public Result signup(@Valid @RequestBody User user) {
        if(!this.roleService.existRole(user.getRid())) {
            return Result.FAILURE("角色ID不存在") ;
        }

        user.setAmount(BigDecimal.ZERO) ;

        user.setPassword(BCrypt.hashpw(user.getPassword())) ;
        user.setRegistTime(LocalDateTime.now());
        this.userService.save(user) ;
        return Result.SUCCESS() ;
    }

    @PostMapping("/signin")
    public ResponseEntity<?> signin(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
        ) ;
        SecurityContextHolder.getContext().setAuthentication(authentication) ;

        String jwt = jwtUtils.generateJwtToken(authentication) ;
        UserDetailImpl userDetail = (UserDetailImpl) authentication.getPrincipal();

        List<String> roles = userDetail.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList()) ;

        return ResponseEntity.ok(new JwtResponse(
                jwt,
                userDetail.getRid(),
                userDetail.getUsername(),
                roles)) ;
    }


    @PostMapping("/signout")
    public Result signout() {
        return null ;
    }
}
