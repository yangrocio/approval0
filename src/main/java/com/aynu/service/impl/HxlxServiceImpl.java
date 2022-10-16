package com.aynu.service.impl;

import com.aynu.Utils.ExcelUtils;
import com.aynu.bean.Hxlx;
import com.aynu.dao.HxlxDao;
import com.aynu.dto.Audit;
import com.aynu.service.HxlxService;
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
 * @Date 2020/11/9 11:14
 * @description:
 */
@Service
@Slf4j
public class HxlxServiceImpl implements HxlxService {
    @Autowired
    HxlxDao hxlxDao;
    @Value("${upload.fjscdir}")
    String filefjscPath;

    @Override
    public List<Hxlx> selectHxlxByDetail(String department, String number, String name, String start, String end, String judgestatus, String roletype) {
        return hxlxDao.selectHxlxByDetail(department,number,name,start,end,judgestatus,roletype);

    }

    @Override
    public List<Hxlx> selectHxlxByDetail2(String department, String number, String name, String start, String end, String judgestatus, String roletype, String XMName, String Principal, String AUnit, String ZJE, String ContractNumber, String QDTime) {
        return hxlxDao.selectHxlxByDetail2(department,number,name,start,end,judgestatus,roletype,XMName,Principal,AUnit,ZJE,ContractNumber,QDTime);

    }

    @Override
    public boolean insertHxlx(Hxlx hxlx) {
        return hxlxDao.insertHxlx(hxlx);
    }

    @Override
    public boolean deleteHxlx(List<String> list, String number, String department) {
        return hxlxDao.deleteHxlx(list,number,department);
    }

    @Override
    public int deleteById(List<String> list) {
        deleteFjscByList(list);
        return hxlxDao.deleteById(list);
    }

    @Override
    public Hxlx showHxlx(String department, String number, String id) {
        return hxlxDao.showHxlx(department,number,id);

    }

    @Override
    public boolean updateHxlx(Hxlx hxlx) {
        return hxlxDao.updateHxlx(hxlx);

    }

    @Override
    public boolean updateHxlxByHigh(Audit audit) {
        return hxlxDao.updateHxlxByHigh(audit);

    }

    @Override
    public void deleteFjscByList(List<String> list) {
        List<String> fileNames = hxlxDao.getFileNameByList(list);
        for (String fileName : fileNames) {
            log.info("删除附件=="+fileName);
            if (fileName != null) {
                File file = new File(filefjscPath + fileName);
                file.delete();
            }
        }
    }

    @Override
    public String downloadhxlxZipFjsc(List<String> list) {
        List<String> fileNames = hxlxDao.getFileNameByList(list);
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
    public HSSFWorkbook exporthxlxExcel(String department, String number, String roletype) {
        List<Hxlx> hxxms = hxlxDao.selectHxlxByDiff(department, number, roletype);
        //设置文件名称  产生excel文件内容

        String[] title = new String[]{"科研单位", "工号", "姓名", "项目名称", "负责人", "立项/结项/终止", "总金额(万元)", "已拨款(万元)",
                "合同编号", "协议单位", "签订时间", "开始日期", "截止日期",
                "业绩点","科研奖金(万元)","备注"};
        String[][] data = new String[hxxms.size()][];
        int length = hxxms.size();
        for (int i = 0; i < length; i++) {
            data[i] = new String[title.length];
            data[i][0] = hxxms.get(i).getDepartment();
            data[i][1] = hxxms.get(i).getNumber();
            data[i][2] = hxxms.get(i).getName();
            data[i][3] = hxxms.get(i).getXmname();
            data[i][4] = hxxms.get(i).getPrincipal();
            data[i][5] = hxxms.get(i).getXmstatus();
            data[i][6] = hxxms.get(i).getZje();
            data[i][7] = hxxms.get(i).getYbk();
            data[i][8] = hxxms.get(i).getContractnumber();
            data[i][9] = hxxms.get(i).getAunit();
            data[i][10] = hxxms.get(i).getQdtime();
            data[i][11] = hxxms.get(i).getJztime();

            data[i][12] = hxxms.get(i).getPoints();
            data[i][13] = hxxms.get(i).getReward();
            data[i][14] = hxxms.get(i).getRemark();
        }

        HSSFWorkbook wb = ExcelUtils.getHSSFWorkbook("横向立项", title, data, null);
        return wb;
    }

    @Override
    public String getExcelJson(String department, String number,String name, String start, String end, String judgestatus, List<String> params) {
        List<Map<Object, Object>> datamaps = hxlxDao.getExcelJson(department, number,name, start, end, judgestatus, params);
        String[] title = new String[params.size()];
        Map<String,String> originMap = new HashMap<>();
        originMap.put("number","工号");
        originMap.put("name","姓名");
        originMap.put("department","院系");

        originMap.put("xmname","项目名称");
        originMap.put("principal","负责人");
        originMap.put("xmstatus","立项/结项/终止");
        originMap.put("zje","总金额(万元)");
        originMap.put("ybk","已拨款(万元)");
        originMap.put("contractnumber","合同编号");
        originMap.put("aunit","协议单位");
        originMap.put("qdtime","签订时间");
        originMap.put("jztime","截止日期");
        originMap.put("stime","开始日期");
        originMap.put("points","业绩点");
        originMap.put("reward","科研奖励(万)");
        originMap.put("remark","备注");
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


        HSSFWorkbook wb = ExcelUtils.getHSSFWorkbook("横向立项", title, data, null);
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
