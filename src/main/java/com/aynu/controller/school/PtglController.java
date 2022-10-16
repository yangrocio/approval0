package com.aynu.controller.school;

import com.aynu.bean.Department;
import com.aynu.bean.Hylw;
import com.aynu.bean.PtglEntity;
import com.aynu.bean.UserInfo;
import com.aynu.controller.UploadController;
import com.aynu.dto.Result;
import com.aynu.service.DepartmentService;
import com.aynu.service.PtglService;
import com.aynu.service.TdglService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.math.BigInteger;
import java.util.List;

/**
 * @author guojingdong
 */
@RequestMapping("/kytd")
@Controller
@Slf4j
public class PtglController {
  @Autowired
  PtglService ptglService;

  @Autowired
  TdglService tdglService;

  @Autowired
  UploadController uploadController;
  @Autowired
  DepartmentService departmentService;

  @GetMapping(value = "ptgl/page")
  public String getPtglPage(HttpSession session, Model model,
                            @RequestParam(required = false, defaultValue = "1", value = "pageNum") Integer pageNum,
                            @RequestParam(defaultValue = "15", value = "pageSize") Integer pageSize,
                            @RequestParam(defaultValue = "-2", value = "department") String department,
                            @RequestParam(defaultValue = "-1", value = "name") String name,
                            @RequestParam(defaultValue = "-1", value = "start") String start,
                            @RequestParam(defaultValue = "-1", value = "end") String end) {
    UserInfo user = (UserInfo) session.getAttribute("USER_SESSION");
    String roleType = user.getRoletype();
    if ((roleType.equals("1") || roleType.equals("2"))){  //普通教师和院系管理员 默认进去自己的页面
      if (department.equals("-2")){   //如果没有值  进去自己页面
        department = user.getDepartment();
      }
    }else{  //如果是学校或者更好等级  进去总页面
      if (department.equals("-2")){   //如果没有值  进去自己页面
        department = "-1";
      }
    }
    try {
      PageHelper.startPage(pageNum, pageSize);
      List<PtglEntity> ptglEntities = ptglService.findCondition(department, name, start, end);
      PageInfo<PtglEntity> pageInfo = new PageInfo<PtglEntity>(ptglEntities, pageSize);
      model.addAttribute("pageInfo", pageInfo);
      if (!start.equals("-1")) {
        model.addAttribute("start", start);
      }
      if (!end.equals("-1")) {
        model.addAttribute("end", end);
      }
      if (!name.equals("-1")) {
        model.addAttribute("name", name);
      }
      List<Department> departments = departmentService.findAll();
      model.addAttribute("department", department);
      model.addAttribute("departments", departments);
    } finally {
      PageHelper.clearPage();
    }
    return "school/sptgl";
  }

  @PostMapping(value = "ptgl/post")
  @ResponseBody
  public Result ptglInsert(@RequestBody PtglEntity ptglEntity, HttpSession session) {
    PtglEntity ptgl = ptglService.insert(ptglEntity);
    return new Result(1, "添加成功");
  }

  @PostMapping(value = "ptgl/put")
  @ResponseBody
  public Result ptglUpdate(@RequestBody PtglEntity ptglEntity, HttpSession session) {
    PtglEntity ptgl = ptglService.update(ptglEntity);
    return new Result(1, "更新成功");
  }

  @GetMapping(value = "ptgl/{id}")
  @ResponseBody
  public PtglEntity ptglGet(@PathVariable(value = "id") String id, HttpSession session) {
    return ptglService.findById(new BigInteger(id));
  }

  @PostMapping(value = "ptgl/{id}/delete")
  @ResponseBody
  public Result ptglDelete(@PathVariable(value = "id") String id, HttpSession session) {
    ptglService.deleteById(new BigInteger(id));
    return new Result(1, "删除成功");
  }
}
