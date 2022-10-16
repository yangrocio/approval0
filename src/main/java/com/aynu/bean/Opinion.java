package com.aynu.bean;

/**
 * @author Susuper 果冻
 * @version 1.0
 * @date 2020/4/30 16:09
 * @description
 */
public class Opinion {
    Integer aid;
    String tusername;
    String roletype;
    Integer tid;
    String departopinion;  //院系意见
    String schoolopinion;  //学校意见

    public Opinion() {
    }

    public Opinion(String tusername, String roletype, Integer tid) {
        this.tusername = tusername;
        this.roletype = roletype;
        this.tid = tid;
    }

    @Override
    public String toString() {
        return "Opinion{" +
                "aid=" + aid +
                ", tusername='" + tusername + '\'' +
                ", roletype='" + roletype + '\'' +
                ", tid=" + tid +
                ", departopinion='" + departopinion + '\'' +
                ", schoolopinion='" + schoolopinion + '\'' +
                '}';
    }

    public Integer getAid() {
        return aid;
    }

    public void setAid(Integer aid) {
        this.aid = aid;
    }

    public String getTusername() {
        return tusername;
    }

    public void setTusername(String tusername) {
        this.tusername = tusername;
    }

    public String getRoletype() {
        return roletype;
    }

    public void setRoletype(String roletype) {
        this.roletype = roletype;
    }

    public Integer getTid() {
        return tid;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }

    public String getDepartopinion() {
        return departopinion;
    }

    public void setDepartopinion(String departopinion) {
        this.departopinion = departopinion;
    }

    public String getSchoolopinion() {
        return schoolopinion;
    }

    public void setSchoolopinion(String schoolopinion) {
        this.schoolopinion = schoolopinion;
    }
}
