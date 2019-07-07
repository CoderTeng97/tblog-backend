package com.tg.blog.core.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author Teng
 * @since 2019-07-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tb_article_content")
public class ArticleContent implements Serializable {

    private static final long serialVersionUID = 1L;


    public ArticleContent(String content) {
        this.content = content;
    }

    public ArticleContent(String id, String content) {
        this.id = id;
        this.content = content;
    }

    @TableId(value = "id", type = IdType.UUID)
    private String id;

    private String content;


}
