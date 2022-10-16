package com.aynu.service.impl;

import com.aynu.bean.Applyforall;
import com.aynu.dao.ApplyforallDao;
import com.aynu.service.ApplyforallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author susuper
 * @Date 2020/10/25 22:35
 * @description:
 */
@Service
public class ApplyforallServiceImpl implements ApplyforallService {
    @Autowired
    ApplyforallDao applyforallDao;
    @Override
    public Applyforall selectApplyByNumber(String number) {
        return applyforallDao.selectApplyByNumber(number);
    }

    @Override
    public boolean updateApplyByNumber(String name,Integer value,String number){
        return applyforallDao.updateApplyByNumber(name,value,number);
    }

    @Override
    public boolean updateApplyAll(String name,Integer value) {
        return applyforallDao.updateApplyAll(name,value);
    }
}
