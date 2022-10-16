package com.aynu.dao;

import com.aynu.bean.Zscq;
import com.aynu.dto.Audit;
import com.aynu.dto.dtos.DepartmentOpinion;
import com.aynu.dto.dtos.SchoolOpinion;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author susuper
 * @Date 2020/9/21 20:57
 * @description:
 */
@Mapper
@Component
public interface ZscqDao {
    List<Zscq> selectZscqByDetail(@Param("department") String department, @Param("username") String username,
                                  @Param("start") String start, @Param("end") String end, @Param("judgestatus") String judgestatus,@Param("roletype") String roletype);
    List<Zscq> selectZscqByDetails(@Param("department") String department, @Param("username") String username,
                                  @Param("tcategory") String tcategory, @Param("start") String start,
                                  @Param("end") String end, @Param("judgestatus") String judgestatus,@Param("roletype") String roletype);
    boolean insertZscq(Zscq cg);

    //删除功能  需要 tid  应该是 删除 一个集合类型的
    boolean deleteZscq(@Param("list") List<String> list,  @Param("number") String number, @Param("department")  String department);

    Zscq showZscq(@Param("department") String department, @Param("number") String number,@Param("id") String id);

    boolean updateZscq(Zscq cg);

    boolean updateZscqByHigh(Audit audit);


    boolean updateZschByDepartmentOpinion(DepartmentOpinion departmentOpinion);
    boolean updateZschBySchoolOpinion(SchoolOpinion schoolOpinion);
}
