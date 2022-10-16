package com.aynu.bean;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @Author susuper
 * @Date 2020/9/23 16:16
 * @description:
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class Applynum {
    String name;
    Integer value;

    @Override
    public String toString() {
        String s = name+"剩余"+value+"个名额;";
        return s;
    }
}
