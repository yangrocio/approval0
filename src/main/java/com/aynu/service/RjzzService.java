package com.aynu.service;

import com.aynu.bean.Rjzz;
import com.aynu.dto.Audit;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.util.List;

/**
 * @author susuper
 * @version 1.0
 * @date 2020/9/11 11:12
 */
public interface RjzzService {
    List<Rjzz> selectRjzzByDetail(String department, String number, String name, String start, String end, String judgestatus, String roletype);

    List<Rjzz> selectRjzzByDetail2(String department, String number, String name, String start, String end, String judgestatus, String roletype, String XMName, String DYZZ, String QTZZ, String DJNumber, String DJTime);

    boolean insertRjzz(Rjzz rjzz);
    boolean deleteRjzz(List<String> list, String number, String department);

    Rjzz showRjzz(String department, String number, String id);

    int deleteById(List<String> list);

    boolean updateRjzz(Rjzz rjzz);
    boolean updateRjzzByHigh(Audit audit);

    void deleteFjscByList(List<String> list);

     String downloadrjzzZipFjsc(List<String> list);

    HSSFWorkbook exportrjzzExcel(String department, String number, String roletype);
    String getExcelJson(String department, String number, String name, String start, String end, String judgestatus, List<String> params);

}
