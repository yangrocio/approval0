package com.aynu.bean;

import java.util.Date;

/**
 * @author susuper
 * @version 1.0
 * @date 2020/7/25 10:11
 * 需要产生 id top 内容 生成时间具体到时分秒 发布人
 */
public class Notice {
    private Integer nid;
    private String noticeTop;
    private String noticeArticle;
    private Date generateDate;
    private String number;  //发布人的账号
    private String name;   //发布人的姓名
    private String assnumber;


    @Override
    public String toString() {
        return "Notice{" +
                "nid=" + nid +
                ", noticeTop='" + noticeTop + '\'' +
                ", noticeArticle='" + noticeArticle + '\'' +
                ", generateDate=" + generateDate +
                ", number='" + number + '\'' +
                ", name='" + name + '\'' +
                ", assnumber='" + assnumber + '\'' +
                '}';
    }

    public String getAssnumber() {
        return assnumber;
    }

    public void setAssnumber(String assnumber) {
        this.assnumber = assnumber;
    }

    public Integer getNid() {
        return nid;
    }

    public void setNid(Integer nid) {
        this.nid = nid;
    }

    public String getNoticeTop() {
        return noticeTop;
    }

    public void setNoticeTop(String noticeTop) {
        this.noticeTop = noticeTop;
    }

    public String getNoticeArticle() {
        return noticeArticle;
    }

    public void setNoticeArticle(String noticeArticle) {
        this.noticeArticle = noticeArticle;
    }

    public Date getGenerateDate() {
        return generateDate;
    }

    public void setGenerateDate(Date generateDate) {
        this.generateDate = generateDate;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
