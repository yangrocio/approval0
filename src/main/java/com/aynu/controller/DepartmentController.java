package com.aynu.controller;

import com.aynu.bean.Department;
import com.aynu.dto.Result;
import com.aynu.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

/**
 * @author susuper
 * @version 1.0
 * @date 2020/8/29 17:00
 */
@RestController
@RequestMapping(value = "/department")
public class DepartmentController {
    @Autowired
    DepartmentService departmentService;

    /**
     * 向数据库中添加院系
     * @return
     */
    @RequestMapping(value = "/save",method = RequestMethod.POST)
    public Result save(@RequestBody Department department){
        List<Department> list = departmentService.findAll();
        for (Department department1:list){
            if (department.getName().equals(department1.getName())){
                return new Result(0,"添加院系重复导致失败");
            }
        }
        boolean flag = departmentService.save(department);
        if (flag){
            return new Result(1,"添加院系成功");
        }
        return new Result(0,"添加院系失败");

    }

    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    public Result delete(@RequestBody HashMap<String,String> map){
        String id = map.get("id");
        boolean flag = departmentService.delete(Long.parseLong(id));
        if (flag){
            return new Result(1,"删除院系成功");
        }else{
            return new Result(0,"删除院系失败");
        }
    }

    /**
     * 查询所有院系的名字
     * @return
     */
    @RequestMapping(value = "/findAll",method = RequestMethod.GET)
    public HashMap findAll(){
        HashMap map = new HashMap();
        map.put("list",departmentService.findAll());
        map.put("result",new Result(1,"查询院系成功"));
        return map;
    }
}
