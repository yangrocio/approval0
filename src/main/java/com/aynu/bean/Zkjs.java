package com.aynu.bean;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigInteger;

/**
 * @Author susuper
 * @Date 2020/11/12 15:22
 * @description:
 */
@Data
//@Accessors(chain = true)
public class Zkjs {
    @ExcelIgnore
    BigInteger zid;
    @ExcelProperty("工号")
    String number;  //工号
    @ExcelProperty("姓名")
    String name;  //姓名
    @ExcelProperty("院系")
    String department; //院系

    @ExcelProperty("成果名称")
    String cgname;//成果名称
    @ExcelProperty("采纳级别")
    String cnjb;//采纳级别
    @ExcelProperty("第一完成人")
    String dyzz;//第一完成人
    @ExcelProperty("其它参与人")
    String qtzz;//其它参与人
    @ExcelProperty("采纳部门")
    String cnbm;//采纳部门
    @ExcelProperty("采纳时间")
    String cntime; //采纳时间

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

}
