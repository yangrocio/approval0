package com.aynu.dto;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @Author susuper
 * @Date 2020/11/7 21:41
 * @description:
 */
@Data
@Accessors(chain = true)
public class Tjb {
    String number;
    String department;
    String name;
    String sex;
    String points;
    String reward;
    String fq;//备注
}
