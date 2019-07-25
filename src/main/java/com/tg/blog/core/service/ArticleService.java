package com.tg.blog.core.service;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.tg.blog.core.model.Article;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tg.blog.core.pojo.dto.ArticleUpdateDTO;
import com.tg.blog.core.pojo.vo.ArticleBaseVO;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Teng
 * @since 2019-07-06
 */
public interface ArticleService extends IService<Article> {
    /**
     * @Author: TengGang
     * @Date: 2019/7/6 16:58
     * @Param: [article]
     * @return: com.tg.blog.core.model.Article
     * @Description 发布文章内容及更新内容
     */
    Article releaseAndUpdateArticle(Article article);

    /** 
    * @Author: TengGang 
    * @Date: 2019/7/7 15:32
    * @Description: 获取文章分页列表
    * @Param: [pageNum, PageSize]
    * @return: com.baomidou.mybatisplus.core.metadata.IPage<com.tg.blog.core.pojo.vo.ArticleBaseVO>
    */ 
    IPage<ArticleBaseVO> getActiclePageList(Long pageNum,Long PageSize,String searchText);


    /**
     * 关联更新文章信息
     * @param articleUpdateDTO
     * @return
     */
    boolean associatedUpdateArticle(ArticleUpdateDTO articleUpdateDTO);

}
