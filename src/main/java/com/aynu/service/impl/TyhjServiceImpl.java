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
            log.info("????????????=="+fileName);
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
        //??????????????????  ??????excel????????????

        String[] title = new String[]{"????????????", "??????", "??????","?????????", "???????????????", "????????????", "????????????",
                "????????????", "?????????", "?????????(??????)"};
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
        HSSFWorkbook wb = ExcelUtils.getHSSFWorkbook("????????????", title, data, null);
        return wb;
    }

    @Override
    public String getExcelJson(String department, String number,String name, String start, String end, String judgestatus, List<String> params) {
        List<Map<Object, Object>> datamaps = tyhjDao.getExcelJson(department, number,name, start, end, judgestatus, params);
        String[] title = new String[params.size()];
        Map<String,String> originMap = new HashMap<>();
        originMap.put("number","??????");
        originMap.put("name","??????");
        originMap.put("department","??????");
        originMap.put("awardee","?????????");
        originMap.put("entryname","???????????????");
        originMap.put("wintime","????????????");
        originMap.put("certificateid","????????????");
        originMap.put("sportslv","???????????????");
        originMap.put("ranking","????????????");
        originMap.put("bjbm","????????????");
        originMap.put("points","?????????");
        originMap.put("reward","????????????(??????)");
        originMap.put("fq","??????");
        originMap.put("systime","????????????");
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


        HSSFWorkbook wb = ExcelUtils.getHSSFWorkbook("????????????", title, data, null);
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
