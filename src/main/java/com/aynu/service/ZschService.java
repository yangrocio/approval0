package com.aynu.service;

import com.aynu.bean.Zscq;
import com.aynu.dto.Audit;
import com.aynu.dto.dtos.DepartmentOpinion;
import com.aynu.dto.dtos.SchoolOpinion;

import java.util.List;

/**
 * @Author susuper
 * @Date 2020/9/21 21:05
 * @description:
 */
public interface ZschService {
    List<Zscq> selectZscqByDetail(String department, String number, String start, String end, String judgestatus,String roletype);
    List<Zscq> selectZscqByDetails(String department, String number, String tcategory,String start, String end, String judgestatus,String roletype);
    boolean insertZscq(Zscq zscq);

    boolean deleteZscq(List<String> list,String number,String department);

    Zscq showZscq(String department, String number,String id);

    boolean updateZscq(Zscq zscq);
    boolean updateZscqByHigh(Audit audit);

    boolean updateZschByDepartmentOpinion(DepartmentOpinion departmentOpinion);
    boolean updateZschBySchoolOpinion(SchoolOpinion schoolOpinion);
}
