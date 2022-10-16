package com.aynu.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author Susuper 果冻
 * @version 1.0
 * @date 2020/4/15 17:31
 * @description
 */
@Data
@Accessors(chain = true)
public class DepartmentSaveWrite {
    Integer tid;
    String dunitopinion;
    String dunitheadname;
    String dunitchapter;
    String ddate;
    String judge;

//    @Override
//    public String toString() {
//        return "DepartmentSaveWrite{" +
//                "tid=" + tid +
//                ", dunitopinion='" + dunitopinion + '\'' +
//                ", dunitheadname='" + dunitheadname + '\'' +
//                ", dunitchapter='" + dunitchapter + '\'' +
//                ", ddate='" + ddate + '\'' +
//                '}';
//    }
//
//    public String getJudge() {
//        return judge;
//    }
//
//    public void setJudge(String judge) {
//        this.judge = judge;
//    }
//
//    public Integer getTid() {
//        return tid;
//    }
//
//    public void setTid(Integer tid) {
//        this.tid = tid;
//    }
//
//    public String getDunitopinion() {
//        return dunitopinion;
//    }
//
//    public void setDunitopinion(String dunitopinion) {
//        this.dunitopinion = dunitopinion;
//    }
//
//    public String getDunitheadname() {
//        return dunitheadname;
//    }
//
//    public void setDunitheadname(String dunitheadname) {
//        this.dunitheadname = dunitheadname;
//    }
//
//    public String getDunitchapter() {
//        return dunitchapter;
//    }
//
//    public void setDunitchapter(String dunitchapter) {
//        this.dunitchapter = dunitchapter;
//    }
//    @JsonFormat(pattern = "yyyy年MM月dd日", timezone = "GMT+8")
//    public Date getDdate() {
//        return ddate;
//    }
//    @DateTimeFormat(pattern = "yyyy年MM月dd日")
//    public void setDdate(Date ddate) {
//        this.ddate = ddate;
//    }
}
