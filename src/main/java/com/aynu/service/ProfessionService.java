package com.aynu.service;

import com.aynu.bean.Profession;

import java.util.List;

/**
 * @author susuper
 * @version 1.0
 * @date 2020/9/5 18:42
 */
public interface ProfessionService {
    boolean save(Profession profession);
    boolean delete(Long aLong);
    List<Profession> findAll();
}
