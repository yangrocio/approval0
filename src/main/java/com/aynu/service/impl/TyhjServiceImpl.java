package com.aynu.service.impl;

import com.aynu.Utils.ExcelUtils;
import com.aynu.bean.Tyhj;
import com.aynu.dao.TyhjDao;
import com.aynu.dto.Audit;
import com.aynu.service.TyhjService;
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
public class TyhjServiceImpl implements TyhjService {
    @Autowired
    TyhjDao tyhjDao;
    @Value("${upload.fjscdir}")
    String filefjscPath;
    @Override
    public List<Tyhj> selectTyhjByDetail(String department, String number, String name, String start, String  end,
                                         String judgestatus, String roletype) {
        return tyhjDao.selectTyhjByDetail(department,number,name,start,end,judgestatus,roletype);
    }
    @Override
    public List<Tyhj> selectTyhjByDetail2(String department, String number, String name, String start, String  end,
                                          String judgestatus, String roletype, String Awardee, String Entryname, String SportsLV, String WinTime) {
        return tyhjDao.selectTyhjByDetail2(department,number,name,start,end,judgestatus,roletype,Awardee,Entryname,SportsLV,WinTime);
    }
    @Override
    public boolean insertTyhj(Tyhj tyhj) {
        return tyhjDao.insertTyhj(tyhj);
    }

    @Override
    public boolean deleteTyhj(List<String> list, String number, String department) {
        return tyhjDao.deleteTyhj(list,number,department);
    }

    @Override
    public int deleteById(List<String> list) {
        deleteFjscByList(list);
        return tyhjDao.deleteById(list);
    }

    @Override
    public Tyhj showTyhj(String department, String number, String id) {
        return tyhjDao.showTyhj(department,number,id);
    }

    @Override
    public boolean updateTyhj(Tyhj tyhj) {
        return tyhjDao.updateTyhj(tyhj);
    }

    @Override
    public boolean updateTyhjByHigh(Audit audit) {
        return tyhjDao.updateTyhjByHigh(audit);
    }

    @Override
    public void deleteFjscByList(List<String> list) {
        List<String> fileNames = tyhjDao.getFileNameByList(list);
        for (String fileName : fileNames) {
            log.info("删除附件=="+fileName);
            if (fileName != null) {
                File file = new File(filefjscPath + fileName);
                file.delete();
            }
        }
    }

    @Override
    public String downloadtyhjZipFjsc(List<String> list) {
        List<String> fileNames = tyhjDao.getFileNameByList(list);
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
    public HSSFWorkbook exporttyhjExcel(String department, String number, String roletype) {
        List<Tyhj> tyhjs = tyhjDao.selectTyhjByDiff(department, number, roletype);
        //设置文件名称  产生excel文件内容

        String[] title = new String[]{"科研单位", "工号", "姓名","获奖人", "体育项目名", "获奖时间", "证书编号",
                "颁奖部门", "业绩点", "奖励金(万元)"};
        String[][] data = new String[tyhjs.size()][];
        int length = tyhjs.size();
        for (int i = 0; i < length; i++) {
            data[i] = new String[title.length];
            data[i][0] = tyhjs.get(i).getDepartment();
            data[i][1] = tyhjs.get(i).getNumber();
            data[i][2] = tyhjs.get(i).getName();
            data[i][3] = tyhjs.get(i).getAwardee();
            data[i][4] = tyhjs.get(i).getEntryname();
            data[i][5] = tyhjs.get(i).getWintime();
            data[i][6] = tyhjs.get(i).getCertificateid();
            data[i][7] = tyhjs.get(i).getBjbm();
            data[i][8] = tyhjs.get(i).getPoints();
            data[i][9] = tyhjs.get(i).getReward();
        }
        HSSFWorkbook wb = ExcelUtils.getHSSFWorkbook("体育获奖", title, data, null);
        return wb;
    }

    @Override
    public String getExcelJson(String department, String number,String name, String start, String end, String judgestatus, List<String> params) {
        List<Map<Object, Object>> datamaps = tyhjDao.getExcelJson(department, number,name, start, end, judgestatus, params);
        String[] title = new String[params.size()];
        Map<String,String> originMap = new HashMap<>();
        originMap.put("number","工号");
        originMap.put("name","姓名");
        originMap.put("department","院系");
        originMap.put("awardee","获奖人");
        originMap.put("entryname","体育项目名");
        originMap.put("wintime","获奖时间");
        originMap.put("certificateid","证书编号");
        originMap.put("sportslv","运动会级别");
        originMap.put("ranking","获奖名次");
        originMap.put("bjbm","颁奖部门");
        originMap.put("points","业绩点");
        originMap.put("reward","科研奖励(万元)");
        originMap.put("fq","备注");
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


        HSSFWorkbook wb = ExcelUtils.getHSSFWorkbook("体育获奖", title, data, null);
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
