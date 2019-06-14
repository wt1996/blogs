package cn.wangtao.serviceImpl;

import cn.wangtao.baseEntity.BaseMapperEntity;
import cn.wangtao.mapper.SysRoleMapper;
import cn.wangtao.pojo.user.SysRole;
import cn.wangtao.service.SysRoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @ClassName SysRoleServiceImpl
 * @Auth 桃子
 * @Date 2019-5-30 10:04
 * @Version 1.0
 **/
@Slf4j
@Transactional
@Service("roleService")
public class SysRoleServiceImpl extends SysRoleService {

    @Autowired
    private SysRoleMapper roleMapper;

    @Override
    public BaseMapperEntity<SysRole, Long> getMapper() {
        return  this.roleMapper;
    }


    @Override
    public int insert(SysRole role) throws Exception {
        role.setCreateDate(new Date());
        return this.roleMapper.insert(role);
    }


    @Override
    public int update(SysRole role) throws Exception {
        //更新人，从session中获取
        role.setUpdateDate(new Date());
        return this.roleMapper.updateByPrimaryKeySelective(role);
    }
}
