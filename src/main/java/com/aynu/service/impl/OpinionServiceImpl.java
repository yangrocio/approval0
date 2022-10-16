package com.aynu.service.impl;

import com.aynu.bean.Opinion;
import com.aynu.dao.OpinionDao;
import com.aynu.service.OpinionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Susuper 果冻
 * @version 1.0
 * @date 2020/4/30 16:43
 * @description
 */
@Service
public class OpinionServiceImpl implements OpinionService {
    @Autowired
    OpinionDao opinionDao;

    @Override
    public boolean insertOpinion(Opinion opinion) {
        return opinionDao.insertOpinion(opinion);
    }

    @Override
    public boolean updateOpinion(Opinion opinion) {
        return opinionDao.updateOpinion(opinion);
    }

    @Override
    public boolean deleteOpinion(Opinion opinion) {
        return opinionDao.deleteOpinion(opinion);
    }

    @Override
    public Opinion selectOpinion(Integer tid, String tusername, String roletype) {
        return opinionDao.selectOpinion(tid,tusername,roletype);
    }
}
