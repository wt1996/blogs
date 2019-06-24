package cn.wangtao.blogs.serviceImpl;

import cn.wangtao.baseEntity.BaseMapperEntity;
import cn.wangtao.mapper.SysCmsMapper;
import cn.wangtao.pojo.cms.SysCms;
import cn.wangtao.blogs.service.SysCmsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @ClassName SysCmsServiceImpl
 * @Auth 桃子
 * @Date 2019-6-18 14:52
 * @Version 1.0
 * @Description
 **/
@Slf4j
@Transactional
@Service("sysCmsServiceImpl")
public class SysCmsServiceImpl extends SysCmsService {

    @Autowired
    private SysCmsMapper sysCmsMapper;

    @Override
    public BaseMapperEntity<SysCms, Long> getMapper() throws Exception {
        return this.sysCmsMapper;
    }

    @Override
    public int insert(SysCms sysCms) throws Exception {
        sysCms.setCreateDate(new Date());
        return this.sysCmsMapper.insert(sysCms);
    }

    @Override
    public int update(SysCms sysCms) throws Exception {
        sysCms.setUpdateDate(new Date());
        return this.sysCmsMapper.updateByPrimaryKeySelective(sysCms);
    }

    @Override
    public List<SysCms> selectAllDesc() throws Exception {
        return this.sysCmsMapper.selectAllDesc();
    }
}
