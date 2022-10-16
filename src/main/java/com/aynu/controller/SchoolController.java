package com.aynu.controller;

import com.aynu.bean.Notice;
import com.aynu.bean.TeacherDoc;
import com.aynu.bean.UserInfo;
import com.aynu.dto.Result;
import com.aynu.dto.TableDto;
import com.aynu.service.NoticeService;
import com.aynu.service.TeacherDocService;
import com.aynu.service.UserInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.*;


@RestController
@RequestMapping(value = "/school")
public class SchoolController {
    @Autowired
    UserInfoService userInfoService;
    @Autowired
    TeacherDocService teacherDocService;

    @Autowired
    NoticeService noticeService;

    Logger logger =LoggerFactory.getLogger(this.getClass());

    @PostMapping(value = "/getPersonApply")
    public List<TeacherDoc> getPersonApply(String number) {
        //需要传递过来的数据有 工号 就可以了   主要是根据 numbre获取人家提交的文档  在什么之上的范围
//        List<UserInfo> userInfoList = userInfoService.SelectUserInfosByNumber(number);
        List<TeacherDoc> teacherDocs = teacherDocService.selectTeacherDocByNumber(number);
        return teacherDocs;
    }

    //展示  柱状图的数据
    @PostMapping(value = "/showTableData")
    public List<TableDto> showTableData() {
        List<TableDto> tableDtos_old = userInfoService.SelectCountByDepartAndSex();
        List<TableDto> tableDtos_new = new LinkedList<>();
        Set<String> set_depart = new LinkedHashSet<>();
        for (TableDto tableDto : tableDtos_old) {
            boolean flag = set_depart.add(tableDto.getDepartment());
        }

        //根据 set集合 创建 对象数字 乘以2
        for (String str_depart : set_depart) {
            TableDto tableDto1 = new TableDto(str_depart, 1, 0);
            TableDto tableDto2 = new TableDto(str_depart, 2, 0);
            tableDtos_new.add(tableDto1);
            tableDtos_new.add(tableDto2);
        }
        //遍历 old 集合  将数字  覆盖 new  集合中 出现的情况

        for (TableDto t_new : tableDtos_new) {
            for (TableDto t_old : tableDtos_old) {
                if (t_new.tableCompare(t_old)==true){
                    t_new.setCount(t_old.getCount());
                }
            }
        }

//        System.out.println(tableDtos_new);
        return tableDtos_new;
    }


    @PostMapping(value = "/insertNotice")
    public Result insertNotice(HttpSession session, @RequestParam(value = "notice_top") String noticeTop,
                               @RequestParam(value = "notice_article") String noticeArticle,
                               @RequestParam(value = "datalist_input") String datalist_input){
        UserInfo user = (UserInfo) session.getAttribute("USER_SESSION");
        Notice notice = new Notice();
        notice.setNoticeTop(noticeTop);
        notice.setNoticeArticle(noticeArticle);
        notice.setGenerateDate(new Date());
        notice.setAssnumber(datalist_input);

        notice.setNumber(user.getNumber());
        notice.setName(user.getName());
        System.out.println(notice);
        boolean flag = noticeService.insertNotice(notice);

        if (flag){
            return new Result(1,"good");
        }else{
            return new Result(0,"bad");
        }


    }

    @PostMapping(value = "/deleteNotice")
    public Result deleteNotice(@RequestParam (value = "nid") Integer nid){
        boolean flag = noticeService.deleteNoticeById(nid);
        if (flag){
            return new Result(1,"good");
        }else{
            return new Result(0,"bad");
        }
    }

    @PostMapping(value = "/showNoticeById")
    public Notice showNoticeById(@RequestParam(value = "nid") Integer nid){
        Notice notice = noticeService.selectNoticeById(nid);
        return notice;
    }

    @PostMapping(value = "/getAllNumber")
    public List<UserInfo> getAllNumber(){
        List<UserInfo> userInfos = userInfoService.SelectUserInfoAll();
        UserInfo userInfo = new UserInfo();
        userInfo.setNumber("全体人员");
        userInfo.setName("全体人员");
        userInfos.add(0,userInfo);
        return userInfos;
    }


}
