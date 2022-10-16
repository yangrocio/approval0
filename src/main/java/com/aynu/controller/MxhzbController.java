package com.aynu.controller;

import com.aynu.bean.UserInfo;
import com.aynu.dto.Mxhzb;
import com.aynu.service.MxhzbService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.constraints.Max;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.regex.Pattern;

/**
 * @Author susuper
 * @Date 2020/10/29 10:06
 * @description:
 */
@Controller
@RequestMapping(value = "/mxhzb")
@Slf4j
public class MxhzbController {
    @Resource
    MxhzbService mxhzbService;

    @GetMapping("/gomxhzbpage")
    public String gomxhzbpage(HttpSession session, Model model,
                              @RequestParam(required = false, defaultValue = "1", value = "pageNum") Integer pageNum,
                              @RequestParam(defaultValue = "30", value = "pageSize") Integer pageSize,
                              @RequestParam(defaultValue = "-1",value = "start") String start,
                              @RequestParam(defaultValue = "-1",value = "end") String end,
                              @RequestParam(defaultValue = "-1", value = "department") String department,
                              @RequestParam(defaultValue = "-1", value = "number") String number) {
//        long starttime = System.currentTimeMillis();
        UserInfo user = (UserInfo) session.getAttribute("USER_SESSION");
        if (user.getRoletype().equals("1")) {
            //普通教师 把院系传递过去  把 number传递过去
            department = user.getDepartment();
            number = user.getNumber();
        } else if (user.getRoletype().equals("2")) {
            //院系管理员 只需传递一个院系就可以了
            department = user.getDepartment();
        }
        BigDecimal rewardSum = new BigDecimal(0);
        BigDecimal pointSum = new BigDecimal(0);
//        Double rewardSum = 0.0;
//        Double pointSum = 0.0;
        if (start.equals("-1") && end.equals("-1")){
            Calendar date = Calendar.getInstance();
            String year = String.valueOf(date.get(Calendar.YEAR));
            start = year+"-01-01";
            end = year+"-12-31";
        }



        List<Mxhzb> mxhzbs = mxhzbService.selectAll(start, end, department, number);
        for (Mxhzb mxhzb : mxhzbs) {
            if (isNumber(mxhzb.getReward())) {
                rewardSum=rewardSum.add(new BigDecimal(String.valueOf(mxhzb.getReward())));
//                rewardSum = rewardSum + Double.valueOf();
            }
            if (isNumber(mxhzb.getPoints())) {
                pointSum = pointSum.add(new BigDecimal(String.valueOf(mxhzb.getPoints())));
//                pointSum = pointSum + Double.valueOf(mxhzb.getPoints());
            }
        }
        model.addAttribute("rewardSum", rewardSum.doubleValue());
        model.addAttribute("pointSum", pointSum.doubleValue());
        model.addAttribute("total", mxhzbs.size());
        model.addAttribute("mxhzbs", mxhzbs);

//        System.out.println(System.currentTimeMillis()-starttime);

        if (user.getRoletype().equals("1")) {
            return "kytjcommon/mxhzb";
        } else if (user.getRoletype().equals("2")) {
            return "kytjcommon/dmxhzb";
        } else {
            return "kytjcommon/smxhzb";
        }

    }

    public boolean isNumber(String string) {
        if (string == null)
            return false;
        Pattern pattern = Pattern.compile("^-?\\d+(\\.\\d+)?$");
        return pattern.matcher(string).matches();
    }


    @GetMapping(value = "annualExcel")
    public void annualExcel(HttpSession session, HttpServletResponse response, String year) {
        UserInfo user = (UserInfo) session.getAttribute("USER_SESSION");
        log.info(year);
        String department = user.getDepartment();
        String number = user.getNumber();
        HSSFWorkbook wb = null;
        String fileName = null;
        if (user.getRoletype().equals("1")) {
            //普通用户  传递 一个
            fileName = user.getDepartment() + user.getName() + year + "年度科研业绩点和学术成果奖励明细汇总表.xls";
            wb = mxhzbService.annualExcel(fileName, 1, department, number, year + "-01-01", year + "-12-31");
        } else if (user.getRoletype().equals("2")) {
            fileName = user.getDepartment() + year + "年度科研业绩点和学术成果奖励明细汇总表.xls";
            wb = mxhzbService.annualExcel(fileName, 2, department, "-1", year + "-01-01", year + "-12-31");
        } else if (user.getRoletype().equals("3")) {
            fileName = "安阳师范学院" + year + "年度科研业绩点和学术成果奖励明细汇总表.xls";
            wb = mxhzbService.annualExcel(fileName, 3, "-1", "-1", year + "-01-01", year + "-12-31");
        }
        try {
            setResponseHeader(response, fileName);
            OutputStream os = response.getOutputStream();
            wb.write(os);
            os.flush();
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setResponseHeader(HttpServletResponse response, String fileName) {
        try {
            try {
                fileName = new String(fileName.getBytes(), "ISO8859-1");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            response.setContentType("application/octet-stream;charset=ISO8859-1");
            response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
            response.addHeader("Pargam", "no-cache");
            response.addHeader("Cache-Control", "no-cache");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
