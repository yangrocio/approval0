package com.aynu.dao;

import com.aynu.bean.PtglEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.List;

/**
 * @author guojingdong
 */

public interface PtglResposity extends JpaRepository<PtglEntity, String>, JpaSpecificationExecutor<PtglEntity> {
    @Transactional
    void deleteByNo(String no);


    PtglEntity findByNo(String no);

    PtglEntity findById(BigInteger i);

    PtglEntity findByPlatform(String name);

    List<PtglEntity> findByDepartment(String department);

    @Transactional
    void deleteById(BigInteger i);

}
