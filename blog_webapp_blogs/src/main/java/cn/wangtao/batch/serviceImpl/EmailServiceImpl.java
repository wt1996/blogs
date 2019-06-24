package cn.wangtao.batch.serviceImpl;

import cn.wangtao.batch.service.EmailService;
import cn.wangtao.batch.service.ErrorBatchLogService;
import cn.wangtao.blogs.service.SysNotifyService;
import cn.wangtao.pojo.cms.ErrorBatchLog;
import cn.wangtao.pojo.cms.SysNotify;
import cn.wangtao.utils.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName EmailServiceUtils
 * @Auth 桃子
 * @Date 2019-6-19 10:58
 * @Version 1.0
 * @Description
 **/

@Slf4j
@Service
@Transactional
public class EmailServiceImpl implements EmailService {
    @Resource
    private JavaMailSender javaMailSender;

    @Autowired
    private ErrorBatchLogService errorBatchLogService;

    @Autowired
    private SysNotifyService notifyService;

    @Value("${spring.mail.username}")
    private String sendEmail;

    @Value("${spring.admin.email}")
    private String adminEmail;

    public void sendMail(List<String> emailList, List<SysNotify> sysNotifyList) {
        log.info("开始发送系统通知emailList:[{}]",emailList);
        log.info("sysNotifyList:[{}]",sysNotifyList);
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        //谁发的
        mailMessage.setFrom(sendEmail);
        for (SysNotify sysNotify:sysNotifyList){
            //主题
            mailMessage.setSubject(sysNotify.getNotifyTopic());
            //邮件内容
            mailMessage.setText(sysNotify.getNotifyMessage());
            //发给谁
            for (String email:emailList){
                mailMessage.setTo(email);
                try{
                    javaMailSender.send(mailMessage);
                    //int a= 2/0;
                }catch (Exception e){
                    //记录通知失败的数据记录到数据库中
                    ErrorBatchLog errorBatchLog = new ErrorBatchLog();
                    errorBatchLog.setNotifyMessage(sysNotify.getNotifyMessage());
                    errorBatchLog.setNotifyTopic(sysNotify.getNotifyTopic());
                    errorBatchLog.setEmail(email);
                    errorBatchLog.setNotifySeq(sysNotify.getNotifySeq());
                    errorBatchLog.setErrorMessage(e.getMessage());
                    log.error("通知发生失败记录[{}]",errorBatchLog,e);
                    try {
                        this.errorBatchLogService.insert(errorBatchLog);
                        log.error("添加失败通知记录[{}]成功",errorBatchLog);
                        //通知到手机上（暂定是发送邮件）

                    } catch (Exception e1) {
                        log.error("添加失败通知记录[{}]异常",errorBatchLog,e1);
                    }
                }
            }
            //修改数据库的通知状态
            sysNotify.setNotifyStatus(Constants.NOTIFY_FINISHED);//表示已通知
            sysNotify.setNotifyResultMes("触发通知成功");
            log.info("[{}]触发通知成功",sysNotify);
            try {
                this.notifyService.updateForNotify(sysNotify);
                log.info("修改通知记录的状态成功");
            } catch (Exception e) {
                log.error("修改通知记录的状态失败",e);
                //通知到手机上（暂定是发送邮件）



            }
        }
    }


    //给失败的记录重新发送
    public void replySendMail(List<ErrorBatchLog> list) {
        log.info("重发失败的通知记录[{}]",list);
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        //谁发的
        mailMessage.setFrom(sendEmail);
        for (ErrorBatchLog errorBatchLog:list){
            mailMessage.setTo(errorBatchLog.getEmail());
            mailMessage.setSubject(errorBatchLog.getNotifyTopic());
            mailMessage.setText(errorBatchLog.getNotifyMessage());
            try{
                javaMailSender.send(mailMessage);
                //删除记录
                this.errorBatchLogService.deleteById(errorBatchLog.getLogSeq());
            }catch (Exception e){
                log.error("重新发送失败通知异常[{}]",errorBatchLog,e);
                //发送短信通知（暂定是发送邮件）


            }
        }
    }

}


