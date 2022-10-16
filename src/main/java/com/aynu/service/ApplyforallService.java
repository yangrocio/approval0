package com.aynu.service;

import com.aynu.bean.Applyforall;

/**
 * @Author susuper
 * @Date 2020/10/25 22:35
 * @description:
 */
public interface ApplyforallService {
    Applyforall selectApplyByNumber(String number);

    boolean updateApplyByNumber(String name,Integer value,String number);

    boolean updateApplyAll(String name,Integer value);
}
