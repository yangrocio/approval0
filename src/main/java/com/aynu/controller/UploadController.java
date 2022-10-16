package com.aynu.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.read.metadata.ReadSheet;
import com.aynu.Utils.*;
import com.aynu.bean.*;
import com.aynu.controller.DataListener.*;
import com.aynu.dao.*;
import com.aynu.dto.BriefTeacher;
import com.aynu.dto.Result;
import com.aynu.service.FileSavePathService;
import com.aynu.service.TeacherDocService;
import com.aynu.service.UserInfoService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author Susuper 果冻
 * @version 1.0
 * @date 2020/5/3 16:30
 * @description
 */
@RequestMapping("/file")
@Controller
@Slf4j
//@Scope("prototype")
public class UploadController {
    @Autowired
    FileSavePathService fileSavePathService;

    @Autowired
    TeacherDocService teacherDocService;

    @Autowired
    UserInfoService userInfoService;

    @Autowired
    PdfUtils pdfUtils;

    @Autowired
    Environment environment;

    @Autowired
    HylwDao hylwDao;
    @Autowired
    QklwDao qklwDao;
    @Autowired
    XszzDao xszzDao;
    @Autowired
    HpxmDao hpxmDao;
    @Autowired
    JxysDao jxysDao;
    @Autowired
    HxlxDao hxlxDao;
    @Autowired
    HxxmDao hxxmDao;
    @Autowired
    RjzzDao rjzzDao;
    @Autowired
    HpzlDao hpzlDao;
    @Autowired
    XschDao xschDao;
    @Autowired
    XsjlDao xsjlDao;
    @Autowired
    HpjlDao hpjlDao;
    @Autowired
    YmhjDao ymhjDao;
    @Autowired
    TyhjDao tyhjDao;
    @Autowired
    ZkjsDao zkjsDao;
    @Autowired
    CgzhDao cgzhDao;

    @Autowired
    PtglDao ptglDao;
    @Autowired
    TdglDao tdglDao;

    //    上面是在 windows的一个 临时存储文件夹
//    String filePath = "C:\\Users\\DELL\\Desktop\\wordtemplate\\";

    //    这是在linux上的临时存储文件夹
//    String filePath = "/usr/word/";
    @Value("${upload.dir}")
    String filePath;

    @Value("${upload.fjdir}")
    String filefjPath;

    @Value("${upload.fjscdir}")
    String filefjscPath;


    //上传 文件
    @PostMapping("uploadfjsc")
    @ResponseBody
    public String uploadfjsc(@RequestParam(value = "fjsc") MultipartFile file, HttpSession session) {
        UserInfo user = (UserInfo) session.getAttribute("USER_SESSION");
        String uuid = UUID.randomUUID().toString().replace("-", "");
        //创建 院系文件夹
        String department = user.getDepartment();
        String name = user.getNumber() + user.getName();
        //创建目录文件夹
        String muludir = filefjscPath + department + "/" + name;
        File fp = new File(muludir);
        if (!fp.exists()) {
            fp.mkdirs();
        }
        String fileRandomName = department + "/" + name + "/" + uuid + file.getOriginalFilename();
        String filename = filefjscPath + fileRandomName;
        File desFile = new File(filename);
        try {
            file.transferTo(desFile);
            log.info("上传成功:" + filename);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileRandomName;
    }


    @PostMapping("uploadTrueAnnex/{type}")
    @ResponseBody
    public String uploadtrueAnnex(@RequestParam(value = "annex") MultipartFile file,
                              @PathVariable("type") String type) {
        String department = "科研";
        String uuid = UUID.randomUUID().toString().replace("-", "");
        String name = type;
        //创建目录文件夹
        String muludir = filefjscPath + department + "/" + name;
        File fp = new File(muludir);
        if (!fp.exists()) {
            fp.mkdirs();
        }
        String fileRandomName = department + "/" + name + "/" + uuid + file.getOriginalFilename();
        String filename = filefjscPath + fileRandomName;
        File desFile = new File(filename);
        try {
            file.transferTo(desFile);
            log.info("上传成功:" + filename);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileRandomName;
    }

    //    @RequestParam(value = "platform") String platform,
//    @RequestParam(value = "department") String department,
//    @RequestParam(value = "id") String id,
    @PostMapping("uploadAnnex/{type}")
    @ResponseBody
    public String uploadAnnex(@RequestParam(value = "annex") MultipartFile file,
                              @RequestParam(value = "oldname") String oldname,
                              @RequestParam(value = "id") String id,
                              @PathVariable("type") String type) {
        String department = "科研";
        String uuid = UUID.randomUUID().toString().replace("-", "");
        String name = type;

        //创建目录文件夹
        String muludir = filefjscPath + department + "/" + name;
        File fp = new File(muludir);
        if (!fp.exists()) {
            fp.mkdirs();
        }
        String fileRandomName = department + "/" + name + "/" + uuid + file.getOriginalFilename();
        String filename = filefjscPath + fileRandomName;
        File desFile = new File(filename);
        try {
            file.transferTo(desFile);
            log.info("上传成功:" + filename);
            File oldFile = new File(filefjscPath+oldname);
            if (oldFile.exists()){
                oldFile.delete();
            }
            if (type.equals("ptgl")){
                ptglDao.updateAnnex(fileRandomName,id);
            }else if (type.equals("tdgl")){
                tdglDao.updateAnnex(fileRandomName,id);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileRandomName;
    }


    //写一个在线预览
    @GetMapping(value = "preview")
    public void pdfStreamHandler(@RequestParam(value = "filename") String filename, HttpServletResponse response) {
        File file = new File(filefjscPath + filename);

        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream(file);
            byte[] data = new byte[inputStream.available()];
            inputStream.read(data);
            response.getOutputStream().write(data);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    //下载文件
    @GetMapping("downloadfile")
    public void downloadfile(@RequestParam(value = "filename") String filename, HttpServletResponse response) {
        File file = new File(filefjscPath + filename);
//        System.out.println(file.getName());

        if (file.exists()) {
            try {
                FileInputStream fis = new FileInputStream(file);
                response.setContentType("application/force-download");
                response.addHeader("Content-disposition", "attachment;fileName="
                        + new String(file.getName().getBytes("utf-8"), "ISO8859-1"));
                OutputStream os = response.getOutputStream();
                byte[] buf = new byte[2048];
                int len = 0;
                while ((len = fis.read(buf)) != -1) {
                    os.write(buf, 0, len);
                }
                os.flush();
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //下载文件 并且删除原来文件
    @GetMapping("downloadfiledelteold")
    public void downloadfiledelteold(@RequestParam(value = "filename") String filename, HttpServletResponse response) {
        File file = new File(filefjscPath + filename);

        if (file.exists()) {
            try {
                FileInputStream fis = new FileInputStream(file);
                response.setContentType("application/force-download");
                response.addHeader("Content-disposition", "attachment;fileName="
                        + new String(file.getName().getBytes("utf-8"), "ISO8859-1"));
                OutputStream os = response.getOutputStream();
                byte[] buf = new byte[2048];
                int len = 0;
                while ((len = fis.read(buf)) != -1) {
                    os.write(buf, 0, len);
                }
                os.flush();
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                log.info("文件下载成功：" + filename);
                file.delete();
            }
        }
    }


    //上传excel表格 需要 MultipartFile就可以了
    @ResponseBody
    @PostMapping("/uploadExcel")
    public Result uploadExcel(@RequestParam(value = "file") MultipartFile file,
                              @RequestParam(value = "filetype") String fileType) throws Exception {
        InputStream in = file.getInputStream();
        FileInputStream fle = (FileInputStream) in;
        List<UserInfo> userInfos = SingleExcelUtil.readExcel(fileType, in);

        //批量插入
        int num = userInfoService.InsertManyUserByList(userInfos);
        if (num != 0) {
            return new Result(1, "插入成功，插入" + num + "条");
        } else {
            return new Result(1, "插入失败");
        }
    }


    @PostMapping("/upload")
    @ResponseBody
    public Result upload(HttpSession session, @RequestParam("file") MultipartFile file,
                         @RequestParam("tid") String tid, @RequestParam("filesize") Long filesize) {
        log.info("文件大小是:" + filesize);
        FileSavePath fileOld = fileSavePathService.selectFileSavePath(tid);
        if (fileOld != null) {
            //删除文件
            File OldDelete = new File(filefjPath + fileOld.getFilename());
            OldDelete.delete();
            fileSavePathService.deleteFileSavePath(tid);
        }
        if (file.isEmpty()) {
            return new Result(0, "上传失败,请选择文件");
        }
        String uuid = UUID.randomUUID().toString().replace("-", "");
        String fileRandomName = uuid + file.getOriginalFilename();
        String filename = filefjPath + fileRandomName;
        log.info(filename);
        File desFile = new File(filename);
        try {
//            file.transferTo(desFile);
            BufferedInputStream inputStream = new BufferedInputStream(file.getInputStream());
            BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(desFile));

            byte[] bytes = new byte[10];
            int len = -1;
            while ((len = inputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, len);
            }
            outputStream.close();
            inputStream.close();
            return new Result(1, "上传成功");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
//            processInfo.setShow("end");
            fileSavePathService.insertFileSavePath(tid, fileRandomName);  //存储文件路劲
        }

        return new Result(0, "上传失败");
    }


    @PostMapping("/existFile")
    @ResponseBody
    public Result existFile(HttpSession session, @RequestParam("tid") String tid) {
        UserInfo user = (UserInfo) session.getAttribute("USER_SESSION");
//        FileSavePath fileSavePath = new FileSavePath(tid);
        FileSavePath fileSavePath = fileSavePathService.selectFileSavePath(tid);
        if (fileSavePath == null) {
            return new Result(0, "文件尚未上传");
        } else {
            return new Result(1, "已有文件:" + fileSavePath.getFilename());
        }
    }


    @GetMapping("/download")
//    @OperationLogDetail(detail = "下载上传的专利证书", level = 3, operationType = OperationType.UPDATE, operationUnit = OperationUnit.UNKNOWN)
    public void download(HttpSession session, HttpServletResponse response, @RequestParam("tid") String tid) throws Exception {
        UserInfo user = (UserInfo) session.getAttribute("USER_SESSION");
        FileSavePath fileSavePath = fileSavePathService.selectFileSavePath(tid);
        File file = new File(filefjPath + fileSavePath.getFilename());
        // 穿件输入对象
        FileInputStream fis = new FileInputStream(file);
        // 设置相关格式
        response.setContentType("application/force-download");
        // 设置下载后的文件名以及header
        response.addHeader("Content-disposition", "attachment;fileName=" + new String(fileSavePath.getFilename().getBytes("utf-8"), "ISO8859-1"));
        // 创建输出对象
        OutputStream os = response.getOutputStream();
        // 常规操作
        byte[] buf = new byte[2048];
        int len = 0;
        while ((len = fis.read(buf)) != -1) {
            os.write(buf, 0, len);
        }
        fis.close();
    }


    @GetMapping(value = "/backup")
//    @OperationLogDetail(detail = "学校负责人对数据库进行了备份", level = 3, operationType = OperationType.UPDATE, operationUnit = OperationUnit.UNKNOWN)
    @ResponseBody
    public Map<String, String> backup(HttpServletResponse response) throws IOException {
        Properties properties = new Properties();
        String username = environment.getProperty("spring.datasource.username");
        String password = environment.getProperty("spring.datasource.password");

        SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd_hh_mm_ss");
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        String fileNameTime = uuid + "_" + String.valueOf(sf.format(new Date())) + ".txt";
        //名字
        String command = null;
        command = "mysqldump -u" + username + "  -p" + password + " approval -r  " + filefjscPath + fileNameTime;
        log.info(command);
        Process process = Runtime.getRuntime().exec(command);
        log.info("MYSQL自动备份完成，备份时间为：" + fileNameTime);
        HashMap<String, String> map = new HashMap();
        map.put("filename", fileNameTime);
        map.put("allfilename", filefjscPath + fileNameTime);
        return map;
    }

    @GetMapping(value = "/backuptoexcel")
    public void backuptoexcel(HttpServletResponse response) {
        // 把数据 查询出来  填写data数组
        List<UserInfo> userInfos = userInfoService.SelectUserInfoAll();

        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        //导出为excel表格
        String[] title = new String[]{"编号", "姓名", "账号", "密码", "性别", "生日", "身份证号", "电话", "民族",
                "院系", "职称", "学历", "学位", "毕业学校", "专业", "角色权限"};

        String[][] data = new String[userInfos.size()][];


        for (int i = 0; i < userInfos.size(); i++) {
            data[i] = new String[16];
            data[i][0] = String.valueOf(userInfos.get(i).getId());
            data[i][1] = userInfos.get(i).getName();
            data[i][2] = userInfos.get(i).getNumber();
            data[i][3] = userInfos.get(i).getPassword();
            if (userInfos.get(i).getSex() == 1) {
                data[i][4] = "男";
            } else if (userInfos.get(i).getSex() == 2) {
                data[i][4] = "女";
            }
            //考虑生日为空的情况
            Date birthday = userInfos.get(i).getBirthday();
            if (birthday == null) {
                data[i][5] = "";
            } else {
                data[i][5] = sf.format(birthday);
            }
//            data[i][5] = sf.format();
//            data[i][5] = userInfos.get(i).getBirthday()

            data[i][6] = userInfos.get(i).getIdcard();
            data[i][7] = userInfos.get(i).getTelephone();
            data[i][8] = userInfos.get(i).getNation();
            data[i][9] = userInfos.get(i).getDepartment();
            data[i][10] = userInfos.get(i).getProfession();
            data[i][11] = userInfos.get(i).getQualifications();
            data[i][12] = userInfos.get(i).getDegree();
            data[i][13] = userInfos.get(i).getHdgu();
            data[i][14] = userInfos.get(i).getMajor();
            if (userInfos.get(i).getRoletype().equals("1")) {
                data[i][15] = "教师";
            } else if (userInfos.get(i).getRoletype().equals("2")) {
                data[i][15] = "院系负责人";
            } else if (userInfos.get(i).getRoletype().equals("3")) {
                data[i][15] = "科研处管理员";
            } else if (userInfos.get(i).getRoletype().equals("4")) {
                data[i][15] = "超级管理员";
            }
        }

        String fileName = sf.format(new Date()) + "备份-汇总表格.xls";
        HSSFWorkbook wb = ExcelUtils.getHSSFWorkbook("人员表格", title, data, null);


        //响应到客户端
        try {
            this.setResponseHeader(response, fileName);
            OutputStream os = response.getOutputStream();
            wb.write(os);
            os.flush();
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void sqlDownload(HttpServletResponse response, String fileName) throws IOException {
//        this.setResponseHeader(response, fileName);
    }


    @GetMapping(value = "/ExcelBrief")
    public void ExcelBrief(HttpSession session, HttpServletResponse response) {
        List<BriefTeacher> briefTeachers = teacherDocService.selectBrief("3");
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd hh-mm-ss");
        //导出为excel表格
//        String[] title = new String[]{"科研单位", "工号", "项目编号", "项目名称", "申请人", "电话", "邮箱", "申请日期", "通过日期"};
        String[] title = new String[]{"科研单位", "工号", "项目编号", "项目名称", "申请人", "类別","电话", "邮箱", "申请日期", "通过日期"};
        String[][] data = new String[briefTeachers.size()][];
        for (int i = 0; i < briefTeachers.size(); i++) {
            data[i] = new String[10];//9
            data[i][0] = briefTeachers.get(i).getDepartment();
            data[i][1] = briefTeachers.get(i).getTusername();
            String onlyid = "zscq" + briefTeachers.get(i).getSuredate() + String.valueOf(briefTeachers.get(i).getTid());
            onlyid = onlyid.replaceAll("-", "");
            data[i][2] = onlyid;
            data[i][3] = briefTeachers.get(i).getTname();
            data[i][4] = briefTeachers.get(i).getTinventionname();
            data[i][5] = briefTeachers.get(i).getTcategory();
            data[i][6] = briefTeachers.get(i).getTphone();
            data[i][7] = briefTeachers.get(i).getTemail();
            data[i][8] = briefTeachers.get(i).getAdate();
            data[i][9] = briefTeachers.get(i).getSuredate();
        }

        //创建文件名
        String fileName = new SimpleDateFormat("yyyy-MM-dd hh-mm-ss").format(new Date()) + "-通过审核项目名单.xls";

        HSSFWorkbook wb = ExcelUtils.getHSSFWorkbook("汇总表格", title, data, null);

        //响应到客户端
        try {
            this.setResponseHeader(response, fileName);
            OutputStream os = response.getOutputStream();
            wb.write(os);
            os.flush();
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @GetMapping(value = "/ExcelBriefByDepartment")
    public void ExcelBriefByDepartment(HttpSession session, HttpServletResponse response) {
        UserInfo user = (UserInfo) session.getAttribute("USER_SESSION");
        //按照院系 查询 出来所有的3
        List<BriefTeacher> briefTeachers = teacherDocService.selectBriefByDepartment("3", user.getDepartment());

        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd hh-mm-ss");
        //导出为excel表格
        String[] title = new String[]{"科研单位", "工号", "项目编号", "项目名称", "申请人", "电话", "邮箱", "申请日期", "通过日期"};

        String[][] data = new String[briefTeachers.size()][];
        for (int i = 0; i < briefTeachers.size(); i++) {
            data[i] = new String[9];
            data[i][0] = briefTeachers.get(i).getDepartment();
            data[i][1] = briefTeachers.get(i).getTusername();
            String onlyid = "zscq" + briefTeachers.get(i).getSuredate() + String.valueOf(briefTeachers.get(i).getTid());
            onlyid = onlyid.replaceAll("-", "");
            data[i][2] = onlyid;

            data[i][3] = briefTeachers.get(i).getTname();
            data[i][4] = briefTeachers.get(i).getTinventionname();
            data[i][5] = briefTeachers.get(i).getTphone();
            data[i][6] = briefTeachers.get(i).getTemail();
            data[i][7] = briefTeachers.get(i).getAdate();
            data[i][8] = briefTeachers.get(i).getSuredate();
//            data[i][6] = sf.format(briefTeachers.get(i).getAdate());
//            data[i][7] = sf.format(briefTeachers.get(i).getSuredate());
        }

        //创建文件名
        String fileName = user.getDepartment() + "-" + new SimpleDateFormat("yyyy-MM-dd hh-mm-ss").format(new Date()) + "-通过审核项目名单.xls";

        HSSFWorkbook wb = ExcelUtils.getHSSFWorkbook("汇总表格", title, data, null);

        //响应到客户端
        try {
            this.setResponseHeader(response, fileName);
            OutputStream os = response.getOutputStream();
            wb.write(os);
            os.flush();
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 发送响应流方法
     */
    public void setResponseHeader(HttpServletResponse response, String fileName) {
        try {
            try {
                fileName = new String(fileName.getBytes(), "ISO8859-1");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            response.setContentType("application/octet-stream;charset=ISO8859-1");
            response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
            response.addHeader("Pargam", "no-cache");
            response.addHeader("Cache-Control", "no-cache");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    @GetMapping(value = "/translateDoc")
    public void translateDoc(String tid, HttpServletResponse response) throws IOException {

        TeacherDoc teacherDoc = teacherDocService.selectTeacherDocSingle(tid);

        HashMap<String, String> hashMap = Translate.TeacherToMap(teacherDoc);


        InputStream fin = null;
        ServletOutputStream out = null;
        File file = null;
        try {
            Freemark freemark = new Freemark();
            file = freemark.UseFreemark(hashMap, filefjPath);
            if (file.isFile()) {
                log.info("存在");
//                System.out.println("存在");
            } else {
                log.info("不存在");
//                System.out.println("不存在");
            }
            // 调用工具类的createDoc方法生成Word文档  
            fin = new FileInputStream(file);
            response.setCharacterEncoding("utf-8");
            response.setContentType("application/msword");
            // 设置浏览器以下载的方式处理该文件名  
//            String fileName = title+ DateUtil.formatDateDetailTime(new Date()) + ".doc";
            String fileName = file.getName();
            response.setHeader("Content-Disposition", "attachment;filename="
                    .concat(String.valueOf(URLEncoder.encode(fileName, "UTF-8"))));
            out = response.getOutputStream();
            byte[] buffer = new byte[1024]; // 缓冲区  
            int bytesToRead = -1;
// 通过循环将读入的Word文件的内容输出到浏览器中  
            while ((bytesToRead = fin.read(buffer)) != -1) {
                out.write(buffer, 0, bytesToRead);
            }
        } finally {
            if (fin != null) fin.close();
            if (out != null) out.close();

            if (file != null) file.delete(); // 删除临时文件  
        }

    }


    @GetMapping(value = "/translatePdf")
    public void translatePdf(String tid, HttpServletResponse response) throws IOException {
        TeacherDoc teacherDoc = teacherDocService.selectTeacherDocSingle(tid);
        Map<String, String> hashMap = Translate.TeacherToMap(teacherDoc);
        InputStream fin = null;
        ServletOutputStream out = null;
        File file = null;
        try {
            String filename = pdfUtils.createPDF(hashMap, filefjPath);
            file = new File(filename);
            if (file.isFile()) {
                log.info("存在");
            } else {
                log.info("不存在");
            }
            // 调用工具类的createDoc方法生成Word文档  
            fin = new FileInputStream(file);
            response.setCharacterEncoding("utf-8");
            response.setContentType("application/force-download");
            // 设置浏览器以下载的方式处理该文件名  
//            String fileName = title+ DateUtil.formatDateDetailTime(new Date()) + ".doc";
            String fileName = file.getName();
            response.setHeader("Content-Disposition", "attachment;filename="
                    .concat(String.valueOf(URLEncoder.encode(fileName, "UTF-8"))));
            out = response.getOutputStream();
            byte[] buffer = new byte[1024]; // 缓冲区  
            int bytesToRead = -1;
// 通过循环将读入的Word文件的内容输出到浏览器中  
            while ((bytesToRead = fin.read(buffer)) != -1) {
                out.write(buffer, 0, bytesToRead);
            }
        } finally {
            if (fin != null) fin.close();
            if (out != null) out.close();

            if (file != null) file.delete();
            // 删除临时文件  
        }

    }

    @GetMapping(value = "/ExcelDownloadByPath")
    public void ExcelDownloadByPath(String fileName, HttpServletResponse response) throws IOException {
        InputStream fin = null;
        ServletOutputStream out = null;
        File file = null;
        try {
            file = new File(fileName);
            // 调用工具类的createDoc方法生成Word文档  
            fin = new FileInputStream(file);
            response.setCharacterEncoding("utf-8");
            response.setContentType("application/force-download");
            // 设置浏览器以下载的方式处理该文件名  
//            String fileName = file.getName();
            response.setHeader("Content-Disposition", "attachment;filename=".concat(String.valueOf(URLEncoder.encode(file.getName(), "UTF-8"))));
//                    .concat(String.valueOf(URLEncoder.encode(fileName, "UTF-8") )  )    );
            out = response.getOutputStream();
            byte[] buffer = new byte[1024]; // 缓冲区  
            int bytesToRead = -1;
            while ((bytesToRead = fin.read(buffer)) != -1) {
                out.write(buffer, 0, bytesToRead);
            }
        } finally {
            if (fin != null) fin.close();
            if (out != null) out.close();
            if (file != null) file.delete();
        }
    }


    @ResponseBody
    @PostMapping("/oldDataImport")
    public Result oldDataImport(@RequestParam(value = "file") MultipartFile file,
                                @RequestParam(value = "filetype") String fileType,
                                @RequestParam(value = "excelTypeFile") String excelTypeFile) throws Exception {
        InputStream in = file.getInputStream();
        log.info(excelTypeFile);
        if (excelTypeFile.equals("会议论文")) {
            EasyExcel.read(file.getInputStream(), Hylw.class, new UploadHylwListener(hylwDao)).sheet().doRead();
        } else if (excelTypeFile.equals("期刊论文")) {
            EasyExcel.read(file.getInputStream(), Qklw.class, new UploadQklwListener(qklwDao)).sheet().doRead();
        } else if (excelTypeFile.equals("学术著作")) {
            EasyExcel.read(file.getInputStream(), Xszz.class, new UploadXszzListener(xszzDao)).sheet().doRead();
        } else if (excelTypeFile.equals("获批项目")) {
            EasyExcel.read(file.getInputStream(), Hpxm.class, new UploadHpxmListener(hpxmDao)).sheet().doRead();
        } else if (excelTypeFile.equals("结项验收")) {
            EasyExcel.read(file.getInputStream(), Jxys.class, new UploadJxysListener(jxysDao)).sheet().doRead();
        } else if (excelTypeFile.equals("横向立项")) {
            EasyExcel.read(file.getInputStream(), Hxlx.class, new UploadHxlxListener(hxlxDao)).sheet().doRead();
        } else if (excelTypeFile.equals("横向结项")) {
            EasyExcel.read(file.getInputStream(), Hxxm.class, new UploadHxxmListener(hxxmDao)).sheet().doRead();
        } else if (excelTypeFile.equals("软件著作")) {
            EasyExcel.read(file.getInputStream(), Rjzz.class, new UploadRjzzListener(rjzzDao)).sheet().doRead();
        } else if (excelTypeFile.equals("获批专利")) {
            EasyExcel.read(file.getInputStream(), Hpzl.class, new UploadHpzlListener(hpzlDao)).sheet().doRead();
        } else if (excelTypeFile.equals("学术称号")) {
            EasyExcel.read(file.getInputStream(), Xsch.class, new UploadXschListener(xschDao)).sheet().doRead();
        } else if (excelTypeFile.equals("学术交流")) {
            EasyExcel.read(file.getInputStream(), Xsjl.class, new UploadXsjlListener(xsjlDao)).sheet().doRead();
        } else if (excelTypeFile.equals("获批奖励")) {
            EasyExcel.read(file.getInputStream(), Hpjl.class, new UploadHpjlListener(hpjlDao)).sheet().doRead();
        } else if (excelTypeFile.equals("音美获奖")) {
            EasyExcel.read(file.getInputStream(), Ymhj.class, new UploadYmhjListener(ymhjDao)).sheet().doRead();
        } else if (excelTypeFile.equals("体育获奖")) {
            EasyExcel.read(file.getInputStream(), Tyhj.class, new UploadTyhjListener(tyhjDao)).sheet().doRead();
        } else if (excelTypeFile.equals("智库建设")) {
            EasyExcel.read(file.getInputStream(), Zkjs.class, new UploadZkjsListener(zkjsDao)).sheet().doRead();
        } else if (excelTypeFile.equals("成果转化")) {
            EasyExcel.read(file.getInputStream(), Cgzh.class, new UploadCgzhListener(cgzhDao)).sheet().doRead();
        } else {
            return new Result(1, "插入失败");
        }
        return new Result(1, "插入成功");
    }


    //把文件查出来 然后 上传 上去 然后删除掉

    @ResponseBody
    @RequestMapping(value = "retransmission", method = RequestMethod.POST)
    public Result retransmission(@RequestParam(value = "file") MultipartFile file,
                                 @RequestParam(value = "fjscname", defaultValue = "-1", required = false) String fjscname,
                                 @RequestParam("type") String type, @RequestParam("number") String number,
                                 @RequestParam("name") String name, @RequestParam("department") String department,
                                 @Param("id") String id) {

        String uuid = UUID.randomUUID().toString().replace("-", "");
        String muludir = filefjscPath + department + "/" + number + name;
        File fp = new File(muludir);


        if (!fp.exists()) {
            fp.mkdirs();
        }
        String fileRandomName = department + "/" + number + name + "/" + uuid + file.getOriginalFilename();
        String filename = filefjscPath + fileRandomName;
        File desFile = new File(filename);
        int flag = 1;
        try {
            file.transferTo(desFile);
            log.info("上传成功:" + filename);

            //更改数据库信息
            if (type.equals("会议论文")) {
                hylwDao.updateFjscName(fileRandomName, id);
            } else if (type.equals("期刊论文")) {
                qklwDao.updateFjscName(fileRandomName,id);
            }else if (type.equals("学术著作")) {
                xszzDao.updateFjscName(fileRandomName,id);
            }else if (type.equals("横向立项")) {
                hxlxDao.updateFjscName(fileRandomName,id);
            }else if (type.equals("横向结项")) {
                hxxmDao.updateFjscName(fileRandomName,id);
            }else if (type.equals("获批项目")) {
                hpxmDao.updateFjscName(fileRandomName,id);
            }else if (type.equals("结项验收")) {
                jxysDao.updateFjscName(fileRandomName,id);
            }else if (type.equals("软件著作")) {
                rjzzDao.updateFjscName(fileRandomName,id);
            }else if (type.equals("获批专利")) {
                hpzlDao.updateFjscName(fileRandomName,id);
            }else if (type.equals("学术称号")) {
                xschDao.updateFjscName(fileRandomName,id);
            }else if (type.equals("学术交流")) {
                xsjlDao.updateFjscName(fileRandomName,id);
            }else if (type.equals("获批奖励")) {
                hpjlDao.updateFjscName(fileRandomName,id);
            }else if (type.equals("音美获奖")) {
                ymhjDao.updateFjscName(fileRandomName,id);
            }else if (type.equals("体育获奖")) {
                tyhjDao.updateFjscName(fileRandomName,id);
            }else if (type.equals("智库建设")) {
                zkjsDao.updateFjscName(fileRandomName,id);
            }else if (type.equals("成果转化")) {
                cgzhDao.updateFjscName(fileRandomName,id);
            }

            File oldfile = new File(filefjscPath + fjscname);
            if (oldfile.exists()) {
                oldfile.delete();
                //删除老文件
            } else {
                flag = 0;
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (flag == 1) {
                return new Result(1, "存在旧文件，已删除后添加新文件");
            } else {
                return new Result(1, "原文件为空或无法寻到，已直接更新文件");
            }
        }
    }




}
