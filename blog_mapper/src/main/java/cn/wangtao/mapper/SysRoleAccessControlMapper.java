package cn.wangtao.mapper;

import cn.wangtao.baseEntity.BaseMapperEntity;
import cn.wangtao.pojo.user.SysRoleAccessControl;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @ClassName SysRoleAccessControlMapper
 * @Auth 桃子
 * @Date 2019-5-30 10:04
 * @Version 1.0
 **/
@Repository
public interface SysRoleAccessControlMapper extends BaseMapperEntity<SysRoleAccessControl,Long> {
    int deleteByTwoId(@Param("sysRoleSeq") Long sysRoleSeq, @Param("sysAccessControlSeq") Long sysAccessControlSeq) throws  Exception;
}
