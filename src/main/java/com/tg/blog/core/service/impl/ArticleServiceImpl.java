package com.tg.blog.core.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tg.blog.core.model.Article;
import com.tg.blog.core.mapper.ArticleMapper;
import com.tg.blog.core.model.ArticleContent;
import com.tg.blog.core.pojo.vo.ArticleBaseVO;
import com.tg.blog.core.service.ArticleContentService;
import com.tg.blog.core.service.ArticleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Teng
 * @since 2019-07-06
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {

    @Autowired
    ArticleContentService articleContentService;

    @Override
    public Article releaseAndUpdateArticle(Article article){
        if (StringUtils.isEmpty(article.getId())){
            baseMapper.insert(article);
        }else{
            baseMapper.updateById(article);
        }
        return article;
    }

    @Override
    public IPage<ArticleBaseVO> getActiclePageList(Long pageNum, Long PageSize) {
        return baseMapper.defaultQueryArticlePageList(new Page<>(pageNum,PageSize));
    }
}
