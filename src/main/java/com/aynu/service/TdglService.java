package com.aynu.service;

import com.aynu.bean.Hylw;
import com.aynu.bean.TdglEntity;
import com.aynu.bean.Team;
import com.aynu.bean.UserInfo;
import com.aynu.dto.Audit;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;

/**
 * @author guojingdong
 */
public interface TdglService {
    List<TdglEntity> findAll();



    List<TdglEntity> findCondition(String department,String name,String start,String end);

    TdglEntity findById(BigInteger i);

    TdglEntity findByNo(String no);

    TdglEntity findByName(String name);

    List<TdglEntity> findByDepartment(String department);

    void deleteByNo(String no);

    TdglEntity insert(TdglEntity tdglEntity);

    TdglEntity update(TdglEntity tdglEntity);

    boolean updateState(Audit audit);

    void deleteById(BigInteger id);

    void deleteFjscByList(List<String> list);

    String downloadhylwZipFjsc(List<String> list);

    TdglEntity showtdgl(String department, String no, String id);

    List<TdglEntity> selectByDetail(String department, String number, String name, String start,
                                  String end, String judgestatus,String roletype);

    //String getExcelJson(String department, String number,String name, String start, String end, String judgestatus, List<String> params);

}
