package com.aynu.service.impl;

import com.aynu.Utils.ExcelUtils;
import com.aynu.bean.Xsch;
import com.aynu.dao.XschDao;
import com.aynu.dto.Audit;
import com.aynu.service.XschService;
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
public class XschServiceImpl implements XschService {
    @Autowired
    XschDao xschDao;
    @Value("${upload.fjscdir}")
    String filefjscPath;
    @Override
    public List<Xsch> selectXschByDetail(String department, String number, String name, String start, String  end, String judgestatus, String roletype) {
        return xschDao.selectXschByDetail(department,number,name,start,end,judgestatus,roletype);
    }

    @Override
    public List<Xsch> selectXschByDetail2(String department, String number, String name, String start, String  end, String judgestatus, String roletype, String XMName, String Title, String Rank, String XFBM, String Time) {
        return xschDao.selectXschByDetail2(department,number,name,start,end,judgestatus,roletype,XMName,Title,Rank,XFBM,Time);
    }

    @Override
    public boolean insertXsch(Xsch xsch) {
        return xschDao.insertXsch(xsch);
    }

    @Override
    public boolean deleteXsch(List<String> list, String number, String department) {
        return xschDao.deleteXsch(list,number,department);
    }

    @Override
    public int deleteById(List<String> list) {
        deleteFjscByList(list);
        return xschDao.deleteById(list);
    }

    @Override
    public Xsch showXsch(String department, String number, String id) {
        return xschDao.showXsch(department,number,id);
    }

    @Override
    public boolean updateXsch(Xsch xsch) {
        return xschDao.updateXsch(xsch);
    }

    @Override
    public boolean updateXschByHigh(Audit audit) {
        return xschDao.updateXschByHigh(audit);
    }

    @Override
    public void deleteFjscByList(List<String> list) {
        List<String> fileNames = xschDao.getFileNameByList(list);
        for (String fileName : fileNames) {
            log.info("删除附件=="+fileName);
            if (fileName != null) {
                File file = new File(filefjscPath + fileName);
                file.delete();
            }
        }
    }

    @Override
    public String downloadxschZipFjsc(List<String> list) {
        List<String> fileNames = xschDao.getFileNameByList(list);
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
    public HSSFWorkbook exportxschExcel(String department, String number, String roletype) {
        List<Xsch> xsches = xschDao.selectXschByDiff(department, number, roletype);

        String[] title = new String[]{"科研单位", "工号", "姓名", "称号名称", "称号等级", "证书编号", "获得时间", "下发部门", "开发完成时间",};
        String[][] data = new String[xsches.size()][];
        int length = xsches.size();
        for (int i = 0; i < length; i++) {
            data[i] = new String[title.length];
            data[i][0] = xsches.get(i).getDepartment();
            data[i][1] = xsches.get(i).getNumber();
            data[i][2] = xsches.get(i).getXmname();
            data[i][4] = xsches.get(i).getTitle();
            data[i][5] = xsches.get(i).getRank();
            data[i][6] = xsches.get(i).getZsnumber();
            data[i][7] = xsches.get(i).getTime();
            data[i][8] = xsches.get(i).getXfbm();

        }
        HSSFWorkbook wb = ExcelUtils.getHSSFWorkbook("学术称号", title, data, null);
        return wb;
    }
    @Override
    public String getExcelJson(String department, String number, String name,String start, String end, String judgestatus, List<String> params) {
        List<Map<Object, Object>> datamaps = xschDao.getExcelJson(department, number,name, start, end, judgestatus, params);
        String[] title = new String[params.size()];
        Map<String,String> originMap = new HashMap<>();
        originMap.put("number","工号");
        originMap.put("name","姓名");
        originMap.put("department","院系");
        originMap.put("xmname","获奖人姓名");
        originMap.put("title","称号名称");
        originMap.put("rank","称号等级");
        originMap.put("zsnumber","证书编号");
        originMap.put("time","获得时间");
        originMap.put("xfbm","下发部门");
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


        HSSFWorkbook wb = ExcelUtils.getHSSFWorkbook("学术称号", title, data, null);
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
