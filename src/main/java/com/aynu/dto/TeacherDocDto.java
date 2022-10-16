package com.aynu.dto;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author Susuper 果冻
 * @version 1.0
 * @date 2020/4/13 10:06
 * @description
 */
@Data
@Accessors(chain = true)
public class TeacherDocDto {
    Integer tid;
    String tname;
    String judgestatus;


}
