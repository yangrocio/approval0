package com.aynu.dao;

import com.aynu.bean.TdglEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.List;

/**
 * @author guojingdong
 */

public interface TdglResposity extends JpaRepository<TdglEntity, String>, JpaSpecificationExecutor<TdglEntity> {
    @Transactional
    void deleteByNo(TdglEntity no);

    TdglEntity findByNo(String no);

    TdglEntity findById(BigInteger i);

    TdglEntity findByName(String name);

    List<TdglEntity> findByDepartment(String department);

    @Transactional
    void deleteById(BigInteger i);
}
