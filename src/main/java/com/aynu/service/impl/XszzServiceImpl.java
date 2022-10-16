package com.aynu.service.impl;

import com.aynu.Utils.ExcelUtils;
import com.aynu.bean.Xszz;
import com.aynu.dao.XszzDao;
import com.aynu.dto.Audit;
import com.aynu.service.XszzService;
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
public class XszzServiceImpl implements XszzService {
    @Autowired
    XszzDao xszzDao;
    @Value("${upload.fjscdir}")
    String filefjscPath;
    @Override
    public List<Xszz> selectXszzByDetail(String department, String number,  String name, String start, String  end, String judgestatus,String roletype) {
        return xszzDao.selectXszzByDetail(department,number,name,start,end,judgestatus,roletype);
    }

    @Override
    public List<Xszz> selectXszzByDetail2(String department, String number,  String name, String start, String  end, String judgestatus,String roletype,String timu,String dyzz,String qtzz,String cbsType,String zzType,String time) {
        return xszzDao.selectXszzByDetail2(department,number,name,start,end,judgestatus,roletype,timu,dyzz,qtzz,cbsType,zzType,time);
    }

    @Override
    public boolean insertXszz(Xszz xszz) {
        return xszzDao.insertXszz(xszz);
    }

    @Override
    public boolean deleteXszz(List<String> list, String number, String department) {
        return xszzDao.deleteXszz(list,number,department);
    }

    @Override
    public int deleteById(List<String> list) {
        deleteFjscByList(list);
        return xszzDao.deleteById(list);
    }

    @Override
    public Xszz showXszz(String department, String number, String id) {
        return xszzDao.showXszz(department,number,id);
    }

    @Override
    public boolean updateXszz(Xszz xszz) {
        return xszzDao.updateXszz(xszz);
    }

    @Override
    public boolean updateXszzByHigh(Audit audit) {
        return xszzDao.updateXszzByHigh(audit);
    }

    @Override
    public void deleteFjscByList(List<String> list) {
        List<String> fileNames = xszzDao.getFileNameByList(list);
        for (String fileName : fileNames) {
            log.info("删除附件=="+fileName);
            if (fileName != null) {
                File file = new File(filefjscPath + fileName);
                file.delete();
            }
        }
    }

    @Override
    public HSSFWorkbook exportxszzExcel(String department, String number, String roletype) {
        List<Xszz> qklws = xszzDao.selectXszzByDiff(department, number, roletype);
        //设置文件名称  产生excel文件内容

        String[] title = new String[]{"科研单位", "工号", "姓名", "题目", "第一作者", "其它作者", "出版社类型", "出版社", "ISBN",
                "CIP核字", "著作类型", "业绩点", "科研奖励(万元)", "出版时间"};
        String[][] data = new String[qklws.size()][];
        int length = qklws.size();
        for (int i = 0; i < length; i++) {
            data[i] = new String[title.length];
            data[i][0] = qklws.get(i).getDepartment();
            data[i][1] = qklws.get(i).getNumber();
            data[i][2] = qklws.get(i).getName();
            data[i][3] = qklws.get(i).getTimu();
            data[i][4] = qklws.get(i).getDyzz();
            data[i][5] = qklws.get(i).getQtzz();
            data[i][6] = qklws.get(i).getPresstype();
            data[i][7] = qklws.get(i).getPress();
            data[i][8] = qklws.get(i).getIsbn();
            data[i][9] = qklws.get(i).getCip();
            data[i][10] = qklws.get(i).getWorktype();
            data[i][11] = qklws.get(i).getPoints();
            data[i][12] = qklws.get(i).getReward();
            data[i][13] = qklws.get(i).getTime();

        }

        HSSFWorkbook wb = ExcelUtils.getHSSFWorkbook("学术著作", title, data, null);
        return wb;
    }

    @Override
    public String downloadxszzZipFjsc(List<String> list) {
        List<String> fileNames = xszzDao.getFileNameByList(list);
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
    public String getExcelJson(String department, String number, String name,String start, String end, String judgestatus, List<String> params) {
        List<Map<Object, Object>> datamaps = xszzDao.getExcelJson(department, number, name,start, end, judgestatus, params);
        String[] title = new String[params.size()];
        Map<String,String> originMap = new HashMap<>();
        originMap.put("number","工号");
        originMap.put("name","姓名");
        originMap.put("department","院系");
        originMap.put("timu","题目");
        originMap.put("dyzz","第一作者");
        originMap.put("qtzz","其它作者");
        originMap.put("isbn","ISBN");
        originMap.put("worktype","出版社类型");
        originMap.put("press","出版社");
        originMap.put("zzlx","著作类型");
        originMap.put("cip","CIP核字");
        originMap.put("time","出版时间");
        originMap.put("time","出版时间");
        originMap.put("fq","备注");

        originMap.put("points","业绩点");
        originMap.put("reward","科研奖励(万元)");
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


        HSSFWorkbook wb = ExcelUtils.getHSSFWorkbook("学术著作", title, data, null);
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
