package com.aynu.service;

import com.aynu.bean.Ymhj;
import com.aynu.dto.Audit;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.util.List;

/**
 * @author susuper
 * @version 1.0
 * @date 2020/9/11 11:12
 */
public interface YmhjService {
    List<Ymhj> selectYmhjByDetail(String department, String number, String name, String start, String end,
                                  String judgestatus, String roletype);
    List<Ymhj> selectYmhjByDetail2(String department, String number, String name, String start, String end,
                                   String judgestatus, String roletype, String TiMu, String AwardName, String DYZZ, String QTZZ, String Organizer,
                                   String RewardSort, String AwardTime);
    boolean insertYmhj(Ymhj ymhj);

    boolean deleteYmhj(List<String> list, String number, String department);

    int deleteById(List<String> list);

    Ymhj showYmhj(String department, String number, String id);

    boolean updateYmhj(Ymhj ymhj);
    boolean updateYmhjByHigh(Audit audit);

    void deleteFjscByList(List<String> list);

    String downloadymhjZipFjsc(List<String> list);

    HSSFWorkbook exportymhjExcel(String department, String number, String roletype);
    String getExcelJson(String department, String number, String name, String start, String end, String judgestatus, List<String> params);

}
