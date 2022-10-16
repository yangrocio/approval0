package com.aynu.bean;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.math.BigInteger;

/**
 * @author guojingdong
 */
@Data
@Slf4j
@Entity
@Table(name = "ptgl")
public class PtglEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigInteger id;

    @Column
    private String platform;

    @Column
    private String no;

    @Column
    private String level;

    //批准院部 所属部门
    @Column
    private String approve_department;

    //所属院部
    @Column
    private String department;

    @Column
    private String principal;


    @Column
    private String approved_time;

    @Column
    private String annex;
}
