package com.aynu.bean;


import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigInteger;

@Data
@Accessors(chain = true)
public class Team {
    @ExcelIgnore
    BigInteger id;

    @ExcelProperty("团队名称")
    String name;

    @ExcelProperty("负责人编号")
    String number;

    @ExcelProperty("团队编号")
    String no;

    @ExcelProperty("类别")
    String type;

    @ExcelProperty("来源")
    String source;

    @ExcelProperty("层次")
    String level;

    @ExcelProperty("补助")
    String subsidize;

    @ExcelProperty("显示的类别")
    String selecttype;

    @ExcelProperty("绩点")
    String point;

    @ExcelProperty("科研奖励")
    String scientific;

    @ExcelProperty("审核部门")
    String approve_department;

    @ExcelProperty("所属院部")
    String department;

    @ExcelProperty("负责人")
    String principal;

    @ExcelProperty("获批时间")
    String approved_time;

    @ExcelProperty("备注")
    String fq;

    @ExcelProperty("附件")
    @ExcelIgnore
    String annex;

    @ExcelProperty("审核状态")
    String judgestatus;
    @ExcelIgnore
    String departmentopinion;
    @ExcelIgnore
    String schoolopinion;
    @ExcelIgnore
    String uploadfile;

    @ExcelProperty("申请时间")
    String systime;


}
