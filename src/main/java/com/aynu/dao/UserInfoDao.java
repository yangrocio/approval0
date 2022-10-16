package com.aynu.dao;

import com.aynu.bean.User;
import com.aynu.bean.UserInfo;
import com.aynu.dto.TableDto;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author Susuper 果冻
 * @version 1.0
 * @date 2020/4/11 22:19
 * @description
 */
@Mapper
@Repository
public interface UserInfoDao {

    @Select("select * from userinfo where number=#{number} and roletype=#{roletype}")
    public UserInfo UserLogin(@Param("number") String number, @Param("roletype") String roletype);

    @Select("select * from userinfo where number=#{number} and roletype=#{roletype}")
    public UserInfo SelectUserInfo(@Param("number") String number, @Param("roletype") String roletype);

    public boolean UpdateUserInfo(UserInfo userInfo);

    public boolean UpdateUserPwd(UserInfo user);


    public boolean UpdateUserRoleType(@Param("user") User user, @Param("newroletype") String newroletype);

    public boolean UpdateUserLoginStatus(@Param("number") String number, @Param("loginstatus") String loginstatus);


    @Select("select * from userinfo order by roletype desc,department")
    public List<UserInfo> SelectUserInfoAll();

    @Select("select * from userinfo where number=#{number}")
    User FindUserExist(String number);

//    @Insert("insert into userinfo(name,number,password,roletype,major,sex,department) values(#{name},#{number},#{password},#{roletype},#{major},#{sex},#{department})")
    boolean InsertUser(UserInfo userInfo);
    //存在问题 TODO

    @Select("select * from userinfo where number=#{number}")
    List<UserInfo> SelectUserInfosByNumber(@Param("number") String number);

    //按照性别 和院系分组来查询
    @Select("select department,sex,count(*) count from userinfo group by department,sex")
    List<TableDto> SelectCountByDepartAndSex();

    @Select("select * from userinfo where department=#{department}")
    List<UserInfo> selectUserInfoByDepart(String department);

    //插入 一个 list集合
    int InsertManyUserByList(@RequestParam("userInfos") List<UserInfo> userInfos);

    //学校多条件查询出来一个 userinfo 集合
    List<UserInfo> selectSchoolDetail(@Param("department") String department,
                                      @Param("teaid") String teaid,
                                      @Param("tea_name") String tea_name,
                                      @Param("roletype") String roletype);

    //换页 多条件查询
    List<UserInfo> selectUserInfoByDetail(@Param("department") String department,
                                          @Param("teaid") String teaid,
                                          @Param("tea_name") String tea_name,
                                          @Param("roletype") String roletype);
}
