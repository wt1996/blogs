package cn.wangtao.pojo.RequestEntity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;

/**
 * @ClassName SysUserModel
 * @Auth 桃子
 * @Date 2019-5-28 9:04
 * @Version 1.0
 * @Description
 **/
@Data
public class SysUserModel {

    @ApiModelProperty(value = "用户序列号",dataType = "long",required = false)
    @Column(name = "sys_user_seq")
    @Id
    private Long sysUserSeq;

    @ApiModelProperty(value = "用户名",dataType = "string",required = false)
    private String userName;

    @ApiModelProperty(value = "密码",dataType = "string",required = false)
    private String password;

    @ApiModelProperty(value = "年龄",dataType = "int",required = false)
    private Integer age;

    @ApiModelProperty(value = "生日(yyyyMMdd)",dataType = "string",required = false)
    private  String birthday;

    @ApiModelProperty(value = "邮箱",dataType = "string",required = false)
    private String email;

    @ApiModelProperty(value = "手机号",dataType = "string",required = false)
    private String phone;

    @ApiModelProperty(value = "真实姓名",dataType = "string",required = false)
    private String realName;

    @ApiModelProperty(value = "真实生日(yyyyMMdd)",dataType = "string",required = false)
    private String realBirthday;

    /**
     * 用户的状态：0 正常,1 禁用
     */
    @ApiModelProperty(value = "用户的状态",dataType = "char",required = false)
    private Character sysUserStatus;

    /**
     * 拥有角色
     */
    @ApiModelProperty(value = "拥有角色",dataType = "string",required = false,hidden = true)
    private String sysRoleSeq;

    /**
     * 用户类型：0 系统用户，1 普通用户
     */
    @ApiModelProperty(value = "用户类型",dataType = "char",required = false,hidden = true)
    private Character userType;
}
