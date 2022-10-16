package com.aynu.dao;

import com.aynu.bean.Department;
import com.aynu.bean.Profession;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author susuper
 * @version 1.0
 * @date 2020/9/5 18:40
 */
@Mapper
public interface ProfessionDao extends BaseDao<Profession,Long> {
    @Insert("insert into profession(name) values(#{name})")
    boolean save(Profession profession);

    @Override
    @Delete("delete from profession where id=#{aLong}")
    boolean delete(Long aLong);

    @Select("select * from profession")
    List<Profession> findAll();
}
