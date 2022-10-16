package com.aynu.dao;

import com.aynu.bean.BarChartDepartment;
import com.aynu.bean.BarChartTeacher;
import com.aynu.bean.CountChart;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author susuper
 * @Date 2020/12/25 22:22
 * @description:
 */
@Mapper
@Component
public interface TjtDao {
    List<BarChartDepartment> getSDepartmentBarChart(@Param("year") Integer year, @Param("selecttype") String selecttype,@Param("department") String department);

    List<BarChartTeacher> getSTeacherBarChart(@Param("year") Integer year, @Param("selecttype") String selecttype,@Param("department") String department);

    List<CountChart> getCountChart(@Param("selecttype") String selecttype,@Param("department") String department, @Param("yearstart") Integer yearstart, @Param("yearend") Integer yearend);

}
