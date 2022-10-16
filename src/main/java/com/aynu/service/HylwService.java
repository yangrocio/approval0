package com.aynu.service;

import com.aynu.bean.Hylw;
import com.aynu.dto.Audit;
import org.apache.ibatis.annotations.Param;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.util.List;
import java.util.Map;

/**
 * @author susuper
 * @version 1.0
 * @date 2020/9/11 11:12
 */
public interface HylwService {
    List<Hylw> selectHylwByDetail(String department, String number, String name, String start,
                                  String end, String judgestatus,String roletype);
    List<Hylw> selectHylwByDetail2(String department, String number, String name, String start, String end,
                                  String judgestatus,String roletype,String tm,String dyzz,String qtzz,String hymc,
                                  String hysj,String hyjb,String jllb);

    boolean insertHylw(Hylw hylw);

    boolean deleteHylw(List<String> list, String number, String department);

    Hylw showHylw(String department, String number, String id);

    boolean updateHylw(Hylw hylw);

    int deleteById(List<String> list);

    boolean updateHylwByHigh(Audit audit);

    void deleteFjscByList(List<String> list);

    public String downloadhylwZipFjsc(List<String> list);

    HSSFWorkbook exporthylwExcel(String department, String number, String roletype);


    String getExcelJson(String department, String number,String name, String start, String end, String judgestatus, List<String> params);


}