package cn.wangtao.blogs.service;

import cn.wangtao.baseEntity.BaseServiceEntity;
import cn.wangtao.pojo.cms.Message;

import java.util.List;
import java.util.Map;

/**
 * @ClassName MessageService
 * @Auth 桃子
 * @Date 2019-6-17 10:22
 * @Version 1.0
 * @Description
 **/
public  abstract class MessageService extends BaseServiceEntity<Message,Long> {

    //查询所有未读的消息，按照类别分组
    public abstract List<Map<String,Integer>> selectNumGroupByType(String currentUserName)throws Exception;

    //查询所有未读的数据
    public abstract int selectNumByNo(String currentUserName) throws  Exception;

    //根据类别查询所有的消息
    public abstract List<Message> selectAllByType(String currentUserName, Character messageType) throws  Exception;



}
