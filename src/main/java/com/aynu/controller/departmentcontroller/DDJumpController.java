package com.aynu.controller.departmentcontroller;

import com.aynu.bean.*;
import com.aynu.dto.Audit;
import com.aynu.dto.Result;
import com.aynu.service.*;
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
 * @Author susuper
 * @Date 2020/9/21 12:40
 * @description:
 */
@Controller
@RequestMapping(value = "/ddjump")
@Slf4j
public class DDJumpController {
    @Autowired
    RjzzService rjzzService;
    @Autowired
    HpzlService hpzlService;
    @Autowired
    XschService xschService;
    @Autowired
    XsjlService xsjlService;

    @GetMapping(value = "godrjzzpage")
    public String godrjzzpage(HttpSession session, Model model, @RequestParam(required = false, defaultValue = "1", value = "pageNum") Integer pageNum,
                              @RequestParam(defaultValue = "15", value = "pageSize") Integer pageSize,
                              @RequestParam(defaultValue = "-1", value = "number") String number,
                              @RequestParam(defaultValue = "-1", value = "start") String start,
                              @RequestParam(defaultValue = "-1", value = "end") String end,
                              @RequestParam(defaultValue = "-1", value = "judgestatus") String judgestatus){
        UserInfo user = (UserInfo) session.getAttribute("USER_SESSION");
        if (!user.getRoletype().equals("2")){
            return "pages/test";
        }
        //1.引入分页插件,pageNum是第几页，pageSize是每页显示多少条,默认查询总数count
        PageHelper.startPage(pageNum, pageSize);
        //2.紧跟的查询就是一个分页查询-必须紧跟.后面的其他查询不会被分页，除非再次调用PageHelper.startPage
        try {
            List<Rjzz> rjzzes = rjzzService.selectRjzzByDetail(user.getDepartment(),"-1",number,start,end,judgestatus,"2");
            //3.使用PageInfo包装查询后的结果,5是连续显示的条数,结果list类型是Page<E>
            PageInfo<Rjzz> pageInfo = new PageInfo<Rjzz>(rjzzes, pageSize);
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
            model.addAttribute("judgestatus", judgestatus);
        } finally {
            PageHelper.clearPage(); //清理 ThreadLocal 存储的分页参数,保证线程安全
        }

        return "department/departmentrjzz";
    }

    @PostMapping(value = "passauditrjzz")
    @ResponseBody
    public Result passauditrjzz(HttpSession session, @RequestBody Audit audit){
        System.out.println(audit);
        boolean flag = rjzzService.updateRjzzByHigh(audit);
        if (flag == true){
            return new Result(1,"操作成功");
        }else{
            return new Result(0,"操作失败");
        }

    }

    @PostMapping(value = "passnoauditrjzz")
    @ResponseBody
    public Result passnoauditrjzz(HttpSession session, @RequestBody Audit audit){
        System.out.println(audit);
        boolean flag = rjzzService.updateRjzzByHigh(audit);
        if (flag == true){
            return new Result(1,"操作成功");
        }else{
            return new Result(0,"操作失败");
        }
    }



    @GetMapping(value = "godhpzlpage")
    public String godhpzlpage(HttpSession session, Model model, @RequestParam(required = false, defaultValue = "1", value = "pageNum") Integer pageNum,
                              @RequestParam(defaultValue = "15", value = "pageSize") Integer pageSize,
                              @RequestParam(defaultValue = "-1", value = "number") String number,
                              @RequestParam(defaultValue = "-1", value = "start") String start,
                              @RequestParam(defaultValue = "-1", value = "end") String end,
                              @RequestParam(defaultValue = "-1", value = "judgestatus") String judgestatus){
        UserInfo user = (UserInfo) session.getAttribute("USER_SESSION");
        if (!user.getRoletype().equals("2")){
            return "pages/test";
        }
        //1.引入分页插件,pageNum是第几页，pageSize是每页显示多少条,默认查询总数count
        PageHelper.startPage(pageNum, pageSize);
        //2.紧跟的查询就是一个分页查询-必须紧跟.后面的其他查询不会被分页，除非再次调用PageHelper.startPage
        try {
            List<Hpzl> hpzls = hpzlService.selectHpzlByDetail(user.getDepartment(),"-1",number,start,end,judgestatus,"2");
            //3.使用PageInfo包装查询后的结果,5是连续显示的条数,结果list类型是Page<E>
            PageInfo<Hpzl> pageInfo = new PageInfo<Hpzl>(hpzls, pageSize);
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
            model.addAttribute("judgestatus", judgestatus);
        } finally {
            PageHelper.clearPage(); //清理 ThreadLocal 存储的分页参数,保证线程安全
        }

        return "department/departmenthpzl";
    }

    @PostMapping(value = "passaudithpzl")
    @ResponseBody
    public Result passaudithpzl(HttpSession session, @RequestBody Audit audit){
        System.out.println(audit);
        boolean flag = hpzlService.updateHpzlByHigh(audit);
        if (flag == true){
            return new Result(1,"操作成功");
        }else{
            return new Result(0,"操作失败");
        }

    }

    @PostMapping(value = "passnoaudithpzl")
    @ResponseBody
    public Result passnoaudithpzl(HttpSession session, @RequestBody Audit audit){
        System.out.println(audit);
        boolean flag = hpzlService.updateHpzlByHigh(audit);

        if (flag == true){
            return new Result(1,"操作成功");
        }else{
            return new Result(0,"操作失败");
        }
    }



    @GetMapping(value = "godxschpage")
    public String godxschpage(HttpSession session, Model model, @RequestParam(required = false, defaultValue = "1", value = "pageNum") Integer pageNum,
                              @RequestParam(defaultValue = "15", value = "pageSize") Integer pageSize,
                              @RequestParam(defaultValue = "-1", value = "number") String number,
                              @RequestParam(defaultValue = "-1", value = "start") String start,
                              @RequestParam(defaultValue = "-1", value = "end") String end,
                              @RequestParam(defaultValue = "-1", value = "judgestatus") String judgestatus){
        UserInfo user = (UserInfo) session.getAttribute("USER_SESSION");
        if (!user.getRoletype().equals("2")){
            return "pages/test";
        }
        //1.引入分页插件,pageNum是第几页，pageSize是每页显示多少条,默认查询总数count
        PageHelper.startPage(pageNum, pageSize);
        //2.紧跟的查询就是一个分页查询-必须紧跟.后面的其他查询不会被分页，除非再次调用PageHelper.startPage
        try {
            List<Xsch> xsches = xschService.selectXschByDetail(user.getDepartment(),"-1",number,start,end,judgestatus,"2");
            //3.使用PageInfo包装查询后的结果,5是连续显示的条数,结果list类型是Page<E>
            PageInfo<Xsch> pageInfo = new PageInfo<Xsch>(xsches, pageSize);
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
            model.addAttribute("judgestatus", judgestatus);
        } finally {
            PageHelper.clearPage(); //清理 ThreadLocal 存储的分页参数,保证线程安全
        }

        return "department/departmentxsch";
    }

    @PostMapping(value = "passauditxsch")
    @ResponseBody
    public Result passauditxsch(HttpSession session, @RequestBody Audit audit){
        System.out.println(audit);
        boolean flag = xschService.updateXschByHigh(audit);
        if (flag == true){
            return new Result(1,"操作成功");
        }else{
            return new Result(0,"操作失败");
        }

    }

    @PostMapping(value = "passnoauditxsch")
    @ResponseBody
    public Result passnoauditxsch(HttpSession session, @RequestBody Audit audit){
        System.out.println(audit);
        boolean flag = xschService.updateXschByHigh(audit);

        if (flag == true){
            return new Result(1,"操作成功");
        }else{
            return new Result(0,"操作失败");
        }
    }



    @GetMapping(value = "godxsjlpage")
    public String godxsjlpage(HttpSession session, Model model, @RequestParam(required = false, defaultValue = "1", value = "pageNum") Integer pageNum,
                              @RequestParam(defaultValue = "15", value = "pageSize") Integer pageSize,
                              @RequestParam(defaultValue = "-1", value = "number") String number,
                              @RequestParam(defaultValue = "-1", value = "start") String start,
                              @RequestParam(defaultValue = "-1", value = "end") String end,
                              @RequestParam(defaultValue = "-1", value = "judgestatus") String judgestatus){
        UserInfo user = (UserInfo) session.getAttribute("USER_SESSION");
        if (!user.getRoletype().equals("2")){
            return "pages/test";
        }
        //1.引入分页插件,pageNum是第几页，pageSize是每页显示多少条,默认查询总数count
        PageHelper.startPage(pageNum, pageSize);
        //2.紧跟的查询就是一个分页查询-必须紧跟.后面的其他查询不会被分页，除非再次调用PageHelper.startPage
        try {
            List<Xsjl> xsjls = xsjlService.selectXsjlByDetail(user.getDepartment(),"-1",number,start,end,judgestatus,"2");
            //3.使用PageInfo包装查询后的结果,5是连续显示的条数,结果list类型是Page<E>
            PageInfo<Xsjl> pageInfo = new PageInfo<Xsjl>(xsjls, pageSize);
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
            model.addAttribute("judgestatus", judgestatus);
        } finally {
            PageHelper.clearPage(); //清理 ThreadLocal 存储的分页参数,保证线程安全
        }

        return "department/departmentxsjl";
    }

    @PostMapping(value = "passauditxsjl")
    @ResponseBody
    public Result passauditxsjl(HttpSession session, @RequestBody Audit audit){
        System.out.println(audit);
        boolean flag = xsjlService.updateXsjlByHigh(audit);
        if (flag == true){
            return new Result(1,"操作成功");
        }else{
            return new Result(0,"操作失败");
        }

    }

    @PostMapping(value = "passnoauditxsjl")
    @ResponseBody
    public Result passnoauditxsjl(HttpSession session, @RequestBody Audit audit){
        System.out.println(audit);
        boolean flag = xsjlService.updateXsjlByHigh(audit);
        if (flag == true){
            return new Result(1,"操作成功");
        }else{
            return new Result(0,"操作失败");
        }
    }
}
