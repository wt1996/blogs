package cn.wangtao.pojo.article;

import lombok.Data;

/**
 * @ClassName SysArticleCategory
 * @Auth 桃子
 * @Date 2019-5-14 10:49
 * @Version 1.0
 * @Description  文章类别，系统创建的，语言类别，技术类别等
 **/
@Data
public class SysArticleCategory extends  ArticleCategory{

    /**
    * 系统类别ID
    */
    private String sysArticleCategorySeq;
}
