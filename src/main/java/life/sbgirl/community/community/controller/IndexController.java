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
import org.springframework.web.bind.annotation.PostMapping;
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
                        @RequestParam(name="size",defaultValue = "8") Integer size,
                        @RequestParam(name="temperature",defaultValue = "") String temperature,
                        @RequestParam(name="direct",defaultValue = "") String direct,
                        @RequestParam(name="city",defaultValue = "") String city
                        ) throws UnsupportedEncodingException {
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
        model.addAttribute("direct",java.net.URLDecoder.decode(direct, "utf-8"));
        model.addAttribute("temperature",temperature);
        model.addAttribute("city",java.net.URLDecoder.decode(city, "utf-8"));

            return "index";
    }
    @PostMapping("/")
    public String goindex( @RequestParam("city") String city,
                           Model model) throws UnsupportedEncodingException {
        //获取明日温度
        if (city!=null){
            Realtime t=weatherProvider.getTemperature(city);
            String temperature=t.getTemperature();
            String direct=t.getDirect();
            System.out.println(city);
            System.out.println(temperature);
            System.out.println(direct);
            String url="redirect:/?temperature="+temperature+"&direct="+java.net.URLEncoder.encode(direct, "utf-8")+"&city="+java.net.URLEncoder.encode(city, "utf-8");
            System.out.println(url);
            return url;
           // return "redirect:/";
        }
        else {
            return "redirect:/";
        }


    }

}
