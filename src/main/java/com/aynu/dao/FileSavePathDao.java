package com.aynu.dao;

import com.aynu.bean.FileSavePath;
import org.apache.ibatis.annotations.*;

/**
 * @author Susuper 果冻
 * @version 1.0
 * @date 2020/5/3 18:26
 * @description
 */
@Mapper
public interface FileSavePathDao {
    @Insert("insert into filesavepath(tid,filename) values(#{tid},#{filename})")
    public boolean insertFileSavePath(@Param("tid") String tid, @Param("filename") String filename);

    @Update("update filesavepath set filename=#{filename}  where  tid=#{tid} ")
    public boolean updateFileSavePath(@Param("tid") String tid, @Param("filename") String filename);

    @Delete("delete from filesavepath where tid=#{tid} ")
    public boolean deleteFileSavePath( @Param("tid") String tid);

    @Select("select * from filesavepath where tid=#{tid} ")
    public FileSavePath selectFileSavePath(@Param("tid") String tid);
}
