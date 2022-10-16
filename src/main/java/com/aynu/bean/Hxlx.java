package com.aynu.bean;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigInteger;

/**
 * @Author susuper
 * @Date 2020/11/9 11:06
 * @description:
 */
@Data
//@Accessors(chain = true)
public class Hxlx {
    @ExcelIgnore
    BigInteger hid;
    @ExcelProperty("工号")
    String number;  //工号
    @ExcelProperty("姓名")
    String name;  //姓名
    @ExcelProperty("院系")
    String department; //院系

    @ExcelProperty("项目名称")
    String xmname;
    @ExcelProperty("负责人")
    String principal;  //负责人
    @ExcelProperty("立项/结项/终止")
    String xmstatus; //项目状态  立项 结项等
    @ExcelProperty("总金额(万元)")
    String zje;
    @ExcelProperty("已拨款(万元)")
    String ybk; //已拨款
    @ExcelProperty("合同编号")
    String contractnumber;//合同编号
    @ExcelProperty("协议单位")
    String aunit;  //协议单位
    @ExcelProperty("签订时间")
    String qdtime;  //签订时间
    @ExcelProperty("开始日期")
    String stime;//开始时间
    @ExcelProperty("截止日期")
    String jztime;  //截至时间
//    String jxtime; //结项时间

    @ExcelProperty("业绩点")
    String points;
    @ExcelProperty("科研奖金(万元)")
    String reward;
    @ExcelProperty("备注")
    String remark;//备注
    @ExcelIgnore
    String fjsc;

    @ExcelProperty("审核状态")
    String judgestatus;
    @ExcelIgnore
    String departmentopinion;
    @ExcelIgnore
    String schoolopinion;
    @ExcelProperty("申请时间")
    String systime;

    @ExcelIgnore
    String fq;//没用
}
