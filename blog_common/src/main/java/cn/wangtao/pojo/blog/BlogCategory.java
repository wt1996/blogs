package cn.wangtao.pojo.blog;

import cn.wangtao.baseEntity.BasePojoEntity;
import cn.wangtao.utils.Constants;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @ClassName BlogCategory
 * @Auth 桃子
 * @Date 2019-5-14 10:49
 * @Version 1.0
 * @Description  用户个人菜单 可以直接删除
 **/
@Data
@Table(name="blog_category")
public  class BlogCategory extends BasePojoEntity implements Serializable {

    //分类序列号 自增
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(hidden = true)
    private Long categorySeq;

    //分类名称 唯一
    private String categoryName;

    //父类类别ID 默认是0L
    private Long  parentCategorySeq= Constants.PARENTCATEGORY;

    //分类描述
    private String categoryDesc;

}
