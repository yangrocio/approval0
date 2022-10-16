package com.aynu.controller.teachercontroller;

import com.aynu.bean.*;
import com.aynu.controller.UploadController;
import com.aynu.dto.Result;
import com.aynu.service.*;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * @author susuper
 * @version 1.0
 * 教师页面跳转部分  包含 期刊论文  会议论文  横向项目 学术著作
 * @date 2020/9/10 18:08
 */
@Controller
@RequestMapping(value = "/tjump")
@Slf4j
public class TJumpController {
    @Autowired
    HylwService hylwService;
    @Autowired
    QklwService qklwService;
    @Autowired
    XszzService xszzService;
    @Autowired
    HxxmService hxxmService;

    @Autowired
    UploadController uploadController;


    //跳转 界面 并且 查询出来数据 负责展示出来
    @GetMapping(value = "gohylwpage")
    public String gohylwpage(HttpSession session, Model model,
                             @RequestParam(required = false, defaultValue = "1", value = "pageNum") Integer pageNum,
                             @RequestParam(defaultValue = "15", value = "pageSize") Integer pageSize,
                             @RequestParam(defaultValue = "-1", value = "start") String start,
                             @RequestParam(defaultValue = "-1", value = "end") String end,
                             @RequestParam(defaultValue = "-1", value = "judgestatus") String judgestatus) {
        UserInfo user = (UserInfo) session.getAttribute("USER_SESSION");
        if (!user.getRoletype().equals("1")){
            return "pages/test";
        }
        //分页工具：先PageHelper.startPage(1, 10)开始分页，再selectlist查询数据库的时候会自动加上limit 1，10，
        // 最后封装成PageInfo的时候会自动带上页码、页大小、总数等
        //这里的意思就是 将分页信息保存在当前线程中
        PageHelper.startPage(pageNum, pageSize);
        try {
            List<Hylw> hylwList = hylwService.selectHylwByDetail(user.getDepartment(), user.getNumber(),"-1", start, end, judgestatus, "1");
            PageInfo<Hylw> pageInfo = new PageInfo<Hylw>(hylwList, pageSize);
            //4.使用model/map/modelandview等带回前端
            model.addAttribute("pageInfo", pageInfo);
            if (!start.equals("-1")) {
                model.addAttribute("start", start);
            }
            if (!end.equals("-1")) {
                model.addAttribute("end", end);
            }
            model.addAttribute("judgestatus", judgestatus);
        } finally {
            PageHelper.clearPage(); //清理 ThreadLocal 存储的分页参数,保证线程安全
        }

        return "teacher/teacherhylw";
    }

    @GetMapping(value = "gohylwwrite")
    public String gohylwwrite(HttpSession session, Model model) {
        UserInfo user = (UserInfo) session.getAttribute("USER_SESSION");
        if (!user.getRoletype().equals("1")){
            return "pages/test";
        }
        return "teacher/teacherhylwwrite";
    }

    @PostMapping(value = "fileup")
    @ResponseBody
    public String fileup(@RequestParam(value = "fjsc") MultipartFile file) {
        //这里 设置一下把文件给存储 下来  然后 返回存储的文件位置
        return file.getOriginalFilename();
    }

    @PostMapping(value = "puthylw")
    @ResponseBody
    public Result putHylw(@RequestBody Hylw hylw, HttpSession session) {
        UserInfo user = (UserInfo) session.getAttribute("USER_SESSION");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String systime = LocalDate.now().format(formatter); // get the current date
//        hylw.setDepartment(user.getDepartment()).setName(user.getName()).setNumber(user.getNumber()).setSystime(systime);
        hylw.setDepartment(user.getDepartment());
        hylw.setName(user.getName());
        hylw.setNumber(user.getNumber());
        hylw.setSystime(systime);
        boolean flag = hylwService.insertHylw(hylw);
        if (flag) {
            return new Result(1, "添加成功");
        }
        return new Result(0, "添加失败");
    }

    @PostMapping(value = "deletehylw")
    @ResponseBody
    public Result deletehylw(@RequestBody List<String> list, HttpSession session) {
        UserInfo user = (UserInfo) session.getAttribute("USER_SESSION");
        if (list.isEmpty()) {
            return new Result(1, "所选可删除内容为空");
        }
        hylwService.deleteFjscByList(list);
        boolean flag = hylwService.deleteHylw(list, user.getNumber(), user.getDepartment());
        //先去找一下 干脆 查询出来  根据审核状态 进行判断 是否可以删除 放在前端处理？
        if (flag) {
            return new Result(1, "删除成功");
        } else {
            return new Result(0, "删除失败");
        }
    }

    @PostMapping(value = "hylwshow")
    @ResponseBody
    public Hylw hylwshow(String id, HttpSession session) {
        UserInfo user = (UserInfo) session.getAttribute("USER_SESSION");
        Hylw hylw = hylwService.showHylw(user.getDepartment(),user.getNumber(), id);
        return hylw;
    }

    @PostMapping(value = "hylwupdate")
    @ResponseBody
    public Result hylwupdate(@RequestBody Hylw hylw, HttpSession session) {
        UserInfo user = (UserInfo) session.getAttribute("USER_SESSION");
        //先去查出来  根据 id，如果有就删除 没有就不删除

        if (hylw.getFjsc() != null) {
            List<String> list = new ArrayList<>();
            list.add((hylw.getHid().toString()));
            hylwService.deleteFjscByList(list);
        }
        hylw.setDepartment(user.getDepartment());
        hylw.setName(user.getName());
        hylw.setNumber(user.getNumber());
        hylw.setSystime(null);


        boolean flag = hylwService.updateHylw(hylw);
        if (flag) {
            return new Result(1, "更新成功");
        } else {
            return new Result(0, "更新失败");
        }
    }

    @PostMapping(value = "downloadhylwfjsc")
    @ResponseBody
    public Result downloadhylwfjsc(@RequestBody List<String> list) {
        String zipFileName = hylwService.downloadhylwZipFjsc(list);
        return new Result(1, zipFileName);
    }


    @GetMapping(value = "hylwexportall")
    public void hylwexportall(HttpSession session, HttpServletResponse response) {
        UserInfo user = (UserInfo) session.getAttribute("USER_SESSION");
        String department = user.getDepartment();
        String number = user.getNumber();
        String fileName = department + "-" + number + "-" + user.getName() + "-会议论文通过审核" + UUID.randomUUID().toString().replaceAll("-", "") + ".xls";
        HSSFWorkbook wb = hylwService.exporthylwExcel(department, number, user.getRoletype());
        //响应到客户端
        try {
            uploadController.setResponseHeader(response, fileName);
            OutputStream os = response.getOutputStream();
            wb.write(os);
            os.flush();
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                wb.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }



    //需要 传递过来 用session直接 查  需要传递过来 是全部，还是个体
    @PostMapping(value = "hylwExcelJson")
    @ResponseBody
    public Result hylwExcelJson(HttpSession session, @RequestBody Map<String, Object> mapdata) {
        List<String> params = (List<String>) mapdata.get("params");
        if (params.size()==0){
            return new Result(0,"请务必选择列参数");
        }

        UserInfo user = (UserInfo) session.getAttribute("USER_SESSION");
        String start = (String) mapdata.get("startExcelTime");
        String end = (String) mapdata.get("endExcelTime");
        String roletype = user.getRoletype();
        List<Map<Object, Object>> map = null;
        String fileName = null;
        if (roletype.equals("1")) {
            //传递过去 number  department  params 传过去
            fileName = hylwService.getExcelJson(user.getDepartment(), user.getNumber(),"-1", start, end, "3", params);
        } else if (roletype.equals("2")) {
            String name = (String) mapdata.get("name");  //其实传递的name
            fileName = hylwService.getExcelJson(user.getDepartment(),"-1", name, start, end, "3", params);
        } else if (roletype.equals("3")) {
            String department = (String) mapdata.get("department");
            String name = (String) mapdata.get("name");

            fileName = hylwService.getExcelJson(department, "-1",name, start, end, "3", params);
        }
        return new Result(1, fileName);
    }


    //期刊论文
    @GetMapping(value = "goqklwpage")
    public String goqklwpage(HttpSession session, Model model, @RequestParam(required = false, defaultValue = "1", value = "pageNum") Integer pageNum,
                             @RequestParam(defaultValue = "15", value = "pageSize") Integer pageSize,
                             @RequestParam(defaultValue = "-1", value = "start") String start,
                             @RequestParam(defaultValue = "-1", value = "end") String end,
                             @RequestParam(defaultValue = "-1", value = "judgestatus") String judgestatus) {
        UserInfo user = (UserInfo) session.getAttribute("USER_SESSION");
        if (!user.getRoletype().equals("1")){
            return "pages/test";
        }
        //分页工具：先PageHelper.startPage(1, 10)开始分页，再selectlist查询数据库的时候会自动加上limit 1，10，
        // 最后封装成PageInfo的时候会自动带上页码、页大小、总数等
        //这里的意思就是 将分页信息保存在当前线程中
        //PageHelper.startPage(pageNum, pageSize);

        //1.引入分页插件,pageNum是第几页，pageSize是每页显示多少条,默认查询总数count
        PageHelper.startPage(pageNum, pageSize);
        //2.紧跟的查询就是一个分页查询-必须紧跟.后面的其他查询不会被分页，除非再次调用PageHelper.startPage
        try {
            List<Qklw> qklwList = qklwService.selectQklwByDetail(user.getDepartment(),  user.getNumber(),"-1",   start, end, judgestatus, "1");
            //3.使用PageInfo包装查询后的结果,5是连续显示的条数,结果list类型是Page<E>
            PageInfo<Qklw> pageInfo = new PageInfo<Qklw>(qklwList, pageSize);
            //4.使用model/map/modelandview等带回前端
            model.addAttribute("pageInfo", pageInfo);
            if (!start.equals("-1")) {
                model.addAttribute("start", start);
            }
            if (!end.equals("-1")) {
                model.addAttribute("end", end);
            }
            if (!judgestatus.equals("-1")) {
                model.addAttribute("judgestatus", judgestatus);
            }
        } finally {
            PageHelper.clearPage(); //清理 ThreadLocal 存储的分页参数,保证线程安全
            //手动清理ThreadLocal存储的分页参数
        }

        return "teacher/teacherqklw";
    }

    @GetMapping(value = "goqklwwrite")
    public String goqklwwrite(HttpSession session, Model model) {
        UserInfo user = (UserInfo) session.getAttribute("USER_SESSION");
        if (!user.getRoletype().equals("1")){
            return "pages/test";
        }
        return "teacher/teacherqklwwrite";
    }

    @PostMapping(value = "putqklw")
    @ResponseBody
    public Result putqklw(@RequestBody Qklw qklw, HttpSession session) {
        UserInfo user = (UserInfo) session.getAttribute("USER_SESSION");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String systime = LocalDate.now().format(formatter); // get the current date
        qklw.setDepartment(user.getDepartment());
        qklw.setName(user.getName());
        qklw.setNumber(user.getNumber());
        qklw.setSystime(systime);
        boolean flag = qklwService.insertQklw(qklw);
        if (flag) {
            return new Result(1, "添加成功");
        }
        return new Result(0, "添加失败");
    }

    @PostMapping(value = "deleteqklw")
    @ResponseBody
    public Result deleteqklw(@RequestBody List<String> list, HttpSession session) {
        UserInfo user = (UserInfo) session.getAttribute("USER_SESSION");
        if (list.isEmpty()) {
            return new Result(1, "所选可删除内容为空");
        }
        qklwService.deleteFjscByList(list);
        boolean flag = qklwService.deleteQklw(list, user.getNumber(), user.getDepartment());
        if (flag) {
            return new Result(1, "删除成功");
        } else {
            return new Result(0, "删除失败");
        }
    }

    @PostMapping(value = "qklwshow")
    @ResponseBody
    public Qklw qklwshow(String id, HttpSession session) {
        UserInfo user = (UserInfo) session.getAttribute("USER_SESSION");
        Qklw qklw = qklwService.showQklw(user.getDepartment(), user.getNumber(), id);
        return qklw;
    }

    @PostMapping(value = "qklwupdate")
    @ResponseBody
    public Result qklwupdate(@RequestBody Qklw qklw, HttpSession session) {
        UserInfo user = (UserInfo) session.getAttribute("USER_SESSION");
        if (qklw.getFjsc() != null) {
            List<String> list = new ArrayList<>();
            list.add((qklw.getQid().toString()));
            qklwService.deleteFjscByList(list);
        }

        qklw.setDepartment(user.getDepartment());
        qklw.setName(user.getName());
        qklw.setNumber(user.getNumber());

//        if (qklw.getSystime() == null) {
//            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//            String systime = LocalDate.now().format(formatter); // get the current date
//            qklw.setSystime(systime);
//        } else if (qklw.getSystime().equals("-1")) {
            qklw.setSystime(null);
//        }

        boolean flag = qklwService.updateQklw(qklw);
        if (flag) {
            return new Result(1, "更新成功");
        } else {
            return new Result(0, "更新失败");
        }
    }


    @GetMapping(value = "qklwexportall")
    public void qklwexportall(HttpSession session, HttpServletResponse response) {
        UserInfo user = (UserInfo) session.getAttribute("USER_SESSION");
        String department = user.getDepartment();
        String number = user.getNumber();
        String fileName = department + "-" + number + "-" + user.getName() + "-学术著作通过审核" + UUID.randomUUID().toString().replaceAll("-", "") + ".xls";
        HSSFWorkbook wb = qklwService.exportqklwExcel(department, number, user.getRoletype());
        //响应到客户端
        try {
            uploadController.setResponseHeader(response, fileName);
            OutputStream os = response.getOutputStream();
            wb.write(os);
            os.flush();
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                wb.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @PostMapping(value = "downloadqklwfjsc")
    @ResponseBody
    public Result downloadqklwfjsc(@RequestBody List<String> list) {
        String zipFileName = qklwService.downloadqklwZipFjsc(list);
        return new Result(1, zipFileName);
    }

    @PostMapping(value = "qklwExcelJson")
    @ResponseBody
    public Result qklwExcelJson(HttpSession session, @RequestBody Map<String, Object> mapdata) {
        List<String> params = (List<String>) mapdata.get("params");
        if (params.size()==0){
            return new Result(0,"请务必选择列参数");
        }
        UserInfo user = (UserInfo) session.getAttribute("USER_SESSION");
        String start = (String) mapdata.get("startExcelTime");
        String end = (String) mapdata.get("endExcelTime");
        String roletype = user.getRoletype();
        List<Map<Object, Object>> map = null;
        String fileName = null;
        if (roletype.equals("1")) {
            //传递过去 number  department  params 传过去
            fileName = qklwService.getExcelJson(user.getDepartment(), user.getNumber(), "-1",start, end, "3", params);
        } else if (roletype.equals("2")) {
            String name = (String) mapdata.get("name");
            fileName = qklwService.getExcelJson(user.getDepartment(),"-1", name, start, end, "3", params);
        } else if (roletype.equals("3")) {
            String department = (String) mapdata.get("department");
            String name = (String) mapdata.get("name");
            fileName = qklwService.getExcelJson(department,"-1", name, start, end, "3", params);
        }
        return new Result(1, fileName);
    }

    //学术著作
    @GetMapping(value = "goxszzpage")
    public String goxszz(HttpSession session, Model model, @RequestParam(required = false, defaultValue = "1", value = "pageNum") Integer pageNum,
                         @RequestParam(defaultValue = "15", value = "pageSize") Integer pageSize,
                         @RequestParam(defaultValue = "-1", value = "start") String start,
                         @RequestParam(defaultValue = "-1", value = "end") String end,
                         @RequestParam(defaultValue = "-1", value = "judgestatus") String judgestatus) {
        UserInfo user = (UserInfo) session.getAttribute("USER_SESSION");
        if (!user.getRoletype().equals("1")){
            return "pages/test";
        }
        PageHelper.startPage(pageNum, pageSize);
        try {
            List<Xszz> xszzList = xszzService.selectXszzByDetail(user.getDepartment(), user.getNumber(),"-1", start, end, judgestatus, "1");
            PageInfo<Xszz> pageInfo = new PageInfo<Xszz>(xszzList, pageSize);
            model.addAttribute("pageInfo", pageInfo);
            if (!start.equals("-1")) {
                model.addAttribute("start", start);
            }
            if (!end.equals("-1")) {
                model.addAttribute("end", end);
            }
            if (!judgestatus.equals("-1")) {
                model.addAttribute("judgestatus", judgestatus);
            }
        } finally {
            PageHelper.clearPage(); //清理 ThreadLocal 存储的分页参数,保证线程安全
            //手动清理ThreadLocal存储的分页参数
        }

        return "teacher/teacherxszz";
    }

    @GetMapping(value = "goxszzwrite")
    public String goxszzwrite(HttpSession session, Model model) {
        UserInfo user = (UserInfo) session.getAttribute("USER_SESSION");
        if (!user.getRoletype().equals("1")){
            return "pages/test";
        }
        return "teacher/teacherxszzwrite";
    }

    @PostMapping(value = "putxszz")
    @ResponseBody
    public Result putxszz(@RequestBody Xszz xszz, HttpSession session) {
        UserInfo user = (UserInfo) session.getAttribute("USER_SESSION");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String systime = LocalDate.now().format(formatter); // get the current date
//        xszz.setDepartment(user.getDepartment()).setName(user.getName()).setNumber(user.getNumber()).setSystime(systime);
//        System.out.println(xszz);

        xszz.setDepartment(user.getDepartment());
        xszz.setName(user.getName());
        xszz.setNumber(user.getNumber());
        xszz.setSystime(systime);


        boolean flag = xszzService.insertXszz(xszz);
        if (flag) {
            return new Result(1, "添加成功");
        }
        return new Result(0, "添加失败");

    }

    @PostMapping(value = "deletexszz")
    @ResponseBody
    public Result deletexszz(@RequestBody List<String> list, HttpSession session) {
        UserInfo user = (UserInfo) session.getAttribute("USER_SESSION");
        if (list.isEmpty()) {
            return new Result(1, "所选可删除内容为空");
        }
        xszzService.deleteFjscByList(list);
        boolean flag = xszzService.deleteXszz(list, user.getNumber(), user.getDepartment());
        if (flag) {
            return new Result(1, "删除成功");
        } else {
            return new Result(0, "删除失败");
        }
    }

    @GetMapping(value = "xszzexportall")
    public void xszzexportall(HttpSession session, HttpServletResponse response) {
        UserInfo user = (UserInfo) session.getAttribute("USER_SESSION");
        String department = user.getDepartment();
        String number = user.getNumber();
        String fileName = department + "-" + number + "-" + user.getName() + "-学术著作通过审核" + UUID.randomUUID().toString().replaceAll("-", "") + ".xls";
        HSSFWorkbook wb = xszzService.exportxszzExcel(department, number, user.getRoletype());
        //响应到客户端
        try {
            uploadController.setResponseHeader(response, fileName);
            OutputStream os = response.getOutputStream();
            wb.write(os);
            os.flush();
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                wb.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @PostMapping(value = "downloadxszzfjsc")
    @ResponseBody
    public Result downloadxszzfjsc(@RequestBody List<String> list) {
        String zipFileName = xszzService.downloadxszzZipFjsc(list);
        return new Result(1, zipFileName);
    }

    @PostMapping(value = "xszzshow")
    @ResponseBody
    public Xszz xszzshow(String id, HttpSession session) {
        UserInfo user = (UserInfo) session.getAttribute("USER_SESSION");
        System.out.println(id);
        Xszz xszz = xszzService.showXszz(user.getDepartment(), user.getNumber(), id);
        return xszz;
    }

    @PostMapping(value = "xszzupdate")
    @ResponseBody
    public Result xszzupdate(@RequestBody Xszz xszz, HttpSession session) {
        UserInfo user = (UserInfo) session.getAttribute("USER_SESSION");
        if (xszz.getFjsc() != null) {
            List<String> list = new ArrayList<>();
            list.add((xszz.getXid().toString()));
            xszzService.deleteFjscByList(list);
        }


        xszz.setDepartment(user.getDepartment());
        xszz.setName(user.getName());
        xszz.setNumber(user.getNumber());

//        if (xszz.getSystime() == null) {
//            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//            String systime = LocalDate.now().format(formatter); // get the current date
//            xszz.setSystime(systime);
//        } else if (xszz.getSystime().equals("-1")) {
            xszz.setSystime(null);
//        }

        boolean flag = xszzService.updateXszz(xszz);
        if (flag) {
            return new Result(1, "更新成功");
        } else {
            return new Result(0, "更新失败");
        }
    }

    @PostMapping(value = "xszzExcelJson")
    @ResponseBody
    public Result xszzExcelJson(HttpSession session, @RequestBody Map<String, Object> mapdata) {
        List<String> params = (List<String>) mapdata.get("params");
        if (params.size()==0){
            return new Result(0,"请务必选择列参数");
        }
        UserInfo user = (UserInfo) session.getAttribute("USER_SESSION");
        String start = (String) mapdata.get("startExcelTime");
        String end = (String) mapdata.get("endExcelTime");
        String roletype = user.getRoletype();
        List<Map<Object, Object>> map = null;
        String fileName = null;
        if (roletype.equals("1")) {
            //传递过去 number  department  params 传过去
            fileName = xszzService.getExcelJson(user.getDepartment(), user.getNumber(),"-1", start, end, "3", params);
        } else if (roletype.equals("2")) {
            String name = (String) mapdata.get("name");
            fileName = xszzService.getExcelJson(user.getDepartment(),"-1", name, start, end, "3", params);
        } else if (roletype.equals("3")) {
            String department = (String) mapdata.get("department");
            String name = (String) mapdata.get("name");
            fileName = xszzService.getExcelJson(department,"-1", name, start, end, "3", params);
        }
        return new Result(1, fileName);
    }


    //横向项目
    @GetMapping(value = "gohxxmpage")
    public String gohxxmpage(HttpSession session, Model model, @RequestParam(required = false, defaultValue = "1", value = "pageNum") Integer pageNum,
                             @RequestParam(defaultValue = "15", value = "pageSize") Integer pageSize,
                             @RequestParam(defaultValue = "-1", value = "start") String start,
                             @RequestParam(defaultValue = "-1", value = "end") String end,
                             @RequestParam(defaultValue = "-1", value = "judgestatus") String judgestatus) {
        UserInfo user = (UserInfo) session.getAttribute("USER_SESSION");
        if (!user.getRoletype().equals("1")){
            return "pages/test";
        }
        PageHelper.startPage(pageNum, pageSize);
        try {
            List<Hxxm> hxxmList = hxxmService.selectHxxmByDetail(user.getDepartment(), user.getNumber(), "-1",start, end, judgestatus, "1");
            PageInfo<Hxxm> pageInfo = new PageInfo<Hxxm>(hxxmList, pageSize);
            model.addAttribute("pageInfo", pageInfo);
            if (!start.equals("-1")) {
                model.addAttribute("start", start);
            }
            if (!end.equals("-1")) {
                model.addAttribute("end", end);
            }
            if (!judgestatus.equals("-1")) {
                model.addAttribute("judgestatus", judgestatus);
            }
        } finally {
            PageHelper.clearPage(); //清理 ThreadLocal 存储的分页参数,保证线程安全
        }

        return "teacher/teacherhxxm";
    }

    @GetMapping(value = "gohxxmwrite")
    public String gohxxmwrite(HttpSession session, Model model) {
        UserInfo user = (UserInfo) session.getAttribute("USER_SESSION");
        if (!user.getRoletype().equals("1")){
            return "pages/test";
        }
        return "teacher/teacherhxxmwrite";
    }

    @PostMapping(value = "puthxxm")
    @ResponseBody
    public Result puthxxm(@RequestBody Hxxm hxxm, HttpSession session) {
        UserInfo user = (UserInfo) session.getAttribute("USER_SESSION");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String systime = LocalDate.now().format(formatter); // get the current date
//        hxxm.setDepartment(user.getDepartment()).setName(user.getName()).setNumber(user.getNumber()).setSystime(systime);

        hxxm.setDepartment(user.getDepartment());
        hxxm.setName(user.getName());
        hxxm.setNumber(user.getNumber());
        hxxm.setSystime(systime);

        boolean flag = hxxmService.insertHxxm(hxxm);
        if (flag) {
            return new Result(1, "添加成功");
        }
        return new Result(0, "添加失败");
    }


    @PostMapping(value = "deletehxxm")
    @ResponseBody
    public Result deletehxxm(@RequestBody List<String> list, HttpSession session) {
        UserInfo user = (UserInfo) session.getAttribute("USER_SESSION");
        if (list.isEmpty()) {
            return new Result(1, "所选可删除内容为空");
        }
        hxxmService.deleteFjscByList(list);
        boolean flag = hxxmService.deleteHxxm(list, user.getNumber(), user.getDepartment());
        if (flag) {
            return new Result(1, "删除成功");
        } else {
            return new Result(0, "删除失败");
        }
    }

    @PostMapping(value = "hxxmshow")
    @ResponseBody
    public Hxxm hxxmshow(String id, HttpSession session) {
        UserInfo user = (UserInfo) session.getAttribute("USER_SESSION");
        Hxxm hxxm = hxxmService.showHxxm(user.getDepartment(), user.getNumber(), id);
        return hxxm;
    }

    @PostMapping(value = "hxxmupdate")
    @ResponseBody
    public Result hxxmupdate(@RequestBody Hxxm hxxm, HttpSession session) {
        UserInfo user = (UserInfo) session.getAttribute("USER_SESSION");
        if (hxxm.getFjsc() != null) {
            List<String> list = new ArrayList<>();
            list.add((hxxm.getHid().toString()));
            hxxmService.deleteFjscByList(list);
        }

        hxxm.setDepartment(user.getDepartment());
        hxxm.setName(user.getName());
        hxxm.setNumber(user.getNumber());

//        if (hxxm.getSystime() == null) {
//            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//            String systime = LocalDate.now().format(formatter); // get the current date
//            hxxm.setSystime(systime);
//        } else if (hxxm.getSystime().equals("-1")) {
            hxxm.setSystime(null);
//        }



//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//        String systime = LocalDate.now().format(formatter); // get the current date
//        hxxm.setDepartment(user.getDepartment()).setName(user.getName()).setNumber(user.getNumber()).setSystime(systime);




        boolean flag = hxxmService.updateHxxm(hxxm);
        if (flag) {
            return new Result(1, "更新成功");
        } else {
            return new Result(0, "更新失败");
        }
    }

    @PostMapping(value = "downloadhxxmfjsc")
    @ResponseBody
    public Result downloadhxxmfjsc(@RequestBody List<String> list) {
        String zipFileName = hxxmService.downloadhxxmZipFjsc(list);
        return new Result(1, zipFileName);
    }


    @GetMapping(value = "hxxmexportall")
    public void hxxmexportall(HttpSession session, HttpServletResponse response) {
        UserInfo user = (UserInfo) session.getAttribute("USER_SESSION");
        String department = user.getDepartment();
        String number = user.getNumber();
        String fileName = department + "-" + number + "-" + user.getName() + "-横向项目通过审核" + UUID.randomUUID().toString().replaceAll("-", "") + ".xls";
        HSSFWorkbook wb = hxxmService.exporthxxmExcel(department, number, user.getRoletype());
        //响应到客户端
        try {
            uploadController.setResponseHeader(response, fileName);
            OutputStream os = response.getOutputStream();
            wb.write(os);
            os.flush();
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                wb.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    @PostMapping(value = "hxxmExcelJson")
    @ResponseBody
    public Result hxxmExcelJson(HttpSession session, @RequestBody Map<String, Object> mapdata) {
        List<String> params = (List<String>) mapdata.get("params");
        if (params.size()==0){
            return new Result(0,"请务必选择列参数");
        }
        UserInfo user = (UserInfo) session.getAttribute("USER_SESSION");
        String start = (String) mapdata.get("startExcelTime");
        String end = (String) mapdata.get("endExcelTime");
        String roletype = user.getRoletype();
        List<Map<Object, Object>> map = null;
        String fileName = null;
        if (roletype.equals("1")) {
            //传递过去 number  department  params 传过去
            fileName = hxxmService.getExcelJson(user.getDepartment(), user.getNumber(), "-1",start, end, "3", params);
        } else if (roletype.equals("2")) {
            String name = (String) mapdata.get("name");
            fileName = hxxmService.getExcelJson(user.getDepartment(), "-1",name, start, end, "3", params);
        } else if (roletype.equals("3")) {
            String department = (String) mapdata.get("department");
            String name = (String) mapdata.get("name");

            fileName = hxxmService.getExcelJson(department, "-1",name, start, end, "3", params);
        }
        return new Result(1, fileName);
    }
}
