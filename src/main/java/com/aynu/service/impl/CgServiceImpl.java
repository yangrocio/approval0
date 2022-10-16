package com.aynu.service.impl;

import com.aynu.Utils.ExcelUtils;
import com.aynu.bean.Cg;
import com.aynu.dao.CgDao;
import com.aynu.dto.Audit;
import com.aynu.service.CgService;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
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
public class CgServiceImpl implements CgService {
    @Autowired
    CgDao cgDao;
    @Value("${upload.fjscdir}")
    String filefjscPath;
    @Override
    public List<Cg> selectCgByDetail(String department, String number, String start, String  end,
                                     String judgestatus,String roletype) {
        return cgDao.selectCgByDetail(department,number,start,end,judgestatus,roletype);
    }

    @Override
    public boolean insertCg(Cg cg) {
        return cgDao.insertCg(cg);
    }


    @Override
    public boolean deleteCg(List<String> list, String number, String department) {
        return cgDao.deleteCg(list,number,department);
    }

    @Override
    public Cg showCg(String department, String number, String id) {
        return cgDao.showCg(department,number,id);
    }

    @Override
    public boolean updateCg(Cg cg) {
        return cgDao.updateCg(cg);
    }

    @Override
    public boolean updateCgByHigh(Audit audit) {
        return cgDao.updateCgByHigh(audit);
    }

    @Override
    public void deleteFjscByList(List<String> list) {
        List<String> fileNames = cgDao.getFileNameByList(list);
        for (String fileName : fileNames) {
            log.info(fileName);
            if (fileName != null) {
                log.info("删除");
                File file = new File(filefjscPath + fileName);
                file.delete();
            }
        }
    }

    @Override
    public String downloadcgZipFjsc(List<String> list) {
        List<String> fileNames = cgDao.getFileNameByList(list);
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
        List<Cg> cgs = cgDao.selectCgByDiff(department, number, roletype);
        //设置文件名称  产生excel文件内容

        String[] title = new String[]{"科研单位", "工号", "姓名", "成果名称", "成果类型", "第一完成人", "其它参与人", "采纳部门",
                "成果转让到账经费(万元)", "时 间", "业绩点", "科研奖金(万元)"};
        String[][] data = new String[cgs.size()][];
        int length = cgs.size();
        for (int i = 0; i < length; i++) {
            data[i] = new String[title.length];
            data[i][0] = cgs.get(i).getDepartment();
            data[i][1] = cgs.get(i).getNumber();
            data[i][2] = cgs.get(i).getName();
            data[i][3] = cgs.get(i).getCgname();
            data[i][4] = cgs.get(i).getCgtype();
            data[i][5] = cgs.get(i).getDyzz();
            data[i][6] = cgs.get(i).getQtzz();
            data[i][7] = cgs.get(i).getCnbm();
            data[i][8] = cgs.get(i).getCgdz();
            data[i][9] = cgs.get(i).getTime();
            data[i][10] = cgs.get(i).getPoints();
            data[i][11] = cgs.get(i).getReward();
        }
        HSSFWorkbook wb = ExcelUtils.getHSSFWorkbook("成果", title, data, null);
        return wb;
    }
}
