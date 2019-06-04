package cn.wangtao.utils;

/**
 * @ClassName Constans
 * @Auth 桃子
 * @Date 2019-5-14 11:12
 * @Version 1.0
 * @Description  系统常量
 **/
public interface Constants {

    //用户类别
    Long USER_COMMON=1l;//普通用户

    char STATUS__NORMAL='0';//正常
    char STATUS_FORBIDDEN='1';//禁用

    String DEFAULTARTICLECATEGORY="未分类";

    /**
     *  系统类别分类
     */
    String CATEGORYTYPE_LANGUAGE="categoryType_language"; //语言分类
    String CATEGORYTYPE_TECHNOLOGY="categoryType_technology"; //技术分类
    String CATEGORYTYPE_COMMUNITY="categoryType_community"; //语言分类
    String CATEGORYTYPE_USER="categoryType_user"; //用户自定义分类



}
