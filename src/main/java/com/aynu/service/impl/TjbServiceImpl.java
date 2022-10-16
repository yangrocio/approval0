package com.aynu.service.impl;

import com.aynu.dao.TjbDao;
import com.aynu.dto.Mxhzb;
import com.aynu.dto.Tjb;
import com.aynu.service.TjbService;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;
import java.util.regex.Pattern;

/**
 * @Author susuper
 * @Date 2020/11/8 9:58
 * @description:
 */
@Service
public class TjbServiceImpl implements TjbService {
    @Resource
    TjbDao tjbDao;

    @Override
    public List<Tjb> selectAll(String start, String end, String department, String number) {
        List<Tjb> tjbList = tjbDao.selectAll(start, end, department, number);
        tjbList.remove(null);
        return tjbList;
    }

    public boolean isNumber(String string) {
        if (string == null)
            return false;
        Pattern pattern = Pattern.compile("^-?\\d+(\\.\\d+)?$");
        return pattern.matcher(string).matches();
    }

    @Override
    public HSSFWorkbook annualExcel(String fileName, Integer flag, String department, String number, String start, String end) {
        List<Tjb> tjbList = tjbDao.selectAll(start, end, department, number);
        tjbList.remove(null);
        String[] title = new String[]{"部门", "工号", "姓名", "业绩点", "科研奖金(万)", "备注", "签名"};
        String[][] data = new String[tjbList.size()][];
        int length = tjbList.size();
        BigDecimal rewardSum = new BigDecimal(0);
        BigDecimal pointSum = new BigDecimal(0);
//        Double rewardSum = 0.0;
//        Double pointSum = 0.0;
        for (int i = 0; i < length; i++) {
            data[i] = new String[title.length];
            data[i][0] = tjbList.get(i).getDepartment();
            data[i][1] = tjbList.get(i).getNumber();
            data[i][2] = tjbList.get(i).getName();
            data[i][3] = tjbList.get(i).getPoints();
            data[i][4] = tjbList.get(i).getReward();
            data[i][5] = tjbList.get(i).getFq();
            data[i][6] = "";
            if (isNumber(tjbList.get(i).getReward())) {
                rewardSum = rewardSum.add(new BigDecimal(String.valueOf(tjbList.get(i).getReward())));
//                rewardSum = rewardSum + Double.valueOf(tjbList.get(i).getReward());
            }
            if (isNumber(tjbList.get(i).getPoints())) {
                pointSum = pointSum.add(new BigDecimal(String.valueOf(tjbList.get(i).getPoints())));

//                pointSum = pointSum + Double.valueOf(tjbList.get(i).getPoints());
            }

        }

        HSSFWorkbook wb = getHSSFWorkbook(fileName, flag, "业绩点、奖励汇总", title, data, null, pointSum, rewardSum);
        return wb;
    }

    public HSSFWorkbook getHSSFWorkbook(String topic, Integer flag, String sheetName, String[] title, String[][] values, HSSFWorkbook wb, BigDecimal points, BigDecimal rewards) {
        if (wb == null) {
            wb = new HSSFWorkbook();
        }
        HSSFCell cell = null;
        HSSFRow row = null;
        topic = topic.substring(0, topic.indexOf("."));
        HSSFSheet sheet = wb.createSheet(sheetName);
        CellRangeAddress cellRange1 = new CellRangeAddress(0, 0, (short) 0, (short) (title.length - 1));

        sheet.addMergedRegion(cellRange1);

        HSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setWrapText(true);
        HSSFFont font = wb.createFont();
        font.setFontName("宋体");
        font.setFontHeightInPoints((short) 16);
        style.setFont(font);

        if (flag == 1) {
            row = sheet.createRow(0);  //使用第0行

            row.setHeightInPoints(30);
            cell = row.createCell(0);
            cell.setCellStyle(style);
            cell.setCellValue(topic);
            return setMessageExcel(1, title, values, wb, sheet, points, rewards);
        } else if (flag == 2) {
            CellRangeAddress cellRange2 = new CellRangeAddress(1, 1, (short) 0, (short) (title.length / 2 - 1));
            CellRangeAddress cellRange3 = new CellRangeAddress(1, 1, (short) title.length / 2, (short) (title.length - 1));
            CellRangeAddress cellRange4 = new CellRangeAddress(2, 2, (short) 0, (short) (title.length / 2 - 1));
            CellRangeAddress cellRange5 = new CellRangeAddress(2, 2, (short) title.length / 2, (short) (title.length - 1));
            sheet.addMergedRegion(cellRange2);
            sheet.addMergedRegion(cellRange3);
            sheet.addMergedRegion(cellRange4);
            sheet.addMergedRegion(cellRange5);
            //院系部分  需要占用3行
            row = sheet.createRow(0);  //使用第0行
            row.setHeightInPoints(30);

            cell = row.createCell(0);
            cell.setCellStyle(style);
            cell.setCellValue(topic);


            //第一行的两个格子
            HSSFCellStyle style1 = wb.createCellStyle();
            style1.setWrapText(true);
            HSSFFont font1 = wb.createFont();
            font1.setFontName("黑体");
            font1.setBold(true);
            font1.setFontHeightInPoints((short) 14);
            style1.setFont(font1);

            row = sheet.createRow(1);
            row.setHeightInPoints(30);

            cell = row.createCell(0);
            cell.setCellValue("单位（公章）：");
            cell.setCellStyle(style1);

            cell = row.createCell(title.length / 2);
            cell.setCellValue("院长（主任）签字：");
            cell.setCellStyle(style1);

            row = sheet.createRow(2);
            row.setHeightInPoints(30);
            cell = row.createCell(0);
            cell.setCellValue("填表人签字： ");
            cell.setCellStyle(style1);
            cell = row.createCell(title.length / 2);
            cell.setCellValue("公示时间：");
            cell.setCellStyle(style1);

            return setMessageExcel(3, title, values, wb, sheet, points, rewards);
        } else if (flag == 3) {
            CellRangeAddress cellRange2 = new CellRangeAddress(1, 1, (short) 0, (short) (title.length / 2 - 1));
            CellRangeAddress cellRange3 = new CellRangeAddress(1, 1, (short) title.length / 2, (short) (title.length - 1));
            sheet.addMergedRegion(cellRange2);
            sheet.addMergedRegion(cellRange3);
            //学校部分 需要占用 1行
            row = sheet.createRow(0);  //使用第0行
            row.setHeightInPoints(30);
            cell = row.createCell(0);
            cell.setCellStyle(style);
            cell.setCellValue(topic);


            //第一行的两个格子
            HSSFCellStyle style1 = wb.createCellStyle();
            style1.setWrapText(true);
            HSSFFont font1 = wb.createFont();
            font1.setFontName("黑体");
            font1.setBold(true);
            font1.setFontHeightInPoints((short) 14);
            style1.setFont(font1);


            row = sheet.createRow(1);
            row.setHeightInPoints(30);
            cell = row.createCell(0);
            cell.setCellValue("填表人签字： ");
            cell.setCellStyle(style1);
            cell = row.createCell(title.length / 2);
            cell.setCellValue("公示时间：");
            cell.setCellStyle(style1);

            return setMessageExcel(2, title, values, wb, sheet, points, rewards);
        }
        return wb;
    }

    public HSSFWorkbook setMessageExcel(Integer mark, String[] title, String[][] values, HSSFWorkbook wb, HSSFSheet sheet, BigDecimal points, BigDecimal rewards) {
        HSSFRow row = sheet.createRow(mark);
        HSSFCell cell = null;

        row.setHeightInPoints(30);

        HSSFFont font1 = wb.createFont();
        font1.setFontName("黑体");
        font1.setFontHeightInPoints((short) 12);
        HSSFCellStyle style1 = wb.createCellStyle();

        style1.setBorderBottom(BorderStyle.MEDIUM);
        style1.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        style1.setBorderLeft(BorderStyle.MEDIUM);
        style1.setLeftBorderColor(IndexedColors.BLACK.getIndex());
        style1.setBorderRight(BorderStyle.MEDIUM);
        style1.setRightBorderColor(IndexedColors.BLACK.getIndex());
        style1.setBorderTop(BorderStyle.MEDIUM);
        style1.setTopBorderColor(IndexedColors.BLACK.getIndex());


        style1.setAlignment(HorizontalAlignment.CENTER);
        style1.setWrapText(true);
        style1.setFont(font1);


        //设置标题
        int width[] = new int[]{19 * 256, 15 * 256, 18 * 256, 12 * 256, 17 * 256, 11 * 256, 26 * 256};
        for (int i = 0; i < title.length; i++) {
            sheet.setColumnWidth(i, width[i]);
            cell = row.createCell(i);
            cell.setCellValue(title[i]);
            cell.setCellStyle(style1);
        }


        HSSFFont font2 = wb.createFont();
        font2.setFontName("宋体");
        font2.setFontHeightInPoints((short) 12);
        HSSFCellStyle style2 = wb.createCellStyle();

        style2.setBorderBottom(BorderStyle.MEDIUM);
        style2.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        style2.setBorderLeft(BorderStyle.MEDIUM);
        style2.setLeftBorderColor(IndexedColors.BLACK.getIndex());
        style2.setBorderRight(BorderStyle.MEDIUM);
        style2.setRightBorderColor(IndexedColors.BLACK.getIndex());
        style2.setBorderTop(BorderStyle.MEDIUM);
        style2.setTopBorderColor(IndexedColors.BLACK.getIndex());

        style2.setAlignment(HorizontalAlignment.CENTER);
        style2.setVerticalAlignment(VerticalAlignment.CENTER);
        style2.setWrapText(true);
        style2.setFont(font2);

        for (int i = 0; i < values.length; i++) {
            row = sheet.createRow(i + mark + 1);
            row.setHeightInPoints(45);
            for (int j = 0; j < values[i].length; j++) {
                //将内容按顺序赋给对应的列对象
                cell = row.createCell(j);
                cell.setCellValue(values[i][j]);
                cell.setCellStyle(style2);
            }
        }

        row = sheet.createRow(values.length + mark + 1);
        row.setHeightInPoints(45);

        row.createCell(0).setCellStyle(style1);
        row.createCell(1).setCellStyle(style1);
        row.createCell(2).setCellStyle(style1);
        row.createCell(3).setCellStyle(style1);


        row.createCell(title.length-1).setCellStyle(style1);
        row.createCell(title.length-2).setCellStyle(style1);

        cell = row.createCell(title.length - 5);
        cell.setCellStyle(style1);
        cell.setCellValue("合计： ");
        cell = row.createCell(title.length - 4);
        cell.setCellStyle(style1);
        cell.setCellValue(points.toString());
        cell = row.createCell(title.length - 3);
        cell.setCellStyle(style1);
        cell.setCellValue(rewards.toString());
        return wb;
    }
}
