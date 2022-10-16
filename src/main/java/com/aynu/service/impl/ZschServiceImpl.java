package com.aynu.service.impl;

import com.aynu.bean.Zscq;
import com.aynu.dao.ZscqDao;
import com.aynu.dto.Audit;
import com.aynu.dto.dtos.DepartmentOpinion;
import com.aynu.dto.dtos.SchoolOpinion;
import com.aynu.service.ZschService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author susuper
 * @Date 2020/9/21 21:07
 * @description:
 */
@Service
public class ZschServiceImpl implements ZschService {
    @Autowired
    ZscqDao zscqDao;

    @Override
    public List<Zscq> selectZscqByDetail(String department, String number, String start, String end, String judgestatus, String roletype) {
        return zscqDao.selectZscqByDetail(department,number,start,end,judgestatus,roletype);
    }

    @Override
    public List<Zscq> selectZscqByDetails(String department, String number, String tcategory,String start, String end, String judgestatus,String roletype) {
        return zscqDao.selectZscqByDetails(department,number,tcategory,start,end,judgestatus,roletype);
    }

    @Override
    public boolean insertZscq(Zscq zscq) {
        return zscqDao.insertZscq(zscq);
    }

    @Override
    public boolean deleteZscq(List<String> list, String number, String department) {
        return zscqDao.deleteZscq(list,number,department);
    }

    @Override
    public Zscq showZscq(String department, String number, String id) {
        return zscqDao.showZscq(department,number,id);
    }


    @Override
    public boolean updateZscq(Zscq zscq) {
        return zscqDao.updateZscq(zscq);
    }

    @Override
    public boolean updateZscqByHigh(Audit audit) {
        return zscqDao.updateZscqByHigh(audit);
    }

    @Override
    public boolean updateZschByDepartmentOpinion(DepartmentOpinion departmentOpinion) {
        return zscqDao.updateZschByDepartmentOpinion(departmentOpinion);
    }

    @Override
    public boolean updateZschBySchoolOpinion(SchoolOpinion schoolOpinion) {
        return zscqDao.updateZschBySchoolOpinion(schoolOpinion);
    }
}
