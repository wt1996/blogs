package cn.wangtao.blogs.controller;

import cn.wangtao.ResponseEntity.BlogResponse;
import cn.wangtao.blogs.controller.blog_controller.BlogMenuControllerApi;
import cn.wangtao.exception.ConstantException;
import cn.wangtao.pojo.blog.BlogMenu;
import cn.wangtao.pojo.user.SysUser;
import cn.wangtao.blogs.service.BlogMenuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

/**
 * @ClassName blogMenuController
 * @Auth 桃子
 * @Date 2019-6-12 17:42
 * @Version 1.0
 * @Description
 **/
@Controller
@RequestMapping("blogMenu")
@Slf4j
public class BlogMenuController implements BlogMenuControllerApi {
    
    @Autowired
    private BlogMenuService blogMenuService;


    @GetMapping("selectAll")
    @ResponseBody
    public BlogResponse selectAll() {
        BlogResponse blogResponse = new BlogResponse();
        HashMap<String, Object> map = new HashMap<>();
        try{
            List<BlogMenu> list= this.blogMenuService.selectAll();
            blogResponse.setReturnCode(ConstantException.SUCCESSCODE);
            blogResponse.setReturnMessage("查询所有系统标签成功");
            map.put("blogMenuList",list);
            blogResponse.setData(map);
            log.info("查询所有系统标签成功:[{}]",list);
        }catch (Exception e){
            blogResponse.setReturnCode(ConstantException.ERRORCODE);
            blogResponse.setReturnMessage("查询所有系统标签失败");
            log.error("查询所有系统标签失败，代码出现异常",e);
        }
        return blogResponse;
    }

    @GetMapping("selectById")
    @ResponseBody
    public BlogResponse selectById(@RequestParam("id") Long id) {
        log.info("根据id查询对象接受到参数id:[{}]",id);
        BlogResponse blogResponse = new BlogResponse();
        HashMap<String, Object> map = new HashMap<>();
        //参数校验
        if(StringUtils.isEmpty(id)){
            blogResponse.setReturnCode(ConstantException.ERRORCODE);
            blogResponse.setReturnMessage("查询系统标签失败，请求参数不合法");
            log.error("查询系统标签失败，主键id不能为空");
            return blogResponse;
        }
        try {
            BlogMenu blogMenu = this.blogMenuService.selectById(id);
            map.put("blogMenu",blogMenu);
            blogResponse.setReturnCode(ConstantException.SUCCESSCODE);
            blogResponse.setReturnMessage("查询系统标签对象成功");
            blogResponse.setData(map);
            log.info("根据id[{}]查询系统标签对象成功[{}]",id,blogMenu);
        }catch (Exception e){
            blogResponse.setReturnCode(ConstantException.ERRORCODE);
            blogResponse.setReturnMessage("查询系统标签对象失败");
            log.error("根据id[{}]查询对象失败，代码出现异常",id,e);
        }
        return blogResponse;
    }


    @PostMapping("insert")
    @ResponseBody
    public BlogResponse insert(BlogMenu blogMenu, HttpServletRequest request) {
        log.info("添加系统标签接受到参数BlogMenu：[{}]",blogMenu);
        BlogResponse blogResponse = new BlogResponse();
        HashMap<String, Object> map = new HashMap<>();
        //参数校验
        if(StringUtils.isEmpty(blogMenu.getMenuName())){
            blogResponse.setReturnCode(ConstantException.ERRORCODE);
            blogResponse.setReturnMessage("增加系统标签失败，请求参数不合法");
            log.error("增加系统标签失败，系统标签名不能为空");
            return blogResponse;
        }
        //执行增加
        try{
            SysUser currentUser = (SysUser)request.getSession().getAttribute("user");
            blogMenu.setCreateByName(currentUser.getUserName());//从Session中取
            int num = this.blogMenuService.insert(blogMenu);
            map.put("blogMenu",blogMenu);
            blogResponse.setReturnCode(ConstantException.SUCCESSCODE);
            blogResponse.setReturnMessage("添加系统标签成功");
            blogResponse.setData(map);
            log.info("添加系统标签成功[{}]记录数[{}]",blogMenu,num);
        }catch (Exception e){
            blogResponse.setReturnCode(ConstantException.ERRORCODE);
            blogResponse.setReturnMessage("添加系统标签失败");
            log.error("添加系统标签失败[{}],代码出现异常",blogMenu,e);
        }
        return blogResponse;
    }

    @PostMapping("updateById")
    @ResponseBody
    public BlogResponse update(BlogMenu blogMenu, HttpServletRequest request) {
        log.info("修改系统标签接受到参数BlogMenu:[{}]",blogMenu);
        BlogResponse blogResponse = new BlogResponse();
        //参数校验
        if(StringUtils.isEmpty(blogMenu.getMenuSeq())){
            blogResponse.setReturnCode(ConstantException.ERRORCODE);
            blogResponse.setReturnMessage("修改系统标签失败，请求参数不合法");
            log.error("修改系统标签失败，主键id不能为空");
            return blogResponse;
        }
        try {
            SysUser currentUser = (SysUser)request.getSession().getAttribute("user");
            blogMenu.setUpdateByName(currentUser.getUserName());
            int num = this.blogMenuService.update(blogMenu);
            log.info("修改系统标签对象成功[{}]记录数[{}]",blogMenu,num);
            blogResponse.setReturnCode(ConstantException.SUCCESSCODE);
            blogResponse.setReturnMessage("修改系统标签对象成功");
        }catch (Exception e){
            blogResponse.setReturnCode(ConstantException.ERRORCODE);
            blogResponse.setReturnMessage("修改系统标签对象失败");
            log.error("修改系统标签对象[{}]失败，代码出现异常",blogMenu,e);
        }
        return blogResponse;
    }

    @GetMapping("deleteById")
    @ResponseBody
    public BlogResponse deleteById(@RequestParam("id") Long id,HttpServletRequest request) {
        log.info("根据id删除系统标签对象接受到参数id:[{}]",id);
        BlogResponse blogResponse = new BlogResponse();
        //参数校验
        if(StringUtils.isEmpty(id)){
            blogResponse.setReturnCode(ConstantException.ERRORCODE);
            blogResponse.setReturnMessage("删除系统标签失败，请求参数不合法");
            log.error("删除系统标签失败，主键id不能为空");
            return blogResponse;
        }
        try {
            SysUser currentUser = (SysUser)request.getSession().getAttribute("user");
            int num = this.blogMenuService.deleteById(id,currentUser.getUserName());
            blogResponse.setReturnCode(ConstantException.SUCCESSCODE);
            blogResponse.setReturnMessage("删除系统标签对象成功");
            log.info("根据id[{}]删除系统标签对象成功,记录数[{}]",id,num);
        }catch (Exception e){
            blogResponse.setReturnCode(ConstantException.ERRORCODE);
            blogResponse.setReturnMessage("删除系统标签对象失败");
            log.error("根据id[{}]删除系统标签对象失败，代码出现异常",id,e);
        }
        return blogResponse;
    }
}

