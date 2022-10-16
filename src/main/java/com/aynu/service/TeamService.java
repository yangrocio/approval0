package com.aynu.service;

import com.aynu.bean.Team;
import com.aynu.dto.Mxhzb;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.util.List;

public interface TeamService {
    List<Team> selectAll(String start, String end, String department, String number,String principal);

    HSSFWorkbook annualExcel(String fileName, Integer flag , String department, String number, String start, String end,String principal);

}
