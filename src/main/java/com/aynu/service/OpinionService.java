package com.aynu.service;

import com.aynu.bean.Opinion;
import org.springframework.stereotype.Service;

/**
 * @author Susuper 果冻
 * @version 1.0
 * @date 2020/4/30 16:42
 * @description
 */

public interface OpinionService {
    public boolean insertOpinion(Opinion opinion);
    public boolean updateOpinion(Opinion opinion);
    public boolean deleteOpinion(Opinion opinion);
    public Opinion selectOpinion(Integer tid,  String tusername, String roletype);


}
