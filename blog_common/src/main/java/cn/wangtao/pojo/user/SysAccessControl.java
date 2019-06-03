package cn.wangtao.pojo.user;

import cn.wangtao.baseEntity.BasePojoEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @ClassName SysAccessControl
 * @Auth 桃子
 * @Date 2019-5-14 12:31
 * @Version 1.0
 * @Description 角色所拥有的权限功能
 **/
@Data
@Table(name = "sys_access_control")
public class SysAccessControl extends BasePojoEntity implements Serializable {

    @ApiModelProperty(value = "功能序列号",dataType = "long",required = true,hidden = true)
    @Column(name = "sys_access_control_seq")
    @Id
    private long sysAccessControlSeq;

    @ApiModelProperty(value = "功能名",dataType = "string",required = true)
    private String accessControlName;
}
