package cn.wangtao.baseEntity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import springfox.documentation.annotations.ApiIgnore;

import java.util.Date;

/**
 * @ClassName BasePojoEntity
 * @Auth 桃子
 * @Date 2019-5-14 12:33
 * @Version 1.0
 * @Description
 **/
@Data
public class BasePojoEntity {

    @ApiModelProperty(value = "创建人", hidden = true)
    private String createBy;

    @ApiModelProperty(value = "创建时间", hidden = true)
    private Date createDate;

    @ApiModelProperty(value = "修改人", hidden = true)
    private String updateBy;

    @ApiModelProperty(value = "修改时间", hidden = true)
    private Date updateDate;

}
