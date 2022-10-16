package com.aynu.service.impl;

import com.aynu.Utils.ExcelUtils;
import com.aynu.bean.Hpjl;
import com.aynu.dao.HpjlDao;
import com.aynu.dto.Audit;
import com.aynu.service.HpjlService;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @author susuper
 * @version 1.0
 * @date 2020/9/11 11:13
 */
@Service
@Slf4j
public class HpjlServiceImpl implements HpjlService {
    @Autowired
    HpjlDao hpjlDao;
    @Value("${upload.fjscdir}")
    String filefjscPath;
    @Override
    public List<Hpjl> selectHpjlByDetail(String department, String number, String name, String start, String  end,
                                         String judgestatus, String roletype) {
        return hpjlDao.selectHpjlByDetail(department,number,name,start,end,judgestatus,roletype);
    }

    @Override
    public List<Hpjl> selectHpjlByDetail2(String department, String number, String name, String start, String  end,
                                          String judgestatus, String roletype, String CGName, String JLName, String DYHJR, String QTHJR, String JIXDBM, String JLRank, String HJTime) {
        return hpjlDao.selectHpjlByDetail2(department,number,name,start,end,judgestatus,roletype,CGName,JLName,DYHJR,QTHJR,JIXDBM,JLRank,HJTime);
    }

    @Override
    public boolean insertHpjl(Hpjl hpjl) {
        return hpjlDao.insertHpjl(hpjl);
    }

    @Override
    public boolean deleteHpjl(List<String> list, String number, String department) {
        return hpjlDao.deleteHpjl(list,number,department);
    }
    @Override
    public int deleteById(List<String> list) {
        deleteFjscByList(list);
        return hpjlDao.deleteById(list);
    }
    @Override
    public Hpjl showHpjl(String department, String number, String id) {
        return hpjlDao.showHpjl(department,number,id);
    }

    @Override
    public boolean updateHpjl(Hpjl hpjl) {
        return hpjlDao.updateHpjl(hpjl);
    }

    @Override
    public boolean updateHpjlByHigh(Audit audit) {
        return hpjlDao.updateHpjlByHigh(audit);
    }


    @Override
    public void deleteFjscByList(List<String> list) {
        List<String> fileNames = hpjlDao.getFileNameByList(list);
        for (String fileName : fileNames) {
            log.info("删除附件=="+fileName);
            if (fileName != null) {
                File file = new File(filefjscPath + fileName);
                file.delete();
            }
        }
    }

    @Override
    public String downloadhpjlZipFjsc(List<String> list) {
        List<String> fileNames = hpjlDao.getFileNameByList(list);
        String zipName = UUID.randomUUID().toString().replaceAll("-", "") + ".zip";

        ZipOutputStream outputStream = null;

        try {
            byte[] buffer = new byte[1024];
            outputStream = new ZipOutputStream(new FileOutputStream(new File(filefjscPath + zipName)));
            for (String fjscname : fileNames) {
                File file = new File(filefjscPath + fjscname);
                FileInputStream fis = new FileInputStream(file);
                outputStream.putNextEntry(new ZipEntry(file.getName()));
                int len = -1;
                while ((len = fis.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, len);
                }
                outputStream.flush();
                outputStream.closeEntry();
                fis.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return zipName;
    }

    @Override
    public HSSFWorkbook exporthpjlExcel(String department, String number, String roletype) {
        List<Hpjl> hpjls = hpjlDao.selectHpjlByDiff(department, number, roletype);
        //设置文件名称  产生excel文件内容

        String[] title = new String[]{"科研单位", "工号", "姓名", "获奖成果名称", "奖励名称", "第一获奖人", "其它获奖人", "奖励下达部门", "证书编号",
                "获奖时间", "奖励等级", "获奖级别", "备注","业绩点", "科研奖金(万元)"};
        String[][] data = new String[hpjls.size()][];
        int length = hpjls.size();
        for (int i = 0; i < length; i++) {
            data[i] = new String[title.length];
            data[i][0] = hpjls.get(i).getDepartment();
            data[i][1] = hpjls.get(i).getNumber();
            data[i][2] = hpjls.get(i).getName();
            data[i][3] = hpjls.get(i).getCgname();
            data[i][4] = hpjls.get(i).getJlname();
            data[i][5] = hpjls.get(i).getDyhjr();
            data[i][6] = hpjls.get(i).getQthjr();
            data[i][7] = hpjls.get(i).getJixdbm();
            data[i][8] = hpjls.get(i).getZsnumber();
            data[i][9] = hpjls.get(i).getHjtime();
            data[i][10] = hpjls.get(i).getJlrank();
            data[i][11] = hpjls.get(i).getHjrank();

            data[i][12] = hpjls.get(i).getRemark();
            data[i][13] = hpjls.get(i).getPoints();
            data[i][14] = hpjls.get(i).getKyjl();
        }
        HSSFWorkbook wb = ExcelUtils.getHSSFWorkbook("获批奖励", title, data, null);
        return wb;
    }

    @Override
    public String getExcelJson(String department, String number,String name, String start, String end, String judgestatus, List<String> params) {
        List<Map<Object, Object>> datamaps = hpjlDao.getExcelJson(department, number, name,start, end, judgestatus, params);
        String[] title = new String[params.size()];
        Map<String,String> originMap = new HashMap<>();
        originMap.put("number","工号");
        originMap.put("name","姓名");
        originMap.put("department","院系");
        originMap.put("cgname","获奖成果名称");
        originMap.put("jlname","奖励名称");
        originMap.put("dyhjr","第一获奖人");
        originMap.put("qthjr","其它获奖人");
        originMap.put("jixdbm","奖励下达部门");
        originMap.put("zsnumber","证书编号");
        originMap.put("hjtime","获奖时间");
        originMap.put("jlrank","奖励等级");
        originMap.put("hjrank","获奖级别");
        originMap.put("points","业绩点");
        originMap.put("kyjl","科研奖励(万)");
        originMap.put("remark","备注");
        originMap.put("systime","申请时间");
        for (int i = 0; i < params.size(); i++) {
            String s = originMap.get(params.get(i));
            title[i] = s;
        }

        String[][] data = new String[datamaps.size()][];
        for (int i = 0; i < datamaps.size(); i++  ) {
            data[i] = new String[title.length];
            for (int count=0;count<params.size();count++) {
                Map<Object,Object> objectMap =  datamaps.get(i);
                String temp = String.valueOf( objectMap.get(params.get(count)));
                data[i][count] = temp;
            }
        }


        HSSFWorkbook wb = ExcelUtils.getHSSFWorkbook("获批奖励", title, data, null);
        String fileName =null;
        try {
            fileName = filefjscPath+String.valueOf(UUID.randomUUID()).replace("-","")+".csv";
            wb.write(new File(fileName));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                wb.close();
                title = null;
                data = null;
                originMap = null;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return fileName;
    }
}
