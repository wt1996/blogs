package cn.wangtao.serviceImpl;

import cn.wangtao.baseEntity.BaseMapperEntity;
import cn.wangtao.mapper.SysAccessControlMapper;
import cn.wangtao.pojo.user.SysAccessControl;
import cn.wangtao.service.SysAccessControlService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @ClassName SysAccessControlServiceImpl
 * @Auth 桃子
 * @Date 2019-5-30 10:04
 * @Version 1.0
 **/
@Service
@Slf4j
@Transactional
public class SysAccessControlServiceImpl implements SysAccessControlService {

    @Autowired
    private SysAccessControlMapper accessControlMapper;

    @Override
    public BaseMapperEntity<SysAccessControl, Long> getMappser() {
        return  this.accessControlMapper;
    }

    @Override
    public SysAccessControl selectOne(SysAccessControl accessControl) throws Exception {
        return accessControlMapper.selectOne(accessControl);
    }

    @Override
    public List<SysAccessControl> selectAll() throws Exception {
        log.info("查询所有的SysAccessControl对象");
        return accessControlMapper.selectAll();
    }

    @Override
    public List<SysAccessControl> selectByIds(String ids) throws Exception {
        log.info("根据多个主键id查询SysAccessControl对象:[{}]",ids);
        return accessControlMapper.selectByPrimaryKeys();
    }

    @Override
    public SysAccessControl selectById(Long id) throws Exception {
        return accessControlMapper.selectByPrimaryKey(id);
    }

    @Override
    public int insert(SysAccessControl accessControl) throws Exception {
        accessControl.setCreateDate(new Date());
        return accessControlMapper.insert(accessControl);
    }

    @Override
    public int insertList(List<SysAccessControl> list) throws Exception {
        log.info("添加多个SysAccessControl对象:[{}]",list);
        return accessControlMapper.insertList(list);
    }

    @Override
    public int update(SysAccessControl accessControl) throws Exception {
        //更新人，从session中获取
        accessControl.setUpdateDate(new Date());
        return accessControlMapper.updateByPrimaryKeySelective(accessControl);
    }

    @Override
    public int deleteById(Long id) throws Exception {
            return accessControlMapper.deleteByPrimaryKey(id);
    }

    //以下暂时不用
    @Override
    public int delete(SysAccessControl accessControl) throws Exception {
        // log.info("根据SysAccessControl属性删除对象:[{}]",accessControl);
        return 0;
    }
}
