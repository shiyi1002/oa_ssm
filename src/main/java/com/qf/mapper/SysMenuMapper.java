package com.qf.mapper;

import com.qf.dao.IBaseDao;
import com.qf.entity.SysMenu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysMenuMapper extends IBaseDao<SysMenu>{

    List<SysMenu> getList();
    int updateFlagById(Long menuId);//夹删除
    int  queryByParentId(Long menuId);//查询组织有没有子组织
    int  queryByParentIds(List<Long> ids);
    int  updateFlagByIds(List<Long> ids);//批量假删除
    List<SysMenu> showMenuByCondition(SysMenu sysMenu);//搜索框
    int delAll(List<Long> ids);//批量删除

    List<SysMenu> showMenu();

    List<SysMenu> selectByRoleId(Long roleId);

    List<SysMenu> selectMenuByName(@Param("menuName") String menuName,@Param("roleId") Long roleId);

    int batchdel(List<Long> ids);
}