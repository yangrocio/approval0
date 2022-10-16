package com.aynu.controller.school;

import com.aynu.Utils.CosineSimilarity;
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

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @Author susuper
 * @Date 2020/9/21 14:04
 * @description:
 */
@Controller
@RequestMapping(value = "/ssjump")
@Slf4j
public class SSJumpController {
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
    @Autowired
    DepartmentService departmentService;
    //软件著作
    @GetMapping(value = "gosrjzzpage")
    public String gosrjzzpage(HttpSession session, Model model, @RequestParam(required = false, defaultValue = "1", value = "pageNum") Integer pageNum,
                              @RequestParam(defaultValue = "15", value = "pageSize") Integer pageSize,
                              @RequestParam(defaultValue = "-1", value = "department") String department,
                              @RequestParam(defaultValue = "-1", value = "number") String number,
                              @RequestParam(defaultValue = "-1", value = "start") String start,
                              @RequestParam(defaultValue = "-1", value = "end") String end,
                              @RequestParam(defaultValue = "-1", value = "judgestatus") String judgestatus,
                              @RequestParam(defaultValue = "-1", value = "XMName") String XMName,
                              @RequestParam(defaultValue = "-1", value = "DYZZ") String DYZZ,
                              @RequestParam(defaultValue = "-1", value = "QTZZ") String QTZZ,
                              @RequestParam(defaultValue = "-1", value = "DJNumber") String DJNumber,
                              @RequestParam(defaultValue = "-1", value = "DJTime") String DJTime){
        UserInfo user = (UserInfo) session.getAttribute("USER_SESSION");
        if (!user.getRoletype().equals("3") && !user.getRoletype().equals("5")){
            return "pages/test";
        }
        //1.引入分页插件,pageNum是第几页，pageSize是每页显示多少条,默认查询总数count
        PageHelper.startPage(pageNum, pageSize);
        //2.紧跟的查询就是一个分页查询-必须紧跟.后面的其他查询不会被分页，除非再次调用PageHelper.startPage
        try {
            List<Rjzz> hylwList = rjzzService.selectRjzzByDetail2(department,"-1",number,start,end,judgestatus,"3",XMName,DYZZ,QTZZ,DJNumber,DJTime);
            //3.使用PageInfo包装查询后的结果,5是连续显示的条数,结果list类型是Page<E>
            PageInfo<Rjzz> pageInfo = new PageInfo<Rjzz>(hylwList, pageSize);
            //4.使用model/map/modelandview等带回前端
            model.addAttribute("pageInfo", pageInfo);
            if (!start.equals("-1")) {
                model.addAttribute("start", start);
            }
            if (!end.equals("-1")) {
                model.addAttribute("end", end);
            }
            if (!number.equals("-1")) {
                model.addAttribute("number", number);
            }
            if (!XMName.equals("-1")) {
                model.addAttribute("XMName", XMName);
            }
            if (!DYZZ.equals("-1")) {
                model.addAttribute("DYZZ", DYZZ);
            }
            if (!QTZZ.equals("-1")) {
                model.addAttribute("QTZZ", QTZZ);
            }
            if (!DJNumber.equals("-1")) {
                model.addAttribute("DJNumber", DJNumber);
            }
            if (!DJTime.equals("-1")) {
                model.addAttribute("DJTime", DJTime);
            }
            List<Department> departments = departmentService.findAll();
            model.addAttribute("department", department);
            model.addAttribute("departments", departments);
            model.addAttribute("judgestatus", judgestatus);
        } finally {
            PageHelper.clearPage(); //清理 ThreadLocal 存储的分页参数,保证线程安全
        }

        return "school/schoolrjzz";
    }
    @PostMapping(value = "rjzzcheck")
    @ResponseBody
    public List<String> rjzzcheck(String id, HttpSession session) {
        UserInfo user = (UserInfo) session.getAttribute("USER_SESSION");
        Rjzz rjzz = rjzzService.showRjzz(user.getDepartment(), user.getNumber(), id);
        List<Rjzz> rjzzcheck = rjzzService.selectRjzzByDetail(rjzz.getDepartment(),rjzz.getNumber(),"-1","-1","-1","3","3");
        List<String> timus = new ArrayList<String>();
        for(int i = 0;i < rjzzcheck.size(); i ++){

            double  score= CosineSimilarity.getSimilarity(rjzz.getXmname(),rjzzcheck.get(i).getXmname());
            System.out.println(score);
            if( score >= 0.8 ){
                if(timus.size()==0){
                    timus.add("待审核题目为："+rjzz.getXmname());
                    timus.add("与以下题目相似度过高：");
                }
                timus.add(rjzzcheck.get(i).getXmname());
            }
        }
        if(timus.size()==0){
            timus.add("待审核题目为："+rjzz.getXmname());
            timus.add("未找到相似度过高的题目！");
        }

        return timus;
    }
    @GetMapping(value = "srjzzexportall")
    public void srjzzexportall(HttpSession session, HttpServletResponse response) {
        UserInfo user = (UserInfo) session.getAttribute("USER_SESSION");
        String department = user.getDepartment();
        String number = user.getNumber();
        String fileName =number +"-"+user.getName()+ "-软件著作通过审核" + UUID.randomUUID().toString().replaceAll("-", "") + ".xls";
        HSSFWorkbook wb = rjzzService.exportrjzzExcel("-1", "-1", user.getRoletype());
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
    @PostMapping(value = "deletesrjzz")
    @ResponseBody
    public Result deletesrjzz(@RequestBody List<String> list, HttpSession session) {
        if (list.isEmpty()) {
            return new Result(1, "所选可删除内容为空");
        }
        int num = rjzzService.deleteById(list);
        if (num!=0) {
            return new Result(1, "删除成功");
        } else {
            return new Result(0, "删除失败");
        }

    }

    //获批专利
    @GetMapping(value = "goshpzlpage")
    public String goshpzlpage(HttpSession session, Model model, @RequestParam(required = false, defaultValue = "1", value = "pageNum") Integer pageNum,
                              @RequestParam(defaultValue = "15", value = "pageSize") Integer pageSize,
                              @RequestParam(defaultValue = "-1", value = "department") String department,
                              @RequestParam(defaultValue = "-1", value = "number") String number,
                              @RequestParam(defaultValue = "-1", value = "start") String start,
                              @RequestParam(defaultValue = "-1", value = "end") String end,
                              @RequestParam(defaultValue = "-1", value = "judgestatus") String judgestatus,
                              @RequestParam(defaultValue = "-1", value = "XMName") String XMName,
                              @RequestParam(defaultValue = "-1", value = "FMR") String FMR,
                              @RequestParam(defaultValue = "-1", value = "QTFMR") String QTFMR,
                              @RequestParam(defaultValue = "-1", value = "ZLNumber") String ZLNumber,
                              @RequestParam(defaultValue = "-1", value = "PType") String PType,
                              @RequestParam(defaultValue = "-1", value = "SYTime") String SYTime){
        UserInfo user = (UserInfo) session.getAttribute("USER_SESSION");
        if (!user.getRoletype().equals("3")&& !user.getRoletype().equals("5")){
            return "pages/test";
        }
        //1.引入分页插件,pageNum是第几页，pageSize是每页显示多少条,默认查询总数count
        PageHelper.startPage(pageNum, pageSize);
        //2.紧跟的查询就是一个分页查询-必须紧跟.后面的其他查询不会被分页，除非再次调用PageHelper.startPage
        try {
            List<Hpzl> hylwList = hpzlService.selectHpzlByDetail2(department,"-1",number,start,end,judgestatus,"3",XMName,FMR,QTFMR,ZLNumber,PType,SYTime);
            //3.使用PageInfo包装查询后的结果,5是连续显示的条数,结果list类型是Page<E>
            PageInfo<Hpzl> pageInfo = new PageInfo<Hpzl>(hylwList, pageSize);
            //4.使用model/map/modelandview等带回前端
            model.addAttribute("pageInfo", pageInfo);
            if (!start.equals("-1")) {
                model.addAttribute("start", start);
            }
            if (!end.equals("-1")) {
                model.addAttribute("end", end);
            }
            if (!number.equals("-1")) {
                model.addAttribute("number", number);
            }
            if (!XMName.equals("-1")) {
                model.addAttribute("XMName", XMName);
            }
            if (!FMR.equals("-1")) {
                model.addAttribute("FMR", FMR);
            }
            if (!QTFMR.equals("-1")) {
                model.addAttribute("QTFMR", QTFMR);
            }
            if (!ZLNumber.equals("-1")) {
                model.addAttribute("ZLNumber", ZLNumber);
            }
            if (!PType.equals("-1")) {
                model.addAttribute("PType", PType);
            }
            if (!SYTime.equals("-1")) {
                model.addAttribute("SYTime", SYTime);
            }
            List<Department> departments = departmentService.findAll();
            model.addAttribute("department", department);
            model.addAttribute("departments", departments);
            model.addAttribute("judgestatus", judgestatus);
        } finally {
            PageHelper.clearPage(); //清理 ThreadLocal 存储的分页参数,保证线程安全
        }

        return "school/schoolhpzl";
    }
    @PostMapping(value = "hpzlcheck")
    @ResponseBody
    public List<String> hpzlcheck(String id, HttpSession session) {
        UserInfo user = (UserInfo) session.getAttribute("USER_SESSION");
        Hpzl hpzl = hpzlService.showHpzl(user.getDepartment(), user.getNumber(), id);
        List<Hpzl> hpzlcheck = hpzlService.selectHpzlByDetail(hpzl.getDepartment(),hpzl.getNumber(),"-1","-1","-1","3","3");
        List<String> timus = new ArrayList<String>();
        for(int i = 0;i < hpzlcheck.size(); i ++){

            double  score= CosineSimilarity.getSimilarity(hpzl.getXmname(),hpzlcheck.get(i).getXmname());
            System.out.println(score);
            if( score >= 0.8 ){
                if(timus.size()==0){
                    timus.add("待审核题目为："+hpzl.getXmname());
                    timus.add("与以下题目相似度过高：");
                }
                timus.add(hpzlcheck.get(i).getXmname());
            }
        }
        if(timus.size()==0){
            timus.add("待审核题目为："+hpzl.getXmname());
            timus.add("未找到相似度过高的题目！");
        }

        return timus;
    }

    @GetMapping(value = "shpzlexportall")
    public void shpzlexportall(HttpSession session, HttpServletResponse response) {
        UserInfo user = (UserInfo) session.getAttribute("USER_SESSION");
        String department = user.getDepartment();
        String number = user.getNumber();
        String fileName =number +"-"+user.getName()+ "-获批专利通过审核" + UUID.randomUUID().toString().replaceAll("-", "") + ".xls";
        HSSFWorkbook wb = hpzlService.exporthpzlExcel("-1", "-1", user.getRoletype());
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

    @PostMapping(value = "deleteshpzl")
    @ResponseBody
    public Result deleteshpzl(@RequestBody List<String> list, HttpSession session) {
        if (list.isEmpty()) {
            return new Result(1, "所选可删除内容为空");
        }
        int num = hpzlService.deleteById(list);
        if (num!=0) {
            return new Result(1, "删除成功");
        } else {
            return new Result(0, "删除失败");
        }

    }
    //学术称号
    @GetMapping(value = "gosxschpage")
    public String gosxschpage(HttpSession session, Model model, @RequestParam(required = false, defaultValue = "1", value = "pageNum") Integer pageNum,
                              @RequestParam(defaultValue = "15", value = "pageSize") Integer pageSize,
                              @RequestParam(defaultValue = "-1", value = "department") String department,
                              @RequestParam(defaultValue = "-1", value = "number") String number,
                              @RequestParam(defaultValue = "-1", value = "start") String start,
                              @RequestParam(defaultValue = "-1", value = "end") String end,
                              @RequestParam(defaultValue = "-1", value = "judgestatus") String judgestatus,
                              @RequestParam(defaultValue = "-1", value = "XMName") String XMName,
                              @RequestParam(defaultValue = "-1", value = "Title") String Title,
                              @RequestParam(defaultValue = "-1", value = "Rank") String Rank,
                              @RequestParam(defaultValue = "-1", value = "XFBM") String XFBM,
                              @RequestParam(defaultValue = "-1", value = "Time") String Time){
        UserInfo user = (UserInfo) session.getAttribute("USER_SESSION");
        if (!user.getRoletype().equals("3")&& !user.getRoletype().equals("5")){
            return "pages/test";
        }
        //1.引入分页插件,pageNum是第几页，pageSize是每页显示多少条,默认查询总数count
        PageHelper.startPage(pageNum, pageSize);
        //2.紧跟的查询就是一个分页查询-必须紧跟.后面的其他查询不会被分页，除非再次调用PageHelper.startPage
        try {
            List<Xsch> hylwList = xschService.selectXschByDetail2(department,"-1",number,start,end,judgestatus,"3",XMName,Title,Rank,XFBM,Time);
            //3.使用PageInfo包装查询后的结果,5是连续显示的条数,结果list类型是Page<E>
            PageInfo<Xsch> pageInfo = new PageInfo<Xsch>(hylwList, pageSize);
            //4.使用model/map/modelandview等带回前端
            model.addAttribute("pageInfo", pageInfo);
            if (!start.equals("-1")) {
                model.addAttribute("start", start);
            }
            if (!end.equals("-1")) {
                model.addAttribute("end", end);
            }
            if (!number.equals("-1")) {
                model.addAttribute("number", number);
            }
            if (!XMName.equals("-1")) {
                model.addAttribute("XMName", XMName);
            }
            if (!Title.equals("-1")) {
                model.addAttribute("Title", Title);
            }
            if (!Rank.equals("-1")) {
                model.addAttribute("Rank", Rank);
            }
            if (!XFBM.equals("-1")) {
                model.addAttribute("XFBM", XFBM);
            }
            if (!Time.equals("-1")) {
                model.addAttribute("Time", Time);
            }
            List<Department> departments = departmentService.findAll();
            model.addAttribute("department", department);
            model.addAttribute("departments", departments);
            model.addAttribute("judgestatus", judgestatus);
        } finally {
            PageHelper.clearPage(); //清理 ThreadLocal 存储的分页参数,保证线程安全
        }

        return "school/schoolxsch";
    }

    @PostMapping(value = "xschcheck")
    @ResponseBody
    public List<String> xschcheck(String id, HttpSession session) {
        UserInfo user = (UserInfo) session.getAttribute("USER_SESSION");
        Xsch xsch = xschService.showXsch(user.getDepartment(), user.getNumber(), id);
        List<Xsch> xschcheck = xschService.selectXschByDetail(xsch.getDepartment(),xsch.getNumber(),"-1","-1","-1","3","3");
        List<String> timus = new ArrayList<String>();
        for(int i = 0;i < xschcheck.size(); i ++){

            double  score= CosineSimilarity.getSimilarity(xsch.getTitle(),xschcheck.get(i).getTitle());
            System.out.println(score);
            if( score >= 0.8 ){
                if(timus.size()==0){
                    timus.add("待审核称号为："+xsch.getTitle());
                    timus.add("与以下称号相似度过高：");
                }
                timus.add(xschcheck.get(i).getTitle());
            }
        }
        if(timus.size()==0){
            timus.add("待审核称号为："+xsch.getTitle());
            timus.add("未找到相似度过高的称号！");
        }

        return timus;
    }

    @GetMapping(value = "sxschexportall")
    public void sxschexportall(HttpSession session, HttpServletResponse response) {
        UserInfo user = (UserInfo) session.getAttribute("USER_SESSION");
        String department = user.getDepartment();
        String number = user.getNumber();
        String fileName =number +"-"+user.getName()+ "-学术称号通过审核" + UUID.randomUUID().toString().replaceAll("-", "") + ".xls";
        HSSFWorkbook wb = xschService.exportxschExcel("-1", "-1", user.getRoletype());
        //响应到客户端
        try {
            uploadController.setResponseHeader(response, fileName);
            OutputStream os = response.getOutputStream();
            wb.write(os);
            os.flush();
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @PostMapping(value = "deletesxsch")
    @ResponseBody
    public Result deletesxsch(@RequestBody List<String> list, HttpSession session) {
        if (list.isEmpty()) {
            return new Result(1, "所选可删除内容为空");
        }
        int num = xschService.deleteById(list);
        if (num!=0) {
            return new Result(1, "删除成功");
        } else {
            return new Result(0, "删除失败");
        }

    }
    //学术奖励
    @GetMapping(value = "gosxsjlpage")
    public String gosxsjlpage(HttpSession session, Model model, @RequestParam(required = false, defaultValue = "1", value = "pageNum") Integer pageNum,
                              @RequestParam(defaultValue = "15", value = "pageSize") Integer pageSize,
                              @RequestParam(defaultValue = "-1", value = "department") String department,
                              @RequestParam(defaultValue = "-1", value = "number") String number,
                              @RequestParam(defaultValue = "-1", value = "start") String start,
                              @RequestParam(defaultValue = "-1", value = "end") String end,
                              @RequestParam(defaultValue = "-1", value = "judgestatus") String judgestatus,
                              @RequestParam(defaultValue = "-1", value = "XMName") String XMName,
                              @RequestParam(defaultValue = "-1", value = "CYRY") String CYRY,
                              @RequestParam(defaultValue = "-1", value = "Rank") String Rank){
        UserInfo user = (UserInfo) session.getAttribute("USER_SESSION");
        if (!user.getRoletype().equals("3")&& !user.getRoletype().equals("5")){
            return "pages/test";
        }
        //1.引入分页插件,pageNum是第几页，pageSize是每页显示多少条,默认查询总数count
        PageHelper.startPage(pageNum, pageSize);
        //2.紧跟的查询就是一个分页查询-必须紧跟.后面的其他查询不会被分页，除非再次调用PageHelper.startPage
        try {
            List<Xsjl> hylwList = xsjlService.selectXsjlByDetail2(department,"-1",number,start,end,judgestatus,"3",XMName,CYRY,Rank);
            //3.使用PageInfo包装查询后的结果,5是连续显示的条数,结果list类型是Page<E>
            PageInfo<Xsjl> pageInfo = new PageInfo<Xsjl>(hylwList, pageSize);
            //4.使用model/map/modelandview等带回前端
            model.addAttribute("pageInfo", pageInfo);
            if (!start.equals("-1")) {
                model.addAttribute("start", start);
            }
            if (!end.equals("-1")) {
                model.addAttribute("end", end);
            }
            if (!number.equals("-1")) {
                model.addAttribute("number", number);
            }
            if (!XMName.equals("-1")) {
                model.addAttribute("XMName", XMName);
            }
            if (!CYRY.equals("-1")) {
                model.addAttribute("CYRY", CYRY);
            }
            if (!Rank.equals("-1")) {
                model.addAttribute("Rank", Rank);
            }
            List<Department> departments = departmentService.findAll();
            model.addAttribute("department", department);
            model.addAttribute("departments", departments);
            model.addAttribute("judgestatus", judgestatus);
        } finally {
            PageHelper.clearPage(); //清理 ThreadLocal 存储的分页参数,保证线程安全
        }

        return "school/schoolxsjl";
    }

    @PostMapping(value = "xsjlcheck")
    @ResponseBody
    public List<String> xsjlcheck(String id, HttpSession session) {
        UserInfo user = (UserInfo) session.getAttribute("USER_SESSION");
        Xsjl xsjl = xsjlService.showXsjl(user.getDepartment(), user.getNumber(), id);
        List<Xsjl> xsjlcheck = xsjlService.selectXsjlByDetail(xsjl.getDepartment(),xsjl.getNumber(),"-1","-1","-1","3","3");
        List<String> timus = new ArrayList<String>();
        for(int i = 0;i < xsjlcheck.size(); i ++){

            double  score= CosineSimilarity.getSimilarity(xsjl.getXmname(),xsjlcheck.get(i).getXmname());
            System.out.println(score);
            if( score >= 0.8 ){
                if(timus.size()==0){
                    timus.add("待审核会议名为："+xsjl.getXmname());
                    timus.add("与以下会议名相似度过高：");
                }
                timus.add(xsjlcheck.get(i).getXmname());
            }
        }
        if(timus.size()==0){
            timus.add("待审核会议名为："+xsjl.getXmname());
            timus.add("未找到相似度过高的会议名！");
        }

        return timus;
    }


    @GetMapping(value = "sxsjlexportall")
    public void sxsjlexportall(HttpSession session, HttpServletResponse response) {
        UserInfo user = (UserInfo) session.getAttribute("USER_SESSION");
        String department = user.getDepartment();
        String number = user.getNumber();
        String fileName =number +"-"+user.getName()+ "-学术奖励通过审核" + UUID.randomUUID().toString().replaceAll("-", "") + ".xls";
        HSSFWorkbook wb = xsjlService.exportxsjlExcel("-1", "-1", user.getRoletype());
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

    @PostMapping(value = "deletesxsjl")
    @ResponseBody
    public Result deletesxsjl(@RequestBody List<String> list, HttpSession session) {
        if (list.isEmpty()) {
            return new Result(1, "所选可删除内容为空");
        }
        int num = xsjlService.deleteById(list);
        if (num!=0) {
            return new Result(1, "删除成功");
        } else {
            return new Result(0, "删除失败");
        }

    }
}
