package cn.wangtao.batch.controller;

import cn.wangtao.ResponseEntity.BlogResponse;
import cn.wangtao.batch.service.ErrorBatchLogService;
import cn.wangtao.blogs.controller.batch_controller.ErrorBatchLogControllerApi;
import cn.wangtao.exception.ConstantException;
import cn.wangtao.pojo.cms.ErrorBatchLog;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

/**
 * @ClassName ErrorBatchLogController
 * @Auth 桃子
 * @Date 2019-6-19 15:40
 * @Version 1.0
 * @Description   删除 增加 查询所有 根据ID查询
 **/
@Slf4j
@Controller
@RequestMapping("errorBatch")
public class ErrorBatchLogController implements ErrorBatchLogControllerApi {

    @Autowired
    private ErrorBatchLogService errorBatchLogService;

    //查询所有
    @GetMapping("selectAll")
    @ResponseBody
    public BlogResponse selectAll() {
        BlogResponse blogResponse = new BlogResponse();
        try{
            List<ErrorBatchLog> errorBatchList= this.errorBatchLogService.selectAll();
            HashMap<String, Object> map = new HashMap<>();
            map.put("errorBatchList",errorBatchList);
            blogResponse.setData(map);
            blogResponse.setReturnCode(ConstantException.SUCCESSCODE);
            blogResponse.setReturnMessage("查询所有通知失败记录成功");
            log.info("查询所有通知失败记录成功[{}]",errorBatchList);
        }catch (Exception e){
            blogResponse.setReturnCode(ConstantException.ERRORCODE);
            blogResponse.setReturnMessage("查询所有通知失败记录失败");
            log.error("查询所有通知失败记录失败，代码出现异常",e);
        }
        return blogResponse;
    }

    //根据ID查询
    @GetMapping("selectById")
    @ResponseBody
    public BlogResponse selectById(@RequestParam("id") Long id) {
        log.info("根据id查询通知失败记录接受到参数id：[{}]",id);
        BlogResponse blogResponse = new BlogResponse();
        //参数校验
        if(StringUtils.isEmpty(id)){
            blogResponse.setReturnCode(ConstantException.ERRORCODE);
            blogResponse.setReturnMessage("查询通知失败记录失败，请求参数不合法");
            log.error("查询通知失败记录失败，ID不能为空");
            return blogResponse;
        }
        //执行查询
        try {
            ErrorBatchLog errorBatchLog =this.errorBatchLogService.selectById(id);
            HashMap<String, Object> map = new HashMap<>();
            map.put("errorBatchLog",errorBatchLog);
            blogResponse.setReturnCode(ConstantException.SUCCESSCODE);
            blogResponse.setReturnMessage("查询通知失败记录成功");
            blogResponse.setData(map);
            log.info("根据ID[{}]查询通知失败记录成功[{}]",id,errorBatchLog);
        }catch (Exception e){
            blogResponse.setReturnCode(ConstantException.ERRORCODE);
            blogResponse.setReturnMessage("查询博客对象失败");
            log.error("根据主博客ID[{}]查询通知失败记录失败",id,e);
        }
        return blogResponse;
    }

    //增加
    @PostMapping("insert")
    @ResponseBody
    public BlogResponse insert(ErrorBatchLog errorBatchLog) {
        log.info("添加通知失败记录接受到参数ErrorBatchLog：[{}]",errorBatchLog);
        BlogResponse blogResponse = new BlogResponse();
        //参数校验
        if(StringUtils.isEmpty(errorBatchLog.getEmail())
                ||StringUtils.isEmpty(errorBatchLog.getNotifyMessage())
                ||StringUtils.isEmpty(errorBatchLog.getNotifySeq())){
            blogResponse.setReturnCode(ConstantException.ERRORCODE);
            blogResponse.setReturnMessage("增加通知失败记录失败，请求参数不合法");
            log.error("增加通知失败记录失败，请求参数不合法");
            return blogResponse;
        }
        //执行增加
        try{
            int num = this.errorBatchLogService.insert(errorBatchLog);
            HashMap<String, Object> map = new HashMap<>();
            map.put("errorBatchLog",errorBatchLog);
            blogResponse.setReturnCode(ConstantException.SUCCESSCODE);
            blogResponse.setReturnMessage("添加通知失败记录成功");
            blogResponse.setData(map);
            log.info("添加通知失败记录成功[{}]记录数[{}]",errorBatchLog,num);
        }catch (Exception e){
            blogResponse.setReturnCode(ConstantException.ERRORCODE);
            blogResponse.setReturnMessage("添加通知失败记录失败");
            log.error("添加通知失败记录失败[{}],代码出现异常",errorBatchLog,e);
        }
        return blogResponse;
    }

    //删除
    @GetMapping("deleteById")
    @ResponseBody
    public BlogResponse deleteById(@RequestParam("id") Long id) {
        log.info("根据id删除通知失败记录对象接受到参数id:[{}]",id);
        BlogResponse blogResponse = new BlogResponse();
        //参数校验
        if(StringUtils.isEmpty(id)){
            blogResponse.setReturnCode(ConstantException.ERRORCODE);
            blogResponse.setReturnMessage("删除通知失败记录失败，请求参数不合法");
            log.error("删除通知失败记录失败，ID不能为空");
            return blogResponse;
        }
        try {
            int num=this.errorBatchLogService.deleteById(id);
            blogResponse.setReturnCode(ConstantException.SUCCESSCODE);
            blogResponse.setReturnMessage("根据id删除通知失败记录对象成功");
            log.info("根据id[{}]删除通知失败记录对象成功,记录数[{}]",id,num);
        }catch (Exception e){
            blogResponse.setReturnCode(ConstantException.ERRORCODE);
            blogResponse.setReturnMessage("根据id删除通知失败记录对象失败");
            log.error("根据id[{}]删除通知失败记录对象失败，代码出现异常",id,e);
        }
        return blogResponse;
    }


}
