package cn.wangtao.controller.user_controller;

import cn.wangtao.pojo.RequestEntity.SysUserModel;
import cn.wangtao.ResponseEntity.BlogResponse;
import cn.wangtao.pojo.user.SysUser;
import io.swagger.annotations.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName SysUserControllerApi
 * @Auth 桃子
 * @Date 2019-5-17 14:34
 * @Version 1.0
 * @Description
 **/
@Api(value = "用户信息API",description = "用户相关信息的API，包括CRUD，分页查询等")
public interface SysUserControllerApi {

    @ApiOperation("添加用户接口")
    BlogResponse insertSysUser(SysUser user, HttpServletRequest request);

    @ApiOperation("查询所有用户")
    BlogResponse selectAll();

    @ApiOperation( "根据多个字段查询用户，暂时是用模型接收的，会占用资源太多，后期会优化")
    BlogResponse selectByParams(SysUserModel params);

    @ApiOperation("根据用户字段查询用户")
    BlogResponse selectById(Long ids);



}
