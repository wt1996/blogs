package cn.wangtao.batch.service;

import cn.wangtao.pojo.cms.ErrorBatchLog;
import cn.wangtao.pojo.cms.SysNotify;

import java.util.List;

/**
 * @ClassName EmailService
 * @Auth 桃子
 * @Date 2019-6-19 14:21
 * @Version 1.0
 **/
public interface EmailService {
    void sendMail(List<String> emailList, List<SysNotify> sysNotifyList) throws Exception;
    void replySendMail(List<ErrorBatchLog> list)throws Exception;
}
