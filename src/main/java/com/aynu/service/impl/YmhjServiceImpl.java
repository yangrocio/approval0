package com.aynu.service.impl;

import com.aynu.Utils.ExcelUtils;
import com.aynu.bean.Ymhj;
import com.aynu.dao.YmhjDao;
import com.aynu.dto.Audit;
import com.aynu.service.YmhjService;
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
public class YmhjServiceImpl implements YmhjService {
    @Autowired
    YmhjDao ymhjDao;
    @Value("${upload.fjscdir}")
    String filefjscPath;
    @Override
    public List<Ymhj> selectYmhjByDetail(String department, String number, String name, String start, String  end,
                                         String judgestatus, String roletype) {
        return ymhjDao.selectYmhjByDetail(department,number,name,start,end,judgestatus,roletype);
    }
    @Override
    public List<Ymhj> selectYmhjByDetail2(String department, String number, String name, String start, String  end,
                                          String judgestatus, String roletype, String TiMu, String AwardName, String DYZZ, String QTZZ, String Organizer,
                                          String RewardSort, String AwardTime) {
        return ymhjDao.selectYmhjByDetail2(department,number,name,start,end,judgestatus,roletype,TiMu,AwardName,DYZZ,QTZZ,Organizer,RewardSort,AwardTime);
    }

    @Override
    public boolean insertYmhj(Ymhj ymhj) {
        return ymhjDao.insertYmhj(ymhj);
    }

    @Override
    public boolean deleteYmhj(List<String> list, String number, String department) {
        return ymhjDao.deleteYmhj(list,number,department);
    }

    @Override
    public int deleteById(List<String> list) {
        deleteFjscByList(list);
        return ymhjDao.deleteById(list);
    }

    @Override
    public Ymhj showYmhj(String department, String number, String id) {
        return ymhjDao.showYmhj(department,number,id);
    }

    @Override
    public boolean updateYmhj(Ymhj ymhj) {
        return ymhjDao.updateYmhj(ymhj);
    }

    @Override
    public boolean updateYmhjByHigh(Audit audit) {
        return ymhjDao.updateYmhjByHigh(audit);
    }

    @Override
    public void deleteFjscByList(List<String> list) {
        List<String> fileNames = ymhjDao.getFileNameByList(list);
        for (String fileName : fileNames) {
            log.info("删除附件=="+fileName);
            if (fileName != null) {
                File file = new File(filefjscPath + fileName);
                file.delete();
            }
        }
    }

    @Override
    public String downloadymhjZipFjsc(List<String> list) {
        List<String> fileNames = ymhjDao.getFileNameByList(list);
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
    public HSSFWorkbook exportymhjExcel(String department, String number, String roletype) {
        List<Ymhj> ymhjs = ymhjDao.selectYmhjByDiff(department, number, roletype);
        //设置文件名称  产生excel文件内容

        String[] title = new String[]{"科研单位", "工号", "姓名", "题目", "获奖名称", "第一作者", "其它作者", "主办单位", "获奖时间",
                "奖励类别", "奖励金(万元)", "业绩点", "备注"};
        String[][] data = new String[ymhjs.size()][];
        int length = ymhjs.size();
        for (int i = 0; i < length; i++) {
            data[i] = new String[title.length];
            data[i][0] = ymhjs.get(i).getDepartment();
            data[i][1] = ymhjs.get(i).getNumber();
            data[i][2] = ymhjs.get(i).getName();
            data[i][3] = ymhjs.get(i).getTimu();
            data[i][4] = ymhjs.get(i).getAwardname();
            data[i][5] = ymhjs.get(i).getDyzz();
            data[i][6] = ymhjs.get(i).getQtzz();
            data[i][7] = ymhjs.get(i).getOrganizer();
            data[i][8] = ymhjs.get(i).getAwardtime();
            data[i][9] = ymhjs.get(i).getRewardsort();
            data[i][10] = ymhjs.get(i).getReward();
            data[i][11] = ymhjs.get(i).getPoints();

            data[i][12] = ymhjs.get(i).getRemark();
        }
        HSSFWorkbook wb = ExcelUtils.getHSSFWorkbook("音美获奖", title, data, null);
        return wb;
    }

    @Override
    public String getExcelJson(String department, String number,String name, String start, String end, String judgestatus, List<String> params) {
        List<Map<Object, Object>> datamaps = ymhjDao.getExcelJson(department, number,name, start, end, judgestatus, params);
        String[] title = new String[params.size()];
        Map<String,String> originMap = new HashMap<>();
        originMap.put("number","工号");
        originMap.put("name","姓名");
        originMap.put("department","院系");
        originMap.put("timu","参赛作品");
        originMap.put("awardname","比赛名称");
        originMap.put("dyzz","第一作者");
        originMap.put("qtzz","其它作者");
        originMap.put("organizer","主办单位");
        originMap.put("awardtime","获奖时间");
        originMap.put("rewardsort","奖励类别");
        originMap.put("points","业绩点");
        originMap.put("reward","科研奖励(万元)");
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


        HSSFWorkbook wb = ExcelUtils.getHSSFWorkbook("音美获奖", title, data, null);
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
