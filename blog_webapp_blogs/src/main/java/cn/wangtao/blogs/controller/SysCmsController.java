package cn.wangtao.blogs.controller;

import cn.wangtao.ResponseEntity.BlogResponse;
import cn.wangtao.blogs.controller.blog_controller.SysCmsControllerApi;
import cn.wangtao.exception.ConstantException;
import cn.wangtao.pojo.cms.SysCms;
import cn.wangtao.pojo.user.SysUser;
import cn.wangtao.blogs.service.SysCmsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

/**
 * @ClassName SysCmsController
 * @Auth 桃子
 * @Date 2019-6-18 15:19
 * @Version 1.0
 * @Description  推荐博客信息管理接口：增加，删除（可以直接删除），修改，排序（也是修改，修改TopNum的值）
 **/
@Slf4j
@Controller
@RequestMapping("cms")
public class SysCmsController implements SysCmsControllerApi {

    @Autowired
    private SysCmsService sysCmsService;

    //添加推荐博客对象
    @PostMapping("insert")
    @ResponseBody
    public BlogResponse insert(SysCms sysCms, HttpServletRequest request) {
        log.info("添加推荐博客对象接受到参数SysCms:[{}]",sysCms);
        BlogResponse blogResponse = new BlogResponse();
        //参数校验
        if(StringUtils.isEmpty(sysCms.getBlogSeq())){
            blogResponse.setReturnCode(ConstantException.ERRORCODE);
            blogResponse.setReturnMessage("增加推荐博客对象失败，请求参数不合法");
            log.error("增加推荐博客对象失败，博客信息不能为空");
            return blogResponse;
        }
        //执行增加
        try{
            SysUser currentUser = (SysUser)request.getSession().getAttribute("user");
            sysCms.setCreateByName(currentUser.getUserName());//从Session中取
            int num = this.sysCmsService.insert(sysCms);
            HashMap<String, Object> map = new HashMap<>();
            map.put("sysCms",sysCms);
            blogResponse.setReturnCode(ConstantException.SUCCESSCODE);
            blogResponse.setReturnMessage("添加推荐博客对象成功");
            blogResponse.setData(map);
            log.info("添加推荐博客对象成功[{}]记录数[{}]",sysCms,num);
        }catch (Exception e){
            blogResponse.setReturnCode(ConstantException.ERRORCODE);
            blogResponse.setReturnMessage("添加推荐博客对象失败");
            log.error("添加推荐博客对象失败[{}],代码出现异常",sysCms,e);
        }
        return blogResponse;
    }

    //删除
    @GetMapping("deleteById")
    @ResponseBody
    public BlogResponse deleteById(@RequestParam("id") Long cmsSeq) {
        log.info("删除推荐博客对象接受到参数cmsSeq:[{}]",cmsSeq);
        BlogResponse blogResponse = new BlogResponse();
        //参数校验
        if(StringUtils.isEmpty(cmsSeq)){
            blogResponse.setReturnCode(ConstantException.ERRORCODE);
            blogResponse.setReturnMessage("删除推荐博客对象失败，请求参数不合法");
            log.error("删除推荐博客对象失败，cmsSeq不能为空");
            return blogResponse;
        }
        //执行增加
        try{
            int num = this.sysCmsService.deleteById(cmsSeq);
            blogResponse.setReturnCode(ConstantException.SUCCESSCODE);
            blogResponse.setReturnMessage("删除推荐博客对象成功");
            log.info("删除推荐博客对象成功[{}]记录数[{}]",cmsSeq,num);
        }catch (Exception e){
            blogResponse.setReturnCode(ConstantException.ERRORCODE);
            blogResponse.setReturnMessage("添加推荐博客对象失败");
            log.error("删除推荐博客对象失败[{}],代码出现异常",cmsSeq,e);
        }
        return blogResponse;
    }

    //查询所有（根据topNum排序）
    @GetMapping("selectAllDesc")
    @ResponseBody
    public BlogResponse selectAllDesc() {
        log.info("查询所有推荐博客对象根据TopNum排序开始");
        BlogResponse blogResponse = new BlogResponse();
        HashMap<String, Object> map = new HashMap<>();
        //执行查询
        try{
            List<SysCms> cmsList = this.sysCmsService.selectAllDesc();
            map.put("cmsList",cmsList);
            blogResponse.setReturnCode(ConstantException.SUCCESSCODE);
            blogResponse.setReturnMessage("查询所有推荐博客对象成功");
            blogResponse.setData(map);
            log.info("查询所有推荐博客对象成功[{}]",cmsList);
        }catch (Exception e){
            blogResponse.setReturnCode(ConstantException.ERRORCODE);
            blogResponse.setReturnMessage("查询所有推荐博客对象失败");
            log.error("查询所有推荐博客对象失败,代码出现异常",e);
        }
        return blogResponse;
    }

    //根据ID进行查询
    @GetMapping("selectById")
    @ResponseBody
    public BlogResponse selectById(@RequestParam("id") Long cmsSeq) {
        log.info("查询推荐博客对象接受到参数ID[{}]",cmsSeq);
        BlogResponse blogResponse = new BlogResponse();
        //参数校验
        if(StringUtils.isEmpty(cmsSeq)){
            blogResponse.setReturnCode(ConstantException.ERRORCODE);
            blogResponse.setReturnMessage("查询推荐博客对象失败，请求参数不合法");
            log.error("查询推荐博客对象失败，cmsSeq不能为空");
            return blogResponse;
        }
        //执行增加
        try{
            SysCms sysCms = this.sysCmsService.selectById(cmsSeq);
            HashMap<String, Object> map = new HashMap<>();
            map.put("sysCms",sysCms);
            blogResponse.setData(map);
            blogResponse.setReturnCode(ConstantException.SUCCESSCODE);
            blogResponse.setReturnMessage("查询推荐博客对象成功");
            log.info("根据ID[{}]查询推荐博客对象成功[{}]",cmsSeq,sysCms);
        }catch (Exception e){
            blogResponse.setReturnCode(ConstantException.ERRORCODE);
            blogResponse.setReturnMessage("查询推荐博客对象失败");
            log.error("根据ID[{}]查询推荐博客对象失败,代码出现异常",cmsSeq,e);
        }
        return blogResponse;
    }

    //修改
    @PostMapping("update")
    @ResponseBody
    public BlogResponse update(SysCms sysCms, HttpServletRequest request) {
        log.info("修改推荐博客对象接受到参数SysCms:[{}]",sysCms);
        BlogResponse blogResponse = new BlogResponse();
        //参数校验
        if(StringUtils.isEmpty(sysCms.getCmsSeq())){
            blogResponse.setReturnCode(ConstantException.ERRORCODE);
            blogResponse.setReturnMessage("修改推荐博客对象失败，请求参数不合法");
            log.error("修改推荐博客对象失败，博客信息不能为空");
            return blogResponse;
        }
        //执行增加
        try{
            SysUser currentUser = (SysUser)request.getSession().getAttribute("user");
            sysCms.setUpdateByName(currentUser.getUserName());//从Session中取
            int num = this.sysCmsService.update(sysCms);
            blogResponse.setReturnCode(ConstantException.SUCCESSCODE);
            blogResponse.setReturnMessage("修改推荐博客对象成功");
            log.info("修改推荐博客对象成功[{}]记录数[{}]",sysCms,num);
        }catch (Exception e){
            blogResponse.setReturnCode(ConstantException.ERRORCODE);
            blogResponse.setReturnMessage("修改推荐博客对象失败");
            log.error("修改推荐博客对象失败[{}],代码出现异常",sysCms,e);
        }
        return blogResponse;
    }

}
