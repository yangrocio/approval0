package com.aynu.service.impl;

import com.aynu.bean.Applynum;
import com.aynu.dao.ApplynumDao;
import com.aynu.service.ApplynumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author susuper
 * @Date 2020/9/23 16:24
 * @description:
 */
@Service
public class ApplynumServiceImpl  implements ApplynumService {
    @Autowired
    ApplynumDao applynumDao;
    @Override
    public List<Applynum> queryAll() {
        return applynumDao.queryAll();
    }

    @Override
    public Applynum queryByName(String name) {
        return applynumDao.queryByName(name);
    }

    @Override
    public boolean updateApplynum(Applynum applynums) {
        return applynumDao.updateApplynum(applynums);
    }
}
