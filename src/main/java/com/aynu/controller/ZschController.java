package com.aynu.controller;

import com.aynu.bean.*;
import com.aynu.dto.Audit;
import com.aynu.dto.Result;
import com.aynu.dto.dtos.DepartmentOpinion;
import com.aynu.dto.dtos.SchoolOpinion;
import com.aynu.service.*;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @Author susuper
 * @Date 2020/9/21 21:09
 * @description:
 */
@RequestMapping(value = "zscq")
@Controller
@Slf4j
public class ZschController {
    @Autowired
    ZschService zschService;
    @Autowired
    DepartmentService departmentService;
    @Autowired
    TeacherDocService teacherDocService;
    @Autowired
    ApplynumService applynumService;
    @Autowired
    ApplyforallService applyforallService;

    @GetMapping(value = "gozscqpage")
    public String gozscqpage(HttpSession session, Model model, @RequestParam(required = false, defaultValue = "1", value = "pageNum") Integer pageNum,
                             @RequestParam(defaultValue = "15", value = "pageSize") Integer pageSize,
                             @RequestParam(defaultValue = "-1", value = "start") String start,
                             @RequestParam(defaultValue = "-1", value = "end") String end,
                             @RequestParam(defaultValue = "-1", value = "judgestatus") String judgestatus) {
        UserInfo user = (UserInfo) session.getAttribute("USER_SESSION");
        //1.引入分页插件,pageNum是第几页，pageSize是每页显示多少条,默认查询总数count
        PageHelper.startPage(pageNum, pageSize);
        //2.紧跟的查询就是一个分页查询-必须紧跟.后面的其他查询不会被分页，除非再次调用PageHelper.startPage
        try {
            List<Zscq> zscqList = zschService.selectZscqByDetail(user.getDepartment(), user.getNumber(), start, end, judgestatus, "1");
            //3.使用PageInfo包装查询后的结果,5是连续显示的条数,结果list类型是Page<E>
            PageInfo<Zscq> pageInfo = new PageInfo<Zscq>(zscqList, pageSize);
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

        return "pages/teacherpage";
    }

    @GetMapping(value = "gozscqwrite")
    public String gozscqwrite(HttpSession session, Model model) {
        UserInfo user = (UserInfo) session.getAttribute("USER_SESSION");
        List<Department> departments = departmentService.findAll();
        model.addAttribute("departments", departments);
//        StringBuffer notice = new StringBuffer("请注意本年度各类项目剩余数量。");
//        List<Applynum> applynums = applynumService.queryAll();
//        for (Applynum applynum : applynums) {
//            notice.append(applynum);
//        }
//        model.addAttribute("notice", notice);
//        notice = null;
        return "pages/teacherwrite";
    }


    @PostMapping(value = "putzscq")
    @ResponseBody
    public Result putzscq(@RequestBody Zscq zscq, HttpSession session) {
        UserInfo user = (UserInfo) session.getAttribute("USER_SESSION");
        zscq.setDepartment(user.getDepartment()).setName(user.getName()).setTusername(user.getNumber());



        boolean flag = zschService.insertZscq(zscq);
        if (flag) {
            return new Result(1, "添加成功");
        }
        return new Result(0, "添加失败");
    }

    @PostMapping(value = "deletezscq")
    @ResponseBody
    public Result deletezscq(@RequestBody List<String> list, HttpSession session) {
        UserInfo user = (UserInfo) session.getAttribute("USER_SESSION");
        System.out.println(list);
        boolean flag = zschService.deleteZscq(list, user.getNumber(), user.getDepartment());
        //先去找一下 干脆 查询出来  根据审核状态 进行判断 是否可以删除 放在前端处理？
        if (flag) {
            return new Result(1, "删除成功");
        } else {
            return new Result(0, "删除失败");
        }
    }

    @PostMapping(value = "zscqshow")
    @ResponseBody
    public Zscq hylwshow(String id, HttpSession session) {
        UserInfo user = (UserInfo) session.getAttribute("USER_SESSION");
        System.out.println(id);
        Zscq zscq = zschService.showZscq(user.getDepartment(), user.getNumber(), id);
        return zscq;
    }

    @PostMapping(value = "zscqupdate")
    @ResponseBody
    public Result hylwupdate(@RequestBody Zscq zscq, HttpSession session) {
        UserInfo user = (UserInfo) session.getAttribute("USER_SESSION");
        zscq.setDepartment(user.getDepartment()).setName(user.getName()).setTusername(user.getNumber());
        System.out.println(zscq);
        boolean flag = zschService.updateZscq(zscq);
        if (flag) {
            return new Result(1, "更新成功");
        } else {
            return new Result(0, "更新失败");
        }
    }


    @GetMapping(value = "godzscqpage")
    public String godzscqpage(HttpSession session, Model model, @RequestParam(required = false, defaultValue = "1", value = "pageNum") Integer pageNum,
                              @RequestParam(defaultValue = "15", value = "pageSize") Integer pageSize,
                              @RequestParam(defaultValue = "-1", value = "number") String number,

                              @RequestParam(defaultValue = "-1", value = "start") String start,
                              @RequestParam(defaultValue = "-1", value = "end") String end,
                              @RequestParam(defaultValue = "-1", value = "judgestatus") String judgestatus) {
        UserInfo user = (UserInfo) session.getAttribute("USER_SESSION");
        //1.引入分页插件,pageNum是第几页，pageSize是每页显示多少条,默认查询总数count
        PageHelper.startPage(pageNum, pageSize);
        //2.紧跟的查询就是一个分页查询-必须紧跟.后面的其他查询不会被分页，除非再次调用PageHelper.startPage
        try {
            List<Zscq> zscqList = zschService.selectZscqByDetail(user.getDepartment(), number, start, end, judgestatus, "2");
            //3.使用PageInfo包装查询后的结果,5是连续显示的条数,结果list类型是Page<E>
            PageInfo<Zscq> pageInfo = new PageInfo<Zscq>(zscqList, pageSize);
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
            model.addAttribute("judgestatus", judgestatus);
        } finally {
            PageHelper.clearPage(); //清理 ThreadLocal 存储的分页参数,保证线程安全
        }

        return "pages/departmentpage";
    }

    //院系 填写
    @GetMapping(value = "godzscqwrite")
    public String godzscqwrite(HttpSession session, Model model) {
        UserInfo user = (UserInfo) session.getAttribute("USER_SESSION");

        return "pages/departmentwrite";
    }

    //通用退回函数
    @PostMapping(value = "passnoauditzscq")
    @ResponseBody
    public Result passnoaudithylw(HttpSession session, @RequestBody Audit audit) {
        System.out.println(audit);
        boolean flag = zschService.updateZscqByHigh(audit);
        if (flag == true) {
            return new Result(1, "操作成功");
        } else {
            return new Result(0, "操作失败");
        }
    }


    @GetMapping(value = "goszscqpage")
    public String goszscqpage(HttpSession session, Model model, @RequestParam(required = false, defaultValue = "1", value = "pageNum") Integer pageNum,
                              @RequestParam(defaultValue = "15", value = "pageSize") Integer pageSize,
                              @RequestParam(defaultValue = "-1", value = "number") String number,
                              @RequestParam(defaultValue = "-1", value = "tcategory") String tcategory,
                              @RequestParam(defaultValue = "-1", value = "department") String department,
                              @RequestParam(defaultValue = "-1", value = "start") String start,
                              @RequestParam(defaultValue = "-1", value = "end") String end,
                              @RequestParam(defaultValue = "-1", value = "judgestatus") String judgestatus) {
        UserInfo user = (UserInfo) session.getAttribute("USER_SESSION");
        //1.引入分页插件,pageNum是第几页，pageSize是每页显示多少条,默认查询总数count
        PageHelper.startPage(pageNum, pageSize);
        //2.紧跟的查询就是一个分页查询-必须紧跟.后面的其他查询不会被分页，除非再次调用PageHelper.startPage
        try {
//            List<Zscq> zscqList = zschService.selectZscqByDetail(department, number, start, end, judgestatus, "3");
            List<Zscq> zscqList = zschService.selectZscqByDetails(department, number, tcategory,start, end, judgestatus, "3");
            //3.使用PageInfo包装查询后的结果,5是连续显示的条数,结果list类型是Page<E>
            PageInfo<Zscq> pageInfo = new PageInfo<Zscq>(zscqList, pageSize);
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
            if (!tcategory.equals("-1")) {
                model.addAttribute("tcategory", tcategory);
            }
            List<Department> departments = departmentService.findAll();
            model.addAttribute("departments", departments);
            model.addAttribute("department", department);
            model.addAttribute("judgestatus", judgestatus);
        } finally {
            PageHelper.clearPage(); //清理 ThreadLocal 存储的分页参数,保证线程安全
        }

        return "pages/schoolpage";
    }


    //院系 同意  和院系 不同意 都放过来
    @PostMapping(value = "deopinion")
    @ResponseBody
    public Result deopinion(HttpSession session, @RequestBody DepartmentOpinion departmentOpinion) {
        System.out.println(departmentOpinion);
        boolean flag = zschService.updateZschByDepartmentOpinion(departmentOpinion);
        if (flag == true) {
            return new Result(1, "操作成功");
        } else {
            return new Result(0, "操作失败");
        }
    }

    @PostMapping(value = "scopinion")
    @ResponseBody
    public Result scopinion(HttpSession session, @RequestBody SchoolOpinion schoolOpinion) {
        System.out.println(schoolOpinion);
        if (schoolOpinion.getJudgestatus().equals("5")) {  //5的时候 就是退回
            boolean flag = zschService.updateZschBySchoolOpinion(schoolOpinion);
            if (flag == true) {
                return new Result(1, "操作成功");
            } else {
                return new Result(0, "操作失败");
            }
        } else {
            //剩下的只有3了
//            Applynum applynum =  applynumService.queryByName(schoolOpinion.getTcategory());
//            if (applynum.getValue()<=0){
//                return new Result(0,"操作失败,本年度申请的"+applynum.getName()+"已经达到上限");
//            }else{
            boolean flag = zschService.updateZschBySchoolOpinion(schoolOpinion);
            if (flag == true) {
//                    applynum = applynum.setValue(applynum.getValue()-1);
//                    applynumService.updateApplynum(applynum);
                return new Result(1, "操作成功");
            } else {
                return new Result(0, "操作失败");
            }
//            }
        }
    }


    //获得院系数据
    @GetMapping(value = "goschoolupdate")
    public String goschoolupdate(String tid, Model model) {


        TeacherDoc teacherDoc = teacherDocService.selectTeacherDoc(null, null, tid);
        model.addAttribute("teacherDoc", teacherDoc);
        model.addAttribute("tid", tid);
        List<Department> departments = departmentService.findAll();
        model.addAttribute("departments", departments);

        return "pages/schoolupdate";
    }


    @PostMapping(value = "/schoolupdate")
    @ResponseBody
    public Result UpdateDoc(@RequestBody TeacherDoc teacherDoc) {
        log.info(teacherDoc.toString());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        teacherDoc.setAdate(sdf.format(new Date()));
        //需要 用到的 roletype  username tid
        teacherDoc.setRoletype("1");
        boolean flag = teacherDocService.UpdateDocforTeacherAll(teacherDoc);
        if (flag == true) {
            return new Result(1, "成功");
        }
        return new Result(0, "错误");
    }


}
