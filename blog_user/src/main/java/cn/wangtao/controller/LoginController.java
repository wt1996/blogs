package cn.wangtao.controller;

import cn.wangtao.ResponseEntity.BlogResponse;
import cn.wangtao.controller.user_controller.LoginControllerApi;
import cn.wangtao.exception.ConstantException;
import cn.wangtao.pojo.user.SysUser;
import cn.wangtao.service.SysUserService;
import cn.wangtao.utils.Constants;
import cn.wangtao.utils.SecurityUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
@RequestMapping("/user")
@Slf4j
public class LoginController  implements LoginControllerApi {

    @Autowired
    private SysUserService sysUserService;

    @PostMapping("login")
    @ResponseBody
    public BlogResponse login(@RequestParam("userName") String userName, @RequestParam("password")String password, HttpServletRequest request) {
        BlogResponse blogResponse=new BlogResponse();
        HashMap<String, Object> map = new HashMap<>();
        //进行校验
        userName=userName.trim();
        password=password.trim();
        log.info("[{}]请求登录，登录密码[{}]",userName,password);
        //判断Session中是否存在
        HttpSession session = request.getSession();
        SysUser sysUser = (SysUser)session.getAttribute("user");
        if(sysUser!=null){
            blogResponse.setReturnCode(ConstantException.SUCCESSCODE);
            blogResponse.setReturnMessage("登录成功");
            //map.put("user",sysUser);
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
            //判断状态是否可用
            if(Constants.USER_SYSTEM != sysUser.getSysUserStatus()){
                blogResponse.setReturnCode(ConstantException.ERRORCODE);
                blogResponse.setReturnMessage("用户状态异常，请重试或联系管理员");
                return blogResponse;
            }
            if(SecurityUtil.verify(password, userName, sysUser.getPassword())) {
                blogResponse.setReturnCode(ConstantException.SUCCESSCODE);
                blogResponse.setReturnMessage("登录成功");
                //map.put("user",sysUser);
                blogResponse.setData(map);
                session.setAttribute("user",sysUser);
            }else{
                blogResponse.setReturnCode(ConstantException.ERRORCODE);
                blogResponse.setReturnMessage("登录失败，密码错误");
            }
        } catch (Exception e) {
            log.error("密码验证时异常",e);
            blogResponse.setReturnCode(ConstantException.ERRORCODE);
            blogResponse.setReturnMessage("网络异常请重试");
        }
        return blogResponse;
    }
}
