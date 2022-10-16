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
 * 期刊论文
 * @date 2020/9/10 20:46
 */
@Data
//@Accessors(chain = true)
public class Qklw {
    @ExcelIgnore
    BigInteger qid;
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

    @ExcelProperty("奖励类别")
    String type;//奖励类别
    @ExcelProperty("发表期刊")
    String qikanname;//发表期刊
    @ExcelProperty("卷期号")
    String jqnumber;//卷期号
    @ExcelProperty("页码")
    String page;//页码
    @ExcelProperty("发表时间")
    String time;//发表时间



    @ExcelProperty("字数(千)")
    String zishu;//字数
    @ExcelProperty("分区")
    String fq;//分区
    @ExcelProperty("影响因子")
    String yxyz;//影响因子
    @ExcelIgnore
    String fjsc;

    @ExcelIgnore
    String rank;//论文级别  没用
    @ExcelIgnore
    String departmentopinion;
    @ExcelIgnore
    String schoolopinion;
    @ExcelIgnore
    String uploadfile;

    @ExcelProperty("备注")
    String remark;//备注

    @ExcelProperty("业绩点")
    String points;  //业绩点
    @ExcelProperty("科研奖金(万元)")
    String reward;  //科研奖励(万元)
    @ExcelProperty("申请时间")
    String systime;  //申请时间
    @ExcelProperty("审核状态")
    String judgestatus;

}
