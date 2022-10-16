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
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.OutputStream;
import java.util.*;

/**
 * @Author susuper
 * @Date 2020/9/19 22:10
 * @description:
 */
@Controller
@RequestMapping(value = "/sjump")
@Slf4j
public class SJumpController {
    @Autowired
    HylwService hylwService;
    @Autowired
    QklwService qklwService;
    @Autowired
    XszzService xszzService;
    @Autowired
    HxxmService hxxmService;
//    @Autowired
//
    @Autowired
    UploadController uploadController;
    @Autowired
    DepartmentService departmentService;
    //会议论文
    @GetMapping(value = "goshylwpage")
    public String goshylwpage(HttpSession session, Model model,
                              @RequestParam(required = false, defaultValue = "1", value = "pageNum") Integer pageNum,
                              @RequestParam(defaultValue = "15", value = "pageSize") Integer pageSize,
                              @RequestParam(defaultValue = "-1", value = "department") String department,
                              @RequestParam(defaultValue = "-1", value = "number") String number,
                              @RequestParam(defaultValue = "-1", value = "start") String start,
                              @RequestParam(defaultValue = "-1", value = "end") String end,
                              @RequestParam(defaultValue = "-1", value = "judgestatus") String judgestatus,
                              @RequestParam(defaultValue = "-1", value = "tm") String tm,
                              @RequestParam(defaultValue = "-1", value = "dyzz") String dyzz,
                              @RequestParam(defaultValue = "-1", value = "qtzz") String qtzz,
                              @RequestParam(defaultValue = "-1", value = "hymc") String hymc,
                              @RequestParam(defaultValue = "-1", value = "hysj") String hysj,
                              @RequestParam(defaultValue = "-1", value = "hyjb") String hyjb,
                              @RequestParam(defaultValue = "-1", value = "jllb") String jllb){
        UserInfo user = (UserInfo) session.getAttribute("USER_SESSION");
        //1.引入分页插件,pageNum是第几页，pageSize是每页显示多少条,默认查询总数count
        if (!user.getRoletype().equals("3")&& !user.getRoletype().equals("5")){
            return "pages/test";
        }
        PageHelper.startPage(pageNum, pageSize);
        //2.紧跟的查询就是一个分页查询-必须紧跟.后面的其他查询不会被分页，除非再次调用PageHelper.startPage
        try {
            //数据库
            List<Hylw> hylwList = hylwService.selectHylwByDetail2(department,"-1",number,start,end,judgestatus,"3",tm,dyzz,qtzz,hymc,hysj,hyjb,jllb);
            System.out.println(hylwList);
            //3.使用PageInfo包装查询后的结果,5是连续显示的条数,结果list类型是Page<E>
            PageInfo<Hylw> pageInfo = new PageInfo<Hylw>(hylwList, pageSize);
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
            if (!tm.equals("-1")) {
                model.addAttribute("tm", tm);
            }
            if (!dyzz.equals("-1")) {
                model.addAttribute("dyzz", dyzz);
            }
            if (!qtzz.equals("-1")) {
                model.addAttribute("qtzz", qtzz);
            }
            if (!hymc.equals("-1")) {
                model.addAttribute("hymc", hymc);
            }
            if (!hysj.equals("-1")) {
                model.addAttribute("hysj", hysj);
            }
            if (!hyjb.equals("-1")) {
                model.addAttribute("hyjb", hyjb);
            }
            if (!jllb.equals("-1")) {
                model.addAttribute("jllb", jllb);
            }
            List<Department> departments = departmentService.findAll();
            model.addAttribute("department", department);
            model.addAttribute("departments", departments);
            model.addAttribute("judgestatus", judgestatus);
        } finally {
            PageHelper.clearPage(); //清理 ThreadLocal 存储的分页参数,保证线程安全
        }


        return "school/schoolhylw";
    }



    @PostMapping(value = "hylwcheck")
    @ResponseBody
    public List<String> hylwcheck(String id, HttpSession session) {
        UserInfo user = (UserInfo) session.getAttribute("USER_SESSION");
        Hylw hylw = hylwService.showHylw(user.getDepartment(),user.getNumber(), id);
        List<Hylw> hylwcheck = hylwService.selectHylwByDetail(hylw.getDepartment(),hylw.getNumber(),"-1","-1","-1","3","3");
        List<String> timus = new ArrayList<String>();
        for(int i = 0;i < hylwcheck.size(); i ++){

            double  score= CosineSimilarity.getSimilarity(hylw.getTimu(),hylwcheck.get(i).getTimu());
            System.out.println(score);
            if( score >= 0.8 ){
                if(timus.size()==0){
                    timus.add("待审核题目为："+hylw.getTimu());
                    timus.add("与以下题目相似度过高：");
                }
                timus.add(hylwcheck.get(i).getTimu());
            }
        }
        if(timus.size()==0){
            timus.add("待审核题目为："+hylw.getTimu());
            timus.add("未找到相似度过高的题目！");
        }

        return timus;
    }

    @GetMapping(value = "shylwexportall")
    public void shylwexportall(HttpSession session, HttpServletResponse response) {
        UserInfo user = (UserInfo) session.getAttribute("USER_SESSION");
        String department = user.getDepartment();
        String number = user.getNumber();
        String fileName =number +"-"+user.getName()+ "-会议论文通过审核" + UUID.randomUUID().toString().replaceAll("-", "") + ".xls";
        HSSFWorkbook wb = hylwService.exporthylwExcel("-1", "-1", user.getRoletype());
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


    @PostMapping(value = "deleteshylw")
    @ResponseBody
    public Result deleteshylw(@RequestBody List<String> list, HttpSession session) {
        if (list.isEmpty()) {
            return new Result(1, "所选可删除内容为空");
        }
        int num = hylwService.deleteById(list);
        if (num!=0) {
            return new Result(1, "删除成功");
        } else {
            return new Result(0, "删除失败");
        }

    }

    //期刊论文
    @GetMapping(value = "gosqklwpage")
    public String gosqklwpage(HttpSession session, Model model, @RequestParam(required = false, defaultValue = "1", value = "pageNum") Integer pageNum,
                              @RequestParam(defaultValue = "15", value = "pageSize") Integer pageSize,
                              @RequestParam(defaultValue = "-1", value = "department") String department,
                              @RequestParam(defaultValue = "-1", value = "number") String number,
                              @RequestParam(defaultValue = "-1", value = "start") String start,
                              @RequestParam(defaultValue = "-1", value = "end") String end,
                              @RequestParam(defaultValue = "-1", value = "judgestatus") String judgestatus,
                              @RequestParam(defaultValue = "-1", value = "timu") String timu,
                              @RequestParam(defaultValue = "-1", value = "dyzz") String dyzz,
                              @RequestParam(defaultValue = "-1", value = "qtzz") String qtzz,
                              @RequestParam(defaultValue = "-1", value = "type") String type,
                              @RequestParam(defaultValue = "-1", value = "fbqk") String fbqk,
                              @RequestParam(defaultValue = "-1", value = "time") String time,
                              @RequestParam(defaultValue = "-1", value = "fq") String fq){
        UserInfo user = (UserInfo) session.getAttribute("USER_SESSION");
        if (!user.getRoletype().equals("3")&& !user.getRoletype().equals("5")){
            return "pages/test";
        }
        //1.引入分页插件,pageNum是第几页，pageSize是每页显示多少条,默认查询总数count
        PageHelper.startPage(pageNum, pageSize);
        //2.紧跟的查询就是一个分页查询-必须紧跟.后面的其他查询不会被分页，除非再次调用PageHelper.startPage
        try {
            List<Qklw> hylwList = qklwService.selectQklwByDetail2(department,"-1",number,start,end,judgestatus,"3",timu,dyzz,qtzz,type,fbqk,time,fq);
            //3.使用PageInfo包装查询后的结果,5是连续显示的条数,结果list类型是Page<E>
            PageInfo<Qklw> pageInfo = new PageInfo<Qklw>(hylwList, pageSize);
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
            if (!timu.equals("-1")) {
                model.addAttribute("timu", timu);
            }
            if (!dyzz.equals("-1")) {
                model.addAttribute("dyzz", dyzz);
            }
            if (!qtzz.equals("-1")) {
                model.addAttribute("qtzz", qtzz);
            }
            if (!type.equals("-1")) {
                model.addAttribute("type", type);
            }
            if (!fbqk.equals("-1")) {
                model.addAttribute("fbqk", fbqk);
            }
            if (!time.equals("-1")) {
                model.addAttribute("time", time);
            }
            if (!fq.equals("-1")) {
                model.addAttribute("fq", fq);
            }
            List<Department> departments = departmentService.findAll();
            model.addAttribute("department", department);
            model.addAttribute("departments", departments);
            model.addAttribute("judgestatus", judgestatus);
        } finally {
            PageHelper.clearPage(); //清理 ThreadLocal 存储的分页参数,保证线程安全
        }

        return "school/schoolqklw";
    }
    @PostMapping(value = "qklwcheck")
    @ResponseBody
    public List<String> qklwcheck(String id, HttpSession session) {
        UserInfo user = (UserInfo) session.getAttribute("USER_SESSION");
        Qklw qklw = qklwService.showQklw(user.getDepartment(), user.getNumber(), id);
        List<Qklw> qklwcheck = qklwService.selectQklwByDetail(qklw.getDepartment(),qklw.getNumber(),"-1","-1","-1","3","3");
        List<String> timus = new ArrayList<String>();
        for(int i = 0;i < qklwcheck.size(); i ++){

            double  score= CosineSimilarity.getSimilarity(qklw.getTimu(),qklwcheck.get(i).getTimu());
            System.out.println(score);
            if( score >= 0.8 ){
                if(timus.size()==0){
                    timus.add("待审核题目为："+qklw.getTimu());
                    timus.add("与以下题目相似度过高：");
                }
                timus.add(qklwcheck.get(i).getTimu());
            }
        }
        if(timus.size()==0){
            timus.add("待审核题目为："+qklw.getTimu());
            timus.add("未找到相似度过高的题目！");
        }

        return timus;
    }

    @GetMapping(value = "sqklwexportall")
    public void sqklwexportall(HttpSession session, HttpServletResponse response) {
        UserInfo user = (UserInfo) session.getAttribute("USER_SESSION");
        String department = user.getDepartment();
        String number = user.getNumber();
        String fileName =number +"-"+user.getName()+ "-期刊论文通过审核" + UUID.randomUUID().toString().replaceAll("-", "") + ".xls";
        HSSFWorkbook wb = qklwService.exportqklwExcel("-1", "-1", user.getRoletype());
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

    @PostMapping(value = "deletesqklw")
    @ResponseBody
    public Result deletesqklw(@RequestBody List<String> list, HttpSession session) {
        if (list.isEmpty()) {
            return new Result(1, "所选可删除内容为空");
        }
        int num = qklwService.deleteById(list);
        if (num!=0) {
            return new Result(1, "删除成功");
        } else {
            return new Result(0, "删除失败");
        }

    }
    //学术著作
    @GetMapping(value = "gosxszzpage")
    public String gosxszzpage(HttpSession session, Model model, @RequestParam(required = false, defaultValue = "1", value = "pageNum") Integer pageNum,
                              @RequestParam(defaultValue = "15", value = "pageSize") Integer pageSize,
                              @RequestParam(defaultValue = "-1", value = "department") String department,
                              @RequestParam(defaultValue = "-1", value = "number") String number,
                              @RequestParam(defaultValue = "-1", value = "start") String start,
                              @RequestParam(defaultValue = "-1", value = "end") String end,
                              @RequestParam(defaultValue = "-1", value = "judgestatus") String judgestatus,
                              @RequestParam(defaultValue = "-1", value = "timu") String timu,
                              @RequestParam(defaultValue = "-1", value = "dyzz") String dyzz,
                              @RequestParam(defaultValue = "-1", value = "qtzz") String qtzz,
                              @RequestParam(defaultValue = "-1", value = "cbstype") String cbstype,
                              @RequestParam(defaultValue = "-1", value = "zztype") String zztype,
                              @RequestParam(defaultValue = "-1", value = "times") String times){
        UserInfo user = (UserInfo) session.getAttribute("USER_SESSION");
        if (!user.getRoletype().equals("3")&& !user.getRoletype().equals("5")){
            return "pages/test";
        }
        //1.引入分页插件,pageNum是第几页，pageSize是每页显示多少条,默认查询总数count
        PageHelper.startPage(pageNum, pageSize);
        //2.紧跟的查询就是一个分页查询-必须紧跟.后面的其他查询不会被分页，除非再次调用PageHelper.startPage
        try {
            List<Xszz> xszzList = xszzService.selectXszzByDetail2(department,"-1",number,start,end,judgestatus,"3",timu,dyzz,qtzz,cbstype,zztype,times);
            //3.使用PageInfo包装查询后的结果,5是连续显示的条数,结果list类型是Page<E>
            PageInfo<Xszz> pageInfo = new PageInfo<Xszz>(xszzList, pageSize);
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
            if (!timu.equals("-1")) {
                model.addAttribute("timu", timu);
            }
            if (!dyzz.equals("-1")) {
                model.addAttribute("dyzz", dyzz);
            }
            if (!qtzz.equals("-1")) {
                model.addAttribute("qtzz", qtzz);
            }
            if (!cbstype.equals("-1")) {
                model.addAttribute("cbstype", cbstype);
            }
            if (!zztype.equals("-1")) {
                model.addAttribute("zztype", zztype);
            }
            if (!times.equals("-1")) {
                model.addAttribute("times", times);
            }
            List<Department> departments = departmentService.findAll();
            model.addAttribute("department", department);
            model.addAttribute("departments", departments);
            model.addAttribute("judgestatus", judgestatus);
        } finally {
            PageHelper.clearPage(); //清理 ThreadLocal 存储的分页参数,保证线程安全
        }

        return "school/schoolxszz";
    }

    @PostMapping(value = "xszzcheck")
    @ResponseBody
    public List<String> xszzcheck(String id, HttpSession session) {
        UserInfo user = (UserInfo) session.getAttribute("USER_SESSION");
        Xszz xszz = xszzService.showXszz(user.getDepartment(), user.getNumber(), id);
        List<Xszz> xszzcheck = xszzService.selectXszzByDetail(xszz.getDepartment(),xszz.getNumber(),"-1","-1","-1","3","3");
        List<String> timus = new ArrayList<String>();
        for(int i = 0;i < xszzcheck.size(); i ++){

            double  score= CosineSimilarity.getSimilarity(xszz.getTimu(),xszzcheck.get(i).getTimu());
            System.out.println(score);
            if( score >= 0.8 ){
                if(timus.size()==0){
                    timus.add("待审核题目为："+xszz.getTimu());
                    timus.add("与以下题目相似度过高：");
                }
                timus.add(xszzcheck.get(i).getTimu());
            }
        }
        if(timus.size()==0){
            timus.add("待审核题目为："+xszz.getTimu());
            timus.add("未找到相似度过高的题目！");
        }

        return timus;
    }

    @GetMapping(value = "sxszzexportall")
    public void sxszzexportall(HttpSession session, HttpServletResponse response) {
        UserInfo user = (UserInfo) session.getAttribute("USER_SESSION");
        String department = user.getDepartment();
        String number = user.getNumber();
        String fileName =number +"-"+user.getName()+ "-学术著作通过审核" + UUID.randomUUID().toString().replaceAll("-", "") + ".xls";
        HSSFWorkbook wb = xszzService.exportxszzExcel("-1", "-1", user.getRoletype());
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
    @PostMapping(value = "deletesxszz")
    @ResponseBody
    public Result deletesxszz(@RequestBody List<String> list, HttpSession session) {
        if (list.isEmpty()) {
            return new Result(1, "所选可删除内容为空");
        }
        int num = xszzService.deleteById(list);
        if (num!=0) {
            return new Result(1, "删除成功");
        } else {
            return new Result(0, "删除失败");
        }

    }

    //横向项目
    @GetMapping(value = "goshxxmpage")
    public String goshxxmpage(HttpSession session, Model model, @RequestParam(required = false, defaultValue = "1", value = "pageNum") Integer pageNum,
                              @RequestParam(defaultValue = "15", value = "pageSize") Integer pageSize,
                              @RequestParam(defaultValue = "-1", value = "department") String department,
                              @RequestParam(defaultValue = "-1", value = "number") String number,
                              @RequestParam(defaultValue = "-1", value = "start") String start,
                              @RequestParam(defaultValue = "-1", value = "end") String end,
                              @RequestParam(defaultValue = "-1", value = "judgestatus") String judgestatus,
                              @RequestParam(defaultValue = "-1", value = "XMName") String XMName,
                              @RequestParam(defaultValue = "-1", value = "Principal") String Principal,
                              @RequestParam(defaultValue = "-1", value = "AUnit") String AUnit,
                              @RequestParam(defaultValue = "-1", value = "ZJE") String ZJE,
                              @RequestParam(defaultValue = "-1", value = "ContractNumber") String ContractNumber,
                              @RequestParam(defaultValue = "-1", value = "JXTime") String JXTime){
        UserInfo user = (UserInfo) session.getAttribute("USER_SESSION");
        if (!user.getRoletype().equals("3")&& !user.getRoletype().equals("5")){
            return "pages/test";
        }
        //1.引入分页插件,pageNum是第几页，pageSize是每页显示多少条,默认查询总数count
        PageHelper.startPage(pageNum, pageSize);
        //2.紧跟的查询就是一个分页查询-必须紧跟.后面的其他查询不会被分页，除非再次调用PageHelper.startPage
        try {
            List<Hxxm> hylwList = hxxmService.selectHxxmByDetail2(department,"-1", number, start, end, judgestatus, "3",XMName,Principal,AUnit,ZJE,ContractNumber,JXTime);
            //3.使用PageInfo包装查询后的结果,5是连续显示的条数,结果list类型是Page<E>
            PageInfo<Hxxm> pageInfo = new PageInfo<Hxxm>(hylwList, pageSize);
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
            if (!Principal.equals("-1")) {
                model.addAttribute("Principal", Principal);
            }
            if (!AUnit.equals("-1")) {
                model.addAttribute("AUnit", AUnit);
            }
            if (!ZJE.equals("-1")) {
                model.addAttribute("ZJE", ZJE);
            }
            if (!ContractNumber.equals("-1")) {
                model.addAttribute("ContractNumber", ContractNumber);
            }
            if (!JXTime.equals("-1")) {
                model.addAttribute("QDTime", JXTime);
            }
            List<Department> departments = departmentService.findAll();
            model.addAttribute("department", department);
            model.addAttribute("departments", departments);
            model.addAttribute("judgestatus", judgestatus);
        } finally {
            PageHelper.clearPage(); //清理 ThreadLocal 存储的分页参数,保证线程安全
        }

        return "school/schoolhxxm";


    }

    @PostMapping(value = "hxxmcheck")
    @ResponseBody
    public List<String> hxxmcheck(String id, HttpSession session) {
        UserInfo user = (UserInfo) session.getAttribute("USER_SESSION");
        Hxxm hxxm = hxxmService.showHxxm(user.getDepartment(), user.getNumber(), id);
        List<Hxxm> hxxmcheck = hxxmService.selectHxxmByDetail(hxxm.getDepartment(),hxxm.getNumber(),"-1","-1","-1","3","3");
        List<String> timus = new ArrayList<String>();
        for(int i = 0;i < hxxmcheck.size(); i ++){

            double  score= CosineSimilarity.getSimilarity(hxxm.getXmname(),hxxmcheck.get(i).getXmname());
            System.out.println(score);
            if( score >= 0.8 ){
                if(timus.size()==0){
                    timus.add("待审核题目为："+hxxm.getXmname());
                    timus.add("与以下题目相似度过高：");
                }
                timus.add(hxxmcheck.get(i).getXmname());
            }
        }
        if(timus.size()==0){
            timus.add("待审核题目为："+hxxm.getXmname());
            timus.add("未找到相似度过高的题目！");
        }

        return timus;
    }

    @GetMapping(value = "shxxmexportall")
    public void shxxmexportall(HttpSession session, HttpServletResponse response) {
        UserInfo user = (UserInfo) session.getAttribute("USER_SESSION");
        String department = user.getDepartment();
        String number = user.getNumber();
        String fileName =number +"-"+user.getName()+ "-横向项目通过审核" + UUID.randomUUID().toString().replaceAll("-", "") + ".xls";
        HSSFWorkbook wb = hxxmService.exporthxxmExcel("-1", "-1", user.getRoletype());
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

    @PostMapping(value = "deleteshxxm")
    @ResponseBody
    public Result deleteshxxm(@RequestBody List<String> list, HttpSession session) {
        if (list.isEmpty()) {
            return new Result(1, "所选可删除内容为空");
        }
        int num = hxxmService.deleteById(list);
        if (num!=0) {
            return new Result(1, "删除成功");
        } else {
            return new Result(0, "删除失败");
        }

    }
}
