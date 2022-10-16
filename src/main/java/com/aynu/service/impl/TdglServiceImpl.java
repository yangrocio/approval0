package com.aynu.service.impl;

import com.aynu.bean.TdglEntity;
import com.aynu.bean.Team;
import com.aynu.dao.TdglDao;
import com.aynu.dao.TdglResposity;
import com.aynu.dto.Audit;
import com.aynu.service.TdglService;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class TdglServiceImpl implements TdglService {
    @Autowired
    TdglResposity tdglResposity;

    @Autowired
    TdglDao tdglDao;

    @Value("${upload.fjscdir}")
    String filefjscPath;

    @Override
    public List<TdglEntity> findAll() {
        return tdglResposity.findAll();
    }


    @Override
    public List<TdglEntity> findCondition(String department, String name, String start, String end) {
        return tdglDao.findCondition(department,name,start,end);

    }

    @Override
    public TdglEntity findById(BigInteger i) {
        return tdglResposity.findById(i);
    }

    @Override
    public TdglEntity findByNo(String no) {
        return tdglResposity.findByNo(no);
    }

    @Override
    public TdglEntity findByName(String name) {
        return tdglResposity.findByName(name);
    }

    @Override
    public List<TdglEntity> findByDepartment(String department) {
        return tdglResposity.findByDepartment(department);
    }

    @Override
    public void deleteByNo(String no) {
        TdglEntity byNo = tdglResposity.findByNo(no);
        tdglResposity.deleteByNo(byNo);
    }

    @Override
    public TdglEntity insert(TdglEntity tdglEntity) {
        return tdglResposity.saveAndFlush(tdglEntity);
    }

    @Override
    public TdglEntity update(TdglEntity tdglEntity) {
        return tdglResposity.saveAndFlush(tdglEntity);
    }

    @Override
    public boolean updateState(Audit audit) {
        return tdglDao.updateState(audit);
    }


    @Override
    public void deleteById(BigInteger id) {
        TdglEntity tdglEntity = tdglResposity.findById(id);
        new File(filefjscPath+tdglEntity.getAnnex()).delete();
        tdglResposity.deleteById(id);
    }

    @Override
    public void deleteFjscByList(List<String> list) {
        List<String> fileNameByList = tdglDao.getFileNameByList(list);
        for (String fileName : fileNameByList) {
            //log.info("删除附件=="+fileName);
            if (fileName != null) {
                File file = new File(filefjscPath + fileName);
                file.delete();
            }
        }
    }

    @Override
    public String downloadhylwZipFjsc(List<String> list) {
        return null;
    }

    @Override
    public TdglEntity showtdgl(String department, String no, String id) {
        return tdglDao.showtdgl(department, no, id);
    }

    @Override
    public List<TdglEntity> selectByDetail(String department, String number, String name, String start, String end, String judgestatus, String roletype) {
        return tdglDao.selectByDetail(department, number, name, start, end, judgestatus, roletype);
    }
}
