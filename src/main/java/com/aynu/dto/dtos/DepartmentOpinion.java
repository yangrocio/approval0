package com.aynu.dto.dtos;

import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigInteger;

/**
 * @Author susuper
 * @Date 2020/9/22 21:19
 * @description:
 */
@Data
@Accessors(chain = true)
public class DepartmentOpinion {
    String tid;
    String dunitopinion;  //单位意见
    String dunitheadname;  //盖章
    String dunitchapter;  //签名
    String judgestatus;
    String departmentopinion;
    String ddate;
}
