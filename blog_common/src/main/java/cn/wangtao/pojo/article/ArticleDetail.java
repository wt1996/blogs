package cn.wangtao.pojo.article;


import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName ArticleDetail
 * @Auth 桃子
 * @Date 2019-5-14 10:49
 * @Version 1.0
 * @Description:主键的id与文章的保持一致
 **/
@Data
public class ArticleDetail {

    private  String articleDetailSeq;

    /**
     * 文章对象
     */
    Article article;
    /**
     * 文章标签（系统分类）
     */
    private List<String> tags = new ArrayList<>();

    /**
     * 文章类别
     */
    private List<String> category = new ArrayList<String>();

    /**
     * 文章使用的语言类别
     */
    private String useLanguage;

    /**
     * 上一篇文章详细的ID
     */
    private String previousId;

    /**
     * 下一篇文章详细的Id
     */
    private String nextId;


}