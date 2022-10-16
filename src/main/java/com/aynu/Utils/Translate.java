package com.aynu.Utils;

import com.aynu.bean.TeacherDoc;
//import sun.misc.BASE64Encoder;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

/**
 * @author Susuper 果冻
 * @version 1.0
 * @date 2020/4/16 11:43
 * @description
 */
public class Translate {
    public static HashMap<String, String> TeacherToMap(TeacherDoc teacherDoc) {
        HashMap<String, String> hashMap = new HashMap<>();
        SimpleDateFormat sf = new SimpleDateFormat("yyyy年MM月dd日");
        String onlyid = "zscq" + teacherDoc.getSuredate() + String.valueOf(teacherDoc.getTid());
        onlyid = onlyid.replaceAll("-", "");
        hashMap.put("onlyid", onlyid);
        hashMap.put("tid", String.valueOf(teacherDoc.getTid()));
        hashMap.put("tusername", teacherDoc.getTusername());
        hashMap.put("tname", teacherDoc.getTname());
        hashMap.put("tcategory", teacherDoc.getTcategory());
        hashMap.put("tapply", teacherDoc.getTapply());
        hashMap.put("tapplyname", teacherDoc.getTapplyname());
        if (teacherDoc.getTproject() == null) {
            hashMap.put("tproject", "zzz");  //TODO
        } else {
            hashMap.put("tproject", teacherDoc.getTproject());

        }
        hashMap.put("tprojectid", teacherDoc.getTprojectid());
        hashMap.put("tprojectname", teacherDoc.getTprojectname());
        hashMap.put("tsortname", teacherDoc.getTsortname());
        hashMap.put("tinventionname", teacherDoc.getTinventionname());
        hashMap.put("tunitname", teacherDoc.getTunitname());
        hashMap.put("tphone", teacherDoc.getTphone());
        hashMap.put("temail", teacherDoc.getTemail());
        hashMap.put("tintroduce", teacherDoc.getTintroduce());
        hashMap.put("tduty", teacherDoc.getTduty());
        hashMap.put("tinventionname2", teacherDoc.getTinventionname2());
        hashMap.put("roletype", teacherDoc.getRoletype());
        hashMap.put("judgestatus", teacherDoc.getJudgestatus());
        hashMap.put("dunitopinion", teacherDoc.getDunitopinion());
        hashMap.put("dunitheadname", teacherDoc.getDunitheadname());
        hashMap.put("dunitchapter", teacherDoc.getDunitchapter());
        hashMap.put("ddate", teacherDoc.getDdate());
        hashMap.put("suretduty", teacherDoc.getSuretduty());
        hashMap.put("sureapply", teacherDoc.getSureapply());
        hashMap.put("surename", teacherDoc.getSurename());
        hashMap.put("surechapter", teacherDoc.getSurechapter());
        hashMap.put("department", teacherDoc.getDepartment());
        hashMap.put("kyc", "科研处");
        hashMap.put("suredate", teacherDoc.getSuredate());
        return hashMap;
    }

}
