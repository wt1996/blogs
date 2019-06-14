package cn.wangtao.serviceImpl;

import cn.wangtao.baseEntity.BaseMapperEntity;
import cn.wangtao.mapper.BlogCategoryMapper;
import cn.wangtao.pojo.blog.BlogCategory;
import cn.wangtao.service.BlogCategoryService;
import cn.wangtao.utils.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;


/**
 * @ClassName BlogCategoryServiceImpl
 * @Auth 桃子
 * @Date 2019-6-12 9:27
 * @Version 1.0
 * @Description
 **/
@Transactional
@Slf4j
@Service("blogCategoryService")
public class BlogCategoryServiceImpl extends BlogCategoryService {

    @Autowired
    private BlogCategoryMapper blogCategoryMapper;

    @Override
    public BaseMapperEntity<BlogCategory, Long> getMapper() throws Exception {
        return this.blogCategoryMapper;
    }

    @Override
    public int insert(BlogCategory blogCategory) throws Exception {
        blogCategory.setCreateDate(new Date());
        return this.blogCategoryMapper.insert(blogCategory);
    }

    @Override
    public int update(BlogCategory blogCategory) throws Exception {
        blogCategory.setUpdateDate(new Date());
        return this.blogCategoryMapper.updateByPrimaryKeySelective(blogCategory);
    }

    @Override
    public List<BlogCategory> selectAll(String userName) throws Exception {
        return this.blogCategoryMapper.selectAllByUserName(userName);
    }

    @Override
    public BlogCategory selectById(Long id, String userName) throws Exception {
        return this.blogCategoryMapper.selectByIdAndUserName(id,userName);
    }

    @Override
    public int deleteById(Long id, String userName) throws Exception {
        return this.blogCategoryMapper.deleteByIdAndUserName(id,userName);
    }
}
