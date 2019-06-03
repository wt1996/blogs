package cn.wangtao.pojo.article;

import cn.wangtao.baseEntity.BasePojoEntity;
import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName Article
 * @Auth 桃子
 * @Date 2019-5-14 10:49
 * @Version 1.0
 **/
@Data
public class Article extends BasePojoEntity implements Serializable {

    private String articleSeq;

    /**
     * 文章标题
     */
    private String title;

    /**
     * 文章关键字
     */
    private String keywords;

    /**
     * 阅读次数
     */
    private int readNumber;

    /**
     * 评论次数：包含别人评论以及自己回复或者评论总数
     */
    private int discussNumber;

    /**
     * 点赞次数
     */
    private int likeNumber;

    /**
     * 文章详细表的ID
     */
    private String content;

    /**
     * 次序(置顶功能),大的在前面，默认值是当前日期
     */
    private int topNum;

    /**
     * 文章的状态:正在编辑，已完成，已发布，已删除
     */
    private char articleStatus;


}