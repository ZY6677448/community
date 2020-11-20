package life.sbgirl.community.community.controller;

import life.sbgirl.community.community.dto.QuestionDTO;
import life.sbgirl.community.community.mapper.QuestionMapper;
import life.sbgirl.community.community.mapper.UserMapper;
import life.sbgirl.community.community.model.Question;
import life.sbgirl.community.community.model.User;
import life.sbgirl.community.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;


/**
 * @Author : sbgirl
 * @Date: 2020/11/14 14:45
 **/
@Controller
public class IndexController {
    @Autowired(required = false)
    private  UserMapper userMapper;

    @Autowired (required = false)
    private QuestionService questionService;

    @GetMapping("/")
    public String index(HttpServletRequest request,
                        Model model){
        Cookie[] cookies = request.getCookies();
        if (cookies!=null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")) {
                    String token = cookie.getValue();
                    User user = userMapper.findByToken(token);
                    if (user != null) {
                        request.getSession().setAttribute("user", user);
                    }
                    break;
                }
            }
        }

        List<QuestionDTO> questionList =questionService.list();
        model.addAttribute("questions",questionList);
            return "index";
    }

}
