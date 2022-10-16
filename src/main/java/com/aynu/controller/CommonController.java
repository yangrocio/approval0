package com.aynu.controller;

import com.aynu.bean.UserInfo;
import com.aynu.dao.SysTimeDao;
import com.aynu.dto.Result;
import com.aynu.dto.SysTime;
import com.aynu.service.CommonMethodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

/**
 * @Author susuper
 * @Date 2020/10/20 21:53
 * @description:
 */
@Controller
@RequestMapping(value = "/common")
public class CommonController {
    @Autowired
    SysTimeDao sysTimeDao;
    @Autowired
    CommonMethodService commonMethodService;

    @GetMapping(value = "/getTimeOpe")
    @ResponseBody
    public Result getTimeOpe() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        SysTime sysTime = sysTimeDao.selectSysTime();

        Result result = new Result();

        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date startTime = ft.parse(sysTime.getStarttime());
            Date endTime = ft.parse(sysTime.getEndtime());
            Date nowTime = new Date();


            if (nowTime.getTime() == startTime.getTime() || nowTime.getTime() == endTime.getTime()) {
                result.setStatus(1).setDescription("可以操作");
            }

            Calendar date = Calendar.getInstance();
            date.setTime(nowTime);

            Calendar begin = Calendar.getInstance();
            begin.setTime(startTime);

            Calendar end = Calendar.getInstance();
            end.setTime(endTime);

            if (date.after(begin) && date.before(end)) {
                result.setStatus(1).setDescription("可以操作");
            } else {
                result.setStatus(0).setDescription("可操作时间为:" + sysTime.getStarttime() + "到" + sysTime.getEndtime());
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return result;
    }

    @GetMapping(value = "/getTime")
    @ResponseBody
    public SysTime getTime() {
        SysTime sysTime = sysTimeDao.selectSysTime();
        return sysTime;
    }

    @PostMapping(value = "/setTime")
    @ResponseBody
    public Result setTime(SysTime sysTime) {
        if (sysTimeDao.updateSysTime(sysTime)) {
            return new Result(1, "设置成功");
        } else {
            return new Result(0, "设置失败");
        }
    }

    @PostMapping(value = "/remainCount")
    @ResponseBody
    public Result remainCount(String selecttype, HttpSession session) {
        UserInfo user = (UserInfo) session.getAttribute("USER_SESSION");
        String s = null;
        int flag = 1;
        if (user.getRoletype().equals("1")) {
            int remainCount = commonMethodService.remainCount(selecttype, user.getNumber(), user.getDepartment(), "1");
            int remainCount2 = commonMethodService.remainCount(selecttype, user.getNumber(), user.getDepartment(), "2");
            s = "待院系审核：" + remainCount + "  待学校审核：" + remainCount2;
            if (remainCount==0 && remainCount2==0){
                flag = 0;
            }
        } else if (user.getRoletype().equals("2")) {
            int remainCount = commonMethodService.remainCount(selecttype, "-1", user.getDepartment(), "1");
            s = "待院系审核：" + remainCount;
            if (remainCount==0){
                flag=0;
            }
        } else if (user.getRoletype().equals("3")) {
            int remainCount = commonMethodService.remainCount(selecttype, "-1", "-1", "2");
            s = "待学校审核：" + remainCount;
            if (remainCount==0){
                flag=0;
            }
        }else if (user.getRoletype().equals("5")){
            return new Result(0,null);
        }
        return new Result(flag,s);
    }

}

