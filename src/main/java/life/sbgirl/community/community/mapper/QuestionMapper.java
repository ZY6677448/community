package life.sbgirl.community.community.mapper;

import life.sbgirl.community.community.model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Author : sbgirl
 * @Date: 2020/11/18 19:20
 **/
@Mapper
public interface QuestionMapper {
    @Insert("insert into QUESTION(title,description,gmt_create,gmt_modified,creator,tag) values(#{title},#{description},#{gmtCreate},#{gmtModified},#{creator},#{tag})")
    public void create(Question question);
    @Select("select * from question limit #{offset}, #{size}")
    List<Question> list(@Param(value = "offset") Integer offset,@Param(value = "size") Integer size);
    @Select("select count(1) from question")
    Integer count();
    @Select("select * from question where creator =#{userid} limit #{offset}, #{size} ")
    List<Question> listByUserId(@Param(value = "userid")Integer userid, @Param(value = "offset")Integer offset, @Param(value = "size")Integer size);
    @Select("select count(1) from question  where creator =#{userid} ")
    Integer countByUserid(@Param(value = "userid")Integer userid);
}
