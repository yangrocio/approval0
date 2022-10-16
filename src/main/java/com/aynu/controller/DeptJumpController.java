package com.aynu.controller;

import com.aynu.bean.Department;
import com.aynu.bean.TeacherDoc;
import com.aynu.bean.UserInfo;
import com.aynu.service.DepartmentService;
import com.aynu.service.NoticeService;
import com.aynu.service.TeacherDocService;
import com.aynu.service.UserInfoService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author susuper
 * @version 1.0
 * @date 2020/9/5 23:16
 */
@Controller
@RequestMapping(value = "/departjump")
@Slf4j
public class DeptJumpController {
    @Autowired
    TeacherDocService teacherDocService;
    @Autowired
    UserInfoService userInfoService;
    @Autowired
    DepartmentService departmentService;

    @Autowired
    NoticeService noticeService;

    @GetMapping(value = "/goDepartPeopByDetail")
    public String goDocByDetail(HttpSession session, Model model,
                                @RequestParam(required = false, defaultValue = "1", value = "pageNum") Integer pageNum,
                                @RequestParam(defaultValue = "15", value = "pageSize") Integer pageSize,
                                @RequestParam(value = "id") String id,
                                @RequestParam(value = "name") String name) {
        PageHelper.startPage(pageNum, pageSize);
        UserInfo user = (UserInfo) session.getAttribute("USER_SESSION");
        String department = user.getDepartment();
        try {
            //院系用系统 默认 权限输入-1  代表不考虑
            List<UserInfo> userInfos = userInfoService.selectSchoolDetail(department, id, name, "-1");
            //3.使用PageInfo包装查询后的结果,5是连续显示的条数,结果list类型是Page<E>
            PageInfo<UserInfo> pageInfo = new PageInfo<UserInfo>(userInfos, pageSize);
            //4.使用model/map/modelandview等带回前端
            model.addAttribute("pageInfo", pageInfo);

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

        } finally {
            PageHelper.clearPage(); //清理 ThreadLocal 存储的分页参数,保证线程安全
        }
        //添加再model上一个数据  select 处添加
        //查询出来 所有教师的信息
        return "pages/deppeoplepage";
    }


    @GetMapping(value = "/goDepartDocByDetail")
    public String goDepartDocByDetail(HttpSession session, Model model,
                                      @RequestParam(required = false, defaultValue = "1", value = "pageNum") Integer pageNum,
                                      @RequestParam(defaultValue = "15", value = "pageSize") Integer pageSize,
                                      @RequestParam(value = "id") String id,
                                      @RequestParam(value = "name") String name) {
        PageHelper.startPage(pageNum, pageSize);
        UserInfo user = (UserInfo) session.getAttribute("USER_SESSION");
        String department = user.getDepartment();
        try {
            List<TeacherDoc> departmentOld = teacherDocService.selectDepartmentDocDtoByDetail(-1, 2, -1, 4, 5, 100, department, id, name);
            //3.使用PageInfo包装查询后的结果,5是连续显示的条数,结果list类型是Page<E>
            PageInfo<TeacherDoc> pageInfo = new PageInfo<TeacherDoc>(departmentOld, pageSize);

            //4.使用model/map/modelandview等带回前端
            model.addAttribute("pageInfo", pageInfo);

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

        } finally {
            PageHelper.clearPage(); //清理 ThreadLocal 存储的分页参数,保证线程安全
        }
        //添加再model上一个数据  select 处添加
        //查询出来 所有教师的信息
        return "pages/departmentpage";
    }


    //主申请页面 展示数据
    @GetMapping(value = "/goTeacherPageByDetail")
    public String goTeacherPageByDetail(HttpSession session, Model model,
                                        @RequestParam(required = false, defaultValue = "1", value = "pageNum") Integer pageNum,
                                        @RequestParam(defaultValue = "15", value = "pageSize") Integer pageSize,
                                        @RequestParam(defaultValue = "-1", value = "start") String start,
                                        @RequestParam(defaultValue = "-1", value = "end") String end,
                                        @RequestParam(defaultValue = "-1", value = "judgestatus") String judgestatus) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");

        Date startDate = sdf.parse(start);
        Date endtDate = sdf.parse(end);
        PageHelper.startPage(pageNum, pageSize);
        UserInfo user = (UserInfo) session.getAttribute("USER_SESSION");
        sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            List<TeacherDoc> departmentOld = teacherDocService.selectDocByDetail(sdf.format(startDate), sdf.format(endtDate), "1", user.getNumber());
            //3.使用PageInfo包装查询后的结果,5是连续显示的条数,结果list类型是Page<E>
            PageInfo<TeacherDoc> pageInfo = new PageInfo<TeacherDoc>(departmentOld, pageSize);

            //4.使用model/map/modelandview等带回前端
            model.addAttribute("pageInfo", pageInfo);
        } finally {
            PageHelper.clearPage(); //清理 ThreadLocal 存储的分页参数,保证线程安全
        }

        return "pages/teacherpage";
    }
}
