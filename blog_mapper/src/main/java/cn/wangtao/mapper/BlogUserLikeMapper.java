package cn.wangtao.mapper;

import cn.wangtao.baseEntity.BaseMapperEntity;
import cn.wangtao.pojo.blog.BlogUserLike;
import org.springframework.stereotype.Repository;

/**
 * @ClassName BlogUserMapper
 * @Auth 桃子
 * @Date 2019-6-13 16:33
 * @Version 1.0
 **/
@Repository
public interface BlogUserLikeMapper extends BaseMapperEntity<BlogUserLike,Long> {
    BlogUserLike selectByTwo(Long blogSeq, Long sysUserSeq) throws Exception;

    int deleteByTwo(Long blogSeq, Long sysUserSeq) throws Exception;
}
