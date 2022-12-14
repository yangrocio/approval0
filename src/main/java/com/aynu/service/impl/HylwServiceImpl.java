package com.aynu.service.impl;

import com.aynu.Utils.ExcelUtils;
import com.aynu.bean.Hylw;
import com.aynu.dao.HylwDao;
import com.aynu.dto.Audit;
import com.aynu.service.HylwService;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @author susuper
 * @version 1.0
 * @date 2020/9/11 11:13
 */
@Service
@Slf4j
public class HylwServiceImpl implements HylwService {
    @Autowired
    HylwDao hylwDao;


    @Value("${upload.fjscdir}")
    String filefjscPath;

    @Override
    public List<Hylw> selectHylwByDetail(String department, String number, String name,  String start, String end, String judgestatus, String roletype) {
        return hylwDao.selectHylwByDetail(department, number,name, start, end, judgestatus, roletype);
    }

    @Override
    public List<Hylw> selectHylwByDetail2(String department, String number, String name,  String start, String end,
                                         String judgestatus, String roletype,String tm,String dyzz,String qtzz,String hymc,
                                         String hysj,String hyjb,String jllb) {
        return hylwDao.selectHylwByDetail2(department, number,name, start, end, judgestatus, roletype,tm,dyzz,qtzz,hymc,hysj,hyjb,jllb);
    }


    @Override
    public boolean insertHylw(Hylw hylw) {
        return hylwDao.insertHylw(hylw);
    }

    @Override
    public boolean deleteHylw(List<String> list, String number, String department) {
        return hylwDao.deleteHylw(list, number, department);
    }

    @Override
    public int deleteById(List<String> list) {
        deleteFjscByList(list);
        return hylwDao.deleteById(list);
    }

    @Override
    public boolean updateHylw(Hylw hylw) {
        return hylwDao.updateHylw(hylw);
    }

    @Override
    public boolean updateHylwByHigh(Audit audit) {
        return hylwDao.updateHylwByHigh(audit);
    }

    @Override
    public Hylw showHylw(String department, String number, String id) {
        return hylwDao.showHylw(department, number, id);
    }

    @Override
    public void deleteFjscByList(List<String> list) {
        List<String> fileNames = hylwDao.getFileNameByList(list);
        for (String fileName : fileNames) {
            log.info("????????????=="+fileName);
            if (fileName != null) {
                File file = new File(filefjscPath + fileName);
                file.delete();
            }
        }
    }

    //????????? ?????? ?????? ?????? ????????? zip  ?????????controller   controller????????????  ????????????
    public String downloadhylwZipFjsc(List<String> list) {
        List<String> fileNames = hylwDao.getFileNameByList(list);
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
    public HSSFWorkbook exporthylwExcel(String department, String number, String roletype) {
        List<Hylw> hylws = hylwDao.selectHylwByDiff(department, number, roletype);
        //??????????????????  ??????excel????????????

        String[] title = new String[]{"????????????", "??????", "??????", "??????", "????????????",
                "????????????", "?????????", "????????????", "????????????",
                "????????????", "????????????", "????????????", "?????????", "????????????(??????)",
                "????????????", "??????(???)"};
        String[][] data = new String[hylws.size()][];
        int length = hylws.size();
        for (int i = 0; i < length; i++) {
            data[i] = new String[title.length];
            data[i][0] = hylws.get(i).getDepartment();
            data[i][1] = hylws.get(i).getNumber();
            data[i][2] = hylws.get(i).getName();
            data[i][3] = hylws.get(i).getTimu();
            data[i][4] = hylws.get(i).getDyzz();
            data[i][5] = hylws.get(i).getQtzz();
            data[i][6] = hylws.get(i).getPress();
            data[i][7] = hylws.get(i).getHyname();
            data[i][8] = hylws.get(i).getWheres();
            data[i][9] = hylws.get(i).getTimes();
            data[i][10] = hylws.get(i).getRank();
            data[i][11] = hylws.get(i).getTypes();
            data[i][12] = hylws.get(i).getPoints();
            data[i][13] = hylws.get(i).getReward();
            data[i][14] = hylws.get(i).getShoulu();
            data[i][15] = hylws.get(i).getZishu();
        }
        HSSFWorkbook wb = ExcelUtils.getHSSFWorkbook("????????????", title, data, null);
        return wb;
    }


    @Override
    public String getExcelJson(String department, String number,String name,  String start, String end, String judgestatus, List<String> params) {
        List<Map<Object, Object>> datamaps = hylwDao.getExcelJson(department, number,name, start, end, judgestatus, params);
        String[] title = new String[params.size()];
        Map<String,String> originMap = new HashMap<>();
        originMap.put("number","??????");
        originMap.put("name","??????");
        originMap.put("department","??????");
        originMap.put("timu","??????");
        originMap.put("dyzz","????????????");
        originMap.put("qtzz","????????????");
        originMap.put("press","?????????");
        originMap.put("hyname","????????????");
        originMap.put("wheres","????????????");
        originMap.put("times","????????????");
        originMap.put("rank","????????????");
        originMap.put("types","????????????");
        originMap.put("points","?????????");
        originMap.put("reward","????????????(??????)");
        originMap.put("zishu","??????(???)");
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
