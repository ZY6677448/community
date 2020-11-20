package life.sbgirl.community.community.dto;

import life.sbgirl.community.community.model.User;
import lombok.Data;

/**
 * @Author : sbgirl
 * @Date: 2020/11/20 23:27
 **/
@Data
public class QuestionDTO {
    private Integer id;
    private String title;
    private String description;
    private String tag;
    private Long gmtCreate;
    private Long gmtModified;
    private Integer creator;
    private Integer commentCount;
    private Integer viewCount;
    private Integer likeCount;
    private User user;
}
