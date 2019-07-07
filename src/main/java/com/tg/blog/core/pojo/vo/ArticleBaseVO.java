package com.tg.blog.core.pojo.vo;


import lombok.Data;

import java.time.LocalDateTime;


/**
 * @Author: TengGang
 * @Date: 2019/7/6 15:16
 * @Description: 文章发布DTO　用户文章参数接受及传输
 */
@Data
public class ArticleBaseVO {
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
}
