package com.aynu.bean;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @Author susuper
 * @Date 2020/10/25 22:17
 * @description:
 */
@Data
@Accessors(chain = true)
public class Applyforall {
    String number;
    Integer fmzl;
    Integer xxzl;
    Integer sjzl;
    Integer rjzzq;
    Integer btsj;
    Integer qtsj;
}
