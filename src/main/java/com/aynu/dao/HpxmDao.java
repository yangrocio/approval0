package com.aynu.dao;

import com.aynu.bean.CountChart;
import com.aynu.bean.Hpxm;
import com.aynu.dto.Audit;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @Author susuper
 * @Date 2020/11/9 8:24
 * @description:
 */
@Mapper
@Component
public interface HpxmDao {
    List<Hpxm> selectHpxmByDetail(@Param("department") String department, @Param("username") String username,
                                  @Param("name") String name, @Param("start") String start, @Param("end") String end,
                                  @Param("judgestatus") String judgestatus, @Param("roletype") String roletype);
    boolean insertHpxm(Hpxm hpxm);

    List<Hpxm> selectHpxmByDetail2(@Param("department") String department, @Param("username") String username,
                                   @Param("name") String name, @Param("start") String start, @Param("end") String end,
                                   @Param("judgestatus") String judgestatus, @Param("roletype") String roletype,
                                   @Param("xmname") String xmname, @Param("hoster") String hoster, @Param("cyry") String cyry,
                                   @Param("xdbm") String xdbm, @Param("xmsource") String xmsource, @Param("prorank") String prorank,
                                   @Param("proproperty") String proproperty, @Param("lxtime") String lxtime);

    //删除功能  需要 tid  应该是 删除 一个集合类型的
    boolean deleteHpxm(@Param("list") List<String> list, @Param("number") String number, @Param("department") String department);
    int deleteById(@Param("list") List<String> list);
    Hpxm showHpxm(@Param("department") String department, @Param("number") String number, @Param("id") String id);

    boolean updateHpxm(Hpxm hpxm);

    boolean updateHpxmByHigh(Audit audit);

    //根据 list集合 获取 文件名称
    List<String> getFileNameByList(@Param("list") List<String> list);

    List<Hpxm> selectHpxmByDiff(@Param("department") String department, @Param("number") String number,
                                @Param("roletype") String roletype);

    List<Map<Object,Object>> getExcelJson(@Param("department") String department,
                                          @Param("number") String number,
                                          @Param("name") String name,
                                          @Param("start") String start,
                                          @Param("end") String end,
                                          @Param("judgestatus") String judgestatus,
                                          @Param("params") List<String> params);
    int insertExcel(List<Hpxm> list);

    @Update("UPDATE hpxm SET  fjsc=#{fjscName} WHERE hid=#{id}")
    boolean updateFjscName(@Param("fjscName") String fjscName, @Param("id") String id);

    List<CountChart> getCountChart(@Param("department") String department, @Param("yearstart") Integer yearstart, @Param("yearend") Integer yearend);
}
