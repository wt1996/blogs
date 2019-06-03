package cn.wangtao.mapper;

import cn.wangtao.baseEntity.BaseMapperEntity;
import cn.wangtao.pojo.user.SysUser;
import org.springframework.stereotype.Repository;

/**
 * @ClassName SysUserMapper
 * @Auth 桃子
 * @Date 2019-5-17 14:28
 * @Version 1.0
 **/
@Repository
public interface SysUserMapper extends  BaseMapperEntity<SysUser,Long>{
    SysUser selectByUserName(String userName) throws Exception;
}
