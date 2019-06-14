package cn.wangtao.pojo.user;

import cn.wangtao.baseEntity.BasePojoEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
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

    //自增
    @ApiModelProperty(value = "功能序列号",dataType = "long",required = true,hidden = true)
    @Column(name = "sys_access_control_seq")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long sysAccessControlSeq;

    @ApiModelProperty(value = "功能名",dataType = "string",required = true)
    private String accessControlName;

    @ApiModelProperty(value = "功能英语别名",dataType = "string",required = true)
    @Column(name = "access_control_en_name")
    private String accessControlEnName;
}
