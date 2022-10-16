package com.aynu.dto;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @Author susuper
 * @Date 2020/10/29 16:11
 * @description:
 */
@Data
@Accessors(chain = true)
public class Mxhzb {
    String number;
    String department;
    String name;

    String selecttype;

    String topic;
    String points;
    String reward;
    String dyzz;//第一作者
    String time;// 发表 或 出版时间
    String kwtype;  //刊物类型
    String fbkw;  //发表刊物

    String fq;//备注

    String systime;
}
