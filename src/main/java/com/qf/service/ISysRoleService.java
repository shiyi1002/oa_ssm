package com.qf.service;


import com.qf.common.SysResult;
import com.qf.entity.SysRole;
import com.qf.entity.SysUser;

import java.util.List;

public interface ISysRoleService extends IBaseService<SysRole> {
    List<SysRole> getList();
    int updateFlagById(Long roleId);//夹删除
    int  queryByParentId(Long roleId);//查询组织有没有子组织
    int  queryByParentIds(List<Long> ids);
    int  updateFlagByIds(List<Long> ids);//批量假删除
    List<SysRole> showRoleByCondition(SysRole sysRole);//搜索框
    int delAll(List<Long> ids);//批量删除

    SysResult batchAdd(List<Long> ids, Long roleId);

    SysResult delRoleAndUser(Long userId, Long roleId);

    SysResult batchAddMenu(List<Long> ids, Long roleId);

    SysResult delRoleAndMenu(Long menuId, Long roleId);
}
