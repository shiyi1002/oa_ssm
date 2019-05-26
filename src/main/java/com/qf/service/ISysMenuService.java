package com.qf.service;


import com.github.pagehelper.PageInfo;
import com.qf.common.Page;
import com.qf.common.SysResult;
import com.qf.entity.SysMenu;
import com.qf.entity.SysUser;

import java.util.List;

public interface ISysMenuService extends IBaseService<SysMenu> {
    List<SysMenu> getList();
    int updateFlagById(Long menuId);//夹删除
    int  queryByParentId(Long menuId);//查询组织有没有子组织
    int  queryByParentIds(List<Long> ids);
    int  updateFlagByIds(List<Long> ids);//批量假删除
    List<SysMenu> showMenuByCondition(SysMenu sysMenu);//搜索框
    int delAll(List<Long> ids);//批量删除

    List<SysMenu> showMenu();



   

    PageInfo<SysMenu> selectByRoleId(Long roleId, Page page);

    PageInfo<SysMenu> selectMenuByName(String menuName, Long roleId, Page page);

    SysResult batchdel(List<Long> ids);
}
