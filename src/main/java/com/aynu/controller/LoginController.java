package com.aynu.controller;

import com.aynu.annotation.OperationLogDetail;
import com.aynu.bean.*;
import com.aynu.dao.TdglDao;
import com.aynu.dto.Result;
import com.aynu.enums.OperationType;
import com.aynu.enums.OperationUnit;
import com.aynu.service.TdglService;
import com.aynu.service.UserInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author Susuper 果冻
 * @version 1.0
 * @date 2020/4/11 18:25
 * @description
 */
@Controller
@RequestMapping("/login")
public class LoginController {
    @Autowired
    UserInfoService userInfoService;
    //放在session里面 存储数据  后台判断一下 获取三个string 返回一个状态

    /**
     * 账户类型的判断  并且存储在了session里面 方便后面session的获取 后面可能会改这段代码
     * @param username
     * @param password
     * @param operator
     * @param httpSession
     * @return
     */

    private Logger logger = LoggerFactory.getLogger(this.getClass());//使用指定类初始化日志对象，在日志输出的时候，可以打印出日志信息所在类

    @RequestMapping(value = "/opelogin",method = RequestMethod.POST)
    @ResponseBody
    public Result Login(String username, String password, String operator, HttpSession httpSession){
        UserInfo user = userInfoService.UserLogin(username,operator);
        if (user!=null && user.getLoginstatus()!=null && user.getLoginstatus().equals("0")){
            return new Result(0,"此账号被停用");
        }
        if (user!=null && user.getPassword()!=null && user.getPassword().equals(password) ){
            httpSession.setAttribute("USER_SESSION",user);
            httpSession.setMaxInactiveInterval(60*10);
            if(operator.equals("1")){
                return new Result(1,"普通教师");
            }else if (operator.equals("2")){
                return new Result(2,"院系");
            }else if (operator.equals("3")){
                return new Result(3,"学校");
            }else if (operator.equals("4")){
                return new Result(4,"超管");
            }else if(operator.equals("5")){
                return new Result(5,"学校领导部分");
            }
        }
        return new Result(0,"类型选择或工号或密码错误");
    }

    /**
     * 退出 删除session
     * @param session
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "/lougout",method = RequestMethod.GET)
    public void lougout(HttpSession session, HttpServletResponse response) throws IOException {
        session.removeAttribute("USER_SESSION");
        response.sendRedirect("/approval/page/index");
//        return "/pages/index";
    }

    /**
     * 个人信息修改
     * @param session
     * @param userInfo
     * @return
     */
    @PostMapping(value = "/changePerson")
    @ResponseBody
//    @OperationLogDetail(detail = "修改了个人信息",level = 3,operationType = OperationType.UNKNOWN,operationUnit = OperationUnit.UNKNOWN)
    public Result changePerson(HttpSession session, @RequestBody UserInfo userInfo){
        //更新 user
        UserInfo user = (UserInfo) session.getAttribute("USER_SESSION");
        user.setName(userInfo.getName());
        session.removeAttribute("USER_SESSION");
        session.setAttribute("USER_SESSION",user);

        userInfo.setNumber(user.getNumber());
        userInfo.setRoletype(user.getRoletype());

        userInfoService.UpdateUserInfo(userInfo);
        return new Result(1,"ok");
    }

    /**
     * 账户密码修改
     * @param session
     * @param password
     * @return
     */
    @PostMapping(value = "/changePassword")
    @ResponseBody
//    @OperationLogDetail(detail = "修改了密码",level = 3,operationType = OperationType.UNKNOWN,operationUnit = OperationUnit.UNKNOWN)
    public Result changePassword(HttpSession session,String password){
        UserInfo user = (UserInfo) session.getAttribute("USER_SESSION");
        user.setPassword(password);

        userInfoService.UpdateUserPwd(user);

        session.removeAttribute("USER_SESSION");
        return new Result(1,"ok");
    }

    @PostMapping(value = "/canLoginUpdate")
    @ResponseBody
    public Result canLoginUpdate( String number,String loginstatus){
        logger.info("修改"+number+"是否可以登录："+loginstatus);
        if (userInfoService.UpdateUserLoginStatus(number,loginstatus)){
            return new Result(1,"修改成功");
        }else{
            return new Result(1,"修改失败");
        }

    }


    //读取properties的配置文件

}


//页面跳转 展示数据 文档填写 设计数据库
//文档的主键依赖于 账号  文档需要的各类数据 最后存储的长文档简介 手机号  insert 还需要有id 单独找个键跟账号主外键关联
//个人信息界面 update
