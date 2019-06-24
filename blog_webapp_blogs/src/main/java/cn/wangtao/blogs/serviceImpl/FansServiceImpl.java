package cn.wangtao.blogs.serviceImpl;

import cn.wangtao.baseEntity.BaseMapperEntity;
import cn.wangtao.mapper.FansMapper;
import cn.wangtao.pojo.cms.Fans;
import cn.wangtao.blogs.service.FansService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @ClassName FansServiceImpl
 * @Auth 桃子
 * @Date 2019-6-17 11:05
 * @Version 1.0
 * @Description
 **/
@Slf4j
@Transactional
@Service("fansServiceImpl")
public class FansServiceImpl extends FansService {

    @Autowired
    private FansMapper fansMapper;

    @Override
    public BaseMapperEntity<Fans, Long> getMapper() throws Exception {
        return this.fansMapper;
    }


    //添加关注
    @Override
    public int insert(Fans fans) throws Exception {
        fans.setCreateDate(new Date());
        return this.fansMapper.insert(fans);
    }

    //关注没有修改
    @Override
    public int update(Fans o) throws Exception {
        log.warn("关注中没有修改操作");
        return 0;
    }

    //查询有哪些粉丝
    @Override
    public List<Fans> selectFans(String authorName) throws Exception {
        return  this.fansMapper.selectFans(authorName);
    }

    //查询关注了哪些博主
    @Override
    public List<Fans> selectFocus(String userName) throws Exception {
        return this.fansMapper.selectFocus(userName);
    }

    @Override
    public Map<String, Integer> selectTwoFansNum(String userName) throws Exception {
        return this.fansMapper.selectTwoFansNum(userName);
    }

    @Override
    public int cancelByName(String authorName,String currentName) throws Exception {
        return this.fansMapper.cancelByName(authorName,currentName);
    }

    @Override
    public Fans selectByTwoName(String authorName, String currentName) throws Exception {
        return this.fansMapper.selectByTwoName(authorName,currentName);
    }
}
