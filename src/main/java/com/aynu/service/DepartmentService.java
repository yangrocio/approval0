package com.aynu.service;

import com.aynu.bean.Department;

import java.util.List;

/**
 * @author susuper
 * @version 1.0
 * @date 2020/8/29 16:51
 */
public interface DepartmentService {
    boolean save(Department department);
    boolean delete(Long aLong);
    Department getById(Long aLong);
    List<Department> findAll();

}
