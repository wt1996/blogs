package cn.wangtao.pojo.user;

import cn.wangtao.baseEntity.BasePojoEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @ClassName SysRole
 * @Auth 桃子
 * @Date 2019-5-14 12:23
 * @Version 1.0
 * @Description 角色
 **/
@Data
@Table(name="sys_role")
public class SysRole extends BasePojoEntity implements Serializable {

    @ApiModelProperty(value = "角色序列号",dataType = "long",required = true,hidden = true)
    @Column(name = "sys_role_seq")
    @Id
    private Long sysRoleSeq;

    @ApiModelProperty(value = "角色名",dataType = "string",required = true)
    private String roleName;

}
