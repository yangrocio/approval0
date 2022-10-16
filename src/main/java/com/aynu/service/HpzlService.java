package com.aynu.service;

import com.aynu.bean.Hpzl;
import com.aynu.dto.Audit;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.util.List;

/**
 * @author susuper
 * @version 1.0
 * @date 2020/9/11 11:12
 */
public interface HpzlService {
    List<Hpzl> selectHpzlByDetail(String department, String number, String name, String start, String end,
                                  String judgestatus, String roletype);

    List<Hpzl> selectHpzlByDetail2(String department, String number, String name, String start, String end,
                                   String judgestatus, String roletype, String XMName, String FMR, String QTFMR, String ZLNumber, String PType, String SYTime);

    boolean insertHpzl(Hpzl hpzl);

    boolean deleteHpzl(List<String> list, String number, String department);

    int deleteById(List<String> list);

    Hpzl showHpzl(String department, String number, String id);

    boolean updateHpzl(Hpzl hpzl);
    boolean updateHpzlByHigh(Audit audit);

    void deleteFjscByList(List<String> list);

    String downloadhpzlZipFjsc(List<String> list);
    String getExcelJson(String department, String number, String name, String start, String end, String judgestatus, List<String> params);

    HSSFWorkbook exporthpzlExcel(String department, String number, String roletype);
}
