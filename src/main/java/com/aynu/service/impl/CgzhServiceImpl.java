package com.aynu.service.impl;

import com.aynu.Utils.ExcelUtils;
import com.aynu.bean.Cgzh;
import com.aynu.dao.CgzhDao;
import com.aynu.dto.Audit;
import com.aynu.service.CgzhService;
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
 * @Date 2020/11/12 21:12
 * @description:
 */
@Service
@Slf4j
public class CgzhServiceImpl implements CgzhService {
    @Autowired
    CgzhDao cgzhDao;
    @Value("${upload.fjscdir}")
    String filefjscPath;
    @Override
    public List<Cgzh> selectCgzhByDetail(String department, String number, String name, String start, String end, String judgestatus, String roletype) {
        return cgzhDao.selectCgzhByDetail(department,number,name,start,end,judgestatus,roletype);

    }

    @Override
    public List<Cgzh> selectCgzhByDetail2(String department, String number, String name, String start, String end, String judgestatus, String roletype, String cgname, String dyzz, String qtzz, String zhdw, String zrlx, String zhtime) {
        return cgzhDao.selectCgzhByDetail2(department,number,name,start,end,judgestatus,roletype,cgname,dyzz,qtzz,zhdw,zrlx,zhtime);

    }

    @Override
    public boolean insertCgzh(Cgzh cgzh) {
        return cgzhDao.insertCgzh(cgzh);

    }

    @Override
    public boolean deleteCgzh(List<String> list, String number, String department) {
        return cgzhDao.deleteCgzh(list,number,department);
    }

    @Override
    public int deleteById(List<String> list) {
        deleteFjscByList(list);
        return cgzhDao.deleteById(list);
    }
    @Override
    public Cgzh showCgzh(String department, String number, String id) {
        return cgzhDao.showCgzh(department,number,id);

    }

    @Override
    public boolean updateCgzh(Cgzh cgzh) {
        return cgzhDao.updateCgzh(cgzh);

    }

    @Override
    public boolean updateCgzhByHigh(Audit audit) {
        return cgzhDao.updateCgzhByHigh(audit);
    }

    @Override
    public void deleteFjscByList(List<String> list) {
        List<String> fileNames = cgzhDao.getFileNameByList(list);
        for (String fileName : fileNames) {
            log.info("????????????=="+fileName);
            if (fileName != null) {
                File file = new File(filefjscPath + fileName);
                file.delete();
            }
        }
    }

    @Override
    public String downloadcgzhZipFjsc(List<String> list) {
        List<String> fileNames = cgzhDao.getFileNameByList(list);
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
    public HSSFWorkbook exportcgzhExcel(String department, String number, String roletype) {
        List<Cgzh> cgzhList = cgzhDao.selectCgzhByDiff(department, number, roletype);
        //??????????????????  ??????excel????????????

        String[] title = new String[]{"????????????", "??????", "??????", "??????????????????", "????????????","??????????????????(???)", "???????????????", "???????????????", "????????????",
                "????????????", "?????????", "????????????(???)"};
        String[][] data = new String[cgzhList.size()][];
        int length = cgzhList.size();
        for (int i = 0; i < length; i++) {
            data[i] = new String[title.length];
            data[i][0] = cgzhList.get(i).getDepartment();
            data[i][1] = cgzhList.get(i).getNumber();
            data[i][2] = cgzhList.get(i).getName();
            data[i][3] = cgzhList.get(i).getCgname();
            data[i][4] = cgzhList.get(i).getZrlx();
            data[i][5] = cgzhList.get(i).getZrdzjf();
            data[i][6] = cgzhList.get(i).getDyzz();
            data[i][7] = cgzhList.get(i).getQtzz();
            data[i][8] = cgzhList.get(i).getZhtime();
            data[i][9] = cgzhList.get(i).getPoints();
            data[i][10] = cgzhList.get(i).getReward();
        }
        HSSFWorkbook wb = ExcelUtils.getHSSFWorkbook("????????????", title, data, null);
        return wb;
    }

    @Override
    public String getExcelJson(String department, String number, String name,String start, String end, String judgestatus, List<String> params) {
        List<Map<Object, Object>> datamaps = cgzhDao.getExcelJson(department, number,name, start, end, judgestatus, params);
        String[] title = new String[params.size()];
        Map<String,String> originMap = new HashMap<>();
        originMap.put("number","??????");
        originMap.put("name","??????");
        originMap.put("department","??????");
        originMap.put("cgname","??????????????????");
        originMap.put("zrlx","????????????");
        originMap.put("zrdzjf","??????????????????(???)");
        originMap.put("dyzz","???????????????");
        originMap.put("qtzz","???????????????");
        originMap.put("zhdw","????????????");
        originMap.put("zhtime","????????????");
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
