package com.aynu.Utils;

import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.enums.CellDataTypeEnum;
import com.alibaba.excel.metadata.CellData;
import com.alibaba.excel.metadata.GlobalConfiguration;
import com.alibaba.excel.metadata.property.ExcelContentProperty;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author susuper
 * @Date 2020/12/2 21:47
 * @description:
 */
public class DateAndStringConverter implements Converter<String> {
    @Override
    public String convertToJavaData(CellData cellData, ExcelContentProperty excelContentProperty, GlobalConfiguration globalConfiguration) throws Exception {
        System.out.println(cellData.toString()+":cellData");
        System.out.println(cellData.getStringValue());
        SimpleDateFormat sdf =new SimpleDateFormat("yyyy/MM");
//        String news =
//                sdf.parse(cellData.getDataFormatString());
//        return news;
        return null;
    }

    @Override
    public CellData convertToExcelData(String o, ExcelContentProperty excelContentProperty, GlobalConfiguration globalConfiguration) throws Exception {
//        System.out.println(o+"Object");
        return null;
    }

    @Override
    public Class supportJavaTypeKey() {
        return String.class;
    }

    @Override
    public CellDataTypeEnum supportExcelTypeKey() {
        return CellDataTypeEnum.STRING;
    }
}
