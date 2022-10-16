package com.aynu.service;

import com.aynu.bean.Zkjs;
import com.aynu.dto.Audit;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.util.List;

/**
 * @author susuper
 * @version 1.0
 * @date 2020/9/11 11:12
 */
public interface ZkjsService {
    List<Zkjs> selectZkjsByDetail(String department, String number, String name, String start, String end,
                                  String judgestatus, String roletype);
    List<Zkjs> selectZkjsByDetail2(String department, String number, String name, String start, String end,
                                   String judgestatus, String roletype, String cgname, String cnjb, String dyzz,
                                   String qtzz, String cnbm, String cntime);
    boolean insertZkjs(Zkjs zkjs);

    boolean deleteZkjs(List<String> list, String number, String department);

    int deleteById(List<String> list);

    Zkjs showZkjs(String department, String number, String id);

    boolean updateZkjs(Zkjs zkjs);
    boolean updateZkjsByHigh(Audit audit);

    void deleteFjscByList(List<String> list);

    String downloadzkjsZipFjsc(List<String> list);

    HSSFWorkbook exportzkjsExcel(String department, String number, String roletype);
    String getExcelJson(String department, String number, String name, String start, String end, String judgestatus, List<String> params);

}
