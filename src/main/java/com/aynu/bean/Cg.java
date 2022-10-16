package com.aynu.bean;

import com.fasterxml.jackson.databind.node.BigIntegerNode;
import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigInteger;

/**
 * @author susuper
 * @version 1.0
 * @date 2020/9/14 9:55
 */
@Data
@Accessors(chain = true)
public class Cg {
    BigInteger cid;
    String number;
    String name;
    String department;
    String cgname;
    String cgtype;
    String dyzz;
    String qtzz;
    String cnbm;
    String cgdz;
    String time;
    String points;
    String reward;
    String fjsc;
    String judgestatus;
    String departmentopinion;
    String schoolopinion;
    String systime;
    String fq;

    String uploadfile;

}
