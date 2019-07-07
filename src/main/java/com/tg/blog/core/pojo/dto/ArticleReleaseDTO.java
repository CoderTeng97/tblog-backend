package com.tg.blog.core.pojo.dto;


import lombok.Data;



/**
 * @Author: TengGang
 * @Date: 2019/7/6 15:16
 * @Description: 文章发布DTO　用户文章参数接受及传输
 */
@Data
public class ArticleReleaseDTO  {
    /**
     * 标题
     */
    private String title;

    /**
     * 描述
     */
    private String description;

    private String content;

    /**
     * 作者
     */
    private String auther;

    /**
     * 标签
     */
    private String label;

    /**
     * 分类类型
     */
    private String classification;

    /**
     * 图片url 支持png,jpg
     */
    private String picUrl;

}
