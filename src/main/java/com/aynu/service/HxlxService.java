package com.aynu.service;

import com.aynu.bean.Hxlx;
import com.aynu.dto.Audit;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.util.List;

/**
 * @author susuper
 * @version 1.0
 * @date 2020/9/11 11:12
 */
public interface HxlxService {
    List<Hxlx> selectHxlxByDetail(String department, String number, String name, String start, String end, String judgestatus, String roletype);

    List<Hxlx> selectHxlxByDetail2(String department, String number, String name, String start, String end, String judgestatus, String roletype, String XMName, String Principal, String AUnit, String ZJE, String ContractNumber, String JXTime);


    boolean insertHxlx(Hxlx hxlx);

    boolean deleteHxlx(List<String> list, String number, String department);

    int deleteById(List<String> list);

    Hxlx showHxlx(String department, String number, String id);

    boolean updateHxlx(Hxlx hxlx);

    boolean updateHxlxByHigh(Audit audit);

    void deleteFjscByList(List<String> list);

    public String downloadhxlxZipFjsc(List<String> list);
    String getExcelJson(String department, String number, String name, String start, String end, String judgestatus, List<String> params);

    HSSFWorkbook exporthxlxExcel(String department, String number, String roletype);
}
