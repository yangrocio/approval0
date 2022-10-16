package com.aynu.controller.school;

import com.aynu.Utils.CosineSimilarity;
import com.aynu.bean.*;
import com.aynu.controller.UploadController;
import com.aynu.dto.Result;
import com.aynu.service.DepartmentService;
import com.aynu.service.HpjlService;
import com.aynu.service.TyhjService;
import com.aynu.service.YmhjService;
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
@RequestMapping(value = "/sssjump")
@Slf4j
public class SSSJumpController {
    @Autowired
    HpjlService hpjlService;
    @Autowired
    YmhjService ymhjService;
    @Autowired
    TyhjService tyhjService;
    @Autowired
    UploadController uploadController;
    @Autowired
    DepartmentService departmentService;
    //获批奖励
    @GetMapping(value = "goshpjlpage")
    public String goshpjlpage(HttpSession session, Model model, @RequestParam(required = false, defaultValue = "1", value = "pageNum") Integer pageNum,
                              @RequestParam(defaultValue = "15", value = "pageSize") Integer pageSize,
                              @RequestParam(defaultValue = "-1", value = "department") String department,
                              @RequestParam(defaultValue = "-1", value = "number") String number,
                              @RequestParam(defaultValue = "-1", value = "start") String start,
                              @RequestParam(defaultValue = "-1", value = "end") String end,
                              @RequestParam(defaultValue = "-1", value = "judgestatus") String judgestatus,
                              @RequestParam(defaultValue = "-1", value = "CGName") String CGName,
                              @RequestParam(defaultValue = "-1", value = "JLName") String JLName,
                              @RequestParam(defaultValue = "-1", value = "DYHJR") String DYHJR,
                              @RequestParam(defaultValue = "-1", value = "QTHJR") String QTHJR,
                              @RequestParam(defaultValue = "-1", value = "JIXDBM") String JIXDBM,
                              @RequestParam(defaultValue = "-1", value = "JLRank") String JLRank,
                              @RequestParam(defaultValue = "-1", value = "HJTime") String HJTime){
        UserInfo user = (UserInfo) session.getAttribute("USER_SESSION");
        if (!user.getRoletype().equals("3")&& !user.getRoletype().equals("5")){
            return "pages/test";
        }
        //1.引入分页插件,pageNum是第几页，pageSize是每页显示多少条,默认查询总数count
        PageHelper.startPage(pageNum, pageSize);
        //2.紧跟的查询就是一个分页查询-必须紧跟.后面的其他查询不会被分页，除非再次调用PageHelper.startPage
        try {
            List<Hpjl> hylwList = hpjlService.selectHpjlByDetail2(department,"-1",number,start,end,judgestatus,"3",CGName,JLName,DYHJR,QTHJR,JIXDBM,JLRank,HJTime);
            //3.使用PageInfo包装查询后的结果,5是连续显示的条数,结果list类型是Page<E>
            PageInfo<Hpjl> pageInfo = new PageInfo<Hpjl>(hylwList, pageSize);
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
            if (!CGName.equals("-1")) {
                model.addAttribute("CGName", CGName);
            }
            if (!JLName.equals("-1")) {
                model.addAttribute("JLName", JLName);
            }
            if (!DYHJR.equals("-1")) {
                model.addAttribute("DYHJR", DYHJR);
            }
            if (!QTHJR.equals("-1")) {
                model.addAttribute("QTHJR", QTHJR);
            }
            if (!JIXDBM.equals("-1")) {
                model.addAttribute("JIXDBM", JIXDBM);
            }
            if (!JLRank.equals("-1")) {
                model.addAttribute("JLRank", JLRank);
            }
            if (!HJTime.equals("-1")) {
                model.addAttribute("HJTime", HJTime);
            }
            List<Department> departments = departmentService.findAll();
            model.addAttribute("department", department);
            model.addAttribute("departments", departments);
            model.addAttribute("judgestatus", judgestatus);
        } finally {
            PageHelper.clearPage(); //清理 ThreadLocal 存储的分页参数,保证线程安全
        }

        return "school/schoolhpjl";
    }

    @PostMapping(value = "hpjlcheck")
    @ResponseBody
    public List<String> hpjlcheck(String id, HttpSession session) {
        UserInfo user = (UserInfo) session.getAttribute("USER_SESSION");
        Hpjl hpjl = hpjlService.showHpjl(user.getDepartment(), user.getNumber(), id);
        List<Hpjl> hpjlcheck = hpjlService.selectHpjlByDetail(hpjl.getDepartment(),hpjl.getNumber(),"-1","-1","-1","3","3");
        List<String> timus = new ArrayList<String>();
        for(int i = 0;i < hpjlcheck.size(); i ++){

            double  score= CosineSimilarity.getSimilarity(hpjl.getCgname(),hpjlcheck.get(i).getCgname());
            System.out.println(score);
            if( score >= 0.8 ){
                if(timus.size()==0){
                    timus.add("待审核成果名称为："+hpjl.getCgname());
                    timus.add("与以下成果名称相似度过高：");
                }
                timus.add(hpjlcheck.get(i).getCgname());
            }
        }
        if(timus.size()==0){
            timus.add("待审核成果名称为："+hpjl.getCgname());
            timus.add("未找到相似度过高的成果名称！");
        }

        return timus;
    }

    @GetMapping(value = "shpjlexportall")
    public void shpjlexportall(HttpSession session, HttpServletResponse response) {
        UserInfo user = (UserInfo) session.getAttribute("USER_SESSION");
        String department = user.getDepartment();
        String number = user.getNumber();
        String fileName =number +"-"+user.getName()+ "-获批奖励通过审核" + UUID.randomUUID().toString().replaceAll("-", "") + ".xls";
        HSSFWorkbook wb = hpjlService.exporthpjlExcel("-1", "-1", user.getRoletype());
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
    @PostMapping(value = "deleteshpjl")
    @ResponseBody
    public Result deleteshpjl(@RequestBody List<String> list, HttpSession session) {
        if (list.isEmpty()) {
            return new Result(1, "所选可删除内容为空");
        }
        int num = hpjlService.deleteById(list);
        if (num!=0) {
            return new Result(1, "删除成功");
        } else {
            return new Result(0, "删除失败");
        }

    }

    //音美获奖
    @GetMapping(value = "gosymhjpage")
    public String gosymhjpage(HttpSession session, Model model, @RequestParam(required = false, defaultValue = "1", value = "pageNum") Integer pageNum,
                              @RequestParam(defaultValue = "15", value = "pageSize") Integer pageSize,
                              @RequestParam(defaultValue = "-1", value = "department") String department,
                              @RequestParam(defaultValue = "-1", value = "number") String number,
                              @RequestParam(defaultValue = "-1", value = "start") String start,
                              @RequestParam(defaultValue = "-1", value = "end") String end,
                              @RequestParam(defaultValue = "-1", value = "judgestatus") String judgestatus,
                              @RequestParam(defaultValue = "-1", value = "TiMu") String TiMu,
                              @RequestParam(defaultValue = "-1", value = "AwardName") String AwardName,
                              @RequestParam(defaultValue = "-1", value = "DYZZ") String DYZZ,
                              @RequestParam(defaultValue = "-1", value = "QTZZ") String QTZZ,
                              @RequestParam(defaultValue = "-1", value = "Organizer") String Organizer,
                              @RequestParam(defaultValue = "-1", value = "RewardSort") String RewardSort,
                              @RequestParam(defaultValue = "-1", value = "AwardTime") String AwardTime){
        System.out.println(RewardSort);
        UserInfo user = (UserInfo) session.getAttribute("USER_SESSION");
        if (!user.getRoletype().equals("3")&& !user.getRoletype().equals("5")){
            return "pages/test";
        }
        //1.引入分页插件,pageNum是第几页，pageSize是每页显示多少条,默认查询总数count
        PageHelper.startPage(pageNum, pageSize);
        //2.紧跟的查询就是一个分页查询-必须紧跟.后面的其他查询不会被分页，除非再次调用PageHelper.startPage
        try {
            List<Ymhj> hylwList = ymhjService.selectYmhjByDetail2(department,"-1",number,start,end,judgestatus,"3",TiMu,AwardName,DYZZ,QTZZ,Organizer,RewardSort,AwardTime);
            //3.使用PageInfo包装查询后的结果,5是连续显示的条数,结果list类型是Page<E>
            PageInfo<Ymhj> pageInfo = new PageInfo<Ymhj>(hylwList, pageSize);
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

            if (!TiMu.equals("-1")) {
                model.addAttribute("TiMu", TiMu);
            }
            if (!AwardName.equals("-1")) {
                model.addAttribute("AwardName", AwardName);
            }
            if (!DYZZ.equals("-1")) {
                model.addAttribute("DYZZ", DYZZ);
            }
            if (!QTZZ.equals("-1")) {
                model.addAttribute("QTZZ", QTZZ);
            }
            if (!Organizer.equals("-1")) {
                model.addAttribute("Organizer", Organizer);
            }
            if (!RewardSort.equals("-1")) {
                model.addAttribute("RewardSort", RewardSort);
            }
            if (!AwardTime.equals("-1")) {
                model.addAttribute("AwardTime", AwardTime);
            }
            List<Department> departments = departmentService.findAll();
            model.addAttribute("department", department);
            model.addAttribute("departments", departments);
            model.addAttribute("judgestatus", judgestatus);
        } finally {
            PageHelper.clearPage(); //清理 ThreadLocal 存储的分页参数,保证线程安全
        }

        return "school/schoolymhj";
    }
    @PostMapping(value = "ymhjcheck")
    @ResponseBody
    public List<String> ymhjcheck(String id, HttpSession session) {
        UserInfo user = (UserInfo) session.getAttribute("USER_SESSION");
        Ymhj ymhj = ymhjService.showYmhj(user.getDepartment(), user.getNumber(), id);
        List<Ymhj> ymhjcheck = ymhjService.selectYmhjByDetail(ymhj.getDepartment(),ymhj.getNumber(),"-1","-1","-1","3","3");
        List<String> timus = new ArrayList<String>();
        for(int i = 0;i < ymhjcheck.size(); i ++){

            double  score= CosineSimilarity.getSimilarity(ymhj.getTimu(),ymhjcheck.get(i).getTimu());
            System.out.println(score);
            if( score >= 0.8 ){
                if(timus.size()==0){
                    timus.add("待审核题目为："+ymhj.getTimu());
                    timus.add("与以下题目相似度过高：");
                }
                timus.add(ymhjcheck.get(i).getTimu());
            }
        }
        if(timus.size()==0){
            timus.add("待审核题目为："+ymhj.getTimu());
            timus.add("未找到相似度过高的题目！");
        }

        return timus;
    }

    @GetMapping(value = "symhjexportall")
    public void symhjexportall(HttpSession session, HttpServletResponse response) {
        UserInfo user = (UserInfo) session.getAttribute("USER_SESSION");
        String department = user.getDepartment();
        String number = user.getNumber();
        String fileName =number +"-"+user.getName()+ "-音美获奖通过审核" + UUID.randomUUID().toString().replaceAll("-", "") + ".xls";
        HSSFWorkbook wb = ymhjService.exportymhjExcel("-1", "-1", user.getRoletype());
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
    @PostMapping(value = "deletesymhj")
    @ResponseBody
    public Result deletesymhj(@RequestBody List<String> list, HttpSession session) {
        if (list.isEmpty()) {
            return new Result(1, "所选可删除内容为空");
        }
        int num = ymhjService.deleteById(list);
        if (num!=0) {
            return new Result(1, "删除成功");
        } else {
            return new Result(0, "删除失败");
        }

    }
    //体育获奖
    @GetMapping(value = "gostyhjpage")
    public String gostyhjpage(HttpSession session, Model model, @RequestParam(required = false, defaultValue = "1", value = "pageNum") Integer pageNum,
                              @RequestParam(defaultValue = "15", value = "pageSize") Integer pageSize,
                              @RequestParam(defaultValue = "-1", value = "department") String department,
                              @RequestParam(defaultValue = "-1", value = "number") String number,
                              @RequestParam(defaultValue = "-1", value = "start") String start,
                              @RequestParam(defaultValue = "-1", value = "end") String end,
                              @RequestParam(defaultValue = "-1", value = "judgestatus") String judgestatus,
                              @RequestParam(defaultValue = "-1", value = "Awardee") String Awardee,
                              @RequestParam(defaultValue = "-1", value = "Entryname") String Entryname,
                              @RequestParam(defaultValue = "-1", value = "SportsLV") String SportsLV,
                              @RequestParam(defaultValue = "-1", value = "WinTime") String WinTime){
        UserInfo user = (UserInfo) session.getAttribute("USER_SESSION");
        if (!user.getRoletype().equals("3")&& !user.getRoletype().equals("5")){
            return "pages/test";
        }
        //1.引入分页插件,pageNum是第几页，pageSize是每页显示多少条,默认查询总数count
        PageHelper.startPage(pageNum, pageSize);
        //2.紧跟的查询就是一个分页查询-必须紧跟.后面的其他查询不会被分页，除非再次调用PageHelper.startPage
        try {
            List<Tyhj> hylwList = tyhjService.selectTyhjByDetail2(department,"-1",number,start,end,judgestatus,"3",Awardee,Entryname,SportsLV,WinTime);
            //3.使用PageInfo包装查询后的结果,5是连续显示的条数,结果list类型是Page<E>
            PageInfo<Tyhj> pageInfo = new PageInfo<Tyhj>(hylwList, pageSize);
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
            if (!Awardee.equals("-1")) {
                model.addAttribute("Awardee", Awardee);
            }
            if (!Entryname.equals("-1")) {
                model.addAttribute("Entryname", Entryname);
            }
            if (!SportsLV.equals("-1")) {
                model.addAttribute("SportsLV", SportsLV);
            }
            if (!WinTime.equals("-1")) {
                model.addAttribute("WinTime", WinTime);
            }
            List<Department> departments = departmentService.findAll();
            model.addAttribute("department", department);
            model.addAttribute("departments", departments);
            model.addAttribute("judgestatus", judgestatus);
        } finally {
            PageHelper.clearPage(); //清理 ThreadLocal 存储的分页参数,保证线程安全
        }

        return "school/schooltyhj";
    }
    @PostMapping(value = "tyhjcheck")
    @ResponseBody
    public List<String> tyhjcheck(String id, HttpSession session) {
        UserInfo user = (UserInfo) session.getAttribute("USER_SESSION");
        Tyhj tyhj = tyhjService.showTyhj(user.getDepartment(), user.getNumber(), id);
        List<Tyhj> tyhjcheck = tyhjService.selectTyhjByDetail(tyhj.getDepartment(),tyhj.getNumber(),"-1","-1","-1","3","3");
        List<String> timus = new ArrayList<String>();
        for(int i = 0;i < tyhjcheck.size(); i ++){

            double  score= CosineSimilarity.getSimilarity(tyhj.getEntryname(),tyhjcheck.get(i).getEntryname());
            System.out.println(score);
            if( score >= 0.8 ){
                if(timus.size()==0){
                    timus.add("待审核题目为："+tyhj.getEntryname());
                    timus.add("与以下题目相似度过高：");
                }
                timus.add(tyhjcheck.get(i).getEntryname());
            }
        }
        if(timus.size()==0){
            timus.add("待审核题目为："+tyhj.getEntryname());
            timus.add("未找到相似度过高的题目！");
        }

        return timus;
    }

    @GetMapping(value = "styhjexportall")
    public void styhjexportall(HttpSession session, HttpServletResponse response) {
        UserInfo user = (UserInfo) session.getAttribute("USER_SESSION");
        String department = user.getDepartment();
        String number = user.getNumber();
        String fileName =number +"-"+user.getName()+ "-体育获奖通过审核" + UUID.randomUUID().toString().replaceAll("-", "") + ".xls";
        HSSFWorkbook wb = tyhjService.exporttyhjExcel("-1", "-1", user.getRoletype());
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
    @PostMapping(value = "deletestyhj")
    @ResponseBody
    public Result deletestyhj(@RequestBody List<String> list, HttpSession session) {
        if (list.isEmpty()) {
            return new Result(1, "所选可删除内容为空");
        }
        int num = tyhjService.deleteById(list);
        if (num!=0) {
            return new Result(1, "删除成功");
        } else {
            return new Result(0, "删除失败");
        }

    }
}
