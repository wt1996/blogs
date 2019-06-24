package cn.wangtao.blogs.serviceImpl;

import cn.wangtao.baseEntity.BaseMapperEntity;
import cn.wangtao.mapper.MessageMapper;
import cn.wangtao.pojo.cms.Message;
import cn.wangtao.blogs.service.MessageService;
import cn.wangtao.utils.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @ClassName MessageServiceImpl
 * @Auth 桃子
 * @Date 2019-6-17 11:06
 * @Version 1.0
 * @Description
 **/
@Slf4j
@Transactional
@Service("messageServiceImpl")
public class MessageServiceImpl extends MessageService {

    @Autowired
    private MessageMapper messageMapper;

    @Override
    public BaseMapperEntity<Message, Long> getMapper() throws Exception {
        return  this.messageMapper;
    }

    @Override
    public int insert(Message message) throws Exception {
        message.setCreateDate(new Date());
        return this.messageMapper.insert(message);
    }

    @Override
    public int update(Message o) throws Exception {
        log.warn("消息通知没有修改功能");
        return 0;
    }

    @Override
    public  List<Map<String, Integer>> selectNumGroupByType(String currentUserName) throws Exception {
        return this.messageMapper.selectNumGroupByType(currentUserName);
    }

    @Override
    public int selectNumByNo(String currentUserName) throws Exception {
        return this.messageMapper.selectNumByNo(currentUserName);
    }

    @Override
    public List<Message> selectAllByType(String currentUserName, Character messageType) throws Exception {
        return this.messageMapper.selectAllByType(currentUserName,messageType);
    }

    //根据ID查询
    @Override
    public Message selectById(Long id) throws Exception{
        Message message =new Message();
        message.setMessageSeq(id);
        message.setMessageStatus(Constants.MESSAGESTATUS_YES);//表示已读
        this.messageMapper.updateByPrimaryKeySelective(message);
        return this.messageMapper.selectByPrimaryKey(id);
    }
}
