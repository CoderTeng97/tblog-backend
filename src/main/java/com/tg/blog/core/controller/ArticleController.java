package com.tg.blog.core.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tg.blog.base.annotation.ControllerExceptionProcessor;
import com.tg.blog.base.controller.BaseController;
import com.tg.blog.base.enums.ResponseMsgType;
import com.tg.blog.core.model.Article;
import com.tg.blog.core.model.ArticleContent;
import com.tg.blog.core.pojo.dto.ArticleReleaseDTO;
import com.tg.blog.core.service.ArticleContentService;
import com.tg.blog.core.service.ArticleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.sql.Wrapper;

/**
 * @Author: TengGang
 * @Date: 2019/7/6 15:03
 * @Description: 文章控制层
 */
@Api(description = "文章管理Api接口")
@RestController
@RequestMapping("/article")
public class ArticleController extends BaseController {
    @Autowired
    ArticleService articleService;
    @Autowired
    ArticleContentService articleContentService;

    @ApiOperation("获取文章分页列表")
    @GetMapping("/list")
    public Object list(
            @ApiParam(name = "pageNum", value = "页码", defaultValue = "1")
            @RequestParam(name = "pageNum", defaultValue = "1")
                    Long pageNum,
            @ApiParam(name = "pageNum", value = "分页大小", defaultValue = "10")
            @RequestParam(name = "pageSize", defaultValue = "10")
                    Long pageSize
    ) {
        return articleService.getActiclePageList(pageNum, pageSize);
    }


    @ApiOperation("获取文章内容")
    @GetMapping("/content")
    public Object content(
            @ApiParam(name = "contentId", value = "内容id", required = true)
            @RequestParam
                    String contentId
    ) {
        QueryWrapper<ArticleContent> queryWrapper = new QueryWrapper<>();
        return  articleContentService.getOne(queryWrapper.eq("id",contentId));
    }

//    public Object detail(
//            @ApiParam(name = "articleId" ,value = "文章Id" ,required = true)
//            @RequestParam
//            String articleId
//    ){
//        return null;
//    }

    @ApiOperation("发行文章")
    @PostMapping("/release")
    public Object release(ArticleReleaseDTO releaseDTO) {
        Article article = new Article();
        BeanUtils.copyProperties(releaseDTO, article);
        String contentId = articleContentService.releaseAndUpdateContent(new ArticleContent(releaseDTO.getContent()));
        if (StringUtils.isEmpty(contentId)) {
            responseFail("contentId 为空");
        }
        article.setId(contentId);
        articleService.releaseAndUpdateArticle(article);
        return responseOk();

    }

    @ApiOperation("更新文章")
    @PostMapping("/update")
    public Object update(ArticleReleaseDTO releaseDTO, String articleId) {
        if (StringUtils.isEmpty(articleId)) {
            responseFail("articleId 不能为空");
        }
        Article article = new Article();
        article.setId(articleId);
        BeanUtils.copyProperties(releaseDTO, article);
        articleContentService.releaseAndUpdateContent(new ArticleContent(articleId, releaseDTO.getContent()));
        articleService.releaseAndUpdateArticle(article);
        return responseOk();
    }
}
