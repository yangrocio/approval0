package com.aynu.dao;

import com.aynu.bean.Team;
import com.aynu.dto.Mxhzb;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface TeamDao {
    List<Team> selectAll(@Param("start") String start,@Param("end") String end,@Param("department") String department,@Param("number") String number,@Param("principal") String principal);

}
