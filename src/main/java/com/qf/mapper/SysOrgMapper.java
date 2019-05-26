package com.qf.mapper;

import com.qf.dao.IBaseDao;
import com.qf.entity.SysOrg;

import java.util.List;

public interface SysOrgMapper extends IBaseDao<SysOrg> {

    List<SysOrg> getOrgList();

    int queryCountByOrgParentId(Long orgId);

    int queryCountByIdList(List<Long> idList);

    int batchUpdateFlagByIdList(List<Long> idList);

    List<SysOrg> selectByCondition(SysOrg sysOrg);
}
