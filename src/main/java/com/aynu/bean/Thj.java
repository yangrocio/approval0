package com.aynu.bean;

import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigInteger;

/**
 * @author susuper
 * @version 1.0
 * 体育获奖
 * @date 2020/9/11 18:16
 */
@Data
@Accessors(chain = true)
public class Thj {
    BigInteger tid;
    String number;
    String name;
    String department;

    String judgestatus;
    String departmentopinion;
    String schoolopinion;
    String uploadfile;     String systime;

}
