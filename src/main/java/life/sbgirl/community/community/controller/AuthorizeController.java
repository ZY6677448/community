package life.sbgirl.community.community.controller;

import life.sbgirl.community.community.dto.AccessTokenDTO;
import life.sbgirl.community.community.dto.GithubUser;
import life.sbgirl.community.community.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Author : sbgirl
 * @Date: 2020/11/16 0:09
 **/
@Controller
public class AuthorizeController {
    @Autowired
    private GithubProvider githubProvider;

    @GetMapping("/callback")
    public  String callback(@RequestParam(name = "code") String code,
                            @RequestParam(name="state")String state){
        
        AccessTokenDTO accesstokendto = new AccessTokenDTO();
        accesstokendto.setCode(code);
        accesstokendto.setState(state);
        accesstokendto.setClient_id("3b595ece13f7451cf63d");
        accesstokendto.setClient_secret("80a1832599e7c2fe4cc4cd44af138b60f1b28b25");
        accesstokendto.setRedirect_uri("http://localhost:8887/callback");
        String accessToken = githubProvider.getAccessToken(accesstokendto);
        GithubUser userone = githubProvider.getuser(accessToken);
        System.out.println(userone.getName());
        System.out.println(userone.getId());
        System.out.println(userone.getBio());
        return "index";
    }

}
