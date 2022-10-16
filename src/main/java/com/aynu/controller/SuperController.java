package com.aynu.controller;

import com.aynu.bean.*;
import com.aynu.dto.Result;
import com.aynu.service.ApplyforallService;
import com.aynu.service.ApplynumService;
import com.aynu.service.DepartmentService;
import com.aynu.service.UserInfoService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;


/**
 * @author Susuper 果冻
 * @version 1.0
 * @date 2020/4/18 11:59
 * @description 超管界面
 * 负责
 * 重置密码、导入账号、删除账号、修改账号类型
 */
@Controller
@RequestMapping("/super")
@Slf4j
public class SuperController {
    @Autowired
    UserInfoService userInfoService;

    @Autowired
    DepartmentService departmentService;
    @Autowired
    ApplynumService applynumService;
    @Autowired
    ApplyforallService applyforallService;


    @GetMapping(value = "gosuperpage")
    public String gozscqpage(HttpSession session, Model model, @RequestParam(required = false, defaultValue = "1", value = "pageNum") Integer pageNum,
                             @RequestParam(defaultValue = "15", value = "pageSize") Integer pageSize,
                             @RequestParam(defaultValue = "请选择", value = "department") String department,
                             @RequestParam(defaultValue = "-1", value = "id") String number,
                             @RequestParam(defaultValue = "-1", value = "name") String name) {
        UserInfo user = (UserInfo) session.getAttribute("USER_SESSION");
        //1.引入分页插件,pageNum是第几页，pageSize是每页显示多少条,默认查询总数count
        PageHelper.startPage(pageNum, pageSize);
        //2.紧跟的查询就是一个分页查询-必须紧跟.后面的其他查询不会被分页，除非再次调用PageHelper.startPage
        try {
            List<UserInfo> userInfos = userInfoService.selectSchoolDetail(department,number,name,"-1");
//                    userInfoService.selectUserInfoByDetail(user.getDepartment(), user.getNumber(), start, end, judgestatus, "1");
            //3.使用PageInfo包装查询后的结果,5是连续显示的条数,结果list类型是Page<E>
            PageInfo<UserInfo> pageInfo = new PageInfo<UserInfo>(userInfos, pageSize);
            //4.使用model/map/modelandview等带回前端
            model.addAttribute("pageInfo", pageInfo);
            List<Department> departments = departmentService.findAll();
            model.addAttribute("departments", departments);

            if (number.equals("-1")){
                number="";
            }
            if (name.equals("-1")){
                name="";
            }
            model.addAttribute("department", department);
            model.addAttribute("number", number);
            model.addAttribute("name", name);
        } finally {
            PageHelper.clearPage(); //清理 ThreadLocal 存储的分页参数,保证线程安全
        }

        return "pages/superpage";
    }


    @ResponseBody
    @PostMapping(value = "/updateAllApply")
    public Result updateAllApply(String name, Integer value) {
        log.info(name + ":" + value);
        boolean flag = applyforallService.updateApplyAll(name, value);
        if (flag) {
            return new Result(1, "更新成功");
        }
        return new Result(0, "更新失败");
    }

    @ResponseBody
    @PostMapping(value = "/updateApplyBynumber")
    public Result updateApplyBynumber(String name, Integer value, String number) {
        log.info(name + ":" + value + ":" + number);
        boolean flag = applyforallService.updateApplyByNumber(name, value, number);
        if (flag) {
            return new Result(1, "更新成功");
        }
        return new Result(0, "更新失败");
    }

    @ResponseBody
    @PostMapping(value = "/selectApplyByNumber")
    public Applyforall selectApplyByNumber(String number) {
        log.info(number + "查询申请数据，用于更新");
        Applyforall applyforall = applyforallService.selectApplyByNumber(number);
        return applyforall;
    }


    @RequestMapping(value = "/resetpassword")
    @ResponseBody
//    @OperationLogDetail(detail = "超级管理员重置职工[{{number}}]的密码为123",level = 3,operationType = OperationType.SELECT,operationUnit = OperationUnit.UNKNOWN)
    public Result resetpassword(String number, String roletype, String password) {
        System.out.println(number + ":" + roletype);
        UserInfo user = new UserInfo();
        user.setNumber(number);
        user.setPassword(password);
        user.setRoletype(roletype);
        boolean flag = userInfoService.UpdateUserPwd(user);
        if (flag) {
            return new Result(1, "ok");
        }
        return new Result(0, "no ok");
    }

    @PostMapping(value = "/updatePermissions")
    @ResponseBody
//    @OperationLogDetail(detail = "超级管理员更改职工[{{number}}]的权限",level = 3,operationType = OperationType.SELECT,operationUnit = OperationUnit.UNKNOWN)
    public Result updatePermissions(String number, String roletype, String newroletype) {
        System.out.println(number + ":" + roletype + ":" + newroletype);

        User u = new User();
        u.setNumber(number);
        u.setRoletype(roletype);
        boolean flag = userInfoService.UpdateUserRoleType(u, newroletype);
        if (flag) {
            return new Result(1, "ok");
        } else {
            return new Result(0, "no ok");
        }
    }


    //需要有一个判断是否存在账号的
    @GetMapping(value = "/JudgeNumber")
    @ResponseBody
    public Result JudgeNumber(String number) {
        User user = userInfoService.FindUserExist(number);
        if (user != null) {
            return new Result(0, "账号已存在");
        } else {
            return new Result(1, "账号不存在，可以使用");
        }
    }

    //需要有一个负责注册账号的
    @PostMapping(value = "/CreateUser")
    @ResponseBody
//    @OperationLogDetail(detail = "超级管理员注册账号",level = 3,operationType = OperationType.SELECT,operationUnit = OperationUnit.UNKNOWN)
    public Result CreateUser(UserInfo userInfo) {
        userInfo.setLoginstatus("1");
        userInfo.setFmzl(50);
        userInfo.setXxzl(5);
        userInfo.setSjzl(5);
        userInfo.setRjzzq(5);
        userInfo.setBtsj(5);
        userInfo.setQtsj(5);
        boolean flag = userInfoService.InsertUser(userInfo);


        if (flag) {
            return new Result(1, "ok");
        } else {
            return new Result(0, "no ok");
        }
    }


    //实现数据备份  应该也是需要下载文件 然后删除文件
    //  数据备份


    @GetMapping(value = "/GetApplyAll")
    @ResponseBody
    public List<Applynum> GetApplyAll() {
        List<Applynum> applynums = applynumService.queryAll();
        System.out.println(applynums);
        return applynums;
    }

    @PostMapping(value = "/updateApplyNum")
    @ResponseBody
    public Result updateApplyNum(@RequestBody Applynum applynum) {
        System.out.println(applynum);
        boolean flag = applynumService.updateApplynum(applynum);
        if (flag) {
            return new Result(1, "更新成功");
        }
        return new Result(0, "更新失败");
    }


}
