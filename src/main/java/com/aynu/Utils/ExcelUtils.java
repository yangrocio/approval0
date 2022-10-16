package com.aynu.Utils;

import org.apache.poi.hssf.usermodel.*;

/**
 * excel 汇总表的导出
 * @author Susuper 果冻
 * @version 1.0
 * @date 2020/5/4 11:55
 * @description
 */
public class ExcelUtils {
    public static HSSFWorkbook getHSSFWorkbook(String sheetName, String []title, String [][]values, HSSFWorkbook wb){
        if (wb == null){
            wb = new HSSFWorkbook();
        }
        HSSFSheet sheet = wb.createSheet(sheetName);
        HSSFRow row = sheet.createRow(0);
        HSSFCellStyle style = wb.createCellStyle();
        HSSFCell cell = null;

        for(int i=0;i<title.length;i++){
            cell = row.createCell(i);
            cell.setCellValue(title[i]);
            cell.setCellStyle(style);
        }
        for(int i=0;i<values.length;i++){
            row = sheet.createRow(i + 1);
            for(int j=0;j<values[i].length;j++){
                //将内容按顺序赋给对应的列对象
                row.createCell(j).setCellValue(values[i][j]);
            }
        }

        return wb;
    }
}
