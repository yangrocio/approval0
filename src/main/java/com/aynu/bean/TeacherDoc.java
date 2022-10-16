package com.aynu.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author Susuper 果冻
 * @version 1.0
 * @date 2020/4/12 11:38
 * @description
 */
@Data
@Accessors(chain = true)
public class TeacherDoc {

    String department; //科研单位

    Integer tid;  //编号
    String tusername;  //账户
    String tname;  //项目名称
    String tcategory;  //分类1
    String tcategory2;//分类2
    String tapply;  //申请方式
    String tapplyname;//申请方式为代理时候的机构名称
    String tproject; //项目类别
    String tprojectid;//项目编号
    String tprojectname;//项目名称
    String tsortname;//发明人排序
    String tinventionname;//申请人
    String tunitname;//s所在单位
    String tphone;//电话
    String temail;//邮件
    String tintroduce;//简介
    String tduty;//职务成果
    String tinventionname2;  //签名
    String roletype;//类别
    String judgestatus;//doc类别
    String adate;  //申请日期

    //院系 修改部分转化
    String dunitopinion;  //单位意见
    String dunitheadname;  //盖章
    String dunitchapter;  //签名
    String ddate;

    //学校修改部分
    String suretduty;
    String sureapply;
    String surename;
    String surechapter;
    String suredate;

//    @JsonFormat(pattern = "yyyy年MM月dd日", timezone = "GMT+8")
//    public Date getSuredate() {
//        return suredate;
//    }
//
//    @DateTimeFormat(pattern = "yyyy年MM月dd日")
//    public void setSuredate(Date suredate) {
//        this.suredate = suredate;
//    }
//
//

}
