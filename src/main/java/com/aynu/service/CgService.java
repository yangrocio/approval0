package com.aynu.service;

import com.aynu.bean.Cg;
import com.aynu.dto.Audit;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.util.List;

/**
 * @author susuper
 * @version 1.0
 * @date 2020/9/11 11:12
 */
public interface CgService {
    List<Cg> selectCgByDetail(String department, String number, String start, String end,
                              String judgestatus,String roletype);
    boolean insertCg(Cg cg);

    boolean deleteCg(List<String> list,String number,String department);

    Cg showCg(String department, String number,String id);

    boolean updateCg(Cg cg);
    boolean updateCgByHigh(Audit audit);

    void deleteFjscByList(List<String> list);

    String downloadcgZipFjsc(List<String> list);

    HSSFWorkbook exportcgExcel(String department, String number, String roletype);
}
