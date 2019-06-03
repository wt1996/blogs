package cn.wangtao.ResponseEntity;

import lombok.Data;

import java.io.Serializable;
import java.util.Map;

/**
 * @ClassName BlogResponse
 * @Auth 桃子
 * @Date 2019-5-14 11:12
 * @Version 1.0
 **/
@Data
public class BlogResponse implements Serializable {
    /**
     * 返回状态码
     */
    private String returnCode;
    /**
     * 返回状态信息
     */
    private String returnMessage;
    /**
     * 返回实体类
     */
    private Map<String,Object> data;
}
