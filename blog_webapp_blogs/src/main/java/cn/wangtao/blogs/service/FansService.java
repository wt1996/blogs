package cn.wangtao.blogs.service;

import cn.wangtao.baseEntity.BaseServiceEntity;
import cn.wangtao.pojo.cms.Fans;

import java.util.List;
import java.util.Map;

/**
 * @ClassName FansService
 * @Auth 桃子
 * @Date 2019-6-17 10:18
 * @Version 1.0
 * @Description
 **/
public abstract class FansService extends BaseServiceEntity<Fans,Long> {

    //查询自己的所有粉丝详细
    public abstract List<Fans> selectFans(String authorName) throws Exception;

    //查询自己关注了谁详细
    public abstract List<Fans> selectFocus(String userName) throws  Exception;

    //根据ID 查询这个人的粉丝数与关注数
    public abstract Map<String,Integer> selectTwoFansNum(String userName) throws Exception;


    public abstract int cancelByName(String authorName,String currentName)throws Exception;

    public abstract Fans selectByTwoName(String authorName, String currentName)throws Exception;
}
