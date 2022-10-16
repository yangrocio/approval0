package com.aynu.service.impl;

import com.aynu.bean.PtglEntity;
import com.aynu.dao.PtglDao;
import com.aynu.dao.PtglResposity;
import com.aynu.service.PtglService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.math.BigInteger;
import java.util.List;

/**
 * @author guojingdong
 */
@Service
public class PtglServiceImpl implements PtglService {
    @Autowired
    PtglResposity ptglResposity;

    @Autowired
    PtglDao ptglDao;

    @Value("${upload.fjscdir}")
    String filefjscPath;

    @Override
    public List<PtglEntity> findAll() {
        return ptglResposity.findAll();
    }

    @Override
    public List<PtglEntity> findCondition(String department, String platform, String start, String end) {
        return ptglDao.findCondition(department,platform,start,end);
    }

    @Override
    public PtglEntity findById(BigInteger i) {
        return ptglResposity.findById(i);
    }

    @Override
    public PtglEntity findByNo(String no) {
        return ptglResposity.findByNo(no);
    }

    @Override
    public PtglEntity findByName(String name) {
        return ptglResposity.findByPlatform(name);
    }

    @Override
    public List<PtglEntity> findByDepartment(String department) {
        return ptglResposity.findByDepartment(department);
    }

    @Override
    public void deleteByNo(String no) {
        ptglResposity.deleteByNo(no);
    }

    @Override
    public PtglEntity insert(PtglEntity ptglEntity) {
        return ptglResposity.saveAndFlush(ptglEntity);
    }

    @Override
    public PtglEntity update(PtglEntity ptglEntity) {
        return ptglResposity.saveAndFlush(ptglEntity);
    }

    @Override
    public void deleteById(BigInteger id) {
        PtglEntity ptglEntity = ptglResposity.findById(id);
        new File(filefjscPath+ptglEntity.getAnnex()).delete();
        ptglResposity.deleteById(id);
    }
}
