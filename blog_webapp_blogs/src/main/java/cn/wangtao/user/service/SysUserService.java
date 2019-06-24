package cn.wangtao.user.service;

import cn.wangtao.baseEntity.BaseServiceEntity;
import cn.wangtao.pojo.RequestEntity.SysUserModel;
import cn.wangtao.pojo.user.SysUser;

import java.util.List;

/**
 * @ClassName SysUserService
 * @Auth 桃子
 * @Date 2019-6-12 10:18
 * @Version 1.0
 * @Description
 **/
public  abstract class SysUserService extends BaseServiceEntity<SysUser,Long> {

    public abstract List<SysUser> selectByParams(SysUserModel params) throws Exception;

    public abstract SysUser selectByName(String userName) throws Exception;

    public abstract int deleteById(Long id,String userName) throws Exception;

    public abstract List<String> selectEmails() throws Exception;
}
