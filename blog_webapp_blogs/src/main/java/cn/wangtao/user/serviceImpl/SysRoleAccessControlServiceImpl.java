package cn.wangtao.user.serviceImpl;

import cn.wangtao.mapper.SysRoleAccessControlMapper;
import cn.wangtao.pojo.user.SysRoleAccessControl;
import cn.wangtao.user.service.SysRoleAccessControlService;
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
@Slf4j
@Transactional
@Service("sysRoleAccessControlServiceImpl")
class SysRoleAccessControlServiceImpl implements SysRoleAccessControlService {

    @Autowired
    private SysRoleAccessControlMapper roleAccessControlMapper;

    @Override
    public int insert(SysRoleAccessControl role) throws Exception {
        role.setCreateDate(new Date());
        return this.roleAccessControlMapper.insert(role);
    }

    @Override
    public int deleteByTwoId(Long sysRoleSeq, Long sysAccessControlSeq) throws Exception {
        return this.roleAccessControlMapper.deleteByTwoId(sysRoleSeq,sysAccessControlSeq);
    }


}
