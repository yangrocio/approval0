package com.aynu.service.impl;

import com.aynu.dao.CommonMethodDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author susuper
 * @Date 2021/1/3 18:32
 * @description:
 */
@Service
public class CommonMethodServiceImpl implements com.aynu.service.CommonMethodService {
    @Autowired
    CommonMethodDao commonMethodDao;
    @Override
    public int remainCount(String selecttype,String number, String department, String judgestatus) {
        return commonMethodDao.remainCount(selecttype,number,department,judgestatus);
    }
}
