package com.aynu.Utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.aynu.bean.UserInfo;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.crypto.Data;


/**
 * excel汇总表的导入
 *
 * @author Gjd
 * @version 1.0
 * @date 2020年7月3日 下午11:21:18
 * @remarks TODO
 */
public class SingleExcelUtil {
    private static final String XLS = "xls";
    private static final String XLSX = "xlsx";
    static final Logger logger = LoggerFactory.getLogger(SingleExcelUtil.class);

    /**
     * 根据文件后缀名类型获取对应的工作簿对象
     *
     * @param inputStream 读取文件的输入流
     * @param fileType    文件后缀名类型（xls或xlsx）
     * @return 包含文件数据的工作簿对象
     * @throws IOException
     */
    public static Workbook getWorkbook(InputStream inputStream, String fileType) throws IOException {
        Workbook workbook = null;
        if (fileType.equalsIgnoreCase(XLS)) {
            workbook = new HSSFWorkbook(inputStream);
        } else if (fileType.equalsIgnoreCase(XLSX)) {
            workbook = new XSSFWorkbook(inputStream);
        }
        return workbook;
    }

    /**
     * 读取Excel文件内容
     *
     * @return 读取结果列表，读取失败时返回null
     */
    public static List<UserInfo> readExcel(String fileType, InputStream inputStream) {
//        String fileName
        Workbook workbook = null;
//        FileInputStream inputStream = null;

        try {
            // 获取Excel后缀名
//            String fileType = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
//            // 获取Excel文件
//            File excelFile = new File(fileName);
//            if (!excelFile.exists()) {
//                logger.info("指定的Excel文件不存在！");
//                return null;
//            }

            // 获取Excel工作簿
//            inputStream = new FileInputStream(excelFile);
            workbook = getWorkbook(inputStream, fileType);

            // 读取excel中的数据
            List<UserInfo> resultDataList = parseExcel(workbook);

            return resultDataList;
        } catch (Exception e) {
            logger.info("解析Excel失败，文件名：" + " 错误信息：" + e);
            return null;
        } finally {
            try {
                if (null != workbook) {
                    workbook.close();
                }
                if (null != inputStream) {
                    inputStream.close();
                }
            } catch (Exception e) {
                logger.info("关闭数据流出错！错误信息：" + e);
                return null;
            }
        }
    }


    /**
     * 解析Excel数据
     *
     * @param workbook Excel工作簿对象
     * @return 解析结果
     */
    private static List<UserInfo> parseExcel(Workbook workbook) {
        List<UserInfo> resultDataList = new ArrayList<UserInfo>();
        // 解析sheet
        for (int sheetNum = 0; sheetNum < workbook.getNumberOfSheets(); sheetNum++) {
            Sheet sheet = workbook.getSheetAt(sheetNum);

            // 校验sheet是否合法
            if (sheet == null) {
                continue;
            }

            // 获取第一行数据
            int firstRowNum = sheet.getFirstRowNum();
            Row firstRow = sheet.getRow(firstRowNum);
//            for(int i = 0;i<firstRow.getPhysicalNumberOfCells();i++){
//                System.out.print(firstRow.getCell(i)+"   ");
//            }
//            System.out.println();
            if (null == firstRow) {
                logger.info("解析Excel失败，在第一行没有读取到任何数据！");
            }

            // 解析每一行的数据，构造数据对象
            int rowStart = firstRowNum + 1;
            int rowEnd = sheet.getPhysicalNumberOfRows();
            System.out.println("导入的行数是：" + rowEnd);
            for (int rowNum = rowStart; rowNum < rowEnd; rowNum++) {
                Row row = sheet.getRow(rowNum);
//                for(int i = 0;i<row.getPhysicalNumberOfCells();i++){
//                    System.out.print(row.getCell(i)+"   ");
//                }
                if (null == row) {
                    continue;
                }
                UserInfo resultData = convertRowToData(row);
                if (null == resultData) {
                    logger.info("第 " + row.getRowNum() + "行数据不合法，已忽略！");
                    continue;
                }

//                System.out.println();
                resultDataList.add(resultData);
            }
        }

        return resultDataList;
    }

    /**
     * 提取每一行中需要的数据，构造成为一个结果数据对象
     * <p>
     * 当该行中有单元格的数据为空或不合法时，忽略该行的数据
     *
     * @param row 行数据
     * @return 解析后的行数据对象，行数据错误时返回null
     */
    private static UserInfo convertRowToData(Row row) {
        UserInfo resultData = new UserInfo();

        Cell cell;
        int cellNum = 0;
        // 获取序号
        cell = row.getCell(cellNum++);
        String id = convertCellValueToString(cell);
        if (null == id || "".equals(id)) {
            resultData.setId(0);
        } else {
            resultData.setId(Integer.valueOf(id));
        }

        // 获取姓名
        cell = row.getCell(cellNum++);
        String name = convertCellValueToString(cell);
        resultData.setName(name);

        // 获取性别
        cell = row.getCell(cellNum++);
        String sex = convertCellValueToString(cell);
        int sexNumber;
        if (sex.equals("男")) {
            sexNumber = 1;
        } else {
            sexNumber = 2;
        }
        resultData.setSex(sexNumber);

        // 获取民族
        cell = row.getCell(cellNum++);
        String nation = convertCellValueToString(cell);
        resultData.setNation(nation);

        // 获取身份证号
        cell = row.getCell(cellNum++);
        String idcard = convertCellValueToString(cell);
        resultData.setIdcard(idcard);


        // 获取生日
        cell = row.getCell(cellNum++);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        double value = cell.getNumericCellValue();
        Date date = DateUtil.getJavaDate(value);


        resultData.setBirthday(date);


        // 获取电话
        cell = row.getCell(cellNum++);
        String telephone = convertCellValueToString(cell);
        resultData.setTelephone(telephone);

        // 获取院系
        cell = row.getCell(cellNum++);
        String department = convertCellValueToString(cell);
        resultData.setDepartment(department);

        // 获取Number
        cell = row.getCell(cellNum++);
        String number = convertCellValueToString(cell);
        resultData.setNumber(number);


        // 获取学历 硕士研究生  本科  博士研究生等
        cell = row.getCell(cellNum++);
        String qualifications = convertCellValueToString(cell);
        resultData.setQualifications(qualifications);


        // 获取学位  硕士   博士————
        cell = row.getCell(cellNum++);
        String degree = convertCellValueToString(cell);
        resultData.setDegree(degree);


        // 获取hdgu
        cell = row.getCell(cellNum++);
        String hdgu = convertCellValueToString(cell);
        resultData.setHdgu(hdgu);

        // 获取专业    计算机应用技术   应用数学  软件工程——
        cell = row.getCell(cellNum++);
        String major = convertCellValueToString(cell);
        resultData.setMajor(major);


        // 获取账号
        cell = row.getCell(cellNum++);
        String profession = convertCellValueToString(cell);
        resultData.setProfession(profession);

        //设置几个必须有的情况
        resultData.setRoletype("1");


        resultData.setPassword(idcard.substring(idcard.length() - 6));
        return resultData;
    }

    /**
     * 将单元格内容转换为字符串
     *
     * @param cell
     * @return
     */
    private static String convertCellValueToString(Cell cell) {
        if (cell == null) {
            return null;
        }
        String returnValue = null;
        switch (cell.getCellTypeEnum()) {

            case NUMERIC:   //数字
                Double doubleValue = cell.getNumericCellValue();
                // 格式化科学计数法，取一位整数
                DecimalFormat df = new DecimalFormat("0");
                returnValue = df.format(doubleValue);
                break;
            case STRING:    //字符串
                returnValue = cell.getStringCellValue();
                break;
            case BOOLEAN:   //布尔
                Boolean booleanValue = cell.getBooleanCellValue();
                returnValue = booleanValue.toString();
                break;
            case BLANK:     // 空值
                break;
            case FORMULA:   // 公式
                returnValue = cell.getCellFormula();
                break;
            case ERROR:     // 故障
                break;
            default:
                break;
        }
        return returnValue;
    }

}
