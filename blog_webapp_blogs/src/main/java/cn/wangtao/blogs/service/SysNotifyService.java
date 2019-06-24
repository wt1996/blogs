package cn.wangtao.blogs.service;

import cn.wangtao.baseEntity.BaseServiceEntity;
import cn.wangtao.pojo.cms.SysNotify;

import java.util.List;

/**
 * @ClassName SysNotifyService
 * @Auth 桃子
 * @Date 2019-6-18 14:49
 * @Version 1.0
 * @Description
 **/
public abstract class SysNotifyService extends BaseServiceEntity<SysNotify,Long> {
    public abstract int updateForNotify(SysNotify sysNotify) throws  Exception;

    public abstract List<SysNotify> selectAllByStatus(Character notifyStatus)throws  Exception;
}
