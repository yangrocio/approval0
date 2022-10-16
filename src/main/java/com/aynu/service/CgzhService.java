package com.aynu.service;

import com.aynu.bean.Cgzh;
import com.aynu.dto.Audit;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.util.List;

/**
 * @author susuper
 * @version 1.0
 * @date 2020/9/11 11:12
 */
public interface CgzhService {
    List<Cgzh> selectCgzhByDetail(String department, String number, String name, String start, String end,
                                  String judgestatus, String roletype);
    List<Cgzh> selectCgzhByDetail2(String department, String number, String name, String start, String end,
                                   String judgestatus, String roletype, String cgname, String dyzz, String qtzz, String zhdw, String zrlx, String zhtime);
    boolean insertCgzh(Cgzh cgzh);

    boolean deleteCgzh(List<String> list, String number, String department);

    int deleteById(List<String> list);

    Cgzh showCgzh(String department, String number, String id);

    boolean updateCgzh(Cgzh cgzh);
    boolean updateCgzhByHigh(Audit audit);

    void deleteFjscByList(List<String> list);

    String downloadcgzhZipFjsc(List<String> list);

    HSSFWorkbook exportcgzhExcel(String department, String number, String roletype);
    String getExcelJson(String department, String number, String name, String start, String end, String judgestatus, List<String> params);

}
