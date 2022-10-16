package com.aynu.service;

import com.aynu.bean.TeacherDoc;
import com.aynu.dto.*;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * @author Susuper 果冻
 * @version 1.0
 * @date 2020/4/13 9:38
 * @description
 */
public interface TeacherDocService {
    boolean insertTeacherDoc(TeacherDoc teacherDoc);

    List<TeacherDoc> selectTeacherDto(String tusername, String roletype);

    TeacherDoc selectTeacherDoc(String tusername, String roletype, String tid);

    boolean deleteTeacherDoc(String tusername, String roletype, String tid);

    List<TeacherDoc> selectDepartmentDocDto(String department, Integer t1, Integer t2, Integer t3, Integer t4, Integer t5, Integer t6);

    TeacherDoc selectTeacherDocSingle(String tid);

    boolean dUpdateTeacherDoc(DepartmentSaveWrite departmentSaveWrite);

    boolean UpdateSchoolSure(SchoolSure schoolSure);

    boolean UpdateDocforTeacherAll(TeacherDoc teacherDoc);

    List<TeacherDoc> selectTeacherDocByNumber(String number);


    boolean Updatejudge(String judge, String tid);

    List<BriefTeacher> selectBrief(String judgestatus);


    List<TeacherDoc> selectByname(String tusername, String roletype,  Integer t1, Integer t2, Integer t3, Integer t4, Integer t5, Integer t6);


    List<TeacherDoc> selectDepartmentDocDtoByThree(Integer t1, Integer t2,
                                                   Integer t3, Integer t4,Integer t5, Integer t6);

    List<TeacherDoc> selectDepartmentDocDtoByDetail(Integer t1, Integer t2,
                                                    Integer t3, Integer t4,Integer t5, Integer t6,String department, String teaid, String tea_name);

    List<TeacherDoc> selectDocListByUsername(String username);

    List<BriefTeacher> selectBriefByDepartment(String s, String department);

    List<TeacherDoc> selectDocByDetail(String start,String end,String roletype,String number);
}
