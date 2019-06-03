package cn.wangtao.controller;

import cn.wangtao.ResponseEntity.BlogResponse;
import cn.wangtao.controller.user_controller.SysRoleControllerApi;
import cn.wangtao.exception.ConstantException;
import cn.wangtao.pojo.user.SysRole;
import cn.wangtao.pojo.user.SysUser;
import cn.wangtao.service.SysRoleService;
import cn.wangtao.utils.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

/**
 * @ClassName SysRoleController
 * @Auth 桃子
 * @Date 2019-5-30 11:53
 * @Version 1.0
 * @Description
 **/
@Controller
@Slf4j
@RequestMapping("role")
public class SysRoleController implements SysRoleControllerApi {

    @Autowired
    private  SysRoleService roleService;

    @GetMapping("selectAll")
    @ResponseBody
    @Override
    public BlogResponse selectAll() {
        BlogResponse blogResponse = new BlogResponse();
        HashMap<String, Object> map = new HashMap<>();
        try{
            List<SysRole> list= roleService.selectAll();
            blogResponse.setReturnCode(ConstantException.SUCCESSCODE);
            blogResponse.setReturnMessage("查询所有角色成功");
            map.put("roleList",list);
            blogResponse.setData(map);
            log.info("查询所有角色成功");
        }catch (Exception e){
            blogResponse.setReturnCode(ConstantException.ERRORCODE);
            blogResponse.setReturnMessage("查询所有角色失败");
            log.error("查询所有角色失败",e);
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
        try {
            SysRole role = roleService.selectById(id);
            map.put("role",role);
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


    @Override
    @PostMapping("insert")
    @ResponseBody
    public BlogResponse insert(@RequestParam("roleName") String roleName, HttpServletRequest request) {
        log.info("添加角色接受到参数：[{}]",roleName);
        BlogResponse blogResponse = new BlogResponse();
        HashMap<String, Object> map = new HashMap<>();
        SysRole role =new SysRole();
        role.setRoleName(roleName);
        map.put("roleName",roleName);
        SysUser sysUser = (SysUser)request.getSession().getAttribute("user");
        if(sysUser!=null&& Constants.USER_SYSTEM==sysUser.getUserType()){
            role.setCreateBy(sysUser.getSysUserSeq());//从Session中取
        }
        try{
            int num = roleService.insert(role);
            blogResponse.setReturnCode(ConstantException.SUCCESSCODE);
            blogResponse.setReturnMessage("添加角色成功");
            blogResponse.setData(map);
            log.info("添加角色成功[{}]记录数[{}]",role,num);
        }catch (Exception e){
            blogResponse.setReturnCode(ConstantException.ERRORCODE);
            blogResponse.setReturnMessage("添加角色失败");
            log.error("添加角色失败[{}],原因[{}]",role,e);
        }
        return blogResponse;
    }

    @Override
    @PostMapping("updateById")
    @ResponseBody
    public BlogResponse update(SysRole role,HttpServletRequest request) {
        log.info("修改角色接受到参数:[{}]",role);
        BlogResponse blogResponse = new BlogResponse();
        HashMap<String, Object> map = new HashMap<>();
        SysUser currentUser = (SysUser)request.getSession().getAttribute("user");
        role.setUpdateBy(currentUser.getSysUserSeq());
        try {
            int num = roleService.update(role);
            if(1==num){
                SysRole sysRole=null;
                try{
                    sysRole= roleService.selectById(role.getSysRoleSeq());
                    log.info("修改对象成功[{}]记录数[{}]查询修改后的对象成功:[{}]",role,num,sysRole);
                }catch (Exception e){
                    log.error("修改对象成功[{}]记录数[{}]查询修改后的对象失败:[{}]",role,num,sysRole,e);
                }
                map.put("user",sysRole);
                blogResponse.setReturnCode(ConstantException.SUCCESSCODE);
                blogResponse.setReturnMessage("修改对象成功");
                blogResponse.setData(map);
            }
        }catch (Exception e){
            blogResponse.setReturnCode(ConstantException.ERRORCODE);
            blogResponse.setReturnMessage("修改对象失败");
            log.error("修改对象[{}]失败",role,e);
        }
        return blogResponse;
    }

    @Override
    @GetMapping("deleteById")
    @ResponseBody
    public BlogResponse deleteById(@RequestParam("id") Long id,HttpServletRequest request) {
        log.info("根据id删除角色对象接受到参数:[{}]",id);
        BlogResponse blogResponse = new BlogResponse();
        HashMap<String, Object> map = new HashMap<>();
        try {
            int num = roleService.deleteById(id);
            blogResponse.setReturnCode(ConstantException.SUCCESSCODE);
            blogResponse.setReturnMessage("根据id删除角色对象成功");
            blogResponse.setData(map);
            log.info("根据id[{}]删除角色对象成功,记录数[{}]",id,num);
        }catch (Exception e){
            blogResponse.setReturnCode(ConstantException.ERRORCODE);
            blogResponse.setReturnMessage("根据id删除角色对象失败");
            log.error("根据id[{}]删除角色对象失败",id,e);
        }
        return blogResponse;
    }
}
