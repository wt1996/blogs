package cn.wangtao.blogs.controller;

import cn.wangtao.ResponseEntity.BlogResponse;
import cn.wangtao.blogs.controller.blog_controller.SysCmsControllerApi;
import cn.wangtao.exception.ConstantException;
import cn.wangtao.pojo.cms.SysNotify;
import cn.wangtao.pojo.user.SysUser;
import cn.wangtao.blogs.service.SysNotifyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

/**
 * @ClassName SysNotifyController
 * @Auth 桃子
 * @Date 2019-6-19 10:00
 * @Version 1.0
 * @Description  添加 删除 修改 查询所有 根据id查询
 **/
@Slf4j
@Controller
@RequestMapping("notify")
public class SysNotifyController implements SysCmsControllerApi {

    @Autowired
    private SysNotifyService sysNotifyService;

    //增加
    @PostMapping("insert")
    @ResponseBody
    public BlogResponse insert(SysNotify sysNotify, HttpServletRequest request) {
        log.info("添加系统通知对象接受到参数SysNotify:[{}]",sysNotify);
        BlogResponse blogResponse = new BlogResponse();
        //参数校验
        if(StringUtils.isEmpty(sysNotify.getNotifyMessage())||StringUtils.isEmpty(sysNotify.getNotifyTopic())){
            blogResponse.setReturnCode(ConstantException.ERRORCODE);
            blogResponse.setReturnMessage("增加系统通知对象失败，请求参数不合法");
            log.error("增加系统通知对象失败，通知的内容与主题信息不能为空");
            return blogResponse;
        }
        //执行增加
        try{
            SysUser currentUser = (SysUser)request.getSession().getAttribute("user");
            sysNotify.setCreateByName(currentUser.getUserName());//从Session中取
            int num = this.sysNotifyService.insert(sysNotify);
            HashMap<String, Object> map = new HashMap<>();
            map.put("sysNotify",sysNotify);
            blogResponse.setReturnCode(ConstantException.SUCCESSCODE);
            blogResponse.setReturnMessage("添加系统通知对象成功");
            blogResponse.setData(map);
            log.info("添加系统通知对象成功[{}]记录数[{}]",sysNotify,num);
        }catch (Exception e){
            blogResponse.setReturnCode(ConstantException.ERRORCODE);
            blogResponse.setReturnMessage("添加系统通知对象失败");
            log.error("添加系统通知对象失败[{}],代码出现异常",sysNotify,e);
        }
        return blogResponse;
    }

    //删除
    @GetMapping("deleteById")
    @ResponseBody
    public BlogResponse deleteById(@RequestParam("id") Long notifySeq) {
        log.info("删除系统通知对象接受到参数notifySeq:[{}]",notifySeq);
        BlogResponse blogResponse = new BlogResponse();
        //参数校验
        if(StringUtils.isEmpty(notifySeq)){
            blogResponse.setReturnCode(ConstantException.ERRORCODE);
            blogResponse.setReturnMessage("删除系统通知对象失败，请求参数不合法");
            log.error("删除系统通知对象失败，cmsSeq不能为空");
            return blogResponse;
        }
        //执行增加
        try{
            int num = this.sysNotifyService.deleteById(notifySeq);
            blogResponse.setReturnCode(ConstantException.SUCCESSCODE);
            blogResponse.setReturnMessage("删除系统通知对象成功");
            log.info("删除系统通知对象成功[{}]记录数[{}]",notifySeq,num);
        }catch (Exception e){
            blogResponse.setReturnCode(ConstantException.ERRORCODE);
            blogResponse.setReturnMessage("添加系统通知对象失败");
            log.error("删除系统通知对象失败[{}],代码出现异常",notifySeq,e);
        }
        return blogResponse;
    }


    //修改
    @PostMapping("update")
    @ResponseBody
    public BlogResponse update(SysNotify sysNotify, HttpServletRequest request) {
        log.info("修改系统通知对象接受到参数SysNotify:[{}]",sysNotify);
        BlogResponse blogResponse = new BlogResponse();
        //参数校验
        if(StringUtils.isEmpty(sysNotify.getNotifySeq())){
            blogResponse.setReturnCode(ConstantException.ERRORCODE);
            blogResponse.setReturnMessage("修改系统通知对象失败，请求参数不合法");
            log.error("修改系统通知对象失败，博客信息不能为空");
            return blogResponse;
        }
        //执行增加
        try{
            SysUser currentUser = (SysUser)request.getSession().getAttribute("user");
            sysNotify.setUpdateByName(currentUser.getUserName());//
            int num = this.sysNotifyService.update(sysNotify);
            blogResponse.setReturnCode(ConstantException.SUCCESSCODE);
            blogResponse.setReturnMessage("修改系统通知对象成功");
            log.info("修改系统通知对象成功[{}]记录数[{}]",sysNotify,num);
        }catch (Exception e){
            blogResponse.setReturnCode(ConstantException.ERRORCODE);
            blogResponse.setReturnMessage("修改系统通知对象失败");
            log.error("修改系统通知对象失败[{}],代码出现异常",sysNotify,e);
        }
        return blogResponse;
    }

    //查询所有
    @GetMapping("selectAll")
    @ResponseBody
    public BlogResponse selectAll() {
        log.info("查询所有系统通知对象开始");
        BlogResponse blogResponse = new BlogResponse();
        HashMap<String, Object> map = new HashMap<>();
        //执行查询
        try{
            List<SysNotify> notifyList = this.sysNotifyService.selectAll();
            map.put("notifyList",notifyList);
            blogResponse.setReturnCode(ConstantException.SUCCESSCODE);
            blogResponse.setReturnMessage("查询所有系统通知对象成功");
            blogResponse.setData(map);
            log.info("查询所有系统通知对象成功[{}]",notifyList);
        }catch (Exception e){
            blogResponse.setReturnCode(ConstantException.ERRORCODE);
            blogResponse.setReturnMessage("查询所有系统通知对象失败");
            log.error("查询所有系统通知对象失败,代码出现异常",e);
        }
        return blogResponse;
    }

    //根据ID进行查询
    @GetMapping("selectById")
    @ResponseBody
    public BlogResponse selectById(@RequestParam("id") Long notifySeq) {
        log.info("查询系统通知对象接受到参数ID[{}]",notifySeq);
        BlogResponse blogResponse = new BlogResponse();
        //参数校验
        if(StringUtils.isEmpty(notifySeq)){
            blogResponse.setReturnCode(ConstantException.ERRORCODE);
            blogResponse.setReturnMessage("查询系统通知对象失败，请求参数不合法");
            log.error("查询系统通知对象失败，notifySeq不能为空");
            return blogResponse;
        }
        //执行增加
        try{
            SysNotify sysNotify = this.sysNotifyService.selectById(notifySeq);
            HashMap<String, Object> map = new HashMap<>();
            map.put("sysNotify",sysNotify);
            blogResponse.setData(map);
            blogResponse.setReturnCode(ConstantException.SUCCESSCODE);
            blogResponse.setReturnMessage("查询系统通知对象成功");
            log.info("根据ID[{}]查询系统通知对象成功[{}]",notifySeq,sysNotify);
        }catch (Exception e){
            blogResponse.setReturnCode(ConstantException.ERRORCODE);
            blogResponse.setReturnMessage("查询系统通知对象失败");
            log.error("根据ID[{}]查询系统通知对象失败,代码出现异常",notifySeq,e);
        }
        return blogResponse;
    }

    //查询所有未发送的通知信息
    @GetMapping("selectAllByStatus")
    @ResponseBody
    public BlogResponse selectAllByStatus(@RequestParam("notifyStatus") Character notifyStatus) {
        log.info("根据通知状态查询所有系统通知对象接收参数notifyStatus：[{}]",notifyStatus);
        BlogResponse blogResponse = new BlogResponse();
        HashMap<String, Object> map = new HashMap<>();
        //参数校验
        if(StringUtils.isEmpty(notifyStatus)){
            blogResponse.setReturnCode(ConstantException.ERRORCODE);
            blogResponse.setReturnMessage("根据通知状态查询所有系统通知对象失败，请求参数不合法");
            log.error("根据通知状态查询所有系统通知对象失败，notifyStatus不能为空");
            return blogResponse;
        }
        //执行查询
        try{
            List<SysNotify> notifyList = this.sysNotifyService.selectAllByStatus(notifyStatus);
            map.put("notifyList",notifyList);
            blogResponse.setReturnCode(ConstantException.SUCCESSCODE);
            blogResponse.setReturnMessage("根据通知状态查询所有系统通知对象成功");
            blogResponse.setData(map);
            log.info("根据通知状态查询所有系统通知对象成功[{}]",notifyList);
        }catch (Exception e){
            blogResponse.setReturnCode(ConstantException.ERRORCODE);
            blogResponse.setReturnMessage("根据通知状态查询所有系统通知对象失败");
            log.error("根据通知状态查询所有系统通知对象失败,代码出现异常",e);
        }
        return blogResponse;
    }
}
