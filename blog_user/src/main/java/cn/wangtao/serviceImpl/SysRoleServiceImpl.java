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
import java.util.List;

/**
 * @ClassName SysRoleServiceImpl
 * @Auth 桃子
 * @Date 2019-5-30 10:04
 * @Version 1.0
 **/
@Service
@Slf4j
@Transactional
public class SysRoleServiceImpl implements SysRoleService {

    @Autowired
    private SysRoleMapper roleMapper;

    @Override
    public BaseMapperEntity<SysRole, Long> getMappser() {
        return  this.roleMapper;
    }

    @Override
    public SysRole selectOne(SysRole role) throws Exception {
        return roleMapper.selectOne(role);
    }

    @Override
    public List<SysRole> selectAll() throws Exception {
        log.info("查询所有的SysRole对象");
        return roleMapper.selectAll();
    }

    @Override
    public SysRole selectById(Long id) throws Exception {
        return roleMapper.selectByPrimaryKey(id);
    }

    @Override
    public int insert(SysRole role) throws Exception {
        role.setCreateDate(new Date());
        return roleMapper.insert(role);
    }


    @Override
    public int update(SysRole role) throws Exception {
        //更新人，从session中获取
        role.setUpdateDate(new Date());
        return roleMapper.updateByPrimaryKeySelective(role);
    }

    @Override
    public int deleteById(Long id) throws Exception {
       return roleMapper.deleteByPrimaryKey(id);
    }

    //暂时不用
    @Override
    public int delete(SysRole role) throws Exception {
       // log.info("根据SysRole属性删除对象:[{}]",role);
        return 0;
    }

    @Override
    public List<SysRole> selectByIds(String ids) throws Exception {
        //log.info("根据多个主键id查询SysRole对象:[{}]",ids);
        return null;
    }

    @Override
    public int insertList(List<SysRole> list) throws Exception {
        //log.info("添加多个SysRole对象:[{}]",list);
        return 0;
    }
}
