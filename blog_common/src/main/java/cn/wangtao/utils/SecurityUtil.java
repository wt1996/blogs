package cn.wangtao.utils;

import org.springframework.util.DigestUtils;

import java.io.UnsupportedEncodingException;

/**
 * @ClassName SecurityUtil
 * @Auth 桃子
 * @Date 2019-5-28 14:30
 * @Version 1.0
 * @Description
 **/
public class SecurityUtil {

    //Md5加密
    public static String md5Encrypt(String source,String salt) throws UnsupportedEncodingException {
        //为了避免每次是一致的

        return DigestUtils.md5DigestAsHex((source+salt).getBytes("UTF-8"));
    }

    //判断是否相同
    public static boolean verify(String source, String salt, String md5) throws Exception {
        String md5Encrypt = md5Encrypt(source, salt);
        return md5.equals(md5Encrypt);
    }
}
