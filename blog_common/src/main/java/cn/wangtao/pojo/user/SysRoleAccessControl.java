package cn.wangtao.pojo.user;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName SysRoleAccessControl
 * @Auth 桃子
 * @Date 2019-5-14 12:38
 * @Version 1.0
 * @Description
 **/
@Data
@Table(name = "sys_role_access_control")
public class SysRoleAccessControl implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="role_access_seq")
    private Long roleAccessSeq;

    @ApiModelProperty(value = "角色序列号",dataType = "long",required = true)
    @Column(name = "sys_role_seq")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long sysRoleSeq;

    @ApiModelProperty(value = "功能序列号",dataType = "long",required = true)
    @Column(name = "sys_access_control_seq")
    @Id
    private long sysAccessControlSeq;

    @ApiModelProperty(value = "创建人姓名",dataType = "char",hidden = true)
    private  String createByName;

    @ApiModelProperty(value = "创建时间",dataType = "char",hidden = true)
    private Date createDate;
}
