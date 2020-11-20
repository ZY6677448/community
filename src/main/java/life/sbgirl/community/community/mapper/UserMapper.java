package life.sbgirl.community.community.mapper;

import life.sbgirl.community.community.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

/**
 * @Author : sbgirl
 * @Date: 2020/11/17 20:04
 **/

@Mapper
public interface UserMapper {
    @Insert("insert into USER(name,account_id,token,gmt_create,gmt_modify,avatar_url) values(#{name},#{accountId},#{token},#{gmtCreate},#{gmtModify},#{avatarUrl})")
    void insert(User user);
    @Select("select * from user where token=#{token}")
    User findByToken(@Param("token") String token);
    @Select("select * from user where id=#{id}")
    User findById(@Param("id") Integer id);
}

