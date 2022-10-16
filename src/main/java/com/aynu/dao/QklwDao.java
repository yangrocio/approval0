package com.aynu.dao;

import com.aynu.bean.BarChartDepartment;
import com.aynu.bean.CountChart;
import com.aynu.bean.Hylw;
import com.aynu.bean.Qklw;
import com.aynu.dto.Audit;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author susuper
 * @version 1.0
 * @date 2020/9/11 11:53
 */
@Mapper
@Component
public interface QklwDao {
    List<Qklw> selectQklwByDetail(@Param("department") String department, @Param("username") String username,
                                  @Param("name") String name,@Param("start") String  start, @Param("end") String end, @Param("judgestatus") String judgestatus, @Param("roletype") String roletype);

    List<Qklw> selectQklwByDetail2(@Param("department") String department, @Param("username") String username,
                                  @Param("name") String name,@Param("start") String  start, @Param("end") String end, @Param("judgestatus") String judgestatus, @Param("roletype") String roletype,
                                  @Param("timu") String timu,@Param("dyzz") String dyzz,@Param("qtzz") String qtzz,@Param("type") String type,@Param("fbqk") String fbqk,@Param("time") String time,
                                  @Param("fq") String fq);


    boolean insertQklw(Qklw qklw);

    //删除功能  需要 tid  应该是 删除 一个集合类型的
    boolean deleteQklw(@Param("list") List<String> list,  @Param("number") String number, @Param("department")  String department);
    int deleteById(@Param("list") List<String> list);
    Qklw showQklw(@Param("department") String department, @Param("number") String number,@Param("id") String id);

    boolean updateQklw(Qklw qklw);

    boolean updateQklwByHigh(Audit audit);

    //根据 list集合 获取 文件名称
    List<String> getFileNameByList(@Param("list") List<String> list);

    List<Qklw> selectQklwByDiff(@Param("department") String department, @Param("number") String number,
                                @Param("roletype") String roletype);

    List<Map<Object,Object>> getExcelJson(@Param("department") String department,
                                          @Param("number") String number,
                                          @Param("name") String name,
                                              @Param("start") String start,
                                              @Param("end") String end,
                                              @Param("judgestatus") String judgestatus,
                                              @Param("params") List<String> params);
    int insertExcel(List<Qklw> list);

    @Update("UPDATE qklw SET  fjsc=#{fjscName} WHERE qid=#{id}")
    boolean updateFjscName(@Param("fjscName") String fjscName,@Param("id") String id);

    List<CountChart> getCountChart(@Param("department") String department, @Param("yearstart") Integer yearstart, @Param("yearend") Integer yearend);


}
