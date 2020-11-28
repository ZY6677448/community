package life.sbgirl.community.community.controller;

import life.sbgirl.community.community.dto.PageDTO;
import life.sbgirl.community.community.mapper.UserMapper;
import life.sbgirl.community.community.model.User;
import life.sbgirl.community.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * @Author : sbgirl
 * @Date: 2020/11/21 16:20
 **/
@Controller
public class ProfileController {
    @Autowired(required = false)
    private UserMapper userMapper;
    @Autowired (required = false)
    private QuestionService questionService;

    @GetMapping("/profile/{action}")
    public  String profile(HttpServletRequest request,
                           @PathVariable(name="action") String action,
                           Model model,
                           @RequestParam(name="page",defaultValue = "1") Integer page,
                           @RequestParam(name="size",defaultValue = "8") Integer size){
        User user=null;
        Cookie[] cookies = request.getCookies();
        if (cookies!=null&&cookies.length!=0) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")) {
                    String token = cookie.getValue();
                   user = userMapper.findByToken(token);
                    if (user != null) {
                        request.getSession().setAttribute("user", user);
                    }
                    break;
                }
            }
        }
        if (user==null){
            return "reditect:/";
        }

        if ("questions".equals(action)){
            model.addAttribute("section","questions");
            model.addAttribute("sectionName","我的提问");
        }else if("replies".equals(action)){
            model.addAttribute("section","replies");
            model.addAttribute("sectionName","最新回复");
        }
         PageDTO pageDTO=questionService.list(user.getId(),page,size);
        model.addAttribute("pagination",pageDTO);
        return "profile";
    }
}

