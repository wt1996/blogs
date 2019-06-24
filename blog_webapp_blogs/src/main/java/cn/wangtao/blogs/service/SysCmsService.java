package cn.wangtao.blogs.service;

import cn.wangtao.baseEntity.BaseServiceEntity;
import cn.wangtao.pojo.cms.SysCms;

import java.util.List;

/**
 * @ClassName SysCmsService
 * @Auth 桃子
 * @Date 2019-6-18 14:49
 * @Version 1.0
 * @Description
 **/
public abstract class SysCmsService extends BaseServiceEntity<SysCms,Long> {
    public abstract List<SysCms> selectAllDesc() throws Exception;
}
