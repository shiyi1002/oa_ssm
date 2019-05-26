package com.qf.mapper;

import com.qf.dao.IBaseDao;
import com.qf.entity.SysRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysRoleMapper extends IBaseDao<SysRole>{
    List<SysRole> getList();
    int updateFlagById(Long roleId);//夹删除
    int  queryByParentId(Long roleId);//查询组织有没有子组织
    int  queryByParentIds(List<Long> ids);
    int  updateFlagByIds(List<Long> ids);//批量假删除
    List<SysRole> showRoleByCondition(SysRole sysRole);//搜索框
    int delAll(List<Long> ids);//批量删除

    int batchAdd(@Param("ids") List<Long> ids, @Param("roleId") Long roleId);

    int delRoleAndUser(@Param("userId") Long userId, @Param("roleId") Long roleId);

    int batchAddMenu(@Param("ids")List<Long> ids, @Param("roleId")Long roleId);

    int delRoleAndMenu(@Param("menuId") Long menuId, @Param("roleId") Long roleId);
}