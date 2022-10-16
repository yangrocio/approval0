package com.aynu.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @Author susuper
 * @Date 2020/9/19 18:12
 * @description:
 */
@Data
@Accessors(chain = true)
public class Audit {
    List<String> ids;
    String judgestatus;
    String departmentopinion;
    String schoolopinion;
}
