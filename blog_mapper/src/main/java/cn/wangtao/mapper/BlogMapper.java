package cn.wangtao.mapper;

import cn.wangtao.baseEntity.BaseMapperEntity;
import cn.wangtao.pojo.blog.Blog;
import org.springframework.stereotype.Repository;

/**
 * @ClassName ArticleMapper
 * @Auth 桃子
 * @Date 2019-6-11 17:23
 * @Version 1.0
 * @Description
 **/
@Repository
public interface BlogMapper extends BaseMapperEntity<Blog,Long> {
}
