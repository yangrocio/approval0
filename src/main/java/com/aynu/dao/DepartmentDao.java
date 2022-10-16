package com.aynu.dao;

import com.aynu.bean.Department;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author susuper
 * @version 1.0
 * @date 2020/8/29 16:24
 */
@Mapper
public interface DepartmentDao extends BaseDao<Department,Long> {
    //增删 改查


    @Override
    @Insert("insert into department(name) values(#{name})")
    boolean save(Department department);

    @Override
    @Delete("delete from department where id=#{aLong}")
    boolean delete(Long aLong);

    @Override
    @Select("select * from department where id=#{aLong}")
    Department getById(Long aLong);

    @Override
    List<Department> findByPage(Integer start, Integer rows);

    @Override
    @Select("select * from department")
    List<Department> findAll();

    @Override
    Integer findTotals();
}
