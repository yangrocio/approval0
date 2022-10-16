package com.aynu.service;

import com.aynu.bean.Applynum;

import java.util.List;

/**
 * @Author susuper
 * @Date 2020/9/23 16:24
 * @description:
 */

public interface ApplynumService {
    List<Applynum> queryAll();
    Applynum queryByName(String name);
    boolean updateApplynum(Applynum applynums);

}
