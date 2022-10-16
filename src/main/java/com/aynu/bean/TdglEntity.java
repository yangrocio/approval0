package com.aynu.bean;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.math.BigInteger;

/**
 * @author guojingdong
 */

@Slf4j
@Entity
@Table(name = "tdgl")
@Data
public class TdglEntity {
    @ExcelIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigInteger id;
    @Column
    private String type;
    @Column
    private String name;
    @Column
    private String source;
    @Column
    private String no;
    @Column
    private String subsidize;
    @Column
    private String level;
    @Column
    private String approve_department;
    @Column
    private String department;
    @Column
    private String principal;
    @Column
    private String approved_time;
    @Column
    private String point;
    @Column
    private String scientific;

    public void setNumber(String number) {
        this.number = number;
    }

    @Column
    private String number;
    @Column
    private String annex;

    public void setJudgestatus(String judgestatus) {
        this.judgestatus = judgestatus;
    }

    @ExcelProperty("审核状态")
    String judgestatus;
    @ExcelIgnore
    String departmentopinion;
    @ExcelIgnore
    String schoolopinion;

    public String getType() {
        return type;
    }

    public String getPrincipal() {
        return principal;
    }

    public String getJudgestatus() {
        return judgestatus;
    }
}
