package cn.wangtao.pojo.cms;

import com.sun.javafx.beans.IDProperty;
import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @ClassName ErrorBatchLog
 * @Auth 桃子
 * @Date 2019-6-19 14:01
 * @Version 1.0
 * @Description
 **/
@Data
@Table(name="error_batch_log")
public class ErrorBatchLog {

    //ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long logSeq;

    //通知的ID
    private Long notifySeq;

    //通知内容
    private String notifyMessage;

    //通知主题
    private String notifyTopic;

    //通知的状态
    private Character notifyStatus;

    //发送的失败的邮箱
    private String email;

    //创建的时间
    private Date createDate;

    //错误信息
    private String errorMessage;
}
