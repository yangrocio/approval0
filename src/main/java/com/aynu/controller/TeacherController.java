package com.aynu.controller;

import com.aynu.Utils.ExcelUtils;
//import com.aynu.annotation.OperationLogDetail;
import com.aynu.bean.*;
import com.aynu.dto.*;
import com.aynu.enums.OperationType;
import com.aynu.enums.OperationUnit;
import com.aynu.service.ApplyforallService;
import com.aynu.service.OpinionService;
import com.aynu.service.TeacherDocService;
import com.aynu.service.ZschService;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


import javax.servlet.http.HttpSession;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author Susuper 果冻
 * @version 1.0
 * @date 2020/4/12 11:42
 * @description
 */

@RestController
@RequestMapping(value = "/teacher")
@Slf4j
public class TeacherController {
    @Autowired
    TeacherDocService teacherDocService;

    @Autowired
    OpinionService opinionService;

    @Autowired
    ZschService zschService;

    @Autowired
    ApplyforallService applyforallService;

    /**
     * 教师完成文档后 保存
     *
     * @param session
     * @return
     */
    @RequestMapping(value = "/SaveDoc", method = RequestMethod.POST)
//    @OperationLogDetail(detail = "对申请内容进行了最终完善确认",level = 3,operationType = OperationType.UPDATE,operationUnit = OperationUnit.UNKNOWN)
    public Result SaveDoc(@RequestBody Zscq zscq, HttpSession session) {
        UserInfo user = (UserInfo) session.getAttribute("USER_SESSION");
        zscq.setTusername(user.getNumber()).setRoletype(user.getRoletype()).setDepartment(user.getDepartment()).setName(user.getName());

        Applyforall applyforall = applyforallService.selectApplyByNumber(user.getNumber());
        String type = zscq.getTcategory();
        log.info(applyforall.toString());
        if (type.equals("发明专利")){
            if (applyforall.getFmzl()>0){
                applyforallService.updateApplyByNumber("fmzl",applyforall.getFmzl()-1,user.getNumber());
            }else{
                return new Result(0, "发明专利申请已达上限");
            }
        }else if (type.equals("实用新型专利")){
            if (applyforall.getXxzl()>0){
                applyforallService.updateApplyByNumber("xxzl",applyforall.getXxzl()-1,user.getNumber());
            }else{
                return new Result(0, "实用新型专利申请已达上限");
            }
        }else if (type.equals("外观设计专利")){
            if (applyforall.getSjzl()>0){
                applyforallService.updateApplyByNumber("sjzl",applyforall.getSjzl()-1,user.getNumber());
            }else{
                return new Result(0, "外观设计专利申请已达上限");
            }
        }else if (type.equals("软件著作权")){
            if (applyforall.getRjzzq()>0){
                applyforallService.updateApplyByNumber("rjzzq",applyforall.getRjzzq()-1,user.getNumber());
            }else{
                return new Result(0, "软件著作权申请已达上限");
            }
        }else if (type.equals("集成电路布图设计")){
            if (applyforall.getBtsj()>0){
                applyforallService.updateApplyByNumber("btsj",applyforall.getBtsj()-1,user.getNumber());
            }else{
                return new Result(0, "集成电路布图设计申请已达上限");
            }
        }else if (type.equals("其他")){
            if (applyforall.getQtsj()>0){
                applyforallService.updateApplyByNumber("qtsj",applyforall.getQtsj()-1,user.getNumber());
            }else{
                return new Result(0, "其他申请已达上限");
            }
        }



        if (zscq.getJudgestatus() == null) {
            zscq.setJudgestatus("1");
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        zscq.setAdate(sdf.format(new Date()));
        System.out.println(zscq);
        boolean flag = zschService.insertZscq(zscq);

//        boolean flag = teacherDocService.insertTeacherDoc(zscq);


        if (flag == true) {
            return new Result(1, "成功");
        }
        return new Result(0, "错误");
    }

    @PostMapping(value = "/UpdateDoc")
//    @OperationLogDetail(detail = "对申请内容进行了完善，仍不完善",level = 3,operationType = OperationType.UPDATE,operationUnit = OperationUnit.UNKNOWN)
    public Result UpdateDoc(@RequestBody TeacherDoc teacherDoc, HttpSession session) {
        UserInfo user = (UserInfo) session.getAttribute("USER_SESSION");


        Applyforall applyforall = applyforallService.selectApplyByNumber(user.getNumber());
        String type = teacherDoc.getTcategory();
        if (type.equals("发明专利")){
            if (applyforall.getFmzl()>0){
                applyforallService.updateApplyByNumber("fmzl",applyforall.getFmzl()-1,user.getNumber());
            }else{
                return new Result(0, "发明专利申请已达上限");
            }
        }else if (type.equals("实用新型专利")){
            if (applyforall.getXxzl()>0){
                applyforallService.updateApplyByNumber("xxzl",applyforall.getFmzl()-1,user.getNumber());
            }else{
                return new Result(0, "实用新型专利申请已达上限");
            }
        }else if (type.equals("外观设计专利")){
            if (applyforall.getSjzl()>0){
                applyforallService.updateApplyByNumber("sjzl",applyforall.getFmzl()-1,user.getNumber());
            }else{
                return new Result(0, "外观设计专利申请已达上限");
            }
        }else if (type.equals("软件著作权")){
            if (applyforall.getRjzzq()>0){
                applyforallService.updateApplyByNumber("rjzzq",applyforall.getFmzl()-1,user.getNumber());
            }else{
                return new Result(0, "软件著作权申请已达上限");
            }
        }else if (type.equals("集成电路布图设计")){
            if (applyforall.getBtsj()>0){
                applyforallService.updateApplyByNumber("btsj",applyforall.getFmzl()-1,user.getNumber());
            }else{
                return new Result(0, "集成电路布图设计申请已达上限");
            }
        }else if (type.equals("其他")){
            if (applyforall.getQtsj()>0){
                applyforallService.updateApplyByNumber("qtsj",applyforall.getFmzl()-1,user.getNumber());
            }else{
                return new Result(0, "其他申请已达上限");
            }
        }


        teacherDoc.setTusername(user.getNumber());
        teacherDoc.setRoletype(user.getRoletype());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        teacherDoc.setAdate(sdf.format(new Date()));
        //需要 用到的 roletype  username tid
        Opinion opinion = new Opinion(user.getNumber(), user.getRoletype(), teacherDoc.getTid());
        opinionService.deleteOpinion(opinion);


        boolean flag = teacherDocService.UpdateDocforTeacherAll(teacherDoc);
        if (flag == true) {
            return new Result(1, "成功");
        }
        return new Result(0, "错误");
    }


    //没用到
    @RequestMapping(value = "/SimpleDoc", method = RequestMethod.GET)
    public void SimpleDoc(Model model, ModelAndView modelAndView, HttpSession session) {
        UserInfo user = (UserInfo) session.getAttribute("USER_SESSION");
        //根据user里面的类型和number 查询出来一组数据
        List<TeacherDoc> teacherDocDtoList = teacherDocService.selectTeacherDto(user.getNumber(), user.getRoletype());
        modelAndView.addObject("teacherDocDtoList", teacherDocDtoList);
        modelAndView.setViewName("/pages/teacherpage");
    }

//1 等待院系审核 2 等待学校审核 3 通过学校审核 4 院系退回 5 学校退回  100的话 是可以编辑


    /**
     * 通过了院系审核 并保存 设置为2
     *
     * @param departmentSaveWrite
     * @return
     */
    @PostMapping(value = "/updateUnitOpinion")
//    @OperationLogDetail(detail = "院系负责人通过了申请审核",level = 3,operationType = OperationType.UPDATE,operationUnit = OperationUnit.UNKNOWN)
    public Result updateUnitOpinion(@RequestBody DepartmentSaveWrite departmentSaveWrite) {
        departmentSaveWrite.setJudge("2");  //表示通过院系审核了 等待学校审核
        teacherDocService.dUpdateTeacherDoc(departmentSaveWrite);
        return new Result(1, "ok");
    }

    /**
     * 院系审核不通过 设置为4
     */
    @PostMapping(value = "/dQuitDoc")
//    @OperationLogDetail(detail = "院系负责人拒绝通过了申请审核",level = 3,operationType = OperationType.UPDATE,operationUnit = OperationUnit.UNKNOWN)
    public Result dQuitDoc(@RequestBody Opinion opinion) {
        boolean flag = opinionService.insertOpinion(opinion);
        if (opinion.getDepartopinion() != null) {
            //学院退回
            flag = flag && teacherDocService.Updatejudge("4", String.valueOf(opinion.getTid()));
        } else if (opinion.getSchoolopinion() != null) {
            //学校退回
            flag = flag && teacherDocService.Updatejudge("5", String.valueOf(opinion.getTid()));
        }
        if (flag == true) {
            return new Result(1, "成功");
        }
        return new Result(0, "错误");

    }


    /**
     * 通过了学校审核
     *
     * @param schoolSure
     * @return
     */
    @PostMapping(value = "/suresave")
//    @OperationLogDetail(detail = "学校负责人通过了申请审核",level = 3,operationType = OperationType.UPDATE,operationUnit = OperationUnit.UNKNOWN)
    public Result suresave(@RequestBody SchoolSure schoolSure) {
        schoolSure.setJudge("3");
        teacherDocService.UpdateSchoolSure(schoolSure);
        return new Result(1, "ok");
    }

    /**
     * 展示退回观点
     *
     * @param tid
     * @param httpSession
     * @return
     */
    @PostMapping(value = "/ShowQuitOpinion")
    public Result ShowQuitOpinion(String tid, HttpSession httpSession) {
        UserInfo user = (UserInfo) httpSession.getAttribute("USER_SESSION");
        Opinion opinion = opinionService.selectOpinion(Integer.valueOf(tid), user.getNumber(), user.getRoletype());
        String message = null;
        if (opinion.getSchoolopinion() != null) {
            message = opinion.getSchoolopinion();
        } else {
            message = opinion.getDepartopinion();
        }
        return new Result(1, message);
    }

    //导出功能  将所有是5的数据关键信息导出到excel表格中，暂时先不打印，后面再考虑，先打印到控制台
    //院系 申请人 项目名称 申请人电话 申请人邮箱  学校通过日期
    // 是否需要在普通人申请时候输入日期


    //用户这边直接使用 自己的session数据查询
    @GetMapping(value = "/getTeaBonus")
    public List<TeacherDoc> getTeaBonus(HttpSession session){
        UserInfo user = (UserInfo) session.getAttribute("USER_SESSION");
        return teacherDocService.selectTeacherDocByNumber(user.getNumber());
    }

    @PostMapping(value = "/getDocListBynumber")
    public List<TeacherDoc> getTeacherListBynumber(String number){
        return teacherDocService.selectTeacherDocByNumber(number);
    }


}
