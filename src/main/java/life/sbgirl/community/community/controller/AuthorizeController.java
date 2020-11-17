package life.sbgirl.community.community.controller;

import life.sbgirl.community.community.dto.AccessTokenDTO;
import life.sbgirl.community.community.dto.GithubUser;
import life.sbgirl.community.community.mapper.UserMapper;
import life.sbgirl.community.community.model.User;
import life.sbgirl.community.community.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

/**
 * @Author : sbgirl
 * @Date: 2020/11/16 0:09
 **/
@Controller
public class AuthorizeController {
    @Autowired
    private GithubProvider githubProvider;
    @Value("${github.client.id}")
    private String clientId;
    @Value("${github.client.secret}")
    private String clientSecret;
    @Value("${github.client.redirect.uri}")
    private String clientSetRedirectUri;

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/callback")
    public  String callback(@RequestParam(name = "code") String code,
                            @RequestParam(name="state")String state,
                            HttpServletRequest request){
        
        AccessTokenDTO accesstokendto = new AccessTokenDTO();
        accesstokendto.setCode(code);
        accesstokendto.setState(state);
        accesstokendto.setClient_id(clientId);
        accesstokendto.setClient_secret(clientSecret);
        accesstokendto.setRedirect_uri(clientSetRedirectUri);
        String accessToken = githubProvider.getAccessToken(accesstokendto);
        GithubUser userone = githubProvider.getuser(accessToken);
        System.out.println(userone.getName());
        if (userone!=null){

            User user = new User();
            user.setToken(UUID.randomUUID().toString());
            user.setName(userone.getName());
            user.setAccountId(String.valueOf(userone.getId()));
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModify(user.getGmtCreate());
            userMapper.insert(user);

            //登录成功，写cookie session
            request.getSession().setAttribute("user",userone);
            return "redirect:/";

        }else {
            //登录失败 重新登录
            return "redirect:/";
        }

    }

}
