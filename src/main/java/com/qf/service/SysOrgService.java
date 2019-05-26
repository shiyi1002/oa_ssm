package com.qf.service;

import com.github.pagehelper.PageInfo;
import com.qf.common.Page;
import com.qf.common.SysResult;
import com.qf.entity.SysOrg;

import java.util.List;

public interface SysOrgService extends IBaseService<SysOrg> {
    PageInfo<SysOrg> getPage(Page page);

    List<SysOrg> getOrgList();

    SysResult updateFlagByOrgId(Long orgId);

    SysResult updateFlagByIdList(List<Long> idList);

    PageInfo<SysOrg> selectByCondition(SysOrg sysOrg, Page page);
}
