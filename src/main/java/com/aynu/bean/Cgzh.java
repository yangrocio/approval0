package com.aynu.bean;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.aynu.Utils.DateAndStringConverter;
import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigInteger;

/**
 * @Author susuper
 * @Date 2020/11/12 15:26
 * @description:
 */
@Data
//@Accessors(chain = true)
public class Cgzh {
    @ExcelIgnore
    BigInteger cid;
    @ExcelProperty("工号")
    String number;  //工号
    @ExcelProperty("姓名")
    String name;  //姓名
    @ExcelProperty("院系")
    String department; //院系

    @ExcelProperty("转让成果名称")
    String cgname;//转让成果名称
    @ExcelProperty("转让类型")
    String zrlx;//转让类型
    @ExcelProperty("转让到账经费(万)")
    String zrdzjf;//转让到账经费
    @ExcelProperty("第一参与人")
    String dyzz;//第一完成人
    @ExcelProperty("其它参与人")
    String qtzz;//其它作者
    @ExcelProperty("转化单位")
    String zhdw;//转化单位
    @ExcelProperty("转化时间")
    String zhtime;//转化时间

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

    @ExcelProperty(value = "申请时间")
    String systime;
    @ExcelProperty("备注")
    String fq;
}
