package cn.wangtao.baseEntity;



import cn.wangtao.pojo.RequestEntity.SysUserModel;
import cn.wangtao.pojo.user.SysUser;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName BaseMapperEntity
 * @Auth 桃子
 * @Date 2019-5-14 12:50
 * @Version 1.0
 * @Description
 **/
public interface BaseMapperEntity<T, E extends Serializable> extends Mapper<T>, MySqlMapper<T> {
    int deleteByPrimaryKeys(String ids) throws Exception;

    List<T> selectByPrimaryKeys() throws Exception;

    List<T> selectByParams(SysUser params) throws Exception;
}
