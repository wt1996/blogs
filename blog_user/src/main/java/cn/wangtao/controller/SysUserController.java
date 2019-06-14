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
import org.springframework.util.StringUtils;
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
        //参数校验
        if(StringUtils.isEmpty(user.getUserName())){
            blogResponse.setReturnCode(ConstantException.ERRORCODE);
            blogResponse.setReturnMessage("增加失败，请求参数不合法");
            log.error("增加失败，主键id不能为空");
            return blogResponse;
        }
        SysUser currentUser = (SysUser)request.getSession().getAttribute("user");
        if(currentUser!=null){
            user.setCreateByName(currentUser.getUserName());//从Session中取
        }else{
            user.setSysRoleSeq(Constants.USER_COMMON);//普通用户
        }

        try{
            int num = sysUserService.insert(user);
            map.put("user",user);
            blogResponse.setReturnCode(ConstantException.SUCCESSCODE);
            blogResponse.setReturnMessage("添加用户成功");
            blogResponse.setData(map);
            log.info("添加用户成功[{}]记录数[{}]",user,num);
        }catch (Exception e){
            blogResponse.setReturnCode(ConstantException.ERRORCODE);
            blogResponse.setReturnMessage("添加用户失败");
            log.error("添加用户失败[{}]，，代码出现异常",user,e);
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
            log.error("查询所有用户失败，代码出现异常",e);
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
            log.error("通过属性字段[{}]查询用户失败，代码出现异常",params,e);
        }
        return blogResponse;
    }

    @GetMapping("selectById")
    @ResponseBody
    public BlogResponse selectById(@RequestParam("id") Long id) {
        log.info("根据id查询对象接受到参数:[{}]",id);
        BlogResponse blogResponse = new BlogResponse();
        HashMap<String, Object> map = new HashMap<>();
        //参数校验
        if(StringUtils.isEmpty(id)){
            blogResponse.setReturnCode(ConstantException.ERRORCODE);
            blogResponse.setReturnMessage("查询失败，请求参数不合法");
            log.error("查询失败，主键id不能为空");
            return blogResponse;
        }
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
            log.error("根据id[{}]查询对象失败，代码出现异常",id,e);
        }
        return blogResponse;
    }

    @PostMapping("updateById")
    @ResponseBody
    public BlogResponse updateById(SysUserModel userModel,HttpServletRequest request) {
        log.info("修改对象接受到参数:[{}]",userModel);
        BlogResponse blogResponse = new BlogResponse();
        SysUser user =new SysUser();
        //参数校验
        if(StringUtils.isEmpty(userModel.getSysRoleSeq())){
            blogResponse.setReturnCode(ConstantException.ERRORCODE);
            blogResponse.setReturnMessage("修改失败，请求参数不合法");
            log.error("修改失败，主键id不能为空");
            return blogResponse;
        }
        //执行更新
        BeanUtils.copyProperties(userModel,user);
        //获取当前对象
        SysUser currentUser = (SysUser)request.getSession().getAttribute("user");
        if(currentUser!=null){
            user.setUpdateBy(currentUser.getUserName());
        }
        try {
            int num = sysUserService.update(user);
            log.info("修改对象成功[{}]记录数[{}]",user,num);
            blogResponse.setReturnCode(ConstantException.SUCCESSCODE);
            blogResponse.setReturnMessage("修改对象成功");
        }catch (Exception e){
            blogResponse.setReturnCode(ConstantException.ERRORCODE);
            blogResponse.setReturnMessage("修改对象失败");
            log.error("修改对象[{}]失败，代码出现异常",user,e);
        }
        return blogResponse;
    }


    @GetMapping("deleteById")
    @ResponseBody
    public BlogResponse deleteById(@RequestParam("id") Long id,HttpServletRequest request) {
        log.info("根据id删除对象接受到参数:[{}]",id);
        BlogResponse blogResponse = new BlogResponse();
        //参数校验
        if(StringUtils.isEmpty(id)){
            blogResponse.setReturnCode(ConstantException.ERRORCODE);
            blogResponse.setReturnMessage("删除失败，请求参数不合法");
            log.error("删除失败，主键id不能为空");
            return blogResponse;
        }
        try {
            //获取当前对象
            SysUser currentUser = (SysUser)request.getSession().getAttribute("user");
            int num = sysUserService.deleteById(id, currentUser.getUserName());
            blogResponse.setReturnCode(ConstantException.SUCCESSCODE);
            blogResponse.setReturnMessage("根据id删除对象成功");
            log.info("根据id[{}]删除对象成功,记录数[{}]",id,num);
        }catch (Exception e){
            blogResponse.setReturnCode(ConstantException.ERRORCODE);
            blogResponse.setReturnMessage("根据id删除对象失败");
            log.error("根据id[{}]删除对象失败，代码出现异常",id,e);
        }
        return blogResponse;
    }


    @GetMapping("verifyName")
    @ResponseBody
    public BlogResponse verifyName(String userName) {
        log.info("查询对象接受到参数:[{}]",userName);
        BlogResponse blogResponse = new BlogResponse();
        HashMap<String, Object> map = new HashMap<>();
        //参数校验
        if(StringUtils.isEmpty(userName)){
            blogResponse.setReturnCode(ConstantException.ERRORCODE);
            blogResponse.setReturnMessage("校验失败，请求参数不合法");
            log.error("校验失败，主键id不能为空");
            return blogResponse;
        }
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
            log.error("根据userName[{}]查询对象失败，代码出现异常",userName,e);
        }
        return blogResponse;
    }

    @GetMapping("selectByName")
    @ResponseBody
    public BlogResponse selectByName(String userName) {
        log.info("查询对象接受到参数:[{}]",userName);
        BlogResponse blogResponse = new BlogResponse();
        HashMap<String, Object> map = new HashMap<>();
        //参数校验
        if(StringUtils.isEmpty(userName)){
            blogResponse.setReturnCode(ConstantException.ERRORCODE);
            blogResponse.setReturnMessage("查询失败，请求参数不合法");
            log.error("查询失败，userName参数为空");
            return blogResponse;
        }
        try {
            SysUser user = sysUserService.selectByName(userName);
            map.put("user",user);
            blogResponse.setReturnCode(ConstantException.SUCCESSCODE);
            blogResponse.setReturnMessage("根据userName查询对象成功");
            blogResponse.setData(map);
            log.info("根据userName[{}]查询对象成功",userName);
        }catch (Exception e){
            blogResponse.setReturnCode(ConstantException.ERRORCODE);
            blogResponse.setReturnMessage("userName对象不存在");
            log.error("根据userName[{}]查询对象失败，代码出现异常",userName,e);
        }
        return blogResponse;
    }
}
