package com.tg.blog.core.service.impl;

import com.tg.blog.core.model.ArticleContent;
import com.tg.blog.core.mapper.ArticleContentMapper;
import com.tg.blog.core.service.ArticleContentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
public class ArticleContentServiceImpl extends ServiceImpl<ArticleContentMapper, ArticleContent> implements ArticleContentService {

    @Override
    public String releaseAndUpdateContent(ArticleContent articleContent) {
        if (StringUtils.isEmpty(articleContent.getId())){
            baseMapper.insert(articleContent);
        }else{
            if(baseMapper.updateById(articleContent) <=  0){
                throw  new RuntimeException("内容更新失败");
            }
        }
        return articleContent.getId();
    }
}
