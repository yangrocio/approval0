package com.aynu.bean;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigInteger;

/**
 * @Author susuper
 * @Date 2020/11/8 20:51
 * @description:
 */
@Data
//@Accessors(chain = true)
public class Jxys {
    @ExcelIgnore
    BigInteger jid;
    @ExcelProperty("工号")
    String number;  //工号
    @ExcelProperty("姓名")
    String name;  //姓名
    @ExcelProperty("院系")
    String department; //院系

    @ExcelProperty("名称")
    String xmname;//名称
    @ExcelProperty("结项编号")
    String pronumber;//结项编号
    @ExcelProperty("项目来源")
    String xmly;  //项目来源
    @ExcelProperty("项目级别")
    String rank;// 项目级别
    @ExcelProperty("项目属性")
    String proproperty;//项目属性
    @ExcelProperty("主持人")
    String hoster; //主持人
    @ExcelProperty("项目下达部门")
    String xdbm;  //下达部门
    @ExcelProperty("项目参与人员")
    String cyry;  //项目参与人员

    @ExcelProperty("结项验收时间")
    String time;  //结项验收时间
    @ExcelProperty("备注")
    String remark;//备注
    @ExcelProperty("业绩点")
    String points;  //业绩点
    @ExcelProperty("科研奖金(万元)")
    String reward;  //科研奖励(万元)

    @ExcelIgnore
    String pstatus;  //项目状态 没用到
    @ExcelIgnore
    String fjsc;
    @ExcelProperty("审核状态")
    String judgestatus;
    @ExcelIgnore
    String departmentopinion;
    @ExcelIgnore
    String schoolopinion;
    @ExcelProperty("申请时间")
    String systime;  //申请时间

    @ExcelIgnore
    String protype;//项目类型  没用上
}
