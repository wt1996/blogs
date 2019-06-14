package cn.wangtao.service;


import cn.wangtao.baseEntity.BaseServiceEntity;
import cn.wangtao.pojo.blog.Blog;
import cn.wangtao.pojo.user.SysUser;

/**
 * @ClassName BlogService
 * @Auth 桃子
 * @Date 2019-5-15 11:48
 * @Version 1.0
 * @Description
 **/
public abstract class BlogService extends BaseServiceEntity<Blog,Long> {
    public abstract Blog selectById(Long blogSeq, SysUser user) throws Exception;

    public abstract int updateLike(Long blogSeq, int num, Long sysUserSeq) throws Exception;
}
