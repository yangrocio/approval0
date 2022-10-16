package com.aynu.controller;

import com.aynu.Utils.Freemark;
import com.aynu.Utils.Translate;
import com.aynu.annotation.OperationLogDetail;
import com.aynu.bean.*;
import com.aynu.dto.DepartmentDocDto;
import com.aynu.dto.TeacherDocDto;
import com.aynu.enums.OperationType;
import com.aynu.enums.OperationUnit;
import com.aynu.service.*;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.net.URLEncoder;
import java.util.*;

/**
 * @author Susuper 果冻
 * @version 1.0
 * @date 2020/4/11 22:48
 * @description 负责所有的跳转页面
 */
@Controller
@RequestMapping(value = "/jump")
@Slf4j
public class JumpController {
    @Autowired
    TeacherDocService teacherDocService;
    @Autowired
    UserInfoService userInfoService;
    @Autowired
    DepartmentService departmentService;
    @Autowired
    ProfessionService professionService;

    @Autowired
    NoticeService noticeService;
    @Autowired
    ApplynumService applynumService;


    @GetMapping(value = "/kygllogin")
    public String LoginSuccess(HttpSession session){
        //跳转个首页吧
        UserInfo user = (UserInfo) session.getAttribute("USER_SESSION");
        String status = user.getRoletype();
        String department = user.getDepartment();
        if (status.equals("1")) {
            return "redirect:/tjump/gohylwpage";
        }else if (status.equals("2")){
            return "redirect:/djump/godhylwpage";
        }else if (status.equals("3")){
            return "redirect:/sjump/goshylwpage";
        }else if (status.equals("4")){
            return "redirect:/super/gosuperpage";
//            return "redirect:loginSuccess";
//            return "pages/superpage";
        }
        else if (status.equals("5")){
            return "redirect:/sjump/goshylwpage";
        }
        return "pages/login";
    }

    /**
     * 登录函数 携带数据跳转到各个界面
     *
     * @param session
     * @param model
     * @return
     */
    @RequestMapping(value = "/loginSuccess", method = RequestMethod.GET)
    public String LoginSuccess(HttpSession session, Model model, @RequestParam(required = false, defaultValue = "1", value = "pageNum") Integer pageNum,
                               @RequestParam(defaultValue = "15", value = "pageSize") Integer pageSize,
                               @RequestParam(defaultValue = "-1", value = "teaname") String teaname) {
        UserInfo user = (UserInfo) session.getAttribute("USER_SESSION");
        String status = user.getRoletype();
        String department = user.getDepartment();
        if (status.equals("1")) {
            return "redirect:/zscq/gozscqpage";
        } else if (status.equals("2")) {
            return "redirect:/zscq/godzscqpage";
        } else if (status.equals("3")) {
            return "redirect:/zscq/goszscqpage";
        } else if (status.equals("4")) {
            return "redirect:/super/gosuperpage";
        }else if (status.equals("5")){
            return "redirect:/zscq/goszscqpage";
        }
        return "pages/login";
    }




    // 学校 页面  查询 文档  细节查询
    @GetMapping(value = "/goDocByDetail")
    public String goDocByDetail(HttpSession session, Model model,
                                @RequestParam(required = false, defaultValue = "1", value = "pageNum") Integer pageNum,
                                @RequestParam(defaultValue = "15", value = "pageSize") Integer pageSize,
                                @RequestParam(value = "department") String department, @RequestParam(value = "id") String id,
                                @RequestParam(value = "name") String name){
        PageHelper.startPage(pageNum, pageSize);
        try {
            List<TeacherDoc> departmentOld  = teacherDocService.selectDepartmentDocDtoByDetail(1, -1, -1, 4, 5, 100,department,id,name);
            //3.使用PageInfo包装查询后的结果,5是连续显示的条数,结果list类型是Page<E>
            PageInfo<TeacherDoc> pageInfo = new PageInfo<TeacherDoc>(departmentOld, pageSize);
            //4.使用model/map/modelandview等带回前端
            model.addAttribute("pageInfo", pageInfo);
            model.addAttribute("department", department);
            if (id.equals("-1")) {
                model.addAttribute("id", null);
            } else {
                model.addAttribute("id", id);
            }
            if (name.equals("-1")) {
                model.addAttribute("name", null);
            } else {
                model.addAttribute("name", name);
            }
            List<Department> departments = departmentService.findAll();
            model.addAttribute("departments", departments);
        } finally {
            PageHelper.clearPage(); //清理 ThreadLocal 存储的分页参数,保证线程安全
        }
        //添加再model上一个数据  select 处添加
        //查询出来 所有教师的信息
        return "pages/schoolpage";
    }


    //根据关键字查询
    @GetMapping(value = "/goSchoolPeopByDetail")
    public String goSchoolPeopByDetail(HttpSession session, Model model,
                                       @RequestParam(required = false, defaultValue = "1", value = "pageNum") Integer pageNum,
                                       @RequestParam(defaultValue = "15", value = "pageSize") Integer pageSize,
                                       @RequestParam(value = "department") String department, @RequestParam(value = "id") String id,
                                       @RequestParam(value = "name") String name,
                                       @RequestParam(value = "roletype") String roletype) {
        PageHelper.startPage(pageNum, pageSize);
        try {
            //把参数都传递过去  让 sql语句进行判断
            List<UserInfo> userInfos = userInfoService.selectSchoolDetail(department, id, name, roletype);

            //3.使用PageInfo包装查询后的结果,5是连续显示的条数,结果list类型是Page<E>
            PageInfo<UserInfo> pageInfo = new PageInfo<UserInfo>(userInfos, pageSize);
            //4.使用model/map/modelandview等带回前端
            model.addAttribute("pageInfo", pageInfo);
            model.addAttribute("department", department);
            if (id.equals("-1")) {
                model.addAttribute("id", null);
            } else {
                model.addAttribute("id", id);
            }
            if (name.equals("-1")) {
                model.addAttribute("name", null);
            } else {
                model.addAttribute("name", name);
            }
            List<Department> departments = departmentService.findAll();
            model.addAttribute("departments", departments);
            model.addAttribute("roletype", roletype);
        } finally {
            PageHelper.clearPage(); //清理 ThreadLocal 存储的分页参数,保证线程安全
        }
        //添加再model上一个数据  select 处添加
        //查询出来 所有教师的信息
        return "pages/schoolpeoplepage";
    }

    //根据  到超级管理区关键字查询
    @GetMapping(value = "/goSuperPeopByDetail")
    public String goSuperPeopByDetail(HttpSession session, Model model,
                                       @RequestParam(required = false, defaultValue = "1", value = "pageNum") Integer pageNum,
                                       @RequestParam(defaultValue = "15", value = "pageSize") Integer pageSize,
                                       @RequestParam(value = "department") String department, @RequestParam(value = "id") String id,
                                       @RequestParam(value = "name") String name) {
        PageHelper.startPage(pageNum, pageSize);
        try {
            //把参数都传递过去  让 sql语句进行判断
            List<UserInfo> userInfos = userInfoService.selectSchoolDetail(department, id, name,"-1");

            //3.使用PageInfo包装查询后的结果,5是连续显示的条数,结果list类型是Page<E>
            PageInfo<UserInfo> pageInfo = new PageInfo<UserInfo>(userInfos, pageSize);
            //4.使用model/map/modelandview等带回前端
            model.addAttribute("pageInfo", pageInfo);
            model.addAttribute("department", department);
            if (id.equals("-1")) {
                model.addAttribute("id", null);
            } else {
                model.addAttribute("id", id);
            }
            if (name.equals("-1")) {
                model.addAttribute("name", null);
            } else {
                model.addAttribute("name", name);
            }
            List<Department> departments = departmentService.findAll();
            model.addAttribute("departments", departments);
        } finally {
            PageHelper.clearPage(); //清理 ThreadLocal 存储的分页参数,保证线程安全
        }
        //添加再model上一个数据  select 处添加
        //查询出来 所有教师的信息
        return "pages/superpage";
    }


    //学校 层 根据   估计用不上了
    @GetMapping(value = "/goSchoolByName")
    public String goSchoolByName(HttpSession session, Model model,
                                 @RequestParam(required = false, defaultValue = "1", value = "pageNum") Integer pageNum,
                                 @RequestParam(defaultValue = "15", value = "pageSize") Integer pageSize,
                                 @RequestParam(defaultValue = "-1", value = "teaname") String teaname) {
        PageHelper.startPage(pageNum, pageSize);

        //传递过来
        try {
            List<UserInfo> userInfos;
            if (teaname.equals("-1")) {
                userInfos = userInfoService.SelectUserInfoAll();
            } else {
                userInfos = userInfoService.SelectUserInfosByNumber(teaname);
            }

            //3.使用PageInfo包装查询后的结果,5是连续显示的条数,结果list类型是Page<E>
            PageInfo<UserInfo> pageInfo = new PageInfo<UserInfo>(userInfos, pageSize);
            //4.使用model/map/modelandview等带回前端
            model.addAttribute("pageInfo", pageInfo);
            List<Department> departments = departmentService.findAll();
            model.addAttribute("departments", departments);

        } finally {
            PageHelper.clearPage(); //清理 ThreadLocal 存储的分页参数,保证线程安全
        }
        //查询出来 所有教师的信息
        return "pages/schoolpeoplepage";
    }

    //查询所有列表
    @GetMapping(value = "/goListPeople")
    public String goListPeople(HttpSession session, Model model,
                               @RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum,
                               @RequestParam(defaultValue = "15", value = "pageSize") Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);

        try {
            List<UserInfo> userInfos = userInfoService.SelectUserInfoAll();
            //3.使用PageInfo包装查询后的结果,5是连续显示的条数,结果list类型是Page<E>
            PageInfo<UserInfo> pageInfo = new PageInfo<UserInfo>(userInfos, pageSize);
            //4.使用model/map/modelandview等带回前端
            model.addAttribute("pageInfo", pageInfo);

            model.addAttribute("department", "请选择");
            model.addAttribute("id", null);
            model.addAttribute("name", null);


            List<Department> departments = departmentService.findAll();
            model.addAttribute("departments", departments);
            model.addAttribute("roletype", "-1");

        } finally {
            PageHelper.clearPage(); //清理 ThreadLocal 存储的分页参数,保证线程安全
        }
        //查询出来 所有教师的信息
        return "pages/schoolpeoplepage";
    }

    //查询所有列表  只需要 把对应多查询方法 给添加上分页就可以了  后面甚至可以直接合并这两个方法
    @GetMapping(value = "/goListPeoplePlus")
    public String goListPeoplePlus(HttpSession session, Model model,
                                   @RequestParam(required = false, defaultValue = "1", value = "pageNum") Integer pageNum,
                                   @RequestParam(defaultValue = "15", value = "pageSize") Integer pageSize,
                                   @RequestParam(value = "department") String department, @RequestParam(value = "id") String id,
                                   @RequestParam(value = "name") String name,
                                   @RequestParam(value = "roletype") String roletype) {
        PageHelper.startPage(pageNum, pageSize);

        try {
            //把参数都传递过去  让 sql语句进行判断
            List<UserInfo> userInfos = userInfoService.selectSchoolDetail(department, id, name, roletype);
            //3.使用PageInfo包装查询后的结果,5是连续显示的条数,结果list类型是Page<E>
            PageInfo<UserInfo> pageInfo = new PageInfo<UserInfo>(userInfos, pageSize);
            //4.使用model/map/modelandview等带回前端
            model.addAttribute("pageInfo", pageInfo);
            model.addAttribute("department", department);
            if (id.equals("-1")) {
                model.addAttribute("id", null);
            } else {
                model.addAttribute("id", id);
            }
            if (name.equals("-1")) {
                model.addAttribute("name", null);
            } else {
                model.addAttribute("name", name);
            }
            List<Department> departments = departmentService.findAll();
            model.addAttribute("departments", departments);

            model.addAttribute("roletype", roletype);

        } finally {
            PageHelper.clearPage(); //清理 ThreadLocal 存储的分页参数,保证线程安全
        }
        //查询出来 所有教师的信息
        return "pages/schoolpeoplepage";
    }


    /**
     * 跳转到 教师写文档界面
     *
     * @return
     */
    @RequestMapping(value = "/goApply", method = RequestMethod.GET)
    public String goApply(Model model) {
        List<Department> departments = departmentService.findAll();
        model.addAttribute("departments", departments);
        return "pages/teacherwrite";
    }

    /**
     * 跳转到私人信息界面
     *
     * @param session
     * @param model
     * @return
     */
    @RequestMapping(value = "/personMessage", method = RequestMethod.GET)
    public String personMessage(HttpSession session, Model model) {
        UserInfo user = (UserInfo) session.getAttribute("USER_SESSION");

        UserInfo userInfo = userInfoService.SelectUserInfo(user.getNumber(), user.getRoletype());
        model.addAttribute("userInfo", userInfo);

        List<Department> departments = departmentService.findAll();
        model.addAttribute("departments", departments);

        List<Profession> professions = professionService.findAll();
        model.addAttribute("professions",professions);

        return "pages/personmessage";
    }

    /**
     * 跳转到 教师预览当前文档界面
     *
     * @param model
     * @param session
     * @param tid
     * @return
     */
    @GetMapping(value = "/goTeaDoc")
    public String goTeaDoc(Model model, HttpSession session, String tid) {
        UserInfo user = (UserInfo) session.getAttribute("USER_SESSION");
        String tnumber = user.getNumber();
        String roleType = user.getRoletype();

        TeacherDoc teacherDoc = teacherDocService.selectTeacherDoc(tnumber, roleType, tid);
        model.addAttribute("teacherDoc", teacherDoc);
        model.addAttribute("tid", tid);

        List<Department> departments = departmentService.findAll();
        model.addAttribute("departments", departments);
        return "pages/teacherwritedisabled";
    }

    /**
     * 删除 这个文档 暂时还没有完成  TODO不敢使用
     *
     * @param session
     * @param tid
     * @return
     */
    @GetMapping(value = "/deleteDoc")
//    @OperationLogDetail(detail = "删除申请",level = 3,operationType = OperationType.DELETE,operationUnit = OperationUnit.USER)
    public String deleteDoc(HttpSession session, String tid) {
        UserInfo user = (UserInfo) session.getAttribute("USER_SESSION");
        String tnumber = user.getNumber();
        String roleType = user.getRoletype();

        boolean flag = teacherDocService.deleteTeacherDoc(tnumber, roleType, tid);

        if (flag == true) {
            return "pages/index";
        }

        return "pages/teacherwrite";
    }

    /**
     * 院系查看文档界面
     *
     * @param tid
     * @param model
     * @return
     */
    @GetMapping(value = "/readdepartmentDoc")
    public String readdepartmentDoc(String tid, Model model) {
        TeacherDoc teacherDoc = teacherDocService.selectTeacherDocSingle(tid);
        model.addAttribute("teacherDoc", teacherDoc);
        List<Department> departments = departmentService.findAll();
        model.addAttribute("departments", departments);
        return "pages/departmentwrite";
    }


    /**
     * 学校查看文档界面
     *
     * @param tid
     * @param model
     * @return
     */
    @GetMapping(value = "/readSchoolDoc")
    public String readSchoolDoc(String tid, Model model) {
        TeacherDoc teacherDoc = teacherDocService.selectTeacherDocSingle(tid);
        model.addAttribute("teacherDoc", teacherDoc);
        List<Department> departments = departmentService.findAll();
        model.addAttribute("departments", departments);
        StringBuffer notice = new StringBuffer("请注意你要申请的项目剩余数量，可能会无法审批通过。本年度");
        List<Applynum> applynums = applynumService.queryAll();
        for (Applynum applynum:applynums){
            notice.append(applynum);
        }
        model.addAttribute("notice",notice);
        notice = null;
        return "pages/schoolwrite";
    }





    //院系界面   查询所有教师
    @GetMapping(value = "/departmentListPeople")
    public String departmentListPeople(HttpSession session, Model model, @RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum,
                                       @RequestParam(defaultValue = "15", value = "pageSize") Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);

        UserInfo user = (UserInfo) session.getAttribute("USER_SESSION");
        try {
            List<UserInfo> userInfos = userInfoService.selectUserInfoByDepart(user.getDepartment());
            //3.使用PageInfo包装查询后的结果,5是连续显示的条数,结果list类型是Page<E>
            PageInfo<UserInfo> pageInfo = new PageInfo<UserInfo>(userInfos, pageSize);
            //4.使用model/map/modelandview等带回前端
            model.addAttribute("pageInfo", pageInfo);
        } finally {
            PageHelper.clearPage(); //清理 ThreadLocal 存储的分页参数,保证线程安全
        }


        //查询出来 所有教师的信息
        return "pages/deppeoplepage";
    }

    //学校管理员查看公告   携带上数据部分过去  直接传递出来数据
    @GetMapping(value = "/goNoticeManage")
    public String goNoticeManage(HttpSession session, Model model, @RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum,
                                 @RequestParam(defaultValue = "15", value = "pageSize") Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        UserInfo user = (UserInfo) session.getAttribute("USER_SESSION");

        try {
            List<Notice> notices = noticeService.selectNoticeAll(user.getNumber(), false);
            //3.使用PageInfo包装查询后的结果,5是连续显示的条数,结果list类型是Page<E>
            PageInfo<Notice> pageInfo = new PageInfo<Notice>(notices, pageSize);
            //4.使用model/map/modelandview等带回前端
            model.addAttribute("pageInfo", pageInfo);
        } finally {
            PageHelper.clearPage(); //清理 ThreadLocal 存储的分页参数,保证线程安全
        }

        return "pages/schoolnotice";
    }


    // 教师查询公告
    @GetMapping(value = "/goTeacherNoticeManage")
    public String goTeacherNoticeManage(HttpSession session, Model model, @RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum,
                                        @RequestParam(defaultValue = "15", value = "pageSize") Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        UserInfo user = (UserInfo) session.getAttribute("USER_SESSION");

        try {
            List<Notice> notices = noticeService.selectNoticeAll(user.getNumber(), true);
            //3.使用PageInfo包装查询后的结果,5是连续显示的条数,结果list类型是Page<E>
            PageInfo<Notice> pageInfo = new PageInfo<Notice>(notices, pageSize);
            //4.使用model/map/modelandview等带回前端
            model.addAttribute("pageInfo", pageInfo);
        } finally {
            PageHelper.clearPage(); //清理 ThreadLocal 存储的分页参数,保证线程安全
        }

        return "pages/teachernotice";
    }


    //院系管理员查看公告
    @GetMapping(value = "/goDepartmentNoticeManage")
    public String goDepartmentNoticeManage(HttpSession session, Model model, @RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum,
                                           @RequestParam(defaultValue = "15", value = "pageSize") Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        UserInfo user = (UserInfo) session.getAttribute("USER_SESSION");

        try {
            List<Notice> notices = noticeService.selectNoticeAll(user.getNumber(), true);
            //3.使用PageInfo包装查询后的结果,5是连续显示的条数,结果list类型是Page<E>
            PageInfo<Notice> pageInfo = new PageInfo<Notice>(notices, pageSize);
            //4.使用model/map/modelandview等带回前端
            model.addAttribute("pageInfo", pageInfo);
        } finally {
            PageHelper.clearPage(); //清理 ThreadLocal 存储的分页参数,保证线程安全
        }

        return "pages/departmentnotice";
    }


    @GetMapping(value = "/temp")
    public String jumptoTemp() {
        System.out.println("跳转个界面");
        return "pages/new";
    }

    @GetMapping(value = "/goTeaBonusPage")
    public String goTeaBonusPage() {
        return "pages/teacherbonus";
    }
}
