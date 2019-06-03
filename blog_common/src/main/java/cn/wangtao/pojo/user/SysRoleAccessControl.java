package cn.wangtao.pojo.user;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
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

    @ApiModelProperty(value = "角色序列号",dataType = "long",required = true)
    @Column(name = "sys_role_seq")
    @Id
    private long sysRoleSeq;

    @ApiModelProperty(value = "功能序列号",dataType = "long",required = true)
    @Column(name = "sys_access_control_seq")
    @Id
    private long sysAccessControlSeq;

    @ApiModelProperty(value = "创建人",dataType = "char",hidden = true)
    private  Long createBy;

    @ApiModelProperty(value = "创建时间",dataType = "char",hidden = true)
    private Date createDate;
}
