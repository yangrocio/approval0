package com.aynu.service;

import com.aynu.bean.Tyhj;
import com.aynu.dto.Audit;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.util.List;

/**
 * @author susuper
 * @version 1.0
 * @date 2020/9/11 11:12
 */
public interface TyhjService {
    List<Tyhj> selectTyhjByDetail(String department, String number, String name, String start, String end,
                                  String judgestatus, String roletype);
    List<Tyhj> selectTyhjByDetail2(String department, String number, String name, String start, String end,
                                   String judgestatus, String roletype, String Awardee, String Entryname, String SportsLV, String WinTime);
    boolean insertTyhj(Tyhj tyhj);

    boolean deleteTyhj(List<String> list, String number, String department);

    int deleteById(List<String> list);

    Tyhj showTyhj(String department, String number, String id);

    boolean updateTyhj(Tyhj tyhj);
    boolean updateTyhjByHigh(Audit audit);
    void deleteFjscByList(List<String> list);

    String downloadtyhjZipFjsc(List<String> list);

    HSSFWorkbook exporttyhjExcel(String department, String number, String roletype);
    String getExcelJson(String department, String number, String name, String start, String end, String judgestatus, List<String> params);

}
