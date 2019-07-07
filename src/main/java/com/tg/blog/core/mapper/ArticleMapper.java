package com.tg.blog.core.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tg.blog.core.model.Article;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tg.blog.core.pojo.vo.ArticleBaseVO;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Teng
 * @since 2019-07-06
 */
public interface ArticleMapper extends BaseMapper<Article> {
    /**
    * @Author: TengGang
    * @Date: 2019/7/7 15:46
    * @Description: 文章分页列表默认查询
    * @Param: [page]
    * @return: com.baomidou.mybatisplus.core.metadata.IPage<com.tg.blog.core.pojo.vo.ArticleBaseVO>
    */
    IPage<ArticleBaseVO> defaultQueryArticlePageList(Page page);
}
