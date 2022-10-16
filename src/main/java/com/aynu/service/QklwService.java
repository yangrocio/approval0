package com.aynu.service;

import com.aynu.bean.Hylw;
import com.aynu.bean.Qklw;
import com.aynu.dto.Audit;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.util.List;

/**
 * @author susuper
 * @version 1.0
 * @date 2020/9/11 11:12
 */
public interface QklwService {
    List<Qklw> selectQklwByDetail(String department, String number,String name, String start, String end, String judgestatus,String roletype);

    List<Qklw> selectQklwByDetail2(String department, String number,String name, String start, String end,
                                   String judgestatus,String roletype,String timu,String dyzz,String qtzz,String type,
                                   String fbqk,String time,String fq);


    boolean insertQklw(Qklw qklw);

    boolean deleteQklw(List<String> list,String number,String department);

    Qklw showQklw(String department, String number,String id);

    int deleteById(List<String> list);

    boolean updateQklw(Qklw qklw);

    boolean updateQklwByHigh(Audit audit);

    void deleteFjscByList(List<String> list);

    HSSFWorkbook exportqklwExcel(String department, String number, String roletype);

    String downloadqklwZipFjsc(List<String> list);


    String getExcelJson(String department, String number,String name, String start, String end, String judgestatus, List<String> params);

}
