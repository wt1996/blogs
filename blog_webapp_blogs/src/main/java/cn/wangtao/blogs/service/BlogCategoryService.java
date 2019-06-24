package cn.wangtao.blogs.service;


import cn.wangtao.baseEntity.BaseServiceEntity;
import cn.wangtao.pojo.blog.BlogCategory;

import java.util.List;

/**
 * @ClassName BlogCategoryService
 * @Auth 桃子
 * @Date 2019-5-15 11:50
 * @Version 1.0
 **/
public abstract class BlogCategoryService extends BaseServiceEntity<BlogCategory,Long> {
    public abstract List<BlogCategory> selectAll(String userName) throws Exception;

    public abstract BlogCategory selectById(Long id, String userName) throws Exception;

    public abstract int deleteById(Long id, String userName) throws Exception;
}
