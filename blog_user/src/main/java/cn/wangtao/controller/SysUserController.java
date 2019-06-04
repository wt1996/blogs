package cn.wangtao.controller;

import cn.wangtao.ResponseEntity.BlogResponse;
import cn.wangtao.controller.user_controller.SysUserControllerApi;
import cn.wangtao.exception.ConstantException;
import cn.wangtao.pojo.RequestEntity.SysUserModel;
import cn.wangtao.pojo.user.SysUser;
import cn.wangtao.service.SysUserService;
import cn.wangtao.utils.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

/**
 * @ClassName SysUserController
 * @Auth 桃子
 * @Date 2019-5-17 14:49
 * @Version 1.0
 * @Description
 **/
@Controller
@Slf4j
@RequestMapping("user")
public class SysUserController implements SysUserControllerApi {

    @Autowired
    private SysUserService sysUserService;

    @Override
    @PostMapping("insert")
    @ResponseBody
    public BlogResponse insertSysUser(SysUser user, HttpServletRequest request) {
        log.info("添加用户接受到参数：[{}]",user);
        BlogResponse blogResponse = new BlogResponse();
        HashMap<String, Object> map = new HashMap<>();
        map.put("user",user);
        SysUser sysUser = (SysUser)request.getSession().getAttribute("user");
        if(sysUser!=null){
            user.setCreateBy(sysUser.getUserName());//从Session中取
        }else{
            user.setSysRoleSeq(Constants.USER_COMMON);//普通用户
        }

        try{
            int num = sysUserService.insert(user);
            blogResponse.setReturnCode(ConstantException.SUCCESSCODE);
            blogResponse.setReturnMessage("添加用户成功");
            blogResponse.setData(map);
            log.info("添加用户成功[{}]记录数[{}]",user,num);
        }catch (Exception e){
            blogResponse.setReturnCode(ConstantException.ERRORCODE);
            blogResponse.setReturnMessage("添加用户失败");
            log.error("添加用户失败[{}]",user,e);
        }
        return blogResponse;
    }

    @GetMapping("selectAll")
    @ResponseBody
    @Override
    public BlogResponse selectAll(){
        BlogResponse blogResponse = new BlogResponse();
        HashMap<String, Object> map = new HashMap<>();
        try{
            List<SysUser> list= sysUserService.selectAll();
            blogResponse.setReturnCode(ConstantException.SUCCESSCODE);
            blogResponse.setReturnMessage("查询所有用户成功");
            map.put("userList",list);
            blogResponse.setData(map);
            log.info("查询所有用户成功");
        }catch (Exception e){
            blogResponse.setReturnCode(ConstantException.ERRORCODE);
            blogResponse.setReturnMessage("查询所有用户失败");
            log.error("查询所有用户失败",e);
        }
        return blogResponse;
    }

    @PostMapping("selectByParams")
    @ResponseBody
    public BlogResponse selectByParams(SysUserModel params) {
        log.info("通过用户属性字段接受到参数：[{}]",params);
        BlogResponse blogResponse = new BlogResponse();
        HashMap<String, Object> map = new HashMap<>();
        try {
            List<SysUser> sysUser = sysUserService.selectByParams(params);
            map.put("user",sysUser);
            blogResponse.setReturnCode(ConstantException.SUCCESSCODE);
            blogResponse.setReturnMessage("通过用户属性字段查询成功");
            blogResponse.setData(map);
            log.info("通过属性字段[{}]查询用户成功",params);
        }catch (Exception e){
            blogResponse.setReturnCode(ConstantException.ERRORCODE);
            blogResponse.setReturnMessage("通过属性字段[{}]查询用户失败");
            log.error("通过属性字段[{}]查询用户失败",params,e);
        }
        return blogResponse;
    }

    @GetMapping("selectById")
    @ResponseBody
    public BlogResponse selectById(@RequestParam("id") Long id) {
        log.info("根据id查询对象接受到参数:[{}]",id);
        BlogResponse blogResponse = new BlogResponse();
        HashMap<String, Object> map = new HashMap<>();
        try {
            SysUser user = sysUserService.selectById(id);
            map.put("user",user);
            blogResponse.setReturnCode(ConstantException.SUCCESSCODE);
            blogResponse.setReturnMessage("根据id查询对象成功");
            blogResponse.setData(map);
            log.info("根据id[{}]查询对象成功",id);
        }catch (Exception e){
            blogResponse.setReturnCode(ConstantException.ERRORCODE);
            blogResponse.setReturnMessage("根据id查询对象失败");
            log.error("根据id[{}]查询对象失败",id,e);
        }
        return blogResponse;
    }

    @PostMapping("updateById")
    @ResponseBody
    public BlogResponse updateById(SysUserModel userModel,HttpServletRequest request) {
        log.info("修改对象接受到参数:[{}]",userModel);
        BlogResponse blogResponse = new BlogResponse();
        HashMap<String, Object> map = new HashMap<>();
        SysUser user =new SysUser();
        BeanUtils.copyProperties(userModel,user);
        //获取当前对象
        SysUser currentUser = (SysUser)request.getSession().getAttribute("user");
        user.setUpdateBy(currentUser.getUserName());
        try {
            int num = sysUserService.update(user);
            if(1==num){
                SysUser sysUser=null;
                try{
                    sysUser= sysUserService.selectById(user.getSysUserSeq());
                    log.info("修改对象成功[{}]记录数[{}]查询修改后的对象成功:[{}]",user,num,sysUser);
                }catch (Exception e){
                    log.error("修改对象成功[{}]记录数[{}]查询修改后的对象失败:[{}]",user,num,sysUser,e);
                }
                map.put("user",sysUser);
                blogResponse.setReturnCode(ConstantException.SUCCESSCODE);
                blogResponse.setReturnMessage("修改对象成功");
                blogResponse.setData(map);
            }
        }catch (Exception e){
            blogResponse.setReturnCode(ConstantException.ERRORCODE);
            blogResponse.setReturnMessage("修改对象失败");
            log.error("修改对象[{}]失败",user,e);
        }
        return blogResponse;
    }


    @GetMapping("deleteById")
    @ResponseBody
    public BlogResponse deleteById(@RequestParam("id") Long id,HttpServletRequest request) {
        log.info("根据id删除对象接受到参数:[{}]",id);
        BlogResponse blogResponse = new BlogResponse();
        HashMap<String, Object> map = new HashMap<>();
        try {
            //获取当前对象
            SysUser currentUser = (SysUser)request.getSession().getAttribute("user");
            int num = sysUserService.deleteById(id, currentUser.getUserName());
            blogResponse.setReturnCode(ConstantException.SUCCESSCODE);
            blogResponse.setReturnMessage("根据id删除对象成功");
            blogResponse.setData(map);
            log.info("根据id[{}]删除对象成功,记录数[{}]",id,num);
        }catch (Exception e){
            blogResponse.setReturnCode(ConstantException.ERRORCODE);
            blogResponse.setReturnMessage("根据id删除对象失败");
            log.error("根据id[{}]删除对象失败",id,e);
        }
        return blogResponse;
    }


    @PostMapping("selectByName")
    @ResponseBody
    public BlogResponse selectByName(String userName) {
        log.info("查询对象接受到参数:[{}]",userName);
        BlogResponse blogResponse = new BlogResponse();
        HashMap<String, Object> map = new HashMap<>();
        try {
            SysUser user = sysUserService.selectByName(userName);
            if(user!=null){
                map.put("ifExit",true);
            }else{

                map.put("ifExit",false);
            }
            blogResponse.setReturnCode(ConstantException.SUCCESSCODE);
            blogResponse.setReturnMessage("根据userName查询对象成功");
            blogResponse.setData(map);
            log.info("根据userName[{}]查询对象成功",userName);
        }catch (Exception e){
            blogResponse.setReturnCode(ConstantException.ERRORCODE);
            blogResponse.setReturnMessage("userName对象不存在");
            log.error("根据userName[{}]查询对象失败",userName,e);
        }
        return blogResponse;
    }

}
