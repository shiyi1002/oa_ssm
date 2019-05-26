package com.qf.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qf.common.Page;
import com.qf.common.SysResult;
import com.qf.dao.IBaseDao;
import com.qf.entity.SysMenu;
import com.qf.mapper.SysMenuMapper;
import com.qf.service.ISysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class SysMenuServiceImpl extends BaseServiceImpl<SysMenu> implements ISysMenuService {
  @Autowired(required = false)
   private SysMenuMapper sysMenuMapper;
    @Override
    public List<SysMenu> getList() {
        return sysMenuMapper.getList();
    }

    @Override
    public int updateFlagById(Long menuId) {
        return sysMenuMapper.updateFlagById(menuId);
    }

    @Override
    public int queryByParentId(Long menuId) {
        return sysMenuMapper.queryByParentId(menuId);
    }

    @Override
    public int queryByParentIds(List<Long> ids) {
        return sysMenuMapper.queryByParentIds(ids);
    }

    @Override
    public int updateFlagByIds(List<Long> ids) {
        return 0;
    }

    @Override
    public List<SysMenu> showMenuByCondition(SysMenu sysMenu) {
        return null;
    }

    @Override
    public int delAll(List<Long> ids) {
        return 0;
    }

    @Override
    public List<SysMenu> showMenu() {
        return sysMenuMapper.showMenu();
    }




    @Override
    public PageInfo<SysMenu> selectByRoleId(Long roleId, Page page) {
        PageHelper.startPage(page.getCurrentPage(),page.getPageSize());
        List<SysMenu> list=sysMenuMapper.selectByRoleId(roleId);
        PageInfo<SysMenu> pageInfo=new PageInfo<>(list);
        return pageInfo;
    }

    @Override
    public PageInfo<SysMenu> selectMenuByName(String menuName, Long roleId, Page page) {
        PageHelper.startPage(page.getCurrentPage(),page.getPageSize());
        List<SysMenu> list=sysMenuMapper.selectMenuByName(menuName,roleId);
        PageInfo<SysMenu> pageInfo=new PageInfo<>(list);
        return pageInfo;

    }

    @Override
    public SysResult batchdel(List<Long> ids) {
        int result=sysMenuMapper.batchdel(ids);
        SysResult sysResult=new SysResult();
        if(result>0){
            sysResult.setResult(true);
            sysResult.setData("修改成功");
        }else{
            sysResult.setResult(false);
            sysResult.setData("修改失败");
        }
        return sysResult;

    }

    @Override
    public IBaseDao<SysMenu> getBaseDao() {
        return sysMenuMapper;
    }
}
