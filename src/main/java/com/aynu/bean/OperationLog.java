package com.aynu.bean;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;


public class OperationLog {
//    private String id;
    private Date createTime;

    private Integer lever; //日志等级
    private String operationUnit; //被操作对象
//    private String method;//方法名
//    private String args;//参数
//    private String userId; //操作人id
//    private String userName;//操作人
    private String describe; //操作日志
    private String operationType; //操作类型
    private Long runTime;  //运行时间
//    private String returnValue;//方法返回值


    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getLever() {
        return lever;
    }

    public void setLever(Integer lever) {
        this.lever = lever;
    }

    public String getOperationUnit() {
        return operationUnit;
    }

    public void setOperationUnit(String operationUnit) {
        this.operationUnit = operationUnit;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getOperationType() {
        return operationType;
    }

    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }

    public Long getRunTime() {
        return runTime;
    }

    public void setRunTime(Long runTime) {
        this.runTime = runTime;
    }

    @Override
    public String toString() {
        return "OperationLog{" +
                "开始时间=" + createTime +
                ", lever=" + lever +
                ", operationUnit='" + operationUnit + '\'' +
                ", 描述='" + describe + '\'' +
                ", operationType='" + operationType + '\'' +
                ", 耗时=" + runTime +
                '}';
    }
}
