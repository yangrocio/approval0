package com.aynu.service;

import com.aynu.bean.Notice;

import java.util.List;

/**
 * @author susuper
 * @version 1.0
 * @date 2020/7/25 11:40
 */
public interface NoticeService {
    public boolean insertNotice(Notice notice);
    public List<Notice> selectNoticeAll(String assnumber,boolean flag);
    public boolean deleteNoticeById(Integer nid);
    public Notice selectNoticeById(Integer nid);
}
