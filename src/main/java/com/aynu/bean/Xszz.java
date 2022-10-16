package com.aynu.bean;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigInteger;
import java.util.Date;

/**
 * @author susuper
 * @version 1.0
 * 学术著作
 * @date 2020/9/10 21:09
 */
@Data
//@Accessors(chain = true)
public class Xszz {
    @ExcelIgnore
    BigInteger xid;
    @ExcelProperty("工号")
    String number;  //工号
    @ExcelProperty("姓名")
    String name;  //姓名
    @ExcelProperty("院系")
    String department; //院系

    @ExcelProperty("题目")
    String timu; //题目
    @ExcelProperty("第一作者")
    String dyzz;//第一作者
    @ExcelProperty("其它作者")
    String qtzz;//其它作者

    @ExcelProperty("ISBN")
    String isbn;//ISBN
    @ExcelProperty("出版社类型")
    String worktype; //出版社类型
    @ExcelProperty("出版社")
    String press;//出版社
    @ExcelProperty("著作类型")
    String zzlx; //著作类型 备注中
    @ExcelProperty("CIP核字")
    String cip;//CIP核字
    @ExcelProperty("出版时间")
    String time;//出版时间


    @ExcelProperty("字数")
    String zishu;  //字数
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
    String presstype;  //废弃
    @ExcelIgnore
    String departmentopinion;
    @ExcelIgnore
    String schoolopinion;
    @ExcelIgnore
    String uploadfile;


}
