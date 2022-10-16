package com.aynu.dao;

import com.aynu.bean.Cg;
import com.aynu.bean.Ymhj;
import com.aynu.dto.Audit;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * @author susuper
 * @version 1.0
 * @date 2020/9/11 11:59
 */
@Mapper
@Component
public interface CgDao {

    List<Cg> selectCgByDetail(@Param("department") String department, @Param("username") String username,
                                @Param("start") String start, @Param("end") String end,
                              @Param("judgestatus") String judgestatus,@Param("roletype") String roletype);
    boolean insertCg(Cg cg);

    //删除功能  需要 tid  应该是 删除 一个集合类型的
    boolean deleteCg(@Param("list") List<String> list,  @Param("number") String number, @Param("department")  String department);

    Cg showCg(@Param("department") String department, @Param("number") String number,@Param("id") String id);

    boolean updateCg(Cg cg);

    boolean updateCgByHigh(Audit audit);

    //根据 list集合 获取 文件名称
    List<String> getFileNameByList(@Param("list") List<String> list);

    List<Cg> selectCgByDiff(@Param("department") String department, @Param("number") String number,
                                @Param("roletype") String roletype);
}
