package cn.wangtao.baseEntity;


import java.io.Serializable;
import java.util.List;

/**
 * @ClassName BaseServiceEntity
 * @Auth 桃子
 * @Date 2019-5-14 12:46
 * @Version 1.0
 * @Description  针对普通的CRUD的创建
 **/
public  interface  BaseServiceEntity<T, E extends Serializable> {

    /**
     * BaseMapper
     */
     BaseMapperEntity<T, E> getMappser() throws Exception;

    /**
     * 查询
     */
     T selectOne(T o) throws Exception;

     List<T> selectAll() throws Exception;

     List<T> selectByIds(String ids) throws Exception;

     T selectById(E id) throws Exception;


    /**
     * 添加
     */

     int insert(T o) throws Exception;

     int insertList(List<T> list) throws Exception;

    /**
     * 修改
     */
     int update(T o) throws Exception;


    /**
     * 删除
     */
     int delete(T o) throws Exception;

     int deleteById(E id) throws Exception;
}
