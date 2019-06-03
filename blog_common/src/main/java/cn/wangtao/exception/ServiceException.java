package cn.wangtao.exception;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @ClassName ServiceException
 * @Auth 桃子
 * @Date 2019-5-14 11:12
 * @Version 1.0
 **/
public class ServiceException extends RuntimeException {

    private Logger logger = LoggerFactory.getLogger(ServiceException.class);
    public ServiceException(String message,Throwable throwable){
        super(message,throwable);
        this.logger.error("代码出现错误信息：[{}]，原因是：[{}]",message,throwable );
    }

    public ServiceException() {
    }
    public ServiceException(String message,String cause){
        this.logger.error("代码出现错误信息：[{}]，原因是：[{}]",message,cause );
    }
    public ServiceException(String message,String cause,Throwable throwable){
        super(message,throwable);
        this.logger.error("代码出现错误信息：[{}]，原因是：[{}]，具体是[{}]",message,cause,throwable );
    }
}
