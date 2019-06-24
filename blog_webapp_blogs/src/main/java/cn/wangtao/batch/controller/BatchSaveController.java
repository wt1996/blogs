package cn.wangtao.batch.controller;

import cn.wangtao.ResponseEntity.BlogResponse;
import cn.wangtao.batch.quartz.EmailBatchQuartz;
import cn.wangtao.blogs.controller.batch_controller.BatchSaveControllerApi;
import cn.wangtao.exception.ConstantException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @ClassName BatchSaveController
 * @Auth 桃子
 * @Date 2019-6-19 15:57
 * @Version 1.0
 * @Description  批量的补救措施类
 **/
@Controller
@Slf4j
@RequestMapping("save")
public class BatchSaveController implements BatchSaveControllerApi {

    @Autowired
    private EmailBatchQuartz emailBatchQuartz;

    //手动触发系统通知
    @GetMapping("sendEmail")
    @ResponseBody
    public BlogResponse sendEmail(){
        log.info("手动触发系统通知开始");
        BlogResponse blogResponse = new BlogResponse();
        try{
            this.emailBatchQuartz.sendSysEmail();
            blogResponse.setReturnCode(ConstantException.SUCCESSCODE);
            blogResponse.setReturnMessage("手动触发系统通知成功");
            log.info("手动触发系统通知成功");
        }catch (Exception e){
            blogResponse.setReturnCode(ConstantException.ERRORCODE);
            blogResponse.setReturnMessage("手动触发系统通知失败");
            log.error("手动触发系统通知失败",e);
        }
        return blogResponse;
    }

    //手动触发重新通知失败的记录
    @GetMapping("replySendMail")
    @ResponseBody
    public BlogResponse replySendMail(){
        log.info("手动触发重新通知失败的记录开始");
        BlogResponse blogResponse = new BlogResponse();
        try{
            this.emailBatchQuartz.replySendMail();
            blogResponse.setReturnCode(ConstantException.SUCCESSCODE);
            blogResponse.setReturnMessage("手动触发重新通知失败的记录成功");
            log.info("手动触发重新通知失败的记录成功");
        }catch (Exception e){
            blogResponse.setReturnCode(ConstantException.ERRORCODE);
            blogResponse.setReturnMessage("手动触发重新通知失败的记录失败");
            log.error("手动触发重新通知失败的记录失败",e);
        }
        return blogResponse;
    }
}
