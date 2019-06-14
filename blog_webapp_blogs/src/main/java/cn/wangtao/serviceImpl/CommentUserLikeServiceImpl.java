package cn.wangtao.serviceImpl;

import cn.wangtao.baseEntity.BaseMapperEntity;
import cn.wangtao.mapper.CommentUserLikeMapper;
import cn.wangtao.pojo.blog.CommentUserLike;
import cn.wangtao.service.CommentUserLikeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @ClassName CommentUserLikeServiceImpl
 * @Auth 桃子
 * @Date 2019-6-13 16:39
 * @Version 1.0
 * @Description
 **/
@Slf4j
@Transactional
@Service("commentUserService")
public class CommentUserLikeServiceImpl extends CommentUserLikeService {

    @Autowired
    private CommentUserLikeMapper commentUserLikeMapper;

    @Override
    public BaseMapperEntity<CommentUserLike, Long> getMapper() throws Exception {
        return this.commentUserLikeMapper;
    }

    @Override
    public int insert(CommentUserLike commentUser) throws Exception {
        commentUser.setCreateDate(new Date());
        return this.commentUserLikeMapper.insert(commentUser);
    }

    @Override
    public int update(CommentUserLike commentUser) throws Exception {
        log.warn("评论点赞系统没有修改操作");
        return 0;
    }

    @Override
    public int deleteByTwo(Long comSeq, Long sysUserSeq) throws Exception {
        return this.commentUserLikeMapper.deleteByTwo(comSeq,sysUserSeq);
    }

    @Override
    public CommentUserLike selectByTwo(Long comSeq, Long sysUserSeq) throws Exception {
        return this.commentUserLikeMapper.selectByTwo(comSeq,sysUserSeq);
    }
}
