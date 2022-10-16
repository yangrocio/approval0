package com.aynu.controller.teachercontroller;

import com.aynu.bean.*;
import com.aynu.controller.UploadController;
import com.aynu.dto.Result;
import com.aynu.service.*;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.OutputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @author susuper
 * @version 1.0
 * @date 2020/9/14 10:06
 * 成果 结项验收 获批项目
 */
@Controller
@RequestMapping(value = "/ttttjump")
@Slf4j
public class TTTTJumpController {
    @Autowired
    CgService cgService;
    @Autowired
    JxysService jxysService;
    @Autowired
    HpxmService hpxmService;
    @Autowired
    HxlxService hxlxService;
    @Autowired
    CgzhService cgzhService;
    @Autowired
    ZkjsService zkjsService;
    @Autowired
    UploadController uploadController;

    @GetMapping(value = "gocgpage")
    public String gocgpage(HttpSession session, Model model, @RequestParam(required = false, defaultValue = "1", value = "pageNum") Integer pageNum,
                           @RequestParam(defaultValue = "15", value = "pageSize") Integer pageSize,
                           @RequestParam(defaultValue = "-1", value = "start") String start,
                           @RequestParam(defaultValue = "-1", value = "end") String end,
                           @RequestParam(defaultValue = "-1", value = "judgestatus") String judgestatus) {
        UserInfo user = (UserInfo) session.getAttribute("USER_SESSION");
        if (!user.getRoletype().equals("1")){
            return "pages/test";
        }
        PageHelper.startPage(pageNum, pageSize);
        //1.引入分页插件,pageNum是第几页，pageSize是每页显示多少条,默认查询总数count
        //2.紧跟的查询就是一个分页查询-必须紧跟.后面的其他查询不会被分页，除非再次调用PageHelper.startPage
        try {
            List<Cg> cgList = cgService.selectCgByDetail(user.getDepartment(), user.getNumber(), start, end, judgestatus, "1");
            //3.使用PageInfo包装查询后的结果,5是连续显示的条数,结果list类型是Page<E>
            PageInfo<Cg> pageInfo = new PageInfo<Cg>(cgList, pageSize);
            //4.使用model/map/modelandview等带回前端
            model.addAttribute("pageInfo", pageInfo);
            if (!start.equals("-1")) {
                model.addAttribute("start", start);
            }
            if (!end.equals("-1")) {
                model.addAttribute("end", end);
            }
            if (!judgestatus.equals("-1")) {
                model.addAttribute("judgestatus", judgestatus);
            }
        } finally {
            PageHelper.clearPage(); //清理 ThreadLocal 存储的分页参数,保证线程安全
        }

        return "teacher/teachercg";
    }

    @GetMapping(value = "gocgwrite")
    public String gocgwrite(HttpSession session, Model model) {
        UserInfo user = (UserInfo) session.getAttribute("USER_SESSION");
        return "teacher/teachercgwrite";
    }

    @PostMapping(value = "putcg")
    @ResponseBody
    public Result putcg(@RequestBody Cg cg, HttpSession session) {
        UserInfo user = (UserInfo) session.getAttribute("USER_SESSION");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String systime = LocalDate.now().format(formatter); // get the current date
        cg.setDepartment(user.getDepartment()).setName(user.getName()).setNumber(user.getNumber()).setSystime(systime);
        System.out.println(cg);
        boolean flag = cgService.insertCg(cg);
        if (flag) {
            return new Result(1, "添加成功");
        }
        return new Result(0, "添加失败");
    }

    @PostMapping(value = "deletecg")
    @ResponseBody
    public Result deletecg(@RequestBody List<String> list, HttpSession session) {
        UserInfo user = (UserInfo) session.getAttribute("USER_SESSION");
        if (list.isEmpty()) {
            return new Result(1, "所选可删除内容为空");
        }
        cgService.deleteFjscByList(list);
        boolean flag = cgService.deleteCg(list, user.getNumber(), user.getDepartment());
        //先去找一下 干脆 查询出来  根据审核状态 进行判断 是否可以删除 放在前端处理？
        if (flag) {
            return new Result(1, "删除成功");
        } else {
            return new Result(0, "删除失败");
        }
    }

    @PostMapping(value = "cgshow")
    @ResponseBody
    public Cg cgshow(String id, HttpSession session) {
        UserInfo user = (UserInfo) session.getAttribute("USER_SESSION");
        System.out.println(id);
        Cg cg = cgService.showCg(user.getDepartment(), user.getNumber(), id);
        return cg;
    }

    @PostMapping(value = "cgupdate")
    @ResponseBody
    public Result cgupdate(@RequestBody Cg cg, HttpSession session) {
        UserInfo user = (UserInfo) session.getAttribute("USER_SESSION");
        if (cg.getFjsc() != null) {
            List<String> list = new ArrayList<>();
            list.add((cg.getCid().toString()));
            cgService.deleteFjscByList(list);
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String systime = LocalDate.now().format(formatter); // get the current date
        cg.setDepartment(user.getDepartment()).setName(user.getName()).setNumber(user.getNumber()).setSystime(systime);
        System.out.println(cg);
        boolean flag = cgService.updateCg(cg);
        if (flag) {
            return new Result(1, "更新成功");
        } else {
            return new Result(0, "更新失败");
        }
    }

    @PostMapping(value = "downloadcgfjsc")
    @ResponseBody
    public Result downloadcgfjsc(@RequestBody List<String> list) {
        String zipFileName = cgService.downloadcgZipFjsc(list);
        return new Result(1, zipFileName);
    }

    @GetMapping(value = "cgexportall")
    public void cgexportall(HttpSession session, HttpServletResponse response) {
        UserInfo user = (UserInfo) session.getAttribute("USER_SESSION");
        String department = user.getDepartment();
        String number = user.getNumber();
        String fileName = department + "-" + number + "-" + user.getName() + "-成果通过审核" + UUID.randomUUID().toString().replaceAll("-", "") + ".xls";
        HSSFWorkbook wb = cgService.exportcgExcel(department, number, user.getRoletype());
        //响应到客户端
        try {
            uploadController.setResponseHeader(response, fileName);
            OutputStream os = response.getOutputStream();
            wb.write(os);
            os.flush();
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                wb.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    //结项验收
    @GetMapping(value = "gojxyspage")
    public String gojxyspage(HttpSession session, Model model, @RequestParam(required = false, defaultValue = "1", value = "pageNum") Integer pageNum,
                             @RequestParam(defaultValue = "15", value = "pageSize") Integer pageSize,
                             @RequestParam(defaultValue = "-1", value = "start") String start,
                             @RequestParam(defaultValue = "-1", value = "end") String end,
                             @RequestParam(defaultValue = "-1", value = "judgestatus") String judgestatus) {
        UserInfo user = (UserInfo) session.getAttribute("USER_SESSION");
        if (!user.getRoletype().equals("1")){
            return "pages/test";
        }
        PageHelper.startPage(pageNum, pageSize);
        //1.引入分页插件,pageNum是第几页，pageSize是每页显示多少条,默认查询总数count
        //2.紧跟的查询就是一个分页查询-必须紧跟.后面的其他查询不会被分页，除非再次调用PageHelper.startPage
        try {
            List<Jxys> jxysList = jxysService.selectJxysByDetail(user.getDepartment(), user.getNumber(), "-1",start, end, judgestatus, "1");
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
            if (!judgestatus.equals("-1")) {
                model.addAttribute("judgestatus", judgestatus);
            }
        } finally {
            PageHelper.clearPage(); //清理 ThreadLocal 存储的分页参数,保证线程安全
        }

        return "teacher/teacherjxys";
    }

    @GetMapping(value = "gojxyswrite")
    public String gojxyswrite(HttpSession session, Model model) {
        UserInfo user = (UserInfo) session.getAttribute("USER_SESSION");
        if (!user.getRoletype().equals("1")){
            return "pages/test";
        }
        return "teacher/teacherjxyswrite";
    }

    @PostMapping(value = "putjxys")
    @ResponseBody
    public Result putjxys(@RequestBody Jxys jxys, HttpSession session) {
        UserInfo user = (UserInfo) session.getAttribute("USER_SESSION");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String systime = LocalDate.now().format(formatter); // get the current date

        jxys.setDepartment(user.getDepartment());
        jxys.setName(user.getName());
        jxys.setNumber(user.getNumber());
        jxys.setSystime(systime);

//        jxys.setDepartment(user.getDepartment()).setName(user.getName()).setNumber(user.getNumber()).setSystime(systime);
//        System.out.println(jxys);
        boolean flag = jxysService.insertJxys(jxys);
        if (flag) {
            return new Result(1, "添加成功");
        }
        return new Result(0, "添加失败");
    }

    @PostMapping(value = "deletejxys")
    @ResponseBody
    public Result deletejxys(@RequestBody List<String> list, HttpSession session) {
        UserInfo user = (UserInfo) session.getAttribute("USER_SESSION");
        if (list.isEmpty()) {
            return new Result(1, "所选可删除内容为空");
        }
        jxysService.deleteFjscByList(list);
        boolean flag = jxysService.deleteJxys(list, user.getNumber(), user.getDepartment());
        //先去找一下 干脆 查询出来  根据审核状态 进行判断 是否可以删除 放在前端处理？
        if (flag) {
            return new Result(1, "删除成功");
        } else {
            return new Result(0, "删除失败");
        }
    }

    @PostMapping(value = "jxysshow")
    @ResponseBody
    public Jxys jxysshow(String id, HttpSession session) {
        UserInfo user = (UserInfo) session.getAttribute("USER_SESSION");
        System.out.println(id);
        Jxys jxys = jxysService.showJxys(user.getDepartment(), user.getNumber(), id);
        return jxys;
    }

    @PostMapping(value = "jxysupdate")
    @ResponseBody
    public Result jxysupdate(@RequestBody Jxys jxys, HttpSession session) {
        UserInfo user = (UserInfo) session.getAttribute("USER_SESSION");
        if (jxys.getFjsc() != null) {
            List<String> list = new ArrayList<>();
            list.add((jxys.getJid().toString()));
            jxysService.deleteFjscByList(list);
        }

        jxys.setDepartment(user.getDepartment());
        jxys.setName(user.getName());
        jxys.setNumber(user.getNumber());
//
//        if (jxys.getSystime() == null) {
//            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//            String systime = LocalDate.now().format(formatter); // get the current date
//            jxys.setSystime(systime);
//        } else if (jxys.getSystime().equals("-1")) {
            jxys.setSystime(null);
//        }

//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//        String systime = LocalDate.now().format(formatter); // get the current date
//        jxys.setDepartment(user.getDepartment()).setName(user.getName()).setNumber(user.getNumber()).setSystime(systime);
        boolean flag = jxysService.updateJxys(jxys);
        if (flag) {
            return new Result(1, "更新成功");
        } else {
            return new Result(0, "更新失败");
        }
    }

    @PostMapping(value = "downloadjxysfjsc")
    @ResponseBody
    public Result downloadjxysfjsc(@RequestBody List<String> list) {
        String zipFileName = jxysService.downloadcgZipFjsc(list);
        return new Result(1, zipFileName);
    }

    @GetMapping(value = "jxysexportall")
    public void jxysexportall(HttpSession session, HttpServletResponse response) {
        UserInfo user = (UserInfo) session.getAttribute("USER_SESSION");
        String department = user.getDepartment();
        String number = user.getNumber();
        String fileName = department + "-" + number + "-" + user.getName() + "-结项验收通过审核" + UUID.randomUUID().toString().replaceAll("-", "") + ".xls";
        HSSFWorkbook wb = jxysService.exportcgExcel(department, number, user.getRoletype());
        //响应到客户端
        try {
            uploadController.setResponseHeader(response, fileName);
            OutputStream os = response.getOutputStream();
            wb.write(os);
            os.flush();
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                wb.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @PostMapping(value = "jxysExcelJson")
    @ResponseBody
    public Result rjzzExcelJson(HttpSession session, @RequestBody Map<String, Object> mapdata) {
        List<String> params = (List<String>) mapdata.get("params");
        if (params.size()==0){
            return new Result(0,"请务必选择列参数");
        }

        UserInfo user = (UserInfo) session.getAttribute("USER_SESSION");
        String start = (String) mapdata.get("startExcelTime");
        String end = (String) mapdata.get("endExcelTime");
        String roletype = user.getRoletype();
        List<Map<Object, Object>> map = null;
        String fileName = null;
        if (roletype.equals("1")) {
            //传递过去 number  department  params 传过去
            fileName = jxysService.getExcelJson(user.getDepartment(), user.getNumber(),"-1", start, end, "3", params);
        } else if (roletype.equals("2")) {
            String name = (String) mapdata.get("name");
            fileName = jxysService.getExcelJson(user.getDepartment(),"-1", name, start, end, "3", params);
        } else if (roletype.equals("3")) {
            String department = (String) mapdata.get("department");
            String name = (String) mapdata.get("name");

            fileName = jxysService.getExcelJson(department,"-1", name, start, end, "3", params);
        }
        return new Result(1, fileName);
    }
    //纵向立项
    @GetMapping(value = "gohpxmpage")
    public String gohpxmpage(HttpSession session, Model model, @RequestParam(required = false, defaultValue = "1", value = "pageNum") Integer pageNum,
                             @RequestParam(defaultValue = "15", value = "pageSize") Integer pageSize,
                             @RequestParam(defaultValue = "-1", value = "start") String start,
                             @RequestParam(defaultValue = "-1", value = "end") String end,
                             @RequestParam(defaultValue = "-1", value = "judgestatus") String judgestatus) {
        UserInfo user = (UserInfo) session.getAttribute("USER_SESSION");
        if (!user.getRoletype().equals("1")){
            return "pages/test";
        }
        PageHelper.startPage(pageNum, pageSize);
        //1.引入分页插件,pageNum是第几页，pageSize是每页显示多少条,默认查询总数count
        //2.紧跟的查询就是一个分页查询-必须紧跟.后面的其他查询不会被分页，除非再次调用PageHelper.startPage
        try {
            List<Hpxm> hpxms = hpxmService.selectHpxmByDetail(user.getDepartment(), user.getNumber(), "-1",start, end, judgestatus, "1");
            //3.使用PageInfo包装查询后的结果,5是连续显示的条数,结果list类型是Page<E>
            PageInfo<Hpxm> pageInfo = new PageInfo<Hpxm>(hpxms, pageSize);
            //4.使用model/map/modelandview等带回前端
            model.addAttribute("pageInfo", pageInfo);
            if (!start.equals("-1")) {
                model.addAttribute("start", start);
            }
            if (!end.equals("-1")) {
                model.addAttribute("end", end);
            }
            if (!judgestatus.equals("-1")) {
                model.addAttribute("judgestatus", judgestatus);
            }
        } finally {
            PageHelper.clearPage(); //清理 ThreadLocal 存储的分页参数,保证线程安全
        }

        return "teacher/teacherhpxm";
    }

    @GetMapping(value = "gohpxmwrite")
    public String gohpxmwrite(HttpSession session, Model model) {
        UserInfo user = (UserInfo) session.getAttribute("USER_SESSION");
        if (!user.getRoletype().equals("1")){
            return "pages/test";
        }
        return "teacher/teacherhpxmwrite";
    }

    @PostMapping(value = "puthpxm")
    @ResponseBody
    public Result puthpxm(@RequestBody Hpxm hpxm, HttpSession session) {
        UserInfo user = (UserInfo) session.getAttribute("USER_SESSION");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String systime = LocalDate.now().format(formatter); // get the current date

        hpxm.setDepartment(user.getDepartment());
        hpxm.setName(user.getName());
        hpxm.setNumber(user.getNumber());
        hpxm.setSystime(systime);

//        hpxm.setDepartment(user.getDepartment()).setName(user.getName()).setNumber(user.getNumber()).setSystime(systime);
        boolean flag = hpxmService.insertHpxm(hpxm);
        if (flag) {
            return new Result(1, "添加成功");
        }
        return new Result(0, "添加失败");
    }

    @PostMapping(value = "deletehpxm")
    @ResponseBody
    public Result deletehpxm(@RequestBody List<String> list, HttpSession session) {
        UserInfo user = (UserInfo) session.getAttribute("USER_SESSION");
        if (list.isEmpty()) {
            return new Result(1, "所选可删除内容为空");
        }
        hpxmService.deleteFjscByList(list);
        boolean flag = hpxmService.deleteHpxm(list, user.getNumber(), user.getDepartment());
        //先去找一下 干脆 查询出来  根据审核状态 进行判断 是否可以删除 放在前端处理？
        if (flag) {
            return new Result(1, "删除成功");
        } else {
            return new Result(0, "删除失败");
        }
    }

    @PostMapping(value = "hpxmshow")
    @ResponseBody
    public Hpxm hpxmshow(String id, HttpSession session) {
        UserInfo user = (UserInfo) session.getAttribute("USER_SESSION");
        System.out.println(id);
        Hpxm hpxm = hpxmService.showHpxm(user.getDepartment(), user.getNumber(), id);
        return hpxm;
    }

    @PostMapping(value = "hpxmupdate")
    @ResponseBody
    public Result hpxmupdate(@RequestBody Hpxm hpxm, HttpSession session) {
        UserInfo user = (UserInfo) session.getAttribute("USER_SESSION");
        if (hpxm.getFjsc() != null) {
            List<String> list = new ArrayList<>();
            list.add((hpxm.getHid().toString()));
            hpxmService.deleteFjscByList(list);
        }

        hpxm.setDepartment(user.getDepartment());
        hpxm.setName(user.getName());
        hpxm.setNumber(user.getNumber());

//        if (hpxm.getSystime() == null) {
//            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//            String systime = LocalDate.now().format(formatter); // get the current date
//            hpxm.setSystime(systime);
//        } else if (hpxm.getSystime().equals("-1")) {
            hpxm.setSystime(null);
//        }

//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//        String systime = LocalDate.now().format(formatter); // get the current date
//        hpxm.setDepartment(user.getDepartment()).setName(user.getName()).setNumber(user.getNumber()).setSystime(systime);
        boolean flag = hpxmService.updateHpxm(hpxm);
        if (flag) {
            return new Result(1, "更新成功");
        } else {
            return new Result(0, "更新失败");
        }
    }

    @PostMapping(value = "downloadhpxmfjsc")
    @ResponseBody
    public Result downloadhpxmfjsc(@RequestBody List<String> list) {
        String zipFileName = hpxmService.downloadhpxmZipFjsc(list);
        return new Result(1, zipFileName);
    }

    @GetMapping(value = "hpxmexportall")
    public void hpxmexportall(HttpSession session, HttpServletResponse response) {
        UserInfo user = (UserInfo) session.getAttribute("USER_SESSION");
        String department = user.getDepartment();
        String number = user.getNumber();
        String fileName = department + "-" + number + "-" + user.getName() + "-获批项目通过审核" + UUID.randomUUID().toString().replaceAll("-", "") + ".xls";
        HSSFWorkbook wb = hpxmService.exporthpxmExcel(department, number, user.getRoletype());
        //响应到客户端
        try {
            uploadController.setResponseHeader(response, fileName);
            OutputStream os = response.getOutputStream();
            wb.write(os);
            os.flush();
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                wb.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    @PostMapping(value = "hpxmExcelJson")
    @ResponseBody
    public Result hpxmExcelJson(HttpSession session, @RequestBody Map<String, Object> mapdata) {
        List<String> params = (List<String>) mapdata.get("params");
        if (params.size()==0){
            return new Result(0,"请务必选择列参数");
        }

        UserInfo user = (UserInfo) session.getAttribute("USER_SESSION");
        String start = (String) mapdata.get("startExcelTime");
        String end = (String) mapdata.get("endExcelTime");
        String roletype = user.getRoletype();
        List<Map<Object, Object>> map = null;
        String fileName = null;
        if (roletype.equals("1")) {
            //传递过去 number  department  params 传过去
            fileName = hpxmService.getExcelJson(user.getDepartment(), user.getNumber(),"-1", start, end, "3", params);
        } else if (roletype.equals("2")) {
            String name = (String) mapdata.get("name");
            fileName = hpxmService.getExcelJson(user.getDepartment(), "-1",name, start, end, "3", params);
        } else if (roletype.equals("3")) {
            String department = (String) mapdata.get("department");
            String name = (String) mapdata.get("name");

            fileName = hpxmService.getExcelJson(department,"-1", name, start, end, "3", params);
        }
        return new Result(1, fileName);
    }
    //横向立项
    @GetMapping(value = "gohxlxpage")
    public String gohxlxpage(HttpSession session, Model model, @RequestParam(required = false, defaultValue = "1", value = "pageNum") Integer pageNum,
                             @RequestParam(defaultValue = "15", value = "pageSize") Integer pageSize,
                             @RequestParam(defaultValue = "-1", value = "start") String start,
                             @RequestParam(defaultValue = "-1", value = "end") String end,
                             @RequestParam(defaultValue = "-1", value = "judgestatus") String judgestatus) {
        UserInfo user = (UserInfo) session.getAttribute("USER_SESSION");
        if (!user.getRoletype().equals("1")){
            return "pages/test";
        }
        PageHelper.startPage(pageNum, pageSize);
        //1.引入分页插件,pageNum是第几页，pageSize是每页显示多少条,默认查询总数count
        //2.紧跟的查询就是一个分页查询-必须紧跟.后面的其他查询不会被分页，除非再次调用PageHelper.startPage
        try {
            List<Hxlx> hxlxes = hxlxService.selectHxlxByDetail(user.getDepartment(), user.getNumber(),"-1", start, end, judgestatus, "1");
            //3.使用PageInfo包装查询后的结果,5是连续显示的条数,结果list类型是Page<E>
            PageInfo<Hxlx> pageInfo = new PageInfo<Hxlx>(hxlxes, pageSize);
            //4.使用model/map/modelandview等带回前端
            model.addAttribute("pageInfo", pageInfo);
            if (!start.equals("-1")) {
                model.addAttribute("start", start);
            }
            if (!end.equals("-1")) {
                model.addAttribute("end", end);
            }
            if (!judgestatus.equals("-1")) {
                model.addAttribute("judgestatus", judgestatus);
            }
        } finally {
            PageHelper.clearPage(); //清理 ThreadLocal 存储的分页参数,保证线程安全
        }

        return "teacher/teacherhxlx";
    }

    @GetMapping(value = "gohxlxwrite")
    public String gohxlxwrite(HttpSession session, Model model) {
        UserInfo user = (UserInfo) session.getAttribute("USER_SESSION");
        if (!user.getRoletype().equals("1")){
            return "pages/test";
        }
        return "teacher/teacherhxlxwrite";
    }

    @PostMapping(value = "puthxlx")
    @ResponseBody
    public Result puthxlx(@RequestBody Hxlx hxlx, HttpSession session) {
        UserInfo user = (UserInfo) session.getAttribute("USER_SESSION");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String systime = LocalDate.now().format(formatter); // get the current date

        hxlx.setDepartment(user.getDepartment());
        hxlx.setName(user.getName());
        hxlx.setNumber(user.getNumber());
        hxlx.setSystime(systime);

//        hxlx.setDepartment(user.getDepartment()).setName(user.getName()).setNumber(user.getNumber()).setSystime(systime);
        boolean flag = hxlxService.insertHxlx(hxlx);
        if (flag) {
            return new Result(1, "添加成功");
        }
        return new Result(0, "添加失败");
    }

    @PostMapping(value = "deletehxlx")
    @ResponseBody
    public Result deletehxlx(@RequestBody List<String> list, HttpSession session) {
        UserInfo user = (UserInfo) session.getAttribute("USER_SESSION");
        if (list.isEmpty()) {
            return new Result(1, "所选可删除内容为空");
        }
        hxlxService.deleteFjscByList(list);
        boolean flag = hxlxService.deleteHxlx(list, user.getNumber(), user.getDepartment());
        //先去找一下 干脆 查询出来  根据审核状态 进行判断 是否可以删除 放在前端处理？
        if (flag) {
            return new Result(1, "删除成功");
        } else {
            return new Result(0, "删除失败");
        }
    }

    @PostMapping(value = "hxlxshow")
    @ResponseBody
    public Hxlx hxlxshow(String id, HttpSession session) {
        UserInfo user = (UserInfo) session.getAttribute("USER_SESSION");
        Hxlx hxlx = hxlxService.showHxlx(user.getDepartment(), user.getNumber(), id);
        return hxlx;
    }

    @PostMapping(value = "hxlxupdate")
    @ResponseBody
    public Result hxlxupdate(@RequestBody Hxlx hxlx, HttpSession session) {
        UserInfo user = (UserInfo) session.getAttribute("USER_SESSION");
        if (hxlx.getFjsc() != null) {
            List<String> list = new ArrayList<>();
            list.add((hxlx.getHid().toString()));
            hxlxService.deleteFjscByList(list);
        }

        hxlx.setDepartment(user.getDepartment());
        hxlx.setName(user.getName());
        hxlx.setNumber(user.getNumber());

//        if (hxlx.getSystime() == null) {
//            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//            String systime = LocalDate.now().format(formatter); // get the current date
//            hxlx.setSystime(systime);
//        } else if (hxlx.getSystime().equals("-1")) {
            hxlx.setSystime(null);
//        }

//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//        String systime = LocalDate.now().format(formatter); // get the current date
//        hxlx.setDepartment(user.getDepartment()).setName(user.getName()).setNumber(user.getNumber()).setSystime(systime);
//        System.out.println(hxlx);
        boolean flag = hxlxService.updateHxlx(hxlx);
        if (flag) {
            return new Result(1, "更新成功");
        } else {
            return new Result(0, "更新失败");
        }
    }

    @PostMapping(value = "downloadhxlxfjsc")
    @ResponseBody
    public Result downloadhxlxfjsc(@RequestBody List<String> list) {
        String zipFileName = hxlxService.downloadhxlxZipFjsc(list);
        return new Result(1, zipFileName);
    }

    @GetMapping(value = "hxlxexportall")
    public void hxlxexportall(HttpSession session, HttpServletResponse response) {
        UserInfo user = (UserInfo) session.getAttribute("USER_SESSION");
        String department = user.getDepartment();
        String number = user.getNumber();
        String fileName = department + "-" + number + "-" + user.getName() + "-横向立项通过审核" + UUID.randomUUID().toString().replaceAll("-", "") + ".xls";
        HSSFWorkbook wb = hxlxService.exporthxlxExcel(department, number, user.getRoletype());
        //响应到客户端
        try {
            uploadController.setResponseHeader(response, fileName);
            OutputStream os = response.getOutputStream();
            wb.write(os);
            os.flush();
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                wb.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    @PostMapping(value = "hxlxExcelJson")
    @ResponseBody
    public Result hxlxExcelJson(HttpSession session, @RequestBody Map<String, Object> mapdata) {
        List<String> params = (List<String>) mapdata.get("params");
        if (params.size()==0){
            return new Result(0,"请务必选择列参数");
        }

        UserInfo user = (UserInfo) session.getAttribute("USER_SESSION");
        String start = (String) mapdata.get("startExcelTime");
        String end = (String) mapdata.get("endExcelTime");
        String roletype = user.getRoletype();
        List<Map<Object, Object>> map = null;
        String fileName = null;
        if (roletype.equals("1")) {
            //传递过去 number  department  params 传过去
            fileName = hxlxService.getExcelJson(user.getDepartment(), user.getNumber(),"-1", start, end, "3", params);
        } else if (roletype.equals("2")) {
            String name = (String) mapdata.get("name");
            fileName = hxlxService.getExcelJson(user.getDepartment(),"-1", name, start, end, "3", params);
        } else if (roletype.equals("3")) {
            String department = (String) mapdata.get("department");
            String name = (String) mapdata.get("name");

            fileName = hxlxService.getExcelJson(department,"-1", name, start, end, "3", params);
        }
        return new Result(1, fileName);
    }

    //成果转化
    @GetMapping(value = "gocgzhpage")
    public String gocgzhpage(HttpSession session, Model model, @RequestParam(required = false, defaultValue = "1", value = "pageNum") Integer pageNum,
                           @RequestParam(defaultValue = "15", value = "pageSize") Integer pageSize,
                           @RequestParam(defaultValue = "-1", value = "start") String start,
                           @RequestParam(defaultValue = "-1", value = "end") String end,
                           @RequestParam(defaultValue = "-1", value = "judgestatus") String judgestatus) {
        UserInfo user = (UserInfo) session.getAttribute("USER_SESSION");
        if (!user.getRoletype().equals("1")){
            return "pages/test";
        }
        PageHelper.startPage(pageNum, pageSize);
        //1.引入分页插件,pageNum是第几页，pageSize是每页显示多少条,默认查询总数count
        //2.紧跟的查询就是一个分页查询-必须紧跟.后面的其他查询不会被分页，除非再次调用PageHelper.startPage
        try {
            List<Cgzh> cgzhList = cgzhService.selectCgzhByDetail(user.getDepartment(), user.getNumber(), "-1",start, end, judgestatus, "1");
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
            if (!judgestatus.equals("-1")) {
                model.addAttribute("judgestatus", judgestatus);
            }
        } finally {
            PageHelper.clearPage(); //清理 ThreadLocal 存储的分页参数,保证线程安全
        }

        return "teacher/teachercgzh";
    }

    @GetMapping(value = "gocgzhwrite")
    public String gocgzhwrite(HttpSession session, Model model) {
        UserInfo user = (UserInfo) session.getAttribute("USER_SESSION");
        if (!user.getRoletype().equals("1")){
            return "pages/test";
        }
        return "teacher/teachercgzhwrite";
    }

    @PostMapping(value = "putcgzh")
    @ResponseBody
    public Result putcgzh(@RequestBody Cgzh cgzh, HttpSession session) {
        UserInfo user = (UserInfo) session.getAttribute("USER_SESSION");


        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String systime = LocalDate.now().format(formatter); // get the current date

        cgzh.setDepartment(user.getDepartment());
        cgzh.setName(user.getName());
        cgzh.setNumber(user.getNumber());
        cgzh.setSystime(systime);

//        cgzh.setDepartment(user.getDepartment()).setName(user.getName()).setNumber(user.getNumber()).setSystime(systime);
        boolean flag = cgzhService.insertCgzh(cgzh);
        if (flag) {
            return new Result(1, "添加成功");
        }
        return new Result(0, "添加失败");
    }

    @PostMapping(value = "deletecgzh")
    @ResponseBody
    public Result deletecgzh(@RequestBody List<String> list, HttpSession session) {
        UserInfo user = (UserInfo) session.getAttribute("USER_SESSION");
        if (list.isEmpty()) {
            return new Result(1, "所选可删除内容为空");
        }
        cgzhService.deleteFjscByList(list);
        boolean flag = cgzhService.deleteCgzh(list, user.getNumber(), user.getDepartment());
        //先去找一下 干脆 查询出来  根据审核状态 进行判断 是否可以删除 放在前端处理？
        if (flag) {
            return new Result(1, "删除成功");
        } else {
            return new Result(0, "删除失败");
        }
    }

    @PostMapping(value = "cgzhshow")
    @ResponseBody
    public Cgzh cgzhshow(String id, HttpSession session) {
        UserInfo user = (UserInfo) session.getAttribute("USER_SESSION");
        Cgzh cgzh = cgzhService.showCgzh(user.getDepartment(), user.getNumber(), id);
        return cgzh;
    }

    @PostMapping(value = "cgzhupdate")
    @ResponseBody
    public Result cgzhupdate(@RequestBody Cgzh cgzh, HttpSession session) {
        UserInfo user = (UserInfo) session.getAttribute("USER_SESSION");
        if (cgzh.getFjsc() != null) {
            List<String> list = new ArrayList<>();
            list.add((cgzh.getCid().toString()));
            cgService.deleteFjscByList(list);
        }

        cgzh.setDepartment(user.getDepartment());
        cgzh.setName(user.getName());
        cgzh.setNumber(user.getNumber());

//        if (cgzh.getSystime() == null) {
//            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//            String systime = LocalDate.now().format(formatter); // get the current date
//            cgzh.setSystime(systime);
//        } else if (cgzh.getSystime().equals("-1")) {
            cgzh.setSystime(null);
//        }


//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//        String systime = LocalDate.now().format(formatter); // get the current date
//        cgzh.setDepartment(user.getDepartment()).setName(user.getName()).setNumber(user.getNumber()).setSystime(systime);
        boolean flag = cgzhService.updateCgzh(cgzh);
        if (flag) {
            return new Result(1, "更新成功");
        } else {
            return new Result(0, "更新失败");
        }
    }

    @PostMapping(value = "downloadcgzhfjsc")
    @ResponseBody
    public Result downloadcgzhfjsc(@RequestBody List<String> list) {
        String zipFileName = cgzhService.downloadcgzhZipFjsc(list);
        return new Result(1, zipFileName);
    }

    @GetMapping(value = "cgzhexportall")
    public void cgzhexportall(HttpSession session, HttpServletResponse response) {
        UserInfo user = (UserInfo) session.getAttribute("USER_SESSION");
        String department = user.getDepartment();
        String number = user.getNumber();
        String fileName = department + "-" + number + "-" + user.getName() + "-成果转化通过审核" + UUID.randomUUID().toString().replaceAll("-", "") + ".xls";
        HSSFWorkbook wb = cgzhService.exportcgzhExcel(department, number, user.getRoletype());
        //响应到客户端
        try {
            uploadController.setResponseHeader(response, fileName);
            OutputStream os = response.getOutputStream();
            wb.write(os);
            os.flush();
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                wb.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    @PostMapping(value = "cgzhExcelJson")
    @ResponseBody
    public Result cgzhExcelJson(HttpSession session, @RequestBody Map<String, Object> mapdata) {
        List<String> params = (List<String>) mapdata.get("params");
        if (params.size()==0){
            return new Result(0,"请务必选择列参数");
        }

        UserInfo user = (UserInfo) session.getAttribute("USER_SESSION");
        String start = (String) mapdata.get("startExcelTime");
        String end = (String) mapdata.get("endExcelTime");
        String roletype = user.getRoletype();
        List<Map<Object, Object>> map = null;
        String fileName = null;
        if (roletype.equals("1")) {
            //传递过去 number  department  params 传过去
            fileName = cgzhService.getExcelJson(user.getDepartment(), user.getNumber(),"-1", start, end, "3", params);
        } else if (roletype.equals("2")) {
            String name = (String) mapdata.get("name");
            fileName = cgzhService.getExcelJson(user.getDepartment(), "-1",name, start, end, "3", params);
        } else if (roletype.equals("3")) {
            String department = (String) mapdata.get("department");
            String name = (String) mapdata.get("name");

            fileName = cgzhService.getExcelJson(department, "-1",name, start, end, "3", params);
        }
        return new Result(1, fileName);
    }
    //智库建设
    @GetMapping(value = "gozkjspage")
    public String gozkjspage(HttpSession session, Model model, @RequestParam(required = false, defaultValue = "1", value = "pageNum") Integer pageNum,
                           @RequestParam(defaultValue = "15", value = "pageSize") Integer pageSize,
                           @RequestParam(defaultValue = "-1", value = "start") String start,
                           @RequestParam(defaultValue = "-1", value = "end") String end,
                           @RequestParam(defaultValue = "-1", value = "judgestatus") String judgestatus) {
        UserInfo user = (UserInfo) session.getAttribute("USER_SESSION");
        if (!user.getRoletype().equals("1")){
            return "pages/test";
        }
        PageHelper.startPage(pageNum, pageSize);
        //1.引入分页插件,pageNum是第几页，pageSize是每页显示多少条,默认查询总数count
        //2.紧跟的查询就是一个分页查询-必须紧跟.后面的其他查询不会被分页，除非再次调用PageHelper.startPage
        try {
            List<Zkjs> zkjsList = zkjsService.selectZkjsByDetail(user.getDepartment(), user.getNumber(), "-1",start, end, judgestatus, "1");
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
            if (!judgestatus.equals("-1")) {
                model.addAttribute("judgestatus", judgestatus);
            }
        } finally {
            PageHelper.clearPage(); //清理 ThreadLocal 存储的分页参数,保证线程安全
        }

        return "teacher/teacherzkjs";
    }

    @GetMapping(value = "gozkjswrite")
    public String gozkjswrite(HttpSession session, Model model) {
        UserInfo user = (UserInfo) session.getAttribute("USER_SESSION");
        if (!user.getRoletype().equals("1")){
            return "pages/test";
        }
        return "teacher/teacherzkjswrite";
    }

    @PostMapping(value = "putzkjs")
    @ResponseBody
    public Result putzkjs(@RequestBody Zkjs zkjs, HttpSession session) {
        UserInfo user = (UserInfo) session.getAttribute("USER_SESSION");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String systime = LocalDate.now().format(formatter); // get the current date

        zkjs.setDepartment(user.getDepartment());
        zkjs.setName(user.getName());
        zkjs.setNumber(user.getNumber());
        zkjs.setSystime(systime);

//        zkjs.setDepartment(user.getDepartment()).setName(user.getName()).setNumber(user.getNumber()).setSystime(systime);
//        System.out.println(zkjs);
        boolean flag = zkjsService.insertZkjs(zkjs);
        if (flag) {
            return new Result(1, "添加成功");
        }
        return new Result(0, "添加失败");
    }

    @PostMapping(value = "deletezkjs")
    @ResponseBody
    public Result deletezkjs(@RequestBody List<String> list, HttpSession session) {
        UserInfo user = (UserInfo) session.getAttribute("USER_SESSION");
        if (list.isEmpty()) {
            return new Result(1, "所选可删除内容为空");
        }
        zkjsService.deleteFjscByList(list);
        boolean flag = zkjsService.deleteZkjs(list, user.getNumber(), user.getDepartment());
        //先去找一下 干脆 查询出来  根据审核状态 进行判断 是否可以删除 放在前端处理？
        if (flag) {
            return new Result(1, "删除成功");
        } else {
            return new Result(0, "删除失败");
        }
    }

    @PostMapping(value = "zkjsshow")
    @ResponseBody
    public Zkjs zkjsshow(String id, HttpSession session) {
        UserInfo user = (UserInfo) session.getAttribute("USER_SESSION");
        System.out.println(id);
        Zkjs zkjs = zkjsService.showZkjs(user.getDepartment(), user.getNumber(), id);
        return zkjs;
    }

    @PostMapping(value = "zkjsupdate")
    @ResponseBody
    public Result zkjsupdate(@RequestBody Zkjs zkjs, HttpSession session) {
        UserInfo user = (UserInfo) session.getAttribute("USER_SESSION");
        if (zkjs.getFjsc() != null) {
            List<String> list = new ArrayList<>();
            list.add((zkjs.getZid().toString()));
            zkjsService.deleteFjscByList(list);
        }

        zkjs.setDepartment(user.getDepartment());
        zkjs.setName(user.getName());
        zkjs.setNumber(user.getNumber());

//        if (zkjs.getSystime() == null) {
//            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//            String systime = LocalDate.now().format(formatter); // get the current date
//            zkjs.setSystime(systime);
//        } else if (zkjs.getSystime().equals("-1")) {
            zkjs.setSystime(null);
//        }

//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//        String systime = LocalDate.now().format(formatter); // get the current date
//        zkjs.setDepartment(user.getDepartment()).setName(user.getName()).setNumber(user.getNumber()).setSystime(systime);
        boolean flag = zkjsService.updateZkjs(zkjs);
        if (flag) {
            return new Result(1, "更新成功");
        } else {
            return new Result(0, "更新失败");
        }
    }

    @PostMapping(value = "downloadzkjsfjsc")
    @ResponseBody
    public Result downloadzkjsfjsc(@RequestBody List<String> list) {
        String zipFileName = zkjsService.downloadzkjsZipFjsc(list);
        return new Result(1, zipFileName);
    }

    @GetMapping(value = "zkjsexportall")
    public void zkjsexportall(HttpSession session, HttpServletResponse response) {
        UserInfo user = (UserInfo) session.getAttribute("USER_SESSION");
        String department = user.getDepartment();
        String number = user.getNumber();
        String fileName = department + "-" + number + "-" + user.getName() + "-智库建设通过审核" + UUID.randomUUID().toString().replaceAll("-", "") + ".xls";
        HSSFWorkbook wb = zkjsService.exportzkjsExcel(department, number, user.getRoletype());
        //响应到客户端
        try {
            uploadController.setResponseHeader(response, fileName);
            OutputStream os = response.getOutputStream();
            wb.write(os);
            os.flush();
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                wb.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    @PostMapping(value = "zkjsExcelJson")
    @ResponseBody
    public Result zkjsExcelJson(HttpSession session, @RequestBody Map<String, Object> mapdata) {
        List<String> params = (List<String>) mapdata.get("params");
        if (params.size()==0){
            return new Result(0,"请务必选择列参数");
        }

        UserInfo user = (UserInfo) session.getAttribute("USER_SESSION");
        String start = (String) mapdata.get("startExcelTime");
        String end = (String) mapdata.get("endExcelTime");
        String roletype = user.getRoletype();
        List<Map<Object, Object>> map = null;
        String fileName = null;
        if (roletype.equals("1")) {
            //传递过去 number  department  params 传过去
            fileName = zkjsService.getExcelJson(user.getDepartment(), user.getNumber(),"-1", start, end, "3", params);
        } else if (roletype.equals("2")) {
            String name = (String) mapdata.get("name");
            fileName = zkjsService.getExcelJson(user.getDepartment(), "-1",name, start, end, "3", params);
        } else if (roletype.equals("3")) {
            String department = (String) mapdata.get("department");
            String name = (String) mapdata.get("name");

            fileName = zkjsService.getExcelJson(department,"-1", name, start, end, "3", params);
        }
        return new Result(1, fileName);
    }
}
