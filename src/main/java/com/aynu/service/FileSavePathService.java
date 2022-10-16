package com.aynu.service;

import com.aynu.bean.FileSavePath;

/**
 * @author Susuper 果冻
 * @version 1.0
 * @date 2020/5/3 18:31
 * @description
 */
public interface FileSavePathService {
    public boolean insertFileSavePath(String tid,String filename);
    public boolean updateFileSavePath(String tid,String filename);
    public boolean deleteFileSavePath(String tid);
    public FileSavePath selectFileSavePath(String tid);

}
