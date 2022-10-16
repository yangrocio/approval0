package com.aynu.dao;

import com.aynu.bean.Notice;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author susuper
 * @version 1.0
 * @date 2020/7/25 11:18
 */
@Mapper
public interface NoticeDao {
    @Insert("insert into notice(noticeTop,noticeArticle,generateDate,number,name,assnumber) values(#{noticeTop},#{noticeArticle},#{generateDate}" +
            ",#{number},#{name},#{assnumber})")
    public boolean insertNotice(Notice notice);

    @Select(" select * from notice where assnumber='全体人员' or assnumber=#{assnumber} order by generateDate desc")
//    @Select("select * from notice order by generateDate desc ")
    public List<Notice> selectNoticeAll(String assnumber);

    @Select("select * from notice order by generateDate desc")
//    @Select("select * from notice order by generateDate desc ")
    public List<Notice> selectNoticeAllTwo();

    @Delete("delete from notice where nid=#{nid}")
    public boolean deleteNoticeById(Integer nid);

    @Select("select * from notice where nid=#{nid}")
    public Notice selectNoticeById(Integer nid);

}
