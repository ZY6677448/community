package life.sbgirl.community.community.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author : sbgirl
 * @Date: 2020/11/21 14:27
 **/
@Data
public class PageDTO {
    private List<QuestionDTO> questions;
    private boolean showPreviuos;
    private boolean showFirstPage;
    private boolean showNext;
    private boolean showEndPage;
    private Integer page;
    private List<Integer> pages =new ArrayList<>();
    private Integer totalPage;

    public void setPagination(Integer totalcount, Integer page, Integer size) {
        this.page=page;
        Integer totalPage;
        if (totalcount%size==0){
            totalPage=totalcount/size;
        }else {
            totalPage=totalcount/size+1;
        }
        this.totalPage=totalPage;
        page=page<1?1:page;
        page=page>totalPage?totalPage:page;

        for (int i =page-3;i<=page+3;i++){
            if(i>0&&i<=totalPage)
                pages.add(i);
        }
        //是否展示上一页
        showPreviuos=page==1?false:true;
        //是否展示下一页
        showNext=page==totalPage?false:true;
        //是否展示第一页
        showFirstPage=pages.contains(1)?false:true;
        //是否展示最后一页
        showEndPage=pages.contains(totalPage)?false:true;
    }
}
