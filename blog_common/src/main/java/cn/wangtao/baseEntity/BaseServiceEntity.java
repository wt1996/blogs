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
public abstract class  BaseServiceEntity<T, E extends Serializable> {

    public abstract BaseMapperEntity<T, E> getMapper() throws Exception;

    /**
     * @Author wangtao
     * @Date 2019-6-12  10:31
     * @Param 对象模型参数
     * @return 符合的对象
     * @Description  根据对象属性进行查询对象
     **/
    public T selectOne(T o)throws Exception{
        return this.getMapper().selectOne(o);
    }

    /**
     * @Author wangtao
     * @Date 2019-6-12  10:31
     * @return 所有的记录
     * @Description 查询所有的对象
     **/
    public List<T> selectAll() throws Exception{
        return this.getMapper().selectAll();
    }
    /**
     * @Author wangtao
     * @Date 2019-6-12  10:31
     * @Param 主键id
     * @return 操作记录数
     * @Description 根据id进行删除对象--直接删除
     **/
    public T selectById(E id) throws Exception{
        return this.getMapper().selectByPrimaryKey(id);
    }

    /**
     * @Author wangtao
     * @Date 2019-6-12  10:31
     * @Param 主键id
     * @return 操作记录数
     * @Description 根据id进行删除对象--直接删除
     **/
    public int deleteById(E id) throws Exception{
        return this.getMapper().deleteByPrimaryKey(id);
    }

    /**
     * @Author wangtao
     * @Date 2019-6-12  10:32
     * @Param  原对象 o
     * @return 操作记录数
     * @Description 添加对象
     **/
    public abstract int insert(T o) throws Exception;

    /**
     * @Author wangtao
     * @Date 2019-6-12  10:32
     * @Param  原对象 o
     * @return 操作记录数
     * @Description 修改对象
     **/
    public abstract int update(T o) throws Exception;

}
