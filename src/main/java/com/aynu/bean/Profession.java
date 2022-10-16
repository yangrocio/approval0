package com.aynu.bean;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author susuper
 * @version 1.0
 * @date 2020/9/5 18:40
 */
@Data
@Accessors(chain = true)
public class Profession {
    Long id;
    String name;
}
