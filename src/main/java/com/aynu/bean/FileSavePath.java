package com.aynu.bean;

import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigInteger;

/**
 * @author Susuper 果冻
 * @version 1.0
 * @date 2020/5/3 18:25
 * @description
 */
@Data
@Accessors(chain = true)
public class FileSavePath {
    String tusername;
    String roletype;
    BigInteger tid;
    String filename;

//    public FileSavePath(String id){
//        tid = id;
//    }

//    public FileSavePath(BigInteger bigInteger){
//        this.tid = bigInteger;
//    }




}
