package com.aynu.service.impl;

import com.aynu.bean.FileSavePath;
import com.aynu.dao.FileSavePathDao;
import com.aynu.service.FileSavePathService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Susuper 果冻
 * @version 1.0
 * @date 2020/5/3 18:31
 * @description
 */
@Service
public class FileSavePathServiceImpl implements FileSavePathService {
    @Autowired
    FileSavePathDao fileSavePathDao;

    @Override
    public boolean insertFileSavePath(String tid, String filename) {
        return fileSavePathDao.insertFileSavePath(tid,filename);
    }

    @Override
    public boolean updateFileSavePath(String tid, String filename) {
        return fileSavePathDao.updateFileSavePath(tid,filename);
    }

    @Override
    public boolean deleteFileSavePath(String tid) {
        return fileSavePathDao.deleteFileSavePath(tid);
    }

    @Override
    public FileSavePath selectFileSavePath(String tid) {
        return fileSavePathDao.selectFileSavePath(tid);
    }
}
