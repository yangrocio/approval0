package com.aynu.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @author Susuper 果冻
 * @version 1.0
 * @date 2020/4/15 16:37
 * @description
 */
@Data
@Accessors(chain = true)
public class DepartmentDocDto {
    Integer tid;
    String tinventionname;
    String tname;
    String tphone;
    String tapplydate;  //申请日期
    String judgestatus;


//    @Override
//    public String toString() {
//        return "DepartmentDocDto{" +
//                "tinventionname='" + tinventionname + '\'' +
//                ", tname='" + tname + '\'' +
//                ", tphone='" + tphone + '\'' +
//                ", tapplydate=" + tapplydate +
//                ", judgestatus='" + judgestatus + '\'' +
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
//    public String getTinventionname() {
//        return tinventionname;
//    }
//
//    public void setTinventionname(String tinventionname) {
//        this.tinventionname = tinventionname;
//    }
//
//    public String getTname() {
//        return tname;
//    }
//
//    public void setTname(String tname) {
//        this.tname = tname;
//    }
//
//    public String getTphone() {
//        return tphone;
//    }
//
//    public void setTphone(String tphone) {
//        this.tphone = tphone;
//    }
//    @JsonFormat(pattern = "yyyy年MM月dd日", timezone = "GMT+8")
//    public Data getTapplydate() {
//        return tapplydate;
//    }
//    @DateTimeFormat(pattern = "yyyy年MM月dd日")
//    public void setTapplydate(Data tapplydate) {
//        this.tapplydate = tapplydate;
//    }
//
//    public String getJudgestatus() {
//        return judgestatus;
//    }
//
//    public void setJudgestatus(String judgestatus) {
//        this.judgestatus = judgestatus;
//    }
}
