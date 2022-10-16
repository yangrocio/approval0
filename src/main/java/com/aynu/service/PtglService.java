package com.aynu.service;

import com.aynu.bean.PtglEntity;
import org.apache.poi.ss.formula.ptg.Ptg;

import java.math.BigInteger;
import java.util.List;

/**
 * @author guojingdong
 */
public interface PtglService {
    List<PtglEntity> findAll();

    List<PtglEntity> findCondition(String department,String platform,String start,String end);

    PtglEntity findById(BigInteger i);

    PtglEntity findByNo(String no);

    PtglEntity findByName(String name);

    List<PtglEntity> findByDepartment(String department);

    void deleteByNo(String no);

    PtglEntity insert(PtglEntity ptglEntity);

    PtglEntity update(PtglEntity ptglEntity);

    void deleteById(BigInteger id);

}
