package com.aynu.controller.teachercontroller;

import com.aynu.bean.*;
import com.aynu.controller.UploadController;
import com.aynu.dto.Result;
import com.aynu.service.HpjlService;
import com.aynu.service.TyhjService;
import com.aynu.service.YmhjService;
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
 * @date 2020/9/11 22:32
 * 获批奖励 音美比赛获奖 体育比赛获奖 成果
 * //考虑补充一下纵向项目管理下的内容
 */
@Controller
@RequestMapping(value = "/tttjump")
@Slf4j
public class TTTJumpController {
    @Autowired
    HpjlService hpjlService;
    @Autowired
    YmhjService ymhjService;
    @Autowired
    TyhjService tyhjService;

    @Autowired
    UploadController uploadController;

    @GetMapping(value = "gohpjlpage")
    public String gohpjlpage(HttpSession session, Model model, @RequestParam(required = false, defaultValue = "1", value = "pageNum") Integer pageNum,
                             @RequestParam(defaultValue = "15", value = "pageSize") Integer pageSize,
                             @RequestParam(defaultValue = "-1", value = "start") String start,
                             @RequestParam(defaultValue = "-1", value = "end") String end,
                             @RequestParam(defaultValue = "-1", value = "judgestatus") String judgestatus) {
        UserInfo user = (UserInfo) session.getAttribute("USER_SESSION");
        if (!user.getRoletype().equals("1")){
            return "pages/test";
        }
        PageHelper.startPage(pageNum, pageSize);
        try {
            List<Hpjl> hpjls = hpjlService.selectHpjlByDetail(user.getDepartment(), user.getNumber(),"-1", start, end, judgestatus,"1");
            PageInfo<Hpjl> pageInfo = new PageInfo<Hpjl>(hpjls, pageSize);
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

        return "teacher/teacherhpjl";
    }

    @GetMapping(value = "gohpjlwrite")
    public String gohpjlwrite(HttpSession session, Model model) {
        UserInfo user = (UserInfo) session.getAttribute("USER_SESSION");
        if (!user.getRoletype().equals("1")){
            return "pages/test";
        }
        return "teacher/teacherhpjlwrite";
    }

    @PostMapping(value = "puthpjl")
    @ResponseBody
    public Result puthpjl(@RequestBody Hpjl hpjl, HttpSession session) {
        UserInfo user = (UserInfo) session.getAttribute("USER_SESSION");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String systime = LocalDate.now().format(formatter); // get the current date


        hpjl.setDepartment(user.getDepartment());
        hpjl.setName(user.getName());
        hpjl.setNumber(user.getNumber());
        hpjl.setSystime(systime);

//        hpjl.setDepartment(user.getDepartment()).setName(user.getName()).setNumber(user.getNumber()).setSystime(systime);
//        System.out.println(hpjl);
        boolean flag = hpjlService.insertHpjl(hpjl);
        if (flag) {
            return new Result(1, "添加成功");
        }
        return new Result(0, "添加失败");
    }

    @PostMapping(value = "deletehpjl")
    @ResponseBody
    public Result deletehpjl(@RequestBody List<String> list, HttpSession session) {
        UserInfo user = (UserInfo) session.getAttribute("USER_SESSION");
        System.out.println(list);
        if (list.isEmpty()){
            return new Result(1,"所选可删除内容为空");
        }
        hpjlService.deleteFjscByList(list);
        boolean flag = hpjlService.deleteHpjl(list, user.getNumber(), user.getDepartment());
        //先去找一下 干脆 查询出来  根据审核状态 进行判断 是否可以删除 放在前端处理？
        if (flag) {
            return new Result(1, "删除成功");
        } else {
            return new Result(0, "删除失败");
        }
    }

    @PostMapping(value = "hpjlshow")
    @ResponseBody
    public Hpjl hpjlshow(String id, HttpSession session) {
        UserInfo user = (UserInfo) session.getAttribute("USER_SESSION");
        System.out.println(id);
        Hpjl hpjl = hpjlService.showHpjl(user.getDepartment(), user.getNumber(), id);
        return hpjl;
    }

    @PostMapping(value = "hpjlupdate")
    @ResponseBody
    public Result hpjlupdate(@RequestBody Hpjl hpjl, HttpSession session) {
        UserInfo user = (UserInfo) session.getAttribute("USER_SESSION");
        if (hpjl.getFjsc()!=null){
            List<String> list = new ArrayList<>();
            list.add((hpjl.getHid().toString()));
            hpjlService.deleteFjscByList(list);
        }

        hpjl.setDepartment(user.getDepartment());
        hpjl.setName(user.getName());
        hpjl.setNumber(user.getNumber());

//        if (hpjl.getSystime() == null) {
//            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//            String systime = LocalDate.now().format(formatter); // get the current date
//            hpjl.setSystime(systime);
//        } else if (hpjl.getSystime().equals("-1")) {
            hpjl.setSystime(null);
//        }


//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//        String systime = LocalDate.now().format(formatter); // get the current date
//
//
//        hpjl.setDepartment(user.getDepartment()).setName(user.getName()).setNumber(user.getNumber()).setSystime(systime);
//        System.out.println(hpjl);
        boolean flag = hpjlService.updateHpjl(hpjl);
        if (flag) {
            return new Result(1, "更新成功");
        } else {
            return new Result(0, "更新失败");
        }
    }
    @PostMapping(value = "downloadhpjlfjsc")
    @ResponseBody
    public Result downloadhpjlfjsc(@RequestBody List<String> list) {
        String zipFileName = hpjlService.downloadhpjlZipFjsc(list);
        return new Result(1,zipFileName);
    }

    @GetMapping(value = "hpjlexportall")
    public void hpjlexportall(HttpSession session, HttpServletResponse response) {
        UserInfo user = (UserInfo) session.getAttribute("USER_SESSION");
        String department = user.getDepartment();
        String number = user.getNumber();
        String fileName =department + "-" + number +"-"+user.getName()+ "-获批奖励通过审核" + UUID.randomUUID().toString().replaceAll("-", "") + ".xls";
        HSSFWorkbook wb = hpjlService.exporthpjlExcel(department, number, user.getRoletype());
        //响应到客户端
        try {
            uploadController.setResponseHeader(response, fileName);
            OutputStream os = response.getOutputStream();
            wb.write(os);
            os.flush();
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                wb.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @PostMapping(value = "hpjlExcelJson")
    @ResponseBody
    public Result hpjlExcelJson(HttpSession session, @RequestBody Map<String, Object> mapdata) {
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
            fileName = hpjlService.getExcelJson(user.getDepartment(), user.getNumber(), "-1",start, end, "3", params);
        } else if (roletype.equals("2")) {
            String name = (String) mapdata.get("name");
            fileName = hpjlService.getExcelJson(user.getDepartment(),"-1", name, start, end, "3", params);
        } else if (roletype.equals("3")) {
            String department = (String) mapdata.get("department");
            String name = (String) mapdata.get("name");

            fileName = hpjlService.getExcelJson(department, "-1",name, start, end, "3", params);
        }
        return new Result(1, fileName);
    }
    //音美获奖
    @GetMapping(value = "goymhjpage")
    public String goymhjpage(HttpSession session, Model model, @RequestParam(required = false, defaultValue = "1", value = "pageNum") Integer pageNum,
                             @RequestParam(defaultValue = "15", value = "pageSize") Integer pageSize,
                             @RequestParam(defaultValue = "-1", value = "start") String start,
                             @RequestParam(defaultValue = "-1", value = "end") String end,
                             @RequestParam(defaultValue = "-1", value = "judgestatus") String judgestatus) {
        UserInfo user = (UserInfo) session.getAttribute("USER_SESSION");
        if (!user.getRoletype().equals("1")){
            return "pages/test";
        }
        PageHelper.startPage(pageNum, pageSize);
        try {
            List<Ymhj> ymhjs = ymhjService.selectYmhjByDetail(user.getDepartment(), user.getNumber(), "-1",start, end, judgestatus,"1");
            PageInfo<Ymhj> pageInfo = new PageInfo<Ymhj>(ymhjs, pageSize);
            model.addAttribute("pageInfo", pageInfo);
            if (!start.equals("-1")) {
                model.addAttribute("start", start);
            }
            if (!end.equals("-1")) {
                model.addAttribute("end", end);
            }
            model.addAttribute("judgestatus", judgestatus);
        } finally {
            PageHelper.clearPage(); //清理 ThreadLocal 存储的分页参数,保证线程安全
        }

        return "teacher/teacherymhj";
    }

    @GetMapping(value = "goymhjwrite")
    public String goymhjwrite(HttpSession session, Model model) {
        UserInfo user = (UserInfo) session.getAttribute("USER_SESSION");
        if (!user.getRoletype().equals("1")){
            return "pages/test";
        }
        return "teacher/teacherymhjwrite";
    }


    @PostMapping(value = "putymhj")
    @ResponseBody
    public Result putymhj(@RequestBody Ymhj ymhj, HttpSession session) {
        UserInfo user = (UserInfo) session.getAttribute("USER_SESSION");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String systime = LocalDate.now().format(formatter); // get the current date

        ymhj.setDepartment(user.getDepartment());
        ymhj.setName(user.getName());
        ymhj.setNumber(user.getNumber());
        ymhj.setSystime(systime);

//        if (hpjl.getSystime() == null) {
//            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//            String systime = LocalDate.now().format(formatter); // get the current date
//            hpjl.setSystime(systime);
//        } else if (hpjl.getSystime().equals("-1")) {
//            hpjl.setSystime(null);
//        }
//
//        ymhj.setDepartment(user.getDepartment()).setName(user.getName()).setNumber(user.getNumber()).setSystime(systime);
//        System.out.println(ymhj);
        boolean flag = ymhjService.insertYmhj(ymhj);
        if (flag) {
            return new Result(1, "添加成功");
        }
        return new Result(0, "添加失败");

    }

    @PostMapping(value = "deleteymhj")
    @ResponseBody
    public Result deleteymhj(@RequestBody List<String> list, HttpSession session) {
        UserInfo user = (UserInfo) session.getAttribute("USER_SESSION");
        if (list.isEmpty()){
            return new Result(1,"所选可删除内容为空");
        }
        ymhjService.deleteFjscByList(list);
        System.out.println(list);
        boolean flag = ymhjService.deleteYmhj(list, user.getNumber(), user.getDepartment());
        //先去找一下 干脆 查询出来  根据审核状态 进行判断 是否可以删除 放在前端处理？
        if (flag) {
            return new Result(1, "删除成功");
        } else {
            return new Result(0, "删除失败");
        }
    }

    @PostMapping(value = "ymhjshow")
    @ResponseBody
    public Ymhj ymhjshow(String id, HttpSession session) {
        UserInfo user = (UserInfo) session.getAttribute("USER_SESSION");
        System.out.println(id);
        Ymhj ymhj = ymhjService.showYmhj(user.getDepartment(), user.getNumber(), id);
        return ymhj;
    }

    @PostMapping(value = "ymhjupdate")
    @ResponseBody
    public Result ymhjupdate(@RequestBody Ymhj ymhj, HttpSession session) {
        UserInfo user = (UserInfo) session.getAttribute("USER_SESSION");
        if (ymhj.getFjsc()!=null){
            List<String> list = new ArrayList<>();
            list.add((ymhj.getYid().toString()));
            ymhjService.deleteFjscByList(list);
        }

        ymhj.setDepartment(user.getDepartment());
        ymhj.setName(user.getName());
        ymhj.setNumber(user.getNumber());

//        if (ymhj.getSystime() == null) {
//            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//            String systime = LocalDate.now().format(formatter); // get the current date
//            ymhj.setSystime(systime);
//        } else if (ymhj.getSystime().equals("-1")) {
            ymhj.setSystime(null);
//        }

//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//        String systime = LocalDate.now().format(formatter); // get the current date
//        ymhj.setDepartment(user.getDepartment()).setName(user.getName()).setNumber(user.getNumber()).setSystime(systime);
//        System.out.println(ymhj);
        boolean flag = ymhjService.updateYmhj(ymhj);
        if (flag) {
            return new Result(1, "更新成功");
        } else {
            return new Result(0, "更新失败");
        }
    }
    @PostMapping(value = "downloadymhjfjsc")
    @ResponseBody
    public Result downloadymhjfjsc(@RequestBody List<String> list) {
        String zipFileName = ymhjService.downloadymhjZipFjsc(list);
        return new Result(1,zipFileName);
    }

    @GetMapping(value = "ymhjexportall")
    public void ymhjexportall(HttpSession session, HttpServletResponse response) {
        UserInfo user = (UserInfo) session.getAttribute("USER_SESSION");
        String department = user.getDepartment();
        String number = user.getNumber();
        String fileName =department + "-" + number +"-"+user.getName()+ "-音美获奖通过审核" + UUID.randomUUID().toString().replaceAll("-", "") + ".xls";
        HSSFWorkbook wb = ymhjService.exportymhjExcel(department, number, user.getRoletype());
        //响应到客户端
        try {
            uploadController.setResponseHeader(response, fileName);
            OutputStream os = response.getOutputStream();
            wb.write(os);
            os.flush();
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                wb.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @PostMapping(value = "ymhjExcelJson")
    @ResponseBody
    public Result ymhjExcelJson(HttpSession session, @RequestBody Map<String, Object> mapdata) {
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
            fileName = ymhjService.getExcelJson(user.getDepartment(), user.getNumber(),"-1", start, end, "3", params);
        } else if (roletype.equals("2")) {
            String name = (String) mapdata.get("name");
            fileName = ymhjService.getExcelJson(user.getDepartment(), "-1",name, start, end, "3", params);
        } else if (roletype.equals("3")) {
            String department = (String) mapdata.get("department");
            String name = (String) mapdata.get("name");

            fileName = ymhjService.getExcelJson(department, "-1",name, start, end, "3", params);
        }
        return new Result(1, fileName);
    }
    //体育获奖
    @GetMapping(value = "gotyhjpage")
    public String gotyhjpage(HttpSession session, Model model, @RequestParam(required = false, defaultValue = "1", value = "pageNum") Integer pageNum,
                             @RequestParam(defaultValue = "15", value = "pageSize") Integer pageSize,
                             @RequestParam(defaultValue = "-1", value = "start") String start,
                             @RequestParam(defaultValue = "-1", value = "end") String end,
                             @RequestParam(defaultValue = "-1", value = "judgestatus") String judgestatus) {
        UserInfo user = (UserInfo) session.getAttribute("USER_SESSION");
        if (!user.getRoletype().equals("1")){
            return "pages/test";
        }
        PageHelper.startPage(pageNum, pageSize);
        try {
            List<Tyhj> tyhjs = tyhjService.selectTyhjByDetail(user.getDepartment(), user.getNumber(),"-1", start, end, judgestatus,"1");
            PageInfo<Tyhj> pageInfo = new PageInfo<Tyhj>(tyhjs, pageSize);
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

        return "teacher/teachertyhj";
    }

    @GetMapping(value = "gotyhjwrite")
    public String gotyhjwrite(HttpSession session, Model model) {
        UserInfo user = (UserInfo) session.getAttribute("USER_SESSION");
        if (!user.getRoletype().equals("1")){
            return "pages/test";
        }
        return "teacher/teachertyhjwrite";
    }


    @PostMapping(value = "puttyhj")
    @ResponseBody
    public Result puttyhj(@RequestBody Tyhj tyhj, HttpSession session) {
        UserInfo user = (UserInfo) session.getAttribute("USER_SESSION");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String systime = LocalDate.now().format(formatter); // get the current date

        tyhj.setDepartment(user.getDepartment());
        tyhj.setName(user.getName());
        tyhj.setNumber(user.getNumber());
        tyhj.setSystime(systime);




//        tyhj.setDepartment(user.getDepartment()).setName(user.getName()).setNumber(user.getNumber()).setSystime(systime);
//        System.out.println(tyhj);
        boolean flag = tyhjService.insertTyhj(tyhj);
        if (flag) {
            return new Result(1, "添加成功");
        }
        return new Result(0, "添加失败");

    }


    @PostMapping(value = "deletetyhj")
    @ResponseBody
    public Result deletetyhj(@RequestBody List<String> list, HttpSession session) {
        UserInfo user = (UserInfo) session.getAttribute("USER_SESSION");
        if (list.isEmpty()){
            return new Result(1,"所选可删除内容为空");
        }
        tyhjService.deleteFjscByList(list);
        boolean flag = tyhjService.deleteTyhj(list, user.getNumber(), user.getDepartment());
        //先去找一下 干脆 查询出来  根据审核状态 进行判断 是否可以删除 放在前端处理？
        if (flag) {
            return new Result(1, "删除成功");
        } else {
            return new Result(0, "删除失败");
        }
    }

    @PostMapping(value = "tyhjshow")
    @ResponseBody
    public Tyhj tyhjshow(String id, HttpSession session) {
        UserInfo user = (UserInfo) session.getAttribute("USER_SESSION");
        System.out.println(id);
        Tyhj tyhj = tyhjService.showTyhj(user.getDepartment(), user.getNumber(), id);
        return tyhj;
    }

    @PostMapping(value = "tyhjupdate")
    @ResponseBody
    public Result tyhjupdate(@RequestBody Tyhj tyhj, HttpSession session) {
        UserInfo user = (UserInfo) session.getAttribute("USER_SESSION");
        if (tyhj.getFjsc()!=null){
            List<String> list = new ArrayList<>();
            list.add((tyhj.getTid().toString()));
            tyhjService.deleteFjscByList(list);
        }

        tyhj.setDepartment(user.getDepartment());
        tyhj.setName(user.getName());
        tyhj.setNumber(user.getNumber());

//        if (tyhj.getSystime() == null) {
//            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//            String systime = LocalDate.now().format(formatter); // get the current date
//            tyhj.setSystime(systime);
//        } else if (tyhj.getSystime().equals("-1")) {
            tyhj.setSystime(null);
//        }


//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//        String systime = LocalDate.now().format(formatter); // get the current date
//        tyhj.setDepartment(user.getDepartment()).setName(user.getName()).setNumber(user.getNumber()).setSystime(systime);
//        System.out.println(tyhj);
        boolean flag = tyhjService.updateTyhj(tyhj);
        if (flag) {
            return new Result(1, "更新成功");
        } else {
            return new Result(0, "更新失败");
        }
    }

    @PostMapping(value = "downloadtyhjfjsc")
    @ResponseBody
    public Result downloadtyhjfjsc(@RequestBody List<String> list) {
        String zipFileName = tyhjService.downloadtyhjZipFjsc(list);
        return new Result(1,zipFileName);
    }

    @GetMapping(value = "tyhjexportall")
    public void tyhjexportall(HttpSession session, HttpServletResponse response) {
        UserInfo user = (UserInfo) session.getAttribute("USER_SESSION");
        String department = user.getDepartment();
        String number = user.getNumber();
        String fileName =department + "-" + number +"-"+user.getName()+ "-体育获奖通过审核" + UUID.randomUUID().toString().replaceAll("-", "") + ".xls";
        HSSFWorkbook wb = tyhjService.exporttyhjExcel(department, number, user.getRoletype());
        //响应到客户端
        try {
            uploadController.setResponseHeader(response, fileName);
            OutputStream os = response.getOutputStream();
            wb.write(os);
            os.flush();
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                wb.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    @PostMapping(value = "tyhjExcelJson")
    @ResponseBody
    public Result tyhjExcelJson(HttpSession session, @RequestBody Map<String, Object> mapdata) {
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
            fileName = tyhjService.getExcelJson(user.getDepartment(), user.getNumber(),"-1", start, end, "3", params);
        }else if (roletype.equals("2")) {
            String name = (String) mapdata.get("name");
            fileName = tyhjService.getExcelJson(user.getDepartment(),"-1", name, start, end, "3", params);
        } else if (roletype.equals("3")) {
            String department = (String) mapdata.get("department");
            String name = (String) mapdata.get("name");

            fileName = tyhjService.getExcelJson(department, "-1",name, start, end, "3", params);
        }
        return new Result(1, fileName);
    }
}
