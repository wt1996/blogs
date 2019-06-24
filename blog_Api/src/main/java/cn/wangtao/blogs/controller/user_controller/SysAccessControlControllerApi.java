package cn.wangtao.blogs.controller.user_controller;

import cn.wangtao.ResponseEntity.BlogResponse;
import cn.wangtao.pojo.user.SysAccessControl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName SysAccessControlControllerApi
 * @Auth 桃子
 * @Date 2019-5-15 11:51
 * @Version 1.0
 * @Description
 **/
@Api(value = "用户功能API",description = "对于功能模块菜单的CRUD等接口")
public interface SysAccessControlControllerApi {

    @ApiOperation("查询所有功能信息")
    BlogResponse selectAll();

    @ApiOperation("根据ID查询功能信息")
    BlogResponse selectById(Long id);

    @ApiOperation("添加功能信息")
    BlogResponse insert( String accessControlName, HttpServletRequest request);

    @ApiOperation("修改功能信息")
    BlogResponse update(SysAccessControl role, HttpServletRequest request);

    @ApiOperation("根据ID删除功能信息")
    BlogResponse deleteById(Long id, HttpServletRequest request);
}
