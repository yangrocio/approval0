package com.aynu.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @author Susuper 果冻
 * @version 1.0
 * @date 2020/5/2 11:20
 * @description
 */
//院系 申请人 项目名称 申请人电话 申请人邮箱  学校通过日期
// 是否需要在普通人申请时候输入日期
@Data
@Accessors(chain = true)
public class BriefTeacher {
    Integer tid;
    String department; //科研单位
    String tusername;
    String tname;  //项目名称
    String tinventionname;//申请人
    String roletype;//角色类别
    String judgestatus;//审核状态
    String tcategory;//專利類別
    String tphone;
    String temail;
    String adate;  //申请日期
    String suredate;  //学校通过日期
}
