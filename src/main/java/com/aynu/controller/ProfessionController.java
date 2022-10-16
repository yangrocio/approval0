package com.aynu.controller;

import com.aynu.bean.Department;
import com.aynu.bean.Profession;
import com.aynu.dto.Result;
import com.aynu.service.ProfessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

/**
 * @author susuper
 * @version 1.0
 * @date 2020/9/5 18:44
 */
@RestController
@RequestMapping(value = "/profession")
public class ProfessionController {
    @Autowired
    ProfessionService professionService;

    /**
     * 向数据库中添加职称
     * @return
     */
    @RequestMapping(value = "/save",method = RequestMethod.POST)
    public Result save(@RequestBody Profession profession){
        List<Profession> list = professionService.findAll();
        for (Profession profession1:list){
            if (profession.getName().equals(profession1.getName())){
                return new Result(0,"添加职称重复导致失败");
            }
        }
        boolean flag = professionService.save(profession);
        if (flag){
            return new Result(1,"添加职称成功");
        }
        return new Result(0,"添加职称失败");

    }

    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    public Result delete(@RequestBody HashMap<String,String> map){
        String id = map.get("id");
        boolean flag = professionService.delete(Long.parseLong(id));
        if (flag){
            return new Result(1,"删除职称成功");
        }else{
            return new Result(0,"删除职称失败");
        }
    }

    /**
     * 查询所有职称的名字
     * @return
     */
    @RequestMapping(value = "/findAll",method = RequestMethod.GET)
    public HashMap findAll(){
        HashMap map = new HashMap();
        map.put("list",professionService.findAll());
        map.put("result",new Result(1,"查询成功"));
        return map;
    }
}
