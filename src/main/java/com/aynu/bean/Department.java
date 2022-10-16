package com.aynu.bean;

import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * @author susuper
 * @version 1.0
 * @date 2020/8/29 16:22
 */
@Data
@Accessors(chain = true)
public class Department {
    Long id;
    String name;
}
