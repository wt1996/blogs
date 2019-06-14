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
import java.util.Date;

/**
 * @ClassName Comment
 * @Auth 桃子
 * @Date 2019-6-11 13:54
 * @Version 1.0
 * @Description 评论  可以直接删除
 **/
@Data
@Table(name="comment")
public class  Comment  implements Serializable {

    //序列号 自增
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(hidden = true)
    private Long  comSeq;

    //文章序列号
    private Long blogSeq;

    //评论的内容
    private String comContent;

    //评论人的头像 URL
    private String userHeadImage;

    //上一级评论 默认是没有上级为0L
    private Long comParent= Constants.PARENTCATEGORY;

    //是否是博主的评论 0:不是 1:是
    private char isAdmin;

    //点赞数量
    private Integer likeNum;

    @ApiModelProperty(value = "评论者姓名", hidden = true)
    private String createByName;

    @ApiModelProperty(value = "创建时间", hidden = true)
    private Date createDate;

}
