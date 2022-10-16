package com.aynu.dao;

import com.aynu.bean.CountChart;
import com.aynu.bean.Ymhj;
import com.aynu.dto.Audit;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @author susuper
 * @version 1.0
 * @date 2020/9/11 11:59
 */
@Mapper
@Component
public interface YmhjDao {
    List<Ymhj> selectYmhjByDetail(@Param("department") String department, @Param("username") String username,
                                  @Param("name") String name, @Param("start") String start, @Param("end") String end,
                                  @Param("judgestatus") String judgestatus, @Param("roletype") String roletype);
    List<Ymhj> selectYmhjByDetail2(@Param("department") String department, @Param("username") String username,
                                   @Param("name") String name, @Param("start") String start, @Param("end") String end,
                                   @Param("judgestatus") String judgestatus, @Param("roletype") String roletype,
                                   @Param("TiMu") String TiMu, @Param("AwardName") String AwardName,
                                   @Param("DYZZ") String DYZZ, @Param("QTZZ") String QTZZ, @Param("Organizer") String Organizer,
                                   @Param("RewardSort") String RewardSort, @Param("AwardTime") String AwardTime);
    boolean insertYmhj(Ymhj ymhj);

    //删除功能  需要 tid  应该是 删除 一个集合类型的
    boolean deleteYmhj(@Param("list") List<String> list, @Param("number") String number, @Param("department") String department);
    int deleteById(@Param("list") List<String> list);
    Ymhj showYmhj(@Param("department") String department, @Param("number") String number, @Param("id") String id);

    boolean updateYmhj(Ymhj ymhj);

    boolean updateYmhjByHigh(Audit audit);

    List<String> getFileNameByList(@Param("list") List<String> list);

    List<Ymhj> selectYmhjByDiff(@Param("department") String department, @Param("number") String number,
                                @Param("roletype") String roletype);

    List<Map<Object,Object>> getExcelJson(@Param("department") String department,
                                          @Param("number") String number,
                                          @Param("name") String name,
                                          @Param("start") String start,
                                          @Param("end") String end,
                                          @Param("judgestatus") String judgestatus,
                                          @Param("params") List<String> params);
    int insertExcel(List<Ymhj> list);

    @Update("UPDATE ymhj SET  fjsc=#{fjscName} WHERE yid=#{id}")
    boolean updateFjscName(@Param("fjscName") String fjscName, @Param("id") String id);

    List<CountChart> getCountChart(@Param("department") String department, @Param("yearstart") Integer yearstart, @Param("yearend") Integer yearend);

}
