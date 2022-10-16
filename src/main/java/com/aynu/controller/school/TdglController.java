package com.aynu.controller.school;

import com.aynu.Utils.CosineSimilarity;
import com.aynu.bean.*;
import com.aynu.controller.UploadController;
import com.aynu.dao.TdglDao;
import com.aynu.dto.Audit;
import com.aynu.dto.Result;
import com.aynu.service.DepartmentService;
import com.aynu.service.TdglService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.math.BigInteger;
import java.security.Certificate;
import java.util.ArrayList;
import java.util.List;

/**
 * @author guojingdong
 */
@RequestMapping("/kytd")
@Controller
@Slf4j
public class TdglController {
  @Autowired
  TdglService tdglService;

  @Autowired
  TdglDao tdglDao;
  @Autowired
  UploadController uploadController;
  @Autowired
  DepartmentService departmentService;

    @GetMapping(value = "tdgl/page")
    public String getPage(HttpSession session, Model model,
                          @RequestParam(required = false, defaultValue = "1", value = "pageNum") Integer pageNum,
                          @RequestParam(defaultValue = "15", value = "pageSize") Integer pageSize,
                          @RequestParam(defaultValue = "-2", value = "department") String department,
                          @RequestParam(defaultValue = "-1", value = "name") String name,
                          @RequestParam(defaultValue = "-1", value = "start") String start,
                          @RequestParam(defaultValue = "-1", value = "judgestatus") String judgestatus,
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
            if(roleType.equals("1")){
                List<TdglEntity> tdglEntities = tdglService.selectByDetail(department,user.getNumber(),"-1",start,end,judgestatus,roleType);
                PageInfo<TdglEntity> pageInfo = new PageInfo<TdglEntity>(tdglEntities, pageSize);
                model.addAttribute("pageInfo", pageInfo);
                if (!start.equals("-1")) {
                    model.addAttribute("start", start);
                }
                if (!end.equals("-1")) {
                    model.addAttribute("end", end);
                }
                if (!judgestatus.equals("-1")) {
                    model.addAttribute("name", name);
                }
            }
            else{
                List<TdglEntity> tdglEntities = tdglService.findCondition(department,name, start, end);
                PageInfo<TdglEntity> pageInfo = new PageInfo<TdglEntity>(tdglEntities, pageSize);
                model.addAttribute("pageInfo", pageInfo);
                if (!start.equals("-1")) {
                    model.addAttribute("start", start);
                }
                if (!end.equals("-1")) {
                    model.addAttribute("end", end);
                }
                if (!judgestatus.equals("-1")) {
                    model.addAttribute("name", name);
                }
            }
            List<Department> departments = departmentService.findAll();
            model.addAttribute("department", department);
            model.addAttribute("departments", departments);
            model.addAttribute("judgestatus", judgestatus);
        } finally {
            PageHelper.clearPage();
        }
        return "teacher/scientificTeam";
    }


  @GetMapping(value = "tdgl/goteamwrite")
  public String goteamwrite(HttpSession session, Model model) {
    UserInfo user = (UserInfo) session.getAttribute("USER_SESSION");
    if (!user.getRoletype().equals("1")){
      return "pages/test";
    }
    return "teacher/scientificTeamWrite";
  }


  //下载zip
  @PostMapping(value = "tdgl/download")
  @ResponseBody
  public Result download(@RequestBody List<String> list) {
    String zipFileName = tdglService.downloadhylwZipFjsc(list);
    return new Result(1, zipFileName);
  }

 //添加
  @PostMapping(value = "tdgl/post")
  @ResponseBody
  public Result tdglInsert(@RequestBody TdglEntity tdglEntitie, HttpSession session) {
    UserInfo user = (UserInfo) session.getAttribute("USER_SESSION");
      tdglEntitie.setJudgestatus("1");
      tdglEntitie.setNumber(user.getNumber());
      TdglEntity insert = tdglService.insert(tdglEntitie);
    if(tdglEntitie.getType().equals("团队")|| !user.getRoletype().equals("1"))
    {
      return new Result(1, "添加成功");
    }
    else{
      return new Result(0,"团队页面只能添加团队,添加失败");
    }
  }

  //更新
  @PostMapping(value = "tdgl/put")
  @ResponseBody
  public Result update(@RequestBody TdglEntity tdglEntity,HttpSession session) {
    UserInfo user = (UserInfo) session.getAttribute("USER_SESSION");
    TdglEntity flag = tdglService.update(tdglEntity);
    if(user.getName().equals(tdglEntity.getPrincipal())|| !user.getRoletype().equals("1"))
    {
      return new Result(1, "更新成功");
    }
    else
    {
      return new Result(0,"只有团队负责人可以进行编辑，编辑失败");
    }
  }

  //查看
  @PostMapping(value = "tdgl/teamshow")
  @ResponseBody
  public TdglEntity show(String id, HttpSession session) {
    UserInfo user = (UserInfo) session.getAttribute("USER_SESSION");
    TdglEntity showtdgl = tdglService.showtdgl(user.getDepartment(), user.getNumber(), id);
    return showtdgl;
  }

  //按id删除
  @PostMapping(value = "tdgl/{id}/delete")
  @ResponseBody
  public Result delete(@PathVariable(value = "id") String id, HttpSession session) {
    tdglService.deleteById(new BigInteger(id));
    return new Result(1, "删除成功");
  }

  //审核通过
  @PostMapping(value = "tdgl/passTdgl")
  @ResponseBody
  public Result passTdgl(HttpSession session, @RequestBody Audit audit) {
    System.out.println(audit);
    boolean flag = tdglService.updateState(audit);
    if (flag == true) {
      return new Result(1, "操作成功");
    } else {
      return new Result(0, "操作失败");
    }
  }

  //审核不通过
  @PostMapping(value = "tdgl/noPassTdgl")
  @ResponseBody
  public Result noPassTdgl(HttpSession session, @RequestBody Audit audit) {
    System.out.println(audit);
    boolean flag = tdglService.updateState(audit);
    if (flag == true) {
      return new Result(1, "操作成功");
    } else {
      return new Result(0, "操作失败");
    }
  }

  //审核
  @PostMapping(value = "tdgl/teamCheck")
  @ResponseBody
  public List<String> check(String id, HttpSession session) {
    UserInfo user = (UserInfo) session.getAttribute("USER_SESSION");
    TdglEntity showtdgl = tdglService.showtdgl(user.getDepartment(), user.getNumber(), id);
    //查数据库里有多少叫这个名字的团队
    List<TdglEntity> check = tdglService.selectByDetail(showtdgl.getDepartment(),showtdgl.getNo(),"-1","-1","-1","3","3");
    //存放查到有相同的团队名称的集合
    List<String> name = new ArrayList<String>();
    for(int i = 0;i < check.size(); i ++){
      //如果check=1则调用string，如果不止一个名字一样就调用list
      //前者获得的是界面上的团队名称，后者是数据库拿出来遍历的团队名称
      double  score= CosineSimilarity.getSimilarity(showtdgl.getName(),check.get(i).getName());
      //调用string结果只有0和1
      //调用list结果判断相似度：余弦相似度，余弦值越接近1，也就是两个向量越相似，这就叫"余弦相似性"，
      //余弦值越接近0，也就是两个向量越不相似，也就是这两个字符串越不相似
      // 1.先分词，然后封装分词的结果（看词名和权重） 2.计算词频
      // a=(x1,x2,x3,...) b=(y1,y2,y3,...)
      //x，y代表的是每个词出现的次数
      System.out.println(score);
      if( score >= 0.8 ){
        if(name.size()==0){
          name.add("待审核团队名称为："+showtdgl.getName());
          name.add("与以下团队名称相似度过高：");
        }
        name.add(check.get(i).getName());
      }
    }
    if(name.size()==0){
      name.add("待审核团队名称为："+showtdgl.getName());
      name.add("未找到相似度过高的团队名称！");
    }
    return name;
  }
}
