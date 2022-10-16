package com.aynu.bean;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigInteger;

/**
 * @author susuper
 * @version 1.0
 * 音美获奖
 * @date 2020/9/11 18:15
 */
@Data
//@Accessors(chain = true)
public class Ymhj {
    @ExcelIgnore
    BigInteger yid;
    @ExcelProperty("工号")
    String number;  //工号
    @ExcelProperty("姓名")
    String name;  //姓名
    @ExcelProperty("院系")
    String department; //院系

    @ExcelProperty("参加比赛作品名称")
    String timu;//参赛作品
    @ExcelProperty("获奖名称")
    String awardname;//获奖名称
    @ExcelProperty("第一作者")
    String dyzz;//第一作者
    @ExcelProperty("其它作者")
    String qtzz;
    @ExcelProperty("主办单位")
    String organizer;//主办单位
    @ExcelProperty("获奖时间")
    String awardtime;//获奖时间
    @ExcelProperty("奖励类别")
    String rewardsort;//奖励类别


    @ExcelProperty("备注")
    String remark;


    @ExcelProperty("业绩点")
    String points;  //业绩点
    @ExcelProperty("科研奖金(万元)")
    String reward;  //科研奖励(万元)
    @ExcelProperty("申请时间")
    String systime;  //申请时间
    @ExcelProperty("审核状态")
    String judgestatus;


    @ExcelIgnore
    String fq; //废弃

    @ExcelIgnore
    String fjsc;
    @ExcelIgnore
    String departmentopinion;
    @ExcelIgnore
    String schoolopinion;
    @ExcelIgnore
    String uploadfile;
}
