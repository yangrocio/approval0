package com.aynu.service;

import com.aynu.dto.Mxhzb;
import org.apache.ibatis.annotations.Param;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.util.List;

/**
 * @Author susuper
 * @Date 2020/10/29 17:26
 * @description:
 */
public interface MxhzbService {
    List<Mxhzb> selectAll(String start,String end,String department,String number);

    HSSFWorkbook annualExcel(String fileName,Integer flag ,String department, String number, String start,String end);

}
