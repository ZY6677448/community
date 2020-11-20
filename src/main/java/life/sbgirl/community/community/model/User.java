package life.sbgirl.community.community.model;

import lombok.Data;

/**
 * @Author : sbgirl
 * @Date: 2020/11/17 20:06
 **/
@Data
public class User {
    private Integer id;
    private String name;
    private String accountId;
    private String token;
    private Long gmtCreate;
    private Long gmtModify;
    private String avatarUrl;

}
