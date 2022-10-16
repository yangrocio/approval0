package com.aynu.controller;

import com.aynu.annotation.OperationLogDetail;
import com.aynu.enums.OperationType;
import com.aynu.enums.OperationUnit;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Susuper 果冻
 * @version 1.0
 * @date 2020/4/11 17:11
 * @description
 */
@Controller
@RequestMapping
public class IndexController {
    @RequestMapping(value = "/page/index")
    public String ToLogin(){
        return "pages/login";
    }
}
