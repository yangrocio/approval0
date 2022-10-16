package com.aynu.service;

import com.aynu.bean.Hxxm;
import com.aynu.dto.Audit;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.util.List;

/**
 * @author susuper
 * @version 1.0
 * @date 2020/9/11 11:12
 */
public interface HxxmService {
    List<Hxxm> selectHxxmByDetail(String department, String number, String name, String start, String end, String judgestatus, String roletype);
    boolean insertHxxm(Hxxm hxxm);

    List<Hxxm> selectHxxmByDetail2(String department, String number, String name, String start, String end, String judgestatus, String roletype, String XMName, String Principal, String AUnit, String ZJE, String ContractNumber, String JXTime);


    boolean deleteHxxm(List<String> list, String number, String department);

    int deleteById(List<String> list);

    Hxxm showHxxm(String department, String number, String id);

    boolean updateHxxm(Hxxm hxxm);

    boolean updateHxxmByHigh(Audit audit);

    void deleteFjscByList(List<String> list);

    public String downloadhxxmZipFjsc(List<String> list);

    HSSFWorkbook exporthxxmExcel(String department, String number, String roletype);
    String getExcelJson(String department, String number, String name, String start, String end, String judgestatus, List<String> params);

}
