package cn.wangtao.blogs.controller;

import cn.wangtao.ResponseEntity.BlogResponse;
import cn.wangtao.blogs.controller.blog_controller.CommentControllerApi;
import cn.wangtao.exception.ConstantException;
import cn.wangtao.pojo.blog.Comment;
import cn.wangtao.pojo.user.SysUser;
import cn.wangtao.blogs.service.CommentService;
import cn.wangtao.utils.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

/**
 * @ClassName CommentController
 * @Auth 桃子
 * @Date 2019-6-12 17:43
 * @Version 1.0
 * @Description 增加 删除 查看(根据主键查询，根据文章id查询，根据创建人（评论人）查询)  点赞（修改中间表与记录数），
 **/
@Controller
@RequestMapping("comment")
@Slf4j
public class CommentController implements CommentControllerApi {

    @Autowired
    private CommentService commentService;

    //增加评论 登录后即拥有权限
    @PostMapping("insert")
    @ResponseBody
    public BlogResponse insert(Comment comment, HttpServletRequest request) {
        log.info("添加评论接受到参数Comment：[{}]",comment);
        BlogResponse blogResponse = new BlogResponse();
        HashMap<String, Object> map = new HashMap<>();
        //参数校验
        if(StringUtils.isEmpty(comment.getComContent())||StringUtils.isEmpty(comment.getBlogSeq())){
            blogResponse.setReturnCode(ConstantException.ERRORCODE);
            blogResponse.setReturnMessage("增加评论失败，请求参数不合法");
            log.error("增加评论失败，评论名不能为空");
            return blogResponse;
        }
        //执行增加
        try{
            SysUser currentUser = (SysUser)request.getSession().getAttribute("user");
            int num = this.commentService.insert(comment,currentUser);
            map.put("comment",comment);
            blogResponse.setReturnCode(ConstantException.SUCCESSCODE);
            blogResponse.setReturnMessage("添加评论成功");
            blogResponse.setData(map);
            log.info("添加评论成功[{}]记录数[{}]",comment,num);
        }catch (Exception e){
            blogResponse.setReturnCode(ConstantException.ERRORCODE);
            blogResponse.setReturnMessage("添加评论失败");
            log.error("添加评论失败[{}],代码出现异常",comment,e);
        }
        return blogResponse;
    }

    //删除
    @GetMapping("deleteById")
    @ResponseBody
    public BlogResponse deleteById(@RequestParam("id") Long id) {
        log.info("根据id删除评论对象接受到参数id:[{}]",id);
        BlogResponse blogResponse = new BlogResponse();
        //参数校验
        if(StringUtils.isEmpty(id)){
            blogResponse.setReturnCode(ConstantException.ERRORCODE);
            blogResponse.setReturnMessage("删除评论失败，请求参数不合法");
            log.error("删除评论失败，用户ID与用户名不能为空");
            return blogResponse;
        }
        try {
            int num=this.commentService.deleteById(id);
            blogResponse.setReturnCode(ConstantException.SUCCESSCODE);
            blogResponse.setReturnMessage("删除评论对象成功");
            log.info("根据id[{}]删除评论对象成功,记录数[{}]",id,num);
        }catch (Exception e){
            blogResponse.setReturnCode(ConstantException.ERRORCODE);
            blogResponse.setReturnMessage("删除评论对象失败");
            log.error("根据ID[{}]删除评论对象失败，代码出现异常",id,e);
        }
        return blogResponse;
    }

    //根据主键查询
    @GetMapping("selectById")
    @ResponseBody
    public BlogResponse selectById(@RequestParam("id") Long id) {
        log.info("根据id查询评论接受到参数id：[{}]",id);
        BlogResponse blogResponse = new BlogResponse();
        HashMap<String, Object> map = new HashMap<>();
        //参数校验
        if(StringUtils.isEmpty(id)){
            blogResponse.setReturnCode(ConstantException.ERRORCODE);
            blogResponse.setReturnMessage("查询评论失败，请求参数不合法");
            log.error("查询评论失败，评论ID不能为空");
            return blogResponse;
        }
        //执行查询
        try {
            Comment comment =this.commentService.selectById(id);
            map.put("comment",comment);
            blogResponse.setReturnCode(ConstantException.SUCCESSCODE);
            blogResponse.setReturnMessage("查询评论对象成功");
            blogResponse.setData(map);
            log.info("根据评论ID[{}]查询评论对象成功[{}]",id,comment);
        }catch (Exception e){
            blogResponse.setReturnCode(ConstantException.ERRORCODE);
            blogResponse.setReturnMessage("查询评论对象失败");
            log.error("根据评论ID[{}]查询评论对象失败",id,e);
        }
        return blogResponse;
    }


    //根据文章查询评论信息
    @GetMapping("selectByBlog")
    @ResponseBody
    public BlogResponse selectByBlog(@RequestParam("id") Long blogSeq) {
        log.info("根据博客ID查询评论接受到参数id：[{}]",blogSeq);
        BlogResponse blogResponse = new BlogResponse();
        HashMap<String, Object> map = new HashMap<>();
        //参数校验
        if(StringUtils.isEmpty(blogSeq)){
            blogResponse.setReturnCode(ConstantException.ERRORCODE);
            blogResponse.setReturnMessage("查询评论失败，请求参数不合法");
            log.error("查询评论失败，博客ID不能为空");
            return blogResponse;
        }
        //执行查询
        try {
            List<Comment> commentList =this.commentService.selectByBlog(blogSeq);
            map.put("commentList",commentList);
            blogResponse.setReturnCode(ConstantException.SUCCESSCODE);
            blogResponse.setReturnMessage("查询评论对象成功");
            blogResponse.setData(map);
            log.info("根据博客ID[{}]查询评论对象成功[{}]",blogSeq,commentList);
        }catch (Exception e){
            blogResponse.setReturnCode(ConstantException.ERRORCODE);
            blogResponse.setReturnMessage("查询评论对象失败");
            log.error("根据博客ID[{}]查询评论对象失败",blogSeq,e);
        }
        return blogResponse;
    }


    //根据评论人进行查询
    @GetMapping("selectByComUser")
    @ResponseBody
    public BlogResponse selectByComUser(@RequestParam("userName") String userName) {
        log.info("根据评论人查询评论接受到参数userName：[{}]",userName);
        BlogResponse blogResponse = new BlogResponse();
        HashMap<String, Object> map = new HashMap<>();
        //参数校验
        if(StringUtils.isEmpty(userName)){
            blogResponse.setReturnCode(ConstantException.ERRORCODE);
            blogResponse.setReturnMessage("查询评论失败，请求参数不合法");
            log.error("查询评论失败，评论人不能为空");
            return blogResponse;
        }
        //执行查询
        try {
            List<Comment> commentList =this.commentService.selectByComUser(userName);
            map.put("commentList",commentList);
            blogResponse.setReturnCode(ConstantException.SUCCESSCODE);
            blogResponse.setReturnMessage("查询评论对象成功");
            blogResponse.setData(map);
            log.info("根据评论人[{}]查询评论对象成功[{}]",userName,commentList);
        }catch (Exception e){
            blogResponse.setReturnCode(ConstantException.ERRORCODE);
            blogResponse.setReturnMessage("查询评论对象失败");
            log.error("根据评论人[{}]查询评论对象失败",userName,e);
        }
        return blogResponse;
    }

    //点赞通道
    @PostMapping("updateLike")
    @ResponseBody
    public BlogResponse updateLike(@RequestParam("comSeq")Long comSeq,@RequestParam("num")int num, HttpServletRequest request) {
        log.info("修改评论的点赞信息接受到参数comSeq:[{}],num:[{}]",comSeq,num);
        BlogResponse blogResponse = new BlogResponse();
        //参数校验
        if(num!= Constants.ADDNUM&&num!=Constants.DESCNUM||StringUtils.isEmpty(comSeq)){
            blogResponse.setReturnCode(ConstantException.ERRORCODE);
            blogResponse.setReturnMessage("评论点赞失败，请求参数不合法");
            log.error("评论点赞失败,请求参数不合法");
            return blogResponse;
        }
        //执行更新
        try {
            SysUser currentUser = (SysUser)request.getSession().getAttribute("user");
            int result = this.commentService.updateLike(comSeq,num,currentUser);
            log.info("修改评论的点赞信息对象成功[{}]记录数[{}]",comSeq,result);
            blogResponse.setReturnCode(ConstantException.SUCCESSCODE);
            blogResponse.setReturnMessage("评论点赞成功");
        }catch (Exception e){
            blogResponse.setReturnCode(ConstantException.ERRORCODE);
            blogResponse.setReturnMessage("评论点赞失败");
            log.error("修改评论点赞对象[{}]失败，代码出现异常",comSeq,e);
        }
        return blogResponse;
    }

}
