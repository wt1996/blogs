package cn.wangtao.mapper;

import cn.wangtao.baseEntity.BaseMapperEntity;
import cn.wangtao.pojo.blog.BlogCategory;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @ClassName ArticleCategoryMapper
 * @Auth 桃子
 * @Date 2019-6-11 17:33
 * @Version 1.0
 **/
@Repository
public interface BlogCategoryMapper extends BaseMapperEntity<BlogCategory,Long> {
    List<BlogCategory> selectAllByUserName(@Param("setCreateByName") String userName) throws  Exception;

    BlogCategory selectByIdAndUserName(@Param("categorySeq")Long id, @Param("userName") String userName) throws  Exception;

    int deleteByIdAndUserName(@Param("categorySeq")Long id, @Param("userName") String userName)throws  Exception;;
}
