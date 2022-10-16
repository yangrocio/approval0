package com.aynu.service.impl;

import com.aynu.bean.Department;
import com.aynu.dao.DepartmentDao;
import com.aynu.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author susuper
 * @version 1.0
 * @date 2020/8/29 16:52
 */
@Service
public class DepartmentServiceImpl  implements DepartmentService {
    @Autowired
    DepartmentDao departmentDao;
    @Override
    public boolean save(Department department) {
        return departmentDao.save(department);
    }

    @Override
    public boolean delete(Long aLong) {
        return departmentDao.delete(aLong);
    }

    @Override
    public Department getById(Long aLong) {
        return departmentDao.getById(aLong);
    }

    @Override
    public List<Department> findAll() {
        return departmentDao.findAll();
    }
}
