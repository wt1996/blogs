package cn.wangtao.pojo.blog;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName BlogDetail
 * @Auth 桃子
 * @Date 2019-5-14 10:49
 * @Version 1.0
 * @Description:文章的详细内容序列号跟文章的序列号保持一致
 **/
@Data
@Table(name="blog_detail")
public class BlogDetail implements Serializable {

    //序列号
    @Id
    @ApiModelProperty(hidden = true)
    private  Long detailSeq;

    //文章内容
    private String detailContent;

    //创建时间
    @ApiModelProperty(hidden = true)
    private Date createDate;

    //创建时间
    @ApiModelProperty(hidden = true)
    private Date updateDate;

}