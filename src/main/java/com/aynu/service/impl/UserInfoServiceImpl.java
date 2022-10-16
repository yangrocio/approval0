package com.aynu.service.impl;

import com.aynu.annotation.OperationLogDetail;
import com.aynu.bean.User;
import com.aynu.bean.UserInfo;
import com.aynu.dao.UserInfoDao;
import com.aynu.dto.TableDto;
import com.aynu.enums.OperationType;
import com.aynu.enums.OperationUnit;
import com.aynu.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Susuper 果冻
 * @version 1.0
 * @date 2020/4/11 22:23
 * @description
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {
    @Autowired
    UserInfoDao userInfoDao;

    @Override
    public UserInfo UserLogin(String number, String roletype) {
        return userInfoDao.UserLogin(number, roletype);
    }

    public UserInfo SelectUserInfo(String number, String roletype) {
        return userInfoDao.SelectUserInfo(number, roletype);
    }

    @Override
    public boolean UpdateUserInfo(UserInfo userInfo) {
        return userInfoDao.UpdateUserInfo(userInfo);
    }

    @Override
    public boolean UpdateUserPwd(UserInfo user) {
        return userInfoDao.UpdateUserPwd(user);
    }


    public boolean UpdateUserRoleType(User user, String newroletype) {
        return userInfoDao.UpdateUserRoleType(user, newroletype);
    }

    public boolean UpdateUserLoginStatus(String number,String loginstatus){
        return userInfoDao.UpdateUserLoginStatus(number,loginstatus);
    }


    public List<UserInfo> SelectUserInfoAll() {
        return userInfoDao.SelectUserInfoAll();
    }

    public List<UserInfo> selectUserInfoByDetail(String department,String teaid,String tea_name,String roletype){
        if (department.equals("请选择")){
            department = "-1";
        }
//        if (teaid.equals("-1")){
//            teaid = null;
//        }
//        if (tea_name.equals("-1")){
//            tea_name = null;
//        }
        return userInfoDao.selectUserInfoByDetail(department,teaid,tea_name,roletype);
    }


    public List<UserInfo> selectSchoolDetail(String department,String teaid,String tea_name,String roletype){
        if (department.equals("请选择")){
            department = "-1";
        }
//        if (teaid.equals("-1")){
//            teaid = null;
//        }
//        if (tea_name.equals("-1")){
//            tea_name = null;
//        }
        return userInfoDao.selectSchoolDetail(department,teaid,tea_name,roletype);
    }
    public User FindUserExist(String number) {
        return userInfoDao.FindUserExist(number);
    }

    public boolean InsertUser(UserInfo userInfo) {
        return userInfoDao.InsertUser(userInfo);
    }

    public List<UserInfo> SelectUserInfosByNumber(String number) {
        return userInfoDao.SelectUserInfosByNumber(number);
    }

    public List<TableDto> SelectCountByDepartAndSex() {
        return userInfoDao.SelectCountByDepartAndSex();
    }

    @Override
    public int InsertManyUserByList(List<UserInfo> userInfos) {
        return userInfoDao.InsertManyUserByList(userInfos);
    }


    public List<UserInfo> selectUserInfoByDepart(String department){
        return userInfoDao.selectUserInfoByDepart(department);
    }


}
