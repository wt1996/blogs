package cn.wangtao.utils;

/**
 * @ClassName Constans
 * @Auth 桃子
 * @Date 2019-5-14 11:12
 * @Version 1.0
 * @Description  系统常量
 **/
public interface Constants {
    /**
     * 用户身份相关
     */

    Long USER_COMMON=1l;
    /**
     *  状态相关
     */

    Character STATUS__NORMAL='0';//正常
    Character STATUS_FORBIDDEN='1';//禁用

    //文章的发布状态 0:公开 1：私密
    Character POSTSTATUS_PUBLIC='0';
    Character POSTSTATUS_PRIVATE='1';

    //文章的状态:0 正在编辑，1 已完成，2 草稿箱
    Character ARTICLESTATUS_EDIT='0';
    Character ARTICLESTATUS_FINISHED='1';
    Character ARTICLESTATUS_DELETE='2';


    /**
     *  系统类别分类
     */
    String CATEGORYTYPE_LANGUAGE="categoryType_language"; //语言分类
    String CATEGORYTYPE_TECHNOLOGY="categoryType_technology"; //技术分类
    String CATEGORYTYPE_COMMUNITY="categoryType_community"; //语言分类
    String CATEGORYTYPE_USER="categoryType_user"; //用户自定义分类


    /**
     * 系统常量
     */
    String DEFAULTARTICLECATEGORY="未分类";

    Long PARENTCATEGORY=0L;

    /**
     * 点赞次数限制每次只能是1
     */
    int ADDNUM=1;
    int DESCNUM=-1;
}
