package com.aynu.dao;

import com.aynu.bean.CountChart;
import com.aynu.bean.Hxxm;
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
 * @date 2020/9/11 18:22
 */
@Mapper
@Component
public interface HxxmDao {
    List<Hxxm> selectHxxmByDetail(@Param("department") String department, @Param("username") String username,
                                  @Param("name") String name, @Param("start") String start, @Param("end") String end,
                                  @Param("judgestatus") String judgestatus, @Param("roletype") String roletype);

    List<Hxxm> selectHxxmByDetail2(@Param("department") String department, @Param("username") String username,
                                   @Param("name") String name, @Param("start") String start, @Param("end") String end,
                                   @Param("judgestatus") String judgestatus, @Param("roletype") String roletype,
                                   @Param("XMName") String XMName, @Param("Principal") String Principal, @Param("AUnit") String AUnit,
                                   @Param("ZJE") String ZJE, @Param("ContractNumber") String ContractNumber, @Param("JXTime") String JXTime);

    boolean insertHxxm(Hxxm hxxm);

    //删除功能  需要 tid  应该是 删除 一个集合类型的
    boolean deleteHxxm(@Param("list") List<String> list, @Param("number") String number, @Param("department") String department);
    int deleteById(@Param("list") List<String> list);
    Hxxm showHxxm(@Param("department") String department, @Param("number") String number, @Param("id") String id);

    boolean updateHxxm(Hxxm hxxm);

    boolean updateHxxmByHigh(Audit audit);

    //根据 list集合 获取 文件名称
    List<String> getFileNameByList(@Param("list") List<String> list);

    List<Hxxm> selectHxxmByDiff(@Param("department") String department, @Param("number") String number,
                                @Param("roletype") String roletype);

    List<Map<Object,Object>> getExcelJson(@Param("department") String department,
                                          @Param("number") String number,
                                          @Param("name") String name,
                                          @Param("start") String start,
                                          @Param("end") String end,
                                          @Param("judgestatus") String judgestatus,
                                          @Param("params") List<String> params);
    int insertExcel(List<Hxxm> list);

    @Update("UPDATE hxxm SET  fjsc=#{fjscName} WHERE hid=#{id}")
    boolean updateFjscName(@Param("fjscName") String fjscName, @Param("id") String id);

    List<CountChart> getCountChart(@Param("department") String department, @Param("yearstart") Integer yearstart, @Param("yearend") Integer yearend);
}
