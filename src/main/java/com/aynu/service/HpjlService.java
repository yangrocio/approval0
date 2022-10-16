package com.aynu.service;

import com.aynu.bean.Hpjl;
import com.aynu.dto.Audit;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.util.List;

/**
 * @author susuper
 * @version 1.0
 * @date 2020/9/11 11:12
 */
public interface HpjlService {
    List<Hpjl> selectHpjlByDetail(String department, String number, String name, String start, String end, String judgestatus, String roletype);
    List<Hpjl> selectHpjlByDetail2(String department, String number, String name, String start, String end, String judgestatus, String roletype, String CGName, String JLName, String DYHJR, String QTHJR, String JIXDBM, String JLRank, String HJTime);
    boolean insertHpjl(Hpjl hpjl);

    boolean deleteHpjl(List<String> list, String number, String department);

    int deleteById(List<String> list);

    Hpjl showHpjl(String department, String number, String id);

    boolean updateHpjl(Hpjl hpjl);
    boolean updateHpjlByHigh(Audit audit);
    void deleteFjscByList(List<String> list);

    String downloadhpjlZipFjsc(List<String> list);

    HSSFWorkbook exporthpjlExcel(String department, String number, String roletype);
    String getExcelJson(String department, String number, String name, String start, String end, String judgestatus, List<String> params);

}
