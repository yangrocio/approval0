package com.aynu.service;

import com.aynu.bean.TeacherDoc;
import com.aynu.bean.User;
import com.aynu.bean.UserInfo;
import com.aynu.dto.TableDto;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Susuper 果冻
 * @version 1.0
 * @date 2020/4/11 22:23
 * @description
 */
public interface UserInfoService {
    public UserInfo UserLogin(String number, String roletype);

    public boolean UpdateUserInfo(UserInfo userInfo);

    public boolean UpdateUserPwd(UserInfo user);

    public UserInfo SelectUserInfo( String number, String roletype);

    public boolean UpdateUserRoleType(User user,String newroletype);

    public boolean UpdateUserLoginStatus(String number,String loginstatus);



    public List<UserInfo> SelectUserInfoAll();


    public List<UserInfo> selectUserInfoByDetail(String department,String teaid,String tea_name,String roletype);

    User FindUserExist(String number);

    boolean InsertUser(UserInfo userInfo);

    List<UserInfo> SelectUserInfosByNumber(String number);

    List<UserInfo> selectUserInfoByDepart(String department);

    List<TableDto> SelectCountByDepartAndSex();

    int InsertManyUserByList(List<UserInfo> userInfos);

    List<UserInfo> selectSchoolDetail(String department,String teaid,String tea_name,String roletype);


}
