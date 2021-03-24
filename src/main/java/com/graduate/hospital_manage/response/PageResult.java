package com.graduate.hospital_manage.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName: PageResult
 * @Description TODO
 * @Author: jff
 * @Date: 2020-02-04 16:01
 * @Version: 1.0
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageResult<T> implements Serializable {
    private Long total ;
    private List<T> rows ;
}
