package cn.wangtao.service;

import cn.wangtao.baseEntity.BaseServiceEntity;
import cn.wangtao.pojo.blog.CommentUserLike;

/**
 * @ClassName CommentUserLikeService
 * @Auth 桃子
 * @Date 2019-6-13 16:37
 * @Version 1.0
 * @Description
 **/
public abstract class CommentUserLikeService extends BaseServiceEntity<CommentUserLike,Long> {
    public abstract int deleteByTwo(Long comSeq, Long sysUserSeq) throws  Exception;

    public abstract CommentUserLike selectByTwo(Long comSeq, Long sysUserSeq) throws  Exception;
}
