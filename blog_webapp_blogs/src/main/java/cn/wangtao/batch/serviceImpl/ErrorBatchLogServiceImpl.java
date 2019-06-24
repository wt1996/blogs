package cn.wangtao.batch.serviceImpl;

import cn.wangtao.baseEntity.BaseMapperEntity;
import cn.wangtao.batch.service.ErrorBatchLogService;
import cn.wangtao.mapper.ErrorBatchLogMapper;
import cn.wangtao.pojo.cms.ErrorBatchLog;
import cn.wangtao.utils.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @ClassName ErrorBatchLogServiceImpl
 * @Auth 桃子
 * @Date 2019-6-19 14:15
 * @Version 1.0
 * @Description
 **/
@Slf4j
@Service
@Transactional
public class ErrorBatchLogServiceImpl extends ErrorBatchLogService {

    @Autowired
    private ErrorBatchLogMapper errorBatchLogMapper;

    @Override
    public BaseMapperEntity<ErrorBatchLog, Long> getMapper() throws Exception {
        return this.errorBatchLogMapper;
    }

    @Override
    public int insert(ErrorBatchLog errorBatchLog) throws Exception {
        errorBatchLog.setCreateDate(new Date());
        errorBatchLog.setNotifyStatus(Constants.NOTIFY_ERROR);//通知异常
        return this.errorBatchLogMapper.insert(errorBatchLog);
    }

    @Override
    public int update(ErrorBatchLog o) throws Exception {
        log.warn("出错通知记录表没有修改操作");
        return 0;
    }
}
