package cn.wangtao.controller.user_controller;

import cn.wangtao.ResponseEntity.BlogResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName LoginControllerApi
 * @Auth 桃子
 * @Date 2019-5-28 17:01
 * @Version 1.0
 **/
@Api(value = "用户登录API",description = "用户登录接口")
public interface LoginControllerApi {

    @ApiOperation("用户登录接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name="userName",value = "用户名",required=true,paramType="query",dataType="String"),
            @ApiImplicitParam(name="password",value = "登录密码",required=true,paramType="query",dataType="String")
    })
    BlogResponse login(@RequestParam("userName") String userName, @RequestParam("password")String password, HttpServletRequest request);
}
