package com.tg.blog.core.service;

import com.tg.blog.core.model.ArticleContent;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Teng
 * @since 2019-07-06
 */
public interface ArticleContentService extends IService<ArticleContent> {
    /**
     * @Author: TengGang
     * @Date: 2019/7/6 16:58
     * @Param: [article]
     * @return: com.tg.blog.core.model.Article
     * @Description 发布文章内容及更新内容
     */
    public  String releaseAndUpdateContent(ArticleContent articleContent);
}
