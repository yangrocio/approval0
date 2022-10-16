package com.aynu.service;

import com.aynu.bean.Hpxm;
import com.aynu.bean.Hylw;
import com.aynu.dto.Audit;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.util.List;

/**
 * @author susuper
 * @version 1.0
 * @date 2020/9/11 11:12
 */
public interface HpxmService {
    List<Hpxm> selectHpxmByDetail(String department, String number, String name, String start, String end, String judgestatus, String roletype);

    List<Hpxm> selectHpxmByDetail2(String department, String number, String name, String start, String end, String judgestatus, String roletype, String xmname, String hoster, String cyry, String xdbm, String xmsource, String prorank, String proproperty, String lxtime);

    boolean insertHpxm(Hpxm hpxm);

    boolean deleteHpxm(List<String> list, String number, String department);

    int deleteById(List<String> list);

    Hpxm showHpxm(String department, String number, String id);

    boolean updateHpxm(Hpxm hpxm);

    boolean updateHpxmByHigh(Audit audit);

    void deleteFjscByList(List<String> list);

    public String downloadhpxmZipFjsc(List<String> list);

    HSSFWorkbook exporthpxmExcel(String department, String number, String roletype);
    String getExcelJson(String department, String number, String name, String start, String end, String judgestatus, List<String> params);


}