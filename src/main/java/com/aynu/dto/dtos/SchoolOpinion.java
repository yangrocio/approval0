package com.aynu.dto.dtos;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @Author susuper
 * @Date 2020/9/22 21:19
 * @description:
 */
@Data
@Accessors(chain = true)
public class SchoolOpinion {
    String tid;
    String suretduty;
    String sureapply;
    String surename;
    String surechapter;
    String suredate;
    String judgestatus;

    String schoolopinion;

    String  tcategory;//临时加的
}
