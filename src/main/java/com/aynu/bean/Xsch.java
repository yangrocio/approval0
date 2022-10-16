package com.aynu.bean;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigInteger;

/**
 * @author susuper
 * @version 1.0
 * 学术称号
 * @date 2020/9/11 18:01
 */
@Data
//@Accessors(chain = true)
public class Xsch {
    @ExcelIgnore
    BigInteger xid;
    @ExcelProperty("工号")
    String number;  //工号
    @ExcelProperty("姓名")
    String name;  //姓名
    @ExcelProperty("院系")
    String department; //院系

    @ExcelProperty("获奖人姓名")
    String xmname;//获奖人姓名
    @ExcelProperty("称号名称")
    String title;//称号名称
    @ExcelProperty("称号等级")
    String rank;//称号等级
    @ExcelProperty("证书编号")
    String zsnumber;//证书编号
    @ExcelProperty("获得时间")
    String time;//获得时间
    @ExcelProperty("下发部门")
    String xfbm;//下发部门
    String fjsc;


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
    String departmentopinion;
    @ExcelIgnore
    String schoolopinion;
    @ExcelIgnore
    String uploadfile;
}
