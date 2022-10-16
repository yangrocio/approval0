package com.aynu.service.impl;

import com.aynu.bean.Notice;
import com.aynu.dao.NoticeDao;
import com.aynu.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author susuper
 * @version 1.0
 * @date 2020/7/25 11:41
 */
@Service
public class NoticeServiceImpl implements NoticeService {
    @Autowired
    NoticeDao noticeDao;

    @Override
    public boolean insertNotice(Notice notice) {
        return noticeDao.insertNotice(notice);
    }

    //如果给的是true  就代表是 普通教师在查询公告  给的false则为 学校管理员查询公告
    @Override
    public List<Notice> selectNoticeAll(String assnumber,boolean flag) {
        if (flag == true){
            return noticeDao.selectNoticeAll(assnumber);
        }else{
            return noticeDao.selectNoticeAllTwo();
        }

    }

    @Override
    public boolean deleteNoticeById(Integer nid){
        return noticeDao.deleteNoticeById(nid);
    }

    public Notice selectNoticeById(Integer nid){
        return noticeDao.selectNoticeById(nid);
    }
}
