package com.aynu.dao;

import com.aynu.bean.CountChart;
import com.aynu.bean.Hylw;
import com.aynu.bean.Qklw;
import com.aynu.dto.Audit;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author susuper
 * @version 1.0
 * @date 2020/9/10 21:16
 */
@Mapper
@Repository
public interface HylwDao {
    //需要传递 的参数 包括  时间区域(起始日期 和终止日志)   审核状态
    List<Hylw> selectHylwByDetail(@Param("department") String department, @Param("username") String username,
                                  @Param("name") String name,
                                  @Param("start") String start, @Param("end") String end,
                                  @Param("judgestatus") String judgestatus,@Param("roletype") String roletype);

    List<Hylw> selectHylwByDetail2(@Param("department") String department, @Param("username") String username,
                                  @Param("name") String name,
                                  @Param("start") String start, @Param("end") String end,
                                  @Param("judgestatus") String judgestatus,@Param("roletype") String roletype,
                                  @Param("tm")String tm,@Param("dyzz")String dyzz,@Param("qtzz")String qtzz,
                                  @Param("hymc")String hymc,@Param("hysj")String hysj,@Param("hyjb")String hyjb,
                                  @Param("jllb")String jllb);
    //增加功能
    boolean insertHylw(Hylw hylw);

    //删除功能  需要 tid  应该是 删除 一个集合类型的
    boolean deleteHylw(@Param("list") List<String> list,  @Param("number") String number, @Param("department")  String department);


    int deleteById(@Param("list") List<String> list);

    Hylw showHylw(@Param("department") String department, @Param("number") String number,@Param("id") String id);

    boolean updateHylw(Hylw hylw);

    boolean updateHylwByHigh(Audit audit);


    //根据 list集合 获取 文件名称
    List<String> getFileNameByList(@Param("list") List<String> list);

    List<Hylw> selectHylwByDiff(@Param("department") String department, @Param("number") String number,
                                @Param("roletype") String roletype);

    List<Map<Object,Object>> getExcelJson(@Param("department") String department,
                                        @Param("number") String number,
                                        @Param("name") String name,
                                        @Param("start") String start,
                                        @Param("end") String end,
                                        @Param("judgestatus") String judgestatus,
                                        @Param("params") List<String> params);

    int insertExcel(List<Hylw> list);

    @Update("UPDATE hylw SET  fjsc=#{fjscName} WHERE hid=#{id}")
    boolean updateFjscName(@Param("fjscName") String fjscName,@Param("id") String id);

    List<CountChart> getCountChart(@Param("department") String department, @Param("yearstart") Integer yearstart, @Param("yearend") Integer yearend);

}
