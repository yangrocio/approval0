package com.aynu.service.impl;

import com.aynu.Utils.ExcelUtils;
import com.aynu.bean.Jxys;
import com.aynu.dao.JxysDao;
import com.aynu.dto.Audit;
import com.aynu.service.JxysService;
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
 * @Date 2020/11/8 21:16
 * @description:
 */
@Service
@Slf4j
public class JxysServiceImpl implements JxysService {
    @Autowired
    JxysDao jxysDao;
    @Value("${upload.fjscdir}")
    String filefjscPath;

    @Override
    public List<Jxys> selectJxysByDetail(String department, String number,  String name, String start, String end, String judgestatus, String roletype) {
        return jxysDao.selectJxysByDetail(department,number,name,start,end,judgestatus,roletype);
    }

    @Override
    public List<Jxys> selectJxysByDetail2(String department, String number,  String name, String start, String end, String judgestatus, String roletype, String xmname, String hoster, String cyry, String xdbm, String xmly, String rank, String proproperty, String time) {
        return jxysDao.selectJxysByDetail2(department,number,name,start,end,judgestatus,roletype,xmname,hoster,cyry,xdbm,xmly,rank,proproperty,time);
    }

    @Override
    public boolean insertJxys(Jxys jxys) {
        return jxysDao.insertJxys(jxys);
    }

    @Override
    public boolean deleteJxys(List<String> list, String number, String department) {
        return jxysDao.deleteJxys(list,number,department);
    }

    @Override
    public int deleteById(List<String> list) {
        deleteFjscByList(list);
        return jxysDao.deleteById(list);
    }

    @Override
    public Jxys showJxys(String department, String number, String id) {
        return jxysDao.showJxys(department,number,id);

    }

    @Override
    public boolean updateJxys(Jxys jxys) {
        return jxysDao.updateJxys(jxys);
    }

    @Override
    public boolean updateJxysByHigh(Audit audit) {
        return jxysDao.updateJxysByHigh(audit);
    }

    @Override
    public void deleteFjscByList(List<String> list) {
        List<String> fileNames = jxysDao.getFileNameByList(list);
        for (String fileName : fileNames) {
            log.info("删除附件=="+fileName);
            if (fileName != null) {
                File file = new File(filefjscPath + fileName);
                file.delete();
            }
        }
    }

    @Override
    public String downloadcgZipFjsc(List<String> list) {
        List<String> fileNames = jxysDao.getFileNameByList(list);
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
    public HSSFWorkbook exportcgExcel(String department, String number, String roletype) {
        List<Jxys> jxys = jxysDao.selectJxysByDiff(department, number, roletype);
        //设置文件名称  产生excel文件内容

        String[] title = new String[]{"科研单位", "工号", "姓名", "名称", "结项编号", "项目来源", "项目级别", "项目类型",
                "项目属性", "主持人", "项目下达部门", "项目参与人员", "科研奖金", "结项验收时间", "项目状态"
                , "业绩点", "备注"};
        String[][] data = new String[jxys.size()][];
        int length = jxys.size();
        for (int i = 0; i < length; i++) {
            data[i] = new String[title.length];
//            data[i][0] = cgs.get(i).getDepartment();
//            data[i][1] = cgs.get(i).getNumber();
//            data[i][2] = cgs.get(i).getName();
//            data[i][3] = cgs.get(i).getCgname();
//            data[i][4] = cgs.get(i).getCgtype();
//            data[i][5] = cgs.get(i).getDyzz();
//            data[i][6] = cgs.get(i).getQtzz();
//            data[i][7] = cgs.get(i).getCnbm();
//            data[i][8] = cgs.get(i).getCgdz();
//            data[i][9] = cgs.get(i).getTime();
//            data[i][10] = cgs.get(i).getPoints();
//            data[i][11] = cgs.get(i).getReward();
        }
        HSSFWorkbook wb = ExcelUtils.getHSSFWorkbook("结项验收", title, data, null);
        return wb;
    }

    @Override
    public String getExcelJson(String department, String number,String name, String start, String end, String judgestatus, List<String> params) {
        List<Map<Object, Object>> datamaps = jxysDao.getExcelJson(department, number,name, start, end, judgestatus, params);
        String[] title = new String[params.size()];
        Map<String,String> originMap = new HashMap<>();
        originMap.put("number","工号");
        originMap.put("name","姓名");
        originMap.put("department","院系");

        originMap.put("xmname","名称");
        originMap.put("pronumber","项目编号");
        originMap.put("xmly","项目来源");
        originMap.put("rank","项目级别");
        originMap.put("proproperty","项目属性");
        originMap.put("hoster","主持人");
        originMap.put("xdbm","项目下达部门");
        originMap.put("cyry","项目参与人员");
        originMap.put("time","结项验收时间");
        originMap.put("remark","备注");
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


        HSSFWorkbook wb = ExcelUtils.getHSSFWorkbook("结项验收", title, data, null);
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
