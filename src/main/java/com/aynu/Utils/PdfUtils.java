package com.aynu.Utils;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

/**
 * @Author susuper
 * @Date 2020/9/27 9:42
 * @description:
 */
@Slf4j
@Component
public class PdfUtils {
    @Value("${upload.fjdir}")
    String filefjPath;

    @Value("${upload.fjscdir}")
    String filefjscPath;
    public String createPDF(Map<String, String> data,String filePath) {
        PdfReader reader = null;
        AcroFields s = null;
        PdfStamper ps = null;
        ByteArrayOutputStream bos = null;


        //创建上传文件目录

        File folder = new File(filePath);
        if (!folder.exists()) {
            folder.mkdirs();
        }
        //设置文件名
        String uuid = UUID.randomUUID().toString().replaceAll("-","");

        String fileName = uuid +".pdf";
        String savePath = filePath +  fileName;

        try {
//            filefjscPath = "C:\\Users\\DELL\\Desktop\\";
            String file =filefjscPath+"test.pdf";
//            log.info("files:"+files);
//            log.info("file:"+file);
//                    this.getClass().getClassLoader().getResource("comfirmTemplate.pdf").getPath();
            //设置字体
//            String font = filefjscPath+"仿宋_GB2312.ttf";
            String font = filefjscPath+"仿宋_GB2312.ttf";
//            log.info("fonts:"+fonts);
//            log.info("font:"+font);
//                    this.getClass().getClassLoader().getResource("YaHei.ttf").getPath();

            reader = new PdfReader(file);
            bos = new ByteArrayOutputStream();
            ps = new PdfStamper(reader, bos);
            s = ps.getAcroFields();
            //使用中文字体 使用 AcroFields填充值的不需要在程序中设置字体，在模板文件中设置字体为中文字体 Adobe 宋体 std L
            //设置编码格式
            BaseFont bfChinese = BaseFont.createFont(font, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
            //设置编码格式
            s.addSubstitutionFont(bfChinese);
            // 遍历data 给pdf表单表格赋值
            for (String key : data.keySet()) {
                if (data.get(key) != null) {
                    String value = data.get(key);
                    if(value.equals("自办") || value.equals("代理") ||value.equals("发明专利")
                            ||value.equals("实用新型专利") ||value.equals("集成电路布图设计") ||value.equals("软件著作权") ||
                            value.equals("外观设计专利") ||value.equals("其他") || value.equals("职务成果")
                            ||value.equals("非职务成果") ||value.equals("是") ||value.equals("否")
                            ||value.equals("横向") ||value.equals("纵向")){
                        s.setField(key,data.get(key).toString(),true);
                    } else{
                        s.setField(key, data.get(key).toString());
                    }

                }
            }

            // 如果为false那么生成的PDF文件还能编辑，一定要设为true
            ps.setFormFlattening(true);
            ps.close();

            FileOutputStream fos = new FileOutputStream(savePath);

            fos.write(bos.toByteArray());
            fos.flush();
            fos.close();
            return savePath;
        } catch (IOException
                | DocumentException e) {
            log.error("读取文件异常");
            e.printStackTrace();
            return "";
        } finally {
            try {
                bos.close();
                reader.close();
            } catch (IOException e) {
                log.error("关闭流异常");
                e.printStackTrace();
            }
        }
    }
}
