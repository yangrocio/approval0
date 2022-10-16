package com.aynu.dao;

import com.aynu.bean.Applyforall;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

/**
 * @Author susuper
 * @Date 2020/10/25 22:30
 * @description:
 */
@Repository
@Mapper
public interface ApplyforallDao {
    //需要一个 查询 个体  需要一个 更新个体  需要一个更新所有
    @Select("select number,fmzl,xxzl,sjzl,rjzzq,btsj,qtsj from userinfo where number=#{number}")
    public Applyforall selectApplyByNumber(@Param("number") String number);

    boolean updateApplyByNumber(@Param("name") String name,@Param("value") Integer value,@Param("number") String number);

    boolean updateApplyAll(@Param("name") String name,@Param("value") Integer value);
}
