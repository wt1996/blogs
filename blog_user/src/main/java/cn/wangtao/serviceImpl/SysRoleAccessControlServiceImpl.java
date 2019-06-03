package cn.wangtao.serviceImpl;

import cn.wangtao.mapper.SysRoleAccessControlMapper;
import cn.wangtao.pojo.user.SysRoleAccessControl;
import cn.wangtao.service.SysRoleAccessControlService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @ClassName SysRoleAccessControlServiceImpl
 * @Auth 桃子
 * @Date 2019-5-30 10:04
 * @Version 1.0
 **/
@Service
@Slf4j
@Transactional
class SysRoleAccessControlServiceImpl implements SysRoleAccessControlService {

    @Autowired
    private SysRoleAccessControlMapper roleAccessControlMapper;

    @Override
    public int insert(SysRoleAccessControl role) throws Exception {
        role.setCreateDate(new Date());
        return roleAccessControlMapper.insert(role);
    }

    @Override
    public int deleteByTwoId(Long sysRoleSeq, Long sysAccessControlSeq) throws Exception {
        return roleAccessControlMapper.deleteByTwoId(sysRoleSeq,sysAccessControlSeq);
    }


}
