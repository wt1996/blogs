package cn.wangtao.blogs.controller.user_controller;

import cn.wangtao.ResponseEntity.BlogResponse;
import cn.wangtao.pojo.user.SysRole;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName SysRoleControllerApi
 * @Auth 桃子
 * @Date 2019-5-15 11:49
 * @Version 1.0
 * @Description
 **/
@Api(value = "用户角色API",description = "角色相关的CRUD等接口")
public interface SysRoleControllerApi {

    @ApiOperation("查询所有角色信息")
    BlogResponse selectAll();

    @ApiOperation("根据ID查询角色信息")
    BlogResponse selectById(Long id);

    @ApiOperation("添加角色信息")
    BlogResponse insert( String roleName, HttpServletRequest request);

    @ApiOperation("修改角色信息")
    BlogResponse update(SysRole role,HttpServletRequest request);

    @ApiOperation("根据ID删除角色信息")
    BlogResponse deleteById(Long id, HttpServletRequest request);

}
