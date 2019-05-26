package com.qf.service;


import com.github.pagehelper.PageInfo;
import com.qf.common.Page;
import com.qf.entity.SysMenu;
import com.qf.entity.SysUser;

import java.util.List;

public interface ISysUserService extends IBaseService<SysUser> {
    List<SysUser> getList();
    int updateFlagById(Long userId);//夹删除
    int  queryByParentId(Long userId);//查询组织有没有子组织
    int  queryByParentIds(List<Long> ids);
    int  updateFlagByIds(List<Long> ids);//批量假删除

    int delAll(List<Long> ids);//批量删除
    List<SysUser> queryAllUserByRoleId(Long roleId);//通过角色id查用户



    List<SysUser> selectUserByName(String userName, Long roleId);

    List<SysUser> selAllProvince();

    SysUser checkUser(SysUser sysUser);

    List<SysMenu> getMenu(Long userId);

    int batchAdd(List<Long> ids, Long roleId);

    SysUser getUserByName(String username);
    PageInfo<SysUser> selectByCondition(Page page, SysUser sysUser);

    PageInfo<SysUser> queryAuthUserByRoleId(Long roleId, Page page);
    //查询没有授权的用户
    PageInfo<SysUser> queryNoAuthUserByRoleId(Long roleId, String userName, Page page);
}
