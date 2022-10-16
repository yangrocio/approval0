package com.aynu.service.impl;

import com.aynu.bean.Profession;
import com.aynu.dao.ProfessionDao;
import com.aynu.service.ProfessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author susuper
 * @version 1.0
 * @date 2020/9/5 18:42
 */
@Service
public class ProfessionServiceImpl implements ProfessionService {
    @Autowired
    ProfessionDao professionDao;
    @Override
    public boolean save(Profession profession) {
        return professionDao.save(profession);
    }

    @Override
    public boolean delete(Long aLong) {
        return professionDao.delete(aLong);
    }

    @Override
    public List<Profession> findAll() {
        return professionDao.findAll();
    }
}
