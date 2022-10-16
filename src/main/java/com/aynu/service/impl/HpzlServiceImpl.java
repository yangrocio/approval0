package com.aynu.service.impl;

import com.aynu.Utils.ExcelUtils;
import com.aynu.bean.Hpzl;
import com.aynu.dao.HpzlDao;
import com.aynu.dto.Audit;
import com.aynu.service.HpzlService;
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
 * @Date 2020/9/14 10:21
 * @description:
 */
@Service
@Slf4j
public  class HpzlServiceImpl implements HpzlService {
    @Autowired
    HpzlDao hpzlDao;
    @Value("${upload.fjscdir}")
    String filefjscPath;
    @Override
    public List<Hpzl> selectHpzlByDetail(String department, String number, String name, String start, String  end, String judgestatus, String roletype) {
        return hpzlDao.selectHpzlByDetail(department,number,name,start,end,judgestatus,roletype);
    }

    @Override
    public List<Hpzl> selectHpzlByDetail2(String department, String number, String name, String start, String  end, String judgestatus, String roletype, String XMName, String FMR, String QTFMR, String ZLNumber, String PType, String SYTime) {
        return hpzlDao.selectHpzlByDetail2(department,number,name,start,end,judgestatus,roletype,XMName,FMR,QTFMR,ZLNumber,PType,SYTime);
    }

    @Override
    public boolean insertHpzl(Hpzl xszz) {
        return hpzlDao.insertHpzl(xszz);
    }

    @Override
    public boolean deleteHpzl(List<String> list, String number, String department) {
        return hpzlDao.deleteHpzl(list,number,department);
    }

    @Override
    public int deleteById(List<String> list) {
        deleteFjscByList(list);
        return hpzlDao.deleteById(list);
    }

    @Override
    public Hpzl showHpzl(String department, String number, String id) {
        return hpzlDao.showHpzl(department,number,id);
    }

    @Override
    public boolean updateHpzl(Hpzl hpzl) {
        return hpzlDao.updateHpzl(hpzl);
    }

    @Override
    public boolean updateHpzlByHigh(Audit audit) {
        return hpzlDao.updateHpzlByHigh(audit);
    }

    @Override
    public void deleteFjscByList(List<String> list) {
        List<String> fileNames = hpzlDao.getFileNameByList(list);
        for (String fileName : fileNames) {
            log.info("删除附件=="+fileName);
            if (fileName != null) {
                File file = new File(filefjscPath + fileName);
                file.delete();
            }
        }
    }

    @Override
    public String downloadhpzlZipFjsc(List<String> list) {
        List<String> fileNames = hpzlDao.getFileNameByList(list);
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
    public HSSFWorkbook exporthpzlExcel(String department, String number, String roletype) {
        List<Hpzl> hpzls = hpzlDao.selectHpzlByDiff(department, number, roletype);
        //设置文件名称  产生excel文件内容

        String[] title = new String[]{"科研单位", "工号", "姓名", "项目名称", "专利权人", "第一发明人", "其它发明人", "颁发部门", "专利号",
                "申请时间", "授权时间", "专利类别", "专利状态","科研奖金(万元)", "业绩点"};
        String[][] data = new String[hpzls.size()][];
        int length = hpzls.size();
        for (int i = 0; i < length; i++) {
            data[i] = new String[title.length];
            data[i][0] = hpzls.get(i).getDepartment();
            data[i][1] = hpzls.get(i).getNumber();
            data[i][2] = hpzls.get(i).getName();
            data[i][3] = hpzls.get(i).getXmname();
            data[i][4] = hpzls.get(i).getZlqr();
            data[i][5] = hpzls.get(i).getFmr();
            data[i][6] = hpzls.get(i).getQtfmr();
            data[i][7] = hpzls.get(i).getBfbm();
            data[i][8] = hpzls.get(i).getZlnumber();
            data[i][9] = hpzls.get(i).getSqtime();
            data[i][10] = hpzls.get(i).getSytime();
            data[i][11] = hpzls.get(i).getPtype();

            data[i][12] = hpzls.get(i).getPstatus();
            data[i][13] = hpzls.get(i).getReward();
            data[i][14] = hpzls.get(i).getPoints();
        }
        HSSFWorkbook wb = ExcelUtils.getHSSFWorkbook("获批专利", title, data, null);
        return wb;
    }

    @Override
    public String getExcelJson(String department, String number,String name, String start, String end, String judgestatus, List<String> params) {
        List<Map<Object, Object>> datamaps = hpzlDao.getExcelJson(department, number, name,start, end, judgestatus, params);
        String[] title = new String[params.size()];
        Map<String,String> originMap = new HashMap<>();
        originMap.put("number","工号");
        originMap.put("name","姓名");
        originMap.put("department","院系");
        originMap.put("xmname","名称");
        originMap.put("zlqr","专利权人");
        originMap.put("fmr","第一发明人");
        originMap.put("qtfmr","其它发明人");
        originMap.put("bfbm","颁发部门");
        originMap.put("zlnumber","专利号");
        originMap.put("sqtime","申请时间");
        originMap.put("sytime","授权时间");
        originMap.put("ptype","专利类别");
        originMap.put("pstatus","专利状态");
        originMap.put("points","业绩点");
        originMap.put("reward","科研奖励(万元)");
        originMap.put("systime","申请时间");
        originMap.put("fq","备注");
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


        HSSFWorkbook wb = ExcelUtils.getHSSFWorkbook("获批专利", title, data, null);
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
