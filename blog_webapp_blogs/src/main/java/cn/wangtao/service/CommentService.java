package cn.wangtao.service;


import cn.wangtao.baseEntity.BaseServiceEntity;
import cn.wangtao.pojo.blog.Comment;

import java.util.List;

/**
 * @ClassName CommentService
 * @Auth 桃子
 * @Date 2019-6-12 9:21
 * @Version 1.0
 **/
public abstract class CommentService extends BaseServiceEntity<Comment,Long> {
    public abstract List<Comment> selectByBlog(Long blogSeq) throws Exception;

    public abstract List<Comment> selectByComUser(String userName) throws Exception;

    public abstract int updateLike(Long comSeq, int num, Long sysUserSeq) throws Exception;

    public abstract int insert(Comment comment, String userName) throws Exception;
}
