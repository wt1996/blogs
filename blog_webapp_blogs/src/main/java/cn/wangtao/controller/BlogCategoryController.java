package cn.wangtao.controller;

import cn.wangtao.ResponseEntity.BlogResponse;
import cn.wangtao.controller.blog_controller.BlogCategoryControllerApi;
import cn.wangtao.exception.ConstantException;
import cn.wangtao.pojo.blog.BlogCategory;
import cn.wangtao.pojo.user.SysUser;
import cn.wangtao.service.BlogCategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

/**
 * @ClassName blogCategoryController
 * @Auth 桃子
 * @Date 2019-6-12 17:37
 * @Version 1.0
 * @Description
 **/
@Controller
@RequestMapping("blogCategory")
@Slf4j
public class BlogCategoryController implements BlogCategoryControllerApi {

    @Autowired
    private BlogCategoryService blogCategoryService;

    @GetMapping("selectAllByUser")
    @ResponseBody
    public BlogResponse selectAll(@RequestParam( "userName")String userName) {

        log.info("根据用户名[{}]查询用户的个人菜单开始",userName);
        BlogResponse blogResponse = new BlogResponse();
        HashMap<String, Object> map = new HashMap<>();
        //参数校验
        if(StringUtils.isEmpty(userName)){
            blogResponse.setReturnCode(ConstantException.ERRORCODE);
            blogResponse.setReturnMessage("查询个人菜单失败，请求参数不合法");
            log.error("查询个人菜单失败，用户名不能为空");
            return blogResponse;
        }
        //执行查询
        try{
            List<BlogCategory> list= this.blogCategoryService.selectAll(userName);
            blogResponse.setReturnCode(ConstantException.SUCCESSCODE);
            blogResponse.setReturnMessage("查询用户所有个人菜单成功");
            map.put("categoryList",list);
            blogResponse.setData(map);
            log.info("查询用户所有个人菜单成功");
        }catch (Exception e){
            blogResponse.setReturnCode(ConstantException.ERRORCODE);
            blogResponse.setReturnMessage("查询用户所有个人菜单失败");
            log.error("查询用户所有个人菜单失败，代码出现异常",e);
        }
        return blogResponse;
    }

    
    @GetMapping("selectById")
    @ResponseBody
    public BlogResponse selectById(@RequestParam("id") Long id,@RequestParam("userName")String userName) {
        log.info("根据id查询用户个人菜单接受到参数id：[{}]userName:[{}]",id,userName);
        BlogResponse blogResponse = new BlogResponse();
        HashMap<String, Object> map = new HashMap<>();
        //参数校验
        if(StringUtils.isEmpty(userName)||StringUtils.isEmpty(id)){
            blogResponse.setReturnCode(ConstantException.ERRORCODE);
            blogResponse.setReturnMessage("查询个人菜单失败，请求参数不合法");
            log.error("查询个人菜单失败，用户ID与用户名不能为空");
            return blogResponse;
        }
        //执行查询
        try {
            BlogCategory blogCategory =this.blogCategoryService.selectById(id,userName);
            map.put("blogCategory",blogCategory);
            blogResponse.setReturnCode(ConstantException.SUCCESSCODE);
            blogResponse.setReturnMessage("查询用户个人菜单对象成功");
            blogResponse.setData(map);
            log.info("根据用户名[{}]与菜单id[{}]查询用户个人菜单对象成功[{}]",userName,id,blogCategory);
        }catch (Exception e){
            blogResponse.setReturnCode(ConstantException.ERRORCODE);
            blogResponse.setReturnMessage("查询用户个人菜单对象失败");
            log.error("根据用户名[{}]与菜单id[{}]查询用户个人菜单对象失败",userName,id,e);
        }
        return blogResponse;
    }


    
    @PostMapping("insert")
    @ResponseBody
    public BlogResponse insert(BlogCategory blogCategory, HttpServletRequest request) {
        log.info("添加个人菜单接受到参数：[{}]",blogCategory);
        BlogResponse blogResponse = new BlogResponse();
        HashMap<String, Object> map = new HashMap<>();
        //参数校验
        if(StringUtils.isEmpty(blogCategory.getCategoryName())){
            blogResponse.setReturnCode(ConstantException.ERRORCODE);
            blogResponse.setReturnMessage("添加个人菜单失败，请求参数不合法");
            log.error("添加个人菜单失败，个人菜单名不能为空");
            return blogResponse;
        }

        try{
            SysUser currentUser = (SysUser)request.getSession().getAttribute("user");
            blogCategory.setCreateByName(currentUser.getUserName());//从Session中取
            int num = this.blogCategoryService.insert(blogCategory);
            map.put("blogCategory",blogCategory);
            blogResponse.setReturnCode(ConstantException.SUCCESSCODE);
            blogResponse.setReturnMessage("添加个人菜单成功");
            blogResponse.setData(map);
            log.info("添加个人菜单成功[{}]记录数[{}]",blogCategory,num);
        }catch (Exception e){
            blogResponse.setReturnCode(ConstantException.ERRORCODE);
            blogResponse.setReturnMessage("添加个人菜单失败");
            log.error("添加个人菜单失败[{}]，代码出现异常",blogCategory,e);
        }
        return blogResponse;
    }

    
    @PostMapping("updateById")
    @ResponseBody
    public BlogResponse update(BlogCategory blogCategory, HttpServletRequest request) {
        log.info("修改个人菜单接受到参数:[{}]",blogCategory);
        BlogResponse blogResponse = new BlogResponse();
        //参数校验
        if(StringUtils.isEmpty(blogCategory.getCategorySeq())){
            blogResponse.setReturnCode(ConstantException.ERRORCODE);
            blogResponse.setReturnMessage("修改个人菜单对象失败，请求参数不合法");
            log.error("修改个人菜单对象失败，主键不存在");
            return blogResponse;
        }
        //执行更新
        try {
            SysUser currentUser = (SysUser)request.getSession().getAttribute("user");
            blogCategory.setUpdateBy(currentUser.getUserName());
            int num = this.blogCategoryService.update(blogCategory);
            log.info("修改个人菜单对象成功[{}]记录数[{}]",blogCategory,num);
            blogResponse.setReturnCode(ConstantException.SUCCESSCODE);
            blogResponse.setReturnMessage("修改个人菜单对象成功");
        }catch (Exception e){
            blogResponse.setReturnCode(ConstantException.ERRORCODE);
            blogResponse.setReturnMessage("修改个人菜单对象失败");
            log.error("修改个人菜单对象[{}]失败，代码出现异常",blogCategory,e);
        }
        return blogResponse;
    }

    
    @GetMapping("deleteById")
    @ResponseBody
    public BlogResponse deleteById(@RequestParam("categoryId") Long categoryId, @RequestParam("userName") String userName) {
        log.info("根据id删除个人菜单对象接受到参数id:[{}]userName[{}]",categoryId,userName);
        BlogResponse blogResponse = new BlogResponse();
        //参数校验
        if(StringUtils.isEmpty(userName)||StringUtils.isEmpty(categoryId)){
            blogResponse.setReturnCode(ConstantException.ERRORCODE);
            blogResponse.setReturnMessage("删除个人菜单失败，请求参数不合法");
            log.error("删除个人菜单失败，用户ID与用户名不能为空");
            return blogResponse;
        }
        try {
            int num=this.blogCategoryService.deleteById(categoryId,userName);
            blogResponse.setReturnCode(ConstantException.SUCCESSCODE);
            blogResponse.setReturnMessage("删除个人菜单对象成功");
            log.info("根据id[{}]删除个人菜单对象成功,记录数[{}]",categoryId,num);
        }catch (Exception e){
            blogResponse.setReturnCode(ConstantException.ERRORCODE);
            blogResponse.setReturnMessage("删除个人菜单对象失败");
            log.error("根据id[{}]删除个人菜单对象失败，代码出现异常",categoryId,e);
        }
        return blogResponse;
    }



}
