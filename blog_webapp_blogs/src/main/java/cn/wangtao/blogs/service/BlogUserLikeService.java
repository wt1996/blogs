package cn.wangtao.blogs.service;

import cn.wangtao.baseEntity.BaseServiceEntity;
import cn.wangtao.pojo.blog.BlogUserLike;

/**
 * @ClassName BlogUserLikeService
 * @Auth 桃子
 * @Date 2019-6-13 16:37
 * @Version 1.0
 * @Description
 **/
public abstract class BlogUserLikeService extends BaseServiceEntity<BlogUserLike,Long>{

    public abstract BlogUserLike selectByTwo(Long blogSeq, Long sysUserSeq) throws Exception;

    public abstract int deleteByTwo(Long blogSeq, Long sysUserSeq) throws  Exception;
}
