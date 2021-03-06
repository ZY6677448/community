package life.sbgirl.community.community.model;

import lombok.Data;

/**
 * @Author : sbgirl
 * @Date: 2020/11/18 19:25
 **/
@Data
public class Question {
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


}
