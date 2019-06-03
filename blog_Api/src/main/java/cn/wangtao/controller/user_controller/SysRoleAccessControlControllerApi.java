package cn.wangtao.controller.user_controller;

import cn.wangtao.ResponseEntity.BlogResponse;
import cn.wangtao.pojo.user.SysRoleAccessControl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName SysRoleAccessControlControllerApi
 * @Auth 桃子
 * @Date 2019-5-30 15:55
 * @Version 1.0
 **/
@Api(value = "角色权限管理API",description ="对角色的权限进行增加，删除等接口")
public interface SysRoleAccessControlControllerApi {

    @ApiOperation("添加角色权限接口")
    BlogResponse insert(SysRoleAccessControl roleAccessControl, HttpServletRequest request) ;

    @ApiOperation("删除角色权限接口")
    BlogResponse deleteByTwoId(Long sysRoleSeq, Long sysAccessControlSeq);
}
