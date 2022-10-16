package com.aynu.bean;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigInteger;

/**
 * @author susuper
 * @version 1.0
 * 获批专利
 * @date 2020/9/11 17:57
 */
@Data
//@Accessors(chain = true)
public class Hpzl {
    @ExcelIgnore
    BigInteger hid;
    @ExcelProperty("工号")
    String number;  //工号
    @ExcelProperty("姓名")
    String name;  //姓名
    @ExcelProperty("院系")
    String department; //院系


    @ExcelProperty("名称")
    String xmname;
    @ExcelProperty("专利权人")
    String zlqr;
    @ExcelProperty("第一发明人")
    String fmr;
    @ExcelProperty("其它发明人")
    String qtfmr;//其它发明人
    @ExcelProperty("颁发部门")
    String bfbm;//颁发部门
    @ExcelProperty("专利号")
    String zlnumber;//专利号
    @ExcelProperty("申请时间")
    String sqtime;//申请时间
    @ExcelProperty("授权时间")
    String sytime;//授权时间
    @ExcelProperty("专利类别")
    String ptype;//专利类别
    @ExcelProperty("专利状态")
    String pstatus;//专利状态
    @ExcelProperty("业绩点")
    String points;
    @ExcelProperty("科研奖金(万元)")
    String reward;

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
    @ExcelProperty("申请时间systime")
    String systime;
    @ExcelProperty("备注")
    String fq;
}
