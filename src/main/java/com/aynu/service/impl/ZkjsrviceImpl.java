package com.aynu.service.impl;

import com.aynu.Utils.ExcelUtils;
import com.aynu.bean.Zkjs;
import com.aynu.dao.ZkjsDao;
import com.aynu.dto.Audit;
import com.aynu.service.ZkjsService;
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
 * @Date 2020/11/12 21:10
 * @description:
 */
@Service
@Slf4j
public class ZkjsrviceImpl implements ZkjsService {
    @Autowired
    ZkjsDao zkjsDao;
    @Value("${upload.fjscdir}")
    String filefjscPath;
    @Override
    public List<Zkjs> selectZkjsByDetail(String department, String number, String name, String start, String end, String judgestatus, String roletype) {
        return zkjsDao.selectZkjsByDetail(department,number,name,start,end,judgestatus,roletype);
    }

    @Override
    public List<Zkjs> selectZkjsByDetail2(String department, String number, String name, String start, String end, String judgestatus, String roletype, String cgname, String cnjb, String dyzz, String qtzz, String cnbm, String cntime) {
        return zkjsDao.selectZkjsByDetail2(department,number,name,start,end,judgestatus,roletype,cgname,cnjb,dyzz,qtzz,cnbm,cntime);
    }
    @Override
    public boolean insertZkjs(Zkjs zkjs) {
        return zkjsDao.insertZkjs(zkjs);
    }

    @Override
    public boolean deleteZkjs(List<String> list, String number, String department) {
        return zkjsDao.deleteZkjs(list,number,department);

    }

    @Override
    public int deleteById(List<String> list) {
        deleteFjscByList(list);
        return zkjsDao.deleteById(list);
    }

    @Override
    public Zkjs showZkjs(String department, String number, String id) {
        return zkjsDao.showZkjs(department,number,id);

    }

    @Override
    public boolean updateZkjs(Zkjs zkjs) {
        return zkjsDao.updateZkjs(zkjs);

    }

    @Override
    public boolean updateZkjsByHigh(Audit audit) {
        return zkjsDao.updateZkjsByHigh(audit);

    }

    @Override
    public void deleteFjscByList(List<String> list) {
        List<String> fileNames = zkjsDao.getFileNameByList(list);
        for (String fileName : fileNames) {
            log.info("删除附件=="+fileName);
            if (fileName != null) {
                File file = new File(filefjscPath + fileName);
                file.delete();
            }
        }
    }

    @Override
    public String downloadzkjsZipFjsc(List<String> list) {
        List<String> fileNames = zkjsDao.getFileNameByList(list);
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
    public HSSFWorkbook exportzkjsExcel(String department, String number, String roletype) {
        List<Zkjs> zkjsList = zkjsDao.selectZkjsByDiff(department, number, roletype);
        //设置文件名称  产生excel文件内容

        String[] title = new String[]{"科研单位", "工号", "姓名", "成果名称", "采纳级别", "第一完成人", "其它参与人", "采纳部门",
                "采纳时间", "业绩点", "科研奖金(万元)"};
        String[][] data = new String[zkjsList.size()][];
        int length = zkjsList.size();
        for (int i = 0; i < length; i++) {
            data[i] = new String[title.length];
            data[i][0] = zkjsList.get(i).getDepartment();
            data[i][1] = zkjsList.get(i).getNumber();
            data[i][2] = zkjsList.get(i).getName();
            data[i][3] = zkjsList.get(i).getCgname();
            data[i][4] = zkjsList.get(i).getCnjb();
            data[i][5] = zkjsList.get(i).getDyzz();
            data[i][6] = zkjsList.get(i).getQtzz();
            data[i][7] = zkjsList.get(i).getCnbm();
            data[i][8] = zkjsList.get(i).getCntime();
            data[i][9] = zkjsList.get(i).getPoints();
            data[i][10] = zkjsList.get(i).getReward();
        }
        HSSFWorkbook wb = ExcelUtils.getHSSFWorkbook("智库建设", title, data, null);
        return wb;
    }

    @Override
    public String getExcelJson(String department, String number, String name,String start, String end, String judgestatus, List<String> params) {
        List<Map<Object, Object>> datamaps = zkjsDao.getExcelJson(department, number,name, start, end, judgestatus, params);
        String[] title = new String[params.size()];
        Map<String,String> originMap = new HashMap<>();
        originMap.put("number","工号");
        originMap.put("name","姓名");
        originMap.put("department","院系");
        originMap.put("timu","题目");
        originMap.put("cgname","成果名称");
        originMap.put("cnjb","采纳级别");
        originMap.put("dyzz","第一作者");
        originMap.put("qtzz","其它作者");
        originMap.put("cnbm","采纳部门");
        originMap.put("cntime","采纳时间");
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


        HSSFWorkbook wb = ExcelUtils.getHSSFWorkbook("智库建设", title, data, null);
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
