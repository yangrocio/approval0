package com.aynu.service.impl;

import com.aynu.bean.TeacherDoc;
import com.aynu.dao.TeacherDocDao;
import com.aynu.dto.*;
import com.aynu.service.TeacherDocService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author Susuper 果冻
 * @version 1.0
 * @date 2020/4/13 9:39
 * @description
 */
@Service
public class TeacherDocServiceImpl implements TeacherDocService {
    @Autowired
    TeacherDocDao teacherDocDao;

    @Override
    public boolean insertTeacherDoc(TeacherDoc teacherDoc) {
        return teacherDocDao.insertTeacherDoc(teacherDoc);
    }

    @Override
    public List<TeacherDoc> selectTeacherDto(String tusername, String roletype) {
        return teacherDocDao.selectTeacherDto(tusername, roletype);
    }

    @Override
    public TeacherDoc selectTeacherDoc(String tusername, String roletype, String tid) {
        return teacherDocDao.selectTeacherDoc(tusername, roletype, tid);
    }

    @Override
    public boolean deleteTeacherDoc(String tusername, String roletype, String tid) {
        return teacherDocDao.deleteTeacherDoc(tusername, roletype, tid);
    }

    @Override
    public List<TeacherDoc> selectDepartmentDocDto(String department, Integer t1, Integer t2, Integer t3, Integer t4, Integer t5, Integer t6) {
        return teacherDocDao.selectDepartmentDocDto(department, t1, t2, t3, t4, t5, t6);
    }

    @Override
    public TeacherDoc selectTeacherDocSingle(String tid) {
        return teacherDocDao.selectTeacherDocSingle(tid);
    }

    @Override
    public boolean dUpdateTeacherDoc(DepartmentSaveWrite departmentSaveWrite) {
        return teacherDocDao.dUpdateTeacherDoc(departmentSaveWrite);
    }

    public boolean UpdateDocforTeacherAll(TeacherDoc teacherDoc) {
        return teacherDocDao.UpdateDocforTeacherAll(teacherDoc);
    }

    @Override
    public boolean UpdateSchoolSure(SchoolSure schoolSure) {
        return teacherDocDao.UpdateSchoolSure(schoolSure);
    }

    @Override
    public boolean Updatejudge(String judge, String tid) {
        return teacherDocDao.Updatejudge(judge, tid);
    }

    public List<BriefTeacher> selectBrief(String judgestatus) {
        return teacherDocDao.selectBrief(judgestatus);
    }

    public List<TeacherDoc> selectByname(String tusername, String roletype,  Integer t1, Integer t2, Integer t3, Integer t4, Integer t5, Integer t6) {
        return teacherDocDao.selectByname(tusername, roletype, t1,t2,t3,t4,t5,t6);
    }


    public List<TeacherDoc> selectDepartmentDocDtoByThree(Integer t1, Integer t2,
                                                   Integer t3, Integer t4,Integer t5, Integer t6){
        return teacherDocDao.selectDepartmentDocDtoByThree(t1,t2,t3,t4,t5,t6);
    }

    public List<TeacherDoc> selectDepartmentDocDtoByDetail(Integer t1, Integer t2,
                                                    Integer t3, Integer t4,Integer t5, Integer t6,String department, String teaid, String tea_name){
        if (department.equals("请选择")){
            department = null;
        }
        if (teaid.equals("-1")){
            teaid = null;
        }
        if (tea_name.equals("-1")){
            tea_name = null;
        }
        return teacherDocDao.selectUserInfoByDetail(t1,t2,t3,t4,t5,t6,department,teaid,tea_name);
    }




    public List<TeacherDoc> selectTeacherDocByNumber(String number){
        return teacherDocDao.selectTeacherDocByNumber(number);
    }


    public List<TeacherDoc> selectDocListByUsername(String username){
        return teacherDocDao.selectTeacherDocByNumber(username);
    }

    @Override
    public List<BriefTeacher> selectBriefByDepartment(String s, String department) {
        return teacherDocDao.selectBriefByDepartment(s,department);
    }

    @Override
    public List<TeacherDoc> selectDocByDetail(String start, String end, String roletype, String number) {
        return teacherDocDao.selectDocByDetail(start,end,roletype,number);
    }


}
