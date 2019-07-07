package com.tg.blog.core.pojo.vo;

import java.time.LocalDateTime;

/**
 * @Author: TengGang
 * @Date: 2019/7/7 15:56
 * @Description:
 */
public class ArticleDetailVO {
    /**
     * 标题
     */
    private String title;

    /**
     * 描述
     */
    private String description;

    /**
     * 作者
     */
    //private String auther;

    /**
     * 标签
     */
    private String label;

    /**
     * 关联文章id
     */
    private Integer contentId;

    /**
     * 浏览量
     */
    private Integer pageViews;

    /**
     * 创建日期
     */
    private LocalDateTime gmtCreate;

    /**
     * 文章内容
     */
    private String content;
}
