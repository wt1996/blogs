package cn.wangtao.service;

import cn.wangtao.baseEntity.BaseServiceEntity;
import cn.wangtao.pojo.blog.BlogMenu;

/**
 * @ClassName BlogMenuService
 * @Auth 桃子
 * @Date 2019-6-12 9:21
 * @Version 1.0
 **/
public abstract class BlogMenuService extends BaseServiceEntity<BlogMenu,Long> {
    public abstract  int deleteById(Long id, String userName) throws Exception;
}
