package cn.wangtao.blogs.serviceImpl;

import cn.wangtao.baseEntity.BaseMapperEntity;
import cn.wangtao.mapper.SysNotifyMapper;
import cn.wangtao.pojo.cms.SysNotify;
import cn.wangtao.blogs.service.SysNotifyService;
import cn.wangtao.utils.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @ClassName SysNotifyServiceImpl
 * @Auth 桃子
 * @Date 2019-6-18 14:52
 * @Version 1.0
 * @Description
 **/
@Slf4j
@Transactional
@Service("sysNotifyServiceImpl")
public class SysNotifyServiceImpl extends SysNotifyService {

    @Autowired
    private SysNotifyMapper sysNotifyMapper;

    @Override
    public BaseMapperEntity<SysNotify, Long> getMapper() throws Exception {
        return this.sysNotifyMapper;
    }

    @Override
    public int insert(SysNotify sysNotify) throws Exception {
        sysNotify.setCreateDate(new Date());
        sysNotify.setNotifyStatus(Constants.NOTIFY_NEW);
        return this.sysNotifyMapper.insert(sysNotify);
    }

    @Override
    public int update(SysNotify sysNotify) throws Exception {
        sysNotify.setUpdateDate(new Date());
        return this.sysNotifyMapper.updateByPrimaryKeySelective(sysNotify);
    }

    //通知之后的修改
    public int updateForNotify(SysNotify sysNotify) throws Exception {
        sysNotify.setNotifyDate(new Date());
        return this.sysNotifyMapper.updateByPrimaryKeySelective(sysNotify);
    }

    @Override
    public List<SysNotify> selectAllByStatus(Character notifyStatus) throws Exception {
        return this.sysNotifyMapper.selectAllByStatus(notifyStatus);
    }
}
