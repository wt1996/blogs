package cn.wangtao.serviceImpl;

import cn.wangtao.exception.ServiceException;
import cn.wangtao.pojo.RequestEntity.SysUserModel;
import cn.wangtao.baseEntity.BaseMapperEntity;
import cn.wangtao.mapper.SysUserMapper;
import cn.wangtao.pojo.user.SysUser;
import cn.wangtao.service.SysUserService;
import cn.wangtao.utils.Constants;
import cn.wangtao.utils.SecurityUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;

/**
 * @ClassName SysUserServiceImpl
 * @Auth 桃子
 * @Date 2019-5-17 15:24
 * @Version 1.0
 * @Description
 **/
@Service
@Slf4j
@Transactional
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private SysUserMapper userMapper;

    @Override
    public BaseMapperEntity<SysUser, Long> getMappser() {
        return  this.userMapper;
    }

    @Override
    public SysUser selectOne(SysUser user) throws Exception {
        return userMapper.selectOne(user);
    }

    @Override
    public List<SysUser> selectAll() throws Exception {
        log.info("查询所有的SysUser对象");
        return userMapper.selectAll();
    }

    @Override
    public List<SysUser> selectByIds(String ids) throws Exception {
        log.info("根据多个主键id查询SysUser对象:[{}]",ids);
        return userMapper.selectByPrimaryKeys();
    }


    @Override
    public SysUser selectById(Long id) throws Exception {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<SysUser> selectByParams(SysUserModel params) throws Exception {
        SysUser sysUser = new SysUser();
        BeanUtils.copyProperties(params,sysUser);
        return userMapper.selectByParams(sysUser);
    }

    @Override
    public int insert(SysUser user) throws Exception {
        user.setSysUserStatus(Constants.STATUS__NORMAL);//0 正常
        user.setCreateDate(new Date());
        //密码加密
        String password="";
        try {
            password= SecurityUtil.md5Encrypt(user.getPassword(), user.getUserName());
        } catch (UnsupportedEncodingException e) {
            log.error("对密码加密时异常",e);
            password=user.getPassword();
        }
        user.setPassword(password);
        return userMapper.insert(user);
    }

    @Override
    public int insertList(List<SysUser> list) throws Exception {
        log.info("添加多个SysUser对象:[{}]",list);
        return userMapper.insertList(list);
    }

    @Override
    public int update(SysUser user) throws Exception {
        //更新人，从session中获取
        user.setUpdateDate(new Date());
        return userMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public int deleteById(Long id,String userName) throws Exception {
        log.info("根据主键id删除对象 :[{}]",id);
        SysUser sysUser = selectById(id);
        if(sysUser!=null){
            //更新人，从session中获取
            sysUser.setSysUserStatus(Constants.STATUS_FORBIDDEN);//1 禁用
            sysUser.setUpdateBy(userName);
            return userMapper.updateByPrimaryKeySelective(sysUser);
        }else{
            throw new ServiceException("用户不存在","");
        }
    }

    @Override
    public SysUser selectByName(String userName) throws Exception {
        return userMapper.selectByUserName(userName);
    }

    //以下暂时不用
    @Override
    public int delete(SysUser user) throws Exception {
        //log.info("根据SysUser属性删除对象:[{}]",user);
        return 0;
    }

    @Override
    public int deleteById(Long id) throws Exception {
        return 0;
    }
}
