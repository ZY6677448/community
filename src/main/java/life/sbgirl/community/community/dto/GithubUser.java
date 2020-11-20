package life.sbgirl.community.community.dto;

import lombok.Data;

/**
 * @Author : sbgirl
 * @Date: 2020/11/16 0:58
 **/
@Data
public class GithubUser {
    private String name;
    private Long  id;
    private String bio;
    private String avatarUrl;

}
