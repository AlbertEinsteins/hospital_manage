package com.graduate.hospital_manage.dto;

import com.graduate.hospital_manage.model.EnHospitalized;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnHospitalizedDto extends BaseDtoImpl<EnHospitalized> {
    //在实体类之外的查询条件
    private Integer tid ;


    //用于出院登记，receive 前端的信息
    private String doctorName ;
    private String isPay ;
}
