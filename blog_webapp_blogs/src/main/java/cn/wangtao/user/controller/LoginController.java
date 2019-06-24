package cn.wangtao.user.controller;

import cn.wangtao.ResponseEntity.BlogResponse;
import cn.wangtao.blogs.controller.user_controller.LoginControllerApi;
import cn.wangtao.exception.ConstantException;
import cn.wangtao.pojo.user.SysUser;
import cn.wangtao.user.service.SysUserService;
import cn.wangtao.utils.Constants;
import cn.wangtao.utils.SecurityUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;

/**
 * @ClassName LoginControllerHandle
 * @Auth 桃子
 * @Date 2019-5-13 15:24
 * @Version 1.0
 **/
@Controller
@RequestMapping("user")
@Slf4j
public class LoginController  implements LoginControllerApi {

    @Autowired
    private SysUserService sysUserService;

    @PostMapping("login")
    @ResponseBody
    public BlogResponse login(@RequestParam("userName") String userName, @RequestParam("password")String password, HttpServletRequest request) {
        log.info("[{}]请求登录，登录密码[{}]",userName,password);
        BlogResponse blogResponse=new BlogResponse();
        HashMap<String, Object> map = new HashMap<>();
        //参数校验
        if(StringUtils.isEmpty(userName)||StringUtils.isEmpty(password)){
            log.error("请求参数不合法");
            blogResponse.setReturnCode(ConstantException.ERRORCODE);
            blogResponse.setReturnMessage("登录失败，请求参数不合法");
            return blogResponse;
        }
        userName=userName.trim();
        password=password.trim();
        //判断Session中是否存在
        HttpSession session = request.getSession();
        SysUser sysUser = (SysUser)session.getAttribute("user");
        if(sysUser!=null){
            blogResponse.setReturnCode(ConstantException.SUCCESSCODE);
            blogResponse.setReturnMessage("登录成功");
            map.put("userName",sysUser.getUserName());
            blogResponse.setData(map);
            return blogResponse;
        }
        try {
            sysUser = sysUserService.selectByName(userName);
        } catch (Exception e) {
            log.error("根据名字查询用户异常",e);
            blogResponse.setReturnCode(ConstantException.ERRORCODE);
            blogResponse.setReturnMessage("网络异常请重试");
            return blogResponse;
        }

        //判断用户是否存在
        if(sysUser==null){
            blogResponse.setReturnCode(ConstantException.ERRORCODE);
            blogResponse.setReturnMessage("登录失败,用户不存在");
            return blogResponse;
        }
        try {
            //判断状态是否可用 0 正常，1 禁用
            if(Constants.STATUS__NORMAL != sysUser.getSysUserStatus()){
                blogResponse.setReturnCode(ConstantException.ERRORCODE);
                blogResponse.setReturnMessage("用户状态异常，请重试或联系管理员");
                return blogResponse;
            }
            if(SecurityUtil.verify(password, userName, sysUser.getPassword())) {
                blogResponse.setReturnCode(ConstantException.SUCCESSCODE);
                blogResponse.setReturnMessage("登录成功");
                map.put("userName",sysUser.getUserName());
                blogResponse.setData(map);
                session.setAttribute("user",sysUser);
            }else{
                blogResponse.setReturnCode(ConstantException.ERRORCODE);
                blogResponse.setReturnMessage("登录失败，用户名或密码错误");
            }
        } catch (Exception e) {
            log.error("密码验证时异常",e);
            blogResponse.setReturnCode(ConstantException.ERRORCODE);
            blogResponse.setReturnMessage("网络异常请重试");
        }
        return blogResponse;
    }
}
