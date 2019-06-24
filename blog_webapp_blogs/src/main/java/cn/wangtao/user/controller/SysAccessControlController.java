package cn.wangtao.user.controller;

import cn.wangtao.ResponseEntity.BlogResponse;
import cn.wangtao.blogs.controller.user_controller.SysAccessControlControllerApi;
import cn.wangtao.exception.ConstantException;
import cn.wangtao.pojo.user.SysAccessControl;
import cn.wangtao.pojo.user.SysUser;
import cn.wangtao.user.service.SysAccessControlService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

/**
 * @ClassName SysAccessControlController
 * @Auth 桃子
 * @Date 2019-5-30 15:08
 * @Version 1.0
 * @Description
 **/
@Controller
@Slf4j
@RequestMapping("accessControl")
public class SysAccessControlController implements SysAccessControlControllerApi {

    @Autowired
    private SysAccessControlService accessControlService;

    @GetMapping("selectAll")
    @ResponseBody
    @Override
    public BlogResponse selectAll() {
        BlogResponse blogResponse = new BlogResponse();
        HashMap<String, Object> map = new HashMap<>();
        try{
            List<SysAccessControl> list= accessControlService.selectAll();
            blogResponse.setReturnCode(ConstantException.SUCCESSCODE);
            blogResponse.setReturnMessage("查询所有功能成功");
            map.put("accessControlList",list);
            blogResponse.setData(map);
            log.info("查询所有功能成功");
        }catch (Exception e){
            blogResponse.setReturnCode(ConstantException.ERRORCODE);
            blogResponse.setReturnMessage("查询所有功能失败");
            log.error("查询所有功能失败，代码出现异常",e);
        }
        return blogResponse;
    }

    @Override
    @GetMapping("selectById")
    @ResponseBody
    public BlogResponse selectById(@RequestParam("id") Long id) {
        log.info("根据id查询对象接受到参数:[{}]",id);
        BlogResponse blogResponse = new BlogResponse();
        HashMap<String, Object> map = new HashMap<>();
        //参数校验
        if(StringUtils.isEmpty(id)){
            blogResponse.setReturnCode(ConstantException.ERRORCODE);
            blogResponse.setReturnMessage("请求参数不合法");
            log.error("请求参数不合法,id不能为空");
            return blogResponse;
        }
        try {
            SysAccessControl accessControl = accessControlService.selectById(id);
            map.put("accessControl",accessControl);
            blogResponse.setReturnCode(ConstantException.SUCCESSCODE);
            blogResponse.setReturnMessage("根据id查询对象成功");
            blogResponse.setData(map);
            log.info("根据id[{}]查询对象成功",id);
        }catch (Exception e){
            blogResponse.setReturnCode(ConstantException.ERRORCODE);
            blogResponse.setReturnMessage("根据id查询对象失败");
            log.error("根据id[{}]查询对象失败，代码出现异常",id,e);
        }
        return blogResponse;
    }


    @Override
    @PostMapping("insert")
    @ResponseBody
    public BlogResponse insert(@RequestParam("accessControlName") String accessControlName, HttpServletRequest request) {
        log.info("添加功能接受到参数：[{}]",accessControlName);
        BlogResponse blogResponse = new BlogResponse();
        HashMap<String, Object> map = new HashMap<>();
        //参数校验
        if(StringUtils.isEmpty(accessControlName)){
            blogResponse.setReturnCode(ConstantException.ERRORCODE);
            blogResponse.setReturnMessage("请求参数不合法");
            log.error("请求参数不合法,accessControlName不能为空");
            return blogResponse;
        }

        SysAccessControl accessControl = new SysAccessControl();
        accessControl.setAccessControlName(accessControlName);
        SysUser currentUser = (SysUser)request.getSession().getAttribute("user");
        if(currentUser!=null){
            accessControl.setCreateByName(currentUser.getUserName());//从Session中取
        }
        try{
            int num = accessControlService.insert(accessControl);
            map.put("accessControl",accessControl);
            blogResponse.setReturnCode(ConstantException.SUCCESSCODE);
            blogResponse.setReturnMessage("添加功能成功");
            blogResponse.setData(map);
            log.info("添加功能对象成功[{}],记录数[{}]",accessControl,num);
        }catch (Exception e){
            blogResponse.setReturnCode(ConstantException.ERRORCODE);
            blogResponse.setReturnMessage("添加功能失败");
            log.error("添加功能对象失败[{}]，代码出现异常",accessControl,e);
        }
        return blogResponse;
    }

    @Override
    @PostMapping("update")
    @ResponseBody
    public BlogResponse update(SysAccessControl accessControl,HttpServletRequest request) {
        log.info("修改功能接受到参数:[{}]",accessControl);
        BlogResponse blogResponse = new BlogResponse();
        //参数校验
        if(StringUtils.isEmpty(accessControl.getSysAccessControlSeq())){
            blogResponse.setReturnCode(ConstantException.ERRORCODE);
            blogResponse.setReturnMessage("修改对象失败，请求参数不合法");
            log.error("修改对象失败，主键不存在");
            return blogResponse;
        }

        SysUser currentUser = (SysUser)request.getSession().getAttribute("user");
        if(currentUser!=null){
            accessControl.setUpdateByName(currentUser.getUserName());
        }
        try {
            int num = accessControlService.update(accessControl);
            log.info("修改对象成功[{}]记录数[{}]查询修改后的对象成功:[{}]",accessControl,num);
            blogResponse.setReturnCode(ConstantException.SUCCESSCODE);
            blogResponse.setReturnMessage("修改对象成功");
        }catch (Exception e){
            blogResponse.setReturnCode(ConstantException.ERRORCODE);
            blogResponse.setReturnMessage("修改对象失败");
            log.error("修改对象[{}]失败，代码出现异常",accessControl,e);
        }
        return blogResponse;
    }

    @Override
    @GetMapping("deleteById")
    @ResponseBody
    public BlogResponse deleteById(@RequestParam("id") Long id,HttpServletRequest request) {
        log.info("根据id删除功能对象接受到参数:[{}]",id);
        BlogResponse blogResponse = new BlogResponse();
        //参数校验
        if(StringUtils.isEmpty(id)){
            blogResponse.setReturnCode(ConstantException.ERRORCODE);
            blogResponse.setReturnMessage("删除失败，请求参数不合法");
            log.error("删除失败，主键id不能为空");
            return blogResponse;
        }
        try {
            int num = accessControlService.deleteById(id);
            blogResponse.setReturnCode(ConstantException.SUCCESSCODE);
            blogResponse.setReturnMessage("根据id删除功能对象成功");
            log.info("根据id[{}]删除功能对象成功，记录数[{}]",id,num);
        }catch (Exception e){
            blogResponse.setReturnCode(ConstantException.ERRORCODE);
            blogResponse.setReturnMessage("根据id删除功能对象失败");
            log.error("根据id[{}]删除功能对象失败，代码出现异常",id,e);
        }
        return blogResponse;
    }
}
