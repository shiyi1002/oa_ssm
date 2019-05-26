package com.qf.service.impl;

import com.qf.common.SysResult;
import com.qf.dao.IBaseDao;
import com.qf.entity.SysRole;
import com.qf.entity.SysUser;
import com.qf.mapper.SysRoleMapper;
import com.qf.mapper.SysUserMapper;
import com.qf.service.ISysRoleService;
import com.qf.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysRoleServiceImpl extends BaseServiceImpl<SysRole> implements ISysRoleService {
   @Autowired(required = false)
    private SysRoleMapper sysRoleMapper;

    @Override
    public IBaseDao<SysRole> getBaseDao() {
        return sysRoleMapper;
    }

    @Override
    public List<SysRole> getList() {
        return sysRoleMapper.getList();
    }

    @Override
    public int updateFlagById(Long roleId) {
        return sysRoleMapper.updateFlagById(roleId);
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
        return sysRoleMapper.updateFlagByIds(ids);
    }

    @Override
    public List<SysRole> showRoleByCondition(SysRole sysRole) {
        return null;
    }

    @Override
    public int delAll(List<Long> ids) {
        return sysRoleMapper.delAll(ids);
    }

    @Override
    public SysResult batchAdd(List<Long> ids, Long roleId) {
        int result=sysRoleMapper.batchAdd(ids,roleId);
        SysResult sysResult=new SysResult();
        if(result>0){
            sysResult.setResult(true);

        }else{

            sysResult.setResult(false);

        }
        return sysResult;

    }

    @Override
    public SysResult delRoleAndUser(Long userId, Long roleId) {
        int result=sysRoleMapper.delRoleAndUser(userId,roleId);
        SysResult sysResult=new SysResult();
        if(result>0){
            sysResult.setResult(true);

        }else{

            sysResult.setResult(false);

        }
        return sysResult;
    }

    @Override
    public SysResult batchAddMenu(List<Long> ids, Long roleId) {
        int result = sysRoleMapper.batchAddMenu(ids, roleId);
        SysResult sysResult = new SysResult();
        if (result > 0) {
            sysResult.setResult(true);

        } else {

            sysResult.setResult(false);

        }
        return sysResult;
    }

    @Override
    public SysResult delRoleAndMenu(Long menuId, Long roleId) {
        int result=sysRoleMapper.delRoleAndMenu(menuId,roleId);
        SysResult sysResult=new SysResult();
        if(result>0){
            sysResult.setResult(true);

        }else{

            sysResult.setResult(false);

        }
        return sysResult;
    }
}
