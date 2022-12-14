package com.aynu.service.impl;

import com.aynu.Utils.ExcelUtils;
import com.aynu.bean.Hpxm;
import com.aynu.dao.HpxmDao;
import com.aynu.dto.Audit;
import com.aynu.service.HpxmService;
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
 * @Author susuper
 * @Date 2020/11/9 8:32
 * @description:
 */
@Service
@Slf4j
public class HpxmServiceImpl implements HpxmService {
    @Autowired
    HpxmDao hpxmDao;
    @Value("${upload.fjscdir}")
    String filefjscPath;
    @Override
    public List<Hpxm> selectHpxmByDetail(String department, String number,  String name, String start, String end, String judgestatus, String roletype) {
        return hpxmDao.selectHpxmByDetail(department,number,name,start,end,judgestatus,roletype);
    }

    @Override
    public List<Hpxm> selectHpxmByDetail2(String department, String number,  String name, String start, String end, String judgestatus, String roletype, String xmname, String hoster, String cyry, String xdbm, String xmsource, String prorank, String proproperty, String lxtime) {
        return hpxmDao.selectHpxmByDetail2(department,number,name,start,end,judgestatus,roletype,xmname,hoster,cyry,xdbm,xmsource,prorank,proproperty,lxtime);
    }

    @Override
    public boolean insertHpxm(Hpxm hpxm) {
        return hpxmDao.insertHpxm(hpxm);

    }

    @Override
    public boolean deleteHpxm(List<String> list, String number, String department) {
        return hpxmDao.deleteHpxm(list,number,department);
    }

    @Override
    public int deleteById(List<String> list) {
        deleteFjscByList(list);
        return hpxmDao.deleteById(list);
    }

    @Override
    public Hpxm showHpxm(String department, String number, String id) {
        return hpxmDao.showHpxm(department,number,id);
    }

    @Override
    public boolean updateHpxm(Hpxm hpxm) {
        return hpxmDao.updateHpxm(hpxm);
    }

    @Override
    public boolean updateHpxmByHigh(Audit audit) {
        return hpxmDao.updateHpxmByHigh(audit);
    }

    @Override
    public void deleteFjscByList(List<String> list) {
        List<String> fileNames = hpxmDao.getFileNameByList(list);
        for (String fileName : fileNames) {
            log.info("????????????=="+fileName);
            if (fileName != null) {
                File file = new File(filefjscPath + fileName);
                file.delete();
            }
        }
    }

    @Override
    public String downloadhpxmZipFjsc(List<String> list) {
        List<String> fileNames = hpxmDao.getFileNameByList(list);
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
    public HSSFWorkbook exporthpxmExcel(String department, String number, String roletype) {
        List<Hpxm> hpxms = hpxmDao.selectHpxmByDiff(department, number, roletype);
        //??????????????????  ??????excel????????????

        String[] title = new String[]{"????????????", "??????", "??????", "?????????", "????????????", "??????????????????(???)", "??????????????????(???)", "??????????????????",
                "????????????", "????????????","?????????","????????????(??????)","?????????","??????????????????","??????","????????????"};
        String[][] data = new String[hpxms.size()][];
        int length = hpxms.size();
        for (int i = 0; i < length; i++) {
            data[i] = new String[title.length];
            data[i][0] = hpxms.get(i).getDepartment();
            data[i][1] = hpxms.get(i).getNumber();
            data[i][2] = hpxms.get(i).getName();
            data[i][3] = hpxms.get(i).getXmname();
            data[i][4] = hpxms.get(i).getPronumber();
            data[i][5] = hpxms.get(i).getWbjf();
            data[i][6] = hpxms.get(i).getPtjf();
            data[i][7] = hpxms.get(i).getXdbm();
            data[i][8] = hpxms.get(i).getXmsource();
            data[i][9] = hpxms.get(i).getProrank();
            data[i][10] = hpxms.get(i).getHoster();
            data[i][11] = hpxms.get(i).getReward();
            data[i][12] = hpxms.get(i).getPoints();
            data[i][13] = hpxms.get(i).getCyry();
            data[i][14] = hpxms.get(i).getRemark();
            data[i][15] = hpxms.get(i).getLxtime();
        }
        HSSFWorkbook wb = ExcelUtils.getHSSFWorkbook("????????????", title, data, null);
        return wb;
    }
    @Override
    public String getExcelJson(String department, String number,String name, String start, String end, String judgestatus, List<String> params) {
        List<Map<Object, Object>> datamaps = hpxmDao.getExcelJson(department, number, name,start, end, judgestatus, params);
        String[] title = new String[params.size()];
        Map<String,String> originMap = new HashMap<>();
        originMap.put("number","??????");
        originMap.put("name","??????");
        originMap.put("department","??????");
        originMap.put("xmname","?????????");
        originMap.put("pronumber","????????????");
        originMap.put("wbjf","??????????????????(???)");
        originMap.put("ptjf","??????????????????(???)");
        originMap.put("xdbm","??????????????????");
        originMap.put("xmsource","????????????");
        originMap.put("prorank","????????????");
        originMap.put("proproperty","????????????");
        originMap.put("hoster","?????????");
        originMap.put("cyry","??????????????????");
        originMap.put("lxtime","????????????");
        originMap.put("remark","??????");
        originMap.put("points","?????????");
        originMap.put("reward","????????????(??????)");
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
