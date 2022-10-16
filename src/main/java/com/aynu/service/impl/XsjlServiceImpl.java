package com.aynu.service.impl;

import com.aynu.Utils.ExcelUtils;
import com.aynu.bean.Xsjl;
import com.aynu.dao.XsjlDao;
import com.aynu.dto.Audit;
import com.aynu.service.XsjlService;
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
public class XsjlServiceImpl implements XsjlService {
    @Autowired
    XsjlDao xsjlDao;
    @Value("${upload.fjscdir}")
    String filefjscPath;
    @Override
    public List<Xsjl> selectXsjlByDetail(String department, String number, String name, String start, String  end,
                                         String judgestatus, String roletype) {
        return xsjlDao.selectXsjlByDetail(department,number,name,start,end,judgestatus,roletype);
    }

    @Override
    public List<Xsjl> selectXsjlByDetail2(String department, String number, String name, String start, String  end,
                                          String judgestatus, String roletype, String XMName, String CYRY, String Rank) {
        return xsjlDao.selectXsjlByDetail2(department,number,name,start,end,judgestatus,roletype,XMName,CYRY,Rank);
    }

    @Override
    public boolean insertXsjl(Xsjl xsjl) {
        return xsjlDao.insertXsjl(xsjl);
    }

    @Override
    public boolean deleteXsjl(List<String> list, String number, String department) {
        return xsjlDao.deleteXsjl(list,number,department);
    }

    @Override
    public int deleteById(List<String> list) {
        deleteFjscByList(list);
        return xsjlDao.deleteById(list);
    }

    @Override
    public Xsjl showXsjl(String department, String number, String id) {
        return xsjlDao.showXsjl(department,number,id);
    }

    @Override
    public boolean updateXsjl(Xsjl xsjl) {
        return xsjlDao.updateXsjl(xsjl);
    }

    @Override
    public boolean updateXsjlByHigh(Audit audit) {
        return xsjlDao.updateXsjlByHigh(audit);
    }

    @Override
    public void deleteFjscByList(List<String> list) {
        List<String> fileNames = xsjlDao.getFileNameByList(list);
        for (String fileName : fileNames) {
            log.info("删除附件=="+fileName);
            if (fileName != null) {
                File file = new File(filefjscPath + fileName);
                file.delete();
            }
        }
    }

    @Override
    public String downloadxsjlZipFjsc(List<String> list) {
        List<String> fileNames = xsjlDao.getFileNameByList(list);
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
    public HSSFWorkbook exportxsjlExcel(String department, String number, String roletype) {
        List<Xsjl> xsjls = xsjlDao.selectXsjlByDiff(department, number, roletype);
        //设置文件名称  产生excel文件内容

        String[] title = new String[]{"科研单位", "工号", "姓名", "会议名称", "参与人员", "会议地点", "会议时间", "主办单位", "会议等级",
                "是否发表论文","是否发言"};
        String[][] data = new String[xsjls.size()][];
        int length = xsjls.size();
        for (int i = 0; i < length; i++) {
            data[i] = new String[title.length];
            data[i][0] = xsjls.get(i).getDepartment();
            data[i][1] = xsjls.get(i).getNumber();
            data[i][2] = xsjls.get(i).getName();
            data[i][3] = xsjls.get(i).getXmname();
            data[i][4] = xsjls.get(i).getCyry();
            data[i][5] = xsjls.get(i).getWheres();
            data[i][6] = xsjls.get(i).getZbdw();
            data[i][7] = xsjls.get(i).getRank();
            data[i][8] = xsjls.get(i).getLunwen();
            data[i][9] = xsjls.get(i).getFayan();
        }
        HSSFWorkbook wb = ExcelUtils.getHSSFWorkbook("学术交流", title, data, null);
        return wb;
    }

    @Override
    public String getExcelJson(String department, String number, String name,String start, String end, String judgestatus, List<String> params) {
        List<Map<Object, Object>> datamaps = xsjlDao.getExcelJson(department, number,name, start, end, judgestatus, params);
        String[] title = new String[params.size()];
        Map<String,String> originMap = new HashMap<>();
        originMap.put("number","工号");
        originMap.put("name","姓名");
        originMap.put("department","院系");
        originMap.put("xmname","会议名称");
        originMap.put("cyry","参与人员");
        originMap.put("wheres","会议地点");
        originMap.put("time","会议时间");
        originMap.put("zbdw","主办单位");
        originMap.put("rank","会议等级");
        originMap.put("lunwen","是否发表论文");
        originMap.put("fayan","是否发言");
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


        HSSFWorkbook wb = ExcelUtils.getHSSFWorkbook("学术交流", title, data, null);
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
