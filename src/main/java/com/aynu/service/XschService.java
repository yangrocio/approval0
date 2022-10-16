package com.aynu.service;

import com.aynu.bean.Xsch;
import com.aynu.dto.Audit;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.util.List;

/**
 * @author susuper
 * @version 1.0
 * @date 2020/9/11 11:12
 */
public interface XschService {
    List<Xsch> selectXschByDetail(String department, String number, String name, String start, String end, String judgestatus, String roletype);
    boolean insertXsch(Xsch xsch);

    List<Xsch> selectXschByDetail2(String department, String number, String name, String start, String end, String judgestatus, String roletype, String XMName, String Title, String Rank, String XFBM, String Time);


    boolean deleteXsch(List<String> list, String number, String department);

    int deleteById(List<String> list);

    Xsch showXsch(String department, String number, String id);

    boolean updateXsch(Xsch xsch);
    boolean updateXschByHigh(Audit audit);

    void deleteFjscByList(List<String> list);

    String downloadxschZipFjsc(List<String> list);

    HSSFWorkbook exportxschExcel(String department, String number, String roletype);
    String getExcelJson(String department, String number, String name, String start, String end, String judgestatus, List<String> params);

}
