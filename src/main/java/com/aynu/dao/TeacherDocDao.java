package com.aynu.dao;

import com.aynu.bean.TeacherDoc;
import com.aynu.dto.*;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * @author Susuper 果冻
 * @version 1.0
 * @date 2020/4/13 9:32
 * @description
 */
@Mapper
@Repository
public interface TeacherDocDao {
    boolean insertTeacherDoc(TeacherDoc teacherDoc);

    @Select("select * from teacherdoc where tusername=#{tusername} and roletype=#{roletype} order by judgestatus")
    List<TeacherDoc> selectTeacherDto(@Param("tusername") String tusername, @Param("roletype") String roletype);


    @Select("select * from teacherdoc where tid=#{tid}")
    TeacherDoc selectTeacherDoc(@Param("tusername") String tusername, @Param("roletype") String roletype, @Param("tid") String tid);

    @Delete("delete from teacherdoc where tusername=#{tusername} and roletype=#{roletype} and tid=#{tid}")
    boolean deleteTeacherDoc(@Param("tusername") String tusername, @Param("roletype") String roletype, @Param("tid") String tid);

    @Select("select * from teacherdoc where department=#{department} " +
            "and judgestatus<>#{t1} and " +
            "judgestatus<>#{t2} and " +
            "judgestatus<>#{t3} and " +
            "judgestatus<>#{t4} and " +
            "judgestatus<>#{t5} and " +
            "judgestatus<>#{t6}   order by department,tusername,judgestatus desc ")
    List<TeacherDoc> selectDepartmentDocDto(@Param("department") String department, @Param("t1") Integer t1, @Param("t2") Integer t2,
                                            @Param("t3") Integer t3, @Param("t4") Integer t4, @Param("t5") Integer t5, @Param("t6") Integer t6);


    @Select("select * from teacherdoc where " +
            "judgestatus<>#{t1} and " +
            "judgestatus<>#{t2} and " +
            "judgestatus<>#{t3} and " +
            "judgestatus<>#{t4} and " +
            "judgestatus<>#{t5} and " +
            "judgestatus<>#{t6}   order by department,tusername,judgestatus desc ")
    List<TeacherDoc> selectDepartmentDocDtoByThree(@Param("t1") Integer t1, @Param("t2") Integer t2,
                                            @Param("t3") Integer t3, @Param("t4") Integer t4, @Param("t5") Integer t5, @Param("t6") Integer t6);


    @Select("select * from teacherdoc where tid=#{tid}")
    TeacherDoc selectTeacherDocSingle(@Param("tid") String tid);

    @Select("select * from teacherdoc where tusername=#{number}")
    List<TeacherDoc> selectTeacherDocByNumber(@Param("number") String number);


    @Select("select * from teacherdoc where tusername=#{tusername} and roletype=#{roletype} and " +
            "judgestatus<>#{t1} and " +
            "judgestatus<>#{t2} and " +
            "judgestatus<>#{t3} and " +
            "judgestatus<>#{t4} and " +
            "judgestatus<>#{t5} and " +
            "judgestatus<>#{t6}   order by judgestatus desc ")
    List<TeacherDoc> selectByname(@Param("tusername") String tusername, @Param("roletype") String roletype, @Param("t1") Integer t1, @Param("t2") Integer t2,
                                  @Param("t3") Integer t3, @Param("t4") Integer t4, @Param("t5") Integer t5, @Param("t6") Integer t6);


    boolean dUpdateTeacherDoc(DepartmentSaveWrite departmentSaveWrite);


    boolean UpdateDocforTeacherAll(TeacherDoc teacherDoc);

    boolean UpdateSchoolSure(SchoolSure schoolSure);

    @Update("update teacherdoc set judgestatus=#{judge} where tid=#{tid}")
    boolean Updatejudge(@Param("judge") String judge, @Param("tid") String tid);


    @Select("select * from teacherdoc where judgestatus=#{judgestatus} order by department,tusername desc ")
    List<BriefTeacher> selectBrief(String judgestatus);


    @Select("select * from teacherdoc where tusername=#{username} order by judgestatus,tcategory")
    List<TeacherDoc> selectDocListByUsername(String username);

    @Select("select * from teacherdoc where judgestatus=#{judgestatus} and department=#{department} order by department,tusername desc ")
    List<BriefTeacher> selectBriefByDepartment(@Param("judgestatus") String s,@Param("department") String department);

    List<TeacherDoc> selectUserInfoByDetail(@Param("t1") Integer t1, @Param("t2") Integer t2,
                                            @Param("t3") Integer t3, @Param("t4") Integer t4, @Param("t5") Integer t5, @Param("t6") Integer t6,
                                            @Param("department") String department,
                                            @Param("teaid") String teaid,
                                            @Param("tea_name") String tea_name);

    @Select("select * from teacherdoc where adate > #{start} and adate < #{end} and roletype=#{roletype} and tusername=#{number}")
    List<TeacherDoc> selectDocByDetail(@Param("start") String start, @Param("end") String end,
                                       @Param("roletype") String roletype,
                                       @Param("number") String number);
}
