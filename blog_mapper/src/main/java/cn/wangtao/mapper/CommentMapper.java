package cn.wangtao.mapper;

import cn.wangtao.baseEntity.BaseMapperEntity;
import cn.wangtao.pojo.blog.Comment;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @ClassName CommentMapper
 * @Auth 桃子
 * @Date 2019-6-11 17:34
 * @Version 1.0
 **/
@Repository
public interface CommentMapper extends BaseMapperEntity<Comment,Long> {

    List<Comment> selectByBlog(Long blogSeq) throws Exception;

    List<Comment> selectByComUser(String userName) throws Exception;
}
