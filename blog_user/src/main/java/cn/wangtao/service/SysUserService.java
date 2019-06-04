package cn.wangtao.service;

import cn.wangtao.pojo.RequestEntity.SysUserModel;
import cn.wangtao.baseEntity.BaseServiceEntity;
import cn.wangtao.exception.ServiceException;
import cn.wangtao.pojo.user.SysUser;

import java.util.List;

/**
 * @ClassName SysUserService
 * @Auth 桃子
 * @Date 2019-5-17 15:19
 * @Version 1.0
 **/
public interface SysUserService  extends BaseServiceEntity<SysUser,Long> {

    SysUser selectByName(String userName) throws Exception;

    List<SysUser> selectByParams(SysUserModel params) throws Exception;

    int deleteById(Long id, String userName)throws Exception;
}
