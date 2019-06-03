package cn.wangtao.pojo.article;

import lombok.Data;

/**
 * @ClassName UserArticleCategory
 * @Auth 桃子
 * @Date 2019-5-14 10:49
 * @Version 1.0
 * @Description  用户创建的实体响应，但是分表保存（用户的自定义类别）
 **/
@Data
public class UserArticleCategory  extends  ArticleCategory{
    private String userArticleCategorySeq;
}
