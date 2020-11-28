package life.sbgirl.community.community.service;

import life.sbgirl.community.community.dto.PageDTO;
import life.sbgirl.community.community.dto.QuestionDTO;
import life.sbgirl.community.community.mapper.QuestionMapper;
import life.sbgirl.community.community.mapper.UserMapper;
import life.sbgirl.community.community.model.Question;
import life.sbgirl.community.community.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author : sbgirl
 * @Date: 2020/11/20 23:30
 **/
@Service
public class QuestionService {
    @Autowired(required = false)
    private UserMapper userMapper;
    @Autowired(required = false)
    private QuestionMapper questionMapper;

    public PageDTO list(Integer page, Integer size) {
        PageDTO pageDTO =new PageDTO();
        Integer totalcount=questionMapper.count();
        pageDTO.setPagination(totalcount,page,size);
        page=page<1?1:page;
        page=page>pageDTO.getTotalPage()?pageDTO.getTotalPage():page;
        Integer offset=size*(page-1);
        List<Question> questions=questionMapper.list(offset,size);
        List<QuestionDTO> questionDTOList=new ArrayList<>();


        for (Question question : questions) {
           User user= userMapper.findById(question.getCreator());

           QuestionDTO questionDTO=new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);

        }
        pageDTO.setQuestions(questionDTOList);
        return pageDTO;
    }


    public PageDTO list(Integer userid, Integer page, Integer size) {
        PageDTO pageDTO =new PageDTO();
        Integer totalcount=questionMapper.countByUserid(userid);
        pageDTO.setPagination(totalcount,page,size);
        page=page<1?1:page;
        page=page>pageDTO.getTotalPage()?pageDTO.getTotalPage():page;

        Integer offset=size*(page-1);
        List<Question> questions=questionMapper.listByUserId(userid,offset,size);
        List<QuestionDTO> questionDTOList=new ArrayList<>();

        for (Question question : questions) {
            User user= userMapper.findById(question.getCreator());
            QuestionDTO questionDTO=new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);

        }
        pageDTO.setQuestions(questionDTOList);
        return pageDTO;

    }
}
