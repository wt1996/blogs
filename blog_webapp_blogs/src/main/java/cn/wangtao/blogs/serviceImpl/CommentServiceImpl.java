package cn.wangtao.blogs.serviceImpl;

import cn.wangtao.baseEntity.BaseMapperEntity;
import cn.wangtao.exception.ServiceException;
import cn.wangtao.mapper.CommentMapper;
import cn.wangtao.pojo.blog.Blog;
import cn.wangtao.pojo.blog.Comment;
import cn.wangtao.pojo.blog.CommentUserLike;
import cn.wangtao.pojo.cms.Message;
import cn.wangtao.pojo.user.SysUser;
import cn.wangtao.blogs.service.BlogService;
import cn.wangtao.blogs.service.CommentService;
import cn.wangtao.blogs.service.CommentUserLikeService;
import cn.wangtao.blogs.service.MessageService;
import cn.wangtao.utils.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

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
    private MessageService messageService;

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
    public int updateLike(Long comSeq, int num, SysUser sysUser) throws Exception {
        Comment comment = this.selectById(comSeq);
        if(comment==null){
            throw new ServiceException("评论已删除","");
        }
        //修改点赞记录数
        comment.setLikeNum(comment.getLikeNum()+num);
        this.commentMapper.updateByPrimaryKeySelective(comment);

        //添加到消息表
        try{
            log.info("添加评论点赞消息到数据库开始");
            Message message=new Message();
            message.setCreateByName(sysUser.getUserName());//谁点赞的
            message.setCurrentUserName(comment.getCreateByName());//消息拥有者
            message.setMessageContent(sysUser.getUserName()+"点赞了您的评论");
            message.setMessageStatus(Constants.MESSAGESTATUS_NO);//表示未读
            message.setMessageType(Constants.MESSAGETYPE_LIKE);//点赞
            message.setOriginId(comment.getComSeq());
            message.setOriginTitle(comment.getComContent().substring(0,20)); //评论内容的一部分
            message.setHeadImage(sysUser.getUserHeadImage());
            this.messageService.insert(message);
        }catch (Exception e){
            log.error("添加评论点赞消息到数据库失败，代码发生异常");
        }

        //添加评论点赞中间表
        if(Constants.ADDNUM==num){
            CommentUserLike commentUser=this.commentUserLikeService.selectByTwo(comSeq,sysUser.getSysUserSeq());
            log.info("查询点赞中间表记录comSeq[{}]sysUserSeq[{}]commentUser[{}]",comSeq,sysUser.getSysUserSeq(),commentUser);
            if(commentUser==null){
                log.info("添加点赞中间表记录comSeq[{}]sysUserSeq[{}]",comSeq,sysUser.getSysUserSeq());
                commentUser=new CommentUserLike();
                commentUser.setComSeq(comSeq);
                commentUser.setUserSeq(sysUser.getSysUserSeq());
                return  this.commentUserLikeService.insert(commentUser);
            }
            return 0;
        }else{
            //删除中间记录
            log.info("删除点赞中间表记录comSeq[{}]sysUserSeq[{}]",comSeq,sysUser.getSysUserSeq());
            return this.commentUserLikeService.deleteByTwo(comSeq,sysUser.getSysUserSeq());
        }
    }

    @Override
    public int insert(Comment comment, SysUser sysUser) throws Exception {
        Blog blog = this.blogService.selectById(comment.getBlogSeq());
        if(blog==null){
            throw new ServiceException("博客不存在","");
        }
        comment.setCreateByName(sysUser.getUserName());//
        comment.setCreateDate(new Date());
        //如果上一级没有，会自动为0l
        if(StringUtils.isEmpty(comment.getComParent())){
           comment.setComParent(Constants.PARENTCATEGORY);
        }else{
            if(comment.getCreateByName().equals(blog.getCreateByName())){
                comment.setIsAdmin(Constants.ISADMIN_YES);
            }else{
                comment.setIsAdmin(Constants.ISADMIN_NO);
            }
        }


        this.commentMapper.insert(comment);
        log.info("添加[{}]评论成功，开始修改博客的评论次数",sysUser.getUserName());

        //添加消息
        try{
            log.info("添加评论消息到数据库开始");
            Message message=new Message();
            message.setCreateByName(sysUser.getUserName());//谁点赞的
            message.setHeadImage(sysUser.getUserHeadImage());
            message.setMessageType(Constants.MESSAGETYPE_COM);//评论
            message.setMessageStatus(Constants.MESSAGESTATUS_NO);//表示未读

            //表示给博客进行评论
            if(comment.getComParent()==Constants.PARENTCATEGORY){
                message.setCurrentUserName(blog.getCreateByName());//消息拥有者
                message.setMessageContent(sysUser.getUserName()+"评论了您的博客");
                message.setOriginId(blog.getBlogSeq());
                message.setOriginTitle(blog.getTitle());
            }
            //表示给评论进行评论
            if(comment.getComParent()!=Constants.PARENTCATEGORY){
                if(Constants.ISADMIN_YES==comment.getIsAdmin()){
                    //博主回复
                    message.setMessageContent("博主"+sysUser.getUserName()+"评论了您的评论");
                }else{
                    //普通评论被评论
                    message.setMessageContent(sysUser.getUserName()+"评论了您的评论");
                }
                message.setCurrentUserName(comment.getComPCreateName());//消息拥有者
                message.setOriginId(comment.getComSeq());
                message.setOriginTitle(comment.getComContent().substring(0,20));
            }

            this.messageService.insert(message);
        }catch (Exception e){
            log.error("添加评论消息到数据库失败，代码发生异常",e);
        }


        //修改文章的评论次数
        blog.setComNum(blog.getComNum()+1);
        return this.blogService.update(blog);
    }

}
