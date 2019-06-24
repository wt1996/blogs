package cn.wangtao.blogs.serviceImpl;

import cn.wangtao.baseEntity.BaseMapperEntity;
import cn.wangtao.exception.ServiceException;
import cn.wangtao.mapper.BlogDetailMapper;
import cn.wangtao.pojo.blog.BlogDetail;
import cn.wangtao.blogs.service.BlogDetailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Date;


/**
 * @ClassName BlogDetailServiceImpl
 * @Auth 桃子
 * @Date 2019-6-12 9:27
 * @Version 1.0
 * @Description
 **/
@Transactional
@Slf4j
@Service("blogDetailService")
public class BlogDetailServiceImpl extends BlogDetailService {

    @Autowired
    private BlogDetailMapper blogDetailMapper;

    @Override
    public BaseMapperEntity<BlogDetail, Long> getMapper() throws Exception {
        return this.blogDetailMapper;
    }

    @Override
    public int insert(BlogDetail blogDetail) throws Exception {
        if(StringUtils.isEmpty(blogDetail.getDetailSeq())){
            throw new ServiceException("文章详细记录的主键为空","");
        }
        blogDetail.setCreateDate(new Date());
        return this.blogDetailMapper.insert(blogDetail);
    }

    @Override
    public int update(BlogDetail blogDetail) throws Exception {
        blogDetail.setUpdateDate(new Date());
        return this.blogDetailMapper.updateByPrimaryKeySelective(blogDetail);
    }

}
