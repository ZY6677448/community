package life.sbgirl.community.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Author : sbgirl
 * @Date: 2020/11/14 14:45
 **/
@Controller
public class helloController {
        @GetMapping("/hello")
    public String hello(@RequestParam(name = "name")String name, Model model){
            model.addAttribute("name",name);
            return "hello";
    }
}