package com.aynu.service;

import com.aynu.dto.Tjb;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.util.List;

/**
 * @Author susuper
 * @Date 2020/11/8 9:57
 * @description:
 */
public interface TjbService {
    List<Tjb> selectAll(String start, String end, String department, String number);

    HSSFWorkbook annualExcel(String fileName, Integer flag , String department, String number, String start, String end);
}
