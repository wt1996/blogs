package cn.wangtao.user.serviceImpl;

import cn.wangtao.baseEntity.BaseMapperEntity;
import cn.wangtao.mapper.SysAccessControlMapper;
import cn.wangtao.pojo.user.SysAccessControl;
import cn.wangtao.user.service.SysAccessControlService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @ClassName SysAccessControlServiceImpl
 * @Auth 桃子
 * @Date 2019-5-30 10:04
 * @Version 1.0
 **/
@Slf4j
@Transactional
@Service("accessControlService")
public class SysAccessControlServiceImpl extends SysAccessControlService {

    @Autowired
    private SysAccessControlMapper accessControlMapper;

    public BaseMapperEntity<SysAccessControl, Long> getMapper() {
        return  this.accessControlMapper;
    }

    @Override
    public int insert(SysAccessControl accessControl) throws Exception {
        accessControl.setCreateDate(new Date());
        return this.accessControlMapper.insert(accessControl);
    }


    @Override
    public int update(SysAccessControl accessControl) throws Exception {
        //更新人，从session中获取
        accessControl.setUpdateDate(new Date());
        return this.accessControlMapper.updateByPrimaryKeySelective(accessControl);
    }

}


