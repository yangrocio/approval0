package com.aynu.bean;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigInteger;

/**
 * @author susuper
 * @version 1.0
 * 学术交流
 * @date 2020/9/11 18:01
 */
@Data
//@Accessors(chain = true)
public class Xsjl {
    @ExcelIgnore
    BigInteger xid;
    @ExcelProperty("工号")
    String number;  //工号
    @ExcelProperty("姓名")
    String name;  //姓名
    @ExcelProperty("院系")
    String department; //院系

    @ExcelProperty("会议名称")
    String xmname;//会议名称
    @ExcelProperty("参与人员")
    String cyry;//参与人员
    @ExcelProperty("会议地点")
    String wheres;//会议地点
    @ExcelProperty("会议时间")
    String time;//会议时间
    @ExcelProperty("主办单位")
    String zbdw;//主办单位
    @ExcelProperty("会议等级")
    String rank;//会议等级
    @ExcelProperty("是否发表论文")
    String lunwen;//是否发表论文
    @ExcelProperty("是否发言")
    String fayan;  //是否发言



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
    @ExcelIgnore
    String filename;
}
