package cn.wangtao.controller;

import cn.wangtao.ResponseEntity.BlogResponse;
import cn.wangtao.controller.blog_controller.BlogControllerApi;
import cn.wangtao.exception.ConstantException;
import cn.wangtao.pojo.blog.Blog;
import cn.wangtao.pojo.user.SysUser;
import cn.wangtao.service.BlogService;
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
 * @ClassName ArticleController
 * @Auth 桃子
 * @Date 2019-6-12 17:41
 * @Version 1.0
 * @Description 增加，查询，修改（伴随着博客详细信息的操作) ,
 *              查询：所有人都可以
 *              评论数：修改在添加评论时修改，登录之后所有人都有权限
 *              阅读数：博客所有人都可以查询，在用户登录之后并是博主的时候，不修改阅读量，别的都修改
 *              点赞数：中间表维护
 *
 **/
@Controller
@RequestMapping("blog")
@Slf4j
public class BlogController implements BlogControllerApi {

    @Autowired
    private BlogService blogService;

    //查询主博客以及详细信息（涉及博客阅读量的修改根据当前用户是否是博主添加阅读量）所有的都可以
    @GetMapping("selectById")
    @ResponseBody
    public BlogResponse selectById(@RequestParam("id") Long id,HttpServletRequest request) {
        log.info("根据id查询主博客接受到参数id：[{}]",id);
        BlogResponse blogResponse = new BlogResponse();
        HashMap<String, Object> map = new HashMap<>();
        //参数校验
        if(StringUtils.isEmpty(id)){
            blogResponse.setReturnCode(ConstantException.ERRORCODE);
            blogResponse.setReturnMessage("查询失败，请求参数不合法");
            log.error("查询失败，主博客ID不能为空");
            return blogResponse;
        }
        //执行查询
        try {
            SysUser currentUser = (SysUser)request.getSession().getAttribute("user");
            Blog blog =this.blogService.selectById(id,currentUser);
            map.put("blog",blog);
            blogResponse.setReturnCode(ConstantException.SUCCESSCODE);
            blogResponse.setReturnMessage("查询博客对象成功");
            blogResponse.setData(map);
            log.info("根据主博客ID[{}]查询评论对象成功[{}]",id,blog);
        }catch (Exception e){
            blogResponse.setReturnCode(ConstantException.ERRORCODE);
            blogResponse.setReturnMessage("查询博客对象失败");
            log.error("根据主博客ID[{}]查询评论对象失败",id,e);
        }
        return blogResponse;
    }

    //查询所有的主博客
    @GetMapping("selectAll")
    @ResponseBody
    public BlogResponse selectAll() {
        BlogResponse blogResponse = new BlogResponse();
        HashMap<String, Object> map = new HashMap<>();
        try{
            List<Blog> blogList= this.blogService.selectAll();
            blogResponse.setReturnCode(ConstantException.SUCCESSCODE);
            blogResponse.setReturnMessage("查询所有主博客成功");
            map.put("blogList",blogList);
            blogResponse.setData(map);
            log.info("查询所有主博客成功:[{}]",blogList);
        }catch (Exception e){
            blogResponse.setReturnCode(ConstantException.ERRORCODE);
            blogResponse.setReturnMessage("查询所有主博客失败");
            log.error("查询所有主博客失败，代码出现异常",e);
        }
        return blogResponse;
    }

    //添加博客（涉及详细信息记录的添加）
    @PostMapping("insert")
    @ResponseBody
    public BlogResponse insert(Blog blog, HttpServletRequest request) {
        log.info("添加博客信息接受到参数：[{}]",blog);
        BlogResponse blogResponse = new BlogResponse();
        HashMap<String, Object> map = new HashMap<>();
        //参数校验
        if(StringUtils.isEmpty(blog.getTitle())){
            blogResponse.setReturnCode(ConstantException.ERRORCODE);
            blogResponse.setReturnMessage("增加失败，请求参数不合法");
            log.error("增加失败，博客内容为空，不能为空");
            return blogResponse;
        }
        //执行增加
        try{
            SysUser currentUser = (SysUser)request.getSession().getAttribute("user");
            blog.setCreateByName(currentUser.getUserName());//从Session中取
            int num = this.blogService.insert(blog);
            map.put("blog",blog);
            blogResponse.setReturnCode(ConstantException.SUCCESSCODE);
            blogResponse.setReturnMessage("添加博客成功");
            blogResponse.setData(map);
            log.info("添加博客成功[{}]记录数[{}]",blog,num);
        }catch (Exception e){
            blogResponse.setReturnCode(ConstantException.ERRORCODE);
            blogResponse.setReturnMessage("添加系统标签失败");
            log.error("添加博客失败[{}],代码出现异常",blog,e);
        }
        return blogResponse;
    }

    //修改博客所有信息（涉及详细信息记录的修改，只有博主可以修改前端进行判断控制）
    @PostMapping("updateById")
    @ResponseBody
    public BlogResponse updateById(Blog blog, HttpServletRequest request) {
        log.info("修改博客所有信息接受到参数:[{}]",blog);
        BlogResponse blogResponse = new BlogResponse();
        //参数校验
        if(StringUtils.isEmpty(blog.getBlogSeq())){
            blogResponse.setReturnCode(ConstantException.ERRORCODE);
            blogResponse.setReturnMessage("修改博客失败，请求参数不合法");
            log.error("修改博客失败， 博客主键id不能为空");
            return blogResponse;
        }
        //执行修改
        try {
            SysUser currentUser = (SysUser)request.getSession().getAttribute("user");
            blog.setUpdateBy(currentUser.getUserName());
            int num = this.blogService.update(blog);
            log.info("修改博客对象成功[{}]记录数[{}]",blog,num);
            blogResponse.setReturnCode(ConstantException.SUCCESSCODE);
            blogResponse.setReturnMessage("修改博客对象成功");
        }catch (Exception e){
            blogResponse.setReturnCode(ConstantException.ERRORCODE);
            blogResponse.setReturnMessage("修改博客对象失败");
            log.error("修改博客对象[{}]失败，代码出现异常",blog,e);
        }
        return blogResponse;
    }

    //点赞通道(修改点赞数，每次只能涉及一次,所有人都可以)
    @PostMapping("updateLike")
    @ResponseBody
    public BlogResponse updateLike(@RequestParam("blogSeq")Long blogSeq,@RequestParam("num")int num, HttpServletRequest request) {
        log.info("修改评论的点赞信息接受到参数comSeq:[{}],num:[{}]",blogSeq,num);
        BlogResponse blogResponse = new BlogResponse();
        //参数校验
        if(Constants.ADDNUM!=num &&Constants.DESCNUM!=num||StringUtils.isEmpty(blogSeq)){
            blogResponse.setReturnCode(ConstantException.ERRORCODE);
            blogResponse.setReturnMessage("博客点赞失败，请求参数不合法");
            log.error("博客点赞失败,请求参数不合法");
            return blogResponse;
        }
        //执行更新
        try {
            SysUser currentUser = (SysUser)request.getSession().getAttribute("user");
            int result = this.blogService.updateLike(blogSeq,num,currentUser.getSysUserSeq());
            log.info("修改博客点赞信息对象成功[{}]记录数[{}]",blogSeq,result);
            blogResponse.setReturnCode(ConstantException.SUCCESSCODE);
            blogResponse.setReturnMessage("博客点赞成功");
        }catch (Exception e){
            blogResponse.setReturnCode(ConstantException.ERRORCODE);
            blogResponse.setReturnMessage("博客点赞失败");
            log.error("修改博客点赞对象[{}]失败，代码出现异常",blogSeq,e);
        }
        return blogResponse;
    }




    //删除主博客（数据库级联删除详细信息记录）
    @GetMapping("deleteById")
    @ResponseBody
    public BlogResponse deleteById(@RequestParam("blogSeq") Long blogSeq) {
        log.info("根据id删除博客对象接受到参数id:[{}]",blogSeq);
        BlogResponse blogResponse = new BlogResponse();
        //参数校验
        if(StringUtils.isEmpty(blogSeq)){
            blogResponse.setReturnCode(ConstantException.ERRORCODE);
            blogResponse.setReturnMessage("删除博客失败，请求参数不合法");
            log.error("删除博客失败，用户ID与用户名不能为空");
            return blogResponse;
        }
        try {
            int num=this.blogService.deleteById(blogSeq);
            blogResponse.setReturnCode(ConstantException.SUCCESSCODE);
            blogResponse.setReturnMessage("根据id删除博客对象成功");
            log.info("根据id[{}]删除博客对象成功,记录数[{}]",blogSeq,num);
        }catch (Exception e){
            blogResponse.setReturnCode(ConstantException.ERRORCODE);
            blogResponse.setReturnMessage("根据id删除博客对象失败");
            log.error("根据id[{}]删除博客对象失败，代码出现异常",blogSeq,e);
        }
        return blogResponse;
    }



}
