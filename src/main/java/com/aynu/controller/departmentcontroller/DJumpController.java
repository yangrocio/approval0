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
 * @Date 2020/9/19 3:26
 * @description:
 */
@Controller
@RequestMapping(value = "/djump")
@Slf4j
public class DJumpController {
    @Autowired
    HylwService hylwService;
    @Autowired
    QklwService qklwService;
    @Autowired
    XszzService xszzService;
    @Autowired
    HxxmService hxxmService;

    @GetMapping(value = "godhylwpage")
    public String godhylwpage(HttpSession session, Model model, @RequestParam(required = false, defaultValue = "1", value = "pageNum") Integer pageNum,
                              @RequestParam(defaultValue = "15", value = "pageSize") Integer pageSize,
                              @RequestParam(defaultValue = "-1", value = "number") String number,
                              @RequestParam(defaultValue = "-1", value = "start") String start,
                              @RequestParam(defaultValue = "-1", value = "end") String end,
                              @RequestParam(defaultValue = "-1", value = "judgestatus") String judgestatus) {
        UserInfo user = (UserInfo) session.getAttribute("USER_SESSION");
        if (!user.getRoletype().equals("2")) {
            return "pages/test";
        }

        //1.引入分页插件,pageNum是第几页，pageSize是每页显示多少条,默认查询总数count
        PageHelper.startPage(pageNum, pageSize);
        //2.紧跟的查询就是一个分页查询-必须紧跟.后面的其他查询不会被分页，除非再次调用PageHelper.startPage
        try {
            //number  就是名字  这个 不需要  真正的工号 所以设置为-1  需要的是名字
            List<Hylw> hylwList = hylwService.selectHylwByDetail(user.getDepartment(), "-1", number, start, end, judgestatus, "2");
            //3.使用PageInfo包装查询后的结果,5是连续显示的条数,结果list类型是Page<E>
            PageInfo<Hylw> pageInfo = new PageInfo<Hylw>(hylwList, pageSize);
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
            PageHelper.clearPage(); //清理 ThreaLdocal 存储的分页参数,保证线程安全
        }

        return "department/departmenthylw";
    }

    @PostMapping(value = "passaudithylw")
    @ResponseBody
    public Result passaudithylw(HttpSession session, @RequestBody Audit audit) {
        System.out.println(audit);
        boolean flag = hylwService.updateHylwByHigh(audit);
        if (flag == true) {
            return new Result(1, "操作成功");
        } else {
            return new Result(0, "操作失败");
        }

    }

    @PostMapping(value = "passnoaudithylw")
    @ResponseBody
    public Result passnoaudithylw(HttpSession session, @RequestBody Audit audit) {
        System.out.println(audit);
        boolean flag = hylwService.updateHylwByHigh(audit);
        if (flag == true) {
            return new Result(1, "操作成功");
        } else {
            return new Result(0, "操作失败");
        }
    }


    //期刊论文
    @GetMapping(value = "godqklwpage")
    public String godqklwpage(HttpSession session, Model model, @RequestParam(required = false, defaultValue = "1", value = "pageNum") Integer pageNum,
                              @RequestParam(defaultValue = "15", value = "pageSize") Integer pageSize,
                              @RequestParam(defaultValue = "-1", value = "number") String number,
                              @RequestParam(defaultValue = "-1", value = "start") String start,
                              @RequestParam(defaultValue = "-1", value = "end") String end,
                              @RequestParam(defaultValue = "-1", value = "judgestatus") String judgestatus) {
        UserInfo user = (UserInfo) session.getAttribute("USER_SESSION");
        if (!user.getRoletype().equals("2")) {
            return "pages/test";
        }

        PageHelper.startPage(pageNum, pageSize);

        //1.引入分页插件,pageNum是第几页，pageSize是每页显示多少条,默认查询总数count
        PageHelper.startPage(pageNum, pageSize);
        //2.紧跟的查询就是一个分页查询-必须紧跟.后面的其他查询不会被分页，除非再次调用PageHelper.startPage
        try {
            List<Qklw> qklwList = qklwService.selectQklwByDetail(user.getDepartment(), "-1", number, start, end, judgestatus, "2");
            //3.使用PageInfo包装查询后的结果,5是连续显示的条数,结果list类型是Page<E>
            PageInfo<Qklw> pageInfo = new PageInfo<Qklw>(qklwList, pageSize);
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

        return "department/departmentqklw";
    }


    @PostMapping(value = "passauditqklw")
    @ResponseBody
    public Result passauditqklw(HttpSession session, @RequestBody Audit audit) {
        System.out.println(audit);
        boolean flag = qklwService.updateQklwByHigh(audit);
        if (flag == true) {
            return new Result(1, "操作成功");
        } else {
            return new Result(0, "操作失败");
        }

    }

    @PostMapping(value = "passnoauditqklw")
    @ResponseBody
    public Result passnoauditqklw(HttpSession session, @RequestBody Audit audit) {
        System.out.println(audit);
        boolean flag = qklwService.updateQklwByHigh(audit);
        if (flag == true) {
            return new Result(1, "操作成功");
        } else {
            return new Result(0, "操作失败");
        }
    }


    //学术著作
    @GetMapping(value = "godxszzpage")
    public String godxszzpage(HttpSession session, Model model, @RequestParam(required = false, defaultValue = "1", value = "pageNum") Integer pageNum,
                              @RequestParam(defaultValue = "15", value = "pageSize") Integer pageSize,
                              @RequestParam(defaultValue = "-1", value = "number") String number,
                              @RequestParam(defaultValue = "-1", value = "start") String start,
                              @RequestParam(defaultValue = "-1", value = "end") String end,
                              @RequestParam(defaultValue = "-1", value = "judgestatus") String judgestatus) {
        UserInfo user = (UserInfo) session.getAttribute("USER_SESSION");
        if (!user.getRoletype().equals("2")) {
            return "pages/test";
        }

        //1.引入分页插件,pageNum是第几页，pageSize是每页显示多少条,默认查询总数count
        PageHelper.startPage(pageNum, pageSize);
        //2.紧跟的查询就是一个分页查询-必须紧跟.后面的其他查询不会被分页，除非再次调用PageHelper.startPage
        try {
            List<Xszz> qklwList = xszzService.selectXszzByDetail(user.getDepartment(), "-1", number, start, end, judgestatus, "2");
            //3.使用PageInfo包装查询后的结果,5是连续显示的条数,结果list类型是Page<E>


            PageInfo<Xszz> pageInfo = new PageInfo<Xszz>(qklwList, pageSize);
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

        return "department/departmentxszz";
    }

    @PostMapping(value = "passauditxszz")
    @ResponseBody
    public Result passauditxszz(HttpSession session, @RequestBody Audit audit) {
        System.out.println(audit);
        boolean flag = xszzService.updateXszzByHigh(audit);
        if (flag == true) {
            return new Result(1, "操作成功");
        } else {
            return new Result(0, "操作失败");
        }

    }

    @PostMapping(value = "passnoauditxszz")
    @ResponseBody
    public Result passnoauditxszz(HttpSession session, @RequestBody Audit audit) {
        System.out.println(audit);
        boolean flag = xszzService.updateXszzByHigh(audit);
        if (flag == true) {
            return new Result(1, "操作成功");
        } else {
            return new Result(0, "操作失败");
        }
    }


    //横向项目
    @GetMapping(value = "godhxxmpage")
    public String godhxxmpage(HttpSession session, Model model, @RequestParam(required = false, defaultValue = "1", value = "pageNum") Integer pageNum,
                              @RequestParam(defaultValue = "15", value = "pageSize") Integer pageSize,
                              @RequestParam(defaultValue = "-1", value = "number") String number,
                              @RequestParam(defaultValue = "-1", value = "start") String start,
                              @RequestParam(defaultValue = "-1", value = "end") String end,
                              @RequestParam(defaultValue = "-1", value = "judgestatus") String judgestatus) {
        UserInfo user = (UserInfo) session.getAttribute("USER_SESSION");
        if (!user.getRoletype().equals("2")) {
            return "pages/test";
        }
        //1.引入分页插件,pageNum是第几页，pageSize是每页显示多少条,默认查询总数count
        PageHelper.startPage(pageNum, pageSize);
        //2.紧跟的查询就是一个分页查询-必须紧跟.后面的其他查询不会被分页，除非再次调用PageHelper.startPage
        try {
            List<Hxxm> hxxmList = hxxmService.selectHxxmByDetail(user.getDepartment(), "-1", number, start, end, judgestatus, "2");
            //3.使用PageInfo包装查询后的结果,5是连续显示的条数,结果list类型是Page<E>
            PageInfo<Hxxm> pageInfo = new PageInfo<Hxxm>(hxxmList, pageSize);
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

        return "department/departmenthxxm";
    }

    @PostMapping(value = "passaudithxxm")
    @ResponseBody
    public Result passaudithxxm(HttpSession session, @RequestBody Audit audit) {
        System.out.println(audit);
        boolean flag = hxxmService.updateHxxmByHigh(audit);
        if (flag == true) {
            return new Result(1, "操作成功");
        } else {
            return new Result(0, "操作失败");
        }

    }

    @PostMapping(value = "passnoaudithxxm")
    @ResponseBody
    public Result passnoaudithxxm(HttpSession session, @RequestBody Audit audit) {
        System.out.println(audit);
        boolean flag = hxxmService.updateHxxmByHigh(audit);
        if (flag == true) {
            return new Result(1, "操作成功");
        } else {
            return new Result(0, "操作失败");
        }
    }


}
