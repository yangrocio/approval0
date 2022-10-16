package com.aynu.service;

import com.aynu.bean.Xszz;
import com.aynu.dto.Audit;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.util.List;

/**
 * @author susuper
 * @version 1.0
 * @date 2020/9/11 11:12
 */
public interface XszzService {
    List<Xszz> selectXszzByDetail(String department, String number, String name,String start, String end, String judgestatus,String roletype);

    List<Xszz> selectXszzByDetail2(String department, String number, String name,String start, String end, String judgestatus,String roletype,String timu,String dyzz,String qtzz,String cbsType,String zzType,String time);


    boolean insertXszz(Xszz xszz);

    boolean deleteXszz(List<String> list,String number,String department);

    int deleteById(List<String> list);

    Xszz showXszz(String department, String number,String id);

    boolean updateXszz(Xszz hylw);
    boolean updateXszzByHigh(Audit audit);

    void deleteFjscByList(List<String> list);

    HSSFWorkbook exportxszzExcel(String department, String number, String roletype);

    String downloadxszzZipFjsc(List<String> list);
    String getExcelJson(String department, String number, String name,String start, String end, String judgestatus, List<String> params);

}
