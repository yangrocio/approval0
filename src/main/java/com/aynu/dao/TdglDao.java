package com.aynu.dao;

import com.aynu.bean.TdglEntity;
import com.aynu.dto.Audit;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author guojingdong
 */
@Repository
@Mapper
public interface TdglDao {
    List<TdglEntity> findCondition(@Param("department") String department,
                                   @Param("name") String name,
                                   @Param("start") String start,
                                   @Param("end") String end);

    @Update("UPDATE tdgl SET  annex=#{annex} WHERE id=#{id}")
    boolean updateAnnex(@Param("annex") String annex,@Param("id") String id);

    TdglEntity showtdgl(@Param("department") String department, @Param("no") String no, @Param("id") String id);

    TdglEntity insert(TdglEntity tdglEntity);

    List<TdglEntity> findAll();


    List<String> getFileNameByList(@Param("list") List<String> list);

    //UPDATE 表名
    //SET 列名=表达式，列名=表达式......
    //WHERE 条件//可以省略
//    @Update("update tdgl set type=#{type},name=#{name},no=#{no}," +
//            "source=#{source},subsidize=#{subsidize},level=#{level}," +
//            "approve_department=#{approve_department},department=#{department}," +
//            "principal=#{principal},approved_time=#{approved_time} where  id=#{id}")
    TdglEntity update(TdglEntity tdglEntity);

    boolean updateState(Audit audit);

    List<TdglEntity> selectByDetail(@Param("department") String department, @Param("username") String username,
                                    @Param("name") String name,
                                    @Param("start") String start, @Param("end") String end,
                                    @Param("judgestatus") String judgestatus, @Param("roletype") String roletype);



    //int insertExcel(List<Hylw> list);

}
