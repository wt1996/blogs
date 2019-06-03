package cn.wangtao.pojo.user;

import cn.wangtao.baseEntity.BasePojoEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @ClassName SysUserModel
 * @Auth 桃子
 * @Date 2019-5-14 12:02
 * @Version 1.0
 * @Description  用户信息
 **/
@Data
@Table(name="sys_user")
public class SysUser extends BasePojoEntity implements Serializable {

    @ApiModelProperty(value = "用户序列号",dataType = "long",required = true,hidden = true)
    @Column(name = "sys_user_seq")
    @Id
    private Long sysUserSeq;

    @ApiModelProperty(value = "用户名",dataType = "string",required = true)
    private String userName;

    @ApiModelProperty(value = "密码",dataType = "string",required = true)
    @JsonIgnore
    private String password;

    @ApiModelProperty(value = "年龄",dataType = "int",required = true)
    private Integer age;

    @ApiModelProperty(value = "生日(yyyyMMdd)",dataType = "string",required = true)
    private  String birthday;

    @ApiModelProperty(value = "邮箱",dataType = "string",required = true)
    private String email;

    @ApiModelProperty(value = "手机号",dataType = "string",required = true)
    private String phone;

    @ApiModelProperty(value = "真实姓名",dataType = "string",required = true,hidden = true)
    private String realName;

    @ApiModelProperty(value = "真实生日(yyyyMMdd)",dataType = "string",required = true,hidden = true)
    private String realBirthday;

    /**
     * 用户的状态：0 正常,1 禁用
     */
    @ApiModelProperty(value = "用户的状态",dataType = "char",required = true,hidden = true)
    @Column(name="sys_user_status")
    private Character sysUserStatus;

    /**
     * 拥有角色
     */
    @ApiModelProperty(value = "拥有角色",dataType = "string",required = true,hidden = true)
    @Column(name="sys_role_seq")
    private String sysRoleSeq;

    /**
     * 用户类型：0 系统用户，1 普通用户
     */
    @ApiModelProperty(value = "用户类型",dataType = "char",required = true,hidden = true)
    @Column(name="user_type")
    private Character userType;

}
