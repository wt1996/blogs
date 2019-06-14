package cn.wangtao.serviceImpl;

import cn.wangtao.baseEntity.BaseMapperEntity;
import cn.wangtao.exception.ServiceException;
import cn.wangtao.mapper.CommentMapper;
import cn.wangtao.pojo.blog.Blog;
import cn.wangtao.pojo.blog.Comment;
import cn.wangtao.pojo.blog.CommentUserLike;
import cn.wangtao.service.BlogService;
import cn.wangtao.service.CommentService;
import cn.wangtao.service.CommentUserLikeService;
import cn.wangtao.utils.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;


/**
 * @ClassName CommentServiceImpl
 * @Auth 桃子
 * @Date 2019-6-12 9:27
 * @Version 1.0
 * @Description
 **/
@Transactional
@Slf4j
@Service("commentService")
public class CommentServiceImpl extends CommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private CommentUserLikeService commentUserLikeService;

    @Autowired
    private BlogService blogService;

    @Override
    public BaseMapperEntity<Comment, Long> getMapper() throws Exception {
        return this.commentMapper;
    }

    @Override
    public int insert(Comment comment) throws Exception {
        comment.setCreateDate(new Date());
        return this.commentMapper.insert(comment);
    }

    @Override
    public int update(Comment comment) throws Exception {
        log.warn("评论信息没有修改操作");
        return 0;
    }

    @Override
    public List<Comment> selectByBlog(Long blogSeq) throws Exception {
        return this.commentMapper.selectByBlog(blogSeq);
    }

    @Override
    public List<Comment> selectByComUser(String userName) throws Exception {
        return this.commentMapper.selectByComUser(userName);
    }

    @Override
    public int updateLike(Long comSeq, int num,Long sysUserSeq) throws Exception {
        Comment comment = this.selectById(comSeq);
        if(comment==null){
            throw new ServiceException("评论已删除","");
        }
        //修改点赞记录数
        comment.setLikeNum(comment.getLikeNum()+num);
        this.commentMapper.updateByPrimaryKeySelective(comment);
        //添加评论点赞中间表
        if(Constants.ADDNUM==num){
            CommentUserLike commentUser=this.commentUserLikeService.selectByTwo(comSeq,sysUserSeq);
            log.info("查询点赞中间表记录comSeq[{}]sysUserSeq[{}]commentUser[{}]",comSeq,sysUserSeq,commentUser);
            if(commentUser==null){
                log.info("添加点赞中间表记录comSeq[{}]sysUserSeq[{}]",comSeq,sysUserSeq);
                commentUser=new CommentUserLike();
                commentUser.setComSeq(comSeq);
                commentUser.setUserSeq(sysUserSeq);
                return  this.commentUserLikeService.insert(commentUser);
            }
            return 0;
        }else{
            //删除中间记录
            log.info("删除点赞中间表记录comSeq[{}]sysUserSeq[{}]",comSeq,sysUserSeq);
            return this.commentUserLikeService.deleteByTwo(comSeq,sysUserSeq);
        }
    }

    @Override
    public int insert(Comment comment, String userName) throws Exception {
        Blog blog = this.blogService.selectById(comment.getBlogSeq());
        if(blog==null){
            throw new ServiceException("博客不存在","");
        }
        comment.setCreateByName(userName);
        comment.setCreateDate(new Date());
        //如果上一级没有，会自动为0l
        this.commentMapper.insert(comment);
        log.info("添加[{}]评论成功，开始修改博客的评论次数",userName);
        //修改文章的评论次数
        blog.setComNum(blog.getComNum()+1);
        return this.blogService.update(blog);
    }
}
