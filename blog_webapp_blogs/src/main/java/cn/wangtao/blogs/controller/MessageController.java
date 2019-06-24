package cn.wangtao.blogs.controller;

import cn.wangtao.ResponseEntity.BlogResponse;
import cn.wangtao.blogs.controller.blog_controller.MessageControllerApi;
import cn.wangtao.exception.ConstantException;
import cn.wangtao.pojo.cms.Message;
import cn.wangtao.pojo.user.SysUser;
import cn.wangtao.blogs.service.MessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName MessageController
 * @Auth 桃子
 * @Date 2019-6-17 15:47
 * @Version 1.0
 * @Description  查询当前用户所有未读消息数量，
 *              分类查询当前用户所有未读消息数量，
 *              根据类别查询当前用户所有的消息信息(未读与已读)
 *              只能查询当前用户的信息
 **/
@Slf4j
@Controller
@RequestMapping("message")
public class MessageController implements MessageControllerApi {

    @Autowired
    private MessageService messageService;

    //分类查询当前所有未读消息数量
    @GetMapping("selectNumGroupByType")
    @ResponseBody
    public BlogResponse selectNumGroupByType(HttpServletRequest request){
        log.info("分类查询当前用户所有未读消息数量");
        BlogResponse blogResponse = new BlogResponse();
        try {
            SysUser currentUser = (SysUser)request.getSession().getAttribute("user");
            List<Map<String,Integer>> numList =this.messageService.selectNumGroupByType(currentUser.getUserName());
            blogResponse.setReturnCode(ConstantException.SUCCESSCODE);
            blogResponse.setReturnMessage("分类查询用户所有未读消息数量成功");
            Map<String,Object> map=new HashMap();
            map.put("numList",numList);
            map.put("userName",currentUser.getUserName());
            blogResponse.setData(map);
            log.info("分类查询用户[{}]所有未读消息数量成功,Map:[{}]",currentUser.getUserName(),map);
        }catch (Exception e){
            blogResponse.setReturnCode(ConstantException.ERRORCODE);
            blogResponse.setReturnMessage("分类查询用户所有未读消息数量失败");
            log.error("分类查询用户所有未读消息数量失败，代码出现异常",e);
        }
        return blogResponse;
    }

    //查询当前用户所有未读消息数量
    @GetMapping("selectNumByNo")
    @ResponseBody
    public BlogResponse selectNumByNo(HttpServletRequest request){
        log.info("查询当前用户所有未读消息数量开始");
        BlogResponse blogResponse = new BlogResponse();
        try {
            SysUser currentUser = (SysUser)request.getSession().getAttribute("user");
            int num=this.messageService.selectNumByNo(currentUser.getUserName());
            Map<String,Object> map=new HashMap<>();
            map.put("num",num);
            blogResponse.setReturnCode(ConstantException.SUCCESSCODE);
            blogResponse.setReturnMessage("查询用户所有未读消息数量成功");
            blogResponse.setData(map);
            log.info("查询用户[{}]所有未读消息数量成功,num:[{}]",currentUser.getUserName(),num);
        }catch (Exception e){
            blogResponse.setReturnCode(ConstantException.ERRORCODE);
            blogResponse.setReturnMessage("查询用户所有未读消息数量失败");
            log.error("查询用户所有未读消息数量失败，代码出现异常",e);
        }
        return blogResponse;
    }

    //根据类别查询用户所有的消息信息
    @GetMapping("selectAllByType")
    @ResponseBody
    public BlogResponse selectAllByType(@RequestParam("messageType") char messageType, HttpServletRequest request){
        log.info("根据类别查询用户所有的消息信息接受到参数messageType:[{}]",messageType);
        BlogResponse blogResponse = new BlogResponse();
        if(StringUtils.isEmpty(messageType)){
            blogResponse.setReturnCode(ConstantException.ERRORCODE);
            blogResponse.setReturnMessage("根据类别查询用户所有的消息信息失败，请求参数不合法");
            log.error("根据类别查询用户所有的消息信息失败，消息类别不能为空");
            return blogResponse;
        }
        try {
            SysUser currentUser = (SysUser)request.getSession().getAttribute("user");
            List<Message> messageList = this.messageService.selectAllByType(currentUser.getUserName(), messageType);
            Map<String,Object> map=new HashMap<>();
            map.put("messageList",messageList);
            blogResponse.setReturnCode(ConstantException.SUCCESSCODE);
            blogResponse.setReturnMessage("根据类别查询用户所有的消息信息成功");
            blogResponse.setData(map);
            log.info("根据类别查询用户[{}]所有的消息信息成功,messageList:[{}]",currentUser.getUserName(),messageList);
        }catch (Exception e){
            blogResponse.setReturnCode(ConstantException.ERRORCODE);
            blogResponse.setReturnMessage("查询用户所有未读消息数量失败");
            log.error("根据类别查询用户所有的消息信息失败，代码出现异常",e);
        }
        return blogResponse;
    }


    //根据ID进行查询消息信息
    @GetMapping("selectById")
    @ResponseBody
    public BlogResponse selectById(@RequestParam("id") Long id,HttpServletRequest request) {
        log.info("根据id查询消息详细信息接受到参数id：[{}]",id);
        BlogResponse blogResponse = new BlogResponse();
        HashMap<String, Object> map = new HashMap<>();
        //参数校验
        if(StringUtils.isEmpty(id)){
            blogResponse.setReturnCode(ConstantException.ERRORCODE);
            blogResponse.setReturnMessage("查询消息详细信息失败，请求参数不合法");
            log.error("根据id查询消息详细信息失败，主博客ID不能为空");
            return blogResponse;
        }
        //执行查询
        try {
            Message message =this.messageService.selectById(id);
            map.put("message",message);
            blogResponse.setReturnCode(ConstantException.SUCCESSCODE);
            blogResponse.setReturnMessage("查询消息详细信息成功");
            blogResponse.setData(map);
            log.info("根据ID[{}]查询消息详细信息成功[{}]",id,message);
        }catch (Exception e){
            blogResponse.setReturnCode(ConstantException.ERRORCODE);
            blogResponse.setReturnMessage("查询消息详细信息失败");
            log.error("根据ID[{}]查询消息详细信息失败",id,e);
        }
        return blogResponse;
    }



}
