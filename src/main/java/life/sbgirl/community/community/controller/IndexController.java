package life.sbgirl.community.community.controller;

import life.sbgirl.community.community.dto.PageDTO;
import life.sbgirl.community.community.dto.QuestionDTO;
import life.sbgirl.community.community.mapper.QuestionMapper;
import life.sbgirl.community.community.mapper.Realtime;
import life.sbgirl.community.community.mapper.UserMapper;
import life.sbgirl.community.community.model.Question;
import life.sbgirl.community.community.model.User;
import life.sbgirl.community.community.provider.WeatherProvider;
import life.sbgirl.community.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
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

    @Autowired (required = false)
    private WeatherProvider weatherProvider;
    @GetMapping("/")
    public String index(HttpServletRequest request,
                        Model model,
                        @RequestParam(name="page",defaultValue = "1") Integer page,
                        @RequestParam(name="size",defaultValue = "8") Integer size) throws UnsupportedEncodingException {
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

        PageDTO pagination =questionService.list(page,size);
        model.addAttribute("pagination",pagination);
        //获取明日温度
        Realtime t=weatherProvider.getTemperature();
        model.addAttribute("temperature",t.getTemperature());
        model.addAttribute("direct",t.getDirect());
            return "index";
    }

}
