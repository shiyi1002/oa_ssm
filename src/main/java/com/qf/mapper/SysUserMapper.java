package com.qf.mapper;

import com.qf.dao.IBaseDao;
import com.qf.entity.SysMenu;
import com.qf.entity.SysUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysUserMapper extends IBaseDao<SysUser>{

    List<SysUser> getList();
    List<SysUser> selectByCondition(SysUser sysUser);

    int delAll(List<Long> ids);
    List<SysUser> queryAllUserByRoleId(@Param("roleId") Long roleId);//查询这个角色下的用户

   
//这里参数前加了注解,sql语句就可以不写参数类型,这是两个参数类型,所以只有在这里加注解
    List<SysUser> selectUserByName(@Param("userName") String userName,@Param("roleId") Long roleId);

    List<SysUser> selAllProvince();

    SysUser checkUser(SysUser sysUser);

    List<SysMenu> getMenu(Long userId);

    void batchAdd(List<Long> ids, Long roleId);

    SysUser getUserByName(String username);

    List<SysUser> queryAuthUserByRoleId(Long roleId);

    List<SysUser> queryNoAuthUserByRoleId(@Param("roleId")Long roleId, @Param("userName")String userName);
}