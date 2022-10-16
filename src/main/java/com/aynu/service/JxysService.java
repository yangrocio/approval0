package com.aynu.service;

import com.aynu.bean.Jxys;
import com.aynu.dto.Audit;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.util.List;

/**
 * @Author susuper
 * @Date 2020/11/8 21:15
 * @description:
 */
public interface JxysService {
    List<Jxys> selectJxysByDetail(String department, String number, String name, String start, String end,
                                  String judgestatus, String roletype);

    List<Jxys> selectJxysByDetail2(String department, String number, String name, String start, String end,
                                   String judgestatus, String roletype, String xmname, String hoster, String cyry, String xdbm, String xmly, String rank, String proproperty, String time);

    boolean insertJxys(Jxys jxys);

    boolean deleteJxys(List<String> list, String number, String department);

    int deleteById(List<String> list);

    Jxys showJxys(String department, String number, String id);

    boolean updateJxys(Jxys jxys);
    boolean updateJxysByHigh(Audit audit);

    void deleteFjscByList(List<String> list);

    String downloadcgZipFjsc(List<String> list);

    HSSFWorkbook exportcgExcel(String department, String number, String roletype);
    String getExcelJson(String department, String number, String name, String start, String end, String judgestatus, List<String> params);

}
