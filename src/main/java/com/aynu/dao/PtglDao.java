package com.aynu.dao;

import com.aynu.bean.PtglEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author guojingdong
 */
@Repository
@Mapper
public interface PtglDao {
    List<PtglEntity> findCondition(@Param("department") String department,
                                   @Param("platform") String platform,
                                   @Param("start") String start,
                                   @Param("end") String end);

    @Update("UPDATE ptgl SET  annex=#{annex} WHERE id=#{id}")
    boolean updateAnnex(@Param("annex") String annex,@Param("id") String id);

}
