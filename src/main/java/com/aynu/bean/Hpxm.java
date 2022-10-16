package com.aynu.bean;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigInteger;

/**
 * @author susuper
 * @version 1.0
 * @date 2020/9/11 18:18
 */
@Data
//@Accessors(chain = true)
public class Hpxm {
    @ExcelIgnore
    BigInteger hid;
    @ExcelProperty("工号")
    String number;  //工号
    @ExcelProperty("姓名")
    String name;  //姓名
    @ExcelProperty("院系")
    String department; //院系

    @ExcelProperty("项目名")
    String xmname; //项目名
    @ExcelProperty("项目编号")
    String pronumber;//	项目编号
    @ExcelProperty("项目外拨经费(万)")
    String wbjf; //项目外拨经费
    @ExcelProperty("学校配套经费(万)")
    String ptjf;//学校配套经费
    @ExcelProperty("项目下达部门")
    String xdbm;//项目下达部门
    @ExcelProperty("项目来源")
    String xmsource;//项目来源
    @ExcelProperty("项目级别")
    String prorank;//项目级别
    @ExcelProperty("项目属性")
    String proproperty;//项目属性
    @ExcelProperty("主持人")
    String hoster;//主持人
    @ExcelProperty("科研奖金(万元)")
    String reward;//科研奖励
    @ExcelProperty("业绩点")
    String points;//业绩点
    @ExcelProperty("项目参与人员")
    String cyry;//项目参与人员
    @ExcelProperty("备注")
    String remark;//备注
//    String pstatus;
@ExcelProperty("立项时间")
    String lxtime;//立项时间
    @ExcelIgnore
    String fjsc;//附件上传

    @ExcelProperty("审核状态")
    String judgestatus;
    @ExcelIgnore
    String departmentopinion;
    @ExcelIgnore
    String schoolopinion;
    @ExcelProperty("申请时间")
    String systime;

}
