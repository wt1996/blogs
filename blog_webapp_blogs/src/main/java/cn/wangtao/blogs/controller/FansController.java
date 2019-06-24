package cn.wangtao.blogs.controller;

import cn.wangtao.ResponseEntity.BlogResponse;
import cn.wangtao.blogs.controller.blog_controller.FansControllerApi;
import cn.wangtao.exception.ConstantException;
import cn.wangtao.pojo.cms.Fans;
import cn.wangtao.pojo.user.SysUser;
import cn.wangtao.blogs.service.FansService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName FansController
 * @Auth 桃子
 * @Date 2019-6-17 15:44
 * @Version 1.0
 * @Description  添加 删除 (统计有多少粉丝，统计关注多少人)，粉丝的详细查询， 关注的详细查询,根据博主名查是否关注
 **/
@Slf4j
@Controller
@RequestMapping("fans")
public class FansController implements FansControllerApi {

    @Autowired
    private FansService fansService;

    //添加关注
    @PostMapping("insert")
    @ResponseBody
    public BlogResponse insert(Fans fans, HttpServletRequest request) {
        log.info("添加关注对象接受到参数Fans:[{}]",fans);
        BlogResponse blogResponse = new BlogResponse();
        HashMap<String, Object> map = new HashMap<>();
        //参数校验
        if(StringUtils.isEmpty(fans.getAuthorName())){
            blogResponse.setReturnCode(ConstantException.ERRORCODE);
            blogResponse.setReturnMessage("增加关注对象失败，请求参数不合法");
            log.error("增加关注对象失败，用户信息不能为空");
            return blogResponse;
        }
        //执行增加
        try{
            SysUser currentUser = (SysUser)request.getSession().getAttribute("user");
            fans.setUserName(currentUser.getUserName());//从Session中取
            int num = this.fansService.insert(fans);
            map.put("fans",fans);
            blogResponse.setReturnCode(ConstantException.SUCCESSCODE);
            blogResponse.setReturnMessage("添加关注对象成功");
            blogResponse.setData(map);
            log.info("添加关注对象成功[{}]记录数[{}]",fans,num);
        }catch (Exception e){
            blogResponse.setReturnCode(ConstantException.ERRORCODE);
            blogResponse.setReturnMessage("添加关注对象失败");
            log.error("添加关注对象失败[{}],代码出现异常",fans,e);
        }
        return blogResponse;
    }

    //取消关注
    @GetMapping("cancelByName")
    @ResponseBody
    public BlogResponse cancelByName(@RequestParam("authorName") String authorName,HttpServletRequest request) {
        log.info("取消关注对象接受到参数authorName:[{}]",authorName);
        BlogResponse blogResponse = new BlogResponse();
        //参数校验
        if(StringUtils.isEmpty(authorName)){
            blogResponse.setReturnCode(ConstantException.ERRORCODE);
            blogResponse.setReturnMessage("取消关注失败，请求参数不合法");
            log.error("取消关注失败，博主名不能为空");
            return blogResponse;
        }
        try {
            SysUser currentUser = (SysUser)request.getSession().getAttribute("user");
            int num=this.fansService.cancelByName(authorName,currentUser.getUserName());
            blogResponse.setReturnCode(ConstantException.SUCCESSCODE);
            blogResponse.setReturnMessage("取消关注对象成功");
            log.info("取消关注对象[{}]成功,记录数[{}]",authorName,num);
        }catch (Exception e){
            blogResponse.setReturnCode(ConstantException.ERRORCODE);
            blogResponse.setReturnMessage("取消关注对象失败");
            log.error("取消关注对象[{}]失败，代码出现异常",authorName,e);
        }
        return blogResponse;
    }

    //判断是否有关注该对象
    @GetMapping("ifFocus")
    @ResponseBody
    public BlogResponse ifFocus(@RequestParam("authorName") String authorName,HttpServletRequest request) {
        log.info("判断是否关注对象接受到参数authorName:[{}]",authorName);
        BlogResponse blogResponse = new BlogResponse();
        Map<String,Object> map=new HashMap<String,Object>();
        //参数校验
        if(StringUtils.isEmpty(authorName)){
            blogResponse.setReturnCode(ConstantException.ERRORCODE);
            blogResponse.setReturnMessage("判断是否关注对象失败，请求参数不合法");
            log.error("判断是否关注对象失败，博主名不能为空");
            return blogResponse;
        }
        try {
            SysUser currentUser = (SysUser)request.getSession().getAttribute("user");
            Fans fans=this.fansService.selectByTwoName(authorName,currentUser.getUserName());
            if(fans==null){
                blogResponse.setReturnCode(ConstantException.ERRORCODE);
                blogResponse.setReturnMessage("未关注该对象");
                map.put("ifFocus",false);
                blogResponse.setData(map);
                log.info("未关注该对象[{}]成功Fans:[{}]",authorName,fans);
            }else{
                blogResponse.setReturnCode(ConstantException.SUCCESSCODE);
                blogResponse.setReturnMessage("已关注该对象");
                map.put("ifFocus",true);
                blogResponse.setData(map);
                log.info("已关注该对象[{}]成功Fans:[{}]",authorName,fans);
            }
        }catch (Exception e){
            blogResponse.setReturnCode(ConstantException.ERRORCODE);
            blogResponse.setReturnMessage("判断是否关注对象失败");
            log.error("判断是否关注对象[{}]失败，代码出现异常",authorName,e);
        }
        return blogResponse;
    }

    //查询所有的粉丝数与关注数
    @GetMapping("selectNums")
    @ResponseBody
    public BlogResponse selectNums(@RequestParam("userName") String userName){
        log.info("查询用户关注的对象数与粉丝数接受到参数userName:[{}]",userName);
        BlogResponse blogResponse = new BlogResponse();
        if(StringUtils.isEmpty(userName)){
            blogResponse.setReturnCode(ConstantException.ERRORCODE);
            blogResponse.setReturnMessage("查询用户关注的对象数与粉丝数失败，请求参数不合法");
            log.error("查询用户关注的对象数与粉丝数失败，用户名不能为空");
            return blogResponse;
        }
        try {
            Map map=this.fansService.selectTwoFansNum(userName);
            map.put("userName",userName);
            blogResponse.setReturnCode(ConstantException.SUCCESSCODE);
            blogResponse.setReturnMessage("查询用户关注的对象数与粉丝数成功");
            blogResponse.setData(map);
            log.info("查询用户[{}]关注的对象数与粉丝数成功,Map:[{}]",userName,map);
        }catch (Exception e){
            blogResponse.setReturnCode(ConstantException.ERRORCODE);
            blogResponse.setReturnMessage("查询用户关注的对象数与粉丝数失败");
            log.error("查询用户[{}]关注的对象数与粉丝数失败，代码出现异常",userName,e);
        }
        return blogResponse;
    }

    //粉丝的详细查询
    @GetMapping("selectFans")
    @ResponseBody
    public BlogResponse selectFans(@RequestParam("userName") String userName){
        log.info("查询用户的粉丝集合信息接受到参数userName:[{}]",userName);
        BlogResponse blogResponse = new BlogResponse();
        if(StringUtils.isEmpty(userName)){
            blogResponse.setReturnCode(ConstantException.ERRORCODE);
            blogResponse.setReturnMessage("查询用户的粉丝信息失败，请求参数不合法");
            log.error("查询用户的粉丝集合信息失败，用户名不能为空");
            return blogResponse;
        }
        try {
            List<Fans> fansList = this.fansService.selectFans(userName);
            Map<String,Object> map=new HashMap<>();
            blogResponse.setReturnCode(ConstantException.SUCCESSCODE);
            blogResponse.setReturnMessage("查询用户的粉丝信息成功");
            map.put("fansList",fansList);
            blogResponse.setData(map);
            log.info("查询用户[{}]的粉丝集合信息成功,fansList:[{}]",userName,fansList);
        }catch (Exception e){
            blogResponse.setReturnCode(ConstantException.ERRORCODE);
            blogResponse.setReturnMessage("查询用户的粉丝信息失败");
            log.error("查询用户[{}]的粉丝集合信息失败，代码出现异常",userName,e);
        }
        return blogResponse;
    }
    //关注的详细查询
    @GetMapping("selectFocus")
    @ResponseBody
    public BlogResponse selectFocus(@RequestParam("userName") String userName){
        log.info("查询用户的关注集合信息接受到参数userName:[{}]",userName);
        BlogResponse blogResponse = new BlogResponse();
        if(StringUtils.isEmpty(userName)){
            blogResponse.setReturnCode(ConstantException.ERRORCODE);
            blogResponse.setReturnMessage("查询用户的关注信息失败，请求参数不合法");
            log.error("查询用户的关注集合信息失败，用户名不能为空");
            return blogResponse;
        }
        try {
            List<Fans> fansList = this.fansService.selectFocus(userName);
            Map<String,Object> map=new HashMap<>();
            blogResponse.setReturnCode(ConstantException.SUCCESSCODE);
            blogResponse.setReturnMessage("查询用户的关注信息成功");
            map.put("fansList",fansList);
            blogResponse.setData(map);
            log.info("查询用户[{}]的关注集合信息成功,fansList:[{}]",userName,fansList);
        }catch (Exception e){
            blogResponse.setReturnCode(ConstantException.ERRORCODE);
            blogResponse.setReturnMessage("查询用户的关注信息失败");
            log.error("查询用户[{}]的关注集合信息失败，代码出现异常",userName,e);
        }
        return blogResponse;
    }
}
