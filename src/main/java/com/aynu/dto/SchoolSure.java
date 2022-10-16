package com.aynu.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author Susuper 果冻
 * @version 1.0
 * @date 2020/4/15 17:58
 * @description
 */
@Data
@Accessors(chain = true)
public class SchoolSure {
     Integer tid;
     String suretduty;
     String sureapply;
     String surename;
     String surechapter;
     String suredate;
     String judge;

//    @Override
//    public String toString() {
//        return "SchoolSure{" +
//                "tid=" + tid +
//                ", suretduty='" + suretduty + '\'' +
//                ", sureapply='" + sureapply + '\'' +
//                ", surename='" + surename + '\'' +
//                ", surechapter='" + surechapter + '\'' +
//                ", suredate=" + suredate +
//                ", judge='" + judge + '\'' +
//                '}';
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
//    public String getSuretduty() {
//        return suretduty;
//    }
//
//    public void setSuretduty(String suretduty) {
//        this.suretduty = suretduty;
//    }
//
//    public String getSureapply() {
//        return sureapply;
//    }
//
//    public void setSureapply(String sureapply) {
//        this.sureapply = sureapply;
//    }
//
//    public String getSurename() {
//        return surename;
//    }
//
//    public void setSurename(String surename) {
//        this.surename = surename;
//    }
//
//    public String getSurechapter() {
//        return surechapter;
//    }
//
//    public void setSurechapter(String surechapter) {
//        this.surechapter = surechapter;
//    }
//
//
//
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
//    public String getJudge() {
//        return judge;
//    }
//
//    public void setJudge(String judge) {
//        this.judge = judge;
//    }
}
