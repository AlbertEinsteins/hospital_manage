package com.graduate.hospital_manage.dto;

import com.graduate.hospital_manage.model.OutHospitalized;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OutHospitalDto extends BaseDtoImpl<OutHospitalized> {
    //接收vo
    private Integer tid ;
}
