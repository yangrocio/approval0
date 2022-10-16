package com.aynu.bean;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.alibaba.excel.annotation.format.NumberFormat;
import com.aynu.Utils.DateAndStringConverter;
import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigInteger;

/**
 * @author susuper
 * @version 1.0
 * 会议论文
 * @date 2020/9/10 20:39
 */
@Data
//@Accessors(chain = true)
public class Hylw {
    @ExcelIgnore
    BigInteger hid;
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
    @ExcelProperty("出版社")
    String press;//出版社区
    @ExcelProperty("会议名称")
    String hyname;//会议名称
    @ExcelProperty("会议地点")
    String wheres;//会议地点
    @ExcelProperty("会议时间")
    String times;//会议时间
    @ExcelProperty("会议级别")
    String rank;//会议级别
    @ExcelProperty("奖励类别")
    String types;   //奖励类别


    @ExcelProperty("字数(千)")
    String zishu;  //字数
    @ExcelProperty("业绩点")
    String points;  //业绩点
    @ExcelProperty("科研奖金(万元)")
    String reward;  //科研奖励(万元)
    @ExcelProperty(value = "申请时间")
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
    String shoulu;  //收录  不用了

}
