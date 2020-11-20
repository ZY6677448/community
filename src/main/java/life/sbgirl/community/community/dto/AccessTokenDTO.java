package life.sbgirl.community.community.dto;

import lombok.Data;

/**
 * @Author : sbgirl
 * @Date: 2020/11/16 0:22
 **/
@Data
public class AccessTokenDTO {
    private String client_id;
    private String client_secret;
    private String redirect_uri;
    private String code;
    private String state;

}
