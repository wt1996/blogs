package cn.wangtao.blogs.serviceImpl;

import cn.wangtao.baseEntity.BaseMapperEntity;
import cn.wangtao.exception.ServiceException;
import cn.wangtao.mapper.BlogMenuMapper;
import cn.wangtao.pojo.blog.BlogMenu;
import cn.wangtao.blogs.service.BlogMenuService;
import cn.wangtao.utils.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;


/**
 * @ClassName BlogMenuServiceImpl
 * @Auth 桃子
 * @Date 2019-6-12 9:27
 * @Version 1.0
 * @Description
 **/
@Transactional
@Slf4j
@Service("blogMenuService")
public class BlogMenuServiceImpl extends BlogMenuService {

    @Autowired
    private BlogMenuMapper blogMenuMapper;

    @Override
    public BaseMapperEntity<BlogMenu, Long> getMapper() throws Exception {
        return this.blogMenuMapper;
    }

    @Override
    public int insert(BlogMenu blogMenu) throws Exception {
        blogMenu.setCreateDate(new Date());
        blogMenu.setMenuStatus(Constants.STATUS__NORMAL);
        return this.blogMenuMapper.insert(blogMenu);
    }

    @Override
    public int update(BlogMenu blogMenu) throws Exception {
        blogMenu.setUpdateDate(new Date());
        return this.blogMenuMapper.updateByPrimaryKeySelective(blogMenu);
    }

    @Override
    public int deleteById(Long id, String userName) throws Exception {
        //进行修改系统菜单状态为禁止
        BlogMenu blogMenu = this.blogMenuMapper.selectByPrimaryKey(id);
        if(blogMenu!=null){
            blogMenu.setMenuStatus(Constants.STATUS_FORBIDDEN);//禁止表示删除
            blogMenu.setUpdateByName(userName);
            blogMenu.setUpdateDate(new Date());
            return this.blogMenuMapper.updateByPrimaryKeySelective(blogMenu);
        }else{
            throw  new ServiceException("系统菜单不存在","");
        }
    }
}
