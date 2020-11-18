package life.sbgirl.community.community.mapper;

import life.sbgirl.community.community.model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author : sbgirl
 * @Date: 2020/11/18 19:20
 **/
@Mapper
public interface QuestionMapper {
    @Insert("insert into QUESTION(title,description,gmt_create,gmt_modified,creator,tag) values(#{title},#{description},#{gmtCreate},#{gmtModified},#{creator},#{tag})")
    public void create(Question question);
}
