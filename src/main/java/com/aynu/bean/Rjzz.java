package com.aynu.bean;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigInteger;

/**
 * @author susuper
 * @version 1.0
 * 软件著作
 * @date 2020/9/11 17:51
 */
@Data
//@Accessors(chain = true)
public class Rjzz {
    @ExcelIgnore
    BigInteger rid;
    @ExcelProperty("工号")
    String number;  //工号
    @ExcelProperty("姓名")
    String name;  //姓名
    @ExcelProperty("院系")
    String department; //院系

    @ExcelProperty("名称")
    String xmname;//名称
    @ExcelProperty("第一著作人")
    String dyzz;
    @ExcelProperty("其它作者")
    String qtzz;
    @ExcelProperty("登记号")
    String djnumber;//登记号
    @ExcelProperty("授予单位")
    String sydw;//授予单位
    @ExcelProperty("开发完成时间")
    String finishtime;//开发完成时间
    @ExcelProperty("软件著作权登记日")
    String djtime;//软件著作权登记日

    @ExcelProperty("版权类型")
    String bqtype;//版权类型
    @ExcelProperty("权利取得方式")
    String syfs;  //权利取得方式

    @ExcelProperty("业绩点")
    String points;  //业绩点
    @ExcelProperty("科研奖金(万元)")
    String reward;  //科研奖励(万元)  可能没有用处
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

    @ExcelIgnore
    String sftime;//无用
}
