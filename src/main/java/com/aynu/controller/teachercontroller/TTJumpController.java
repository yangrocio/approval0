package com.aynu.controller.teachercontroller;

import com.aynu.bean.*;
import com.aynu.controller.UploadController;
import com.aynu.dto.Result;
import com.aynu.service.HpzlService;
import com.aynu.service.RjzzService;
import com.aynu.service.XschService;
import com.aynu.service.XsjlService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @author susuper
 * @version 1.0
 * @date 2020/9/11 20:23
 *软件著作  获批专利    学术称号  学术交流
 */

@Controller
@RequestMapping(value = "/ttjump")
@Slf4j
public  class TTJumpController {
    @Autowired
    RjzzService rjzzService;
    @Autowired
    HpzlService hpzlService;
    @Autowired
    XschService xschService;
    @Autowired
    XsjlService xsjlService;
    @Autowired
    UploadController uploadController;
    //软件 著作
    @GetMapping(value = "gorjzzpage")
    public String gorjzzpage(HttpSession session, Model model, @RequestParam(required = false, defaultValue = "1", value = "pageNum") Integer pageNum,
                             @RequestParam(defaultValue = "15", value = "pageSize") Integer pageSize,
                             @RequestParam(defaultValue = "-1", value = "start") String start,
                             @RequestParam(defaultValue = "-1", value = "end") String end,
                             @RequestParam(defaultValue = "-1", value = "judgestatus") String judgestatus){
        UserInfo user = (UserInfo) session.getAttribute("USER_SESSION");
        if (!user.getRoletype().equals("1")){
            return "pages/test";
        }
        PageHelper.startPage(pageNum, pageSize);
        try {
            List<Rjzz> rjzzList = rjzzService.selectRjzzByDetail(user.getDepartment(),user.getNumber(),"-1", start,end,judgestatus,"1");
            //3.使用PageInfo包装查询后的结果,5是连续显示的条数,结果list类型是Page<E>
            PageInfo<Rjzz> pageInfo = new PageInfo<Rjzz>(rjzzList, pageSize);
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
        }
        return "teacher/teacherrjzz";
    }

    @GetMapping(value = "gorjzzwrite")
    public String gorjzzwrite(HttpSession session, Model model){
        UserInfo user = (UserInfo) session.getAttribute("USER_SESSION");
        if (!user.getRoletype().equals("1")){
            return "pages/test";
        }
        return "teacher/teacherrjzzwrite";
    }


    @PostMapping(value = "putrjzz")
    @ResponseBody
    public Result putrjzz(@RequestBody Rjzz rjzz, HttpSession session){
        UserInfo user = (UserInfo) session.getAttribute("USER_SESSION");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String systime = LocalDate.now().format(formatter); // get the current date
//        rjzz.setDepartment(user.getDepartment()).setName(user.getName()).setNumber(user.getNumber()).setSystime(systime);

        rjzz.setDepartment(user.getDepartment());
        rjzz.setName(user.getName());
        rjzz.setNumber(user.getNumber());
        rjzz.setSystime(systime);

//




        System.out.println(rjzz);
        boolean flag = rjzzService.insertRjzz(rjzz);
        if (flag){
            return new Result(1,"添加成功");
        }
        return new Result(0,"添加失败");

    }

    @PostMapping(value = "deleterjzz")
    @ResponseBody
    public Result deleterjzz(@RequestBody List<String> list,HttpSession session){
        UserInfo user = (UserInfo) session.getAttribute("USER_SESSION");
        if (list.isEmpty()){
            return new Result(1,"所选可删除内容为空");
        }
        rjzzService.deleteFjscByList(list);
        boolean flag = rjzzService.deleteRjzz(list,user.getNumber(),user.getDepartment());
        //先去找一下 干脆 查询出来  根据审核状态 进行判断 是否可以删除 放在前端处理？
        if (flag){
            return new Result(1,"删除成功");
        }else{
            return new Result(0,"删除失败");
        }
    }

    @PostMapping(value = "rjzzshow")
    @ResponseBody
    public Rjzz rjzzshow(String id, HttpSession session){
        UserInfo user = (UserInfo) session.getAttribute("USER_SESSION");
        System.out.println(id);
        Rjzz rjzz = rjzzService.showRjzz(user.getDepartment(),user.getNumber(),id);
        return rjzz;
    }

    @PostMapping(value = "rjzzupdate")
    @ResponseBody
    public Result rjzzupdate(@RequestBody Rjzz rjzz, HttpSession session){
        UserInfo user = (UserInfo) session.getAttribute("USER_SESSION");
        if (rjzz.getFjsc()!=null){
            List<String> list = new ArrayList<>();
            list.add((rjzz.getRid().toString()));
            rjzzService.deleteFjscByList(list);
        }



        rjzz.setDepartment(user.getDepartment());
        rjzz.setName(user.getName());
        rjzz.setNumber(user.getNumber());
//        if (rjzz.getSystime() == null) {
//            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//            String systime = LocalDate.now().format(formatter); // get the current date
//            rjzz.setSystime(systime);
//        } else if (rjzz.getSystime().equals("-1")) {
            rjzz.setSystime(null);
//        }

        boolean flag = rjzzService.updateRjzz(rjzz);
        if (flag){
            return new Result(1,"更新成功");
        }else{
            return new Result(0,"更新失败");
        }
    }

    @PostMapping(value = "downloadrjzzfjsc")
    @ResponseBody
    public Result downloadrjzzfjsc(@RequestBody List<String> list) {
        String zipFileName = rjzzService.downloadrjzzZipFjsc(list);
        return new Result(1,zipFileName);
    }

    @GetMapping(value = "rjzzexportall")
    public void rjzzexportall(HttpSession session, HttpServletResponse response) {
        UserInfo user = (UserInfo) session.getAttribute("USER_SESSION");
        String department = user.getDepartment();
        String number = user.getNumber();
        String fileName =department + "-" + number +"-"+user.getName()+ "-软件著作通过审核" + UUID.randomUUID().toString().replaceAll("-", "") + ".xls";
        HSSFWorkbook wb = rjzzService.exportrjzzExcel(department, number, user.getRoletype());
        //响应到客户端
        try {
            uploadController.setResponseHeader(response, fileName);
            OutputStream os = response.getOutputStream();
            wb.write(os);
            os.flush();
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                wb.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @PostMapping(value = "rjzzExcelJson")
    @ResponseBody
    public Result rjzzExcelJson(HttpSession session, @RequestBody Map<String, Object> mapdata) {
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
            fileName = rjzzService.getExcelJson(user.getDepartment(), user.getNumber(),"-1",  start, end, "3", params);
        } else if (roletype.equals("2")) {
            String number = (String) mapdata.get("name");
            fileName = rjzzService.getExcelJson(user.getDepartment(), "-1", number, start, end, "3", params);
        } else if (roletype.equals("3")) {
            String department = (String) mapdata.get("department");
            String number = (String) mapdata.get("name");

            fileName = rjzzService.getExcelJson(department,"-1",  number, start, end, "3", params);
        }
        return new Result(1, fileName);
    }

    //获批专利
    @GetMapping(value = "gohpzlpage")
    public String gohpzlpage(HttpSession session, Model model, @RequestParam(required = false, defaultValue = "1", value = "pageNum") Integer pageNum,
                             @RequestParam(defaultValue = "15", value = "pageSize") Integer pageSize,
                             @RequestParam(defaultValue = "-1", value = "start") String start,
                             @RequestParam(defaultValue = "-1", value = "end") String end,
                             @RequestParam(defaultValue = "-1", value = "judgestatus") String judgestatus){
        UserInfo user = (UserInfo) session.getAttribute("USER_SESSION");
        if (!user.getRoletype().equals("1")){
            return "pages/test";
        }
        PageHelper.startPage(pageNum, pageSize);
        try {
            List<Hpzl> rjzzList = hpzlService.selectHpzlByDetail(user.getDepartment(),user.getNumber(),"-1", start,end,judgestatus,"1");
            PageInfo<Hpzl> pageInfo = new PageInfo<Hpzl>(rjzzList, pageSize);
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

        return "teacher/teacherhpzl";
    }

    @GetMapping(value = "gohpzlwrite")
    public String gohpzlwrite(HttpSession session, Model model){
        UserInfo user = (UserInfo) session.getAttribute("USER_SESSION");
        if (!user.getRoletype().equals("1")){
            return "pages/test";
        }
        return "teacher/teacherhpzlwrite";
    }


    @PostMapping(value = "puthpzl")
    @ResponseBody
    public Result puthpzl(@RequestBody Hpzl hpzl, HttpSession session){
        UserInfo user = (UserInfo) session.getAttribute("USER_SESSION");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String systime = LocalDate.now().format(formatter); // get the current date

        hpzl.setDepartment(user.getDepartment());
        hpzl.setName(user.getName());
        hpzl.setNumber(user.getNumber());
        hpzl.setSystime(systime);



        boolean flag = hpzlService.insertHpzl(hpzl);
        if (flag){
            return new Result(1,"添加成功");
        }
        return new Result(0,"添加失败");

    }

    @PostMapping(value = "deletehpzl")
    @ResponseBody
    public Result deletehpzl(@RequestBody List<String> list,HttpSession session){
        UserInfo user = (UserInfo) session.getAttribute("USER_SESSION");
        if (list.isEmpty()){
            return new Result(1,"所选可删除内容为空");
        }
        hpzlService.deleteFjscByList(list);
        boolean flag = hpzlService.deleteHpzl(list,user.getNumber(),user.getDepartment());
        //先去找一下 干脆 查询出来  根据审核状态 进行判断 是否可以删除 放在前端处理？
        if (flag){
            return new Result(1,"删除成功");
        }else{
            return new Result(0,"删除失败");
        }
    }

    @PostMapping(value = "hpzlshow")
    @ResponseBody
    public Hpzl hpzlshow(String id, HttpSession session){
        UserInfo user = (UserInfo) session.getAttribute("USER_SESSION");
        System.out.println(id);
        Hpzl hpzl = hpzlService.showHpzl(user.getDepartment(),user.getNumber(),id);
        return hpzl;
    }

    @PostMapping(value = "hpzlupdate")
    @ResponseBody
    public Result hpzlupdate(@RequestBody Hpzl hpzl, HttpSession session){
        UserInfo user = (UserInfo) session.getAttribute("USER_SESSION");
        if (hpzl.getFjsc()!=null){
            List<String> list = new ArrayList<>();
            list.add((hpzl.getHid().toString()));
            hpzlService.deleteFjscByList(list);
        }





        hpzl.setDepartment(user.getDepartment());
        hpzl.setName(user.getName());
        hpzl.setNumber(user.getNumber());


//        if (hpzl.getSystime() == null) {
//            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//            String systime = LocalDate.now().format(formatter); // get the current date
//            hpzl.setSystime(systime);
//        } else if (hpzl.getSystime().equals("-1")) {
            hpzl.setSystime(null);
//        }

        System.out.println(hpzl);
        boolean flag = hpzlService.updateHpzl(hpzl);
        if (flag){
            return new Result(1,"更新成功");
        }else{
            return new Result(0,"更新失败");
        }
    }

    @PostMapping(value = "downloadhpzlfjsc")
    @ResponseBody
    public Result downloadhpzlfjsc(@RequestBody List<String> list) {
        String zipFileName = hpzlService.downloadhpzlZipFjsc(list);
        return new Result(1,zipFileName);
    }

    @GetMapping(value = "hpzlexportall")
    public void hpzlexportall(HttpSession session, HttpServletResponse response) {
        UserInfo user = (UserInfo) session.getAttribute("USER_SESSION");
        String department = user.getDepartment();
        String number = user.getNumber();
        String fileName =department + "-" + number +"-"+user.getName()+ "-获批专利通过审核" + UUID.randomUUID().toString().replaceAll("-", "") + ".xls";
        HSSFWorkbook wb = hpzlService.exporthpzlExcel(department, number, user.getRoletype());
        //响应到客户端
        try {
            uploadController.setResponseHeader(response, fileName);
            OutputStream os = response.getOutputStream();
            wb.write(os);
            os.flush();
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                wb.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @PostMapping(value = "hpzlExcelJson")
    @ResponseBody
    public Result hpzlExcelJson(HttpSession session, @RequestBody Map<String, Object> mapdata) {
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
            fileName =hpzlService.getExcelJson(user.getDepartment(), user.getNumber(), "-1",start, end, "3", params);
        } else if (roletype.equals("2")) {
            String name = (String) mapdata.get("name");
            fileName = hpzlService.getExcelJson(user.getDepartment(), "-1",name, start, end, "3", params);
        } else if (roletype.equals("3")) {
            String department = (String) mapdata.get("department");
            String name = (String) mapdata.get("name");

            fileName = hpzlService.getExcelJson(department,"-1", name, start, end, "3", params);
        }
        return new Result(1, fileName);
    }
    //学术称号

    @GetMapping(value = "goxschpage")
    public String goxschpage(HttpSession session, Model model, @RequestParam(required = false, defaultValue = "1", value = "pageNum") Integer pageNum,
                             @RequestParam(defaultValue = "15", value = "pageSize") Integer pageSize,
                             @RequestParam(defaultValue = "-1", value = "start") String start,
                             @RequestParam(defaultValue = "-1", value = "end") String end,
                             @RequestParam(defaultValue = "-1", value = "judgestatus") String judgestatus){
        UserInfo user = (UserInfo) session.getAttribute("USER_SESSION");
        if (!user.getRoletype().equals("1")){
            return "pages/test";
        }
        PageHelper.startPage(pageNum, pageSize);
        //1.引入分页插件,pageNum是第几页，pageSize是每页显示多少条,默认查询总数count
        //2.紧跟的查询就是一个分页查询-必须紧跟.后面的其他查询不会被分页，除非再次调用PageHelper.startPage
        try {
            List<Xsch> xschList = xschService.selectXschByDetail(user.getDepartment(),user.getNumber(),"-1", start,end,judgestatus,"1");
            //3.使用PageInfo包装查询后的结果,5是连续显示的条数,结果list类型是Page<E>
            PageInfo<Xsch> pageInfo = new PageInfo<Xsch>(xschList, pageSize);
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
        }

        return "teacher/teacherxsch";
    }

    @GetMapping(value = "goxschwrite")
    public String goxschwrite(HttpSession session, Model model){
        UserInfo user = (UserInfo) session.getAttribute("USER_SESSION");
        if (!user.getRoletype().equals("1")){
            return "pages/test";
        }
        return "teacher/teacherxschwrite";
    }


    @PostMapping(value = "putxsch")
    @ResponseBody
    public Result putxsch(@RequestBody Xsch xsch, HttpSession session){
        UserInfo user = (UserInfo) session.getAttribute("USER_SESSION");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String systime = LocalDate.now().format(formatter); // get the current date



        xsch.setDepartment(user.getDepartment());
        xsch.setName(user.getName());
        xsch.setNumber(user.getNumber());
        xsch.setSystime(systime);

//        System.out.println(xsch);
        boolean flag = xschService.insertXsch(xsch);
        if (flag){
            return new Result(1,"添加成功");
        }
        return new Result(0,"添加失败");

    }


    @PostMapping(value = "deletexsch")
    @ResponseBody
    public Result deletexsch(@RequestBody List<String> list,HttpSession session){
        UserInfo user = (UserInfo) session.getAttribute("USER_SESSION");
        System.out.println(list);
        if (list.isEmpty()){
            return new Result(1,"所选可删除内容为空");
        }
        xschService.deleteFjscByList(list);
        boolean flag = xschService.deleteXsch(list,user.getNumber(),user.getDepartment());
        //先去找一下 干脆 查询出来  根据审核状态 进行判断 是否可以删除 放在前端处理？
        if (flag){
            return new Result(1,"删除成功");
        }else{
            return new Result(0,"删除失败");
        }
    }

    @PostMapping(value = "xschshow")
    @ResponseBody
    public Xsch xschshow(String id, HttpSession session){
        UserInfo user = (UserInfo) session.getAttribute("USER_SESSION");
        System.out.println(id);
        Xsch xsch = xschService.showXsch(user.getDepartment(),user.getNumber(),id);
        return xsch;
    }

    @PostMapping(value = "xschupdate")
    @ResponseBody
    public Result xschupdate(@RequestBody Xsch xsch, HttpSession session){
        UserInfo user = (UserInfo) session.getAttribute("USER_SESSION");
        if (xsch.getFjsc()!=null){
            List<String> list = new ArrayList<>();
            list.add((xsch.getXid().toString()));
            xschService.deleteFjscByList(list);
        }

        xsch.setDepartment(user.getDepartment());
        xsch.setName(user.getName());
        xsch.setNumber(user.getNumber());
//
//        if (xsch.getSystime() == null) {
//            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//            String systime = LocalDate.now().format(formatter); // get the current date
//            xsch.setSystime(systime);
//        } else if (xsch.getSystime().equals("-1")) {
            xsch.setSystime(null);
//        }


        boolean flag = xschService.updateXsch(xsch);
        if (flag){
            return new Result(1,"更新成功");
        }else{
            return new Result(0,"更新失败");
        }
    }

    @PostMapping(value = "downloadxschfjsc")
    @ResponseBody
    public Result downloadxschfjsc(@RequestBody List<String> list) {
        String zipFileName = xschService.downloadxschZipFjsc(list);
        return new Result(1,zipFileName);
    }


    @GetMapping(value = "xschexportall")
    public void xschexportall(HttpSession session, HttpServletResponse response) {
        UserInfo user = (UserInfo) session.getAttribute("USER_SESSION");
        String department = user.getDepartment();
        String number = user.getNumber();
        String fileName =department + "-" + number +"-"+user.getName()+ "-学术称号通过审核" + UUID.randomUUID().toString().replaceAll("-", "") + ".xls";
        HSSFWorkbook wb = xschService.exportxschExcel(department, number, user.getRoletype());
        //响应到客户端
        try {
            uploadController.setResponseHeader(response, fileName);
            OutputStream os = response.getOutputStream();
            wb.write(os);
            os.flush();
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                wb.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    @PostMapping(value = "xschExcelJson")
    @ResponseBody
    public Result xschExcelJson(HttpSession session, @RequestBody Map<String, Object> mapdata) {
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
            fileName = xschService.getExcelJson(user.getDepartment(), user.getNumber(),"-1", start, end, "3", params);
        }  else if (roletype.equals("2")) {
            String name = (String) mapdata.get("name");
            fileName = xschService.getExcelJson(user.getDepartment(),"-1", name, start, end, "3", params);
        } else if (roletype.equals("3")) {
            String department = (String) mapdata.get("department");
            String name = (String) mapdata.get("name");

            fileName = xschService.getExcelJson(department,"-1", name, start, end, "3", params);
        }
        return new Result(1, fileName);
    }
    //学术交流

    @GetMapping(value = "goxsjlpage")
    public String goxsjlpage(HttpSession session, Model model, @RequestParam(required = false, defaultValue = "1", value = "pageNum") Integer pageNum,
                             @RequestParam(defaultValue = "15", value = "pageSize") Integer pageSize,
                             @RequestParam(defaultValue = "-1", value = "start") String start,
                             @RequestParam(defaultValue = "-1", value = "end") String end,
                             @RequestParam(defaultValue = "-1", value = "judgestatus") String judgestatus){
        UserInfo user = (UserInfo) session.getAttribute("USER_SESSION");
        if (!user.getRoletype().equals("1")){
            return "pages/test";
        }
        PageHelper.startPage(pageNum, pageSize);
        try {
            List<Xsjl> xschList = xsjlService.selectXsjlByDetail(user.getDepartment(),user.getNumber(), "-1",start,end,judgestatus,"1");
            //3.使用PageInfo包装查询后的结果,5是连续显示的条数,结果list类型是Page<E>
            PageInfo<Xsjl> pageInfo = new PageInfo<Xsjl>(xschList, pageSize);
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
        }

        return "teacher/teacherxsjl";
    }

    @GetMapping(value = "goxsjlwrite")
    public String goxsjlwrite(HttpSession session, Model model){
        UserInfo user = (UserInfo) session.getAttribute("USER_SESSION");
        if (!user.getRoletype().equals("1")){
            return "pages/test";
        }
        return "teacher/teacherxsjlwrite";
    }


    @PostMapping(value = "putxsjl")
    @ResponseBody
    public Result putxsjl(@RequestBody Xsjl xsjl, HttpSession session){
        UserInfo user = (UserInfo) session.getAttribute("USER_SESSION");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String systime = LocalDate.now().format(formatter); // get the current date

        xsjl.setDepartment(user.getDepartment());
        xsjl.setName(user.getName());
        xsjl.setNumber(user.getNumber());
        xsjl.setSystime(systime);

//        xsjl.setDepartment(user.getDepartment()).setName(user.getName()).setNumber(user.getNumber()).setSystime(systime);
//        System.out.println(xsjl);
        boolean flag = xsjlService.insertXsjl(xsjl);
        if (flag){
            return new Result(1,"添加成功");
        }
        return new Result(0,"添加失败");

    }

    @PostMapping(value = "deletexsjl")
    @ResponseBody
    public Result deletexsjl(@RequestBody List<String> list,HttpSession session){
        UserInfo user = (UserInfo) session.getAttribute("USER_SESSION");
        if (list.isEmpty()){
            return new Result(1,"所选可删除内容为空");
        }
        xsjlService.deleteFjscByList(list);
        boolean flag = xsjlService.deleteXsjl(list,user.getNumber(),user.getDepartment());
        //先去找一下 干脆 查询出来  根据审核状态 进行判断 是否可以删除 放在前端处理？
        if (flag){
            return new Result(1,"删除成功");
        }else{
            return new Result(0,"删除失败");
        }
    }

    @PostMapping(value = "xsjlshow")
    @ResponseBody
    public Xsjl xsjlshow(String id, HttpSession session){
        UserInfo user = (UserInfo) session.getAttribute("USER_SESSION");
        System.out.println(id);

        Xsjl xsjl = xsjlService.showXsjl(user.getDepartment(),user.getNumber(),id);
        return xsjl;
    }

    @PostMapping(value = "xsjlupdate")
    @ResponseBody
    public Result xsjlupdate(@RequestBody Xsjl xsjl, HttpSession session){
        UserInfo user = (UserInfo) session.getAttribute("USER_SESSION");
        if (xsjl.getFjsc()!=null){
            List<String> list = new ArrayList<>();
            list.add((xsjl.getXid().toString()));
            xsjlService.deleteFjscByList(list);
        }

        xsjl.setDepartment(user.getDepartment());
        xsjl.setName(user.getName());
        xsjl.setNumber(user.getNumber());
//
//        if (xsjl.getSystime() == null) {
//            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//            String systime = LocalDate.now().format(formatter); // get the current date
//            xsjl.setSystime(systime);
//        } else if (xsjl.getSystime().equals("-1")) {
            xsjl.setSystime(null);
//        }


        boolean flag = xsjlService.updateXsjl(xsjl);
        if (flag){
            return new Result(1,"更新成功");
        }else{
            return new Result(0,"更新失败");
        }
    }

    @PostMapping(value = "downloadxsjlfjsc")
    @ResponseBody
    public Result downloadxsjlfjsc(@RequestBody List<String> list) {
        String zipFileName = xsjlService.downloadxsjlZipFjsc(list);
        return new Result(1,zipFileName);
    }

    @GetMapping(value = "xsjlexportall")
    public void xsjlexportall(HttpSession session, HttpServletResponse response) {
        UserInfo user = (UserInfo) session.getAttribute("USER_SESSION");
        String department = user.getDepartment();
        String number = user.getNumber();
        String fileName =department + "-" + number +"-"+user.getName()+ "-学术交流通过审核" + UUID.randomUUID().toString().replaceAll("-", "") + ".xls";
        HSSFWorkbook wb = xsjlService.exportxsjlExcel(department, number, user.getRoletype());
        //响应到客户端
        try {
            uploadController.setResponseHeader(response, fileName);
            OutputStream os = response.getOutputStream();
            wb.write(os);
            os.flush();
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                wb.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @PostMapping(value = "xsjlExcelJson")
    @ResponseBody
    public Result xsjlExcelJson(HttpSession session, @RequestBody Map<String, Object> mapdata) {
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
            fileName = xsjlService.getExcelJson(user.getDepartment(), user.getNumber(),"-1", start, end, "3", params);
        } else if (roletype.equals("2")) {
            String name = (String) mapdata.get("name");
            fileName = xsjlService.getExcelJson(user.getDepartment(),"-1", name, start, end, "3", params);
        } else if (roletype.equals("3")) {
            String department = (String) mapdata.get("department");
            String name = (String) mapdata.get("name");

            fileName = xsjlService.getExcelJson(department,"-1", name, start, end, "3", params);
        }
        return new Result(1, fileName);
    }
}
