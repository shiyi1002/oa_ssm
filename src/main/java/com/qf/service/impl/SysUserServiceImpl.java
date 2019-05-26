package com.qf.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qf.common.Page;
import com.qf.dao.IBaseDao;
import com.qf.entity.SysMenu;
import com.qf.entity.SysUser;

import com.qf.mapper.SysUserMapper;
import com.qf.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysUserServiceImpl extends BaseServiceImpl<SysUser> implements ISysUserService {
   @Autowired(required = false)
    private SysUserMapper sysUserMapper;

    @Override
    public IBaseDao<SysUser> getBaseDao() {
        return sysUserMapper;
    }

    @Override
    public List<SysUser> getList() {
        return sysUserMapper.getList();
    }

    @Override
    public int updateFlagById(Long userId) {
        return 0;
    }

    @Override
    public int queryByParentId(Long userId) {
        return 0;
    }

    @Override
    public int queryByParentIds(List<Long> ids) {
        return 0;
    }

    @Override
    public int updateFlagByIds(List<Long> ids) {
        return 0;
    }



    @Override
    public int delAll(List<Long> ids) {
        return sysUserMapper.delAll(ids);
    }

    @Override
    public List<SysUser> queryAllUserByRoleId(Long roleId) {
        return sysUserMapper.queryAllUserByRoleId(roleId);
    }



    @Override
    public List<SysUser> selectUserByName(String userName, Long roleId) {
        return sysUserMapper.selectUserByName(userName,roleId);
    }

    @Override
    public List<SysUser> selAllProvince() {
        return sysUserMapper.selAllProvince();
    }

    @Override
    public SysUser checkUser(SysUser sysUser) {
        SysUser sysUser1=sysUserMapper.checkUser(sysUser);

        return sysUser1;
    }

    @Override
    public List<SysMenu> getMenu(Long userId) {
        return sysUserMapper.getMenu(userId);
    }

    @Override
    public int batchAdd(List<Long> ids, Long roleId) {
        sysUserMapper.batchAdd(ids,roleId);
        return 0;
    }

    @Override
    public SysUser getUserByName(String username) {
        return sysUserMapper.getUserByName(username);
    }

    public PageInfo<SysUser> selectByCondition(Page page, SysUser sysUser) {
        PageHelper.startPage(page.getCurrentPage(),page.getPageSize());
        List<SysUser> sysUserList =  sysUserMapper.selectByCondition(sysUser);
        PageInfo<SysUser> pageInfo = new PageInfo<SysUser>(sysUserList);
        return pageInfo;
    }

    @Override
    public PageInfo<SysUser> queryAuthUserByRoleId(Long roleId, Page page) {
        PageHelper.startPage(page.getCurrentPage(),page.getPageSize());
        List<SysUser> sysUserList =  sysUserMapper.queryAuthUserByRoleId(roleId);
        PageInfo<SysUser> pageInfo = new PageInfo<SysUser>(sysUserList);
        return pageInfo;
    }

    @Override
    public PageInfo<SysUser> queryNoAuthUserByRoleId(Long roleId, String userName, Page page) {
        PageHelper.startPage(page.getCurrentPage(),page.getPageSize());
        List<SysUser> sysUserList =  sysUserMapper.queryNoAuthUserByRoleId(roleId,userName);
        PageInfo<SysUser> pageInfo = new PageInfo<SysUser>(sysUserList);
        return pageInfo;
    }

}
