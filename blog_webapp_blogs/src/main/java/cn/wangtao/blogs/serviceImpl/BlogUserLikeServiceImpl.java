package cn.wangtao.blogs.serviceImpl;

import cn.wangtao.baseEntity.BaseMapperEntity;
import cn.wangtao.mapper.BlogUserLikeMapper;
import cn.wangtao.pojo.blog.BlogUserLike;
import cn.wangtao.blogs.service.BlogUserLikeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @ClassName BlogUserLikeServiceImpl
 * @Auth 桃子
 * @Date 2019-6-13 16:38
 * @Version 1.0
 * @Description
 **/
@Slf4j
@Transactional
@Service("blogUserService")
public class BlogUserLikeServiceImpl extends BlogUserLikeService {

    @Autowired
    private BlogUserLikeMapper blogUserLikeMapper;

    @Override
    public BaseMapperEntity<BlogUserLike, Long> getMapper() throws Exception {
        return this.blogUserLikeMapper;
    }

    @Override
    public int insert(BlogUserLike blogUser) throws Exception {
        blogUser.setCreateDate(new Date());
        return this.blogUserLikeMapper.insert(blogUser);
    }

    @Override
    public int update(BlogUserLike blogUser) throws Exception {
        log.warn("博客点赞中间表没有修改操作");
        return 0;
    }

    @Override
    public BlogUserLike selectByTwo(Long blogSeq, Long sysUserSeq) throws Exception {
        return this.blogUserLikeMapper.selectByTwo(blogSeq,sysUserSeq);
    }

    @Override
    public int deleteByTwo(Long blogSeq, Long sysUserSeq) throws Exception {
        return this.blogUserLikeMapper.deleteByTwo(blogSeq,sysUserSeq);
    }
}
