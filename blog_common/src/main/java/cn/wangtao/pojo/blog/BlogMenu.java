package cn.wangtao.pojo.blog;

import cn.wangtao.baseEntity.BasePojoEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @ClassName BlogMenu
 * @Auth 桃子
 * @Date 2019-6-11 14:10
 * @Version 1.0
 * @Description 文章标签：系统菜单，标签等
 **/
@Data
@Table(name="blog_menu")
public class BlogMenu extends BasePojoEntity implements Serializable {

    //序列号 自增
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(hidden = true)
    private Long menuSeq;

    //文章的标签名称 唯一
    private String menuName;

    //文章的标签状态 0：正常 1：禁用
    @ApiModelProperty(hidden = true)
    private char menuStatus;

    //标签描述
    private String menuDesc;

}
