package cn.wangtao.pojo.blog;

import cn.wangtao.baseEntity.BasePojoEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @ClassName Blog
 * @Auth 桃子
 * @Date 2019-5-14 10:49
 * @Version 1.0
 * @Description 文章信息
 **/
@Data
@Table(name = "blogs")
public class Blog extends BasePojoEntity implements Serializable {

    //序列号 自增
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="blog_seq")
    //@ApiModelProperty(hidden = true)
    private Long blogSeq;

    // 文章标题
    private String title;

    //文章的类别ID
    private Long categorySeq;

    //文章的ID
    private Long menuSeq;

    //文章详细信息 一对一,不保存详细信息
    private BlogDetail blogDetail;

    //阅读次数
    private Integer readNum;

    //评论次数：包含别人评论以及自己回复或者评论总数
    private Integer comNum;

    //点赞次数
    private Integer likeNum;

    //次序(置顶功能),大的在前面，默认值是当前日期
    private Integer topNum;

    //文章的状态:0 正在编辑（前端进行控制，后端无此状态），1 已完成，2 草稿箱
    private Character blogStatus;

    //文章的发布状态 0:公开 1：私密
    private Character postStatus;

    //是否允许评论 0允许（默认），1不允许
    private Integer allowCom ;
}