package com.aynu.dao;

import com.aynu.bean.CountChart;
import com.aynu.bean.Zkjs;
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
public interface ZkjsDao {
    List<Zkjs> selectZkjsByDetail(@Param("department") String department, @Param("username") String username,
                                  @Param("name") String name, @Param("start") String start, @Param("end") String end,
                                  @Param("judgestatus") String judgestatus, @Param("roletype") String roletype);
    List<Zkjs> selectZkjsByDetail2(@Param("department") String department, @Param("username") String username,
                                   @Param("name") String name, @Param("start") String start, @Param("end") String end,
                                   @Param("judgestatus") String judgestatus, @Param("roletype") String roletype, @Param("cgname") String cgname,
                                   @Param("cnjb") String cnjb, @Param("dyzz") String dyzz, @Param("qtzz") String qtzz,
                                   @Param("cnbm") String cnbm, @Param("cntime") String cntime);
    boolean insertZkjs(Zkjs zkjs);

    //删除功能  需要 tid  应该是 删除 一个集合类型的
    boolean deleteZkjs(@Param("list") List<String> list, @Param("number") String number, @Param("department") String department);
    int deleteById(@Param("list") List<String> list);
    Zkjs showZkjs(@Param("department") String department, @Param("number") String number, @Param("id") String id);

    boolean updateZkjs(Zkjs zkjs);

    boolean updateZkjsByHigh(Audit audit);

    //根据 list集合 获取 文件名称
    List<String> getFileNameByList(@Param("list") List<String> list);

    List<Zkjs> selectZkjsByDiff(@Param("department") String department, @Param("number") String number,
                                @Param("roletype") String roletype);

    List<Map<Object,Object>> getExcelJson(@Param("department") String department,
                                          @Param("number") String number,
                                          @Param("name") String name,
                                          @Param("start") String start,
                                          @Param("end") String end,
                                          @Param("judgestatus") String judgestatus,
                                          @Param("params") List<String> params);
    int insertExcel(List<Zkjs> list);

    @Update("UPDATE zkjs SET  fjsc=#{fjscName} WHERE zid=#{id}")
    boolean updateFjscName(@Param("fjscName") String fjscName, @Param("id") String id);

    List<CountChart> getCountChart(@Param("department") String department, @Param("yearstart") Integer yearstart, @Param("yearend") Integer yearend);

}
