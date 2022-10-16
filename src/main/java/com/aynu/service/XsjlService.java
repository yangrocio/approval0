package com.aynu.service;

import com.aynu.bean.Xsjl;
import com.aynu.dto.Audit;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.util.List;

/**
 * @author susuper
 * @version 1.0
 * @date 2020/9/11 11:12
 */
public interface XsjlService {
    List<Xsjl> selectXsjlByDetail(String department, String number, String name, String start, String end,
                                  String judgestatus, String roletype);

    List<Xsjl> selectXsjlByDetail2(String department, String number, String name, String start, String end,
                                   String judgestatus, String roletype, String XMName, String CYRY, String Rank);

    boolean insertXsjl(Xsjl xsjl);
    boolean deleteXsjl(List<String> list, String number, String department);

    int deleteById(List<String> list);

    Xsjl showXsjl(String department, String number, String id);

    boolean updateXsjl(Xsjl xsjl);
    boolean updateXsjlByHigh(Audit audit);

    void deleteFjscByList(List<String> list);

    String downloadxsjlZipFjsc(List<String> list);

    HSSFWorkbook exportxsjlExcel(String department, String number, String roletype);
    String getExcelJson(String department, String number, String name, String start, String end, String judgestatus, List<String> params);

}
