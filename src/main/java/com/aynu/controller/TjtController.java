package com.aynu.controller;

import com.aynu.bean.*;
import com.aynu.dao.*;
import com.aynu.service.DepartmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

/**
 * @Author susuper
 * @Date 2020/12/22 15:57
 * @description:
 */
@Controller
@RequestMapping(value = "/tjt")
@Slf4j
public class TjtController {
    @Autowired
    DepartmentService departmentService;
    @Autowired
    HylwDao hylwDao;
    @Autowired
    QklwDao qklwDao;
    @Autowired
    XszzDao xszzDao;
    @Autowired
    HpxmDao hpxmDao;
    @Autowired
    JxysDao jxysDao;
    @Autowired
    HxlxDao hxlxDao;
    @Autowired
    HxxmDao hxxmDao;
    @Autowired
    RjzzDao rjzzDao;
    @Autowired
    HpzlDao hpzlDao;
    @Autowired
    XschDao xschDao;
    @Autowired
    XsjlDao xsjlDao;
    @Autowired
    HpjlDao hpjlDao;
    @Autowired
    YmhjDao ymhjDao;
    @Autowired
    TyhjDao tyhjDao;
    @Autowired
    ZkjsDao zkjsDao;
    @Autowired
    CgzhDao cgzhDao;
    @Autowired
    TjbDao tjbDao;
    @Autowired
    TdglDao tdglDao;
    @Autowired
    TjtDao tjtDao;


    @GetMapping("/gotjtpage")
    public String gomxhzbpage(HttpSession session, Model model,
                              @RequestParam(defaultValue = "全部院系", value = "department") String department){
        UserInfo user = (UserInfo) session.getAttribute("USER_SESSION");
        List<Department> departments = departmentService.findAll();
        model.addAttribute("departments", departments);
        model.addAttribute("department", department);
//        System.out.println(user.get);
        if (user.getRoletype().equals("1")) {
            return "kytjcommon/tjt";
        } else if (user.getRoletype().equals("2")) {
            return "kytjcommon/dtjt";
        } else {
            return "kytjcommon/stjt";
        }
    }


    @PostMapping(value = "/getCountChart")
    @ResponseBody
    public List<CountChart> getCountCharts(@RequestParam(defaultValue = "全部院系", value = "department") String department,
                                           @RequestParam(value = "selecttype") String selecttype,
                                           @RequestParam(value = "yearstart") Integer yearstart,
                                           @RequestParam( value = "yearend") Integer yearend,
                                           HttpSession session){
        List<CountChart> countCharts = new LinkedList<>();
        UserInfo user = (UserInfo) session.getAttribute("USER_SESSION");
        String roleType = user.getRoletype();
        if (!roleType.equals("3") && !roleType.equals("5")){
            department = user.getDepartment();
        }

        if (selecttype.equals("全部")){
            countCharts = tjbDao.getCountChart(department,yearstart,yearend);
        }else{
            countCharts = tjtDao.getCountChart(selecttype,department,yearstart,yearend);
        }
        Integer start = Integer.valueOf(yearstart);
        Integer end = Integer.valueOf(yearend);
        List<CountChart> countCharts_new = new LinkedList<>();
        for (int i = yearstart;i<=yearend;i++){
            CountChart c = new CountChart();
            c.setCount(0);
            c.setYear(i);
            for (CountChart countChart:countCharts){
                if (countChart.getYear()==i){
                    c.setYear(countChart.getYear());
                    c.setCount(countChart.getCount());
                }
            }
            countCharts_new.add(c);
        }

        return countCharts_new;
    }



    @PostMapping(value = "/getSDepartmentBarChart")
    @ResponseBody
    public Map<String,List> getSDepartmentBarChart(@RequestParam(defaultValue = "全部院系", value = "department") String department,
                                                   @RequestParam(value = "selecttype") String selecttype,
                                           @RequestParam(value = "year") Integer year,
                                                   HttpSession session){
        UserInfo user = (UserInfo) session.getAttribute("USER_SESSION");
        String roleType = user.getRoletype();
        if (!roleType.equals("3") && !roleType.equals("5")){
            department = user.getDepartment();
        }
        List<BarChartDepartment> barChartDepartments = tjtDao.getSDepartmentBarChart(year,selecttype,department);
        List<BarChartTeacher> barChartTeachers = tjtDao.getSTeacherBarChart(year,selecttype,department);
        Map map = new HashMap();
        map.put("barChartDepartments",barChartDepartments);
        map.put("barChartTeachers",barChartTeachers);
        return map;
    }


    @PostMapping(value = "/getSTeacherBarChart")
    @ResponseBody
    public List<BarChartTeacher> getSTeacherBarChart(@RequestParam(defaultValue = "全部院系", value = "department") String department,
                                                     @RequestParam(value = "selecttype") String selecttype,
                                                     @RequestParam(value = "year") Integer year,
                                                     HttpSession session){
        UserInfo user = (UserInfo) session.getAttribute("USER_SESSION");
        String roleType = user.getRoletype();
        if (!roleType.equals("3") && !roleType.equals("5")){
            department = user.getDepartment();
        }
        List<BarChartTeacher> barChartTeachers = tjtDao.getSTeacherBarChart(year,selecttype,department);
        return barChartTeachers;
    }
}
