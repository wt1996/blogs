package cn.wangtao.user.controller;

import cn.wangtao.ResponseEntity.BlogResponse;
import cn.wangtao.blogs.controller.user_controller.SysRoleAccessControlControllerApi;
import cn.wangtao.exception.ConstantException;
import cn.wangtao.pojo.user.SysRoleAccessControl;
import cn.wangtao.pojo.user.SysUser;
import cn.wangtao.user.service.SysRoleAccessControlService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

/**
 * @ClassName SysRoleAccessControlController
 * @Auth 桃子
 * @Date 2019-5-30 15:59
 * @Version 1.0
 * @Description
 **/
@Controller
@RequestMapping("roleAccessControl")
@Slf4j
public class SysRoleAccessControlController implements SysRoleAccessControlControllerApi {

    @Autowired
    private SysRoleAccessControlService roleAccessControlService;

    @Override
    @PostMapping("insert")
    @ResponseBody
    public BlogResponse insert(SysRoleAccessControl roleAccessControl, HttpServletRequest request) {
        log.info("添加角色权限接受到参数：[{}]",roleAccessControl);
        BlogResponse blogResponse = new BlogResponse();
        HashMap<String, Object> map = new HashMap<>();
        SysUser sysUser = (SysUser)request.getSession().getAttribute("user");
        if(sysUser!=null){
            roleAccessControl.setCreateByName(sysUser.getUserName());//从Session中取
        }
        try{
            int num = roleAccessControlService.insert(roleAccessControl);
            map.put("roleAccessControl",roleAccessControl);
            blogResponse.setReturnCode(ConstantException.SUCCESSCODE);
            blogResponse.setReturnMessage("添加角色权限成功");
            blogResponse.setData(map);
            log.info("添加角色权限成功[{}]记录数[{}]",roleAccessControl,num);
        }catch (Exception e){
            blogResponse.setReturnCode(ConstantException.ERRORCODE);
            blogResponse.setReturnMessage("添加角色权限失败");
            log.error("添加角色权限失败[{}]，代码出现异常",roleAccessControl,e);
        }
        return blogResponse;
    }

    @Override
    @PostMapping("delete")
    @ResponseBody
    public BlogResponse deleteByTwoId(@RequestParam("sysRoleSeq") Long sysRoleSeq, @RequestParam("sysAccessControlSeq") Long sysAccessControlSeq) {
        log.info("根据sysRoleSeq：[{}], sysAccessControlSeq: [{}]删除角色权限开始",sysRoleSeq,sysAccessControlSeq);
        BlogResponse blogResponse = new BlogResponse();
        try {
            int num = roleAccessControlService.deleteByTwoId(sysRoleSeq, sysAccessControlSeq);
            blogResponse.setReturnCode(ConstantException.SUCCESSCODE);
            blogResponse.setReturnMessage("根据id删除角色权限成功");
            log.info("根据sysRoleSeq[{}], sysAccessControlSeq:[{}]删除角色权限成功,记录数[{}]",sysRoleSeq,sysAccessControlSeq,num);
        }catch (Exception e){
            blogResponse.setReturnCode(ConstantException.ERRORCODE);
            blogResponse.setReturnMessage("根据id删除角色权限失败");
            log.error("根据sysRoleSeq：[{}], sysAccessControlSeq: [{}]删除角色权限失败，代码出现异常",sysRoleSeq,sysAccessControlSeq,e);
        }
        return blogResponse;
    }
}
