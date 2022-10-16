package com.aynu.dao;

import com.aynu.bean.CountChart;
import com.aynu.bean.Tyhj;
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
public interface TyhjDao {
    List<Tyhj> selectTyhjByDetail(@Param("department") String department, @Param("username") String username,
                                  @Param("name") String name, @Param("start") String start, @Param("end") String end,
                                  @Param("judgestatus") String judgestatus, @Param("roletype") String roletype);
    List<Tyhj> selectTyhjByDetail2(@Param("department") String department, @Param("username") String username,
                                   @Param("name") String name, @Param("start") String start, @Param("end") String end,
                                   @Param("judgestatus") String judgestatus, @Param("roletype") String roletype, @Param("Awardee") String Awardee, @Param("Entryname") String Entryname,
                                   @Param("SportsLV") String SportsLV, @Param("WinTime") String WinTime);
    boolean insertTyhj(Tyhj tyhj);

    //删除功能  需要 tid  应该是 删除 一个集合类型的
    boolean deleteTyhj(@Param("list") List<String> list, @Param("number") String number, @Param("department") String department);
    int deleteById(@Param("list") List<String> list);
    Tyhj showTyhj(@Param("department") String department, @Param("number") String number, @Param("id") String id);

    boolean updateTyhj(Tyhj tyhj);

    boolean updateTyhjByHigh(Audit audit);
    //根据 list集合 获取 文件名称
    List<String> getFileNameByList(@Param("list") List<String> list);

    List<Tyhj> selectTyhjByDiff(@Param("department") String department, @Param("number") String number,
                                @Param("roletype") String roletype);

    List<Map<Object,Object>> getExcelJson(@Param("department") String department,
                                          @Param("number") String number,
                                          @Param("name") String name,
                                          @Param("start") String start,
                                          @Param("end") String end,
                                          @Param("judgestatus") String judgestatus,
                                          @Param("params") List<String> params);
    int insertExcel(List<Tyhj> list);

    @Update("UPDATE tyhj SET  fjsc=#{fjscName} WHERE tid=#{id}")
    boolean updateFjscName(@Param("fjscName") String fjscName, @Param("id") String id);

    List<CountChart> getCountChart(@Param("department") String department, @Param("yearstart") Integer yearstart, @Param("yearend") Integer yearend);
}
