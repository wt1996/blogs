package cn.wangtao.service;

import cn.wangtao.pojo.user.SysRoleAccessControl;

/**
 * @ClassName SysRoleAccessControlService
 * @Auth 桃子
 * @Date 2019-6-12 10:11
 * @Version 1.0
 **/
public interface SysRoleAccessControlService {
    int insert(SysRoleAccessControl role) throws Exception;
    int deleteByTwoId(Long sysRoleSeq, Long sysAccessControlSeq) throws Exception;
}
