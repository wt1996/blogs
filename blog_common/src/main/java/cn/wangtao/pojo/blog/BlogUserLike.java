package cn.wangtao.pojo.blog;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * @ClassName BlogUserLike
 * @Auth 桃子
 * @Date 2019-6-13 11:49
 * @Version 1.0
 * @Description  用户与文章的中间表，多对多的关系 点赞的中间表
 **/
@Data
@Table(name="blog_user_like")
public class BlogUserLike {

    //主键
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="blog_user_seq")
    private Long blogUserSeq;

    //主键的ID
    @Id
    @Column(name="blog_seq")
    private Long blogSeq;

    //用户的ID
    @Id
    @Column(name="user_seq")
    private Long userSeq;

    //创建时间
    private Date createDate;



}
