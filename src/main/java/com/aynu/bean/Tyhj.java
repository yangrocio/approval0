package com.aynu.bean;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
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
//@Accessors(chain = true)
public class Tyhj {
    @ExcelIgnore
    BigInteger tid;

    @ExcelProperty("工号")
    String number;  //工号
    @ExcelProperty("姓名")
    String name;  //姓名
    @ExcelProperty("院系")
    String department; //院系

    @ExcelProperty("获奖人")
    String awardee;//获奖人
    @ExcelProperty("体育项目名")
    String entryname;//体育项目名
    @ExcelProperty("获奖时间")
    String wintime;//获奖时间
    @ExcelProperty("证书编号")
    String certificateid;//证书编号
    @ExcelProperty("运动会级别")
    String sportslv;//运动会级别
    @ExcelProperty("获奖名次")
    String ranking;//获奖名次
    @ExcelProperty("颁奖部门")
    String bjbm;//颁奖部门


    @ExcelProperty("业绩点")
    String points;  //业绩点
    @ExcelProperty("科研奖金(万元)")
    String reward;  //科研奖励(万元)
    @ExcelProperty("申请时间")
    String systime;  //申请时间
    @ExcelProperty("审核状态")
    String judgestatus;

    @ExcelProperty("备注")
    String fq;

    @ExcelIgnore
    String fjsc;

    @ExcelIgnore
    String departmentopinion;
    @ExcelIgnore
    String schoolopinion;
    @ExcelIgnore
    String uploadfile;

}
