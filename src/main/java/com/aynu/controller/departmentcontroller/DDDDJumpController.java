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
 * @Date 2020/9/21 12:41
 * @description:
 */
@Controller
@RequestMapping(value = "/ddddjump")
@Slf4j
public class DDDDJumpController {
    @Autowired
    CgService cgService;
    @Autowired
    HxlxService hxlxService;
    @Autowired
    JxysService jxysService;
    @Autowired
    HpxmService hpxmService;
    @Autowired
    CgzhService cgzhService;
    @Autowired
    ZkjsService zkjsService;

    @GetMapping(value = "godcgpage")
    public String godcgpage(HttpSession session, Model model, @RequestParam(required = false, defaultValue = "1", value = "pageNum") Integer pageNum,
                            @RequestParam(defaultValue = "15", value = "pageSize") Integer pageSize,
                            @RequestParam(defaultValue = "-1", value = "number") String number,
                            @RequestParam(defaultValue = "-1", value = "start") String start,
                            @RequestParam(defaultValue = "-1", value = "end") String end,
                            @RequestParam(defaultValue = "-1", value = "judgestatus") String judgestatus) {
        UserInfo user = (UserInfo) session.getAttribute("USER_SESSION");
        if (!user.getRoletype().equals("2")){
            return "pages/test";
        }
        //1.引入分页插件,pageNum是第几页，pageSize是每页显示多少条,默认查询总数count
        PageHelper.startPage(pageNum, pageSize);
        //2.紧跟的查询就是一个分页查询-必须紧跟.后面的其他查询不会被分页，除非再次调用PageHelper.startPage
        try {
            List<Cg> cgs = cgService.selectCgByDetail(user.getDepartment(), number, start, end, judgestatus, "2");
            //3.使用PageInfo包装查询后的结果,5是连续显示的条数,结果list类型是Page<E>
            PageInfo<Cg> pageInfo = new PageInfo<Cg>(cgs, pageSize);
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

        return "department/departmentcg";
    }

    @PostMapping(value = "passauditcg")
    @ResponseBody
    public Result passauditcg(HttpSession session, @RequestBody Audit audit) {
        System.out.println(audit);
        boolean flag = cgService.updateCgByHigh(audit);
        if (flag == true) {
            return new Result(1, "操作成功");
        } else {
            return new Result(0, "操作失败");
        }

    }

    @PostMapping(value = "passnoauditcg")
    @ResponseBody
    public Result passnoauditcg(HttpSession session, @RequestBody Audit audit) {
        System.out.println(audit);
        boolean flag = cgService.updateCgByHigh(audit);
        if (flag == true) {
            return new Result(1, "操作成功");
        } else {
            return new Result(0, "操作失败");
        }
    }


    //获批项目 纵向立项
    @GetMapping(value = "godhpxmpage")
    public String godhpxmpage(HttpSession session, Model model, @RequestParam(required = false, defaultValue = "1", value = "pageNum") Integer pageNum,
                              @RequestParam(defaultValue = "15", value = "pageSize") Integer pageSize,
                              @RequestParam(defaultValue = "-1", value = "number") String number,
                              @RequestParam(defaultValue = "-1", value = "start") String start,
                              @RequestParam(defaultValue = "-1", value = "end") String end,
                              @RequestParam(defaultValue = "-1", value = "judgestatus") String judgestatus) {
        UserInfo user = (UserInfo) session.getAttribute("USER_SESSION");
        if (!user.getRoletype().equals("2")){
            return "pages/test";
        }
        //1.引入分页插件,pageNum是第几页，pageSize是每页显示多少条,默认查询总数count
        PageHelper.startPage(pageNum, pageSize);
        //2.紧跟的查询就是一个分页查询-必须紧跟.后面的其他查询不会被分页，除非再次调用PageHelper.startPage
        try {
            List<Hpxm> hpxmList = hpxmService.selectHpxmByDetail(user.getDepartment(),"-1", number, start, end, judgestatus, "2");
            //3.使用PageInfo包装查询后的结果,5是连续显示的条数,结果list类型是Page<E>
            PageInfo<Hpxm> pageInfo = new PageInfo<Hpxm>(hpxmList, pageSize);
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

        return "department/departmenthpxm";
    }

    @PostMapping(value = "passaudithpxm")
    @ResponseBody
    public Result passaudithpxm(HttpSession session, @RequestBody Audit audit) {
        System.out.println(audit);
        boolean flag = hpxmService.updateHpxmByHigh(audit);
        if (flag == true) {
            return new Result(1, "操作成功");
        } else {
            return new Result(0, "操作失败");
        }

    }

    @PostMapping(value = "passnoaudithpxm")
    @ResponseBody
    public Result passnoaudithpxm(HttpSession session, @RequestBody Audit audit) {
        System.out.println(audit);
        boolean flag = hpxmService.updateHpxmByHigh(audit);
        if (flag == true) {
            return new Result(1, "操作成功");
        } else {
            return new Result(0, "操作失败");
        }
    }


    //纵向结项  结项验收
    @GetMapping(value = "godjxyspage")
    public String godjxyspage(HttpSession session, Model model, @RequestParam(required = false, defaultValue = "1", value = "pageNum") Integer pageNum,
                              @RequestParam(defaultValue = "15", value = "pageSize") Integer pageSize,
                              @RequestParam(defaultValue = "-1", value = "number") String number,
                              @RequestParam(defaultValue = "-1", value = "start") String start,
                              @RequestParam(defaultValue = "-1", value = "end") String end,
                              @RequestParam(defaultValue = "-1", value = "judgestatus") String judgestatus) {
        UserInfo user = (UserInfo) session.getAttribute("USER_SESSION");
        if (!user.getRoletype().equals("2")){
            return "pages/test";
        }
        //1.引入分页插件,pageNum是第几页，pageSize是每页显示多少条,默认查询总数count
        PageHelper.startPage(pageNum, pageSize);
        //2.紧跟的查询就是一个分页查询-必须紧跟.后面的其他查询不会被分页，除非再次调用PageHelper.startPage
        try {
            List<Jxys> jxysList = jxysService.selectJxysByDetail(user.getDepartment(), "-1",number, start, end, judgestatus, "2");
            //3.使用PageInfo包装查询后的结果,5是连续显示的条数,结果list类型是Page<E>
            PageInfo<Jxys> pageInfo = new PageInfo<Jxys>(jxysList, pageSize);
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

        return "department/departmentjxys";
    }

    @PostMapping(value = "passauditjxys")
    @ResponseBody
    public Result passauditjxys(HttpSession session, @RequestBody Audit audit) {
        System.out.println(audit);
        boolean flag = jxysService.updateJxysByHigh(audit);
        if (flag == true) {
            return new Result(1, "操作成功");
        } else {
            return new Result(0, "操作失败");
        }

    }

    @PostMapping(value = "passnoauditjxys")
    @ResponseBody
    public Result passnoauditjxys(HttpSession session, @RequestBody Audit audit) {
        System.out.println(audit);
        boolean flag = jxysService.updateJxysByHigh(audit);
        if (flag == true) {
            return new Result(1, "操作成功");
        } else {
            return new Result(0, "操作失败");
        }
    }

    //横向立项
    @GetMapping(value = "godhxlxpage")
    public String godhxlxpage(HttpSession session, Model model, @RequestParam(required = false, defaultValue = "1", value = "pageNum") Integer pageNum,
                              @RequestParam(defaultValue = "15", value = "pageSize") Integer pageSize,
                              @RequestParam(defaultValue = "-1", value = "number") String number,
                              @RequestParam(defaultValue = "-1", value = "start") String start,
                              @RequestParam(defaultValue = "-1", value = "end") String end,
                              @RequestParam(defaultValue = "-1", value = "judgestatus") String judgestatus) {
        UserInfo user = (UserInfo) session.getAttribute("USER_SESSION");
        if (!user.getRoletype().equals("2")){
            return "pages/test";
        }
        //1.引入分页插件,pageNum是第几页，pageSize是每页显示多少条,默认查询总数count
        PageHelper.startPage(pageNum, pageSize);
        //2.紧跟的查询就是一个分页查询-必须紧跟.后面的其他查询不会被分页，除非再次调用PageHelper.startPage
        try {
            List<Hxlx> hxlxList = hxlxService.selectHxlxByDetail(user.getDepartment(), "-1",number, start, end, judgestatus, "2");
            //3.使用PageInfo包装查询后的结果,5是连续显示的条数,结果list类型是Page<E>
            PageInfo<Hxlx> pageInfo = new PageInfo<Hxlx>(hxlxList, pageSize);
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

        return "department/departmenthxlx";
    }

    @PostMapping(value = "passaudithxlx")
    @ResponseBody
    public Result passaudithxlx(HttpSession session, @RequestBody Audit audit) {
        System.out.println(audit);
        boolean flag = hxlxService.updateHxlxByHigh(audit);
        if (flag == true) {
            return new Result(1, "操作成功");
        } else {
            return new Result(0, "操作失败");
        }

    }

    @PostMapping(value = "passnoaudithxlx")
    @ResponseBody
    public Result passnoaudithxlx(HttpSession session, @RequestBody Audit audit) {
        System.out.println(audit);
        boolean flag = hxlxService.updateHxlxByHigh(audit);
        if (flag == true) {
            return new Result(1, "操作成功");
        } else {
            return new Result(0, "操作失败");
        }
    }

    //成果转化
    @GetMapping(value = "godcgzhpage")
    public String godcgzhpage(HttpSession session, Model model, @RequestParam(required = false, defaultValue = "1", value = "pageNum") Integer pageNum,
                              @RequestParam(defaultValue = "15", value = "pageSize") Integer pageSize,
                              @RequestParam(defaultValue = "-1", value = "number") String number,
                              @RequestParam(defaultValue = "-1", value = "start") String start,
                              @RequestParam(defaultValue = "-1", value = "end") String end,
                              @RequestParam(defaultValue = "-1", value = "judgestatus") String judgestatus) {
        UserInfo user = (UserInfo) session.getAttribute("USER_SESSION");
        if (!user.getRoletype().equals("2")){
            return "pages/test";
        }
        //1.引入分页插件,pageNum是第几页，pageSize是每页显示多少条,默认查询总数count
        PageHelper.startPage(pageNum, pageSize);
        //2.紧跟的查询就是一个分页查询-必须紧跟.后面的其他查询不会被分页，除非再次调用PageHelper.startPage
        try {
            List<Cgzh> cgzhList = cgzhService.selectCgzhByDetail(user.getDepartment(), "-1",number, start, end, judgestatus, "2");
            //3.使用PageInfo包装查询后的结果,5是连续显示的条数,结果list类型是Page<E>
            PageInfo<Cgzh> pageInfo = new PageInfo<Cgzh>(cgzhList, pageSize);
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

        return "department/departmentcgzh";
    }

    @PostMapping(value = "passauditcgzh")
    @ResponseBody
    public Result passauditcgzh(HttpSession session, @RequestBody Audit audit) {
        boolean flag = cgzhService.updateCgzhByHigh(audit);
        if (flag == true) {
            return new Result(1, "操作成功");
        } else {
            return new Result(0, "操作失败");
        }

    }

    @PostMapping(value = "passnoauditcgzh")
    @ResponseBody
    public Result passnoauditcgzh(HttpSession session, @RequestBody Audit audit) {
        boolean flag = cgzhService.updateCgzhByHigh(audit);
        if (flag == true) {
            return new Result(1, "操作成功");
        } else {
            return new Result(0, "操作失败");
        }
    }

    //智库建设
    @GetMapping(value = "godzkjspage")
    public String godzkjspage(HttpSession session, Model model, @RequestParam(required = false, defaultValue = "1", value = "pageNum") Integer pageNum,
                              @RequestParam(defaultValue = "15", value = "pageSize") Integer pageSize,
                              @RequestParam(defaultValue = "-1", value = "number") String number,
                              @RequestParam(defaultValue = "-1", value = "start") String start,
                              @RequestParam(defaultValue = "-1", value = "end") String end,
                              @RequestParam(defaultValue = "-1", value = "judgestatus") String judgestatus) {
        UserInfo user = (UserInfo) session.getAttribute("USER_SESSION");
        if (!user.getRoletype().equals("2")){
            return "pages/test";
        }
        //1.引入分页插件,pageNum是第几页，pageSize是每页显示多少条,默认查询总数count
        PageHelper.startPage(pageNum, pageSize);
        //2.紧跟的查询就是一个分页查询-必须紧跟.后面的其他查询不会被分页，除非再次调用PageHelper.startPage
        try {
            List<Zkjs> zkjsList = zkjsService.selectZkjsByDetail(user.getDepartment(), "-1",number, start, end, judgestatus, "2");
            //3.使用PageInfo包装查询后的结果,5是连续显示的条数,结果list类型是Page<E>
            PageInfo<Zkjs> pageInfo = new PageInfo<Zkjs>(zkjsList, pageSize);
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

        return "department/departmentzkjs";
    }

    @PostMapping(value = "passauditzkjs")
    @ResponseBody
    public Result passauditzkjs(HttpSession session, @RequestBody Audit audit) {
        boolean flag = zkjsService.updateZkjsByHigh(audit);
        if (flag == true) {
            return new Result(1, "操作成功");
        } else {
            return new Result(0, "操作失败");
        }

    }

    @PostMapping(value = "passnoauditzkjs")
    @ResponseBody
    public Result passnoauditzkjs(HttpSession session, @RequestBody Audit audit) {
        boolean flag = zkjsService.updateZkjsByHigh(audit);
        if (flag == true) {
            return new Result(1, "操作成功");
        } else {
            return new Result(0, "操作失败");
        }
    }
}
