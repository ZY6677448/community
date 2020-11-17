package life.sbgirl.community.community.mapper;

import life.sbgirl.community.community.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * @Author : sbgirl
 * @Date: 2020/11/17 20:04
 **/
@Mapper
public interface UserMapper {
    @Insert("insert into USER(name,account_id,token,gmt_create,gmt_modify) values(#{name},#{accountId},#{token},#{gmtCreate},#{gmtModify})")
    void insert(User user);

    }

