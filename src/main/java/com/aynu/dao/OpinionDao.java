package com.aynu.dao;

import com.aynu.bean.Opinion;
import org.apache.ibatis.annotations.*;

/**
 * @author Susuper 果冻
 * @version 1.0
 * @date 2020/4/30 16:11
 * @description
 */
@Mapper
public interface OpinionDao {
    //增加
    @Insert("insert into opinion(tid,tusername,roletype,departopinion,schoolopinion) values(#{tid},#{tusername},#{roletype},#{departopinion},#{schoolopinion})")
    public boolean insertOpinion(Opinion opinion);
    //修改  设置为null
    @Update("update opinion set departopinion=#{departopinion},schoolopinion=#{schoolopinion} where  tid=#{tid} and tusername=#{tusername} and roletype=#{roletype}")
    public boolean updateOpinion(Opinion opinion);
    //查询
    @Select("select * from opinion where tid=#{tid} and tusername=#{tusername} and roletype=#{roletype}")
    public Opinion selectOpinion(@Param("tid") Integer tid, @Param("tusername") String tusername, @Param("roletype") String roletype);
    @Delete("delete from opinion where tid=#{tid} and tusername=#{tusername} and roletype=#{roletype}")
    public boolean deleteOpinion(Opinion opinion);
}
