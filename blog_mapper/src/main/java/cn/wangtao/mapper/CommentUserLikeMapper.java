package cn.wangtao.mapper;

import cn.wangtao.baseEntity.BaseMapperEntity;
import cn.wangtao.pojo.blog.CommentUserLike;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @ClassName CommentUserMapper
 * @Auth 桃子
 * @Date 2019-6-13 16:30
 * @Version 1.0
 **/
@Repository
public interface CommentUserLikeMapper extends BaseMapperEntity<CommentUserLike,Long> {
    int deleteByTwo(@Param("comSeq") Long comSeq, @Param("userSeq") Long userSeq) throws Exception;

    CommentUserLike selectByTwo(@Param("comSeq") Long comSeq, @Param("userSeq") Long userSeq)throws Exception;
}
