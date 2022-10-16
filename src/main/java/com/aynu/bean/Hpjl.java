package com.aynu.bean;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigInteger;

/**
 * @author susuper
 * @version 1.0
 * 获批奖励
 * @date 2020/9/11 17:57
 */
@Data
//@Accessors(chain = true)
public class Hpjl {
    @ExcelIgnore
    BigInteger hid;
    @ExcelProperty("工号")
    String number;  //工号
    @ExcelProperty("姓名")
    String name;  //姓名
    @ExcelProperty("院系")
    String department; //院系

    @ExcelProperty("获奖成果名称")
    String cgname;//获奖成果名称
    @ExcelProperty("奖励名称")
    String jlname;//奖励名称
    @ExcelProperty("第一获奖人")
    String dyhjr;//第一获奖人
    @ExcelProperty("其它获奖人")
    String qthjr;
    @ExcelProperty("奖励下达部门")
    String jixdbm;//奖励下达部门
    @ExcelProperty("证书编号")
    String zsnumber;//证书编号
    @ExcelProperty("获奖时间")
    String hjtime;//获奖时间
    @ExcelProperty("奖励等级")
    String jlrank;//奖励等级
    @ExcelProperty("获奖级别")
    String hjrank;//获奖级别
    @ExcelProperty("备注")
    String remark;//remark
    @ExcelProperty("业绩点")
    String points;
    @ExcelProperty("科研奖金(万元)")
    String kyjl;//reward  科研奖励
    @ExcelIgnore
    String fjsc;

    @ExcelProperty("审核状态")
    String judgestatus;
    @ExcelIgnore
    String departmentopinion;
    @ExcelIgnore
    String schoolopinion;
    @ExcelIgnore
    String uploadfile;

    @ExcelProperty("申请时间")
    String systime;


}
