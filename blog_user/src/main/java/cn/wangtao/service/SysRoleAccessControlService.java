package cn.wangtao.service;

import cn.wangtao.baseEntity.BaseServiceEntity;
import cn.wangtao.exception.ServiceException;
import cn.wangtao.pojo.user.SysRoleAccessControl;

/**
 * @ClassName SysRoleAccessControlService
 * @Auth 桃子
 * @Date 2019-5-30 10:04
 * @Version 1.0
 **/
public interface SysRoleAccessControlService{

    int insert(SysRoleAccessControl role) throws Exception;
    int deleteByTwoId(Long sysRoleSeq, Long sysAccessControlSeq) throws Exception;
}
