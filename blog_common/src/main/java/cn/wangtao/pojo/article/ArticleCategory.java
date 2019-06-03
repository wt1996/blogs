package cn.wangtao.pojo.article;

import cn.wangtao.baseEntity.BasePojoEntity;
import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName ArticleCategory
 * @Auth 桃子
 * @Date 2019-5-14 10:49
 * @Version 1.0
 * @Description  文章类别，系统创建的，用户创建的实体响应，但是分表保存（用户的自定义类别）
 **/
@Data
public abstract class ArticleCategory extends BasePojoEntity implements Serializable {

    private String categoryName;

    /**
     * 类别所属类别：语言类别，技术类别....
     */
    private String categoryType;

    /**
     * 类别状态:0 正常，1 禁用
     */
    private char categoryStatus;

    /**
     * 父类类别ID
     */
    private String  parentCategoryId;

}
