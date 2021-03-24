package com.graduate.hospital_manage;

import cn.hutool.crypto.digest.BCrypt;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.collect.Lists;
import com.graduate.hospital_manage.model.Dict;
import com.graduate.hospital_manage.model.Doctor;
import com.graduate.hospital_manage.model.RoleMenuRelation;
import com.graduate.hospital_manage.model.User;
import com.graduate.hospital_manage.service.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@SpringBootTest
class HospitalManageApplicationTests {

    @Autowired
    private DoctorService doctorService ;

    @Autowired
    private PayService payService ;

    @Test
    void contextLoads() {

    }

//    @Test
//    void addNation() throws InterruptedException {
//        String nationsStr = "汉族、蒙古族、回族、藏族、维吾尔族、苗族、彝族、壮族、布依族、朝鲜族、满族、侗族、瑶族、白族、土家族、哈尼族、哈萨克族、傣族、黎族、僳僳族、佤族、畲族、高山族、拉祜族、水族、东乡族、纳西族、景颇族、柯尔克孜族、" +
//          "土族、达斡尔族、仫佬族、羌族、布朗族、撒拉族、毛南族、仡佬族、锡伯族、阿昌族、普米族、塔吉克族、怒族、乌孜别克族、俄罗斯族、鄂温克族、德昂族、保安族、裕固族、京族、塔塔尔族、独龙族、鄂伦春族、赫哲族、门巴族、珞巴族、基诺族" ;
//
//        String[] nations = nationsStr.split("、");
//        List<Dict> list = new ArrayList<>( ) ;
//        for(int i = 0 ; i < nations.length; i ++ ){
//            Dict dict = new Dict().setId(i + 1)
//                    .setCode("001").setName("民族").setValue(nations[i]) ;
//            list.add(dict) ;
//        }
//        dictService.saveBatch(list) ;
//
//    }
//
//    @Autowired
//    private DictService dictService ;

    @Autowired
    private UserService userService ;

    @Autowired
    private RoleMenuRelationService rmrs ;

    @Autowired
    private MenuService menuService ;

}
