package cn.wangtao.blogs.serviceImpl;

import cn.wangtao.baseEntity.BaseMapperEntity;
import cn.wangtao.exception.ServiceException;
import cn.wangtao.mapper.BlogMapper;
import cn.wangtao.pojo.blog.*;
import cn.wangtao.pojo.cms.Message;
import cn.wangtao.pojo.user.SysUser;
import cn.wangtao.blogs.service.BlogDetailService;
import cn.wangtao.blogs.service.BlogService;
import cn.wangtao.blogs.service.BlogUserLikeService;
import cn.wangtao.blogs.service.MessageService;
import cn.wangtao.utils.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;


/**
 * @ClassName BlogServiceImpl
 * @Auth 桃子
 * @Date 2019-6-12 9:27
 * @Version 1.0
 * @Description
 **/
@Transactional
@Slf4j
@Service("blogService")
public class BlogServiceImpl extends BlogService {

    @Autowired
    private BlogMapper blogMapper;

    @Autowired
    private BlogDetailService blogDetailService;

    @Autowired
    private BlogUserLikeService blogUserLikeService;

    @Autowired
    private MessageService messageService;

    @Override
    public BaseMapperEntity<Blog, Long> getMapper() throws Exception {
        return this.blogMapper;
    }

    @Override
    public int insert(Blog blog) throws Exception {
        blog.setCreateDate(new Date());
        this.blogMapper.insert(blog);
        //报文博客的详细信息
        BlogDetail blogDetail = blog.getBlogDetail();
        blogDetail.setDetailSeq(blog.getBlogSeq());
        return this.blogDetailService.insert(blogDetail);
    }

    @Override
    public int update(Blog blog) throws Exception {
        blog.setUpdateDate(new Date());
        //修改文章详细内容
        BlogDetail blogDetail = blog.getBlogDetail();
        if(blogDetail!=null){
            this.blogMapper.updateByPrimaryKeySelective(blog);
            blogDetail.setDetailSeq(blog.getBlogSeq());
            return this.blogDetailService.update(blogDetail);
        }
        return this.blogMapper.updateByPrimaryKeySelective(blog);
    }


    @Override
    public Blog selectById(Long blogSeq, SysUser user) throws Exception {
        //先进行查询主博客
        Blog blog = this.blogMapper.selectByPrimaryKey(blogSeq);
        if(blog==null){
            throw new ServiceException("博客不存在","");
        }
        //查询详细信息
        BlogDetail blogDetail = this.blogDetailService.selectById(blogSeq);
        blog.setBlogDetail(blogDetail);

        if(!(user!=null&&user.getUserName().equals(blog.getCreateByName()))){
           //修改博客的阅读量
            blog.setReadNum(blog.getReadNum()+1);
            this.blogMapper.updateByPrimaryKeySelective(blog);
       }
        return blog;
    }

    @Override
    public int updateLike(Long blogSeq, int num, SysUser sysUser) throws Exception {
        //查询博客信息
        Blog blog = this.blogMapper.selectByPrimaryKey(blogSeq);
        if(blog==null){
            throw new ServiceException("博客不存在","");
        }
        //修改数量
        blog.setLikeNum(blog.getLikeNum()+num);
        this.blogMapper.updateByPrimaryKeySelective(blog);
        //添加到通知信息
        try{
            log.info("添加点赞消息到数据库开始");
            Message message=new Message();
            message.setCreateByName(sysUser.getUserName());//谁点赞的
            message.setCurrentUserName(blog.getCreateByName());//消息拥有者
            message.setMessageContent(sysUser.getUserName()+"点赞了您的博客");
            message.setMessageStatus(Constants.MESSAGESTATUS_NO);//表示未读
            message.setMessageType(Constants.MESSAGETYPE_LIKE);//点赞
            message.setOriginId(blog.getBlogSeq());
            message.setOriginTitle(blog.getTitle());
            message.setHeadImage(sysUser.getUserHeadImage());
            this.messageService.insert(message);
        }catch (Exception e){
            log.error("添加点赞消息到数据库失败，代码发生异常");
        }

        //添加中间记录
        if(Constants.ADDNUM==num){
            BlogUserLike blogUserLike=this.blogUserLikeService.selectByTwo(blogSeq,sysUser.getSysUserSeq());
            log.info("查询点赞中间表记录blogSeq[{}]sysUserSeq[{}]commentUser[{}]",blogSeq,sysUser.getSysUserSeq(),blogUserLike);
            if(blogUserLike==null){
                log.info("添加点赞中间表记录blogSeq[{}]sysUserSeq[{}]",blogSeq,sysUser.getSysUserSeq());
                blogUserLike=new BlogUserLike();
                blogUserLike.setBlogSeq(blogSeq);
                blogUserLike.setUserSeq(sysUser.getSysUserSeq());
                return  this.blogUserLikeService.insert(blogUserLike);
            }
            return 0;
        }else{
            //删除中间记录
            log.info("删除点赞中间表记录blogSeq[{}]sysUserSeq[{}]",blogSeq,sysUser.getSysUserSeq());
            return this.blogUserLikeService.deleteByTwo(blogSeq,sysUser.getSysUserSeq());
        }
    }

    @Override
    public List<Blog> selectAllOBTopNumDesc() throws Exception {
        return this.blogMapper.selectAllOBTopNumDesc();
    }

}
