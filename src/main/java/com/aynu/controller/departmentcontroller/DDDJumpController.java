package com.aynu.controller.departmentcontroller;

import com.aynu.bean.Hpjl;
import com.aynu.bean.Tyhj;
import com.aynu.bean.UserInfo;
import com.aynu.bean.Ymhj;
import com.aynu.dto.Audit;
import com.aynu.dto.Result;
import com.aynu.service.CommonMethodService;
import com.aynu.service.HpjlService;
import com.aynu.service.TyhjService;
import com.aynu.service.YmhjService;
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
@RequestMapping(value = "/dddjump")
@Slf4j
public class DDDJumpController {
    @Autowired
    HpjlService hpjlService;
    @Autowired
    YmhjService ymhjService;
    @Autowired
    TyhjService tyhjService;


    @GetMapping(value = "godhpjlpage")
    public String godhylwpage(HttpSession session, Model model, @RequestParam(required = false, defaultValue = "1", value = "pageNum") Integer pageNum,
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
            List<Hpjl> hpjls = hpjlService.selectHpjlByDetail(user.getDepartment(),"-1",number,start,end,judgestatus,"2");
            //3.使用PageInfo包装查询后的结果,5是连续显示的条数,结果list类型是Page<E>
            PageInfo<Hpjl> pageInfo = new PageInfo<Hpjl>(hpjls, pageSize);
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

        return "department/departmenthpjl";
    }

    @PostMapping(value = "passaudithpjl")
    @ResponseBody
    public Result passaudithpjl(HttpSession session, @RequestBody Audit audit){
        System.out.println(audit);
        boolean flag = hpjlService.updateHpjlByHigh(audit);
        if (flag == true){
            return new Result(1,"操作成功");
        }else{
            return new Result(0,"操作失败");
        }

    }

    @PostMapping(value = "passnoaudithpjl")
    @ResponseBody
    public Result passnoaudithpjl(HttpSession session, @RequestBody Audit audit){
        System.out.println(audit);
        boolean flag = hpjlService.updateHpjlByHigh(audit);
        if (flag == true){
            return new Result(1,"操作成功");
        }else{
            return new Result(0,"操作失败");
        }
    }




    @GetMapping(value = "godymhjpage")
    public String godymhjpage(HttpSession session, Model model, @RequestParam(required = false, defaultValue = "1", value = "pageNum") Integer pageNum,
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
            List<Ymhj> hylwList = ymhjService.selectYmhjByDetail(user.getDepartment(),"-1",number,start,end,judgestatus,"2");
            //3.使用PageInfo包装查询后的结果,5是连续显示的条数,结果list类型是Page<E>
            PageInfo<Ymhj> pageInfo = new PageInfo<Ymhj>(hylwList, pageSize);
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

        return "department/departmentymhj";
    }

    @PostMapping(value = "passauditymhj")
    @ResponseBody
    public Result passauditymhj(HttpSession session, @RequestBody Audit audit){
        System.out.println(audit);
        boolean flag = ymhjService.updateYmhjByHigh(audit);
        if (flag == true){
            return new Result(1,"操作成功");
        }else{
            return new Result(0,"操作失败");
        }

    }

    @PostMapping(value = "passnoauditymhj")
    @ResponseBody
    public Result passnoauditymhj(HttpSession session, @RequestBody Audit audit){
        System.out.println(audit);
        boolean flag = ymhjService.updateYmhjByHigh(audit);
        if (flag == true){
            return new Result(1,"操作成功");
        }else{
            return new Result(0,"操作失败");
        }
    }

    @GetMapping(value = "godtyhjpage")
    public String godtyhjpage(HttpSession session, Model model, @RequestParam(required = false, defaultValue = "1", value = "pageNum") Integer pageNum,
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
            List<Tyhj> tyhjs = tyhjService.selectTyhjByDetail(user.getDepartment(),"-1",number,start,end,judgestatus,"2");
            //3.使用PageInfo包装查询后的结果,5是连续显示的条数,结果list类型是Page<E>
            PageInfo<Tyhj> pageInfo = new PageInfo<Tyhj>(tyhjs, pageSize);
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

        return "department/departmenttyhj";
    }

    @PostMapping(value = "passaudittyhj")
    @ResponseBody
    public Result passaudittyhj(HttpSession session, @RequestBody Audit audit){
        System.out.println(audit);
        boolean flag = tyhjService.updateTyhjByHigh(audit);
        if (flag == true){
            return new Result(1,"操作成功");
        }else{
            return new Result(0,"操作失败");
        }

    }

    @PostMapping(value = "passnoaudittyhj")
    @ResponseBody
    public Result passnoaudittyhj(HttpSession session, @RequestBody Audit audit){
        System.out.println(audit);
        boolean flag = tyhjService.updateTyhjByHigh(audit);
        if (flag == true){
            return new Result(1,"操作成功");
        }else{
            return new Result(0,"操作失败");
        }
    }
}
