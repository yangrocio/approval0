package com.aynu.service.impl;

import com.aynu.Utils.ExcelUtils;
import com.aynu.bean.Rjzz;
import com.aynu.dao.RjzzDao;
import com.aynu.dto.Audit;
import com.aynu.service.RjzzService;
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
public class RjzzServiceImpl implements RjzzService {
    @Autowired
    RjzzDao rjzzDao;
    @Value("${upload.fjscdir}")
    String filefjscPath;
    @Override
    public List<Rjzz> selectRjzzByDetail(String department, String number, String name, String start, String  end, String judgestatus, String roletype) {
        return rjzzDao.selectRjzzByDetail(department,number,name,start,end,judgestatus,roletype);
    }

    @Override
    public List<Rjzz> selectRjzzByDetail2(String department, String number, String name, String start, String  end, String judgestatus, String roletype, String XMName, String DYZZ, String QTZZ, String DJNumber, String DJTime) {
        return rjzzDao.selectRjzzByDetail2(department,number,name,start,end,judgestatus,roletype,XMName,DYZZ,QTZZ,DJNumber,DJTime);
    }


    @Override
    public boolean insertRjzz(Rjzz rjzz) {
        return rjzzDao.insertRjzz(rjzz);
    }

    @Override
    public boolean deleteRjzz(List<String> list, String number, String department) {
        return rjzzDao.deleteRjzz(list,number,department);
    }

    @Override
    public int deleteById(List<String> list) {
        deleteFjscByList(list);
        return rjzzDao.deleteById(list);
    }

    @Override
    public Rjzz showRjzz(String department, String number, String id) {
        return rjzzDao.showRjzz(department,number,id);
    }

    @Override
    public boolean updateRjzz(Rjzz rjzz) {
        return rjzzDao.updateRjzz(rjzz);
    }

    @Override
    public boolean updateRjzzByHigh(Audit audit) {
        return rjzzDao.updateRjzzByHigh(audit);
    }

    @Override
    public void deleteFjscByList(List<String> list) {
        List<String> fileNames = rjzzDao.getFileNameByList(list);
        for (String fileName : fileNames) {
            log.info("????????????=="+fileName);
            if (fileName != null) {
                File file = new File(filefjscPath + fileName);
                file.delete();
            }
        }
    }

    @Override
    public String downloadrjzzZipFjsc(List<String> list) {
        List<String> fileNames = rjzzDao.getFileNameByList(list);
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
    public HSSFWorkbook exportrjzzExcel(String department, String number, String roletype) {
        List<Rjzz> rjzzes = rjzzDao.selectRjzzByDiff(department, number, roletype);

        String[] title = new String[]{"????????????", "??????", "??????", "????????????", "????????????", "????????????", "?????????", "????????????", "??????????????????",
                "????????????", "??????????????????", "????????????", "??????????????????", "?????????"};
        String[][] data = new String[rjzzes.size()][];
        int length = rjzzes.size();
        for (int i = 0; i < length; i++) {
            data[i] = new String[title.length];
            data[i][0] = rjzzes.get(i).getDepartment();
            data[i][1] = rjzzes.get(i).getNumber();
            data[i][2] = rjzzes.get(i).getName();
            data[i][3] = rjzzes.get(i).getXmname();
            data[i][4] = rjzzes.get(i).getDyzz();
            data[i][5] = rjzzes.get(i).getQtzz();
            data[i][6] = rjzzes.get(i).getDjnumber();
            data[i][7] = rjzzes.get(i).getSydw();
            data[i][8] = rjzzes.get(i).getFinishtime();
            data[i][9] = rjzzes.get(i).getDjtime();
            data[i][10] = rjzzes.get(i).getSftime();
            data[i][11] = rjzzes.get(i).getBqtype();

            data[i][12] = rjzzes.get(i).getSyfs();
            data[i][13] = rjzzes.get(i).getPoints();
        }
        HSSFWorkbook wb = ExcelUtils.getHSSFWorkbook("????????????", title, data, null);
        return wb;
    }

    @Override
    public String getExcelJson(String department, String number,String name, String start, String end, String judgestatus, List<String> params) {
        List<Map<Object, Object>> datamaps = rjzzDao.getExcelJson(department, number, name,start, end, judgestatus, params);
        String[] title = new String[params.size()];
        Map<String,String> originMap = new HashMap<>();
        originMap.put("number","??????");
        originMap.put("name","??????");
        originMap.put("department","??????");
        originMap.put("xmname","??????");
        originMap.put("dyzz","???????????????");
        originMap.put("qtzz","????????????");
        originMap.put("djnumber","?????????");
        originMap.put("sydw","????????????");
        originMap.put("finishtime","??????????????????");
        originMap.put("djtime","????????????????????????");
        originMap.put("bqtype","????????????");
        originMap.put("syfs","??????????????????");
        originMap.put("points","?????????");
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
