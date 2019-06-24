package cn.wangtao.user.serviceImpl;

import cn.wangtao.baseEntity.BaseMapperEntity;
import cn.wangtao.exception.ServiceException;
import cn.wangtao.mapper.SysUserMapper;
import cn.wangtao.pojo.RequestEntity.SysUserModel;
import cn.wangtao.pojo.user.SysUser;
import cn.wangtao.user.service.SysUserService;
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
@Slf4j
@Transactional
@Service(value = "sysUserService")
public class SysUserServiceImpl  extends SysUserService {

    @Autowired
    private SysUserMapper userMapper;

    @Override
    public BaseMapperEntity<SysUser, Long> getMapper() {
        return  this.userMapper;
    }


    @Override
    public List<SysUser> selectByParams(SysUserModel params) throws Exception {
        SysUser sysUser = new SysUser();
        BeanUtils.copyProperties(params,sysUser);
        return this.userMapper.selectByParams(sysUser);
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
        return this.userMapper.insert(user);
    }


    @Override
    public int update(SysUser user) throws Exception {
        //更新人，从session中获取
        user.setUpdateDate(new Date());
        return this.userMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public int deleteById(Long id,String userName) throws Exception {
        log.info("根据主键id删除对象 :[{}]",id);
        SysUser sysUser = this.selectById(id);
        if(sysUser!=null){
            //更新人，从session中获取
            sysUser.setSysUserStatus(Constants.STATUS_FORBIDDEN);//1 禁用
            sysUser.setUpdateByName(userName);
            return this.userMapper.updateByPrimaryKeySelective(sysUser);
        }else{
            throw new ServiceException("用户不存在","");
        }
    }

    @Override
    public List<String> selectEmails() throws Exception {
        return this.userMapper.selectEmails();
    }

    @Override
    public SysUser selectByName(String userName) throws Exception {
        return this.userMapper.selectByUserName(userName);
    }

    @Override
    public int deleteById(Long id) throws Exception {
        log.warn("用户表对于用户没有删除操作");
        return 0;
    }
}
