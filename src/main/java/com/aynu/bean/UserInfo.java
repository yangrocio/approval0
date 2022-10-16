package com.aynu.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigInteger;
import java.util.Date;

/**
 * @author Susuper 果冻
 * @version 1.0
 * @date 2020/4/11 21:41
 * @description
 */
@Data
public class UserInfo {
    Integer id;
    String name;  //姓名  Name
    String number;//账号  Number
    String password;//密码
    Integer sex;//性别 1是男 2是女   Sex
    Date birthday; //生日
    String department; //科研单位  院系
    String profession;//职称     副教授  教授
    String qualifications;//学历 硕士研究生  本科  博士研究生等
    String degree;//学位  硕士   博士——————
    String hdgu;//毕业学校   HDGU
    String major;//  专业    计算机应用技术   应用数学  软件工程——————
    String idcard;//身份证号
    String telephone;//电话
    String nation;//民族   Nation

    String rdirection;//方向
    String picture;//图片

    String email;//邮箱
    String nationality;//国家
    String loginstatus;//是否可以登录
    String source;


    Date assessmentdate;//注册日期
    String roletype;//角色权限 用的1234存储  //默认 给1 2 3 4 5
    Integer fmzl;
    Integer xxzl;
    Integer sjzl;
    Integer rjzzq;
    Integer btsj;
    Integer qtsj;


    public Integer getFmzl() {
        return fmzl;
    }

    public void setFmzl(Integer fmzl) {
        this.fmzl = fmzl;
    }

    public Integer getXxzl() {
        return xxzl;
    }

    public void setXxzl(Integer xxzl) {
        this.xxzl = xxzl;
    }

    public Integer getSjzl() {
        return sjzl;
    }

    public void setSjzl(Integer sjzl) {
        this.sjzl = sjzl;
    }

    public Integer getRjzzq() {
        return rjzzq;
    }

    public void setRjzzq(Integer rjzzq) {
        this.rjzzq = rjzzq;
    }

    public Integer getBtsj() {
        return btsj;
    }

    public void setBtsj(Integer btsj) {
        this.btsj = btsj;
    }

    public Integer getQtsj() {
        return qtsj;
    }

    public void setQtsj(Integer qtsj) {
        this.qtsj = qtsj;
    }

    public String getLoginstatus() {
        return loginstatus;
    }

    public void setLoginstatus(String loginstatus) {
        this.loginstatus = loginstatus;
    }


    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public String getSource(){
        return source;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }
    @JsonFormat(pattern = "yyyy年MM月dd日", timezone = "GMT+8")
    public Date getBirthday() {
        return birthday;
    }

    @DateTimeFormat(pattern = "yyyy年MM月dd日")
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getQualifications() {
        return qualifications;
    }

    public void setQualifications(String qualifications) {
        this.qualifications = qualifications;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getHdgu() {
        return hdgu;
    }

    public void setHdgu(String hdgu) {
        this.hdgu = hdgu;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getRdirection() {
        return rdirection;
    }

    public void setRdirection(String rdirection) {
        this.rdirection = rdirection;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }



    public String getRoletype() {
        return roletype;
    }

    public void setRoletype(String roletype) {
        this.roletype = roletype;
    }
}
