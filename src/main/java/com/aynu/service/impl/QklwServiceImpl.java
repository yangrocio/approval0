package com.aynu.service.impl;

import com.aynu.Utils.ExcelUtils;
import com.aynu.bean.Qklw;
import com.aynu.dao.QklwDao;
import com.aynu.dto.Audit;
import com.aynu.service.QklwService;
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
public class QklwServiceImpl implements QklwService {
    @Autowired
    QklwDao qklwDao;

    @Value("${upload.fjscdir}")
    String filefjscPath;
    @Override
    public List<Qklw> selectQklwByDetail(String department, String number, String name,  String start, String  end, String judgestatus,String roletype) {
        return qklwDao.selectQklwByDetail(department,number,name,start,end,judgestatus,roletype);
    }

    public List<Qklw> selectQklwByDetail2(String department, String number,String name, String start, String end, String judgestatus,String roletype,String timu,String dyzz,String qtzz,String type,String fbqk,String time,String fq)
    {
        return qklwDao.selectQklwByDetail2(department,number,name,start,end,judgestatus,roletype,timu,dyzz,qtzz,type,fbqk,time,fq);
    }

    @Override
    public boolean insertQklw(Qklw qklw) {
        return qklwDao.insertQklw(qklw);
    }

    @Override
    public boolean deleteQklw(List<String> list, String number, String department) {
        return qklwDao.deleteQklw(list,number,department);
    }

    @Override
    public int deleteById(List<String> list) {
        deleteFjscByList(list);
        return qklwDao.deleteById(list);
    }

    @Override
    public Qklw showQklw(String department, String number, String id) {
        return qklwDao.showQklw(department,number,id);
    }

    @Override
    public boolean updateQklw(Qklw qklw) {
        return qklwDao.updateQklw(qklw);
    }

    @Override
    public boolean updateQklwByHigh(Audit audit) {
        return qklwDao.updateQklwByHigh(audit);
    }

    @Override
    public void deleteFjscByList(List<String> list) {
        List<String> fileNames = qklwDao.getFileNameByList(list);
        for (String fileName : fileNames) {
            log.info("删除附件=="+fileName);
            if (fileName != null) {
                File file = new File(filefjscPath + fileName);
                file.delete();
            }
        }
    }

    @Override
    public HSSFWorkbook exportqklwExcel(String department, String number, String roletype) {
        List<Qklw> qklws = qklwDao.selectQklwByDiff(department, number, roletype);
        String[] title = new String[]{"科研单位", "工号", "姓名", "题目", "第一作者", "其它作者", "发表期刊", "卷期号", "页码",
                "发表时间", "论文级别", "字数", "科研奖励(万元)", "业绩点", "分区", "影响因子"};
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
            data[i][6] = qklws.get(i).getQikanname();
            data[i][7] = qklws.get(i).getJqnumber();
            data[i][8] = qklws.get(i).getPage();
            data[i][9] = qklws.get(i).getTime();
            data[i][10] = qklws.get(i).getRank();
            data[i][11] = qklws.get(i).getZishu();
            data[i][12] = qklws.get(i).getReward();
            data[i][13] = qklws.get(i).getPoints();
            data[i][14] = qklws.get(i).getFq();
            data[i][15] = qklws.get(i).getYxyz();
        }
        HSSFWorkbook wb = ExcelUtils.getHSSFWorkbook("期刊论文", title, data, null);
        return wb;
    }

    @Override
    public String downloadqklwZipFjsc(List<String> list) {
        List<String> fileNames = qklwDao.getFileNameByList(list);
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
    public String getExcelJson(String department, String number,String name, String start, String end, String judgestatus, List<String> params) {
        List<Map<Object, Object>> datamaps = qklwDao.getExcelJson(department, number, name,start, end, judgestatus, params);
        String[] title = new String[params.size()];
        Map<String,String> originMap = new HashMap<>();
        originMap.put("number","工号");
        originMap.put("name","姓名");
        originMap.put("department","院系");
        originMap.put("timu","题目");
        originMap.put("dyzz","第一作者");
        originMap.put("qtzz","其它作者");

        originMap.put("type","奖励类别");
        originMap.put("qikanname","发表期刊");
        originMap.put("jqnumber","卷期号");
        originMap.put("page","页码");
        originMap.put("time","发表时间");
        originMap.put("zishu","字数(千)");
        originMap.put("reward","科研奖励(万元)");
        originMap.put("points","业绩点");

        originMap.put("fq","分区");
        originMap.put("yxyz","影响因子");
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


        HSSFWorkbook wb = ExcelUtils.getHSSFWorkbook("期刊论文", title, data, null);
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
