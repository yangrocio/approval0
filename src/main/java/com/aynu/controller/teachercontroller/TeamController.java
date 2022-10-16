//package com.aynu.controller.teachercontroller;
//
//import com.aynu.bean.Team;
//import com.aynu.bean.UserInfo;
//import com.aynu.service.TeamService;
//import com.github.pagehelper.PageHelper;
//import com.github.pagehelper.PageInfo;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//
//import javax.servlet.http.HttpSession;
//import java.util.List;
//
//@Controller
//@RequestMapping(value = "/teamJump")
//@Slf4j
//public class TeamController {
//    @Autowired
//    TeamService teamService;
//
//    @GetMapping(value = "goteampage")
//    public String goteampage(HttpSession session, Model model, @RequestParam(required = false, defaultValue = "1", value = "pageNum") Integer pageNum,
//                             @RequestParam(defaultValue = "15", value = "pageSize") Integer pageSize,
//                             @RequestParam(defaultValue = "-1", value = "start") String start,
//                             @RequestParam(defaultValue = "-1", value = "end") String end,
//                             @RequestParam(defaultValue = "-1", value = "judgestatus") String judgestatus) {
//        UserInfo user = (UserInfo) session.getAttribute("USER_SESSION");
//        if (!user.getRoletype().equals("1")){
//            return "pages/test";
//        }
//        PageHelper.startPage(pageNum, pageSize);
//        try {
//            List<Team> teams = teamService.selectTeamByDetail(user.getDepartment(), "-1", user.getSource(), start, end, judgestatus, "1");
//            PageInfo<Team> pageInfo = new PageInfo<Team>(teams, pageSize);
//            model.addAttribute("pageInfo", pageInfo);
//            if (!start.equals("-1")) {
//                model.addAttribute("start", start);
//            }
//            if (!end.equals("-1")) {
//                model.addAttribute("end", end);
//            }
//            if (!judgestatus.equals("-1")) {
//                model.addAttribute("judgestatus", judgestatus);
//            }
//        } finally {
//            PageHelper.clearPage(); //清理 ThreadLocal 存储的分页参数,保证线程安全
//        }
//
//        return "teacher/scientificTeam";
//    }
//}
