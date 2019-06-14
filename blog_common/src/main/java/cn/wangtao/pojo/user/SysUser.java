package cn.wangtao.pojo.user;

import cn.wangtao.baseEntity.BasePojoEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
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

    //自增
    @ApiModelProperty(value = "用户序列号",dataType = "long",required = true,hidden = true)
    @Column(name = "sys_user_seq")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sysUserSeq;

    @ApiModelProperty(value = "用户名",dataType = "string",required = true)
    private String userName;

    @ApiModelProperty(value = "密码",dataType = "string",required = true)
    @JsonIgnore
    private String password;

    @ApiModelProperty(value = "性别",dataType = "char",required = true)
    private Character sex;

    @ApiModelProperty(value = "年龄",dataType = "int",required = true)
    private Integer age;

    @ApiModelProperty(value = "生日(yyyyMMdd)",dataType = "string",required = true)
    private  String birthday;

    @ApiModelProperty(value = "邮箱",dataType = "string",required = true)
    private String email;

    @ApiModelProperty(value = "手机号",dataType = "string",required = true)
    private String phone;

    @ApiModelProperty(value = "用户头像地址",dataType = "string",required = true)
    private String userHeadImage;

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
    @ApiModelProperty(value = "拥有角色",dataType = "long",required = true,hidden = true)
    @Column(name="sys_role_seq")
    private Long sysRoleSeq;


}
