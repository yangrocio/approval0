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
@RequestMapping(value = "/ssssjump")
@Slf4j
public class SSSSJumpController {
    @Autowired
    CgService cgService;
    @Autowired
    HxlxService hxlxService;
    @Autowired
    JxysService jxysService;
    @Autowired
    HpxmService hpxmService;
    @Autowired
    CgzhService cgzhService;
    @Autowired
    ZkjsService zkjsService;
    @Autowired
    UploadController uploadController;
    @Autowired
    DepartmentService departmentService;

    @GetMapping(value = "goscgpage")
    public String goscgpage(HttpSession session, Model model, @RequestParam(required = false, defaultValue = "1", value = "pageNum") Integer pageNum,
                            @RequestParam(defaultValue = "15", value = "pageSize") Integer pageSize,
                            @RequestParam(defaultValue = "-1", value = "department") String department,
                            @RequestParam(defaultValue = "-1", value = "number") String number,
                            @RequestParam(defaultValue = "-1", value = "start") String start,
                            @RequestParam(defaultValue = "-1", value = "end") String end,
                            @RequestParam(defaultValue = "-1", value = "judgestatus") String judgestatus) {
        UserInfo user = (UserInfo) session.getAttribute("USER_SESSION");
        if (!user.getRoletype().equals("3")&& !user.getRoletype().equals("5")){
            return "pages/test";
        }
        //1.引入分页插件,pageNum是第几页，pageSize是每页显示多少条,默认查询总数count
        PageHelper.startPage(pageNum, pageSize);
        //2.紧跟的查询就是一个分页查询-必须紧跟.后面的其他查询不会被分页，除非再次调用PageHelper.startPage
        try {
            List<Cg> cgList = cgService.selectCgByDetail(department, number, start, end, judgestatus, "3");
            //3.使用PageInfo包装查询后的结果,5是连续显示的条数,结果list类型是Page<E>
            PageInfo<Cg> pageInfo = new PageInfo<Cg>(cgList, pageSize);
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
            List<Department> departments = departmentService.findAll();
            model.addAttribute("department", department);
            model.addAttribute("departments", departments);
            model.addAttribute("judgestatus", judgestatus);
        } finally {
            PageHelper.clearPage(); //清理 ThreadLocal 存储的分页参数,保证线程安全
        }

        return "school/schoolcg";
    }

    @GetMapping(value = "scgexportall")
    public void scgexportall(HttpSession session, HttpServletResponse response) {
        UserInfo user = (UserInfo) session.getAttribute("USER_SESSION");
        String department = user.getDepartment();
        String number = user.getNumber();
        String fileName = number + "-" + user.getName() + "-会议论文通过审核" + UUID.randomUUID().toString().replaceAll("-", "") + ".xls";
        HSSFWorkbook wb = cgService.exportcgExcel("-1", "-1", user.getRoletype());
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


    //纵向立项
    @GetMapping(value = "goshpxmpage")
    public String goshpxmpage(HttpSession session, Model model, @RequestParam(required = false, defaultValue = "1", value = "pageNum") Integer pageNum,
                              @RequestParam(defaultValue = "15", value = "pageSize") Integer pageSize,
                              @RequestParam(defaultValue = "-1", value = "department") String department,
                              @RequestParam(defaultValue = "-1", value = "number") String number,
                              @RequestParam(defaultValue = "-1", value = "start") String start,
                              @RequestParam(defaultValue = "-1", value = "end") String end,
                              @RequestParam(defaultValue = "-1", value = "judgestatus") String judgestatus,
                              @RequestParam(defaultValue = "-1", value = "xmname") String xmname,
                              @RequestParam(defaultValue = "-1", value = "hoster") String hoster,
                              @RequestParam(defaultValue = "-1", value = "cyry") String cyry,
                              @RequestParam(defaultValue = "-1", value = "xdbm") String xdbm,
                              @RequestParam(defaultValue = "-1", value = "xmsource") String xmsource,
                              @RequestParam(defaultValue = "-1", value = "prorank") String prorank,
                              @RequestParam(defaultValue = "-1", value = "proproperty") String proproperty,
                              @RequestParam(defaultValue = "-1", value = "lxtime") String lxtime){
        UserInfo user = (UserInfo) session.getAttribute("USER_SESSION");
        if (!user.getRoletype().equals("3")&& !user.getRoletype().equals("5")){
            return "pages/test";
        }
        //1.引入分页插件,pageNum是第几页，pageSize是每页显示多少条,默认查询总数count
        PageHelper.startPage(pageNum, pageSize);
        //2.紧跟的查询就是一个分页查询-必须紧跟.后面的其他查询不会被分页，除非再次调用PageHelper.startPage
        try {
            List<Hpxm> hpxms = hpxmService.selectHpxmByDetail2(department,"-1",number,start,end,judgestatus,"3",xmname,hoster,cyry,xdbm,xmsource,prorank,proproperty,lxtime);
            //3.使用PageInfo包装查询后的结果,5是连续显示的条数,结果list类型是Page<E>
            PageInfo<Hpxm> pageInfo = new PageInfo<Hpxm>(hpxms, pageSize);
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
            if (!xmname.equals("-1")) {
                model.addAttribute("xmname", xmname);
            }
            if (!hoster.equals("-1")) {
                model.addAttribute("hoster", hoster);
            }
            if (!cyry.equals("-1")) {
                model.addAttribute("cyry", cyry);
            }
            if (!xdbm.equals("-1")) {
                model.addAttribute("xdbm", xdbm);
            }
            if (!xmsource.equals("-1")) {
                model.addAttribute("xmsource", xmsource);
            }
            if (!prorank.equals("-1")) {
                model.addAttribute("prorank", prorank);
            }
            if (!proproperty.equals("-1")) {
                model.addAttribute("proproperty", proproperty);
            }
            if (!lxtime.equals("-1")) {
                model.addAttribute("lxtime", lxtime);
            }
            List<Department> departments = departmentService.findAll();
            model.addAttribute("department", department);
            model.addAttribute("departments", departments);
            model.addAttribute("judgestatus", judgestatus);
        } finally {
            PageHelper.clearPage(); //清理 ThreadLocal 存储的分页参数,保证线程安全
        }

        return "school/schoolhpxm";
    }


    @PostMapping(value = "hpxmcheck")
    @ResponseBody
    public List<String> hpxmcheck(String id, HttpSession session) {
        UserInfo user = (UserInfo) session.getAttribute("USER_SESSION");
        Hpxm hpxm = hpxmService.showHpxm(user.getDepartment(), user.getNumber(), id);
        List<Hpxm> hpxmcheck = hpxmService.selectHpxmByDetail(hpxm.getDepartment(),hpxm.getNumber(),"-1","-1","-1","3","3");
        List<String> timus = new ArrayList<String>();
        for(int i = 0;i < hpxmcheck.size(); i ++){

            double  score= CosineSimilarity.getSimilarity(hpxm.getXmname(),hpxmcheck.get(i).getXmname());
            System.out.println(score);
            if( score >= 0.8 ){
                if(timus.size()==0){
                    timus.add("待审核题目为："+hpxm.getXmname());
                    timus.add("与以下题目相似度过高：");
                }
                timus.add(hpxmcheck.get(i).getXmname());
            }
        }
        if(timus.size()==0){
            timus.add("待审核题目为："+hpxm.getXmname());
            timus.add("未找到相似度过高的题目！");
        }

        return timus;
    }

    @GetMapping(value = "shpxmexportall")
    public void shpxmexportall(HttpSession session, HttpServletResponse response) {
        UserInfo user = (UserInfo) session.getAttribute("USER_SESSION");
        String department = user.getDepartment();
        String number = user.getNumber();
        String fileName = number + "-" + user.getName() + "-获批项目通过审核" + UUID.randomUUID().toString().replaceAll("-", "") + ".xls";
        HSSFWorkbook wb = hpxmService.exporthpxmExcel("-1", "-1", user.getRoletype());
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

    @PostMapping(value = "deleteshpxm")
    @ResponseBody
    public Result deleteshpxm(@RequestBody List<String> list, HttpSession session) {
        if (list.isEmpty()) {
            return new Result(1, "所选可删除内容为空");
        }
        int num = hpxmService.deleteById(list);
        if (num!=0) {
            return new Result(1, "删除成功");
        } else {
            return new Result(0, "删除失败");
        }

    }
    //结项验收 纵向结项
    @GetMapping(value = "gosjxyspage")
    public String gosjxyspage(HttpSession session, Model model, @RequestParam(required = false, defaultValue = "1", value = "pageNum") Integer pageNum,
                              @RequestParam(defaultValue = "15", value = "pageSize") Integer pageSize,
                              @RequestParam(defaultValue = "-1", value = "department") String department,
                              @RequestParam(defaultValue = "-1", value = "number") String number,
                              @RequestParam(defaultValue = "-1", value = "start") String start,
                              @RequestParam(defaultValue = "-1", value = "end") String end,
                              @RequestParam(defaultValue = "-1", value = "judgestatus") String judgestatus,
                              @RequestParam(defaultValue = "-1", value = "xmname") String xmname,
                              @RequestParam(defaultValue = "-1", value = "hoster") String hoster,
                              @RequestParam(defaultValue = "-1", value = "cyry") String cyry,
                              @RequestParam(defaultValue = "-1", value = "xdbm") String xdbm,
                              @RequestParam(defaultValue = "-1", value = "xmly") String xmly,
                              @RequestParam(defaultValue = "-1", value = "rank") String rank,
                              @RequestParam(defaultValue = "-1", value = "proproperty") String proproperty,
                              @RequestParam(defaultValue = "-1", value = "time") String time) {
        UserInfo user = (UserInfo) session.getAttribute("USER_SESSION");
        if (!user.getRoletype().equals("3")&& !user.getRoletype().equals("5")){
            return "pages/test";
        }
        //1.引入分页插件,pageNum是第几页，pageSize是每页显示多少条,默认查询总数count
        PageHelper.startPage(pageNum, pageSize);
        //2.紧跟的查询就是一个分页查询-必须紧跟.后面的其他查询不会被分页，除非再次调用PageHelper.startPage
        try {
            List<Jxys> jxysList = jxysService.selectJxysByDetail2(department,"-1", number, start, end, judgestatus, "3",xmname,hoster,cyry,xdbm,xmly,rank,proproperty,time);
            //3.使用PageInfo包装查询后的结果,5是连续显示的条数,结果list类型是Page<E>
            PageInfo<Jxys> pageInfo = new PageInfo<Jxys>(jxysList, pageSize);
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
            if (!xmname.equals("-1")) {
                model.addAttribute("xmname", xmname);
            }
            if (!hoster.equals("-1")) {
                model.addAttribute("hoster", hoster);
            }
            if (!cyry.equals("-1")) {
                model.addAttribute("cyry", cyry);
            }
            if (!xdbm.equals("-1")) {
                model.addAttribute("xdbm", xdbm);
            }
            if (!xmly.equals("-1")) {
                model.addAttribute("xmly", xmly);
            }
            if (!rank.equals("-1")) {
                model.addAttribute("rank", rank);
            }
            if (!proproperty.equals("-1")) {
                model.addAttribute("proproperty", proproperty);
            }
            if (!time.equals("-1")) {
                model.addAttribute("time", time);
            }
            List<Department> departments = departmentService.findAll();
            model.addAttribute("department", department);
            model.addAttribute("departments", departments);
            model.addAttribute("judgestatus", judgestatus);
        } finally {
            PageHelper.clearPage(); //清理 ThreadLocal 存储的分页参数,保证线程安全
        }

        return "school/schooljxys";
    }

    @PostMapping(value = "jxyscheck")
    @ResponseBody
    public List<String> jxyscheck(String id, HttpSession session) {
        UserInfo user = (UserInfo) session.getAttribute("USER_SESSION");
        Jxys jxys = jxysService.showJxys(user.getDepartment(), user.getNumber(), id);
        List<Jxys> jxyscheck = jxysService.selectJxysByDetail(jxys.getDepartment(),jxys.getNumber(),"-1","-1","-1","3","3");
        List<String> timus = new ArrayList<String>();
        for(int i = 0;i < jxyscheck.size(); i ++){

            double  score= CosineSimilarity.getSimilarity(jxys.getXmname(),jxyscheck.get(i).getXmname());
            System.out.println(score);
            if( score >= 0.8 ){
                if(timus.size()==0){
                    timus.add("待审核题目为："+jxys.getXmname());
                    timus.add("与以下题目相似度过高：");
                }
                timus.add(jxyscheck.get(i).getXmname());
            }
        }
        if(timus.size()==0){
            timus.add("待审核题目为："+jxys.getXmname());
            timus.add("未找到相似度过高的题目！");
        }

        return timus;
    }

    @GetMapping(value = "sjxysexportall")
    public void sjxysexportall(HttpSession session, HttpServletResponse response) {
        UserInfo user = (UserInfo) session.getAttribute("USER_SESSION");
        String department = user.getDepartment();
        String number = user.getNumber();
        String fileName = number + "-" + user.getName() + "-结项验收通过审核" + UUID.randomUUID().toString().replaceAll("-", "") + ".xls";
        HSSFWorkbook wb = jxysService.exportcgExcel("-1", "-1", user.getRoletype());
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
    @PostMapping(value = "deletesjxys")
    @ResponseBody
    public Result deletesjxys(@RequestBody List<String> list, HttpSession session) {
        if (list.isEmpty()) {
            return new Result(1, "所选可删除内容为空");
        }
        int num = jxysService.deleteById(list);
        if (num!=0) {
            return new Result(1, "删除成功");
        } else {
            return new Result(0, "删除失败");
        }

    }
    //横向立项
    @GetMapping(value = "goshxlxpage")
    public String goshxlxpage(HttpSession session, Model model, @RequestParam(required = false, defaultValue = "1", value = "pageNum") Integer pageNum,
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
                              @RequestParam(defaultValue = "-1", value = "QDTime") String QDTime) {
        UserInfo user = (UserInfo) session.getAttribute("USER_SESSION");
        if (!user.getRoletype().equals("3")&& !user.getRoletype().equals("5")){
            return "pages/test";
        }
        //1.引入分页插件,pageNum是第几页，pageSize是每页显示多少条,默认查询总数count
        PageHelper.startPage(pageNum, pageSize);
        //2.紧跟的查询就是一个分页查询-必须紧跟.后面的其他查询不会被分页，除非再次调用PageHelper.startPage
        try {
            List<Hxlx> hxlxList = hxlxService.selectHxlxByDetail2(department,"-1", number, start, end, judgestatus, "3",XMName,Principal,AUnit,ZJE,ContractNumber,QDTime);
            //3.使用PageInfo包装查询后的结果,5是连续显示的条数,结果list类型是Page<E>
            PageInfo<Hxlx> pageInfo = new PageInfo<Hxlx>(hxlxList, pageSize);
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
            if (!QDTime.equals("-1")) {
                model.addAttribute("QDTime", QDTime);
            }

            List<Department> departments = departmentService.findAll();
            model.addAttribute("department", department);
            model.addAttribute("departments", departments);
            model.addAttribute("judgestatus", judgestatus);
        } finally {
            PageHelper.clearPage(); //清理 ThreadLocal 存储的分页参数,保证线程安全
        }

        return "school/schoolhxlx";
    }

    @PostMapping(value = "hxlxcheck")
    @ResponseBody
    public List<String> hxlxcheck(String id, HttpSession session) {
        UserInfo user = (UserInfo) session.getAttribute("USER_SESSION");
        Hxlx hxlx = hxlxService.showHxlx(user.getDepartment(), user.getNumber(), id);
        List<Hxlx> hxlxcheck = hxlxService.selectHxlxByDetail(hxlx.getDepartment(),hxlx.getNumber(),"-1","-1","-1","3","3");
        List<String> timus = new ArrayList<String>();
        for(int i = 0;i < hxlxcheck.size(); i ++){

            double  score= CosineSimilarity.getSimilarity(hxlx.getXmname(),hxlxcheck.get(i).getXmname());
            System.out.println(score);
            if( score >= 0.8 ){
                if(timus.size()==0){
                    timus.add("待审核题目为："+hxlx.getXmname());
                    timus.add("与以下题目相似度过高：");
                }
                timus.add(hxlxcheck.get(i).getXmname());
            }
        }
        if(timus.size()==0){
            timus.add("待审核题目为："+hxlx.getXmname());
            timus.add("未找到相似度过高的题目！");
        }

        return timus;
    }

    @GetMapping(value = "shxlxexportall")
    public void shxlxexportall(HttpSession session, HttpServletResponse response) {
        UserInfo user = (UserInfo) session.getAttribute("USER_SESSION");
        String department = user.getDepartment();
        String number = user.getNumber();
        String fileName = number + "-" + user.getName() + "-横向立项通过审核" + UUID.randomUUID().toString().replaceAll("-", "") + ".xls";
        HSSFWorkbook wb = hxlxService.exporthxlxExcel("-1", "-1", user.getRoletype());
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
    @PostMapping(value = "deleteshxlx")
    @ResponseBody
    public Result deleteshxlx(@RequestBody List<String> list, HttpSession session) {
        if (list.isEmpty()) {
            return new Result(1, "所选可删除内容为空");
        }
        int num = hxlxService.deleteById(list);
        if (num!=0) {
            return new Result(1, "删除成功");
        } else {
            return new Result(0, "删除失败");
        }

    }

    //成果转化


    @GetMapping(value = "goscgzhpage")
    public String goscgzhpage(HttpSession session, Model model, @RequestParam(required = false, defaultValue = "1", value = "pageNum") Integer pageNum,
                              @RequestParam(defaultValue = "15", value = "pageSize") Integer pageSize,
                              @RequestParam(defaultValue = "-1", value = "department") String department,
                              @RequestParam(defaultValue = "-1", value = "number") String number,
                              @RequestParam(defaultValue = "-1", value = "start") String start,
                              @RequestParam(defaultValue = "-1", value = "end") String end,
                              @RequestParam(defaultValue = "-1", value = "judgestatus") String judgestatus,
                              @RequestParam(defaultValue = "-1", value = "cgname") String cgname,
                              @RequestParam(defaultValue = "-1", value = "dyzz") String dyzz,
                              @RequestParam(defaultValue = "-1", value = "qtzz") String qtzz,
                              @RequestParam(defaultValue = "-1", value = "zhdw") String zhdw,
                              @RequestParam(defaultValue = "-1", value = "zrlx") String zrlx,
                              @RequestParam(defaultValue = "-1", value = "zhtime") String zhtime){
        UserInfo user = (UserInfo) session.getAttribute("USER_SESSION");
        if (!user.getRoletype().equals("3")&& !user.getRoletype().equals("5")){
            return "pages/test";
        }
        //1.引入分页插件,pageNum是第几页，pageSize是每页显示多少条,默认查询总数count
        PageHelper.startPage(pageNum, pageSize);
        //2.紧跟的查询就是一个分页查询-必须紧跟.后面的其他查询不会被分页，除非再次调用PageHelper.startPage
        try {
            List<Cgzh> cgzhList = cgzhService.selectCgzhByDetail2(department,"-1", number, start, end, judgestatus, "3",cgname,dyzz,qtzz,zhdw,zrlx,zhtime);
            //3.使用PageInfo包装查询后的结果,5是连续显示的条数,结果list类型是Page<E>
            PageInfo<Cgzh> pageInfo = new PageInfo<Cgzh>(cgzhList, pageSize);
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
            if (!cgname.equals("-1")) {
                model.addAttribute("cgname", cgname);
            }
            if (!dyzz.equals("-1")) {
                model.addAttribute("dyzz", dyzz);
            }
            if (!qtzz.equals("-1")) {
                model.addAttribute("qtzz", qtzz);
            }
            if (!zhdw.equals("-1")) {
                model.addAttribute("zhdw", zhdw);
            }
            if (!zrlx.equals("-1")) {
                model.addAttribute("zrlx", zrlx);
            }
            if (!zhtime.equals("-1")) {
                model.addAttribute("zhtime", zhtime);
            }
            List<Department> departments = departmentService.findAll();
            model.addAttribute("department", department);
            model.addAttribute("departments", departments);
            model.addAttribute("judgestatus", judgestatus);
        } finally {
            PageHelper.clearPage(); //清理 ThreadLocal 存储的分页参数,保证线程安全
        }

        return "school/schoolcgzh";
    }
    @PostMapping(value = "cgzhcheck")
    @ResponseBody
    public List<String> cgchcheck(String id, HttpSession session) {
        UserInfo user = (UserInfo) session.getAttribute("USER_SESSION");
        Cgzh cgch = cgzhService.showCgzh(user.getDepartment(), user.getNumber(), id);
        List<Cgzh> cgchcheck = cgzhService.selectCgzhByDetail(cgch.getDepartment(),cgch.getNumber(),"-1","-1","-1","3","3");
        List<String> timus = new ArrayList<String>();
        for(int i = 0;i < cgchcheck.size(); i ++){

            double  score= CosineSimilarity.getSimilarity(cgch.getCgname(),cgchcheck.get(i).getCgname());
            System.out.println(score);
            if( score >= 0.8 ){
                if(timus.size()==0){
                    timus.add("待审核转让成果名称为："+cgch.getCgname());
                    timus.add("转让成果名称相似度过高：");
                }
                timus.add(cgchcheck.get(i).getCgname());
            }
        }
        if(timus.size()==0){
            timus.add("待审核转让成果名称为："+cgch.getCgname());
            timus.add("未找到相似度过高的转让成果名称！");
        }

        return timus;
    }
    @GetMapping(value = "scgzhexportall")
    public void scgzhexportall(HttpSession session, HttpServletResponse response) {
        UserInfo user = (UserInfo) session.getAttribute("USER_SESSION");
        String department = user.getDepartment();
        String number = user.getNumber();
        String fileName = number + "-" + user.getName() + "-成果转化通过审核" + UUID.randomUUID().toString().replaceAll("-", "") + ".xls";
        HSSFWorkbook wb = cgzhService.exportcgzhExcel("-1", "-1", user.getRoletype());
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
    @PostMapping(value = "deletescgzh")
    @ResponseBody
    public Result deletescgzh(@RequestBody List<String> list, HttpSession session) {
        if (list.isEmpty()) {
            return new Result(1, "所选可删除内容为空");
        }
        int num = cgzhService.deleteById(list);
        if (num!=0) {
            return new Result(1, "删除成功");
        } else {
            return new Result(0, "删除失败");
        }

    }

    //智库建设
    @GetMapping(value = "goszkjspage")
    public String goszkjspage(HttpSession session, Model model, @RequestParam(required = false, defaultValue = "1", value = "pageNum") Integer pageNum,
                              @RequestParam(defaultValue = "15", value = "pageSize") Integer pageSize,
                              @RequestParam(defaultValue = "-1", value = "department") String department,
                              @RequestParam(defaultValue = "-1", value = "number") String number,
                              @RequestParam(defaultValue = "-1", value = "start") String start,
                              @RequestParam(defaultValue = "-1", value = "end") String end,
                              @RequestParam(defaultValue = "-1", value = "judgestatus") String judgestatus,
                              @RequestParam(defaultValue = "-1", value = "cgname") String cgname,
                              @RequestParam(defaultValue = "-1", value = "cnjb") String cnjb,
                              @RequestParam(defaultValue = "-1", value = "dyzz") String dyzz,
                              @RequestParam(defaultValue = "-1", value = "qtzz") String qtzz,
                              @RequestParam(defaultValue = "-1", value = "cnbm") String cnbm,
                              @RequestParam(defaultValue = "-1", value = "cntime") String cntime) {
        UserInfo user = (UserInfo) session.getAttribute("USER_SESSION");
        if (!user.getRoletype().equals("3")&& !user.getRoletype().equals("5")){
            return "pages/test";
        }
        //1.引入分页插件,pageNum是第几页，pageSize是每页显示多少条,默认查询总数count
        PageHelper.startPage(pageNum, pageSize);
        //2.紧跟的查询就是一个分页查询-必须紧跟.后面的其他查询不会被分页，除非再次调用PageHelper.startPage
        try {
            List<Zkjs> zkjsList = zkjsService.selectZkjsByDetail2(department, "-1",number, start, end, judgestatus, "3",cgname,cnjb,dyzz,qtzz,cnbm,cntime);
            //3.使用PageInfo包装查询后的结果,5是连续显示的条数,结果list类型是Page<E>
            PageInfo<Zkjs> pageInfo = new PageInfo<Zkjs>(zkjsList, pageSize);
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
            if (!cgname.equals("-1")) {
                model.addAttribute("cgname", cgname);
            }
            if (!cnjb.equals("-1")) {
                model.addAttribute("cnjb", cnjb);
            }
            if (!dyzz.equals("-1")) {
                model.addAttribute("dyzz", dyzz);
            }
            if (!qtzz.equals("-1")) {
                model.addAttribute("qtzz", qtzz);
            }
            if (!cnbm.equals("-1")) {
                model.addAttribute("cnbm", cnbm);
            }
            if (!cntime.equals("-1")) {
                model.addAttribute("cntime", cntime);
            }
            List<Department> departments = departmentService.findAll();
            model.addAttribute("department", department);
            model.addAttribute("departments", departments);
            model.addAttribute("judgestatus", judgestatus);
        } finally {
            PageHelper.clearPage(); //清理 ThreadLocal 存储的分页参数,保证线程安全
        }

        return "school/schoolzkjs";
    }
    @PostMapping(value = "zkjscheck")
    @ResponseBody
    public List<String> zkjscheck(String id, HttpSession session) {
        UserInfo user = (UserInfo) session.getAttribute("USER_SESSION");
        Zkjs zkjs = zkjsService.showZkjs(user.getDepartment(), user.getNumber(), id);
        List<Zkjs> zkjscheck = zkjsService.selectZkjsByDetail(zkjs.getDepartment(),zkjs.getNumber(),"-1","-1","-1","3","3");
        List<String> timus = new ArrayList<String>();
        for(int i = 0;i < zkjscheck.size(); i ++){

            double  score= CosineSimilarity.getSimilarity(zkjs.getCgname(),zkjscheck.get(i).getCgname());
            System.out.println(score);
            if( score >= 0.8 ){
                if(timus.size()==0){
                    timus.add("待审核成果名称为："+zkjs.getCgname());
                    timus.add("与以下成果名称相似度过高：");
                }
                timus.add(zkjscheck.get(i).getCgname());
            }
        }
        if(timus.size()==0){
            timus.add("待审核成果名称为："+zkjs.getCgname());
            timus.add("未找到相似度过高的成果名称！");
        }

        return timus;
    }

    @GetMapping(value = "szkjsexportall")
    public void szkjsexportall(HttpSession session, HttpServletResponse response) {
        UserInfo user = (UserInfo) session.getAttribute("USER_SESSION");
        String department = user.getDepartment();
        String number = user.getNumber();
        String fileName = number + "-" + user.getName() + "-智库建设通过审核" + UUID.randomUUID().toString().replaceAll("-", "") + ".xls";
        HSSFWorkbook wb = zkjsService.exportzkjsExcel("-1", "-1", user.getRoletype());
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
    @PostMapping(value = "deleteszkjs")
    @ResponseBody
    public Result deleteszkjs(@RequestBody List<String> list, HttpSession session) {
        if (list.isEmpty()) {
            return new Result(1, "所选可删除内容为空");
        }
        int num = zkjsService.deleteById(list);
        if (num!=0) {
            return new Result(1, "删除成功");
        } else {
            return new Result(0, "删除失败");
        }

    }
}
